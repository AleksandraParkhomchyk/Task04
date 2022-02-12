package com.epam.tr.task04.paymentsapp.entity;

import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable {
    private Integer id;
    private Date date;
    private Double amount;
    private String outAccount;
    private Double startBalance;
    private Double endBalance;
    private String inAccount;


    public Transaction() {
    }

    public Transaction(Integer id, Date date, Double amount, String outAccount, Double startBalance, Double endBalance, String inAccount) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.outAccount = outAccount;
        this.startBalance = startBalance;
        this.endBalance = endBalance;
        this.inAccount = inAccount;
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

    public String getOutAccount() {
        return outAccount;
    }

    public void setOutAccount(String outAccount) {
        this.outAccount = outAccount;
    }

    public Double getStartBalance() {
        return startBalance;
    }

    public void setStartBalance(Double startBalance) {
        this.startBalance = startBalance;
    }

    public Double getEndBalance() {
        return endBalance;
    }

    public void setEndBalance(Double endBalance) {
        this.endBalance = endBalance;
    }

    public String getInAccount() {
        return inAccount;
    }

    public void setInAccount(String inAccount) {
        this.inAccount = inAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;

        Transaction that = (Transaction) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getDate() != null ? !getDate().equals(that.getDate()) : that.getDate() != null) return false;
        if (getAmount() != null ? !getAmount().equals(that.getAmount()) : that.getAmount() != null) return false;
        if (getOutAccount() != null ? !getOutAccount().equals(that.getOutAccount()) : that.getOutAccount() != null)
            return false;
        if (getStartBalance() != null ? !getStartBalance().equals(that.getStartBalance()) : that.getStartBalance() != null)
            return false;
        if (getEndBalance() != null ? !getEndBalance().equals(that.getEndBalance()) : that.getEndBalance() != null)
            return false;
        return getInAccount() != null ? getInAccount().equals(that.getInAccount()) : that.getInAccount() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
        result = 31 * result + (getAmount() != null ? getAmount().hashCode() : 0);
        result = 31 * result + (getOutAccount() != null ? getOutAccount().hashCode() : 0);
        result = 31 * result + (getStartBalance() != null ? getStartBalance().hashCode() : 0);
        result = 31 * result + (getEndBalance() != null ? getEndBalance().hashCode() : 0);
        result = 31 * result + (getInAccount() != null ? getInAccount().hashCode() : 0);
        return result;
    }
}
