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

public class OrderCommand extends Command {

    public OrderCommand() {
        super(CommandEnum.ORDER_TABLE_QUERIES);
    }

    @Override
    public CommandResponse execute() {
        return new CommandResponse(true);
    }

    @Override
    public NextCommands nextCommands() {
        return new NextCommands(new AddStoreOrder(), new FindOrderById(), new DeleteOrderById(), new Inventory(),
                new ShowMainCommand(), new ExitCommand());
    }
}