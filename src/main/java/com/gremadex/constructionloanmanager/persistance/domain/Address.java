package com.gremadex.constructionloanmanager.persistance.domain;

import javax.persistence.*;

/**
 * Created by Shashank on 19/9/2017.
 */
@Entity
@Table(name = "ADDRESS")
public class Address {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="address_generator", sequenceName="address_sequence", initialValue = 23)
    @GeneratedValue(generator = "address_generator")
    private Long id;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="street",nullable = false)
    private String street;

    @Column(name="zip_code", nullable = false)
    private String zipCode;

    @Column(name="city",nullable = false)
    private String city;

    @Column(name="country_code", nullable = false)
    private String countryCode;

    @Column(name="latitude",nullable = false)
    private String latitude;

    @Column(name="longitude", nullable = false)
    private String longitude;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
