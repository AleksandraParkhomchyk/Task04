package com.epam.tr.task04.paymentsapp.service.validator;

import com.epam.tr.task04.paymentsapp.entity.User;

public interface UserValidator {

    void validate(User user) throws ValidatorException;

}
