package com.isp.survey.checklist;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isp.survey.space.Space;
import com.isp.survey.space.SpaceRepository;

@RestController
@RequestMapping("/api/checklist-responses")
public class ChecklistResponseController {

    private final ChecklistResponseRepository responseRepository;
    private final ChecklistQuestionRepository questionRepository;
    private final SpaceRepository spaceRepository;

    public ChecklistResponseController(
            ChecklistResponseRepository responseRepository,
            ChecklistQuestionRepository questionRepository,
            SpaceRepository spaceRepository) {
        this.responseRepository = responseRepository;
        this.questionRepository = questionRepository;
        this.spaceRepository = spaceRepository;
    }

    // Submit response for a space
    @PostMapping("/space/{spaceId}/question/{questionId}")
    public ChecklistResponse submitResponse(
            @PathVariable Long spaceId,
            @PathVariable Long questionId,
            @RequestBody ChecklistResponse response) {

        Space space = spaceRepository.findById(spaceId)
                .orElseThrow(() -> new RuntimeException("Space not found"));

        ChecklistQuestion question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        response.setSpace(space);
        response.setQuestion(question);

        return responseRepository.save(response);
    }

    // Get all responses for a space
    @GetMapping("/space/{spaceId}")
    public List<ChecklistResponse> getResponsesBySpace(
            @PathVariable Long spaceId) {

        return responseRepository.findBySpaceId(spaceId);
    }
}
