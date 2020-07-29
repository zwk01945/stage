package com.example.common.util.msg;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.example.common.config.propertie.SmsEnv;
import com.example.common.util.date.DateConstant;
import com.example.common.util.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**************************************************************
 ***       S  T  A  G  E    多模块依赖项目                    ***
 **************************************************************
 *                                                            *
 *         Project Name : base             *
 *                                                            *
 *         File Name : SendSms.java                           *
 *                                                            *
 *         Programmer : Mr.zhang                              *
 *                                                            *
 *         Start Date : 2020/7/29 17:07                       *
 *                                                            *
 *         Last Update : 2020/7/29 17:07                      *
 *                                                            *
 *------------------------------------------------------------*
 * Functions:                                                 *
 *   阿里云短信发送                                              *
 * - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  */
@Component
public class SendSms {

    private SmsEnv smsEnv;

    @Autowired
    public void setSmsEnv(SmsEnv smsEnv) {
        this.smsEnv = smsEnv;
    }

    public Object send() {

        Object result = null;

        DefaultProfile profile = DefaultProfile.getProfile(smsEnv.getRegionName(), smsEnv.getAccessKeyId(), smsEnv.getAccessKeySecret());
        IAcsClient client = new DefaultAcsClient(profile);
        System.out.println(smsEnv.getAccessKeyId()+"-" + smsEnv.getAccessKeySecret());
        Map<String,Object> param = new HashMap<>();
        param.put("code",UUID.randomUUID().toString().substring(0,6));
        try {
            String signName = new String(smsEnv.getSignName().getBytes(StandardCharsets.ISO_8859_1.name()), StandardCharsets.UTF_8.name());
            CommonRequest request = new CommonRequest();
            request.setSysMethod(MethodType.POST);
            request.setSysDomain("dysmsapi.aliyuncs.com");
            request.setSysVersion("2017-05-25");
            request.setSysAction("SendSms");
            request.putQueryParameter("RegionId", smsEnv.getRegionName());
            request.putQueryParameter("PhoneNumbers", "15822496601");
            request.putQueryParameter("SignName",signName);
            request.putQueryParameter("TemplateCode", smsEnv.getTemplateCode());
            request.putQueryParameter("TemplateParam", JSON.toJSONString(param));
            request.putQueryParameter("SmsUpExtendCode", "90999");
            CommonResponse response = client.getCommonResponse(request);
            result = response.getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
