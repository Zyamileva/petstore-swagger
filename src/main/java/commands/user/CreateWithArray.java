package commands.user;

import commands.*;
import commands.general.ShowMainCommand;
import entity.response.ApiResponse;
import entity.user.User;
import service.UserService;

import java.net.URI;
import java.util.ArrayList;

public class CreateWithArray extends Command {
    private final UserService userService = new UserService();

    public CreateWithArray() {
        super(CommandEnum.CREATE_WITH_ARRAY);
    }

    ArrayList<User> users = new ArrayList<>();

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
        String createWithArray = "https://petstore.swagger.io/v2/user/createWithArray";
        ApiResponse apiResponse = userService.createWithArray(URI.create(createWithArray), users);
        return new CommandResponse(true, apiResponse.toString());
    }

    @Override
    public NextCommands nextCommands() {
        return new NextCommands(new ShowMainCommand());
    }
}
