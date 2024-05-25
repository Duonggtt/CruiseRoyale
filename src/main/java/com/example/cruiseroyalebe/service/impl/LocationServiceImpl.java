package com.example.cruiseroyalebe.service.impl;

import com.example.cruiseroyalebe.entity.Location;
import com.example.cruiseroyalebe.entity.Owner;
import com.example.cruiseroyalebe.exception.NotFoundException;
import com.example.cruiseroyalebe.repository.LocationRepository;
import com.example.cruiseroyalebe.repository.OwnerRepository;
import com.example.cruiseroyalebe.service.LocationService;
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
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;

    @Override
    public Page<Location> getAllLocations(Integer page, Integer limit , String sortField, String sortDirection) {
        Sort sort = Sort.by(sortDirection.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortField);
        PageRequest pageRequest = PageRequest.of(page - 1, limit, sort);
        return locationRepository.findAll(pageRequest);
    }

    @Override
    public Page<Location> findPaginated(Integer page, Integer limit, String sortField, String sortDirection, String keyword) {
        Sort sort = buildSort(sortField, sortDirection);

        Pageable pageable = PageRequest.of(page - 1, limit, sort);
        Page<Location> locationPage;
        if(keyword != null) {
            locationPage = locationRepository.findAll(keyword,pageable);
        }else {
            locationPage = locationRepository.findAll(pageable);
        }

        List<Location> locationList = locationPage.getContent();

        return new PageImpl<>(locationList, pageable, locationPage.getTotalElements());
    }

    @Override
    public Sort buildSort(String sortField, String sortDirection) {
        return sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
    }

    @Override
    public Location createLocation(Location request) {
        Location location = new Location();
        location.setRouteName(request.getRouteName());
        location.setAddress(request.getAddress());
        location.setCity(request.getCity());
        locationRepository.save(location);
        return location;
    }

    @Override
    public Location updateLocation(Integer id, Location request) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Location not found"));
        location.setRouteName(request.getRouteName());
        location.setAddress(request.getAddress());
        location.setCity(request.getCity());
        locationRepository.save(location);
        return location;
    }

    @Override
    public Location getLocationById(Integer id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Location not found with id= " + id));
    }

    @Override
    public void deleteLocation(Integer id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Location not found with id = " + id));
        locationRepository.delete(location);
    }

    @Override
    public List<Location> getLocations() {
        return locationRepository.findAll();
    }

}
