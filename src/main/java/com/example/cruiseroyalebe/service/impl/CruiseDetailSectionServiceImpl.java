package com.example.cruiseroyalebe.service.impl;

import com.example.cruiseroyalebe.entity.Cruise;
import com.example.cruiseroyalebe.entity.CruiseDetailSection;
import com.example.cruiseroyalebe.entity.CruiseDtSectionImage;
import com.example.cruiseroyalebe.exception.NotFoundException;
import com.example.cruiseroyalebe.modal.dto.CruiseBookingDto;
import com.example.cruiseroyalebe.modal.dto.CruiseSectionDto;
import com.example.cruiseroyalebe.modal.dto.SectionsDto;
import com.example.cruiseroyalebe.modal.dto.UserDto;
import com.example.cruiseroyalebe.modal.request.UpsertCruiseDetailSection;
import com.example.cruiseroyalebe.repository.CruiseDetailSectionRepository;
import com.example.cruiseroyalebe.repository.CruiseDtSectionImageRepository;
import com.example.cruiseroyalebe.repository.CruiseRepository;
import com.example.cruiseroyalebe.service.CruiseDetailSectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CruiseDetailSectionServiceImpl implements CruiseDetailSectionService {

    private final CruiseDetailSectionRepository cruiseDetailSectionRepository;
    private final CruiseDtSectionImageRepository cruiseDtSectionImageRepository;
    private final CruiseRepository cruiseRepository;


    public List<SectionsDto> getAllCruiseDetailSections() {
        return cruiseDetailSectionRepository.findAll()
                .stream()
                .map(this::convertToSectionsDto)
                .collect(Collectors.toList());
    }

    @Override
    public SectionsDto getCruiseDetailSectionById(Integer id) {
        return cruiseDetailSectionRepository.findById(id)
                .map(this::convertToSectionsDto)
                .orElseThrow(() -> new NotFoundException("CruiseDetailSection not found"));
    }

    @Override
    public CruiseDetailSection saveCruiseDetailSection(UpsertCruiseDetailSection request) {

        CruiseDtSectionImage cruiseDtSectionImage = cruiseDtSectionImageRepository.findById(request.getCruiseDtSectionImageId())
                .orElseThrow(() -> new RuntimeException("CruiseDtSectionImage not found"));

        Cruise cruise = cruiseRepository.findById(request.getCruiseId())
                .orElseThrow(() -> new RuntimeException("Cruise not found"));

        CruiseDetailSection cruiseDetailSection = new CruiseDetailSection();
        cruiseDetailSection.setText(request.getText());
        cruiseDetailSection.setCruise(cruise);
        cruiseDetailSection.setCruiseDtSectionImage(cruiseDtSectionImage);
        return cruiseDetailSectionRepository.save(cruiseDetailSection);
    }

    @Override
    public CruiseDetailSection updateCruiseDetailSection(Integer id, UpsertCruiseDetailSection request) {
        CruiseDtSectionImage cruiseDtSectionImage = cruiseDtSectionImageRepository.findById(request.getCruiseDtSectionImageId())
                .orElseThrow(() -> new RuntimeException("CruiseDtSectionImage not found"));

        Cruise cruise = cruiseRepository.findById(request.getCruiseId())
                .orElseThrow(() -> new RuntimeException("Cruise not found"));

        CruiseDetailSection cruiseDetailSection = cruiseDetailSectionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CruiseDetailSection not found"));

        cruiseDetailSection.setText(request.getText());
        cruiseDetailSection.setCruise(cruise);
        cruiseDetailSection.setCruiseDtSectionImage(cruiseDtSectionImage);
        return cruiseDetailSectionRepository.save(cruiseDetailSection);
    }

    @Override
    public List<SectionsDto> getAllSectionsByCruiseId(Integer cruiseId) {
        List<CruiseDetailSection> sections = cruiseDetailSectionRepository.findByCruiseId(cruiseId);
        if(sections.isEmpty()) {
            throw new NotFoundException("Sections not found with cruise id= " + cruiseId);
        }
        return sections.stream()
                .map(this::convertToSectionsDto)
                .collect(Collectors.toList());
    }

    private SectionsDto convertToSectionsDto(CruiseDetailSection section) {
        SectionsDto sectionsDto = new SectionsDto();
        sectionsDto.setId(section.getId());
        sectionsDto.setText(section.getText());
        if (section.getCruise() != null) {
            CruiseSectionDto cruiseDto = new CruiseSectionDto();
            cruiseDto.setId(section.getCruise().getId());
            cruiseDto.setName(section.getCruise().getName());
            sectionsDto.setCruiseDto(cruiseDto);
        }
        sectionsDto.setCruiseDtSectionImageId(section.getCruiseDtSectionImage().getId());
        return sectionsDto;
    }

    @Override
    public void deleteCruiseDetailSection(Integer id) {
        CruiseDetailSection cruiseDetailSection = cruiseDetailSectionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CruiseDetailSection not found"));
        cruiseDetailSectionRepository.delete(cruiseDetailSection);
    }



}
