package com.server.finderly_backend.controller;

import com.server.finderly_backend.dto.LostDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("lost")
public class LostController {

    // 분실물 검색
    @GetMapping("search")
    public String lostSearch(@RequestParam String lostName) {
        return "lost5";
    }

    // 분실물 리스트 조회
    @GetMapping
    public String lostList() {
        return "lost list";
    }

    // 분실물 삭제
    @DeleteMapping
    public String lostDelete(@RequestParam String lostId) {
        return "lost delete";
    }

    // 분실물 상세 조회
    @GetMapping("detail")
    public String lost3(
            @RequestParam(value = "lostId") String lostId,
            @RequestParam(value = "userId") String userId
    ) {
        return "lost detail";
    }

    // 분실물 등록
    @PostMapping
    public String lost(@RequestBody LostDto lostDto) {
        return "regist lost : " + lostDto.toString();
    }

}