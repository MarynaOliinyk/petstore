package io.swagger.petstore;

import io.swagger.petstore.controllers.AbstractController;
import io.swagger.petstore.controllers.PetController;
import io.swagger.petstore.model.pet.Category;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Random;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;

public class PutPetTest extends AbstractController {

    private PetController petController = new PetController();
    private Random random = new Random();

    @Test(description = "Update a pet in the store with valid data")
    public void updatePetInTheStoreTest() throws IOException {
        String updatedName = "NewUpdatedName";
        String unvailable = "unvailable";
        Category pet = petController.createPet();
        Category createdPet = petController.postNewPet(pet, SC_OK);
        createdPet.setName(updatedName);
        createdPet.setStatus(unvailable);
        Category petUpdate = petController.updatePet(createdPet, SC_OK);
        assertEquals(updatedName, petUpdate.getName());
        assertEquals(unvailable, petUpdate.getStatus());
    }

    //BUG invalid id can create a new pet
    @Test(description = "Update a pet in the store with invalid id", enabled = false)
    public void updatePetWithWrongIdInTheStoreTest() throws IOException {
        Category pet = petController.createPet();
        Category createdPet = petController.postNewPet(pet, SC_OK);
        createdPet.setId(random.nextLong());
        petController.updatePet(createdPet, SC_BAD_REQUEST);
    }
}
