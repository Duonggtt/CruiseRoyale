package com.example.cruiseroyalebe.service.impl;

import com.example.cruiseroyalebe.entity.CruiseDetailSection;
import com.example.cruiseroyalebe.entity.CruiseDtSectionImage;
import com.example.cruiseroyalebe.modal.request.UpsertCruiseDetailSection;
import com.example.cruiseroyalebe.repository.CruiseDetailSectionRepository;
import com.example.cruiseroyalebe.repository.CruiseDtSectionImageRepository;
import com.example.cruiseroyalebe.service.CruiseDetailSectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CruiseDetailSectionServiceImpl implements CruiseDetailSectionService {

    private final CruiseDetailSectionRepository cruiseDetailSectionRepository;
    private final CruiseDtSectionImageRepository cruiseDtSectionImageRepository;


    public List<CruiseDetailSection> getAllCruiseDetailSections() {
        return cruiseDetailSectionRepository.findAll();
    }

    @Override
    public CruiseDetailSection getCruiseDetailSectionById(Integer id) {
        return cruiseDetailSectionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CruiseDetailSection not found"));
    }

    @Override
    public CruiseDetailSection saveCruiseDetailSection(UpsertCruiseDetailSection request) {

        List<CruiseDtSectionImage> cruiseDtSectionImages = cruiseDtSectionImageRepository.findAllById(request.getCruiseDtSectionImageIds());

        CruiseDetailSection cruiseDetailSection = new CruiseDetailSection();
        cruiseDetailSection.setText(request.getText());
        cruiseDetailSection.setCruiseDtSectionImages(cruiseDtSectionImages);
        return cruiseDetailSectionRepository.save(cruiseDetailSection);
    }

    @Override
    public CruiseDetailSection updateCruiseDetailSection(Integer id, UpsertCruiseDetailSection request) {
        List<CruiseDtSectionImage> cruiseDtSectionImages = cruiseDtSectionImageRepository.findAllById(request.getCruiseDtSectionImageIds());

        CruiseDetailSection cruiseDetailSection = cruiseDetailSectionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CruiseDetailSection not found"));

        cruiseDetailSection.setText(request.getText());
        cruiseDetailSection.setCruiseDtSectionImages(cruiseDtSectionImages);
        return cruiseDetailSectionRepository.save(cruiseDetailSection);
    }

    @Override
    public void deleteCruiseDetailSection(Integer id) {
        CruiseDetailSection cruiseDetailSection = cruiseDetailSectionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CruiseDetailSection not found"));
        cruiseDetailSectionRepository.delete(cruiseDetailSection);
    }

}
