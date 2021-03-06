package com.jeta.sample.test;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/main/resources/features/",
        glue = "com.jeta.sample.test",
       // tags ={"@Regression and @Automated"},
        plugin = {"json:target/cucumber-report.json","usage"}
)
public class RunCukesTest {
}