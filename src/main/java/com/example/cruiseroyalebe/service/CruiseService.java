package com.example.cruiseroyalebe.service;

import com.example.cruiseroyalebe.entity.Cruise;
import com.example.cruiseroyalebe.entity.Location;
import com.example.cruiseroyalebe.modal.dto.CruiseDto;
import com.example.cruiseroyalebe.modal.request.UpsertCruiseRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.List;

public interface CruiseService {
    Page<CruiseDto> getAllCruises(Integer page, Integer limit , String sortField, String sortDirection);
    List<Cruise> getAllCruises();
    Page<CruiseDto> findPaginated(Integer page, Integer limit, String sortField, String sortDirection, String keyword);
    Sort buildSort(String sortField, String sortDirection);
    Cruise createCruise(UpsertCruiseRequest request);
    Cruise updateCruise(Integer id, UpsertCruiseRequest request);
    Cruise getCruiseById(Integer id);
    void deleteCruise(Integer id);
    List<CruiseDto> getCruises();
    Page<CruiseDto> findCruisesByPriceRange(int priceRange, Integer page, Integer limit , String sortField, String sortDirection);

    List<CruiseDto> getSomeFeaturedCruise();
}
