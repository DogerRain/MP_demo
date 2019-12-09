package com.meizu.controller;

import com.meizu.common.City;
import com.meizu.service.HomeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
//@Slf4j
public class HomeController {

//    @Autowired
//    HomeService homeService;

    //添加一个日志器

    //映射一个action

    @RequestMapping("/index")
    public  String index(){
        //输出日志文件
//        log.info("测试是否有日志");
//        homeService.print();
        //返回一个index.jsp这个视图
        String city = City.getCityNameByIndex("1");
        System.out.printf(city);
        return "index1";
    }
}
