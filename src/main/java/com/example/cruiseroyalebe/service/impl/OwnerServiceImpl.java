package com.example.cruiseroyalebe.service.impl;

import com.example.cruiseroyalebe.entity.Owner;
import com.example.cruiseroyalebe.entity.Rule;
import com.example.cruiseroyalebe.exception.NotFoundException;
import com.example.cruiseroyalebe.repository.OwnerRepository;
import com.example.cruiseroyalebe.repository.RuleRepository;
import com.example.cruiseroyalebe.service.OwnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;

    @Override
    public Page<Owner> getAllOwners(Integer page, Integer limit , String sortField, String sortDirection) {
        Sort sort = Sort.by(sortDirection.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortField);
        PageRequest pageRequest = PageRequest.of(page - 1, limit, sort);
        return ownerRepository.findAll(pageRequest);
    }

    @Override
    public Page<Owner> findPaginated(Integer page, Integer limit, String sortField, String sortDirection, String keyword) {
        Sort sort = buildSort(sortField, sortDirection);

        Pageable pageable = PageRequest.of(page - 1, limit, sort);
        Page<Owner> ownerPage;
        if(keyword != null) {
            ownerPage = ownerRepository.findAll(keyword,pageable);
        }else {
            ownerPage = ownerRepository.findAll(pageable);
        }

        List<Owner> ownerList = ownerPage.getContent();

        return new PageImpl<>(ownerList, pageable, ownerPage.getTotalElements());
    }

    @Override
    public Sort buildSort(String sortField, String sortDirection) {
        return sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
    }

    @Override
    public Owner createOwner(Owner request) {
        Owner owner = new Owner();
        owner.setName(request.getName());
        ownerRepository.save(owner);
        return owner;
    }

    @Override
    public Owner updateOwner(Integer id, Owner request) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Owner not found"));
        owner.setName(request.getName());
        ownerRepository.save(owner);
        return owner;
    }

    @Override
    public Owner getOwnerById(Integer id) {
        return ownerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Owner not found with id= " + id));
    }

    @Override
    public void deleteOwner(Integer id) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Owner not found with id = " + id));
        ownerRepository.delete(owner);
    }

    @Override
    public List<Owner> getOwners() {
        return ownerRepository.findAll();
    }

}
