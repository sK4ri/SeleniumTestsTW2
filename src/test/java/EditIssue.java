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


public class EditIssue {

    private static WebDriver driver;

    @BeforeEach
    private void openDriver() {
        driver = new ChromeDriver();
        driver.get("https://jira.codecool.codecanvas.hu/");
        Wait wait = new FluentWait(driver).ignoring(NoSuchElementException.class).withTimeout(10, TimeUnit.SECONDS);
        driver.findElement(By.id("login-form-username")).sendKeys("user18");
        driver.findElement((By.id("login-form-password"))).sendKeys("CoolCanvas19.");
        driver.findElement(By.id("login")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("header-details-user-fullname")));

    }

    @AfterEach
    private void closeDriver() {
        driver.close();
    }

    @Test
    public void checkEditIssuePossible() {
        Wait wait = new FluentWait(driver).ignoring(NoSuchElementException.class).withTimeout(20, TimeUnit.SECONDS);
        driver.findElement(By.id("create_link")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("project-field")));
        driver.findElement(By.id("project-field")).sendKeys("Main Testing Project (MTP)" + Keys.RETURN);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("summary")));
        driver.findElement(By.id("summary")).sendKeys("Edit Issue Test");
        driver.findElement(By.id("create-issue-submit")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"aui-flag-container\"]/div/div/a")));
        driver.findElement(By.xpath("//*[@id=\"aui-flag-container\"]/div/div/a")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("summary-val")));
        assertTrue(isEditButtonPresent());

        // Delete test issue
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"opsbar-operations_more\"]")));
        driver.findElement(By.xpath("//*[@id=\"opsbar-operations_more\"]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"delete-issue\"]/a/span")));
        driver.findElement(By.xpath("//*[@id=\"delete-issue\"]/a/span")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("delete-issue-submit")));
        driver.findElement(By.id("delete-issue-submit")).click();
    }

    @Test
    public void checkCoalaEditPossible() {
        driver.get("https://jira.codecool.codecanvas.hu/issues/?jql=project%20%3D%20COALA");
        assertTrue(isEditButtonPresent());
    }

    @Test
    public void checkJetiEditPossible() {
        driver.get("https://jira.codecool.codecanvas.hu/issues/?jql=project%20%3D%20JETI");
        assertTrue(isEditButtonPresent());
    }

    @Test
    public void checkToucanEditPossible() {
        driver.get("https://jira.codecool.codecanvas.hu/issues/?jql=project%20%3D%20TOUCAN");
        assertTrue(isEditButtonPresent());
    }

    public boolean isEditButtonPresent() {
        try {
            driver.findElement(By.id("edit-issue"));
            return  true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}