package com.example.base.config.propertie;


import com.example.base.config.Config;

/**
 * ClassName: ImageProperties <br/>
 * Description: <br/>
 * date: 2020/7/15 15:27<br/>
 *
 * @author zwk<br />
 */
public class ImageProperties {

    public static final String serverIp = Config.getInstance().getProperty("image.serverIp");
    public static final String serverPort = Config.getInstance().getProperty("image.serverPort");
    public static final String serverPath = Config.getInstance().getProperty("image.serverPath");


}
