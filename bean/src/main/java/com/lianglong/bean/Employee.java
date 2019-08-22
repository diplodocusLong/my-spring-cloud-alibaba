package com.lianglong.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author lianglong
 * @date 2019/8/22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String empName;

    private Integer empAge;

    private Integer deptId;

    private LocalDate createDate;

    private LocalDateTime updateTime;

    private Integer isOut;

    private Integer version;

    @TableField(exist = false)
    private Balance balance;

}