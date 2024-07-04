package com.example.cruiseroyalebe.service.impl;

import com.example.cruiseroyalebe.entity.Cruise;
import com.example.cruiseroyalebe.entity.Rule;
import com.example.cruiseroyalebe.entity.Tag;
import com.example.cruiseroyalebe.exception.NotFoundException;
import com.example.cruiseroyalebe.repository.CruiseRepository;
import com.example.cruiseroyalebe.repository.RuleRepository;
import com.example.cruiseroyalebe.repository.TagRepository;
import com.example.cruiseroyalebe.service.RuleService;
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
public class RuleServiceImpl implements RuleService {
    private final RuleRepository ruleRepository;
    private final CruiseRepository cruiseRepository;

    @Override
    public Page<Rule> getAllRules(Integer page, Integer limit , String sortField, String sortDirection) {
        Sort sort = Sort.by(sortDirection.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortField);
        PageRequest pageRequest = PageRequest.of(page - 1, limit, sort);
        return ruleRepository.findAll(pageRequest);
    }

    @Override
    public Page<Rule> findPaginated(Integer page, Integer limit, String sortField, String sortDirection, String keyword) {
        Sort sort = buildSort(sortField, sortDirection);

        Pageable pageable = PageRequest.of(page - 1, limit, sort);
        Page<Rule> rulePage;
        if(keyword != null) {
            rulePage = ruleRepository.findAll(keyword,pageable);
        }else {
            rulePage = ruleRepository.findAll(pageable);
        }

        List<Rule> ruleList = rulePage.getContent();

        return new PageImpl<>(ruleList, pageable, rulePage.getTotalElements());
    }

    @Override
    public Sort buildSort(String sortField, String sortDirection) {
        return sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
    }

    @Override
    public Rule createRule(Rule request) {
        Rule rule = new Rule();
        rule.setContent(request.getContent());
        ruleRepository.save(rule);
        return rule;
    }

    @Override
    public Rule updateRule(Integer id, Rule request) {
        Rule rule = ruleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Rule not found"));
        rule.setContent(request.getContent());
        ruleRepository.save(rule);
        return rule;
    }

    @Override
    public Rule getRuleById(Integer id) {
        return ruleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Rule not found with id= " + id));
    }

    @Override
    public void deleteRule(Integer id) {
        Rule rule = ruleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Rule not found with id = " + id));
        // Remove rule from all Cruises
        List<Cruise> cruisesWithRule = cruiseRepository.findByRulesContaining(rule);
        for (Cruise cruise : cruisesWithRule) {
            cruise.getRules().remove(rule);
            cruiseRepository.save(cruise);
        }
        ruleRepository.delete(rule);
    }

    @Override
    public List<Rule> getRules() {
        return ruleRepository.findAll();
    }


}
