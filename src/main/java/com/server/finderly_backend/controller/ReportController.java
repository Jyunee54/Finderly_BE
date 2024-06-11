package com.server.finderly_backend.controller;

import com.server.finderly_backend.dto.ResponseDto;
import com.server.finderly_backend.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("report")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    // 신고하기
    @GetMapping
    public ResponseEntity<ResponseDto> report(
            @RequestParam(value = "category") int category,
            @RequestParam(value = "id") String id,
            @RequestParam(value = "userId") String userId
    ) {
        return reportService.report(category, id, userId);
    }

}
