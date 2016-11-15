package com.automician.testconfigs;

import com.codeborne.selenide.Configuration;
import core.Helpers;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

import java.util.Properties;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;

public class BaseTest {

    public static String appURL;

    static {
        Properties properties = Helpers.getProperties();
        appURL = properties.getProperty("app.url");
        System.out.println("App URL = " + appURL);
        Configuration.browser = System.getProperty("driver.browser");
        System.out.println("Driver browser = " + Configuration.browser);
    }

    @Before
    public void openApp() {
        open(appURL);
    }

    @After
    public void clearData() {
        executeJavaScript("localStorage.clear();");
    }
}
