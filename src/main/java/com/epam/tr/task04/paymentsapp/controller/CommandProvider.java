package com.epam.tr.task04.paymentsapp.controller;

import com.epam.tr.task04.paymentsapp.controller.command_impl.*;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {
    private final Map<String, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put("LOGINATION", new LoginationCommand());
        commands.put("REGISTRATION", new RegistrationCommand());

        commands.put("GO_TO_REGISTRATION_PAGE", new GoToRegistrationPageCommand());
        commands.put("GO_TO_LOGINATION_PAGE", new GoToLoginationPageCommand());
        commands.put("GO_TO_HOME_PAGE", new GoToHomePageCommand());
        commands.put("CHANGE_LANGUAGE", new ChangeLanguage());

        commands.put("CREATE_ACCOUNT", new CreateAccountCommand());
        commands.put("PAYMENT_ACCOUNT", new PaymentAccountCommand());
        commands.put("GET_ALL_USERS", new GetAllUsersCommand());
        commands.put("GO_TO_USERS_PAGE", new GoToUsersPageCommand());
        commands.put("LOG_OUT_COMMAND", new LogOutCommand());
        commands.put("GET_USERS_TRANSACTIONS", new GetUsersTransactionsCommand());
        commands.put("CASHOUT_REQUEST", new CashoutRequestCommand());
        commands.put("GET_ALL_CASHOUT_REQUESTS", new GetAllCashoutRequestsCommand());
        commands.put("APPROVE_REQUEST", new ApproveRequestCommand());
        commands.put("DECLINE_REQUEST", new DeclineRequestCommand());
        commands.put("GET_USERS_REQUESTS", new GetUsersRequestsCommand());

    }

    public Command getCommand(String commandName) {
        Command command = commands.get(commandName);
        return command;
    }
}
