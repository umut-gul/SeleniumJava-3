package Tests;

import helpers.ExcelReader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;

import java.io.FileWriter;
import java.io.IOException;

public class TestCase1 {
    private WebDriver driver;
    private HomePage homePage;
    private ProductPage productPage;
    private CartPage cartPage;
    private final String excelPath = "/Users/umutgul/Desktop/beymenexcel.xlsx";

    @Before
    public void setUp() {
        // ChromeDriver setup
        driver = new ChromeDriver();
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        driver.manage().window().maximize();
        driver.get("https://www.beymen.com/tr");
    }

    @Test
    public void testBeymenShoppingFlow() throws IOException {

        homePage.acceptCookies();
        homePage.selectGender();
        String shortKeyword = ExcelReader.readExcel(excelPath, 0, 0, 0);
        homePage.searchProduct(shortKeyword);
        homePage.clickSpecificButton();
        driver.navigate().refresh();
        String shirtKeyword = ExcelReader.readExcel(excelPath, 0, 0, 1);
        homePage.searchProduct(shirtKeyword);
        homePage.clickSearchButton();


        productPage.selectRandomProduct();
        productPage.selectRandomSize();
        String productDescription = productPage.getProductDescription();
        String productPrice = productPage.getProductPrice();
        productPage.addToBasket();
        try (FileWriter writer = new FileWriter("/Users/umutgul/Desktop/productDetails.txt")) {
            writer.write("Product Description: " + productDescription + "\n");
            writer.write("Product Price: " + productPrice + "\n");
        }
        try {
            Thread.sleep(5000); // Sabit bir bekleme süresi
        } catch (InterruptedException e) {
            System.out.println("Sleep interrupted: " + e.getMessage());
        }


        cartPage.openBasket();
        try {
            Thread.sleep(1000); // Sabit bir bekleme süresi
        } catch (InterruptedException e) {
            System.out.println("Sleep interrupted: " + e.getMessage());
        }
        String cartPrice = cartPage.getCartPagePrice();
        System.out.println("Sepet Sayfasındaki Fiyat: " + cartPrice);
        double normalizedProductPrice = cartPage.normalizePrice(productPrice);
        double normalizedCartPrice = cartPage.normalizePrice(cartPrice);
        if (Double.compare(normalizedProductPrice, normalizedCartPrice) == 0) {
            System.out.println("Fiyatlar aynı: " + normalizedProductPrice + " TL");
        } else {
            System.out.println("Fiyatlar farklı! Ürün Fiyatı: " + normalizedProductPrice + " TL, Sepet Fiyatı: " + normalizedCartPrice + " TL");
        }
        cartPage.selectQuantity("2");
        cartPage.removeItem();
        assert cartPage.isCartEmpty() : "Cart is not empty!";

    }


    @After
    public void tearDown() {
        // Close browser after test
        if (driver != null) {
            driver.quit();
        }
    }
}
