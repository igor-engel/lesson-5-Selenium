package ru.academits;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
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

        /*
        WebDriverWait wait = new WebDriverWait(driver, timeO);
        Duration timeout = Duration.ofSeconds(30);
        Duration sleep = Duration.ofMillis(500);
        wait = new WebDriverWait(driver, timeout);*/
    }

    @ParameterizedTest
    @ValueSource(strings = {"Laptop", "Smartphone", "Fiction"})
    public void addItems(String item) {
        element = driver.findElement(By.id("small-searchterms"));
        element.sendKeys(item);

        element = driver.findElement(By.cssSelector("input[value = 'Search']"));
        element.click();

        String product = driver.findElement(By.xpath("//*[@class='product-title']//a")).getText();
        System.out.println(product);

        /*Duration timeout = Duration.ofSeconds(30);
        Duration sleep = Duration.ofSeconds(500);
        WebDriverWait wait = new WebDriverWait(driver, timeout, sleep);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[value = 'Add to cart']")));*/

        //периодическое StaleElementReferenceException: не знаю как обойти
        element = driver.findElement(By.xpath("//*[@class='button-2 product-box-add-to-cart-button']"));
        element.click();

        element = driver.findElement(By.xpath("//span[contains(text(),'Shopping cart')]"));
        element.click();

        driver.navigate().refresh();

        element = driver.findElement(By.xpath("//td[3]/a"));

        Assertions.assertEquals(element.getText(), product);
        System.out.println("корзина = " + element.getText() + " меню = " + product);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
