package com.freitag.services;

import com.freitag.repositories.ContractTemplateRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;


@Service
public class DocumentGeneratorService {

    @Autowired
    ContractTemplateRepository contractTemplateRepository;

    final static String invoicePath = "C:\\FH\\6. Semester\\Bachelorarbeit\\Rechnungen\\";
    final static String contractPath = "C:\\FH\\6. Semester\\Bachelorarbeit\\Vertraege\\";

    // Contract properties
    final static String taxNumber = "195/0632";
    final static String invoiceNumber = String.valueOf(Math.random());
    final static String uidNumber = "ATU 628 23 118";
    final static String wofName = "Mathias Freitag";
    final static String wofBank = "Volksbank Süd-Ost Steiermark";

    final static String wofIban = "AT094477031519820000";
    final static String wofSwift = "VBOEATWWGRA";
    final static String wofAddress = "Attn: Mathias Freitag" +
            "Angerstraße 15/3" +
            "8230 Hartberg" +
            "Austria";


    public void generateInvoice(String filename, String artistId) {
        try {

            File inputHTML = new File("C:\\FH\\6. Semester\\Bachelorarbeit\\ArtistBookingPrototype\\ArtistBookingPrototype\\src\\main\\resources\\test.html");


            Document document = Jsoup.parse(inputHTML, "UTF-8");
            document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);

            File outputPdf = new File(invoicePath + artistId + File.separator + filename + ".pdf");
            outputPdf.getParentFile().mkdirs();

            try (OutputStream outputStream = new FileOutputStream(outputPdf)) {
                ITextRenderer renderer = new ITextRenderer();
                SharedContext sharedContext = renderer.getSharedContext();
                sharedContext.setPrint(true);
                sharedContext.setInteractive(false);
                renderer.setDocumentFromString(document.html().replace("{management}", "Saukopf"));
                renderer.layout();
                renderer.createPDF(outputStream);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void generateContract(String filename, String artistId, String contractTemplateId) {
        try {

            File inputHTML = new File("C:\\FH\\6. Semester\\Bachelorarbeit\\ArtistBookingPrototype\\ArtistBookingPrototype\\src\\main\\resources\\test.html");

            inputHTML = Files.createFile(inputHTML.toPath()).toFile();

            Document document = Jsoup.parse(inputHTML, "UTF-8");
            document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);

            File outputPdf = new File(contractPath + artistId + File.separator + filename + ".pdf");
            outputPdf.getParentFile().mkdirs();

            try (OutputStream outputStream = new FileOutputStream(outputPdf)) {
                ITextRenderer renderer = new ITextRenderer();
                SharedContext sharedContext = renderer.getSharedContext();
                sharedContext.setPrint(true);
                sharedContext.setInteractive(false);
                renderer.setDocumentFromString(document.html());
                renderer.layout();
                renderer.createPDF(outputStream);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DocumentGeneratorService service = new DocumentGeneratorService();
        service.generateInvoice("test", "1");
    }

}
