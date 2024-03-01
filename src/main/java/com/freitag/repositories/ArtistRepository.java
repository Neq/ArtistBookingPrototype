package com.freitag.repositories;


import com.freitag.entities.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

interface ArtistRepository extends JpaRepository<Artist, Long> {

}