package com.gremadex.constructionloanmanager.persistance.domain;

import javax.persistence.*;

@Entity
@Table(name = "INDIVIDUAL")
public class Individual {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name ="individual_generator", sequenceName = "individual_sequence", initialValue = 23)
    @GeneratedValue(generator = "individual_generator")

    private Long id;


    @Column(name = "role_type", nullable = false)
    private String roleType;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email_id", nullable = false)
    private String emailId;

    @Column(name = "handphone", nullable = false)
    private String handPhone;

    @Column(name = "deskphone", nullable = false)
    private String deskPhone;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "zip_code", nullable = false)
    private String zipCode;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "country_code", nullable = false)
    private String countryCode;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getHandPhone() {
        return handPhone;
    }

    public void setHandPhone(String handPhone) {
        this.handPhone = handPhone;
    }

    public String getDeskPhone() {
        return deskPhone;
    }

    public void setDeskPhone(String deskPhone) {
        this.deskPhone = deskPhone;
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
}
