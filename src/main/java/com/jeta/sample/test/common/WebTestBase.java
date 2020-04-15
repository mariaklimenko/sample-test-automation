package com.jeta.sample.test.common;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Configuration.baseUrl;

public class WebTestBase {
    public WebTestBase() {
        Configuration.headless = AutomationProperties.BROWSER_HEADLESS_MODE;
        Configuration.timeout = 120000;
        baseUrl = AutomationProperties.WEB_URL;
    }

    protected void open() {
        Selenide.open(baseUrl);
    }
}