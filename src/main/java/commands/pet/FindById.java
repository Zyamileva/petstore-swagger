package commands.pet;

import commands.*;
import commands.general.ShowMainCommand;
import entity.pet.Pet;
import exceptions.ResponseException;
import service.PetService;

import java.net.URI;

public class FindById extends Command {
    private final PetService petService = new PetService();
    public FindById() {
        super(CommandEnum.FIND_BY_ID);
    }
    @Override
    public CommandResponse execute() {
       long id = CommandLineReader.readLong("Please enter ID of Pet");
        String findById = String.format("https://petstore.swagger.io/v2/pet/%d", id);
        try {
            Pet pet = petService.findById(URI.create(findById));
            return new CommandResponse(true, pet.toString());
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