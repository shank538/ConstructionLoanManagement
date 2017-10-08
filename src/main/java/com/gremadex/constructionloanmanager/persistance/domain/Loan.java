package com.gremadex.constructionloanmanager.persistance.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "LOAN")
public class Loan {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name ="loan_generator", sequenceName = "loan_sequence", initialValue = 23)
    @GeneratedValue(generator = "loan_generator")

    private Long id;

    @Column(name = "accountid", nullable = false)
    private String  accountId;

    @Column(name = "bankid", nullable = false)
    private  Integer bankId;


    @Column(name = "constructionphaseid", nullable = false)
    private Integer constructionPhaseId;

    @Column(name = "loandescription", nullable = false)
    private String loanDescription;

    @Column(name = "loantype", nullable = false)
    private String loanType;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "disbursementdate", nullable = false)
    private Date disbursementDate;

    @Column(name = "interestrate", nullable = false)
    private BigDecimal interestRate;

    @Column(name = "loanexpirydate", nullable = false)
    private Date loanExpiryDate;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public Integer getConstructionPhaseId() {
        return constructionPhaseId;
    }

    public void setConstructionPhaseId(Integer constructionPhaseId) {
        this.constructionPhaseId = constructionPhaseId;
    }

    public String getLoanDescription() {
        return loanDescription;
    }

    public void setLoanDescription(String loanDescription) {
        this.loanDescription = loanDescription;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getDisbursementDate() {
        return disbursementDate;
    }

    public void setDisbursementDate(Date disbursementDate) {
        this.disbursementDate = disbursementDate;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public Date getLoanExpiryDate() {
        return loanExpiryDate;
    }

    public void setLoanExpiryDate(Date loanExpiryDate) {
        this.loanExpiryDate = loanExpiryDate;
    }
}
