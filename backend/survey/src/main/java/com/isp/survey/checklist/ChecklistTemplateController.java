package com.isp.survey.checklist;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/checklist-templates")
public class ChecklistTemplateController {

    private final ChecklistTemplateRepository repository;

    public ChecklistTemplateController(ChecklistTemplateRepository repository) {
        this.repository = repository;
    }

    // Create checklist template
    @PostMapping
    public ChecklistTemplate createTemplate(
            @RequestBody ChecklistTemplate template) {
        return repository.save(template);
    }

    // Get all checklist templates
    @GetMapping
    public List<ChecklistTemplate> getAllTemplates() {
        return repository.findAll();
    }
}
