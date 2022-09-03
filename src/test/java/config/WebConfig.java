package config;

import lombok.Data;
import web.WebDriverFactory;

@Data
public class WebConfig {
    private String baseUrl;
    private WebDriverFactory.BrowserType browserType;
    private String webDriverPath;
}
