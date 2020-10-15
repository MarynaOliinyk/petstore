package io.swagger.petstore.controllers;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.config;
import static io.swagger.petstore.core.TestData.General.BASE_URL;

public class AbstractController {
    static {
        config = config().logConfig(LogConfig.logConfig().enablePrettyPrinting(true));
        RestAssured.requestSpecification =
                new RequestSpecBuilder()
                        .setBaseUri(BASE_URL)
                        .setContentType(ContentType.JSON)
                        .log(LogDetail.ALL).build();
    }
}
