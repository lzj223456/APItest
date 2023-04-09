package com.allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


@Epic("类似一级目录")
@Feature("类似二级目录")
public class NewTest {

    @BeforeClass(description = "创建任务和全局配置")
    public void beforclass(){
        System.out.println("beforclass");
        step("第1步",1);
    }

    /**
     * 删除创建的任务
     */
    @AfterClass(description = "删除创建的任务")
    public void afterclass(){
        System.out.println("afterclass");
        step("第1步",4);

    }

    @Story("Story类似三级目录")
    @Test(description = "测试用例1")
    public void awtApi001_001(){
        System.out.println("test1");
        step("第2步",2);

    }
    @Story("Story类似三级目录")
    @Test(description = "测试用例2")
    public void awtApi001_002(){
        System.out.println("test2");
        step("第3步",3);
    }

    @Step("测试步骤{0}编号{1}")
    public void step(String a,int b){

    }

}
