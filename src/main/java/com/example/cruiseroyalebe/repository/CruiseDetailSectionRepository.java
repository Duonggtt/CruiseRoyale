package com.example.cruiseroyalebe.repository;

import com.example.cruiseroyalebe.entity.CruiseDetailSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CruiseDetailSectionRepository extends JpaRepository<CruiseDetailSection, Integer>{
}
