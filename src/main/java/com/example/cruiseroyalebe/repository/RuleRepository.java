package com.example.cruiseroyalebe.repository;

import com.example.cruiseroyalebe.entity.Rule;
import com.example.cruiseroyalebe.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Repository
public interface RuleRepository extends JpaRepository<Rule, Integer> {
    Optional<Rule> findById(Integer id);
    List<Rule> findAllById(Integer id);

    @Query("select r from Rule r where "
            + "concat(r.id, r.content)"
            + "LIKE %?1%")
    Page<Rule> findAll(String keyword, Pageable pageable);


}
