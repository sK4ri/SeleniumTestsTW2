import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.*;

public class Login {

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
    public void checkValidLogin() {

        Wait wait = new FluentWait(driver).ignoring(NoSuchElementException.class).withTimeout(10, TimeUnit.SECONDS);
        driver.findElement(By.id("login-form-username")).sendKeys("user18");
        driver.findElement((By.id("login-form-password"))).sendKeys("CoolCanvas19.");
        driver.findElement(By.id("login")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("header-details-user-fullname")));
        WebElement userID = driver.findElement(By.id("header-details-user-fullname"));
        assertEquals("user18", userID.getAttribute("data-username"));
    }

    @Test
    public void checkMissingLoginCredentials() {

        Wait wait = new FluentWait(driver).ignoring(NoSuchElementException.class).withTimeout(10, TimeUnit.SECONDS);
        driver.findElement(By.id("login")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"usernameerror\"]/p")));
        WebElement errorMessage = driver.findElement(By.xpath("//*[@id=\"usernameerror\"]/p"));
        assertEquals("Sorry, your username and password are incorrect - please try again.", errorMessage.getText());
    }

    @Test
    public void checkLoginWithEnter() {

        Wait wait = new FluentWait(driver).ignoring(NoSuchElementException.class).withTimeout(10, TimeUnit.SECONDS);
        driver.findElement(By.id("login-form-username")).sendKeys("user18");
        driver.findElement((By.id("login-form-password"))).sendKeys("CoolCanvas19." + Keys.RETURN);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("header-details-user-fullname")));
        WebElement userID = driver.findElement(By.id("header-details-user-fullname"));
        assertEquals("user18", userID.getAttribute("data-username"));
    }
}