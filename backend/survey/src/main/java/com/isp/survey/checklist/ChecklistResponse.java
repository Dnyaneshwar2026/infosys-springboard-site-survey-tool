package com.isp.survey.checklist;

import com.isp.survey.space.Space;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "checklist_responses")
public class ChecklistResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String answer;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private ChecklistQuestion question;

    @ManyToOne
    @JoinColumn(name = "space_id")
    private Space space;

    // getters & setters
    public Long getId() {
        return id;
    }

    public String getAnswer() {
        return answer;
    }

    public ChecklistQuestion getQuestion() {
        return question;
    }

    public Space getSpace() {
        return space;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setQuestion(ChecklistQuestion question) {
        this.question = question;
    }

    public void setSpace(Space space) {
        this.space = space;
    }
}
