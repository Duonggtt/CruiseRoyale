package com.example.cruiseroyalebe.service;

import com.example.cruiseroyalebe.entity.CruiseDetailSection;
import com.example.cruiseroyalebe.modal.dto.SectionsDto;
import com.example.cruiseroyalebe.modal.request.UpsertCruiseDetailSection;

import java.util.List;

public interface CruiseDetailSectionService {
    List<SectionsDto> getAllCruiseDetailSections();
    SectionsDto getCruiseDetailSectionById(Integer id);
    CruiseDetailSection saveCruiseDetailSection(UpsertCruiseDetailSection request);
    void deleteCruiseDetailSection(Integer id);
    CruiseDetailSection updateCruiseDetailSection(Integer id, UpsertCruiseDetailSection request);
    List<SectionsDto> getAllSectionsByCruiseId(Integer cruiseId);
}
