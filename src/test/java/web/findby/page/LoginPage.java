package web.findby.page;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import web.findby.elements.Button;
import web.findby.elements.EditBox;
import web.findby.elements.IFrame;

public class LoginPage extends BasePage {
    @FindBy(xpath = "//*[text()='Вход']")
    private Button openLogin;
    @FindBy(xpath = "//div[@data-id-frame]/iframe")
    private IFrame loginIFrame;
    @FindBy(id = "login")
    private EditBox loginBox;
    @FindBy(id = "password")
    private EditBox passwordBox;
    @FindBy(xpath = "//*[text()='Войти']")
    private Button processLogin;

    @FindBy(xpath = "//*[@id='password']/../../div[2]")
    private WebElement loginErrorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
        driver.get(BASE_URL);
        openLogin.click();
        loginIFrame.switchTo();
        loginBox.waitVisibility();
    }

    public LoginPage typeLogin(String email){
        loginBox.sendKeys(email);
        return this;
    }

    public LoginPage typePassword(String password){
        passwordBox.sendKeys(password);
        return this;
    }

    public SearchPage clickLogin(){
        processLogin.click();
        return new SearchPage(driver);
    }

    public String getErrorMessage() {
        return driver.findElement(By.xpath("//*[@id='password']/../../div[2]")).getText();
    }

    public boolean isOpened(){
        return loginBox.waitVisibility().isDisplayed();
    }

    @Step("Логин в портал с логином {email} и паролем {password}")
    @Attachment()
    public SearchPage loginAs(String email, String password){
        typeLogin(email);
        typePassword(password);
        return clickLogin();
    }
}
