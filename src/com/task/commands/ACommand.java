package com.task.commands;

abstract public class ACommand implements ICommand {
    @Override
    public void execute() {
        doExecute();
        //logic registry in command manager
    }

    abstract protected void doExecute();
}
