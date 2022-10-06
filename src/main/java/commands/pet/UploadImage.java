package commands.pet;

import commands.*;
import commands.general.ShowMainCommand;
import entity.response.ApiResponse;
import service.PetService;

import java.net.URI;

public class UploadImage extends Command {
    private final PetService petService = new PetService();

    public UploadImage() {
        super(CommandEnum.UPLOAD_IMAGE);
    }

    @Override
    public CommandResponse execute() {
        String path = CommandLineReader.readLine("Please enter path to image");
        String nameImage = CommandLineReader.readLine("Please enter name of image");
        long id = CommandLineReader.readLong("Please enter id of Pets");
        String uploadImage = String.format("https://petstore.swagger.io/v2/pet/%d/uploadImage", id);
        ApiResponse apiResponse = petService.uploadImage(URI.create(uploadImage), path, nameImage);
        return new CommandResponse(true, apiResponse.toString());
    }

    @Override
    public NextCommands nextCommands() {
        return new NextCommands(new ShowMainCommand());
    }
}