package commands;

import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

public abstract class Command {
    protected final CommandEnum command;

    protected Command(CommandEnum command) {
        this.command = command;
    }

    public abstract CommandResponse execute();

    public abstract NextCommands nextCommands();

    public String toString() {
        return command.getValue();
    }
}
