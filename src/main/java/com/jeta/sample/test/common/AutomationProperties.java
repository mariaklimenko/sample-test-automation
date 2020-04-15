package com.jeta.sample.test.common;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class AutomationProperties {
    private static final String AUTOMATION_PROPERTIES = "automation.properties";
    private static AutomationProperties instance;
    private static Config config;
    public static String TEST_ENV;
    public static Boolean BROWSER_HEADLESS_MODE;
    public static String WEB_URL;
    public static String URL_REST_API;
    public static String SQS_TIMEOUT;


    public static AutomationProperties get() {
        if (instance == null) {
            instance = new AutomationProperties();
        }
        return instance;
    }

    private AutomationProperties() {
        config = ConfigFactory.load(AUTOMATION_PROPERTIES);
        TEST_ENV = getEnvironment();
        URL_REST_API =getUrlRestApi();
        WEB_URL =getWebUrl();
        BROWSER_HEADLESS_MODE =getBrowserHeadlessMode();
        SQS_TIMEOUT = getTimeout();
    }

    private String getEnvironment() {
        String env = System.getProperty("env");
        return env != null ? env : "env1";
    }

    private String getUrlRestApi() {
        String baseUrl = System.getProperty("rest.baseurl");
        return baseUrl != null ? baseUrl : config.getString(TEST_ENV + ".rest.baseurl");
    }

    private Boolean getBrowserHeadlessMode() {
        String headless = System.getProperty("web.headless");
        return headless != null ? Boolean.valueOf(headless) : Boolean.valueOf(config.getString(TEST_ENV + ".web.headless"));
    }

    private String getWebUrl() {
        String baseUrl = System.getProperty("web.baseurl");
        return baseUrl != null ? baseUrl : config.getString(TEST_ENV + ".rest.baseurl");
    }

    private String getTimeout() {
        String timeout = System.getProperty("sqs.timeout");
        return timeout != null ? timeout : config.getString("sqs.timeout");
    }

}