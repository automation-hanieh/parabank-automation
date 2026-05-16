package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegisterPage {

    WebDriver driver;
    WebDriverWait wait;

    private By firstName = By.id("customer.firstName");
    private By lastName = By.id("customer.lastName");
    private By address = By.id("customer.address.street");
    private By city = By.id("customer.address.city");
    private By state = By.id("customer.address.state");
    private By zipCode = By.id("customer.address.zipCode");
    private By phone = By.id("customer.phoneNumber");
    private By ssn = By.id("customer.ssn");
    private By username = By.id("customer.username");
    private By password = By.id("customer.password");
    private By confirm = By.id("repeatedPassword");
    private By registerButton = By.xpath("//input[@value='Register']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateTo() {
        driver.get("https://parabank.parasoft.com/parabank/register.htm");
    }

    public void register(String user, String pass) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
        driver.findElement(firstName).sendKeys("Test");
        driver.findElement(lastName).sendKeys("User");
        driver.findElement(address).sendKeys("123 Main St");
        driver.findElement(city).sendKeys("Tehran");
        driver.findElement(state).sendKeys("TX");
        driver.findElement(zipCode).sendKeys("12345");
        driver.findElement(phone).sendKeys("1234567890");
        driver.findElement(ssn).sendKeys("123456789");
        driver.findElement(username).sendKeys(user);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(confirm).sendKeys(pass);
        driver.findElement(registerButton).click();
    }

    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.linkText("Log Out")));
        driver.findElement(By.linkText("Log Out")).click();
    }
}
