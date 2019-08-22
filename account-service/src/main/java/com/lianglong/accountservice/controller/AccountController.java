package com.lianglong.accountservice.controller;

import com.lianglong.accountservice.mapper.EmployeeMapper;
import com.lianglong.accountservice.service.BalanceService;
import com.lianglong.bean.Balance;
import com.lianglong.bean.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lianglong
 * @date 2019/8/22
 */
@RestController
public class AccountController {

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    BalanceService balanceService;

    @GetMapping("/acc/user/{id}")
    public Employee getUser(@PathVariable Integer id) {

        Employee employee = employeeMapper.selectById(id);

        Balance balance = balanceService.getBalance(1);

        employee.setBalance(balance);

        return employee;


    }


}
