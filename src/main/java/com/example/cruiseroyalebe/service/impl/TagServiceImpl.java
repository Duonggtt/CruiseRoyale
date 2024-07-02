package com.example.cruiseroyalebe.service.impl;

import com.example.cruiseroyalebe.entity.CabinType;
import com.example.cruiseroyalebe.entity.Cruise;
import com.example.cruiseroyalebe.entity.Tag;
import com.example.cruiseroyalebe.exception.NotFoundException;
import com.example.cruiseroyalebe.repository.CabinTypeRepository;
import com.example.cruiseroyalebe.repository.CruiseRepository;
import com.example.cruiseroyalebe.repository.TagRepository;
import com.example.cruiseroyalebe.service.TagService;
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
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final CabinTypeRepository cabinTypeRepository;
    private final CruiseRepository cruiseRepository;

    @Override
    public Page<Tag> getAllTags(Integer page, Integer limit , String sortField, String sortDirection) {
        Sort sort = Sort.by(sortDirection.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortField);
        PageRequest pageRequest = PageRequest.of(page - 1, limit, sort);
        return tagRepository.findAll(pageRequest);
    }

    @Override
    public Page<Tag> findPaginated(Integer page, Integer limit, String sortField, String sortDirection, String keyword) {
        Sort sort = buildSort(sortField, sortDirection);

        Pageable pageable = PageRequest.of(page - 1, limit, sort);
        Page<Tag> tagPage;
        if(keyword != null) {
            tagPage = tagRepository.findAll(keyword,pageable);
        }else {
            tagPage = tagRepository.findAll(pageable);
        }

        List<Tag> tagList = tagPage.getContent();

        return new PageImpl<>(tagList, pageable, tagPage.getTotalElements());
    }

    @Override
    public Sort buildSort(String sortField, String sortDirection) {
        return sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
    }

    @Override
    public Tag createTag(Tag request) {
        Tag tag = new Tag();
        tag.setIcon(request.getIcon());
        tag.setName(request.getName());
        tagRepository.save(tag);
        return tag;
    }

    @Override
    public Tag updateTag(Integer id ,Tag request) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tag not found with id = " + id));

        tag.setIcon(request.getIcon());
        tag.setName(request.getName());
        tagRepository.save(tag);
        return tag;
    }

    @Override
    public Tag getTagById(Integer id) {
        return tagRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tag not found with id = " + id));
    }

    @Override
    public List<Tag> getTagsByName(String name) {
        return tagRepository.findAllByNameLike(name);
    }

    @Override
    @Transactional
    public void deleteTag(Integer tagId) {
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new NotFoundException("Tag not found"));

        // Remove tag from all Cruises
        List<Cruise> cruisesWithTag = cruiseRepository.findByTagsContaining(tag);
        for (Cruise cruise : cruisesWithTag) {
            cruise.getTags().remove(tag);
            cruiseRepository.save(cruise);
        }

        // Remove tag from all CabinTypes
        List<CabinType> cabinTypesWithTag = cabinTypeRepository.findByTagsContaining(tag);
        for (CabinType cabinType : cabinTypesWithTag) {
            cabinType.getTags().remove(tag);
            cabinTypeRepository.save(cabinType);
        }

        // Finally, delete the tag
        tagRepository.delete(tag);
    }
    @Override
    public List<Tag> getTags() {
        return tagRepository.findAll();
    }

}
