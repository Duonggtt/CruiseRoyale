package com.example.cruiseroyalebe.repository;

import com.example.cruiseroyalebe.entity.CruiseDtSectionImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CruiseDtSectionImageRepository extends JpaRepository<CruiseDtSectionImage, Integer> {
    void deleteById(Integer id);
}
