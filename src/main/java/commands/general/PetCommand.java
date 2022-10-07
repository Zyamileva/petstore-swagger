package commands.general;

import commands.Command;
import commands.CommandEnum;
import commands.CommandResponse;
import commands.NextCommands;
import commands.pet.*;
import commands.store.AddStoreOrder;
import commands.store.DeleteOrderById;
import commands.store.FindOrderById;
import commands.store.Inventory;
import commands.user.CreateWithArray;
import commands.user.CreateWithList;

public class PetCommand extends Command {

    public PetCommand() {
        super(CommandEnum.PET_TABLE_QUERIES);
    }

    @Override
    public CommandResponse execute() {
        return new CommandResponse(true);
    }

    @Override
    public NextCommands nextCommands() {
        return new NextCommands(new AddPet(), new FindById(), new FindByStatus(), new UploadImage(), new UpdatePet(),
                new DeleteById(), new ShowMainCommand(), new ExitCommand());
    }
}