package com.campus.rank.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/rank")
@Api(tags = "推荐/排序服务")
public class RankController {

    @GetMapping("/posts")
    @ApiOperation("热度榜单")
    public Map<String, Object> getHotPosts(
            @ApiParam(value = "页码", defaultValue = "1") @RequestParam Integer page,
            @ApiParam(value = "每页大小", defaultValue = "10") @RequestParam Integer size) {
        Map<String, Object> response = new HashMap<>();
        List<Map<String, Object>> data = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Map<String, Object> post = new HashMap<>();
            post.put("postId", i + 1);
            post.put("title", "热门帖子" + (i + 1));
            post.put("score", 100 - i);
            data.add(post);
        }
        response.put("data", data);
        response.put("total", 100);
        return response;
    }

    @PostMapping("/refresh")
    @ApiOperation("刷新热度")
    public Map<String, Object> refreshScore(
            @ApiParam(value = "帖子ID", required = true) @RequestParam Long postId) {
        Map<String, Object> response = new HashMap<>();
        response.put("score", 95.5);
        return response;
    }
}