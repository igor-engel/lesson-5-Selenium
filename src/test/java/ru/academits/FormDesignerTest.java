package ru.academits;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FormDesignerTest {
    private WebDriver driver;
    List<WebElement> errors;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://formdesigner.ru/examples/order.html");
        driver.manage().window().maximize();
    }

    @Test
    public void checkErrors() {
        WebElement accept = driver.findElement(By.id("c-p-bn"));
        accept.click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        WebElement iframe = driver.findElement(By.cssSelector("#form_1006 > iframe"));
        driver.switchTo().frame(iframe);

        driver.findElement(By.xpath("//*[contains(text(), 'Отправить')]")).sendKeys(Keys.RETURN);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        errors = driver.findElements(By.xpath("//*[@class='errorSummary errorSummary_top']//ul//li"));

        Assertions.assertEquals("ФИО: field is required.", errors.get(0).getText());
        Assertions.assertEquals("E-mail field is required.", errors.get(1).getText());
        Assertions.assertEquals("Количество field is required.", errors.get(2).getText());
        Assertions.assertEquals("Дата доставки field is required.", errors.get(3).getText());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}