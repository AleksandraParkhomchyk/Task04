package com.epam.tr.task04.paymentsapp.controller.impl;

import com.epam.tr.task04.paymentsapp.controller.Command;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {
    private final Map<String, Command> commands = new HashMap<String, Command>();

    public CommandProvider() {
        commands.put("Logination", new LoginationCommand());
        commands.put("Registration", new RegistrationCommand());

        commands.put("GO_TO_REGISTRATION_PAGE", new GoToRegistrationPageCommand());
        commands.put("GO_TO_LOGINATION_PAGE", new GoToLoginationPageCommand());
    }

    public final Command getCommand(String commandName) {
        Command command = commands.get(commandName);
        return command;
    }
}
