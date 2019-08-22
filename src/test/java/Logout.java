import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Logout {

    private static WebDriver driver;

    @BeforeEach
    private void openDriver() {
        driver = new ChromeDriver();
        driver.get("https://jira.codecool.codecanvas.hu/");
    }

    @AfterEach
    private void closeDriver() {
        driver.close();
    }

    @Test
    public void checkValidLogout() {

        Wait wait = new FluentWait(driver).ignoring(NoSuchElementException.class).withTimeout(10, TimeUnit.SECONDS);

        driver.findElement(By.id("login-form-username")).sendKeys("user18");
        driver.findElement((By.id("login-form-password"))).sendKeys("CoolCanvas19.");
        driver.findElement(By.id("login")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("header-details-user-fullname")));

        driver.findElement(By.id("header-details-user-fullname")).click();
        driver.findElement((By.id("log_out"))).click();
        wait.until((ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"user-options\"]/a"))));
        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"user-options\"]/a"));
        assertEquals("Log In", loginButton.getText());
    }
}