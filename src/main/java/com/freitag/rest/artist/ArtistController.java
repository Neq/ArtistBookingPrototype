package com.freitag.rest.artist;

import com.freitag.dtos.ArtistDTO;
import com.freitag.entities.Artist;
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
    ResponseEntity<Artist> one(@PathVariable Long id) {
        Artist artist = artistRepository.findById(id).orElseThrow(() -> new NullPointerException("one liefert nix zurück"+id));
        return ResponseEntity.ok(artist);
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

        Artist createdArtist = artistRepository.save(artist);
        ArtistDTO createdArtistDTO = new ArtistDTO();
        createdArtistDTO.toDTOFromObject(createdArtist);

        return new ResponseEntity<>(createdArtistDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/artists/{id}")
    ResponseEntity<Artist> deleteArtist(@PathVariable Long id) {
        Artist artist = artistRepository.findById(id).orElseThrow(() -> new NullPointerException("artist not found"));
        artistRepository.delete(artist);

        return ResponseEntity.ok(artist);
    }
}
