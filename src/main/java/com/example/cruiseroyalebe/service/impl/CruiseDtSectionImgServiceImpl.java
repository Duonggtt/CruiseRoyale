package com.example.cruiseroyalebe.service.impl;

import com.example.cruiseroyalebe.entity.CabinTypeImage;
import com.example.cruiseroyalebe.entity.CruiseDtSectionImage;
import com.example.cruiseroyalebe.exception.NotFoundException;
import com.example.cruiseroyalebe.repository.CabinTypeImageRepository;
import com.example.cruiseroyalebe.repository.CabinTypeRepository;
import com.example.cruiseroyalebe.repository.CruiseDetailSectionRepository;
import com.example.cruiseroyalebe.repository.CruiseDtSectionImageRepository;
import com.example.cruiseroyalebe.service.CruiseDtSectionImgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CruiseDtSectionImgServiceImpl implements CruiseDtSectionImgService {
    private final CruiseDtSectionImageRepository cruiseDtSectionImageRepository;
    private final CruiseDetailSectionRepository cruiseDetailSectionRepository;



    public CruiseDtSectionImage uploadFile(MultipartFile file) throws IOException {

        CruiseDtSectionImage image = new CruiseDtSectionImage();
        image.setType(file.getContentType());
        image.setData(file.getBytes());
        cruiseDtSectionImageRepository.save(image);
        return image;
    }

    public CruiseDtSectionImage getFileById(Integer id) {
        return cruiseDtSectionImageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("File not found with id " + id));
    }

    public void deleteFile(Integer id) {
        CruiseDtSectionImage file = getFileById(id);
        cruiseDtSectionImageRepository.delete(file);
    }
}
