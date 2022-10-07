package commands.user;

import commands.*;
import commands.general.ShowMainCommand;
import entity.order.Order;
import entity.order.OrderStatus;
import entity.response.ApiResponse;
import entity.user.User;
import service.OrderService;
import service.UserService;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Arrays;

public class AddUser extends Command {
    private final UserService userService = new UserService();

    public AddUser() {
        super(CommandEnum.ADD_USER);
    }
    @Override
    public CommandResponse execute() {
        long idUser = CommandLineReader.readLong("Enter User`s id:");
        String username = CommandLineReader.readLine("Enter username:");
        String firstName = CommandLineReader.readLine("Enter firstName:");
        String lastName = CommandLineReader.readLine("Enter lastName:");
        String email = CommandLineReader.readLine("Enter email:");
        String password = CommandLineReader.readLine("Enter password:");
        String phone = CommandLineReader.readLine("Enter phone:");
        int userStatus = CommandLineReader.readInt("Enter user status:");

        User newUser = new User(idUser,username,firstName,lastName,email,password, phone,userStatus);
        String addUser = "https://petstore.swagger.io/v2/user";
        ApiResponse apiResponse = userService.addUser(URI.create(addUser), newUser);
        return new CommandResponse(true, apiResponse.toString());
    }

    @Override
    public NextCommands nextCommands() {
        return new NextCommands(new ShowMainCommand());
    }
}

