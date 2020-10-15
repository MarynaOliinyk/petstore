package io.swagger.petstore;

import io.swagger.petstore.controllers.AbstractController;
import io.swagger.petstore.controllers.PetController;
import io.swagger.petstore.model.pet.Category;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Random;

import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;

public class GetPetTest extends AbstractController {

    private PetController petController = new PetController();
    private Random random = new Random();

    @Test(description = "Read a pet in the store with valid data")
    public void readPetByIdTest() throws IOException {
        Category pet = petController.createPet();
        Category createdPet = petController.postNewPet(pet, SC_OK);
        pet = petController.readPet(pet, SC_OK, createdPet.getId());
        assertEquals(createdPet, pet);
    }

    @Test(description = "Read a pet in the store with invalid id")
    public void readPetInTheStoreWithInvalidIdTest() throws IOException {
        Category pet = petController.createPet();
        petController.readPet(pet, SC_NOT_FOUND, random.nextLong());
    }
}
