package commands.general;

import commands.Command;
import commands.CommandEnum;
import commands.CommandResponse;
import commands.NextCommands;
import commands.pet.*;
import commands.store.AddStoreOrder;
import commands.store.DeleteOrderById;
import commands.store.FindOrderById;

public class ShowMainCommand extends Command {

    public ShowMainCommand() {
        super(CommandEnum.MAIN_COMMANDS);
    }

    @Override
    public CommandResponse execute() {
        return new CommandResponse(true);
    }

    @Override
    public NextCommands nextCommands() {
        return new NextCommands(new AddPet(), new FindById(), new FindByStatus(), new UploadImage(), new UpdatePet(),
                new DeleteById(), new AddStoreOrder(), new FindOrderById(), new DeleteOrderById(), new ExitCommand());
    }

}