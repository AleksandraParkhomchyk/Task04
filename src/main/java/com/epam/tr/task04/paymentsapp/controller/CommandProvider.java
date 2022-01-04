package com.epam.tr.task04.paymentsapp.controller;

import com.epam.tr.task04.paymentsapp.controller.command_impl.*;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {
    private final Map<String, Command> commands = new HashMap<String, Command>();

    public CommandProvider() {
        commands.put("LOGINATION", new LoginationCommand());
        commands.put("REGISTRATION", new RegistrationCommand());

        commands.put("GO_TO_REGISTRATION_PAGE", new GoToRegistrationPageCommand());
        commands.put("GO_TO_LOGINATION_PAGE", new GoToLoginationPageCommand());
        commands.put("GO_TO_INDEX_PAGE", new GoToIndexPageCommand());
        commands.put("CHANGE_LANGUAGE", new ChangeLanguage());
    }

    public final Command getCommand(String commandName) {
        Command command = commands.get(commandName);
        return command;
    }
}
