package com.example.cruiseroyalebe.repository;

import com.example.cruiseroyalebe.entity.CabinTypeImage;
import com.example.cruiseroyalebe.entity.CruiseImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CabinTypeImageRepository extends JpaRepository<CabinTypeImage, Integer> {

}
