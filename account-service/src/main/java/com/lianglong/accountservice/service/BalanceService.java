package com.lianglong.accountservice.service;


import com.lianglong.accountservice.service.impl.BalanceServiceFallback;
import com.lianglong.bean.Balance;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="payment-service",fallback = BalanceServiceFallback.class)
public interface BalanceService {

     @GetMapping(value = "/pay/balance/{id}")
     public Balance getBalance(@PathVariable("id") Integer id);
}
