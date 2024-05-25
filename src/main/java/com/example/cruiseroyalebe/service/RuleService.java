package com.example.cruiseroyalebe.service;

import com.example.cruiseroyalebe.entity.Rule;
import com.example.cruiseroyalebe.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface RuleService {
    Page<Rule> getAllRules(Integer page, Integer limit , String sortField, String sortDirection);
    Page<Rule> findPaginated(Integer page, Integer limit, String sortField, String sortDirection, String keyword);
    Sort buildSort(String sortField, String sortDirection);

    Rule createRule(Rule request);
    Rule updateRule(Integer id ,Rule request);

    Rule getRuleById(Integer id);
    void deleteRule(Integer id);
    List<Rule> getRules();
}
