package commands.pet;

import commands.*;
import commands.general.ShowMainCommand;
import entity.pet.Category;
import entity.pet.Pet;
import entity.pet.Status;
import entity.pet.Tag;
import service.PetService;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

public class AddPet extends Command {
    private final PetService petService = new PetService();

    public AddPet() {
        super(CommandEnum.ADD_PET);
    }

    @Override
    public CommandResponse execute() {
        ArrayList<Tag> tags = new ArrayList<>();
        ArrayList<String> photoUrls = new ArrayList<>();

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

        long idCategory = CommandLineReader.readLong("Enter id of category:");
        String nameCategory = CommandLineReader.readLine("Enter nane of category:");
        Category category = new Category(idCategory, nameCategory);
        boolean answer = true;
        while (answer) {
            long tagId = CommandLineReader.readLong("Enter id tag:");
            String nameTag = CommandLineReader.readLine("Enter Tag`s name:");
            Tag tag = new Tag(tagId, nameTag);
            tags.add(tag);
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
        answer = true;
        while (answer) {
            String photoUrl = CommandLineReader.readLine("Enter photoUrls:");
            photoUrls.add(photoUrl);
            while (true) {
                String str = CommandLineReader.readLine
                        ("Enter \"Yes\" - if there is another photo Url. Else - \"No\".");
                if (str.equals("Yes")) {
                    break;
                } else if (str.equals("No")) {
                    answer = false;
                    break;
                } else {
                    System.out.println("Entered incorrectly.1");
                }
            }
        }


        Pet newPet = new Pet(idPet, category, namePet, photoUrls, tags, statusEnum);
        String addPet = "https://petstore.swagger.io/v2/pet";
        Pet pet = petService.addPet(URI.create(addPet), newPet);
        return new CommandResponse(true, pet.toString());
    }

    @Override
    public NextCommands nextCommands() {
        return new NextCommands(new ShowMainCommand());
    }
}