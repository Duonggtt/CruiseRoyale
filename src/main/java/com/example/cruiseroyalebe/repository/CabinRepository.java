package com.example.cruiseroyalebe.repository;

import com.example.cruiseroyalebe.entity.Cabin;
import com.example.cruiseroyalebe.entity.Cruise;
import com.example.cruiseroyalebe.modal.respone.CabinResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CabinRepository extends JpaRepository<Cabin, Integer> {
    Optional<Cabin> findById(Integer id);

    @Query("select c from Cabin c where "
            + "concat(c.id, c.cabinType.name, c.cruise.name)"
            + "LIKE %?1%")
    Page<Cabin> findAll(String keyword, Pageable pageable);

    @Query("select c.id, c.cabinType from Cabin c join c.cruise cr where cr.id = ?1")
    List<Cabin> findAllByCruiseId(Integer cruiseId);

    @Query("SELECT c FROM Cabin c JOIN FETCH c.cabinType WHERE c.cruise.id = ?1")
    List<Cabin> findByCruiseIdWithCabinType(Integer cruiseId);

    @Query("SELECT c FROM Cabin c JOIN FETCH c.cabinType ct LEFT JOIN FETCH ct.cabinTypeImages WHERE c.cruise.id = ?1")
    List<Cabin> findByCruiseIdWithCabinTypeAndImages(Integer cruiseId);

    @Query("select c from Cabin c where c.id in ?1")
    List<Cabin> findAllByIds(List<Integer> ids);

    List<Cabin> findByCruiseId(Integer cruiseId);
    void deleteByCruiseId(Integer cruiseId);
}
