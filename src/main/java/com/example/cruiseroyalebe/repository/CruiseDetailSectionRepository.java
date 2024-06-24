package com.example.cruiseroyalebe.repository;

import com.example.cruiseroyalebe.entity.CruiseDetailSection;
import com.example.cruiseroyalebe.modal.dto.SectionsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CruiseDetailSectionRepository extends JpaRepository<CruiseDetailSection, Integer>{
    List<CruiseDetailSection> findByCruiseId(Integer cruiseId);
}
