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
        WebElement smallSearchterms = driver.findElement(By.id("small-searchterms"));
        smallSearchterms.sendKeys(item);

        WebElement search = driver.findElement(By.cssSelector("input[value = 'Search']"));
        search.click();

        String product = driver.findElement(By.xpath("//*[@class='product-title']//a")).getText();

        /*Duration timeout = Duration.ofSeconds(30);
        Duration sleep = Duration.ofSeconds(500);
        WebDriverWait wait = new WebDriverWait(driver, timeout, sleep);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("//*[@class='button-2 product-box-add-to-cart-button']")));
        //input[value = 'Add to cart']*/

        //периодическое StaleElementReferenceException: не знаю как обойти
        WebElement cartButton = driver.findElement(By.xpath("//*[@class='button-2 product-box-add-to-cart-button']"));
        cartButton.click();

        WebElement shoppingCart = driver.findElement(By.xpath("//span[contains(text(),'Shopping cart')]"));
        shoppingCart.click();

        driver.navigate().refresh();

        WebElement element = driver.findElement(By.xpath("//td[3]/a"));

        Assertions.assertEquals(element.getText(), product);
        System.out.println("корзина = " + element.getText() + " меню = " + product);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
