package com.freitag.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "artistRequest")
public class ArtistRequest {

    private @Id
    @GeneratedValue Long id;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @ManyToOne
    @JoinColumn(name = "offerStatus_id")
    private OfferStatus offerStatus;

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


    public ArtistRequest() {}

    public ArtistRequest(Artist artist, Date eventStart, Date eventEnd, String notes) {
        this.artist = artist;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.notes = notes;
    }

    public ArtistRequest(Long id, Artist artist, OfferStatus offerStatus, String eventName, Date eventStart, Date eventEnd, String notes, String locationName, String locationWebsite, String invoiceAddress, String invoiceCountry, String invoiceZipCode, String invoiceName, String invoicePlace, Double price) {
        this.id = id;
        this.artist = artist;
        this.offerStatus = offerStatus;
        this.eventName = eventName;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.notes = notes;
        this.locationName = locationName;
        this.locationWebsite = locationWebsite;
        this.invoiceAddress = invoiceAddress;
        this.invoiceCountry = invoiceCountry;
        this.invoiceZipCode = invoiceZipCode;
        this.invoiceName = invoiceName;
        this.invoicePlace = invoicePlace;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Artist getArtist() {
        return this.artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public void setOfferStatus(OfferStatus offerStatus) {
        this.offerStatus = offerStatus;
    }

    public OfferStatus getOfferStatus() {
        return offerStatus;
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

    @Override
    public String toString() {
        return "ArtistRequest{" +
                "id=" + id +
                ", artist=" + artist +
                //", offer=" + offer +
                ", eventStart=" + eventStart +
                ", eventEnd=" + eventEnd +
                ", details='" + notes + '\'' +
                '}';
    }
}
