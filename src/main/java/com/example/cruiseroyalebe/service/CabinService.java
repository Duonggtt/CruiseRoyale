package com.example.cruiseroyalebe.service;

import com.example.cruiseroyalebe.entity.Cabin;
import com.example.cruiseroyalebe.entity.CabinType;
import com.example.cruiseroyalebe.modal.request.UpsertCabinRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CabinService {
    Page<Cabin> getAllCabins(Integer page, Integer limit , String sortField, String sortDirection);
    Page<Cabin> findPaginated(Integer page, Integer limit, String sortField, String sortDirection, String keyword);
    Sort buildSort(String sortField, String sortDirection);
    Cabin createCabin(UpsertCabinRequest request);
    Cabin updateCabin(Integer id, UpsertCabinRequest request);
    Cabin getCabinById(Integer id);
    void deleteCabin(Integer id);
    List<Cabin> getCabins();
}
