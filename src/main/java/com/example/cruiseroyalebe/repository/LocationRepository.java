package com.example.cruiseroyalebe.repository;

import com.example.cruiseroyalebe.entity.Location;
import com.example.cruiseroyalebe.entity.Owner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    Optional<Location> findById(Integer id);

    @Query("select l from Location l where "
            + "concat(l.id, l.routeName, l.address, l.city)"
            + "LIKE %?1%")
    Page<Location> findAll(String keyword, Pageable pageable);

    @Query("SELECT l FROM Location l WHERE l.city LIKE %?1%")
    List<Location> findAllByCityLike(String city);
}
