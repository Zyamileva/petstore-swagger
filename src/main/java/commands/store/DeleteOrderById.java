package commands.store;

import commands.*;
import commands.general.ShowMainCommand;
import exceptions.ResponseException;
import service.OrderService;

import java.net.URI;

public class DeleteOrderById extends Command {
    private final OrderService orderService = new OrderService();

    public DeleteOrderById() {
        super(CommandEnum.DELETE_ORDER_BY_ID);
    }

    @Override
    public CommandResponse execute() {
        long id = CommandLineReader.readLong("Please enter ID of Order");
        String deleteById = String.format("https://petstore.swagger.io/v2/store/order/%d", id);
        try {
            String str = orderService.deleteById(URI.create(deleteById));
            return new CommandResponse(true, str);
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