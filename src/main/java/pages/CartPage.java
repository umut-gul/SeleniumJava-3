package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class CartPage extends BasePage {
    private By basketIcon = By.xpath("/html/body/header/div/div/div[3]/div/a[3]");
    private By quantityDropdown = By.id("quantitySelect0-key-0");
    private By removeButton = By.xpath("//*[@id='removeCartItemBtn0-key-0']");
    private By emptyCartMessage = By.xpath("//strong[@class='m-empty__messageTitle' and text()='Sepetinizde Ürün Bulunmamaktadır']");

    private By cartPagePrice = By.xpath("//*[@id='app']/div[1]/div/div/div[1]/div[2]/div[1]/div[2]/div/div/div[2]/div/div[1]/div/div/span"); // Sepet sayfasındaki fiyat elementi

    public CartPage(WebDriver driver) {
        super(driver);
    }


    public void openBasket() {
        WebElement basket = wait.until(ExpectedConditions.visibilityOfElementLocated(basketIcon));
        clickElement(basket);
        System.out.println("Basket opened successfully.");
    }


    public void selectQuantity(String quantity) {
        WebElement dropdownElement = wait.until(ExpectedConditions.visibilityOfElementLocated(quantityDropdown));
        wait.until(ExpectedConditions.elementToBeClickable(quantityDropdown));
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByValue(quantity);
        System.out.println("Quantity selected successfully: " + quantity);
    }



    public boolean isCartEmpty() {
        wait.until(ExpectedConditions.presenceOfElementLocated(emptyCartMessage));
        boolean isEmpty = !driver.findElements(emptyCartMessage).isEmpty();
        if (isEmpty) {
            System.out.println("Cart is empty.");
        } else {
            System.out.println("Cart is not empty.");
        }
        return isEmpty;
    }


    public void removeItem() {
        WebElement removeButtonElement = wait.until(ExpectedConditions.visibilityOfElementLocated(removeButton));
        clickElement(removeButtonElement);
        System.out.println("Item removed from cart successfully.");
    }

    public String getCartPagePrice() {
        WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(cartPagePrice));
        String price = priceElement.getText();
        System.out.println("Cart page price retrieved: " + price);
        return price;
    }

    public double normalizePrice(String priceText) {
        try {
            return Double.parseDouble(priceText.replace(".", "").replace(",", ".").replace(" TL", "").trim());
        } catch (NumberFormatException e) {
            System.out.println("Error normalizing price: " + e.getMessage());
            throw e;
        }
    }

}
