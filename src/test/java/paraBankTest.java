import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class paraBankTest {

    WebDriver driver;
    LoginPage loginPage;
    AccountPage accountPage;
    TransferPage transferPage;
    OverviewPage overviewPage;
    RegisterPage registerPage;
    String username;
    String password = "Test1234";

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        accountPage = new AccountPage(driver);
        transferPage = new TransferPage(driver);
        overviewPage = new OverviewPage(driver);
        registerPage = new RegisterPage(driver);
        username = "testuse" + System.currentTimeMillis();
        registerPage.navigateTo();
        registerPage.register(username, password);
        registerPage.logout();

    }

    @Test
    public void testSuccessfulLogin() {
        loginPage.navigateTo();
        loginPage.login(username, password);

        String currentUrl = driver.getCurrentUrl();
        Assert.assertFalse(currentUrl.contains("login"));
        System.out.println("✅ Login successful! URL: " + currentUrl);
    }

    @Test
    public void testOpenNewAccount() {
        loginPage.navigateTo();
        loginPage.login(username, password);

        accountPage.clickOpenNewAccount();
        accountPage.selectAccountType("SAVINGS");
        accountPage.clickOpenAccount();

        String newAccountNumber = accountPage.getNewAccountNumber();
        Assert.assertNotNull(newAccountNumber);
        System.out.println("✅ New account opened: " + newAccountNumber);
    }

    @Test
    public void testTransferFunds() {

        // Login
        loginPage.navigateTo();
        loginPage.login(username, password);

        // Create second account
        accountPage.clickOpenNewAccount();
        accountPage.selectAccountType("SAVINGS");
        accountPage.clickOpenAccount();

        // Go to transfer page
        transferPage.navigateTo();

        // Get first account number
        String accountNumber = transferPage.getFirstAccountNumber();

        // Transfer funds
        transferPage.selectFromAccount(accountNumber);
        transferPage.selectToAccount(accountNumber);
        transferPage.enterAmount("100");
        transferPage.clickTransfer();

        // Wait for transfer confirmation
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("#showResult h1.title")));

        String confirmation = driver.findElement(
                By.cssSelector("#showResult h1.title")).getText();

        Assert.assertTrue(
                confirmation.contains("Transfer Complete"));

        System.out.println("✅ Transfer successful!");

        // Go to overview page
        overviewPage.navigateTo();

        // Wait for balances table
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("#accountTable tbody tr")));

        // Get balances
        String firstBalance =
                overviewPage.getFirstAccountBalance();

        String secondBalance =
                overviewPage.getSecondAccountBalance();

        String totalBalance =
                overviewPage.getTotalBalance();

        // Assertions
        Assert.assertFalse(firstBalance.isEmpty());
        Assert.assertFalse(secondBalance.isEmpty());
        Assert.assertFalse(totalBalance.isEmpty());

        // Print balances
        System.out.println(
                "✅ First Account Balance: " + firstBalance);

        System.out.println(
                "✅ Second Account Balance: " + secondBalance);

        System.out.println(
                "✅ Total Balance: " + totalBalance);
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
