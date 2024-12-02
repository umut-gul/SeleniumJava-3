
# Selenium Web Automation Project

This project is a Selenium-based web automation framework designed for testing e-commerce functionalities on [Beymen](https://www.beymen.com/tr).

## **Technologies Used**
- **Java**
- **Selenium WebDriver**
- **JUnit** for unit testing
- **Log4J** for logging
- **Maven** for dependency management


## **How to Run**
Clone the repository:
   git clone https://github.com/username/repository.git
Open the project in IntelliJ IDEA or any Java IDE.

##  Ensure that the following are installed:
ChromeDriver
Java 17 or higher


## Update the excelPath variable in TestCase1.java to point to your Excel file path.
Create an Excel file containing the following table:
1 a şort	
1 b gömlek



## Run the tests:
From IntelliJ: Right-click on TestCase1.java > Run.
From terminal:
  mvn test


## Test Cases
The automation performs the following steps:

Opens the Beymen homepage and accepts cookies.
Searches for products ("şort" and "gömlek") from an Excel file.
Selects a random product and size.
Adds the product to the cart.
Verifies that the product price on the product page matches the cart price.
Updates the quantity to 2 and validates.
Removes the product from the cart and confirms the cart is empty.


## Dependencies
The project uses the following Maven dependencies:

Selenium WebDriver
JUnit
Log4J
Apache POI (for Excel integration)

