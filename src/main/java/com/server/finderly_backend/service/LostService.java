package com.server.finderly_backend.service;

import com.server.finderly_backend.data.entity.Lost;
import com.server.finderly_backend.data.repository.LostRepository;
import com.server.finderly_backend.dto.ResponseDto;
import com.server.finderly_backend.dto.lost.FilteredLostDto;
import com.server.finderly_backend.dto.lost.RegisterLostDto;
import com.server.finderly_backend.dto.lost.RegisterResponseDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LostService {

    private final Logger LOGGER = LoggerFactory.getLogger(LostService.class);
    private final LostRepository lostRepository;
    private final UserService userService;


    // 분실물 등록
    public ResponseEntity<?> registerLost(RegisterLostDto registerLostDto){
        try {
            if(!userService.checkIfUserExists(registerLostDto.getUserId())){
                return new ResponseEntity<>(new ResponseDto("회원가입되지 않은 사용자입니다."), HttpStatus.BAD_REQUEST);
            }
            Lost savedLost = lostRepository.save(new Lost(registerLostDto));
            return new ResponseEntity<>(new RegisterResponseDto("분실물 등록 완료", savedLost.getLostId()), HttpStatus.OK);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            return new ResponseEntity<>(new ResponseDto("분실물 등록 실패"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 분실물 상세 조회
    public ResponseEntity<?> lostDetail(String lostId){
        Optional<Lost> optionalLost = lostRepository.findById(lostId);
        if(optionalLost.isEmpty()){
            return new ResponseEntity<>(new ResponseDto("분실물이 등록되어 있지 않습니다."), HttpStatus.NOT_FOUND);
        }
        Lost lost = optionalLost.get();
        return new ResponseEntity<>(lost, HttpStatus.OK);
    }

    // 분실물 삭제
    public ResponseEntity<ResponseDto> deleteLost(String lostId){
        Optional<Lost> optionalLost = lostRepository.findById(lostId);
        if(optionalLost.isEmpty()){
            return new ResponseEntity<>(new ResponseDto("분실물이 등록되어 있지 않습니다."), HttpStatus.NOT_FOUND);
        }
        try{
            lostRepository.delete(optionalLost.get());
            return new ResponseEntity<>(new ResponseDto("분실물 삭제 완료"), HttpStatus.OK);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            return new ResponseEntity<>(new ResponseDto("분실물 삭제 실패"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 분실물 리스트 조회
    public ResponseEntity<List<FilteredLostDto>> getAllLostList(){
        try{
            List<FilteredLostDto> filteredLosts = lostRepository.findAllWithSelectedFields();
            if(filteredLosts.isEmpty()){
                return new ResponseEntity<>(filteredLosts,HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(filteredLosts);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 분실물 검색
    public ResponseEntity<List<FilteredLostDto>> searchLostByLostName(String lostName){
        List<FilteredLostDto> filteredLosts = lostRepository.findAllByLostNameContaining(lostName);
        if(filteredLosts.isEmpty()){
            return new ResponseEntity<>(filteredLosts,HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(filteredLosts);
    }

}
