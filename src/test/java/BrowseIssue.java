import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class BrowseIssue {

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
    public void searchIssuesPage () {

        Wait wait = new FluentWait(driver).ignoring(NoSuchElementException.class).withTimeout(10, TimeUnit.SECONDS);
        driver.manage().window().fullscreen();
        WebElement issuesDropdown = (WebElement) wait.until(ExpectedConditions.presenceOfElementLocated(By.id("find_link")));
        issuesDropdown.click();
        WebElement searcForIssues = (WebElement) wait.until(ExpectedConditions.presenceOfElementLocated(By.id("issues_new_search_link_lnk")));
        searcForIssues.click();
        assertEquals(true,driver.getCurrentUrl().contains("https://jira.codecool.codecanvas.hu/browse/"));
    }

    @Test
    public void searchForToucanIssues () {

        Wait wait = new FluentWait(driver).ignoring(NoSuchElementException.class).withTimeout(5, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("header-details-user-fullname")));
        driver.get("https://jira.codecool.codecanvas.hu/issues/?jql=project%20%3D%20TOUCAN%20ORDER%20BY%20key%20ASC");
        driver.manage().window().fullscreen();
        boolean testFine;
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-key='TOUCAN-1']")));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-key='TOUCAN-2']")));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-key='TOUCAN-3']")));
            testFine = true;
        }
        catch (TimeoutException e) {
            testFine = false;
        }
        assertTrue(testFine);
    }

    @Test
    public void searchForCoalaIssues () {

        Wait wait = new FluentWait(driver).ignoring(NoSuchElementException.class).withTimeout(5, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("header-details-user-fullname")));
        driver.get("https://jira.codecool.codecanvas.hu/issues/?jql=project%20%3D%20COALA%20ORDER%20BY%20key%20ASC");
        driver.manage().window().fullscreen();
        boolean testFine;
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-key='COALA-1']")));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-key='COALA-2']")));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-key='COALA-3']")));
            testFine = true;
        }
        catch (TimeoutException e) {
            testFine = false;
        }
        assertTrue(testFine);
    }

    @Test
    public void searchForJetiIssues () {

        Wait wait = new FluentWait(driver).ignoring(NoSuchElementException.class).withTimeout(5, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("header-details-user-fullname")));
        driver.get("https://jira.codecool.codecanvas.hu/issues/?jql=project%20%3D%20JETI%20ORDER%20BY%20key%20ASC");
        driver.manage().window().fullscreen();
        boolean testFine;
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-key='JETI-1']")));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-key='JETI-1']")));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-key='JETI-1']")));
            testFine = true;
        }
        catch (TimeoutException e) {
            testFine = false;
        }
        assertTrue(testFine);
    }
}
