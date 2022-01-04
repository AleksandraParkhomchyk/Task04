package com.epam.tr.task04.paymentsapp.entity;

import java.io.Serializable;
import java.util.Date;

public class Operation implements Serializable {
    private Integer id;
    private Date date;
    private Double amount;

    public Operation(Double amount) {
        this.amount = amount;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Operation)) return false;

        Operation operation = (Operation) o;

        if (getId() != null ? !getId().equals(operation.getId()) : operation.getId() != null) return false;
        if (getDate() != null ? !getDate().equals(operation.getDate()) : operation.getDate() != null) return false;
        return getAmount() != null ? getAmount().equals(operation.getAmount()) : operation.getAmount() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
        result = 31 * result + (getAmount() != null ? getAmount().hashCode() : 0);
        return result;
    }
}
