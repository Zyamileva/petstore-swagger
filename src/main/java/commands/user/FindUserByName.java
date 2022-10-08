package commands.user;

import commands.*;
import commands.general.ShowMainCommand;
import entity.user.User;
import exceptions.ResponseException;
import service.UserService;

import java.net.URI;

public class FindUserByName extends Command {
    private final UserService userService = new UserService();

    public FindUserByName() {
        super(CommandEnum.FIND_USER_BY_USER_NAME);
    }

    @Override
    public CommandResponse execute() {
        String userName = CommandLineReader.readLine("Please enter user`s name");
        String findUserByUserName = String.format("https://petstore.swagger.io/v2/user/%s", userName);
        try {
            User user = userService.findUserByUserName(URI.create(findUserByUserName));
            return new CommandResponse(true, user.toString());
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