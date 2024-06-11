package com.server.finderly_backend.controller;

import com.server.finderly_backend.dto.ResponseDto;
import com.server.finderly_backend.dto.lost.FilteredLostDto;
import com.server.finderly_backend.dto.lost.RegisterLostDto;
import com.server.finderly_backend.service.LostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("lost")
public class LostController {

    private final LostService lostService;

    public LostController(LostService lostService) {
        this.lostService = lostService;
    }

    // 분실물 검색
    @GetMapping("search")
    public ResponseEntity<?> searchLost(@RequestParam String keyword) {
        if(keyword == null || keyword.isEmpty()){
            return new ResponseEntity<>(new ResponseDto("검색어를 입력해주세요."), HttpStatus.BAD_REQUEST);
        }
        return lostService.searchLostByLostName(keyword);
    }

    // 분실물 리스트 조회
    @GetMapping
    public ResponseEntity<List<FilteredLostDto>> getAllLostList() {
        return lostService.getAllLostList();
    }

    // 분실물 삭제
    @DeleteMapping("delete")
    public ResponseEntity<?> deleteLost(@RequestParam String lostId) {
        return lostService.deleteLost(lostId);
    }

    // 분실물 상세 조회
    @GetMapping("detail")
    public ResponseEntity<?> lostDetail(@RequestParam(value = "lostId") String lostId) {
        return lostService.lostDetail(lostId);
    }

    // 분실물 등록
    @PostMapping("register")
    public ResponseEntity<?> registerLost(@Valid @RequestBody RegisterLostDto registerLostDto) {
        return lostService.registerLost(registerLostDto);
    }

}