package com.gremadex.constructionloanmanager.persistance.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "INSPECTION")
public class Inspection {


    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name ="inspection_generator", sequenceName = "inspection_sequence", initialValue = 23)
    @GeneratedValue(generator = "inspection_generator")

    private Long id;

    @Column(name = "constructionid", nullable = false)
    private Integer constructionId;

    @Column(name = "inspectionstatus", nullable = false)
    private String inspectionStatus;

    @Column(name = "remark", nullable = false)
    private String remark;

    @Column(name = "startdate", nullable = false)
    private Date startDate;

    @Column(name = "enddate", nullable = false)
    private Date endDate;

    @Column(name = "individualid", nullable = false)
    private Integer individualId;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getConstructionId() {
        return constructionId;
    }

    public void setConstructionId(Integer constructionId) {
        this.constructionId = constructionId;
    }

    public String getInspectionStatus() {
        return inspectionStatus;
    }

    public void setInspectionStatus(String inspectionStatus) {
        this.inspectionStatus = inspectionStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Integer getIndividualId() {
        return individualId;
    }

    public void setIndividualId(Integer individualId) {
        this.individualId = individualId;
    }
}
