package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Random;

public class ProductPage extends BasePage {
    private By productDescription = By.cssSelector("span.o-productDetail__description");
    private By productPrice = By.cssSelector("ins.m-price__new");
    private By addToBasketButton = By.id("addBasket");
    private By sizeOptions = By.cssSelector("span.m-variation__item:not(.-disabled)");
    private By productList = By.xpath("//*[@id='productList']/div"); // Ürünlerin listesi


    public ProductPage(WebDriver driver) {
        super(driver);
    }


    public String getProductDescription() {
        WebElement descriptionElement = wait.until(ExpectedConditions.visibilityOfElementLocated(productDescription));
        String description = descriptionElement.getText();
        System.out.println("Product description retrieved successfully: " + description);
        return description;
    }


    public String getProductPrice() {
        WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(productPrice));
        String price = priceElement.getText();
        System.out.println("Product price retrieved successfully: " + price);
        return price;
    }


    public void addToBasket() {
        WebElement addToBasketElement = wait.until(ExpectedConditions.visibilityOfElementLocated(addToBasketButton));
        clickElement(addToBasketElement);
        System.out.println("Product added to basket successfully.");
    }


    public void selectRandomProduct() {
        List<WebElement> products = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productList));

        if (products.isEmpty()) {
            System.out.println("No products found in the search results.");
            throw new IllegalStateException("No products found in the search results.");
        }


        Random random = new Random();
        int randomIndex = random.nextInt(products.size());
        WebElement randomProduct = products.get(randomIndex);


        clickElement(randomProduct);
        System.out.println("Random product selected and clicked: Product index " + randomIndex);
    }


    public void selectRandomSize() {
        List<WebElement> enabledSizes = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(sizeOptions));

        if (!enabledSizes.isEmpty()) {

            Random random = new Random();
            int randomIndex = random.nextInt(enabledSizes.size());
            WebElement randomSize = enabledSizes.get(randomIndex);


            clickElement(randomSize);
            System.out.println("Selected size: " + randomSize.getText());
        } else {
            System.out.println("No enabled sizes found!");
        }
    }


}
