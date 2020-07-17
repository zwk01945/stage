package com.example.quartz.controller;

import com.example.common.controller.BaseController;
import com.example.common.exception.MyException;
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

    @RequestMapping("/v1")
    public String hello() {
        throw new MyException("aa",500);
    }

    @RequestMapping("/v2")
    public String helloex() {
        int i = 1/0;
        return "Hello World";
    }



}
