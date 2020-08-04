package com.example.common.config.init;

import com.example.common.config.properter.Config;
import com.example.common.config.propertie.ImageProperties;
import com.example.common.config.propertie.LogProperties;
import com.example.common.config.propertie.MinioProperties;
import com.example.common.config.propertie.UploadProperties;
import com.example.common.util.bean.SpringBeanUtils;
import com.example.common.util.date.DateConstant;
import com.example.common.util.date.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Date;

/**************************************************************
 ***       S  T  A  G  E    多模块依赖项目                    ***
 **************************************************************
 *                                                            *
 *         Project Name : base                                *
 *                                                            *
 *         File Name : SysInit.java                           *
 *                                                            *
 *         Programmer : Mr.zhang                              *
 *                                                            *
 *         Start Date : 2020/7/24 17:28                       *
 *                                                            *
 *         Last Update : 2020/7/24 17:28                      *
 *                                                            *
 *------------------------------------------------------------*
 * 功能:                                                 *
 *   项目初始化系统参数配置，包括日志，服务器信息配置，文件上传配置     *
 *   图片服务器配置参数                                          *
 * - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  */
@Component
public class SysInit implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(Config.class);

    /**
     *  系统文件下载配置  UploadProperties
     *  图片服务器   ImageProperties
     *  自定义日志     LogProperties
     **/

    private UploadProperties uploadProperties;
    private ImageProperties imageProperties;
    private LogProperties logProperties;
    private MinioProperties minioProperties;

    @Autowired
    public void setUploadProperties(UploadProperties uploadProperties) {
        this.uploadProperties = uploadProperties;
    }
    @Autowired
    public void setImageProperties(ImageProperties imageProperties) {
        this.imageProperties = imageProperties;
    }
    @Autowired
    public void setLogProperties(LogProperties logProperties) {
        this.logProperties = logProperties;
    }
    @Autowired
    public void setMinioProperties(MinioProperties minioProperties) {
        this.minioProperties = minioProperties;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
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
        log.info("图片文件服务器:{},{},{}",minioProperties.getEndPoint(),minioProperties.getAccessKey(),minioProperties.getSecretKey());
    }
}
