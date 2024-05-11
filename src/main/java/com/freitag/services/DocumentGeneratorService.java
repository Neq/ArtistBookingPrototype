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
    final static String taxNumber = "1234";
    final static String invoiceNumber = String.valueOf(Math.random());
    final static String uidNumber = "ATU 123 123 123";
    final static String wofName = "Tester Test";
    final static String wofBank = "Testbank";

    final static String wofIban = "AT123412341234123";
    final static String wofSwift = "ABDCDEFGH";
    final static String wofAddress = "Attn: Tester Test" +
            "Teststraße 123" +
            "1010 Wien" +
            "Austria";


    public void generateInvoice(String filename, String artistId, ArtistRequest artistRequest) {
        try {
            Artist artist = artistRepository.findById(Long.valueOf(artistId)).orElseThrow(() -> new NullPointerException("Couldn't find Artist with id "+artistId));

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
