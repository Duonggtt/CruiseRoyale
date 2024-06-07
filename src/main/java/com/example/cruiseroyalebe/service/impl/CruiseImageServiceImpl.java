package com.example.cruiseroyalebe.service.impl;

import com.example.cruiseroyalebe.entity.Cruise;
import com.example.cruiseroyalebe.entity.CruiseImage;
import com.example.cruiseroyalebe.entity.User;
import com.example.cruiseroyalebe.entity.UserImage;
import com.example.cruiseroyalebe.exception.NotFoundException;
import com.example.cruiseroyalebe.repository.CruiseImageRepository;
import com.example.cruiseroyalebe.repository.CruiseRepository;
import com.example.cruiseroyalebe.service.CruiseImageService;
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
public class CruiseImageServiceImpl implements CruiseImageService {
    private final CruiseImageRepository cruiseImageRepository;
    private final CruiseRepository cruiseRepository;

    public List<CruiseImage> getFilesOfCurrentCruise(Integer cruiseId) {
        return cruiseImageRepository.findByCruise_IdOrderByCreatedAtDesc(cruiseId);
    }

    public CruiseImage uploadFile(MultipartFile file, Integer cruiseId) throws IOException {

        Cruise cruise = cruiseRepository.findById(cruiseId)
                .orElseThrow(() -> new NotFoundException("Cruise not found with id " + cruiseId));

        CruiseImage image = new CruiseImage();
        image.setType(file.getContentType());
        image.setData(file.getBytes());
        image.setCruise(cruise);
        cruiseImageRepository.save(image);
        return image;
    }

    public CruiseImage getFileById(Integer id) {
        return cruiseImageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("File not found with id " + id));
    }

    public void deleteFile(Integer id) {
        CruiseImage file = getFileById(id);
        cruiseImageRepository.delete(file);
    }
}
