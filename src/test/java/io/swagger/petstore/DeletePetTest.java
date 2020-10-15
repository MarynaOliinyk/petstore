package io.swagger.petstore;

import io.swagger.petstore.controllers.AbstractController;
import io.swagger.petstore.controllers.PetController;
import io.swagger.petstore.model.pet.Category;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Random;

import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;

public class DeletePetTest extends AbstractController {

    private PetController petController = new PetController();
    private Random random = new Random();

    @Test(description = "Delete in the store with invalid id")
    public void deletePetInTheStoreWithInvalidIdTest() throws IOException {
        Category pet = petController.createPet();
        Category createdPet = petController.postNewPet(pet, SC_OK);
        petController.deletePet(createdPet, SC_NOT_FOUND, random.nextLong());
    }

    @Test(description = "Delete in the store with valid data")
    public void deletePetTest() throws IOException {
        Category pet = petController.createPet();
        Category createdPet = petController.postNewPet(pet, SC_OK);
        petController.deletePet(createdPet, SC_OK, createdPet.getId());
    }
}
