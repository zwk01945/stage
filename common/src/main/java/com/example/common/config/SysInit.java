package com.example.common.config;

import com.example.common.config.propertie.ImageProperties;
import com.example.common.config.propertie.LogProperties;
import com.example.common.config.propertie.UploadProperties;
import com.example.common.util.date.DateConstant;
import com.example.common.util.date.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * ClassName: Config <br/>
 * Description: 配置
 * date: 2020/7/15 14:52<br/>
 *
 * @author zwk<br />
 */
@Configuration
public class SysInit {

    private static final Logger log = LoggerFactory.getLogger(Config.class);

    /**
     *  系统文件下载配置  UploadProperties
     *  图片服务器   ImageProperties
     *  自定义日志     LogProperties
     **/
    @Autowired
    UploadProperties uploadProperties;
    @Autowired
    ImageProperties imageProperties;
    @Autowired
    LogProperties logProperties;

    @Bean(value = "globalConfig")
    public void config() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement boot = stackTrace[stackTrace.length -1];
        log.info("当前启动类为:{}",boot.getClassName());
        log.info("项目初始化============================");
        log.info("全局系统配置如下============================");
        log.info("当前系统时间为:{}", DateUtil.getStrByDate(new Date(), DateConstant.YYYY_MM_DD_HH_MM_SS));
        log.info("下载上传配置如下:");
        log.info("win环境:{},{},{},{}", uploadProperties.getWinPath(),
                uploadProperties.getWinPathZip(),
                uploadProperties.getWinBakPath(),
                uploadProperties.getWinBakErrorPath());
        log.info("linux环境:{},{},{},{}",uploadProperties.getLinuxPath(),uploadProperties.getLinuxPathZip(),
                uploadProperties.getLinuxBakPath(),
                uploadProperties.getLinuxBakErrorPath());
        log.info("图片服务器配置如下:");
        log.info("ip,port,path:{},{},{}", imageProperties.getServerIp(),
                imageProperties.getServerPort(),
                imageProperties.getServerPath());
        log.info("自定义日志配置如下:");
        log.info("winpath,linuxpath:{},{}", logProperties.getWinPath(),logProperties.getLinuxPath());

    }


}
