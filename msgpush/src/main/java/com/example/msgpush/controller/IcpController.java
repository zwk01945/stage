package com.example.msgpush.controller;

import com.example.common.controller.BaseController;
import com.example.common.util.msg.SendSms;
import com.example.msgpush.service.IcpCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zwk
 * @since 2020-07-20
 */
@RestController
@RequestMapping("/msg")
public class IcpController extends BaseController {

    @Autowired
    IcpCodeService icpCodeService;

    @Autowired
    SendSms sendSms;


    @RequestMapping("/q")
    public Object hello () {
       return sendSms.send();
    }


}
