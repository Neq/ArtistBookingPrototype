package com.freitag.dtos;

import com.freitag.entities.Artist;
import com.freitag.entities.ContractTemplate;

import java.util.List;

public class ArtistDTO {
    private Long id;
    private List<String> artistRequestIds;

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

    private Long contractTemplateId;

    private Long invoiceTemplateId;


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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setContractTemplateId(Long contractTemplateId) {
        this.contractTemplateId = contractTemplateId;
    }

    public Long getContractTemplateId() {
        return this.contractTemplateId;
    }

    public Long getInvoiceTemplateId() {
        return invoiceTemplateId;
    }

    public void setInvoiceTemplateId(Long invoiceTemplateId) {
        this.invoiceTemplateId = invoiceTemplateId;
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
        setPhone(artist.getPhone());
        setManagementEmail(artist.getManagementEmail());
        setManagementPhone(artist.getManagementPhone());
        setContractTemplateId(artist.getContractTemplate().getId());
        setInvoiceTemplateId(artist.getInvoiceTemplate().getId());
        setPlace(artist.getPlace());
    }

}
