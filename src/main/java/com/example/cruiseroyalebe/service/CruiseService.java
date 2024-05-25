package com.example.cruiseroyalebe.service;

import com.example.cruiseroyalebe.entity.Cruise;
import com.example.cruiseroyalebe.entity.Location;
import com.example.cruiseroyalebe.modal.request.UpsertCruiseRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CruiseService {
    Page<Cruise> getAllCruises(Integer page, Integer limit , String sortField, String sortDirection);
    Page<Cruise> findPaginated(Integer page, Integer limit, String sortField, String sortDirection, String keyword);
    Sort buildSort(String sortField, String sortDirection);
    Cruise createCruise(UpsertCruiseRequest request);
    Cruise updateCruise(Integer id, UpsertCruiseRequest request);
    Cruise getCruiseById(Integer id);
    void deleteCruise(Integer id);
    List<Cruise> getCruises();
}
