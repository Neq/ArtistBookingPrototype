package com.freitag.rest;

import com.freitag.dtos.ArtistFileDTO;
import com.freitag.dtos.ArtistRequestDTO;
import com.freitag.services.DocumentGeneratorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@CrossOrigin
public class ArtistFilesController {

    final static String invoicePath = "C:\\FH\\6. Semester\\Bachelorarbeit\\Rechnungen\\";

    final static String contractPath = "C:\\FH\\6. Semester\\Bachelorarbeit\\Vertraege\\";

    @GetMapping("/artistFiles/{artistId}")
    ResponseEntity<List<ArtistFileDTO>> filesByArtistId(@PathVariable Long artistId) {
        List<String> filesFromArtist = new ArrayList<>();
        filesFromArtist.addAll(listFiles(invoicePath + artistId));
        filesFromArtist.addAll(listFiles(contractPath + artistId));

        List<ArtistFileDTO> artistFiles = new ArrayList<>();
        for(String file : filesFromArtist) {
            ArtistFileDTO artistFileDTO = new ArtistFileDTO();
            artistFileDTO.setFilename(file);
            artistFiles.add(artistFileDTO);
        }

        return ResponseEntity.ok(artistFiles);
    }

    public List<String> listFiles(String directory) {
        if(!Files.exists(Paths.get(directory))) {
            return Collections.emptyList();
        }

        return Stream.of(new File(directory).listFiles())
        .filter(file -> file != null)
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toList());
    }

}
