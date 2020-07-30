package com.example.msgpush.controller;

import com.alibaba.fastjson.JSON;
import com.example.common.controller.BaseController;
import com.example.common.util.msg.QueryParam;
import com.example.common.util.msg.QueryParamBatch;
import com.example.common.util.msg.SendSmsCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


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
public class MsgController extends BaseController {

    private final SendSmsCode sendSmsCode;
    @Autowired
    public MsgController(SendSmsCode sendSmsCode) {
        this.sendSmsCode = sendSmsCode;
    }

    @RequestMapping("/s")
    public Object send () {
        Map<String,Object> param = new HashMap<>();
        String code = UUID.randomUUID().toString().substring(0, 5).toUpperCase();
        param.put("code",code);
        QueryParam queryParam = new QueryParam();
        queryParam.setPhoneNumber("13132256175");
        queryParam.setTemplateParam(param);
       return sendSmsCode.send(queryParam);
    }

    @RequestMapping("/q")
    public Object query () {
        QueryParam queryParam = new QueryParam();
        queryParam.setPhoneNumber("13132256175");
        queryParam.setCurrentPage("1");
        queryParam.setPageSize("10");
        queryParam.setSendDate("20200730");
        return sendSmsCode.querySendDetails(queryParam);
    }

    @RequestMapping("/b")
    public Object batchSend () {
        Map<String,Object> param = new HashMap<>();
        String code = UUID.randomUUID().toString().substring(0, 5).toUpperCase();
        param.put("code",code);
        String s = JSON.toJSONString(param);
        QueryParamBatch queryParam = new QueryParamBatch();
        queryParam.setPhoneNumber(new String[]{"13132256175","13212290608"});
        queryParam.setSignName(new String[]{"ABC商城","ABC商城"});
        queryParam.setTemplateParam(new String[]{s,s});
        return sendSmsCode.batchSend(queryParam);
    }

}
