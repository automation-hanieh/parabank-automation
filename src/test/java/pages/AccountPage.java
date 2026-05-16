package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage {

    WebDriver driver;

    private By accountMenu = By.id("type");
    private By openNewAccountButton = By.cssSelector("input[value='Open New Account']");
    private By newAccountNumber = By.id("newAccountId");

    WebDriverWait wait;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void selectAccountType(String type) {
        Select dropdown = new Select(driver.findElement(accountMenu));
        dropdown.selectByVisibleText(type);
    }

    public void clickOpenNewAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.linkText("Open New Account")));
        driver.findElement(By.linkText("Open New Account")).click();
    }
    public void clickOpenAccount() {
        driver.findElement(openNewAccountButton).click();
    }

    public String getNewAccountNumber() {
        return driver.findElement(newAccountNumber).getText();
    }
}
