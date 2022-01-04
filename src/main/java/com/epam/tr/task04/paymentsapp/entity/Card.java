package com.epam.tr.task04.paymentsapp.entity;

import java.io.Serializable;
import java.util.Date;

public class Card implements Serializable {
    private String cardNumber;
    private String cardType;
    private Date issueDate;
    private Date validThruDate;
    private boolean status;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getValidThruDate() {
        return validThruDate;
    }

    public void setValidThruDate(Date validThruDate) {
        this.validThruDate = validThruDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;

        Card card = (Card) o;

        if (isStatus() != card.isStatus()) return false;
        if (getCardNumber() != null ? !getCardNumber().equals(card.getCardNumber()) : card.getCardNumber() != null)
            return false;
        if (getCardType() != null ? !getCardType().equals(card.getCardType()) : card.getCardType() != null)
            return false;
        if (getIssueDate() != null ? !getIssueDate().equals(card.getIssueDate()) : card.getIssueDate() != null)
            return false;
        return getValidThruDate() != null ? getValidThruDate().equals(card.getValidThruDate()) : card.getValidThruDate() == null;
    }

    @Override
    public int hashCode() {
        int result = getCardNumber() != null ? getCardNumber().hashCode() : 0;
        result = 31 * result + (getCardType() != null ? getCardType().hashCode() : 0);
        result = 31 * result + (getIssueDate() != null ? getIssueDate().hashCode() : 0);
        result = 31 * result + (getValidThruDate() != null ? getValidThruDate().hashCode() : 0);
        result = 31 * result + (isStatus() ? 1 : 0);
        return result;
    }
}
