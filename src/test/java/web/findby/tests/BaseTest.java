package web.findby.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import utils.ScreenshotExtension;
import web.findby.WebDriverFactory;

@ExtendWith(ScreenshotExtension.class)
public class BaseTest {
    protected static final String email = "simpleautomation@rambler.ru";
    protected static final String password = "S1mpl34ut0m4ti0n";
    protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeEach
    public void createDriver(){
        driver.set(WebDriverFactory.getWebDriver());
    }

    //Closed by ScreenshotExtension class methods
//    @AfterEach
//    public void disposeDriver(){
//        if(driver.get() != null){
//            driver.get().quit();
//        }
//    }
}
