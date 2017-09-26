package com.gremadex.constructionloanmanager.persistance.domain;


import javax.persistence.Column;
import java.util.Date;

public class ConstructionGuideline {

    private Address address;


    private String projectName;


    private Date startDate;

    private Date endDate;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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
