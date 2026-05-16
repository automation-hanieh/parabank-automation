package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TransferPage {

    WebDriver driver;
    WebDriverWait wait;

    private By amountField = By.id("amount");
    private By fromAccount = By.id("fromAccountId");
    private By toAccount = By.id("toAccountId");
    private By transferButton = By.cssSelector("input[value='Transfer']");

    public TransferPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterAmount(String amount) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(amountField));
        driver.findElement(amountField).sendKeys(amount);
    }

    public void selectFromAccount(String accountId) {
        wait.until(ExpectedConditions.presenceOfElementLocated(fromAccount));
        wait.until(ExpectedConditions.elementToBeClickable(fromAccount));
        Select dropdown = new Select(driver.findElement(fromAccount));
        dropdown.selectByVisibleText(accountId);

    }

    public void selectToAccount(String accountId) {
        wait.until(ExpectedConditions.presenceOfElementLocated(toAccount));
        Select dropdown = new Select(driver.findElement(toAccount));
        dropdown.selectByVisibleText(accountId);
    }

    public void clickTransfer() {
        driver.findElement(transferButton).click();
    }

    public void navigateTo() {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.linkText("Transfer Funds")));
        driver.findElement(By.linkText("Transfer Funds")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(fromAccount));
    }

    public String getFirstAccountNumber() {

        wait.until(driver -> {
            Select dropdown =
                    new Select(driver.findElement(fromAccount));

            return dropdown.getOptions().size() > 0;
        });

        Select dropdown =
                new Select(driver.findElement(fromAccount));

        return dropdown.getOptions().get(0).getText();
    }
}