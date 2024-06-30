package com.example.cruiseroyalebe.repository;

import com.example.cruiseroyalebe.entity.CruiseImage;
import com.example.cruiseroyalebe.entity.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CruiseImageRepository extends JpaRepository<CruiseImage, Integer> {
    List<CruiseImage> findAllByCruiseId(Integer cruiseId);

    List<CruiseImage> findByCruise_IdOrderByCreatedAtDesc(Integer id);

    List<CruiseImage> findAllByCruiseIdOrderByCreatedAtDesc(Integer cruiseId);
    void deleteByCruiseId(Integer cruiseId);
}
