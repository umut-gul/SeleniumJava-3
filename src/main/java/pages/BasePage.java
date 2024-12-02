package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }


    protected void clickElement(WebElement element) {
        WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(element));
        clickableElement.click();
    }


    protected void enterText(WebElement element, String text) {
        WebElement visibleElement = wait.until(ExpectedConditions.visibilityOf(element));
        visibleElement.sendKeys(text);
    }


    protected void waitForPageLoad() {
        wait.until(webDriver -> ((org.openqa.selenium.JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
    }


    protected void moveToElement(WebElement element) {
        WebElement visibleElement = wait.until(ExpectedConditions.visibilityOf(element));
        new Actions(driver).moveToElement(visibleElement).perform();
    }
}
