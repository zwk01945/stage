package com.example.quartz.init;

import com.alibaba.fastjson.JSON;
import com.example.common.bean.quartz.IcpCode;
import com.example.common.util.redis.RedisUtils;
import com.example.quartz.mapper.IcpCodeMapper;
import com.example.quartz.service.IcpCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.types.RedisClientInfo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ClassName: RedisInit
 * Description:
 * date: 2020/7/24 11:26
 *
 * @author zwk
 */
@Component
public class RedisInit implements CommandLineRunner {
    @Autowired
    IcpCodeService icpCodeService;
    @Autowired
    StringRedisTemplate redisTemplate;

    private static final Logger log = LoggerFactory.getLogger(RedisInit.class);

    @Override
    public void run(String... args) throws Exception {
        log.info("初始化Quartz热点缓存数据==============");
        List<IcpCode> icpCodes = icpCodeService.selectAll();
        String icp_code = redisTemplate.opsForValue().get("icp_code");
        log.info("热点缓存数据存放成功:{}",icp_code);
    }
}
