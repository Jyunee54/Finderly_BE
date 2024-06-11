package com.server.finderly_backend.data.repository;

import com.server.finderly_backend.data.entity.Lost;
import com.server.finderly_backend.dto.lost.FilteredLostDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LostRepository extends MongoRepository<Lost, String> {
    Optional<Lost> findById(String id);
    Optional<Lost> findByUserId(String userId);
    void deleteById(String id);
    @Query(value = "{}", fields = "{'_id': 1,'lostName': 1,'lostLocation': 1, 'storage': 1}")
    List<FilteredLostDto> findAllWithSelectedFields();
    List<FilteredLostDto> findAllByLostNameContaining(String lostName);
    boolean existsById(String lostId);
    List<FilteredLostDto> findAllByUserId(String userId);
}
