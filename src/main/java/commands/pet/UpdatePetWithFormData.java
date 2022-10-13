package commands.pet;

import commands.*;
import commands.general.ShowMainCommand;
import entity.pet.Status;
import entity.response.ApiResponse;
import exceptions.ResponseException;
import service.PetService;

import java.net.URI;
import java.util.Arrays;

public class UpdatePetWithFormData extends Command {
    private final PetService petService = new PetService();

    public UpdatePetWithFormData() {
        super(CommandEnum.UPDATE_PET_WITH_FORM_DATA);
    }

    @Override
    public CommandResponse execute() {
        long idPet = CommandLineReader.readLong("Enter Pet`s id:");
        String namePet = CommandLineReader.readLine("Enter Pet`s name:");
        String status = "";
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
        String updatePet = String.format("https://petstore.swagger.io/v2/pet/%d", idPet);
        try {
            ApiResponse apiResponse = petService.updatePetWithFormData(URI.create(updatePet),namePet,statusEnum);
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