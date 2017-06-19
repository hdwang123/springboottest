package com.hdwang.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hdwang on 2017/6/5.
 */
@Controller
@RequestMapping("/common")
public class Common {

    @Value("${msg:Welcome!}")
    private String msg;

    /**
     * get a page
     * @return a page with name called return value
     */
    @RequestMapping("login")
    public String getLoginPage(ModelMap map){
        map.put("welcomeMsg",this.msg);
        return "login";
    }


}
