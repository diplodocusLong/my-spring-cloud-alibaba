package com.lianglong.accountservice.service.impl;

import com.lianglong.accountservice.service.BalanceService;
import com.lianglong.bean.Balance;
import org.springframework.stereotype.Component;

@Component
public class BalanceServiceFallback implements BalanceService {
    @Override
    public Balance getBalance(Integer id) {
        return new Balance(0, 0, 0, "降级");
    }
}
