package commands.pet;

import commands.*;
import commands.general.ShowMainCommand;
import entity.pet.Pet;
import entity.response.ApiResponse;
import exceptions.ResponseException;
import service.PetService;

import java.net.URI;

public class DeleteById extends Command {
    private final PetService petService = new PetService();

    public DeleteById() {
        super(CommandEnum.DELETE_BY_ID);
    }

    @Override
    public CommandResponse execute() {
        long id = CommandLineReader.readLong("Please enter ID of Pet");
        String deleteById = String.format("https://petstore.swagger.io/v2/pet/%d", id);
        try {
            ApiResponse apiResponse = petService.deleteById(URI.create(deleteById));
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