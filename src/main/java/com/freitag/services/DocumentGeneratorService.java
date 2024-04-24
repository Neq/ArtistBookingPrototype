package com.freitag.services;

import com.freitag.entities.Artist;
import com.freitag.entities.ArtistRequest;
import com.freitag.entities.ContractTemplate;
import com.freitag.repositories.ArtistRepository;
import com.freitag.repositories.ArtistRequestRepository;
import com.freitag.repositories.ContractTemplateRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class DocumentGeneratorService {

    @Autowired
    ContractTemplateRepository contractTemplateRepository;

    @Autowired
    ArtistRequestRepository artistRequestRepository;

    @Autowired
    ArtistRepository artistRepository;

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


    public void generateInvoice(String filename, String artistId, ArtistRequest artistRequest) {
        try {
            Artist artist = artistRepository.findById(Long.valueOf(artistId)).orElseThrow(() -> new NullPointerException("Couldn't find Artist with id "+artistId));

            //File inputHTML = new File("C:\\FH\\6. Semester\\Bachelorarbeit\\ArtistBookingPrototype\\ArtistBookingPrototype\\src\\main\\resources\\test.html");
            Document document = Jsoup.parse(artist.getInvoiceTemplate().getTemplate(), "UTF-8");
            document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);

            String replacedDocumentHtml = replaceTemplateStrings(document.html(), artist, artistRequest);

            File outputPdf = new File(invoicePath + artistId + File.separator + filename + ".pdf");
            outputPdf.getParentFile().mkdirs();

            try (OutputStream outputStream = new FileOutputStream(outputPdf)) {
                ITextRenderer renderer = new ITextRenderer();
                SharedContext sharedContext = renderer.getSharedContext();
                sharedContext.setPrint(true);
                sharedContext.setInteractive(false);
                renderer.setDocumentFromString(replacedDocumentHtml);
                renderer.layout();
                renderer.createPDF(outputStream);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void generateContract(String filename, String artistId, ArtistRequest artistRequest) {
        try {

            Artist artist = artistRepository.findById(Long.valueOf(artistId)).orElseThrow(() -> new NullPointerException("Couldn't find Artist with id "+artistId));

            //File inputHTML = new File("C:\\FH\\6. Semester\\Bachelorarbeit\\ArtistBookingPrototype\\ArtistBookingPrototype\\src\\main\\resources\\test.html");

            //inputHTML = Files.createFile(inputHTML.toPath()).toFile();

            Document document = Jsoup.parse(artist.getContractTemplate().getTemplate(), "UTF-8");
            document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);

            String replacedDocumentHtml = replaceTemplateStrings(document.html(), artist, artistRequest);

            File outputPdf = new File(contractPath + artistId + File.separator + filename + ".pdf");
            outputPdf.getParentFile().mkdirs();

            try (OutputStream outputStream = new FileOutputStream(outputPdf)) {
                ITextRenderer renderer = new ITextRenderer();
                SharedContext sharedContext = renderer.getSharedContext();
                sharedContext.setPrint(true);
                sharedContext.setInteractive(false);
                renderer.setDocumentFromString(replacedDocumentHtml);
                renderer.layout();
                renderer.createPDF(outputStream);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private String replaceTemplateStrings(String templatePreTransformation, Artist artist, ArtistRequest artistRequest) {
        HashMap<String, String> templateStrings = new HashMap<>();
        templateStrings.put("\\{name\\}", artistRequest.getInvoiceName());
        templateStrings.put("\\{management\\}", artist.getManagement());
        templateStrings.put("\\{invoiceAddress\\}", artistRequest.getInvoiceAddress());
        templateStrings.put("\\{invoiceZip\\}", artistRequest.getInvoiceZipCode());
        templateStrings.put("\\{invoiceCountry\\}", artistRequest.getInvoiceCountry());
        String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        templateStrings.put("\\{invoiceDate\\}", currentDate);
        templateStrings.put("\\{invoiceNumber\\}", "122");
        templateStrings.put("\\{invoicePlace\\}", artistRequest.getInvoicePlace());
        templateStrings.put("\\{taxNumber\\}", taxNumber);
        templateStrings.put("\\{uidNumber\\}", uidNumber);
        templateStrings.put("\\{wofName\\}", wofName);
        templateStrings.put("\\{wofBank\\}", wofBank);
        templateStrings.put("\\{wofIban\\}", wofIban);
        templateStrings.put("\\{wofSwift\\}", wofSwift);
        templateStrings.put("\\{wofAddress\\}", wofAddress);

        for(Map.Entry<String, String> entry : templateStrings.entrySet()) {
            templatePreTransformation = templatePreTransformation.replaceAll(entry.getKey(), entry.getValue());
        }

        return templatePreTransformation;
    }

}
