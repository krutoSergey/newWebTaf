package web.findby.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import web.findby.page.LoginPage;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тесты на логин")
@Feature("Login feature")
public class LoginTest extends BaseTest{

    @Test
    @DisplayName("Успешный логин")
    @Story("JIRA-1")
    @Description("Тест на проверку успешного логина при правилном введенном логине и пароле")
    public void login(){
        Assertions.assertTrue(
                new LoginPage(driver.get())
                        .loginAs(email, password)
                        .isLoggedInAs(email)
        );
    }

    @Test
    @DisplayName("Логин с неверным паролем")
    @Story("JIRA-1")
    public void loginWithBadPassword(){
        LoginPage page = new LoginPage(driver.get());
        Assertions.assertFalse(
                page
                        .loginAs(email, "WRONG_VALUE")
                        .isLoggedInAs(email)
        );
        assertThat(page.getErrorMessage().contains("Неправильная почта или пароль"));
    }

    @Test
    @DisplayName("Страница логина доступна")
    @Story("JIRA-2")
    public void openLoginPage(){
        Assertions.assertTrue(
                new LoginPage(driver.get()).isOpened()
        );
    }
}
