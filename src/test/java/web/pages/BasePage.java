package web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.JSExecutor;
import config.TestConfigFactory;
import web.elements.ElementsDecorator;

import java.time.Duration;

public abstract class BasePage {
    private static TestConfigFactory config = TestConfigFactory.getInstance();
    protected static final String BASE_URL = config.getWebConfig().getBaseUrl();
    private static final Duration DEFAULT_TIMEOUT_SECONDS = Duration.ofSeconds(10);

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JSExecutor js;

    public BasePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, DEFAULT_TIMEOUT_SECONDS);
        this.js = new JSExecutor(driver);

        ElementLocatorFactory factory = new DefaultElementLocatorFactory(driver);
        ElementsDecorator decorator = new ElementsDecorator(factory, driver);
        PageFactory.initElements(decorator, this);
    }

    protected void waitAndClick(WebElement locator){
//        new Actions(driver).click(wait.until(ExpectedConditions.elementToBeClickable(locator)));
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected WebElement waitVisibility(WebElement locator){
        return wait.until(ExpectedConditions.visibilityOf(locator));
    }

    public void switchToAnotherTab(){
        driver.switchTo().window(
                driver.getWindowHandles().stream()
                        .filter(h -> !h.equals(driver.getWindowHandle()))
                        .findFirst().get()
        );
    }
}
