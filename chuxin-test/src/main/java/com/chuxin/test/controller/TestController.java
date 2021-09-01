package com.chuxin.test.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("测试")
public class TestController {

    @GetMapping("/user")
    @ApiOperation("获取用户")
    public String getUser(){
        return "user";
    }
}
