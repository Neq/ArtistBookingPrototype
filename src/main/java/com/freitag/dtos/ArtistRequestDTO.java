package com.freitag.dtos;

import com.freitag.entities.ArtistRequest;

import java.util.Date;

public class ArtistRequestDTO {
    private Long id;
    private Long artistId;

    private Long offerStatusId;

    private String eventName;
    private Date eventStart;
    private Date eventEnd;
    private String notes;

    private String locationName;

    private String locationWebsite;

    private String invoiceAddress;

    private String invoiceCountry;

    private String invoiceZipCode;

    private String invoiceName;

    private String invoicePlace;

    private Double price;

    public Long getArtistId() {
        return artistId;
    }

    public ArtistRequestDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Date getEventStart() {
        return eventStart;
    }

    public void setEventStart(Date eventStart) {
        this.eventStart = eventStart;
    }

    public Date getEventEnd() {
        return eventEnd;
    }

    public void setEventEnd(Date eventEnd) {
        this.eventEnd = eventEnd;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationWebsite() {
        return locationWebsite;
    }

    public void setLocationWebsite(String locationWebsite) {
        this.locationWebsite = locationWebsite;
    }

    public Long getOfferStatusId() {
        return offerStatusId;
    }

    public void setOfferStatusId(Long offerStatusId) {
        this.offerStatusId = offerStatusId;
    }

    public String getInvoiceAddress() {
        return invoiceAddress;
    }

    public void setInvoiceAddress(String invoiceAddress) {
        this.invoiceAddress = invoiceAddress;
    }

    public String getInvoiceCountry() {
        return invoiceCountry;
    }

    public void setInvoiceCountry(String invoiceCountry) {
        this.invoiceCountry = invoiceCountry;
    }

    public String getInvoiceZipCode() {
        return invoiceZipCode;
    }

    public void setInvoiceZipCode(String invoiceZipCode) {
        this.invoiceZipCode = invoiceZipCode;
    }

    public String getInvoiceName() {
        return invoiceName;
    }

    public void setInvoiceName(String invoiceName) {
        this.invoiceName = invoiceName;
    }

    public String getInvoicePlace() {
        return invoicePlace;
    }

    public void setInvoicePlace(String invoicePlace) {
        this.invoicePlace = invoicePlace;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void toDTOFromObject(ArtistRequest artistRequest) {
        setId(artistRequest.getId());
        setEventName(artistRequest.getEventName());
        setEventEnd(artistRequest.getEventEnd());
        setEventStart(artistRequest.getEventStart());
        setNotes(artistRequest.getNotes());
        setArtistId(artistRequest.getArtist().getId());
        setOfferStatusId(artistRequest.getOfferStatus().getId());
        setLocationName(artistRequest.getLocationName());
        setLocationWebsite(artistRequest.getLocationWebsite());
        setPrice(artistRequest.getPrice());
        setInvoiceAddress(artistRequest.getInvoiceAddress());
        setInvoiceCountry(artistRequest.getInvoiceCountry());
        setInvoiceZipCode(artistRequest.getInvoiceZipCode());
        setInvoiceName(artistRequest.getInvoiceName());
        setInvoicePlace(artistRequest.getInvoicePlace());
    }
}
