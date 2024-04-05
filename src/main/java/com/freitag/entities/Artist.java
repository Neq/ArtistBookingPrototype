package com.freitag.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "artist")
public class Artist {

    private @Id @GeneratedValue Long id;
    private String name;
    private String management;
    private String email;

    private String country;

    private String address;

    private String zipCode;

    private String firstname;

    private String lastname;

    @OneToOne(mappedBy = "artist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ArtistRequest artistRequest;

    public Artist() {}

    public Artist(String name, String management, String email, String country, String address, String zipCode, String firstname, String lastname) {
        this.name = name;
        this.management = management;
        this.email = email;
        this.country = country;
        this.address = address;
        this.zipCode = zipCode;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getArtistRequestId() {
        return artistRequest != null ? artistRequest.getId() : Long.getLong("0");
    }

    /*public ArtistRequest getArtistRequest() {
        return artistRequest;
    }

    public void setArtistRequest(ArtistRequest artistRequest) {
        this.artistRequest = artistRequest;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return Objects.equals(id, artist.id) && Objects.equals(name, artist.name) && Objects.equals(management, artist.management) && Objects.equals(email, artist.email) && Objects.equals(country, artist.country) && Objects.equals(address, artist.address) && Objects.equals(zipCode, artist.zipCode) && Objects.equals(firstname, artist.firstname) && Objects.equals(lastname, artist.lastname) && Objects.equals(artistRequest, artist.artistRequest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, management, email, country, address, zipCode, firstname, lastname, artistRequest);
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", management='" + management + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                ", address='" + address + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                //", artistRequestId=" + (artistRequest != null ? artistRequest.getId().toString() : "null") +
                '}';
    }
}
