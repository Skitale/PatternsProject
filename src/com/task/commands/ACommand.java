package com.task.commands;

abstract public class ACommand implements ICommand {
    @Override
    public void execute() {
        doExecute();
        CommandManager.getInstance().registryCommand(this.clone());
    }

    abstract protected void doExecute();

    @Override
    abstract protected ACommand clone();
}
