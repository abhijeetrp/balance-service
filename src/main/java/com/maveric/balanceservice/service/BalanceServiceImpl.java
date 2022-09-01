package com.maveric.balanceservice.service;
import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.model.Balance;
import com.maveric.balanceservice.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BalanceServiceImpl implements BalanceService {
    @Autowired
    private BalanceRepository  balanceRepository;

    @Override
    public BalanceDto createBalance(BalanceDto balanceDto) {
        balanceDto.setCreatedAt(LocalDateTime.now());
        balanceDto.setUpdatedAt(LocalDateTime.now());
        Balance balance= convertDtoToEntity(balanceDto);
        Balance newbalance= balanceRepository.save(balance);
        return convertEntityToDto(newbalance);
    }

    @Override
    public List<BalanceDto> getBalances(String id, Integer page, Integer pageSize) {
        return balanceRepository.findByAccountId(id).stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public BalanceDto getBalanceDetails(String accountId, String id) {
        Balance balance=balanceRepository.findByAccountIdOrBalanceId(accountId,id);
        return convertEntityToDto(balance);
    }

    @Override
    public BalanceDto updateBalance(String accountId,String id,BalanceDto balanceDto) {
        Balance updateBalance = balanceRepository.findByAccountIdOrBalanceId(accountId,id);
        updateBalance.setAccountId(balanceDto.getAccountId());
        updateBalance.setAmount(balanceDto.getAmount());
        updateBalance.setCurrency(balanceDto.getCurrency());
        updateBalance.setUpdatedAt(LocalDateTime.now());
        balanceRepository.save(updateBalance);
        return convertEntityToDto(updateBalance);
    }

    @Override
    public String deleteBalance(String accountId,String id)
    {
        balanceRepository.deleteByAccountIdOrBalanceId(accountId,id);
        return "User deleted successfully.";
    }
    private BalanceDto convertEntityToDto(Balance balance){
        BalanceDto  balanceDto =new BalanceDto();
        balanceDto.setId(balance.getId());
        balanceDto.setAccountId(balance.getAccountId());
        balanceDto.setAmount(balance.getAmount());
        balanceDto.setCurrency(balance.getCurrency());
        balanceDto.setCreatedAt(balance.getCreatedAt());
        balanceDto.setUpdatedAt(balance.getUpdatedAt());
        return balanceDto;
    }
    private Balance convertDtoToEntity(BalanceDto balanceDto){
        Balance  balance =new Balance();
        balance.setId(balanceDto.getId());
        balance.setAccountId(balanceDto.getAccountId());
        balance.setAmount(balanceDto.getAmount());
        balance.setCurrency(balanceDto.getCurrency());
        balance.setCreatedAt(balanceDto.getCreatedAt());
        balance.setUpdatedAt(balanceDto.getUpdatedAt());
        return balance;
    }

}
