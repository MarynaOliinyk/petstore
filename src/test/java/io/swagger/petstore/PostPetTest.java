package io.swagger.petstore;

import io.swagger.petstore.controllers.AbstractController;
import io.swagger.petstore.controllers.PetController;
import io.swagger.petstore.model.pet.Category;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.apache.http.HttpStatus.SC_METHOD_NOT_ALLOWED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;

public class PostPetTest extends AbstractController {
    private PetController petController = new PetController();

    @Test(description = "Add a new pet in the store with valid data")
    public void postNewPetInTheStoreWithValidDataTest() throws IOException {
        Category pet = petController.createPet();
        Category createdPet = petController.postNewPet(pet, SC_OK);
        assertEquals(pet.getName(), createdPet.getName());
    }

    @Test(description = "Add a new pet in the store without body")
    public void postWithoutBody() {
        petController.postWithoutBody(SC_METHOD_NOT_ALLOWED);
    }

    //BUG invalid json body can create a new pet
    @Test(description = "Add a new pet in the store with empty body", enabled = false)
    public void postWithEmptyStringBody() throws IOException {
        petController.postWithWrongBody(petController.createPetAsJSONode(), SC_METHOD_NOT_ALLOWED);
    }


}
