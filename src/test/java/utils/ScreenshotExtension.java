package utils;

import io.qameta.allure.Allure;
import lombok.SneakyThrows;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Field;
import java.util.Optional;

public class ScreenshotExtension implements TestWatcher {

    @SneakyThrows
    @Override
    public void testFailed(ExtensionContext context, Throwable throwable) {
        WebDriver driver = getDriver(context);

        Allure.getLifecycle().addAttachment(
                "Screenshot",
                "image/png",
                "png",
                ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)
        );
        getDriver(context).quit();
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        getDriver(context).quit();
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        getDriver(context).quit();
    }

    @SneakyThrows
    @Override
    public void testAborted(ExtensionContext context, Throwable cause)
    {
        WebDriver driver = getDriver(context);

        Allure.getLifecycle().addAttachment(
                "Screenshot",
                "image/png",
                "png",
                ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)
        );
        getDriver(context).quit();
    }

    private WebDriver getDriver(ExtensionContext context) {
        Object instance = context.getRequiredTestInstance();
        try {
            Field field = instance.getClass().getSuperclass().getDeclaredField("driver");
            field.setAccessible(true);
            return ((ThreadLocal<WebDriver>)field.get(instance)).get();
        }
        catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
