package com.example.cruiseroyalebe.service.impl;

import com.example.cruiseroyalebe.entity.CabinType;
import com.example.cruiseroyalebe.entity.CabinTypeImage;
import com.example.cruiseroyalebe.entity.Location;
import com.example.cruiseroyalebe.entity.Tag;
import com.example.cruiseroyalebe.modal.request.UpsertCabinTypeRequest;
import com.example.cruiseroyalebe.repository.CabinTypeImageRepository;
import com.example.cruiseroyalebe.repository.CabinTypeRepository;
import com.example.cruiseroyalebe.repository.TagRepository;
import com.example.cruiseroyalebe.service.CabinTypeService;
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
public class CabinTypeServiceImpl implements CabinTypeService {

    private final CabinTypeRepository cabinTypeRepository;
    private final TagRepository tagRepository;
    private final CabinTypeImageRepository cabinTypeImageRepository;


    @Override
    public Page<CabinType> getAllCabinTypes(Integer page, Integer limit, String sortField, String sortDirection) {
        Sort sort = Sort.by(sortDirection.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortField);
        PageRequest pageRequest = PageRequest.of(page - 1, limit, sort);
        return cabinTypeRepository.findAll(pageRequest);
    }

    @Override
    public Page<CabinType> findPaginated(Integer page, Integer limit, String sortField, String sortDirection, String keyword) {
        Sort sort = buildSort(sortField, sortDirection);

        Pageable pageable = PageRequest.of(page - 1, limit, sort);
        Page<CabinType> cabinTypePage;
        if(keyword != null) {
            cabinTypePage = cabinTypeRepository.findAll(keyword,pageable);
        }else {
            cabinTypePage = cabinTypeRepository.findAll(pageable);
        }

        List<CabinType> cabinTypeList = cabinTypePage.getContent();

        return new PageImpl<>(cabinTypeList, pageable, cabinTypePage.getTotalElements());
    }

    @Override
    public Sort buildSort(String sortField, String sortDirection) {
        return sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
    }

    @Override
    public CabinType createCabinType(UpsertCabinTypeRequest request) {

        List<Tag> tags = tagRepository.findAllById(request.getTagIds());

        List<CabinTypeImage> cabinTypeImages = cabinTypeImageRepository.findAllById(request.getCabinTypeImageIds());

        CabinType cabinType = new CabinType();
        cabinType.setName(request.getName());
        cabinType.setRoomSize(request.getRoomSize());
        cabinType.setMaxGuests(request.getMaxGuests());
        cabinType.setDescription(request.getDescription());
        cabinType.setCabinTypeImages(cabinTypeImages);
        cabinType.setPrice(request.getPrice());
        cabinType.setTags(tags);
        cabinTypeRepository.save(cabinType);
        return cabinType;
    }

    @Override
    public CabinType updateCabinType(Integer id, UpsertCabinTypeRequest request) {
        List<Tag> tags = tagRepository.findAllById(request.getTagIds());

        CabinType cabinType = cabinTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CabinType not found with id= " + id));

        List<CabinTypeImage> cabinTypeImages = cabinTypeImageRepository.findAllById(request.getCabinTypeImageIds());

        cabinType.setName(request.getName());
        cabinType.setRoomSize(request.getRoomSize());
        cabinType.setMaxGuests(request.getMaxGuests());
        cabinType.setDescription(request.getDescription());
        cabinType.setCabinTypeImages(cabinTypeImages);
        cabinType.setPrice(request.getPrice());
        cabinType.setTags(tags);
        cabinTypeRepository.save(cabinType);
        return cabinType;
    }

    @Override
    public CabinType getCabinTypeById(Integer id) {
        return cabinTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CabinType not found with id= " + id));
    }

    @Override
    public void deleteCabinType(Integer id) {
        CabinType cabinType = cabinTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CabinType not found with id= " + id));
        cabinTypeRepository.delete(cabinType);
    }

    @Override
    public List<CabinType> getCabinTypes() {
        return cabinTypeRepository.findAll();
    }
}
