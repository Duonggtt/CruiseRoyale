package com.example.cruiseroyalebe.repository;

import com.example.cruiseroyalebe.entity.CruiseDtSectionImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CruiseDtSectionImageRepository extends JpaRepository<CruiseDtSectionImage, Integer> {
}
