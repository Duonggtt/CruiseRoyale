package com.example.cruiseroyalebe.service;

import com.example.cruiseroyalebe.entity.Owner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface OwnerService {
    Page<Owner> getAllOwners(Integer page, Integer limit , String sortField, String sortDirection);
    Page<Owner> findPaginated(Integer page, Integer limit, String sortField, String sortDirection, String keyword);
    Sort buildSort(String sortField, String sortDirection);
    Owner createOwner(Owner request);
    Owner updateOwner(Integer id, Owner request);
    Owner getOwnerById(Integer id);
    void deleteOwner(Integer id);
    List<Owner> getOwners();

}
