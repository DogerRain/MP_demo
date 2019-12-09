package com.meizu.service.impl;

import com.meizu.service.HomeService;
import org.springframework.stereotype.Service;

@Service
public class HomeServiceImpl implements HomeService {


    @Override
    public String testLog() {
        return null;
    }


    public String testIfPrintLog(HomeService homeService){
        homeService.testLog();
        return "";
    }
}
