package commands.pet;

import commands.*;
import commands.general.ShowMainCommand;
import entity.pet.Status;
import service.PetService;

import java.net.URI;
import java.util.Arrays;
import java.util.Set;

import static utils.StringUtils.NEW_LINE;
import static utils.StringUtils.SEPARATOR_SHORT;

public class FindByStatus extends Command {
    private final PetService petService = new PetService();
    public FindByStatus() {
        super(CommandEnum.FIND_BY_STATUS);
    }
    @Override
    public CommandResponse execute() {
        String status = CommandLineReader.readLine("Please enter Status:" + Arrays.toString(Status.values()));
        String findByStatus = String.format("https://petstore.swagger.io/v2/pet/findByStatus?status=%s", status);
        if (Arrays.stream(Status.values()).anyMatch(element -> element.getName().equals(status))) {
            StringBuilder stringBuilder = new StringBuilder();
            petService.findByStatus(URI.create(findByStatus))
                    .forEach(pet -> stringBuilder
                            .append(pet).append(NEW_LINE)
                            .append(SEPARATOR_SHORT).append(NEW_LINE)
                    );
            return new CommandResponse(true, stringBuilder.toString());
        } else {
            return new CommandResponse(false, Set.of("Not found this status: " + status));
        }
    }

    @Override
    public NextCommands nextCommands() {
        return new NextCommands(new ShowMainCommand());
    }
}