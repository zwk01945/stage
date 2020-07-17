package com.example.common.controller;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName: BaseController
 * Description: 公共Controller
 * date: 2020/7/16 17:25
 *
 * @author zwk
 */
public class BaseController {

    /**
     * 基础类
     */
    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;


    //下面为公共方法  可以入库操作表等

}
