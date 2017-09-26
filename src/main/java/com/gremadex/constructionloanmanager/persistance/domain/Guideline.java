package com.gremadex.constructionloanmanager.persistance.domain;

//import com.sun.javafx.beans.IDProperty;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "GUIDELINE")
public class Guideline {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name ="guideline_generator", sequenceName = "guideline_sequence", initialValue = 23)
    @GeneratedValue(generator = "guideline_generator")


    private Long id;

    @Column(name = "addressid", nullable = false)
    private Long addressId;

    @Column(name = "projectname", nullable = false)
    private String projectName;

    @Column(name = "startdate", nullable = false)
    private Date startDate;

    @Column(name = "enddate", nullable = false)
    private Date endDate;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
