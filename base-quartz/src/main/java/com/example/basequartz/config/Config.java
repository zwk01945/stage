package com.example.basequartz.config;

import com.example.base.config.propertie.ImageProperties;
import com.example.base.config.propertie.LogProperties;
import com.example.base.config.propertie.UploadProperties;
import com.example.base.util.date.DateConstant;
import com.example.base.util.date.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class Config {

    private static final Logger log = LoggerFactory.getLogger(Config.class);

    /**
     *  系统文件下载配置  UploadProperties
     *  图片服务器   ImageProperties
     *  自定义日志     LogProperties
     **/

    @Bean(value = "globalConfig")
    public void config() {
        log.info("项目初始化============================");
        log.info("全局系统配置如下============================");
        log.info("当前系统时间为:{}", DateUtil.getStrByDate(new Date(), DateConstant.YYYY_MM_DD_HH_MM_SS));
        log.info("下载上传配置如下:");
        log.info("win环境:{},{},{},{}", UploadProperties.winPath,
                UploadProperties.winPathZip,
                UploadProperties.winBakPath,
                UploadProperties.winBakErrorPath);
        log.info("linux环境:{},{},{},{}",UploadProperties.linuxPath,UploadProperties.linuxPathZip,
                UploadProperties.linuxBakPath,
                UploadProperties.linuxBakErrorPath);
        log.info("图片服务器配置如下:");
        log.info("ip,port,path:{},{},{}",ImageProperties.serverIp,
                ImageProperties.serverPort,
                ImageProperties.serverPath);
        log.info("自定义日志配置如下:");
        log.info("winpath,linuxpath:{},{}",LogProperties.winPath,LogProperties.linuxPath);
    }


}
