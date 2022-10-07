package commands.store;

import commands.*;
import commands.general.ShowMainCommand;
import entity.order.Order;
import entity.pet.Status;
import exceptions.ResponseException;
import service.OrderService;

import java.net.URI;
import java.util.Map;

public class Inventory extends Command
{
    private final OrderService orderService = new OrderService();

    public Inventory() {
        super(CommandEnum.INVENTORY);
    }

    @Override
    public CommandResponse execute() {
        String inv = "https://petstore.swagger.io/v2/store/inventory";
        try {
            Map<Status, Integer> inventory = orderService.inventory(URI.create(inv));
            return new CommandResponse(true, inventory.toString());
        } catch (ResponseException exception) {
            System.out.println(exception.getMessage());
            return new CommandResponse(false);
        }
    }

    @Override
    public NextCommands nextCommands() {
        return new NextCommands(new ShowMainCommand());
    }
}