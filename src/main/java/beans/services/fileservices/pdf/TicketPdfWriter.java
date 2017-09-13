package beans.services.fileservices.pdf;

import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;

import beans.models.Ticket;
import beans.services.fileservices.FileWriter;

@Component
public class TicketPdfWriter implements FileWriter<Ticket> {

    @Override
    public String writeDocument(Ticket document) {
        PDPage page = new PDPage();
        String filePath = "C://spring_course/" + generateFileName(document);
        final PDFont courierBoldFont = PDType1Font.COURIER_BOLD;
        final int fontSize = 12;
        try(PDDocument file = new PDDocument()) {
            file.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(file, page);
            contentStream.setFont(courierBoldFont, fontSize);
            writeOnePage(contentStream, document);
            file.save(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return filePath;
    }

    @Override
    public String writeDocuments(List<Ticket> documents) {
        String filePath = "C://spring_course/" + generateFileName(documents.get(0));
        final PDFont courierBoldFont = PDType1Font.COURIER_BOLD;
        final int fontSize = 12;
        try(PDDocument file = new PDDocument()) {
            for (Ticket document : documents) {
                PDPage page = new PDPage();
                file.addPage(page);
                PDPageContentStream contentStream = new PDPageContentStream(file, page);
                contentStream.setFont(courierBoldFont, fontSize);
                writeOnePage(contentStream, document);
            }
            file.save(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return filePath;
    }

    @Override
    public String generateFileName(Ticket document) {
        return "ticket_" + document.getEvent().getName() + ".pdf";
    }

    private void writeOnePage(PDPageContentStream contentStream, Ticket document) throws IOException{
        contentStream.beginText();
        contentStream.newLineAtOffset(10, 20);
        contentStream.showText(document.getEvent().getName());
        contentStream.newLineAtOffset(0, 30);
        contentStream.showText("Place: " + document.getEvent().getAuditorium().getName());
        contentStream.newLineAtOffset(0, 40);
        contentStream.showText("Date: " + document.getDateTime());
        contentStream.newLineAtOffset(0, 50);
        contentStream.showText("Price: " + document.getPrice());
        contentStream.newLineAtOffset(0, 60);
        contentStream.showText("Owner: " + document.getUser().getName());
        contentStream.newLineAtOffset(0, 70);
        contentStream.endText();
        contentStream.close();
    }
}
