package com.isp.survey.report;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isp.survey.checklist.ChecklistResponse;
import com.isp.survey.checklist.ChecklistResponseRepository;
import com.isp.survey.space.Space;
import com.isp.survey.space.SpaceRepository;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "http://localhost:3000")
public class ReportController {

    private final PdfReportService pdfReportService;
    private final SpaceRepository spaceRepository;
    private final ChecklistResponseRepository checklistResponseRepository;

    public ReportController(
            PdfReportService pdfReportService,
            SpaceRepository spaceRepository,
            ChecklistResponseRepository checklistResponseRepository) {

        this.pdfReportService = pdfReportService;
        this.spaceRepository = spaceRepository;
        this.checklistResponseRepository = checklistResponseRepository;
    }

    @GetMapping("/space/{spaceId}/pdf")
    public ResponseEntity<byte[]> generateSpacePdf(@PathVariable Long spaceId) {

        Space space = spaceRepository.findById(spaceId)
                .orElseThrow(() -> new RuntimeException("Space not found"));

        List<ChecklistResponse> responses =
                checklistResponseRepository.findBySpaceId(spaceId);

        byte[] pdfBytes =
                pdfReportService.generateSpaceReport(space, responses);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=site-survey-report.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
}
