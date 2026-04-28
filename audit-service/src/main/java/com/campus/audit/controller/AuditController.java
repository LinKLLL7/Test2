package com.campus.audit.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/v1/audit")
@Api(tags = "审核服务")
public class AuditController {

    private final Map<Long, Map<String, Object>> reports = new ConcurrentHashMap<>();
    private final AtomicLong reportIdGenerator = new AtomicLong(1);

    @GetMapping("/reports/{id}")
    @ApiOperation("查询举报")
    public Map<String, Object> getReportStatus(
            @ApiParam(value = "举报ID", required = true) @PathVariable Long id) {
        Map<String, Object> report = reports.get(id);
        if (report == null) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "举报不存在");
            error.put("reportId", id);
            return error;
        }
        Map<String, Object> response = new HashMap<>();
        response.put("reportId", report.get("reportId"));
        response.put("postId", report.get("postId"));
        response.put("reason", report.get("reason"));
        response.put("status", report.get("status"));
        return response;
    }

    @PostMapping("/handle")
    @ApiOperation("处理举报")
    public Map<String, Object> handleReport(
            @ApiParam(value = "举报ID", required = true) @RequestParam Long reportId,
            @ApiParam(value = "处理结果", required = true) @RequestParam String result) {
        Map<String, Object> report = reports.get(reportId);
        if (report == null) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "举报不存在");
            error.put("reportId", reportId);
            return error;
        }

        report.put("status", result);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("reportId", reportId);
        response.put("status", result);
        return response;
    }

    public void saveReport(Long postId, String reason, Long userId) {
        Long reportId = reportIdGenerator.getAndIncrement();
        Map<String, Object> report = new HashMap<>();
        report.put("reportId", reportId);
        report.put("postId", postId);
        report.put("reason", reason);
        report.put("userId", userId);
        report.put("status", "pending");
        reports.put(reportId, report);
    }
}