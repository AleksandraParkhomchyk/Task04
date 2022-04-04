package com.epam.tr.task04.paymentsapp.controller;

import com.epam.tr.task04.paymentsapp.controller.command.*;
import com.epam.tr.task04.paymentsapp.controller.command.gotopage.GoToAdminPageCommand;
import com.epam.tr.task04.paymentsapp.controller.command.gotopage.GoToHomePageCommand;
import com.epam.tr.task04.paymentsapp.controller.command.gotopage.GoToRegistrationPageCommand;
import com.epam.tr.task04.paymentsapp.controller.command.gotopage.GoToUserPageCommand;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {
    private final Map<String, Command> commands = new HashMap<>();

    public CommandProvider() {


        commands.put("GO_TO_HOME_PAGE", new GoToHomePageCommand());
        commands.put("GO_TO_REGISTRATION_PAGE", new GoToRegistrationPageCommand());
        commands.put("LOGINATION", new LoginationCommand());
        commands.put("REGISTRATION", new RegistrationCommand());
        commands.put("GO_TO_USER_PAGE", new GoToUserPageCommand());
        commands.put("GO_TO_ADMIN_PAGE", new GoToAdminPageCommand());


        commands.put("CHANGE_LANGUAGE", new ChangeLanguage());
        commands.put("CREATE_ACCOUNT", new CreateAccountCommand());
        commands.put("PAYMENT", new PaymentCommand());

        commands.put("GET_USERS_TRANSACTIONS", new GetUsersTransactionsCommand());
        commands.put("CASHOUT_REQUEST", new CashoutRequestCommand());
        commands.put("GET_ALL_CASHOUT_REQUESTS", new GetAllCashoutRequestsCommand());
        commands.put("APPROVE_REQUEST", new ApproveRequestCommand());
        commands.put("DECLINE_REQUEST", new DeclineRequestCommand());
        commands.put("GET_USERS_REQUESTS", new GetUsersRequestsCommand());
        commands.put("CANCEL_REQUEST", new CancelRequestCommand());
        commands.put("BLOCK_ACCOUNT", new BlockAccountCommand());
        commands.put("UNBLOCK_ACCOUNT", new UnblockAccountCommand());
        commands.put("LOG_OUT_COMMAND", new LogOutCommand());
    }

    public Command getCommand(String commandName) {
        return commands.get(commandName);
    }
}
