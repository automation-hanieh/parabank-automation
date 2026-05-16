package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OverviewPage {

    WebDriver driver;

    private By firstAccountBalance = By.cssSelector("#accountTable tbody tr:first-child td:nth-child(2)");
    private By secondAccountBalance = By.cssSelector("#accountTable tbody tr:nth-child(2) td:nth-child(2)");
    private By totalBalance = By.cssSelector("td[align='right'] b");

    public OverviewPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getFirstAccountBalance() {
        return driver.findElement(firstAccountBalance).getText();
    }

    public String getSecondAccountBalance() {
        return driver.findElement(secondAccountBalance).getText();
    }

    public String getTotalBalance() {
        return driver.findElement(totalBalance).getText();
    }

    public void navigateTo() {
        driver.findElement(By.linkText("Accounts Overview")).click();
    }
}