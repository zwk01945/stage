package com.example.base.config.propertie;

import com.example.base.config.Config;


/**
 * ClassName: LogProperties
 * Description: 自定义日志配置
 * date: 2020/7/16 10:43
 * @author zwk
 */
public class LogProperties {

    public static final String winPath = Config.getInstance().getProperty("log.winPath");
    public static final String linuxPath = Config.getInstance().getProperty("log.linuxPath");

}
