package com.epam.tr.task04.paymentsapp.entity;

import java.io.Serializable;
import java.util.Date;

public class CashoutRequest implements Serializable {
    private Integer id;
    private Date date;
    private Double amount;
    private String status;


    public CashoutRequest() {
    }

    public CashoutRequest(Integer id, Date date, Double amount, String status) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CashoutRequest)) return false;

        CashoutRequest that = (CashoutRequest) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getDate() != null ? !getDate().equals(that.getDate()) : that.getDate() != null) return false;
        if (getAmount() != null ? !getAmount().equals(that.getAmount()) : that.getAmount() != null) return false;
        return getStatus() != null ? getStatus().equals(that.getStatus()) : that.getStatus() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
        result = 31 * result + (getAmount() != null ? getAmount().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        return result;
    }
}