package com.isp.survey.checklist;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChecklistResponseRepository
        extends JpaRepository<ChecklistResponse, Long> {

    List<ChecklistResponse> findBySpaceId(Long spaceId);
}
