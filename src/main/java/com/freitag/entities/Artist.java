package com.freitag.entities;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "artist")
public class Artist {

    private @Id @GeneratedValue Long id;
    private String name;
    private String management;

    private String managementEmail;

    private String managementPhone;
    private String email;

    private String country;

    private String address;

    private String zipCode;

    private String place;

    private String firstname;

    private String lastname;

    private String phone;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ArtistRequest> artistRequests;

    @ManyToOne(optional = false)
    @JoinColumn(name = "contractTemplate_id")
    private ContractTemplate contractTemplate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "invoiceTemplate_id")
    private InvoiceTemplate invoiceTemplate;

    public Artist() {}

    public Artist(String name, String management, String managementEmail, String managementPhone, String email, String country, String address, String zipCode, String place, String firstname, String lastname, String phone, ContractTemplate contractTemplate, InvoiceTemplate invoiceTemplate) {
        this.name = name;
        this.management = management;
        this.managementEmail = managementEmail;
        this.managementPhone = managementPhone;
        this.email = email;
        this.country = country;
        this.address = address;
        this.zipCode = zipCode;
        this.place = place;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.contractTemplate = contractTemplate;
        this.invoiceTemplate = invoiceTemplate;
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

    public String getManagementEmail() {
        return managementEmail;
    }

    public void setManagementEmail(String managementEmail) {
        this.managementEmail = managementEmail;
    }

    public String getManagementPhone() {
        return managementPhone;
    }

    public void setManagementPhone(String managementPhone) {
        this.managementPhone = managementPhone;
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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Set<ArtistRequest> getArtistRequests() {
        return this.artistRequests;
    }

    public void setArtistRequests(Set<ArtistRequest> artistRequests) {
        this.artistRequests = artistRequests;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ContractTemplate getContractTemplate() {
        return contractTemplate;
    }

    public void setContractTemplate(ContractTemplate contractTemplate) {
        this.contractTemplate = contractTemplate;
    }

    public InvoiceTemplate getInvoiceTemplate() {
        return invoiceTemplate;
    }

    public void setInvoiceTemplate(InvoiceTemplate invoiceTemplate) {
        this.invoiceTemplate = invoiceTemplate;
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
        return Objects.equals(id, artist.id) && Objects.equals(name, artist.name) && Objects.equals(management, artist.management) && Objects.equals(email, artist.email) && Objects.equals(country, artist.country) && Objects.equals(address, artist.address) && Objects.equals(zipCode, artist.zipCode) && Objects.equals(firstname, artist.firstname) && Objects.equals(lastname, artist.lastname) && Objects.equals(artistRequests, artist.artistRequests);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, management, email, country, address, zipCode, firstname, lastname, artistRequests);
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
