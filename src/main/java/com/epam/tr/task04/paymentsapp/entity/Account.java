package com.epam.tr.task04.paymentsapp.entity;

import java.io.Serializable;
import java.util.Date;

public class Account implements Serializable {
    private Integer id;
    private String accountNumber;
    private double balance;
    private Date accountOpeningDate;
    private Integer status;
    private Integer ownerId;

    public Account() {
    }

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public Account(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getAccountOpeningDate() {
        return accountOpeningDate;
    }

    public void setAccountOpeningDate(Date accountOpeningDate) {
        this.accountOpeningDate = accountOpeningDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;

        Account account = (Account) o;

        if (Double.compare(account.getBalance(), getBalance()) != 0) return false;
        if (getId() != null ? !getId().equals(account.getId()) : account.getId() != null) return false;
        if (getAccountNumber() != null ? !getAccountNumber().equals(account.getAccountNumber()) : account.getAccountNumber() != null)
            return false;
        if (getAccountOpeningDate() != null ? !getAccountOpeningDate().equals(account.getAccountOpeningDate()) : account.getAccountOpeningDate() != null)
            return false;
        if (getStatus() != null ? !getStatus().equals(account.getStatus()) : account.getStatus() != null) return false;
        return getOwnerId() != null ? getOwnerId().equals(account.getOwnerId()) : account.getOwnerId() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getAccountNumber() != null ? getAccountNumber().hashCode() : 0);
        temp = Double.doubleToLongBits(getBalance());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getAccountOpeningDate() != null ? getAccountOpeningDate().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = 31 * result + (getOwnerId() != null ? getOwnerId().hashCode() : 0);
        return result;
    }
}
