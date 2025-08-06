package ru.academits;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
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
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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

        errors = driver.findElements(By.xpath("//*[@class='errorSummary errorSummary_top']//ul//li"));

        String name = "Необходимо заполнить поле ФИО:.";
        String mail = "Необходимо заполнить поле E-mail.";
        String count = "Необходимо заполнить поле Количество.";
        String date = "Необходимо заполнить поле Дата доставки.";

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(name).isEqualTo(errors.get(0).getText());
        softAssertions.assertThat(mail).isEqualTo(errors.get(1).getText());
        softAssertions.assertThat(count).isEqualTo(errors.get(2).getText());
        softAssertions.assertThat(date).isEqualTo(errors.get(3).getText());

        softAssertions.assertAll();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}