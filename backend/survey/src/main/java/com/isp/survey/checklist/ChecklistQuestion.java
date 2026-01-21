package com.isp.survey.checklist;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "checklist_questions")
public class ChecklistQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String questionText;

    @Enumerated(EnumType.STRING)
    private AnswerType answerType;

    private boolean required;

    @ManyToOne
    @JoinColumn(name = "template_id")
    private ChecklistTemplate template;

    // getters & setters
    public Long getId() {
        return id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public AnswerType getAnswerType() {
        return answerType;
    }

    public boolean isRequired() {
        return required;
    }

    public ChecklistTemplate getTemplate() {
        return template;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public void setAnswerType(AnswerType answerType) {
        this.answerType = answerType;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public void setTemplate(ChecklistTemplate template) {
        this.template = template;
    }
}
