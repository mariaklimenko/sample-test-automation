package com.jeta.sample.test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/main/resources/features",
        glue = "com.jeta.sample.test",
        tags = "@rest",
        plugin = {
                "json:target/cucumber-report.json",
                "testng:target/testng-results.xml",
                "junit:target/junit.xml",
                "rerun:target/failedrerun.txt",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }
)
public class RunCukesTest extends AbstractTestNGCucumberTests {
  /*  @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        boolean parallel = false;
        if(System.getProperty("parallel") != null){
            parallel = Boolean.parseBoolean(System.getProperty("parallel"));
        }
      //  AutomationProperties.getInstance(parallel);
        return super.scenarios();
    } */
}