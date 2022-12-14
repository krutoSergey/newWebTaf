package web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import config.TestConfigFactory;

public class WebDriverFactory {
    private static TestConfigFactory config = TestConfigFactory.getInstance();

    public static WebDriver getWebDriver(){
        switch (config.getWebConfig().getBrowserType()){
            case FIREFOX:
                return new FirefoxDriver();
            case CHROME:
            default:
                return getChromeDriver();
        }
    }

    public enum BrowserType {
        CHROME, FIREFOX
    }

    private static ChromeDriver getChromeDriver(){
        String chromeBinaryName = config.getWebConfig().getWebDriverPath();

        Boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
        if(isWindows) {
            chromeBinaryName += "chromedriver.exe";
        }
        else {
            chromeBinaryName += "chromedriver";
        }

//        System.setProperty("webdriver.chrome.driver", Resources.getResource(chromeBinaryName).getPath());
        System.setProperty("webdriver.chrome.driver", chromeBinaryName);
        return new ChromeDriver();
    }
}
