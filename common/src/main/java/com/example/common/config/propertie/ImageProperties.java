package com.example.common.config.propertie;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * ClassName: ImageProperties
 * Description: 图片服务器读取类
 * date: 2020/7/15 15:27
 * @author Mr.zhang
 */
@Configuration
@ConfigurationProperties(prefix = "image")
@PropertySource(value = "classpath:sys.properties")
public class ImageProperties {

    public   String serverIp;
    public   String serverPort;
    public   String serverPath;

    public ImageProperties() {
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public String getServerPort() {
        return serverPort;
    }

    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }

    public String getServerPath() {
        return serverPath;
    }

    public void setServerPath(String serverPath) {
        this.serverPath = serverPath;
    }
}
