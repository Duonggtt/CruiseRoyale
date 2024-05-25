package com.example.cruiseroyalebe.service;

import com.example.cruiseroyalebe.entity.CabinType;
import com.example.cruiseroyalebe.entity.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CabinTypeService {
    Page<CabinType> getAllCabinTypes(Integer page, Integer limit , String sortField, String sortDirection);
    Page<CabinType> findPaginated(Integer page, Integer limit, String sortField, String sortDirection, String keyword);
    Sort buildSort(String sortField, String sortDirection);
    CabinType createCabinType(CabinType request);
    CabinType updateCabinType(Integer id, CabinType request);
    CabinType getCabinTypeById(Integer id);
    void deleteCabinType(Integer id);
    List<CabinType> getCabinTypes();
}
