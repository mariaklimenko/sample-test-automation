package com.jeta.sample.test.steps;

import com.codeborne.selenide.SelenideElement;
import com.jeta.sample.test.common.WebTestBase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertEquals;

public class WebSteps extends WebTestBase {
    private final SelenideElement email = $(By.id("Email"));
    private final SelenideElement password = $(By.xpath("//*[@id=\"Password\"]"));
    // ideally, all elements should have ids but in this sample site they don't have it, in this case we can use xpath
    private final SelenideElement header_link_login = $(By.xpath("/html/body/div[4]/div[1]/div[1]/div[2]/div[1]/ul/li[2]/a"));
    private final SelenideElement page_title = $(By.xpath("/html/body/div[4]/div[1]/div[4]/div[2]/div/div[1]/h1"));
    private final SelenideElement header_link_account = $(By.xpath("/html/body/div[4]/div[1]/div[1]/div[2]/div[1]/ul/li[1]/a"));
    private final SelenideElement login_btn = $(By.xpath("/html/body/div[4]/div[1]/div[4]/div[2]/div/div[2]/div[1]/div[2]/div[2]/form/div[5]/input"));
    private final Logger LOGGER = LoggerFactory.getLogger(RestSteps.class);

    @Given("^I navigate to site Demo Shop$")
    public void i_navigate_to_site_demo_shop() throws Throwable {
        open();

        By page_title = By.xpath("/html/body/div[4]/div[1]/div[1]/div[1]/a/img");
        $(page_title).should(visible);
    }

    @Then("^Site title is \"(.*)\"$")
    public void checkSiteTitle (String title) throws Throwable {
        String realTitle = title();
        assertEquals("Site title is invalid", title, realTitle);
    }

    @When("^I click header link Login$")
    public void i_click_header_link_login() throws Throwable {
        LOGGER.info("link text: " + header_link_login.getText());
        header_link_login.click();
        $(page_title).should(visible);
    }

    @Then("^I am on Login page$")
    public void i_am_on_login_page() throws Throwable {
        String expectedTitle = "Welcome, Please Sign In!";
        String actualTitle = page_title.getText();
        assertEquals("Page title is invalid", expectedTitle, actualTitle);
    }

    @Then("^I logged as mary_black$")
    public void i_logged_as_correct_user() throws Throwable {
        String expectedAccName = "maryblack@gmail.com";
        String actualAccName = header_link_account.getText();
        assertEquals("Account name is invalid", expectedAccName, actualAccName);    }

    @Given("^I enter credentials for mary_black$")
    public void i_enter_credentials_user() throws Throwable {
        email.clear();
        email.sendKeys("maryblack@gmail.com");
        password.clear();
        password.sendKeys("12345test");
    }

    @When("^I click Login button$")
    public void i_click_login_button() throws Throwable {
        login_btn.click();
        $(By.id("nivo-slider")).should(visible);
    }
}