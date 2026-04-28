package com.campus.content.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "内容服务")
public class ContentController {

    private final Map<Long, Map<String, Object>> posts = new ConcurrentHashMap<>();
    private final Map<Long, Map<String, Object>> comments = new ConcurrentHashMap<>();
    private final AtomicLong postIdGenerator = new AtomicLong(1);
    private final AtomicLong commentIdGenerator = new AtomicLong(1);

    @PostMapping("/posts")
    @ApiOperation("发帖")
    public Map<String, Object> createPost(
            @ApiParam(value = "标题", required = true) @RequestParam String title,
            @ApiParam(value = "内容", required = true) @RequestParam String content,
            @ApiParam(value = "用户ID", required = true) @RequestParam Long userId) {
        Long postId = postIdGenerator.getAndIncrement();

        Map<String, Object> post = new HashMap<>();
        post.put("postId", postId);
        post.put("title", title);
        post.put("content", content);
        post.put("userId", userId);
        post.put("likeCount", 0);

        posts.put(postId, post);

        Map<String, Object> response = new HashMap<>();
        response.put("postId", postId);
        return response;
    }

    @GetMapping("/posts/{id}")
    @ApiOperation("获取帖子详情")
    public Map<String, Object> getPost(
            @ApiParam(value = "帖子ID", required = true) @PathVariable Long id) {
        Map<String, Object> post = posts.get(id);
        if (post == null) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "帖子不存在");
            error.put("postId", id);
            return error;
        }
        return post;
    }

    @DeleteMapping("/posts/{id}")
    @ApiOperation("删帖")
    public Map<String, Object> deletePost(
            @ApiParam(value = "帖子ID", required = true) @PathVariable Long id,
            @ApiParam(value = "用户ID", required = true) @RequestParam Long userId) {
        Map<String, Object> post = posts.get(id);
        if (post == null) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "帖子不存在");
            error.put("postId", id);
            return error;
        }

        posts.remove(id);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("postId", id);
        return response;
    }

    @PostMapping("/posts/{id}/comments")
    @ApiOperation("评论")
    public Map<String, Object> addComment(
            @ApiParam(value = "帖子ID", required = true) @PathVariable Long id,
            @ApiParam(value = "评论内容", required = true) @RequestParam String content,
            @ApiParam(value = "用户ID", required = true) @RequestParam Long userId) {
        Map<String, Object> post = posts.get(id);
        if (post == null) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "帖子不存在");
            error.put("postId", id);
            return error;
        }

        Long commentId = commentIdGenerator.getAndIncrement();
        Map<String, Object> comment = new HashMap<>();
        comment.put("commentId", commentId);
        comment.put("postId", id);
        comment.put("content", content);
        comment.put("userId", userId);

        comments.put(commentId, comment);

        Map<String, Object> response = new HashMap<>();
        response.put("commentId", commentId);
        return response;
    }

    @PostMapping("/posts/{id}/like")
    @ApiOperation("点赞")
    public Map<String, Object> likePost(
            @ApiParam(value = "帖子ID", required = true) @PathVariable Long id,
            @ApiParam(value = "用户ID", required = true) @RequestParam Long userId) {
        Map<String, Object> post = posts.get(id);
        if (post == null) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "帖子不存在");
            error.put("postId", id);
            return error;
        }

        Integer likeCount = (Integer) post.get("likeCount");
        likeCount++;
        post.put("likeCount", likeCount);

        Map<String, Object> response = new HashMap<>();
        response.put("likeCount", likeCount);
        return response;
    }

    @PostMapping("/reports")
    @ApiOperation("举报")
    public Map<String, Object> reportPost(
            @ApiParam(value = "帖子ID", required = true) @RequestParam Long postId,
            @ApiParam(value = "举报原因", required = true) @RequestParam String reason,
            @ApiParam(value = "用户ID", required = true) @RequestParam Long userId) {
        Map<String, Object> post = posts.get(postId);
        if (post == null) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "帖子不存在");
            error.put("postId", postId);
            return error;
        }

        Long reportId = commentIdGenerator.getAndIncrement();

        Map<String, Object> response = new HashMap<>();
        response.put("reportId", reportId);
        return response;
    }
}