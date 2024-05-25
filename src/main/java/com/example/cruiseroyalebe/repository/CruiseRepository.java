package com.example.cruiseroyalebe.repository;

import com.example.cruiseroyalebe.entity.Cruise;
import com.example.cruiseroyalebe.entity.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CruiseRepository extends JpaRepository<Cruise, Integer> {
    Optional<Cruise> findById(Integer id);

    @Query("select c from Cruise c where "
            + "concat(c.id, c.name)"
            + "LIKE %?1%")
    Page<Cruise> findAll(String keyword, Pageable pageable);
}
