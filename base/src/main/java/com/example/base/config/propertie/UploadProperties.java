package com.example.base.config.propertie;


import com.example.base.config.Config;

/**
 * ClassName: SysProperties <br/>
 * Description: 系统配置
 * date: 2020/7/15 14:30<br/>
 *
 * @author zwk
 */
public class UploadProperties {

    public static final String winPath = Config.getInstance().getProperty("upload.linuxPath");
    public static final String winPathZip = Config.getInstance().getProperty("upload.winPathZip");
    public static final String winBakPath = Config.getInstance().getProperty("upload.winBakPath");
    public static final String winBakErrorPath = Config.getInstance().getProperty("upload.winBakErrorPath");
    public static final String linuxPath = Config.getInstance().getProperty("upload.linuxPath");
    public static final String linuxPathZip = Config.getInstance().getProperty("upload.linuxPathZip");
    public static final String linuxBakPath = Config.getInstance().getProperty("upload.linuxBakPath");
    public static final String linuxBakErrorPath = Config.getInstance().getProperty("upload.linuxBakErrorPath");

}
