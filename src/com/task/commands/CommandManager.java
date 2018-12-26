package com.task.commands;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    private List<ICommand> commandList;
    private static CommandManager commandManager;

    private CommandManager(){
        commandList = new ArrayList<>();
    }

    public static CommandManager getInstance(){
        if(commandManager == null){
            commandManager = new CommandManager();
        }
        return commandManager;
    }

    public void registryCommand(ICommand command){
        if(command != null){
            commandList.add(command);
        }
    }
}
