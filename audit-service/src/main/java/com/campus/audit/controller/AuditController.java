package com.campus.audit.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/audit")
@Api(tags = "审核服务")
public class AuditController {

    @GetMapping("/reports/{id}")
    @ApiOperation("查询举报")
    public Map<String, Object> getReportStatus(
            @ApiParam(value = "举报ID", required = true) @PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "pending");
        return response;
    }

    @PostMapping("/handle")
    @ApiOperation("处理举报")
    public Map<String, Object> handleReport(
            @ApiParam(value = "举报ID", required = true) @RequestParam Long reportId,
            @ApiParam(value = "处理结果", required = true) @RequestParam String result) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        return response;
    }
}