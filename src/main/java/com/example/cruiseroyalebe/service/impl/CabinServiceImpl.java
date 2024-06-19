package com.example.cruiseroyalebe.service.impl;

import com.example.cruiseroyalebe.entity.Cabin;
import com.example.cruiseroyalebe.entity.CabinType;
import com.example.cruiseroyalebe.entity.Cruise;
import com.example.cruiseroyalebe.modal.request.UpsertCabinRequest;
import com.example.cruiseroyalebe.modal.respone.CabinResponse;
import com.example.cruiseroyalebe.repository.CabinRepository;
import com.example.cruiseroyalebe.repository.CabinTypeRepository;
import com.example.cruiseroyalebe.repository.CruiseRepository;
import com.example.cruiseroyalebe.repository.OwnerRepository;
import com.example.cruiseroyalebe.service.CabinService;
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
public class CabinServiceImpl implements CabinService {

    private final CabinRepository cabinRepository;
    private final CruiseRepository cruiseRepository;
    private final CabinTypeRepository cabinTypeRepository;

    @Override
    public Page<Cabin> getAllCabins(Integer page, Integer limit, String sortField, String sortDirection) {
        Sort sort = Sort.by(sortDirection.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortField);
        PageRequest pageRequest = PageRequest.of(page - 1, limit, sort);
        return cabinRepository.findAll(pageRequest);
    }

    @Override
    public Page<Cabin> findPaginated(Integer page, Integer limit, String sortField, String sortDirection, String keyword) {
        Sort sort = buildSort(sortField, sortDirection);

        Pageable pageable = PageRequest.of(page - 1, limit, sort);
        Page<Cabin> cabinPage;
        if(keyword != null) {
            cabinPage = cabinRepository.findAll(keyword,pageable);
        }else {
            cabinPage = cabinRepository.findAll(pageable);
        }

        List<Cabin> cabinList = cabinPage.getContent();

        return new PageImpl<>(cabinList, pageable, cabinPage.getTotalElements());
    }

    @Override
    public Sort buildSort(String sortField, String sortDirection) {
        return sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
    }

    @Override
    public Cabin createCabin(UpsertCabinRequest request) {
        CabinType cabinType = cabinTypeRepository.findById(request.getCabinTypeId())
                .orElseThrow(() -> new RuntimeException("Cabin Type not found"));

        Cruise cruise = cruiseRepository.findById(request.getCruiseId())
                .orElseThrow(() -> new RuntimeException("Cruise not found"));

        Cabin cabin = new Cabin();
        cabin.setCabinType(cabinType);
        cabin.setCruise(cruise);
        cabinRepository.save(cabin);
        return cabin;
    }

    @Override
    public Cabin updateCabin(Integer id, UpsertCabinRequest request) {
        CabinType cabinType = cabinTypeRepository.findById(request.getCabinTypeId())
                .orElseThrow(() -> new RuntimeException("Cabin Type not found"));

        Cruise cruise = cruiseRepository.findById(request.getCruiseId())
                .orElseThrow(() -> new RuntimeException("Cruise not found"));

        Cabin cabin = cabinRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cabin not found with id= " + id));

        cabin.setCabinType(cabinType);
        cabin.setCruise(cruise);
        cabinRepository.save(cabin);
        return cabin;
    }

    @Override
    public Cabin getCabinById(Integer id) {
        return cabinRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cabin not found with id= " + id));
    }

    @Override
    public List<Cabin> getAllCabinByCruiseId(Integer cruiseId) {
        return cabinRepository.findByCruiseIdWithCabinTypeAndImages(cruiseId);
    }

    @Override
    public void deleteCabin(Integer id) {
        Cabin cabin = cabinRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cabin not found with id= " + id));
        cabinRepository.delete(cabin);
    }

    @Override
    public List<Cabin> getCabins() {
        return cabinRepository.findAll();
    }
}
