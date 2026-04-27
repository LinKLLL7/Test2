package com.campus.content.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "内容服务")
public class ContentController {

    @PostMapping("/posts")
    @ApiOperation("发帖")
    public Map<String, Object> createPost(
            @ApiParam(value = "标题", required = true) @RequestParam String title,
            @ApiParam(value = "内容", required = true) @RequestParam String content,
            @ApiParam(value = "用户ID", required = true) @RequestParam Long userId) {
        Map<String, Object> response = new HashMap<>();
        response.put("postId", 1);
        return response;
    }

    @DeleteMapping("/posts/{id}")
    @ApiOperation("删帖")
    public Map<String, Object> deletePost(
            @ApiParam(value = "帖子ID", required = true) @PathVariable Long id,
            @ApiParam(value = "用户ID", required = true) @RequestParam Long userId) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        return response;
    }

    @PostMapping("/posts/{id}/comments")
    @ApiOperation("评论")
    public Map<String, Object> addComment(
            @ApiParam(value = "帖子ID", required = true) @PathVariable Long id,
            @ApiParam(value = "评论内容", required = true) @RequestParam String content,
            @ApiParam(value = "用户ID", required = true) @RequestParam Long userId) {
        Map<String, Object> response = new HashMap<>();
        response.put("commentId", 1);
        return response;
    }

    @PostMapping("/posts/{id}/like")
    @ApiOperation("点赞")
    public Map<String, Object> likePost(
            @ApiParam(value = "帖子ID", required = true) @PathVariable Long id,
            @ApiParam(value = "用户ID", required = true) @RequestParam Long userId) {
        Map<String, Object> response = new HashMap<>();
        response.put("likeCount", 100);
        return response;
    }

    @PostMapping("/reports")
    @ApiOperation("举报")
    public Map<String, Object> reportPost(
            @ApiParam(value = "帖子ID", required = true) @RequestParam Long postId,
            @ApiParam(value = "举报原因", required = true) @RequestParam String reason,
            @ApiParam(value = "用户ID", required = true) @RequestParam Long userId) {
        Map<String, Object> response = new HashMap<>();
        response.put("reportId", 1);
        return response;
    }
}