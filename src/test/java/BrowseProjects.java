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

public class BrowseProjects {

    private static WebDriver driver;

    @BeforeEach
    private void openDriver() {
        driver = new ChromeDriver();
        driver.get("https://jira.codecool.codecanvas.hu/");
        driver.findElement(By.id("login-form-username")).sendKeys("user19");
        driver.findElement((By.id("login-form-password"))).sendKeys("CoolCanvas19.");
        driver.findElement(By.id("login")).click();

    }

    @AfterEach
    private void closeDriver() {
        driver.close();
    }

    @Test
    public void browsingProject() {

        Wait wait = new FluentWait(driver).ignoring(NoSuchElementException.class).withTimeout(10, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("header-details-user-fullname")));
        WebElement userID = driver.findElement(By.id("header-details-user-fullname"));
        driver.get("https://jira.codecool.codecanvas.hu/secure/BrowseProjects.jspa?selectedCategory=all&selectedProjectType=all");
        boolean projectsPresent;
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(@original-title, 'COALA')]")));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(@original-title, 'JETI')]")));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(@original-title, 'TOUCAN')]")));
            projectsPresent = true;
        }
        catch (NoSuchElementException e) {
            projectsPresent = false;
        }
        assertEquals(true, projectsPresent);
    }
}