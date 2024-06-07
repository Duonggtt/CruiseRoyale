package com.example.cruiseroyalebe.repository;

import com.example.cruiseroyalebe.entity.CruiseImage;
import com.example.cruiseroyalebe.entity.Owner;
import com.example.cruiseroyalebe.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer>{
    Optional<Review> findById(Integer id);

    @Query("select r from Review r where "
            + "concat(r.id, r.comment)"
            + "LIKE %?1%")
    Page<Review> findAll(String keyword, Pageable pageable);
}
