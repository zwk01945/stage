package com.example.quartz.controller;

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
}
