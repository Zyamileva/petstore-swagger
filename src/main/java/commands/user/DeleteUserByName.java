package commands.user;

import commands.*;
import commands.general.ShowMainCommand;
import entity.response.ApiResponse;
import exceptions.ResponseException;
import service.OrderService;
import service.UserService;

import java.net.URI;

public class DeleteUserByName extends Command {
    private final UserService userService = new UserService();

    public DeleteUserByName() {
        super(CommandEnum.DELETE_USER);
    }

    @Override
    public CommandResponse execute() {
        String userName = CommandLineReader.readLine("Please enter name");
        String deleteByName = String.format("https://petstore.swagger.io/v2/user/%s", userName);
        try {
            ApiResponse apiResponse = userService.deleteByName(URI.create(deleteByName));
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