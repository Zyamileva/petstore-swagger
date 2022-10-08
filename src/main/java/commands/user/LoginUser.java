package commands.user;

import commands.*;
import commands.general.ShowMainCommand;
import entity.response.ApiResponse;
import exceptions.ResponseException;
import service.UserService;

import java.net.URI;

public class LoginUser extends Command {
    private final UserService userService = new UserService();

    public LoginUser() {
        super(CommandEnum.LOGIN_USER);
    }

    @Override
    public CommandResponse execute() {
        String login = CommandLineReader.readLine("Please enter username");
        String password = CommandLineReader.readLine("Please enter password");
        String loginUser = "https://petstore.swagger.io/v2/user/login";
        try {
            ApiResponse apiResponse = userService.loginUser(URI.create(loginUser), loginUser, password);
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