import commands.CommandInvoker;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

public class Main {
    private static final String findByStatus = "https://petstore.swagger.io/v2/pet/";

    public static void main(String[] args) throws IOException, InterruptedException, ParseException {
//        Category category = new Category();
//
//        Tag tag = new Tag();
//        Pet pet = new Pet();
        //  String findByStatus = "https://petstore.swagger.io/v2/pet/findByStatus?status=sold";
        //    String findByStat = "https://petstore.swagger.io/v2/pet/9223372036854253000/uploadImage";

        CommandInvoker.invoke();
//        PetService h = new PetService();
//
//       // List<Pet> sold = h.findByStatus(URI.create(findByStatus), "sold");
//       h.uploadImage(URI.create(findByStat), "C:\\Users\\User", "two.jpg");
////
//

    }
}
