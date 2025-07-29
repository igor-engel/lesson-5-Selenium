package ru.academits;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class formDesignerTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.get("https://formdesigner.ru/examples/order.html");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void sendForm() {
        WebElement accept = driver.findElement(By.id("c-p-bn"));
        accept.click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        /*WebElement iframe = driver.findElement(By.cssSelector("#form1006-271"));
        driver.switchTo().frame(iframe);*/

        WebElement enter = driver.findElement(By.cssSelector("button[name='submit'][value='send']"));
        enter.click();

        //*[contains(text(), 'Отправить')]"
    }

    /*@Test
    public void scrollWindow() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    @Test
    public void openFrame() {
        WebElement iframe = driver.findElement(By.cssSelector("#form1006-271"));
        driver.switchTo().frame(iframe);
    }*/

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
