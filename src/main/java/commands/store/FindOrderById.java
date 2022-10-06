package commands.store;

import commands.*;
import commands.general.ShowMainCommand;
import entity.order.Order;
import entity.order.OrderStatus;
import entity.pet.Pet;
import exceptions.ResponseException;
import org.apache.hc.client5.http.ClientProtocolException;
import service.OrderService;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Arrays;

public class FindOrderById extends Command {
    private final OrderService orderService = new OrderService();

    public FindOrderById() {
        super(CommandEnum.FIND_ORDER_BY_ID);
    }

    @Override
    public CommandResponse execute() {
        long idOrder = CommandLineReader.readLong("Please enter ID of Order");
        String findOrderById = String.format("https://petstore.swagger.io/v2/store/order/%d", idOrder);
        try {
            Order order = orderService.findOrderById(URI.create(findOrderById));
            return new CommandResponse(true, order.toString());
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