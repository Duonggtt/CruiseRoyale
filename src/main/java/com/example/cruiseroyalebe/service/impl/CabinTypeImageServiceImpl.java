package com.example.cruiseroyalebe.service.impl;

import com.example.cruiseroyalebe.entity.CabinType;
import com.example.cruiseroyalebe.entity.CabinTypeImage;
import com.example.cruiseroyalebe.entity.Cruise;
import com.example.cruiseroyalebe.entity.CruiseImage;
import com.example.cruiseroyalebe.exception.NotFoundException;
import com.example.cruiseroyalebe.repository.CabinTypeImageRepository;
import com.example.cruiseroyalebe.repository.CabinTypeRepository;
import com.example.cruiseroyalebe.repository.CruiseImageRepository;
import com.example.cruiseroyalebe.repository.CruiseRepository;
import com.example.cruiseroyalebe.service.CabinTypeImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CabinTypeImageServiceImpl implements CabinTypeImageService {
    private final CabinTypeImageRepository cabinTypeImageRepository;
    private final CabinTypeRepository cabinTypeRepository;


    public CabinTypeImage uploadFile(MultipartFile file) throws IOException {

        CabinTypeImage image = new CabinTypeImage();
        image.setType(file.getContentType());
        image.setData(file.getBytes());
        cabinTypeImageRepository.save(image);
        return image;
    }

    public CabinTypeImage getFileById(Integer id) {
        return cabinTypeImageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("File not found with id " + id));
    }

    public void deleteFile(Integer id) {
        CabinTypeImage file = getFileById(id);
        cabinTypeImageRepository.delete(file);
    }
}
