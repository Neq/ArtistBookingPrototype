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

    @OneToOne(mappedBy = "artist")
    private ArtistRequest artistRequest;

    public Artist() {}

    public Artist(String name, String management, String email) {
        this.name = name;
        this.management = management;
        this.email = email;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return Objects.equals(id, artist.id) && Objects.equals(name, artist.name) && Objects.equals(management, artist.management) && Objects.equals(email, artist.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, management, email);
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", management='" + management + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
