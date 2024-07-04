package com.example.cruiseroyalebe.service;

import com.example.cruiseroyalebe.entity.Location;
import com.example.cruiseroyalebe.entity.Owner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface LocationService {
    Page<Location> getAllLocations(Integer page, Integer limit , String sortField, String sortDirection);
    Page<Location> findPaginated(Integer page, Integer limit, String sortField, String sortDirection, String keyword);
    Sort buildSort(String sortField, String sortDirection);
    Location createLocation(Location request);
    Location updateLocation(Integer id, Location request);
    Location getLocationById(Integer id);
    void deleteLocation(Integer id);
    List<Location> getLocations();
    List<Location> getLocationByCityLike(String city);
}
