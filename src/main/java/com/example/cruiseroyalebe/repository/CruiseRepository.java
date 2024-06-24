package com.example.cruiseroyalebe.repository;

import com.example.cruiseroyalebe.entity.Cabin;
import com.example.cruiseroyalebe.entity.Cruise;
import com.example.cruiseroyalebe.entity.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface CruiseRepository extends JpaRepository<Cruise, Integer> {
    Optional<Cruise> findById(Integer id);

    @Query("select c from Cruise c where "
            + "concat(c.id, c.name)"
            + "LIKE %?1%")
    Page<Cruise> findAll(String keyword, Pageable pageable);


    @Query("select p from Cruise p where p.price >= :minPrice and p.price <= :maxPrice order by p.price desc")
    List<Cruise> findAllByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);

    @Query(value = "SELECT c.* FROM cruise c JOIN (SELECT cruise_id, COUNT(*) as booking_count FROM booking GROUP BY cruise_id ORDER BY booking_count DESC LIMIT 6) b ON c.id = b.cruise_id", nativeQuery = true)
    List<Cruise> getSomeFeaturedCruise();


    List<Cruise> findAllByLocation_Id(Integer locationId);

    @Query("Select c from Cruise c where c.name like %?1%")
    List<Cruise> findAllByNameLike(String name);

    @Query("SELECT DISTINCT c FROM Cruise c JOIN c.tags t WHERE t.id IN :tagIds")
    List<Cruise> findAllByTagIds(List<Integer> tagIds);

}
