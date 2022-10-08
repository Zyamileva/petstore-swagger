package commands.general;

import commands.Command;
import commands.CommandEnum;
import commands.CommandResponse;
import commands.NextCommands;

public class ExitCommand extends Command {

    public ExitCommand() {
        super(CommandEnum.EXIT);
    }

    @Override
    public CommandResponse execute() {
        System.exit(0);
        return null;
    }

    @Override
    public NextCommands nextCommands() {
        return null;
    }
}