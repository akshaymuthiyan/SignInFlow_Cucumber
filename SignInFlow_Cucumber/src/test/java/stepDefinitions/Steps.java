package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import pageObjects.LoginPage;

import java.time.Duration;

public class Steps {

    WebDriver driver;
    LoginPage loginPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();  // WebDriverManager usage recommended for real project
        driver.manage().window().maximize();
    }

    @Given("User Launch Chrome browser")
    public void user_launch_chrome_browser() {
        loginPage = new LoginPage(driver);
    }

    @When("User opens URL {string}")
    public void user_opens_url(String url) {
        driver.get(url);
    }

    @And("User clicks on Sign In link")
    public void user_clicks_on_sign_in_link() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            WebElement signInLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Sign In")));
            signInLink.click();
        } catch (TimeoutException e) {
            // Try alternate locator if Sign In is not found by linkText
            WebElement signInLinkAlt = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[contains(@class,'authorization-link')]")));
            signInLinkAlt.click();
        }
    }

    @And("User enters Email as {string} and Password as {string}")
    public void user_enters_email_and_password(String email, String password) {
        loginPage.setEmail(email);         // Changed from enterEmail
        loginPage.setPassword(password);   // Changed from enterPassword
    }

    @And("Click on SignIn")
    public void click_on_sign_in() {
        loginPage.clickSignInBtn();        // Changed from clickLogin
    }

    @Then("Page Title should be {string}")
    public void page_title_should_be(String expectedTitle) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleIs(expectedTitle));
        Assert.assertEquals(expectedTitle, driver.getTitle());
    }

    @When("User click on Sign out link")
    public void user_click_on_sign_out_link() {
        loginPage.clickUserMenu();  // click on user menu first
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Wait for the logout link to be visible *after* menu opens
        WebElement logoutLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//a[normalize-space()='Sign Out']")));

        logoutLink.click();
    }



    @Then("User should see logout confirmation message")
    public void user_should_see_logout_confirmation_message() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//span[contains(text(),'You are signed out')]")));
        Assert.assertTrue(msg.isDisplayed());
    }


    @And("Close the browser")
    public void close_the_browser() {
        driver.quit();
    }
}
