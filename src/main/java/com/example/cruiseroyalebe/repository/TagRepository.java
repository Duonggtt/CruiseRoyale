package com.example.cruiseroyalebe.repository;

import com.example.cruiseroyalebe.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {
    Optional<Tag> findById(Integer id);

    @Query("select t from Tag t where "
            + "concat(t.id, t.name)"
            + "LIKE %?1%")
    Page<Tag> findAll(String keyword, Pageable pageable);

    List<Tag> findAll();

    @Query("Select t from Tag t where t.name like %?1%")
    List<Tag> findAllByNameLike(String name);

}
