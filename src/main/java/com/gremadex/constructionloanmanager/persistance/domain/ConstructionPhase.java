package com.gremadex.constructionloanmanager.persistance.domain;


import javax.persistence.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

@Entity
@Table(name = "CONSTRUCTIONPHASE")
public class ConstructionPhase {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name ="construction_phase_generator", sequenceName = "construction_phase_sequence", initialValue = 23)
    @GeneratedValue(generator = "construction_phase_generator")

    private  Integer id;

    @Column(name = "phasename", nullable = false)
    private String phaseName;


    @Column(name = "masterreferenceid", nullable = false)
    private Integer masterReferenceId;


    @Column(name = "constructionphasenumber", nullable = false)
    private Integer constructionPhaseNumber;


    @Column(name = "status", nullable = false)
    private String status;


    @Column(name = "startdate", nullable = false)
    private Date startDate;


    @Column(name = "enddate", nullable = false)
    private Date endDate;


    @Column(name = "constructioncost", nullable = false)
    private BigDecimal constructionCost;


    @Column(name = "revisedconstructioncost", nullable = false)
    private BigDecimal revisedConstructionCost;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhaseName() {
        return phaseName;
    }

    public void setPhaseName(String phaseName) {
        this.phaseName = phaseName;
    }

    public Integer getMasterReferenceId() {
        return masterReferenceId;
    }

    public void setMasterReferenceId(Integer masterReferenceId) {
        this.masterReferenceId = masterReferenceId;
    }

    public Integer getConstructionPhaseNumber() {
        return constructionPhaseNumber;
    }

    public void setConstructionPhaseNumber(Integer constructionPhaseNumber) {
        this.constructionPhaseNumber = constructionPhaseNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public BigDecimal getConstructionCost() {
        return constructionCost;
    }

    public void setConstructionCost(BigDecimal constructionCost) {
        this.constructionCost = constructionCost;
    }

    public BigDecimal getRevisedConstructionCost() {
        return revisedConstructionCost;
    }

    public void setRevisedConstructionCost(BigDecimal revisedConstructionCost) {
        this.revisedConstructionCost = revisedConstructionCost;
    }
}
