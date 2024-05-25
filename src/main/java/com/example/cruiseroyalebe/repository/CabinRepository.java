package com.example.cruiseroyalebe.repository;

import com.example.cruiseroyalebe.entity.Cabin;
import com.example.cruiseroyalebe.entity.Cruise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CabinRepository extends JpaRepository<Cabin, Integer> {
    Optional<Cabin> findById(Integer id);

    @Query("select c from Cabin c where "
            + "concat(c.id, c.cabinType.name, c.cruise.name)"
            + "LIKE %?1%")
    Page<Cabin> findAll(String keyword, Pageable pageable);
}
