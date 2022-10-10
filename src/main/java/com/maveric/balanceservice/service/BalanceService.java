package com.maveric.balanceservice.service;

import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.model.Balance;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BalanceService {
    public BalanceDto createBalance(BalanceDto balanceDto);
   public List<BalanceDto> getBalances(String id, Integer page, Integer pageSize);
    BalanceDto getBalanceDetails(String accountId,String id);
    public BalanceDto updateBalance(String accountId,String id,BalanceDto balanceDto);
    public String deleteBalance(String accountId,String id);

}
