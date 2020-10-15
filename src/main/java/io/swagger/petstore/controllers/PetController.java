package io.swagger.petstore.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.swagger.petstore.model.pet.Category;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static io.swagger.petstore.core.EndPoints.PET;
import static io.swagger.petstore.core.TestData.General.Path.JSON_PATH_POST_NEW_PET;
import static io.swagger.petstore.core.TestData.General.Path.JSON_PATH_POST_NEW_PET_WITH_EMPTY_BODY;

public class PetController {
    private ObjectMapper mapper = new ObjectMapper();

    private RequestSpecification getRequestSpecification() {
        return given().contentType(ContentType.JSON).header("api_key", "special-key");
    }

    public <T> T postNewPet(T body, int statusCode) {
        return (T) getRequestSpecification()
                .body(body)
                .when()
                .post(PET)
                .then()
                .log().all()
                .statusCode(statusCode)
                .extract().body().as(Category.class);
    }

    public <T> T postWithoutBody(int statusCode) {
        return (T) getRequestSpecification()
                .when()
                .post(PET)
                .then()
                .log().all()
                .statusCode(statusCode)
                .extract().body().as(Category.class);
    }

    public <T> T postWithWrongBody(JsonNode object, int statusCode) {
        return (T) getRequestSpecification()
                .when()
                .body(object)
                .post(PET)
                .then()
                .log().all()
                .statusCode(statusCode)
                .extract().body().as(Category.class);
    }


    public <T> T updatePet(Category body, int statusCode) {
        return (T) getRequestSpecification()
                .body(body)
                .when()
                .put(PET)
                .then()
                .log().all()
                .statusCode(statusCode)
                .extract().body().as(Category.class);
    }

    public <T> T readPet(Category body, int statusCode, long id) {
        return (T) getRequestSpecification()
                .body(body)
                .when()
                .get(PET + "/" + id)
                .then()
                .log().all()
                .statusCode(statusCode)
                .extract().body().as(Category.class);
    }

    public <T> T deletePet(Category body, int statusCode, long id) {
        return (T) getRequestSpecification()
                .body(body)
                .when()
                .delete(PET + "/" + id)
                .then()
                .log().all()
                .statusCode(statusCode);
    }

    public Category createPet() throws IOException {
        Category createPet = mapper.readValue(new File(JSON_PATH_POST_NEW_PET), Category.class);
        createPet.setName(UUID.randomUUID().toString());
        return createPet;
    }

    public JsonNode createPetAsJSONode() throws IOException {
        return mapper.readTree(new File(JSON_PATH_POST_NEW_PET_WITH_EMPTY_BODY));
    }
}
