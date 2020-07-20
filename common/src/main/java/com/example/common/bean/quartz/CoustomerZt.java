package com.example.common.bean.quartz;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Coustomer_zt表
 * @author zwk
 * @since 2020-07-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("coustomer_zt")
public class CoustomerZt implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId("ID")
    private Integer id;

    @TableField("NAME")
    private String name;

    @TableField("JOB")
    private String job;

    @TableField("PROVINCE_FLAG")
    private Integer provinceFlag;

    @TableField("AGE")
    private Integer age;

    @TableField("LOCAL_ADDRESS")
    private String localAddress;


}
