package com.jeta.sample.test.common;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestTestBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestTestBase.class);
    private final String baseUrl;

    public RestTestBase() {
        AutomationProperties.get();
        baseUrl = AutomationProperties.URL_REST_API;
        LOGGER.info("Using rest base URL {}", baseUrl);

        RestAssured.baseURI = baseUrl;
    }

    // To do a POST to the pmstatus URL.
    protected Response post(String path, String body) {
        return given()
                .body(body)
                .contentType(ContentType.JSON)
                .post(path);
    }

    // To do a POST to the config URL.
    protected void post(String path, String param, String value) {
        given()
                .contentType(ContentType.URLENC.withCharset("UTF-8"))
                .formParam(param, value)
                .post(path);
    }

    // get from Cardstatus and config
    protected Response get(String path) throws Throwable{
        Response response = given().get(path);
        for (int i=0; i < 10; i++){
            if(response.getStatusCode() == 200)
                break;
            response=given().get(path);
            Thread.sleep(1000); // retry in 1 sec
        }

        return response;
    }

    protected RequestSpecification given() {

        return RestAssured.given()
                .baseUri(baseUrl)
                .header("x-api-key", "kxl01DcHxn4GR7bxJNwzT2VgyxsHIno26X97xvx7")
                .header("Accept", "application/json");
    }
}
