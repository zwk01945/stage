package com.example.common.config.propertie;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * ClassName: SysProperties
 * Description: 文件系统配置
 * date: 2020/7/15 14:30
 * @author Mr.zhang
 */
@Configuration
@ConfigurationProperties(prefix = "upload")
@PropertySource(value = "classpath:sys.properties")
public class UploadProperties {

//    public static final String winPath = Config.getInstance().getProperty("upload.linuxPath");
//    public static final String winPathZip = Config.getInstance().getProperty("upload.winPathZip");
//    public static final String winBakPath = Config.getInstance().getProperty("upload.winBakPath");
//    public static final String winBakErrorPath = Config.getInstance().getProperty("upload.winBakErrorPath");
//    public static final String linuxPath = Config.getInstance().getProperty("upload.linuxPath");
//    public static final String linuxPathZip = Config.getInstance().getProperty("upload.linuxPathZip");
//    public static final String linuxBakPath = Config.getInstance().getProperty("upload.linuxBakPath");
//    public static final String linuxBakErrorPath = Config.getInstance().getProperty("upload.linuxBakErrorPath");


    public   String winPath;
    public   String winPathZip;
    public   String winBakPath;
    public   String winBakErrorPath;
    public   String linuxPath;
    public   String linuxPathZip;
    public   String linuxBakPath;
    public   String linuxBakErrorPath;

    public UploadProperties() {
    }

    public String getWinPath() {
        return winPath;
    }

    public void setWinPath(String winPath) {
        this.winPath = winPath;
    }

    public String getWinPathZip() {
        return winPathZip;
    }

    public void setWinPathZip(String winPathZip) {
        this.winPathZip = winPathZip;
    }

    public String getWinBakPath() {
        return winBakPath;
    }

    public void setWinBakPath(String winBakPath) {
        this.winBakPath = winBakPath;
    }

    public String getWinBakErrorPath() {
        return winBakErrorPath;
    }

    public void setWinBakErrorPath(String winBakErrorPath) {
        this.winBakErrorPath = winBakErrorPath;
    }

    public String getLinuxPath() {
        return linuxPath;
    }

    public void setLinuxPath(String linuxPath) {
        this.linuxPath = linuxPath;
    }

    public String getLinuxPathZip() {
        return linuxPathZip;
    }

    public void setLinuxPathZip(String linuxPathZip) {
        this.linuxPathZip = linuxPathZip;
    }

    public String getLinuxBakPath() {
        return linuxBakPath;
    }

    public void setLinuxBakPath(String linuxBakPath) {
        this.linuxBakPath = linuxBakPath;
    }

    public String getLinuxBakErrorPath() {
        return linuxBakErrorPath;
    }

    public void setLinuxBakErrorPath(String linuxBakErrorPath) {
        this.linuxBakErrorPath = linuxBakErrorPath;
    }
}
