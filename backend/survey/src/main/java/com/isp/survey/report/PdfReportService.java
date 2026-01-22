package com.isp.survey.report;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.isp.survey.checklist.ChecklistResponse;
import com.isp.survey.space.Space;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

@Service
public class PdfReportService {

    public byte[] generateSpaceReport(Space space, List<ChecklistResponse> responses) {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        document.add(new Paragraph("Site Survey Report")
                .setBold()
                .setFontSize(18));

        document.add(new Paragraph(" "));
        document.add(new Paragraph("Campus: " +
                space.getFloor().getBuilding().getCampus().getName()));
        document.add(new Paragraph("Building: " +
                space.getFloor().getBuilding().getName()));
        document.add(new Paragraph("Floor: " +
                space.getFloor().getFloorNumber()));
        document.add(new Paragraph("Space: " + space.getName()));
        document.add(new Paragraph("Type: " + space.getType()));

        document.add(new Paragraph(" "));
        document.add(new Paragraph("Survey Responses").setBold());

        for (ChecklistResponse response : responses) {
            document.add(new Paragraph(
                    response.getQuestion().getQuestionText()
                            + " : "
                            + response.getAnswer()
            ));
        }

        document.close();
        return outputStream.toByteArray();
    }
}
