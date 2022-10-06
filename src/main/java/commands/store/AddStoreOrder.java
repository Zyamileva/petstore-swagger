package commands.store;

import commands.*;
import commands.general.ShowMainCommand;
import entity.order.Order;
import entity.order.OrderStatus;
import service.OrderService;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Arrays;

public class AddStoreOrder extends Command {
    private final OrderService orderService = new OrderService();

    public AddStoreOrder() {
        super(CommandEnum.ADD_STORE_ORDER);
    }

    @Override
    public CommandResponse execute() {
        long idOrder = CommandLineReader.readLong("Enter Order`s id:");
        long idPet = CommandLineReader.readLong("Enter Pet`s id:");
        int quantity = CommandLineReader.readInt("Enter quantity:");
        String shipDate = LocalDateTime.now().toString();

        String status = "";
        OrderStatus statusEnum;
        while (true) {
            status = CommandLineReader.readLine("Enter status: " + Arrays.toString(OrderStatus.values()));
            String finalStatus = status;
            if (Arrays.stream(OrderStatus.values()).anyMatch(element -> element.getName().equals(finalStatus))) {
                statusEnum = Arrays.stream(OrderStatus.values()).filter(element -> element.getName().equals(finalStatus))
                        .findFirst().get();
                break;
            } else {
                System.out.println("Not found this status: " + status + ". Enter yet.");
            }
        }
        Order newOrder = new Order(idOrder, idPet, quantity, shipDate, statusEnum, true);
        String addOrder = "https://petstore.swagger.io/v2/store/order";
        Order order = orderService.addOrder(URI.create(addOrder), newOrder);
        return new CommandResponse(true, order.toString());
    }

    @Override
    public NextCommands nextCommands() {
        return new NextCommands(new ShowMainCommand());
    }
}
