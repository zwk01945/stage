package com.example.common.config.properter;

        import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ClassName: DsProperter
 * Description: 数据源配置文件
 * date: 2020/7/20 16:53
 *
 * @author zwk
 */
public class DsProperter{

    public static final String PROPERTY_FILE = "application.properties";
    public static final String PROPERTY_FILE_YAML = "application.yml";
    public static final String PROPERTIES_START = "spring.datasource.dynamic";

    private static DsProperter instance = null;

    private static Properties paraProps = new Properties();

    private DsProperter() {

        InputStream properties = getClass().getResourceAsStream("/" + PROPERTY_FILE);
        InputStream yaml = getClass().getResourceAsStream("/" + PROPERTY_FILE_YAML);
        try {
            if (properties != null) {
                paraProps.load(properties);
            } else {
                paraProps.load(yaml);
            }
        } catch (Exception e) {

            System.err.println("Can not read application.properties file!");

        } finally {
            try {
                properties.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取实例
     *
     * @return
     */
    public static synchronized DsProperter getInstance() {

        if (instance == null) {
            instance = new DsProperter();
        }
        return instance;

    }

    /**
     * 获取属性
     *
     * @param paraName
     * @return
     */
    public String getProperty(String paraName) {

        String property = "";
        try {
            property = paraProps.getProperty(paraName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return property;
    }

    /**
     * 返回配置文件下的指定配置文件的所有key
     * @return
     */
    public List<String> getList(String preName) {
        List<String> collect = null;
        try {
            Set<String> strings = paraProps.stringPropertyNames();
            collect = strings.stream()
                    .filter(x -> x.startsWith(preName)).collect(Collectors.toList());
            Collections.sort(collect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return collect;
    }

}
