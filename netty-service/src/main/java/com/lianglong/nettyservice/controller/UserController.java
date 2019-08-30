package com.lianglong.nettyservice.controller;

import com.lianglong.bean.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lianglong
 * @date 2019/8/30
 */
@RestController
@Slf4j
@RequestMapping("/employee")
public class UserController {

    @GetMapping("/info/{id}")
    public Employee infoEmployee(@PathVariable Integer id){
        log.info("id is : {}",id);
        return new Employee().setId(1L).setEmpAge(12).setEmpName("张三丰");
    }
}
