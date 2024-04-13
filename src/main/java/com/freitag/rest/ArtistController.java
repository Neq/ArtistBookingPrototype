package com.freitag.rest;

import com.freitag.dtos.ArtistDTO;
import com.freitag.dtos.ArtistRequestDTO;
import com.freitag.entities.Artist;
import com.freitag.entities.ArtistRequest;
import com.freitag.entities.ContractTemplate;
import com.freitag.repositories.ArtistRepository;
import com.freitag.repositories.ContractTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class ArtistController {

    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    ContractTemplateRepository contractTemplateRepository;

    @GetMapping("/artists")
    public ResponseEntity<List<ArtistDTO>> all() {
        List<ArtistDTO> artistDtos = new ArrayList<>();
        for(Artist artist : artistRepository.findAll().stream().toList()) {
            ArtistDTO artistDto = new ArtistDTO();
            artistDto.toDTOFromObject(artist);
            artistDtos.add(artistDto);
        }
        return ResponseEntity.ok(artistDtos);
    }

    @GetMapping("/artists/{id}")
    ResponseEntity<ArtistDTO> one(@PathVariable Long id) {
        Artist artist = artistRepository.findById(id).orElseThrow(() -> new NullPointerException("one liefert nix zurück"+id));
        ArtistDTO artistDto = new ArtistDTO();
        artistDto.toDTOFromObject(artist);
        return ResponseEntity.ok(artistDto);
    }

    @PostMapping("/artists")
    ResponseEntity<ArtistDTO> newArtist(@RequestBody ArtistDTO artistDto) {
        ContractTemplate contractTemplate = contractTemplateRepository.findById(artistDto.getContractTemplateId()).orElseThrow(() -> new NullPointerException(("artist not found")));

        if (contractTemplate == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Artist artist = new Artist();
        artist.setContractTemplate(contractTemplate);
        artist.setId(artistDto.getId());
        artist.setAddress(artistDto.getAddress());
        artist.setEmail(artistDto.getEmail());
        artist.setFirstname(artistDto.getFirstname());
        artist.setLastname(artistDto.getLastname());
        artist.setManagement(artistDto.getManagement());
        artist.setCountry(artistDto.getCountry());
        artist.setZipCode(artistDto.getZipCode());
        artist.setName(artistDto.getName());
        artist.setPhone(artistDto.getPhone());
        artist.setManagementEmail(artistDto.getManagementEmail());
        artist.setManagementPhone(artistDto.getManagementPhone());

        Artist createdArtist = artistRepository.save(artist);
        ArtistDTO createdArtistDTO = new ArtistDTO();
        createdArtistDTO.toDTOFromObject(createdArtist);

        return new ResponseEntity<>(createdArtistDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/artists/{id}")
    ResponseEntity<ArtistDTO> deleteArtist(@PathVariable Long id) {
        Artist artist = artistRepository.findById(id).orElseThrow(() -> new NullPointerException("artist not found"));
        artistRepository.delete(artist);

        ArtistDTO artistDto = new ArtistDTO();
        artistDto.toDTOFromObject(artist);

        return ResponseEntity.ok(artistDto);
    }

    @PutMapping("/artists/{id}")
    ResponseEntity<ArtistDTO> updateArtist(@PathVariable Long id, @RequestBody ArtistDTO artistDto) {
        Artist artistToEdit = artistRepository.findById(id).orElseThrow(() -> new NullPointerException("artistrequest not found"));

        artistToEdit.setManagement(artistDto.getManagement());
        artistToEdit.setPhone(artistDto.getPhone());
        artistToEdit.setManagementEmail(artistDto.getManagementEmail());
        artistToEdit.setManagementPhone(artistDto.getManagementPhone());
        artistToEdit.setName(artistDto.getName());
        artistToEdit.setFirstname(artistDto.getFirstname());
        artistToEdit.setLastname(artistDto.getLastname());
        artistToEdit.setCountry(artistDto.getCountry());
        artistToEdit.setEmail(artistDto.getEmail());
        artistToEdit.setAddress(artistDto.getAddress());
        artistToEdit.setZipCode(artistDto.getZipCode());

        ContractTemplate contractTemplate = contractTemplateRepository.findById(artistDto.getContractTemplateId()).orElseThrow(() -> new NullPointerException("ContractTemplate not found"));
        artistToEdit.setContractTemplate(contractTemplate);

        //Artist artist = artistRe
        //artistRequestToEdit.setArtist();
        artistRepository.save(artistToEdit);

        return ResponseEntity.ok(artistDto);
    }
}
