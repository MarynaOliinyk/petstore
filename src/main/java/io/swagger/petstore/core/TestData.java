package io.swagger.petstore.core;


import static io.swagger.petstore.utils.PropertiesCache.getProperty;

public class TestData {
    public static class General {
        public static final String BASE_URL = getProperty("base.url");

        public static class Path {
            public static final String JSON_PATH_POST_NEW_PET = getProperty("path.post.new.pet");
            public static final String JSON_PATH_POST_NEW_PET_WITH_EMPTY_BODY = getProperty("path.post.new.pet.with.empty.body");
        }
    }
}