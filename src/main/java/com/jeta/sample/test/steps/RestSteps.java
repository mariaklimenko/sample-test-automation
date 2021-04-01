package com.jeta.sample.test.steps;


import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jeta.sample.test.common.RestTestBase;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import net.minidev.json.JSONArray;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

public class RestSteps extends RestTestBase {
    private final Logger LOGGER = LoggerFactory.getLogger(RestSteps.class);
    private static final String RESOURCES = "templates/";
    private static String comment;
    private Response response;

    protected String loadTemplate(String file) throws IOException, URISyntaxException {
        Assert.assertNotNull("Cannot load null file template", file);
        String filePath = RESOURCES + file.trim();
        LOGGER.info("Loading template file content {}", filePath);
        return new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(filePath).toURI())));
    }

    @Before
    public void before() {
        LOGGER.info("Example of BEFORE hook");
    }

    @After
    public void printTestStatus(Scenario scenario) {
        LOGGER.info("Example of AFTER hook");
        LOGGER.info("The execution status of test \"" + scenario.getName() + "\" is " + scenario.getStatus());
    }

    @When("^Request GET comments, code \"(.*?)\"$")
    public void getRequest(int code) throws Throwable {
        response = get("comments");
        assertEquals("Response code is invalid", code, response.getStatusCode());
    }

    @When("^Request GET comment by ID, code \"(.*?)\"$")
    public void getRequestCommentById(int code) throws Throwable {
        response = get("comments/101");
        assertEquals("Response code is invalid", code, response.getStatusCode());
    }

    @When("^Request POST comment, code \"(.*?)\"$")
    public void postRequest(int code) throws Throwable {
        response = post("comments", comment);
        assertEquals("Response code is invalid", 201, response.getStatusCode());
    }

    @Given("^Use file \"(.*?)\" as comment template$")
    public void useFileAsTemplate(String filename) throws Throwable {
        comment = loadTemplate(filename);
        assertNotNull("Loaded comment template should not be null", comment);
    }

    @Then("^Compare comment template with response$")
    public void compareTemplateWithResponse() throws Throwable {
        assertEquals("Actual response is different from template", comment, response.body().asString());
    }

    @When("^Response contains \"(.*?)\" comments$")
    public void response_contains_comments(Integer i) throws Throwable {
        DocumentContext actual = JsonPath.parse(response.body().asString());
        JSONArray comments = actual.read("$[*].id");
        assertEquals("Number of entries in response is wrong", 500, comments.size());
    }
}