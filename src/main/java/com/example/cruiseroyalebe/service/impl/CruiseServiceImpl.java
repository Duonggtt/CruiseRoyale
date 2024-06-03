package com.example.cruiseroyalebe.service.impl;

import com.example.cruiseroyalebe.entity.*;
import com.example.cruiseroyalebe.exception.NotFoundException;
import com.example.cruiseroyalebe.modal.request.UpsertCruiseRequest;
import com.example.cruiseroyalebe.repository.*;
import com.example.cruiseroyalebe.service.CruiseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CruiseServiceImpl implements CruiseService {
    private final CruiseRepository cruiseRepository;
    private final RuleRepository ruleRepository;
    private final LocationRepository locationRepository;
    private final TagRepository tagRepository;
    private final OwnerRepository ownerRepository;

    @Override
    public Page<Cruise> getAllCruises(Integer page, Integer limit , String sortField, String sortDirection) {
        Sort sort = Sort.by(sortDirection.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortField);
        PageRequest pageRequest = PageRequest.of(page - 1, limit, sort);
        return cruiseRepository.findAll(pageRequest);
    }

    @Override
    public Page<Cruise> findPaginated(Integer page, Integer limit, String sortField, String sortDirection, String keyword) {
        Sort sort = buildSort(sortField, sortDirection);

        Pageable pageable = PageRequest.of(page - 1, limit, sort);
        Page<Cruise> cruisePage;
        if(keyword != null) {
            cruisePage = cruiseRepository.findAll(keyword,pageable);
        }else {
            cruisePage = cruiseRepository.findAll(pageable);
        }

        List<Cruise> cruiseList = cruisePage.getContent();

        return new PageImpl<>(cruiseList, pageable, cruisePage.getTotalElements());
    }

    @Override
    public Sort buildSort(String sortField, String sortDirection) {
        return sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
    }

    @Override
    public Cruise createCruise(UpsertCruiseRequest request) {
        List<Rule> rules = ruleRepository.findAllById(request.getRuleIds());
        List<Tag> tags = tagRepository.findAllById(request.getTagIds());
        Owner owner = ownerRepository.findById(request.getOwnerId())
                .orElseThrow(() -> new NotFoundException("Owner not found with id= " + request.getOwnerId()));
        Location location = locationRepository.findById(request.getLocationId())
                .orElseThrow(() -> new NotFoundException("location not found with id= " + request.getLocationId()));

        Cruise cruise = new Cruise();
        cruise.setName(request.getName());
        cruise.setLaunchedYear(request.getLaunchedYear());
        cruise.setCabinQuantity(request.getCabinQuantity());
        cruise.setMaterial(request.getMaterial());
        cruise.setDescription(request.getDescription());
        cruise.setPrice(request.getPrice());
        cruise.setOwnedDate(request.getOwnedDate());
        cruise.setDepartureTime(request.getDepartureTime());
        cruise.setArrivalTime(request.getArrivalTime());
        cruise.setRules(rules);
        cruise.setTags(tags);
        cruise.setOwner(owner);
        cruise.setLocation(location);
        cruiseRepository.save(cruise);
        return cruise;
    }

    @Override
    public Cruise updateCruise(Integer id, UpsertCruiseRequest request) {
        List<Rule> rules = ruleRepository.findAllById(request.getRuleIds());
        List<Tag> tags = tagRepository.findAllById(request.getTagIds());

        Owner owner = ownerRepository.findById(request.getOwnerId())
                .orElseThrow(() -> new NotFoundException("Owner not found with id= " + request.getOwnerId()));

        Location location = locationRepository.findById(request.getLocationId())
                .orElseThrow(() -> new NotFoundException("location not found with id= " + request.getLocationId()));

        Cruise cruise = cruiseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cruise not found with id= " + id));

        cruise.setName(request.getName());
        cruise.setLaunchedYear(request.getLaunchedYear());
        cruise.setCabinQuantity(request.getCabinQuantity());
        cruise.setMaterial(request.getMaterial());
        cruise.setDescription(request.getDescription());
        cruise.setPrice(request.getPrice());
        cruise.setOwnedDate(request.getOwnedDate());
        cruise.setDepartureTime(request.getDepartureTime());
        cruise.setArrivalTime(request.getArrivalTime());
        cruise.setRules(rules);
        cruise.setTags(tags);
        cruise.setOwner(owner);
        cruise.setLocation(location);
        cruiseRepository.save(cruise);
        return cruise;
    }

    @Override
    public Cruise getCruiseById(Integer id) {
        return cruiseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cruise not found with id= " + id));
    }

    @Override
    public void deleteCruise(Integer id) {
        Cruise cruise = cruiseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cruise not found with id= " + id));
        cruiseRepository.delete(cruise);
    }

    @Override
    public List<Cruise> getCruises() {
        return cruiseRepository.findAll();
    }
     
    @Override
    public Page<Cruise> findCruisesByPriceRange(int priceRange, Integer page, Integer limit , String sortField, String sortDirection) {
        BigDecimal minPrice;
        BigDecimal maxPrice;

        switch (priceRange) {
            case 1:
                minPrice = BigDecimal.valueOf(1000000);
                maxPrice = BigDecimal.valueOf(3000000);
                break;
            case 2:
                minPrice = BigDecimal.valueOf(3000000);
                maxPrice = BigDecimal.valueOf(6000000);
                break;
            case 3:
                minPrice = BigDecimal.valueOf(6000000);
                maxPrice = BigDecimal.valueOf(Long.MAX_VALUE);
                break;
            default:
                throw new IllegalArgumentException("Invalid price range");
        }
        Sort sort = Sort.by(sortDirection.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortField);
        PageRequest pageRequest = PageRequest.of(page - 1, limit, sort);
        return cruiseRepository.findAllByPriceRange(minPrice, maxPrice, pageRequest);
    }

}
