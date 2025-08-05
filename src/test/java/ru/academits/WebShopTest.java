package ru.academits;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class WebShopTest {
    private WebDriver driver;
    private WebElement element;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.get("https://demowebshop.tricentis.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        /*WebElement iframe = driver.findElement(By.id("GTM-MX8DD4S"));
        driver.switchTo().frame(iframe);*/
    }

    /*@ParameterizedTest
    @ValueSource(strings = {"Laptop", "Smartphone", "Fiction"})
    public void addItems(String item) {
        WebElement searchTerms = driver.findElement(By.id("small-searchterms"));
        searchTerms.sendKeys(item);

        WebElement search = driver.findElement(By.cssSelector("input[value = 'Search']"));
        search.click();

        WebElement addToCart = driver.findElement(By.cssSelector("input[value = 'Add to cart']"));
        addToCart.click();
    }*/

    @ParameterizedTest
    @ValueSource(strings = {"Laptop", "Smartphone", "Fiction"})
    public void addItems(String item) {
        element = driver.findElement(By.id("small-searchterms"));
        element.sendKeys(item);

        element = driver.findElement(By.cssSelector("input[value = 'Search']"));
        element.click();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        element = driver.findElement(By.cssSelector("input[value = 'Add to cart']"));
        element.click();

        element = driver.findElement(By.xpath("//span[contains(text(),'Shopping cart')]"));
        element.click();

        driver.navigate().refresh();

        //a[contains(text(),'Laptop')]

        element = driver.findElement(By.xpath("//td[3]/a"));

        //Assertions.a
    }

    /*@Test
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
    } */

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
