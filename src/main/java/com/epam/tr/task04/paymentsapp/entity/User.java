package com.epam.tr.task04.paymentsapp.entity;

import java.io.Serializable;

public class User implements Serializable {

    private Integer id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String passport;
    private Integer role;

    public User(String name, String surname, String login, String password, String passport) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.passport = passport;
    }

    public User() {

    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public Integer getRole() { return role; }

    public void setRole(Integer role) { this.role = role; }
}
