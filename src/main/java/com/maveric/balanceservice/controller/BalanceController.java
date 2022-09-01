package com.maveric.balanceservice.controller;


import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.repository.BalanceRepository;
import com.maveric.balanceservice.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class BalanceController {
    @Autowired
    private BalanceService balanceService;

    @PostMapping("{accountId}/balances")
    public ResponseEntity<BalanceDto> createBalance(@PathVariable String accountId, @RequestBody BalanceDto balanceDto) {
        BalanceDto BalanceDtoResponse = balanceService.createBalance(balanceDto);
        return new ResponseEntity<BalanceDto>(BalanceDtoResponse, HttpStatus.OK);
    }

    @GetMapping("{accountId}/balances")
    public ResponseEntity<List<BalanceDto>> getBalances(@PathVariable String accountId, @RequestParam(defaultValue = "0") Integer page,
                                                        @RequestParam(defaultValue = "10") Integer pageSize) {
        List<BalanceDto> balanceDtoResponse = balanceService.getBalances(accountId,page,pageSize);
        return new ResponseEntity<List<BalanceDto>>(balanceDtoResponse, HttpStatus.OK);
    }

    @GetMapping("{accountId}/balances/{id}")
    public BalanceDto getBalanceDetails(@PathVariable(value = "accountId") String accountId, @PathVariable(value = "id") String id)
    {
        return balanceService.getBalanceDetails(accountId,id);
    }

    @PutMapping("{accountId}/balances/{id}")
    public BalanceDto updateBalance(@PathVariable String accountId, @PathVariable String id, @RequestBody BalanceDto balanceDto){
        return balanceService.updateBalance(accountId,id,balanceDto);
    }

    @DeleteMapping("{accountId}/balances/{id}")
    public String deleteUser(@PathVariable(value = "accountId") String accountId, @PathVariable(value = "id") String id){
        return balanceService.deleteBalance(accountId,id);
    }

}
