package com.example.quartz.controller;

import com.example.common.bean.quartz.CoustomerZt;
import com.example.common.bean.quartz.IcpCode;
import com.example.quartz.mapper.IcpCodeMapper;
import com.example.quartz.service.IcpCodeService;
import com.example.quartz.service.impl.ICoustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.common.controller.BaseController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zwk
 * @since 2020-07-20
 */
@RestController
@RequestMapping("/quartz")
public class CoustomerZtController extends BaseController {

    @Autowired
    ICoustomerServiceImpl coustomerService;
    @Autowired
    IcpCodeService icpCodeService;

    @RequestMapping("/q")
    public Object hello () {
        return coustomerService.selectAll();
    }

    @RequestMapping("/o")
    public Object helloOne (Integer id) {
        if (id == null || id == 0) throw new RuntimeException("参数为空");
        Map<String,Object> params = new HashMap<>();
        params.put("ID",id);
        return coustomerService.selectOne(params);
    }

    @RequestMapping("/m")
    public Object helloMore () {
        return coustomerService.selectJoin();
    }
    @RequestMapping("/p")
    public Object helloMore (Integer start,Integer size) {
        return coustomerService.selectByPage(start,size);
    }
    @RequestMapping("/i")
    public Object helloIcp () {
        IcpCode code = new IcpCode();
        code.setId(1);
        code.setName("测试");
        CoustomerZt coustomerZt = new CoustomerZt();
        coustomerZt.setAge(100000000);
        coustomerZt.setId(0);
        coustomerZt.setJob("0");
        coustomerZt.setLocalAddress("0");
        coustomerZt.setName("0");
        coustomerZt.setProvinceFlag(0);
        long insert = icpCodeService.insert(code,coustomerZt);
        return insert;
    }

}
