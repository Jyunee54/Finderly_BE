package com.server.finderly_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("report")
public class ReportController {

    // 신고하기
    @GetMapping
    public String report(
            @RequestParam(value = "category") int category,
            @RequestParam(value = "id") String id
    ) {
        return "신고하기";
    }

}
