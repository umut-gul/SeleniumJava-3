package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {
    private By cookiesAcceptButton = By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]");
    private By genderManButton = By.xpath("//*[@id=\"genderManButton\"]");
    private By searchBar = By.xpath("//input[@placeholder='Ürün, Marka Arayın']");
    private By specificButton = By.xpath("/html/body/header/div[2]/div[1]/div/div/div[2]/div/form/div/button[1]");
    private By searchButton = By.xpath("/html/body/header/div[2]/div[1]/div/div/div[2]/div/form/div/button[2]"); // "Ara" butonunun XPath'i

    public HomePage(WebDriver driver) {
        super(driver);
    }


    public void acceptCookies() {
        WebElement cookiesButton = wait.until(ExpectedConditions.visibilityOfElementLocated(cookiesAcceptButton));
        clickElement(cookiesButton);
        System.out.println("Cookies accepted successfully.");
    }


    public void selectGender() {
        try {
            WebElement genderButton = wait.until(ExpectedConditions.visibilityOfElementLocated(genderManButton));
            clickElement(genderButton);
            System.out.println("Gender selected successfully.");
        } catch (Exception e) {
            System.out.println("Gender selection not required.");
        }
    }


    public void searchProduct(String keyword) {
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBar));
        moveToElement(searchInput);
        enterText(searchInput, keyword);
        System.out.println("Search keyword entered successfully: " + keyword);
    }


    public void clickSpecificButton() {
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(specificButton));
        clickElement(button);
        System.out.println("Specific button clicked successfully.");
    }


    public void clickSearchButton() {
        WebElement searchBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(searchButton));
        clickElement(searchBtn);
        System.out.println("Search button clicked successfully.");
    }
}
