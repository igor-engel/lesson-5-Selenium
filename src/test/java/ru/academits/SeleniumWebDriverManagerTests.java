package ru.academits;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumWebDriverManagerTests {

    @Test
    public void simpleTest() {
        WebDriverManager.chromedriver().setup();
        WebDriver chromeDriver = new ChromeDriver();

        /*WebDriverManager.firefoxdriver().setup();
        WebDriver firefoxDriver = new FirefoxDriver();*/

       /* WebDriverManager.edgedriver().setup();
        WebDriver edgeDriver = new EdgeDriver();*/

        chromeDriver.get("https://demoqa.com/automation-practice-form");

        Assertions.assertEquals("https://demoqa.com/automation-practice-form", chromeDriver.getCurrentUrl(),
                "current URL = " + chromeDriver.getCurrentUrl());

        chromeDriver.quit();
    }
}
