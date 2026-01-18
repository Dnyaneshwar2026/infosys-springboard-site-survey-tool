package com.abhi.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "checklist_items")
public class ChecklistItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String question;       // "Is kitchen clean?"

    private boolean required = false;
    private String type = "text";  // "checkbox", "text", "radio"
    private String response = "";  // User answer

    @ManyToOne
    @JoinColumn(name = "checklist_id")
    private Checklist checklist;

    // Constructors
    public ChecklistItem() {}

    public ChecklistItem(String question, String type) {
        this.question = question;
        this.type = type;
    }

    // Getters/Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }

    public boolean isRequired() { return required; }
    public void setRequired(boolean required) { this.required = required; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getResponse() { return response; }
    public void setResponse(String response) { this.response = response; }

    public Checklist getChecklist() { return checklist; }
    public void setChecklist(Checklist checklist) { this.checklist = checklist; }
}
