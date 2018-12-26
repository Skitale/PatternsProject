package com.task.commands;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    private List<ICommand> commandList;
    private static CommandManager commandManager;
    private boolean lock;

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
        if(lock) return;
        if(command != null){
            commandList.add(command);
        }
    }

    public void undo(){
        if(commandList.size() == 1) return;
        commandList.remove(commandList.size() - 1);
        lock = true;
        for(ICommand command: commandList){
            command.execute();
        }
        lock = false;
    }
}
