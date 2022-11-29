package com.maveric.balanceservice.controller;

import com.maveric.balanceservice.constant.ErrorSuccessMessageConstants;
import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.exception.AccountIdMismatchException;
import com.maveric.balanceservice.feignclient.AccountFeignService;
import com.maveric.balanceservice.model.Account;
import com.maveric.balanceservice.model.Balance;
import com.maveric.balanceservice.service.BalanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class BalanceServiceController {

    @Autowired
    BalanceService balanceService;

    @Autowired
    AccountFeignService accountFeignService;

    private static final Logger logger = LoggerFactory.getLogger(BalanceServiceController.class);



    @PutMapping("/accounts/{accountId}/balances/{balanceId}")
       // public ResponseEntity<BalanceDto> updateBalance(@Valid @RequestBody Balance balance, @PathVariable String accountId, @PathVariable String balanceId,@RequestHeader(value = "userId") String userId) {
        public ResponseEntity<BalanceDto> updateBalance(@Valid @RequestBody Balance balance, @PathVariable String accountId, @PathVariable String balanceId) {
        if (balance.getAccountId().equals(accountId)) {
            //ResponseEntity<List<Account>> accountList = accountFeignService.getAccountsbyId(userId);
            //   balanceService.findAccountIdBelongsToCurrentUser(accountList.getBody(), accountId);
            balance.setAccountId(accountId);
            BalanceDto balanceDetails = balanceService.updateBalance(balance, balanceId);
            logger.info(ErrorSuccessMessageConstants.UPDATE_BALANCE_LOG + accountId);
            return ResponseEntity.status(HttpStatus.OK).body(balanceDetails);
        } else {
            logger.error(ErrorSuccessMessageConstants.UPDATE_BALANCE_ERROR_LOG);
            throw new AccountIdMismatchException(accountId, ErrorSuccessMessageConstants.ACCOUNT_ID_MISMATCH);
        }
    }
}
