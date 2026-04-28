package com.campus.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/v1/users")
@Api(tags = "用户服务")
public class UserController {

    private final Map<Long, Map<String, Object>> users = new ConcurrentHashMap<>();
    private final AtomicLong userIdGenerator = new AtomicLong(1);

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public Map<String, Object> register(
            @ApiParam(value = "用户名", required = true) @RequestParam String username,
            @ApiParam(value = "密码", required = true) @RequestParam String password,
            @ApiParam(value = "手机号", required = true) @RequestParam String phone) {
        Long userId = userIdGenerator.getAndIncrement();

        Map<String, Object> user = new HashMap<>();
        user.put("userId", userId);
        user.put("username", username);
        user.put("password", password);
        user.put("phone", phone);

        users.put(userId, user);

        Map<String, Object> response = new HashMap<>();
        response.put("userId", userId);
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
        Map<String, Object> user = users.get(id);
        if (user == null) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "用户不存在");
            error.put("userId", id);
            return error;
        }
        Map<String, Object> response = new HashMap<>();
        response.put("userId", user.get("userId"));
        response.put("username", user.get("username"));
        response.put("phone", user.get("phone"));
        return response;
    }
}