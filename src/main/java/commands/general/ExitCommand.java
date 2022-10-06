package commands.general;

import commands.Command;
import commands.CommandEnum;
import commands.CommandResponse;
import commands.NextCommands;

public class ExitCommand extends Command {
   // private static final Logger log = LogManager.getLogger(ExitCommand.class);

    public ExitCommand() {
        super(CommandEnum.EXIT);
    }

    @Override
    public CommandResponse execute() {
        //log.info("Shutdown twitter");
        System.exit(0);
        return null;
    }

    @Override
    public NextCommands nextCommands() {
        return null;
    }
}