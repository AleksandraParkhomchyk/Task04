package com.epam.tr.task04.paymentsapp.services;

import java.text.ParseException;

public interface UserService {

    String authorisation(String login, String password);
    boolean registration(String name, String surname, String login, String password, String  passport);
}
