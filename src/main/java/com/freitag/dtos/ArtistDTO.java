package com.freitag.dtos;

import com.freitag.entities.Artist;

import java.util.List;

public class ArtistDTO {
    private Long id;
    private List<String> artistRequestIds;

    private String name;
    private String management;
    private String email;

    private String country;

    private String address;

    private String zipCode;

    private String firstname;

    private String lastname;

    public ArtistDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getArtistRequestIds() {
        return artistRequestIds;
    }

    public void setArtistRequestIds(List<String> artistRequestIds) {
        this.artistRequestIds = artistRequestIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManagement() {
        return management;
    }

    public void setManagement(String management) {
        this.management = management;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void toDTOFromObject(Artist artist) {
        setAddress(artist.getAddress());
        setCountry(artist.getCountry());
        setId(artist.getId());
        setCountry(artist.getCountry());
        setEmail(artist.getEmail());
        setFirstname(artist.getFirstname());
        setLastname(artist.getLastname());
        setZipCode(artist.getZipCode());
        setManagement(artist.getManagement());
        setName(artist.getName());
    }
}
