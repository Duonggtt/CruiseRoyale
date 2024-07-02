package com.example.cruiseroyalebe.service;


import com.example.cruiseroyalebe.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface TagService {
    Page<Tag> getAllTags(Integer page, Integer limit , String sortField, String sortDirection);
    Page<Tag> findPaginated(Integer page, Integer limit, String sortField, String sortDirection, String keyword);

    Sort buildSort(String sortField, String sortDirection);
    Tag createTag(Tag request);
    Tag updateTag(Integer id ,Tag request);

    Tag getTagById(Integer id);
    void deleteTag(Integer id);
    List<Tag> getTags();
    List<Tag> getTagsByName(String name);
}
