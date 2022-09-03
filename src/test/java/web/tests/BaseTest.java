package web.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import utils.ScreenshotExtension;
import web.WebDriverFactory;

@ExtendWith(ScreenshotExtension.class)
public class BaseTest {
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
