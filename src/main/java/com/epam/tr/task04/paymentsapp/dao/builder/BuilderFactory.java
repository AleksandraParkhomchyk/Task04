package com.epam.tr.task04.paymentsapp.dao.builder;

import com.epam.tr.task04.paymentsapp.dao.builder.impl.UserBuilder;

public class BuilderFactory {

    private static final UserBuilder USER_BUILDER = new UserBuilder();

    public static UserBuilder getUserBuilder() {
        return USER_BUILDER;
    }

}
