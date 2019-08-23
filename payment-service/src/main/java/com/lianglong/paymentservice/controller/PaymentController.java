package com.lianglong.paymentservice.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.lianglong.bean.Balance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lianglong
 * @date 2019/8/22
 */
@RefreshScope
@RestController
@Slf4j
public class PaymentController {

    @Value("${sleep:0}")
    private int sleep;

    private static final  Map<Integer, Balance> balanceMap = new HashMap();

    static {

        balanceMap.put(1, new Balance(1, 10, 1000));
        balanceMap.put(2, new Balance(2, 0, 10000));
        balanceMap.put(3, new Balance(3, 100, 0));

    }

    @GetMapping("/pay/balance/{id}")
    @SentinelResource(value = "protected-resource", blockHandler = "handleBlock")
    public Balance getBalance(@PathVariable Integer id) {
        log.info("request:/pay/balance/{},sleep:{}",id,sleep);
        if (sleep > 0) {
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                log.info("exception:{}",e);
                Thread.currentThread().interrupt();
            }
        }
        if (id != null && balanceMap.containsKey(id)) {
            return balanceMap.get(id);
        }
        return new Balance(0, 0, 0);
    }

    public Balance handleBlock(Integer id, BlockException e) {
        log.info("balancdId是：{}，异常是：{}",id,e);
        return new Balance(0, 0, 0, "限流");
    }
}
