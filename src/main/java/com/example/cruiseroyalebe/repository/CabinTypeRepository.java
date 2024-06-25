package com.example.cruiseroyalebe.repository;

import com.example.cruiseroyalebe.entity.Cabin;
import com.example.cruiseroyalebe.entity.CabinType;
import com.example.cruiseroyalebe.entity.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CabinTypeRepository extends JpaRepository<CabinType, Integer> {
    Optional<CabinType> findById(Integer id);

    @Query("select c from CabinType c where "
            + "concat(c.id, c.name)"
            + "LIKE %?1%")
    Page<CabinType> findAll(String keyword, Pageable pageable);
}
