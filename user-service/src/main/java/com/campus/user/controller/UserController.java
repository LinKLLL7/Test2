package com.campus.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
@Api(tags = "用户服务")
public class UserController {

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public Map<String, Object> register(
            @ApiParam(value = "用户名", required = true) @RequestParam String username,
            @ApiParam(value = "密码", required = true) @RequestParam String password,
            @ApiParam(value = "手机号", required = true) @RequestParam String phone) {
        Map<String, Object> response = new HashMap<>();
        response.put("userId", 1);
        response.put("token", "mock-token-" + username);
        return response;
    }

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public Map<String, Object> login(
            @ApiParam(value = "用户名", required = true) @RequestParam String username,
            @ApiParam(value = "密码", required = true) @RequestParam String password) {
        Map<String, Object> response = new HashMap<>();
        response.put("userId", 1);
        response.put("token", "mock-token-" + username);
        return response;
    }

    @GetMapping("/{id}")
    @ApiOperation("获取用户信息")
    public Map<String, Object> getUserInfo(
            @ApiParam(value = "用户ID", required = true) @PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        response.put("userId", id);
        response.put("username", "user" + id);
        response.put("phone", "13800138000");
        return response;
    }
}