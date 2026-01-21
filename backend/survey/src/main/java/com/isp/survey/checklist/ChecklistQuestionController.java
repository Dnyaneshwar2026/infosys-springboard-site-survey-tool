package com.isp.survey.checklist;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/checklist-questions")
public class ChecklistQuestionController {

    private final ChecklistQuestionRepository questionRepository;
    private final ChecklistTemplateRepository templateRepository;

    public ChecklistQuestionController(
            ChecklistQuestionRepository questionRepository,
            ChecklistTemplateRepository templateRepository) {
        this.questionRepository = questionRepository;
        this.templateRepository = templateRepository;
    }

    // Add question to template
    @PostMapping("/template/{templateId}")
    public ChecklistQuestion addQuestion(
            @PathVariable Long templateId,
            @RequestBody ChecklistQuestion question) {

        ChecklistTemplate template =
                templateRepository.findById(templateId)
                        .orElseThrow(() -> new RuntimeException("Template not found"));

        question.setTemplate(template);
        return questionRepository.save(question);
    }

    // Get questions by template
    @GetMapping("/template/{templateId}")
    public List<ChecklistQuestion> getQuestionsByTemplate(
            @PathVariable Long templateId) {

        return questionRepository.findByTemplateId(templateId);
    }
}
