package com.example.quartz.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.common.bean.CoustomerZt;
import com.example.common.controller.BaseController;
import com.example.common.exception.MyException;
import com.example.quartz.dao.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * ClassName: ApiController
 * Description:
 * date: 2020/7/16 18:55
 *
 * @author zwk
 */
@RestController
@RequestMapping("/api")
public class ApiController extends BaseController {

    @Autowired
    private CustomerMapper customerMapper;


    @RequestMapping("/v1")
    public Object hello() {
        QueryWrapper<CoustomerZt> wrapper = new QueryWrapper<CoustomerZt>();
        wrapper.lambda().le(CoustomerZt::getId,50);
        return customerMapper.selectList(wrapper);
    }

    @RequestMapping("/v2")
    public String helloex() {
        int i = 1/0;
        return "Hello World";
    }



}
