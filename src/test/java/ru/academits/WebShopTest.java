package ru.academits;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

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
    }

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
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
