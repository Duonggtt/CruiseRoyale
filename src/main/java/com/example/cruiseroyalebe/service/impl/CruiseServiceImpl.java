package com.example.cruiseroyalebe.service.impl;

import com.example.cruiseroyalebe.entity.*;
import com.example.cruiseroyalebe.exception.NotFoundException;
import com.example.cruiseroyalebe.modal.dto.CruiseDto;
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
import java.util.stream.Collectors;

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
    private final CruiseImageRepository cruiseImageRepository;
    private final ReviewRepository reviewRepository;
    private final CruiseDetailSectionRepository cruiseDetailSectionRepository;


    @Override
    public Page<CruiseDto> getAllCruises(Integer page, Integer limit , String sortField, String sortDirection) {
        Sort sort = Sort.by(sortDirection.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortField);
        PageRequest pageRequest = PageRequest.of(page - 1, limit, sort);
        Page<Cruise> cruisePage = cruiseRepository.findAll(pageRequest);
        List<CruiseDto> cruiseDtos = cruisePage.getContent().stream()
                .map(this::convertToCruiseDto)
                .collect(Collectors.toList());
        return new PageImpl<>(cruiseDtos, pageRequest, cruisePage.getTotalElements());
    }

    @Override
    public List<Cruise> getAllCruises() {
        return cruiseRepository.findAll();
    }


    @Override
    public Page<CruiseDto> findPaginated(Integer page, Integer limit, String sortField, String sortDirection, String keyword) {
        Sort sort = buildSort(sortField, sortDirection);
        Pageable pageable = PageRequest.of(page - 1, limit, sort);
        Page<Cruise> cruisePage;
        if(keyword != null) {
            cruisePage = cruiseRepository.findAll(keyword, pageable);
        } else {
            cruisePage = cruiseRepository.findAll(pageable);
        }
        List<CruiseDto> cruiseDtos = cruisePage.getContent().stream()
                .map(this::convertToCruiseDto)
                .collect(Collectors.toList());
        return new PageImpl<>(cruiseDtos, pageable, cruisePage.getTotalElements());
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

        List<CruiseDetailSection> sections = cruiseDetailSectionRepository.findAllById(request.getCruiseDtSectionIds());

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
        cruise.setSections(sections);
        cruise.setShortDesc(request.getShortDesc());
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

        List<CruiseDetailSection> sections = cruiseDetailSectionRepository.findAllById(request.getCruiseDtSectionIds());

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
        cruise.setSections(sections);
        cruise.setOwner(owner);
        cruise.setShortDesc(request.getShortDesc());
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
    public Cruise getCruiseDetailById(Integer id) {
        return cruiseRepository.findByIdWithSectionsAndSectionImages(id);
    }

    @Override
    public void deleteCruise(Integer id) {
        Cruise cruise = cruiseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cruise not found with id= " + id));
        cruiseRepository.delete(cruise);
    }

    @Override
    public List<CruiseDto> getCruises() {
        List<Cruise> cruises = cruiseRepository.findAll();
        return cruises.stream()
                .map(this::convertToCruiseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Cruise> findCruisesByPriceRange(int priceRange) {
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
        return cruiseRepository.findAllByPriceRange(minPrice, maxPrice);
    }

    @Override
    public List<CruiseDto> getSomeFeaturedCruise() {
        List<Cruise> cruises = cruiseRepository.getSomeFeaturedCruise();
        return cruises.stream()
                .map(this::convertToCruiseDto)
                .collect(Collectors.toList());
    }

    private CruiseDto convertToCruiseDto(Cruise cruise) {
        CruiseDto cruiseDto = new CruiseDto();
        cruiseDto.setId(cruise.getId());
        cruiseDto.setName(cruise.getName());
        cruiseDto.setLaunchedYear(cruise.getLaunchedYear());
        cruiseDto.setCabinQuantity(cruise.getCabinQuantity());
        cruiseDto.setMaterial(cruise.getMaterial());
        cruiseDto.setDescription(cruise.getDescription());
        cruiseDto.setPrice(cruise.getPrice());
        cruiseDto.setOwnedDate(cruise.getOwnedDate());
        cruiseDto.setDepartureTime(cruise.getDepartureTime());
        cruiseDto.setArrivalTime(cruise.getArrivalTime());

        // Map ruleIds
        List<Integer> ruleIds = cruise.getRules().stream()
                .map(Rule::getId)
                .collect(Collectors.toList());
        cruiseDto.setRuleIds(ruleIds);

        // Map tagIds
        List<Integer> tagIds = cruise.getTags().stream()
                .map(Tag::getId)
                .collect(Collectors.toList());
        cruiseDto.setTagIds(tagIds);

        // Set locationId
        cruiseDto.setLocationId(cruise.getLocation().getId());

        // Set ownerId
        cruiseDto.setOwnerId(cruise.getOwner().getId());

        // Map imageIds
        List<CruiseImage> cruiseImages = cruiseImageRepository.findAllByCruiseId(cruise.getId());
        List<Integer> imageIds = cruiseImages.stream()
                .map(CruiseImage::getId)
                .collect(Collectors.toList());
        cruiseDto.setImageIds(imageIds);

        // Set reviewId
//        if (cruise.getReview() != null) {
//            cruiseDto.setReviewId(cruise.getReview().getId());
//        }

        return cruiseDto;
    }

    @Override
    public List<Cruise> getCruisesByLocationId(Integer locationId) {
        return cruiseRepository.findAllByLocation_Id(locationId);
    }

    @Override
    public List<Cruise> getCruisesByNameLike(String name) {
        return cruiseRepository.findAllByNameLike(name);
    }

    @Override
    public List<Cruise> getCruisesByTagIds(List<Integer> tagIds) {
        return cruiseRepository.findAllByTagIds(tagIds);
    }

}
