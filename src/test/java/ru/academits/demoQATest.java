package ru.academits;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class demoQATest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.get("https://demoqa.com/automation-practice-form");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        /*WebElement iframe = driver.findElement(By.id("GTM-MX8DD4S"));
        driver.switchTo().frame(iframe);*/
    }

    @Test
    public void checkURL() {
        Assertions.assertEquals("https://demoqa.com/automation-practice-form", driver.getCurrentUrl(),
                "current URL = " + driver.getCurrentUrl());
    }

    @Test
    public void completeForm() {
        WebElement selectDropdown = driver.findElement(By.xpath("//*[contains(text(), 'Select State')]"));
        Select select = new Select(selectDropdown);

        select.selectByVisibleText("Haryana");
    }

    @Test
    public void completeRadio() {
        WebElement testRadio = driver.findElement(By.cssSelector("input[name='gender'][value='Male']"));
        testRadio.click();
    }

   @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
