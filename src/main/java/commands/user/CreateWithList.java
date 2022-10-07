package commands.user;

import commands.*;
import commands.general.ShowMainCommand;
import entity.response.ApiResponse;
import entity.user.User;
import service.UserService;

import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CreateWithList extends Command {
    private final UserService userService = new UserService();

    public CreateWithList() {
        super(CommandEnum.CREATE_WITH_LIST);
    }

    List<User> users = new LinkedList<>();

    @Override
    public CommandResponse execute() {
        boolean answer = true;
        while (answer) {
            long idUser = CommandLineReader.readLong("Enter User`s id:");
            String userName = CommandLineReader.readLine("Enter User`s name:");
            String firstName = CommandLineReader.readLine("Enter first name:");
            String lastName = CommandLineReader.readLine("Enter last name:");
            String email = CommandLineReader.readLine("Enter email:");
            String password = CommandLineReader.readLine("Enter password:");
            String phone = CommandLineReader.readLine("Enter phone");
            int status = CommandLineReader.readInt("Enter User status:");
            User newUser = new User(idUser, userName, firstName, lastName, email, password, phone, status);
            users.add(newUser);
            while (true) {
                String str = CommandLineReader.readLine
                        ("Enter \"Yes\" - if there is another tag. Else - \"No\".");
                if (str.equals("Yes")) {
                    break;
                } else if (str.equals("No")) {
                    answer = false;
                    break;
                } else {
                    System.out.println("Entered incorrectly.");
                }
            }
        }
        String createWithList = "https://petstore.swagger.io/v2/user/createWithList";
        ApiResponse apiResponse = userService.createWithList(URI.create(createWithList),users);
        return new CommandResponse(true, apiResponse.toString());
    }

    @Override
    public NextCommands nextCommands() {
        return new NextCommands(new ShowMainCommand());
    }
}
