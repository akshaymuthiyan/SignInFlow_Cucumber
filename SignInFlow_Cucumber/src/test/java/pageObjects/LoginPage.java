package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.interactions.Actions;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver rdriver) {
        driver = rdriver;
        PageFactory.initElements(rdriver, this);
    }

    @FindBy(xpath = "//div[@class='panel header']//a[contains(text(),'Sign In')]")
    WebElement lnkSignIn;

    @FindBy(id = "email")
    WebElement txtEmail;

    @FindBy(id = "pass")
    WebElement txtPassword;

    @FindBy(id = "send2")
    WebElement btnSignIn;

    @FindBy(xpath = "//button[@data-action='customer-menu-toggle']")
    public WebElement dropdownUserMenu;  

    By logoutLinkLocator = By.xpath("//a[text()='Sign Out']");

    public WebElement getLogoutLink() {
        return driver.findElement(logoutLinkLocator);
    }


    public void clickSignInLink() {
        lnkSignIn.click();
    }

    public void setEmail(String email) {
        txtEmail.sendKeys(email);
    }

    public void setPassword(String pwd) {
        txtPassword.sendKeys(pwd);
    }


    public void clickSignInBtn() {
        btnSignIn.click();
    }

    public void clickUserMenu() {
        dropdownUserMenu.click();
    }

    public void clickLogout() {
        driver.findElement(logoutLinkLocator).click(); 
    }

}
