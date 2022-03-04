package com.epam.tr.task04.paymentsapp.dao.builder.impl;

import com.epam.tr.task04.paymentsapp.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBuilder {

    public User build(ResultSet resultSet) throws SQLException {
        User user = new User();
        buildUser(user, resultSet);
        return user;
    }

    void buildUser(User user, ResultSet resultSet) throws SQLException {
        user.setId(resultSet.getInt(1));
        user.setLogin(resultSet.getString(2));
        user.setName(resultSet.getString(4));
        user.setSurname(resultSet.getString(5));
        user.setPassport(resultSet.getString(6));
        user.setRole(resultSet.getInt(7));
    }
}
