package com.example.cruiseroyalebe.repository;

import com.example.cruiseroyalebe.entity.Owner;
import com.example.cruiseroyalebe.entity.Rule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {
    Optional<Owner> findById(Integer id);

    @Query("select o from Owner o where "
            + "concat(o.id, o.name)"
            + "LIKE %?1%")
    Page<Owner> findAll(String keyword, Pageable pageable);
}
