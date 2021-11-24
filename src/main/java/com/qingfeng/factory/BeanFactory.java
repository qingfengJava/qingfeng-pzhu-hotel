package com.qingfeng.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 工厂类
 * 用来创建和推送bean实例
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/11/24
 */
public class BeanFactory {

    /**
     * 加载并读取配置文件
     */
    private static final Properties PROPERTIES = new Properties();

    static {
        InputStream in = null;
        try {
            //通过类加载器读取配置文件，获取流对象
            in = BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
            PROPERTIES.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null){
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 通过工厂返回实例
     * @param beanName 要创建bean实例的名称，是一个唯一标识。如foodTypeDao
     * @return
     */
    public static Object getBean(String beanName) {
        Object instance = null;
        try {
            //1、通过beanName得到要创建对象的全类名
            String className = PROPERTIES.getProperty(beanName);
            //判断是否为空，不为空，才创建
            if (className != null && className != ""){
                //2、反射创建对象
                Class<?> clazz = Class.forName(className);
                instance = clazz.newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }
}
