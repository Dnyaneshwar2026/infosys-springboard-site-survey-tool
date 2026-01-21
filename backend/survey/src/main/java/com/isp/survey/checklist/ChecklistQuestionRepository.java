package com.isp.survey.checklist;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChecklistQuestionRepository
        extends JpaRepository<ChecklistQuestion, Long> {

    List<ChecklistQuestion> findByTemplateId(Long templateId);
}
