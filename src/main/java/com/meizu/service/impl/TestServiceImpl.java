package com.meizu.service.impl;

import com.meizu.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author huangyongwen
 * @date 2019/11/21
 * @Description
 */
//@Component("testServiceImpl")
public class TestServiceImpl {
    @Autowired
    HomeServiceImpl homeServiceImpl;
    @Value("TestServiceImpl")
    String name;

    public void test(){
        System.out.println(homeServiceImpl.testIfPrintLog(new HomeService(){
            @Override
            public String testLog() {
                System.out.println(name);
                name = "modify";
                return name;
            }
        }));

    }
}
