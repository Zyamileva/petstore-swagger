package commands.user;

import commands.*;
import commands.general.ShowMainCommand;
import entity.response.ApiResponse;
import entity.user.User;
import exceptions.ResponseException;
import service.UserService;

import java.net.URI;

public class UpdateUser extends Command {
    private final UserService userService = new UserService();

    public UpdateUser() {
        super(CommandEnum.UPDATE_USER_BY_USER_NAME);
    }

    public CommandResponse execute() {
        String userName = CommandLineReader.readLine("Please enter name that need to be updated");
        String url = String.format("https://petstore.swagger.io/v2/user/%s", userName);
        try {
            User user = userService.findUserByUserName(URI.create(url));
            long idUser = CommandLineReader.readLong("Enter User`s id:");
            String username = CommandLineReader.readLine("Enter username:");
            String firstName = CommandLineReader.readLine("Enter firstName:");
            String lastName = CommandLineReader.readLine("Enter lastName:");
            String email = CommandLineReader.readLine("Enter email:");
            String password = CommandLineReader.readLine("Enter password:");
            String phone = CommandLineReader.readLine("Enter phone:");
            int userStatus = CommandLineReader.readInt("Enter user status:");
            User updateUser = new User(idUser, username, firstName, lastName, email, password, phone, userStatus);
            ApiResponse apiResponse = userService.updateUserByUserName(URI.create(url), updateUser);
            return new CommandResponse(true, apiResponse.toString());
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