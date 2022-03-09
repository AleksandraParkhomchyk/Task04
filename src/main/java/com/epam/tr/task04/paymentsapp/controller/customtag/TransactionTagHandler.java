package com.epam.tr.task04.paymentsapp.controller.customtag;

import com.epam.tr.task04.paymentsapp.entity.Transaction;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.ArrayList;

public class TransactionTagHandler extends TagSupport {
    private ArrayList<Transaction> transactionList;

    @Override
    public int doStartTag() throws JspException {

        try {
            JspWriter out = pageContext.getOut();
            out.write("<table><tr><th>ID</th><th>Date</th><th>Amount</th><th>OutAccount</th></tr>");
            for (int i = 0; i < transactionList.size(); i++) {
                out.write("<tr>");
                out.write("<th>" + transactionList.get(i).getId() + "</th>");
                out.write("<th>" + transactionList.get(i).getDate() + "</th>");
                out.write("<th>" + transactionList.get(i).getAmount() + "</th>");
                out.write("<th>" + transactionList.get(i).getOutAccount() + "</th>");
                out.write("<th>" + transactionList.get(i).getStartBalance() + "</th>");
                out.write("<th>" + transactionList.get(i).getEndBalance() + "</th>");
                out.write("<th>" + transactionList.get(i).getInAccount() + "</th>");
                out.write("</tr>");
            }

        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }

    public ArrayList<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(ArrayList<Transaction> transactionList) {
        this.transactionList = transactionList;
    }
}
