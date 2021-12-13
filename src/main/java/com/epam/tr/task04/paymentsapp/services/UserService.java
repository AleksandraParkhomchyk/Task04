package com.epam.tr.task04.paymentsapp.services;

public interface UserService {

    String authorisation(String login, String password);
    boolean registration(String name, String surname);
}
