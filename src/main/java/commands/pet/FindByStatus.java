package commands.pet;

import commands.*;
import commands.general.ShowMainCommand;
import entity.pet.Status;
import service.PetService;

import java.net.URI;
import java.util.Arrays;

import static utils.StringUtils.NEW_LINE;
import static utils.StringUtils.SEPARATOR_SHORT;

public class FindByStatus extends Command {
    private final PetService petService = new PetService();

    public FindByStatus() {
        super(CommandEnum.FIND_BY_STATUS);
    }

    @Override
    public CommandResponse execute() {
        String findByStatus = "https://petstore.swagger.io/v2/pet/findByStatus";
        String status;
        Status statusEnum;
        while (true) {
            status = CommandLineReader.readLine("Enter status: " + Arrays.toString(Status.values()));
            String finalStatus = status;
            if (Arrays.stream(Status.values()).anyMatch(element -> element.getName().equals(finalStatus))) {
                statusEnum = Arrays.stream(Status.values()).filter(element -> element.getName().equals(finalStatus))
                        .findFirst().get();
                break;
            } else {
                System.out.println("Not found this status: " + status + ". Enter yet.");
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        petService.findByStatus(URI.create(findByStatus), statusEnum)
                .forEach(pet -> stringBuilder
                        .append(pet).append(NEW_LINE)
                        .append(SEPARATOR_SHORT).append(NEW_LINE)
                );
        return new CommandResponse(true, stringBuilder.toString());
    }

    @Override
    public NextCommands nextCommands() {
        return new NextCommands(new ShowMainCommand());
    }
}