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
import commands.user.*;

public class UserCommand extends Command {

    public UserCommand() {
        super(CommandEnum.USER_TABLE_QUERIES);
    }

    @Override
    public CommandResponse execute() {
        return new CommandResponse(true);
    }

    @Override
    public NextCommands nextCommands() {
        return new NextCommands(new AddUser(), new UpdateUser(), new FindUserByName(), new DeleteUserByName(),
                new CreateWithArray(), new CreateWithList(), new LoginUser(),
                new ShowMainCommand(), new ExitCommand());
    }
}