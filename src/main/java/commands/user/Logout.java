package commands.user;

import commands.*;
import commands.general.ShowMainCommand;
import entity.response.ApiResponse;
import exceptions.ResponseException;
import service.UserService;

import java.net.URI;

public class Logout extends Command {
    private final UserService userService = new UserService();

    public Logout() {
        super(CommandEnum.LOGOUT_USER);
    }

    @Override
    public CommandResponse execute() {
        String logout = "https://petstore.swagger.io/v2/user/logout";
        try {
            ApiResponse apiResponse = userService.logout(URI.create(logout));
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