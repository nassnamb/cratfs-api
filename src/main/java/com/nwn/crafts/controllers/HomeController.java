package com.nwn.crafts.controllers;


import com.nwn.crafts.core.models.AppInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/home")
public class HomeController {

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @Value("${app.env}")
    private String appEnv;


    @GetMapping("/appInfo")
    public AppInfo getAppInfo() {
        return new AppInfo(appName, appVersion, appEnv);
    }



}
