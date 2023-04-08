package com.test.api;
//把这个类做为我们测试基类，后续在创建测试时只要继承这个类就可以了
import java.io.FileInputStream;
import java.util.Properties;

public class TestApi {
    public Properties prop;
    public String excelPath;
    public String host; 
    
    //构造函数
    public  TestApi() {
        try {
        	//数据流的形式读取配置文件
            prop = new Properties();
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+
"\\src\\main\\resources\\config.properties");
            prop.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        host = prop.getProperty("Host");
        excelPath=prop.getProperty("testData");
      
    }
}