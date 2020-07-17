package com.example.common.bean;

import java.io.Serializable;
import lombok.Data;

/**
 * coustomer_zt
 * @author 
 */
@Data
public class CoustomerZt implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String job;

    private Integer provinceFlag;

    private Integer age;

    private String localAddress;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getProvinceFlag() {
        return provinceFlag;
    }

    public void setProvinceFlag(Integer provinceFlag) {
        this.provinceFlag = provinceFlag;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }
}