package com.epam.tr.task04.paymentsapp.entity;

import java.io.Serializable;
import java.sql.Date;

public class User implements Serializable {
    private int ID;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String passport;
    private Date birthdate;

    public User(int ID, String name, String surname, String login, String password, String passport) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.passport = passport;
        //this.birthdate = birthdate;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

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

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getName() != null ? !getName().equals(user.getName()) : user.getName() != null) return false;
        if (getSurname() != null ? !getSurname().equals(user.getSurname()) : user.getSurname() != null) return false;
        if (getLogin() != null ? !getLogin().equals(user.getLogin()) : user.getLogin() != null) return false;
        if (getPassword() != null ? !getPassword().equals(user.getPassword()) : user.getPassword() != null)
            return false;
        if (getPassport() != null ? !getPassport().equals(user.getPassport()) : user.getPassport() != null)
            return false;
        return getBirthdate() != null ? getBirthdate().equals(user.getBirthdate()) : user.getBirthdate() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getSurname() != null ? getSurname().hashCode() : 0);
        result = 31 * result + (getLogin() != null ? getLogin().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getPassport() != null ? getPassport().hashCode() : 0);
        result = 31 * result + (getBirthdate() != null ? getBirthdate().hashCode() : 0);
        return result;
    }
}