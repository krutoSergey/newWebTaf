package web.tests.rambler;

import enums.Users;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import web.pages.rambler.LoginPage;
import web.tests.BaseTest;

@DisplayName("Тесты на юзер профайл")
@Feature("User profile feature")
public class ProfileTest extends BaseTest {

    @Test
    @DisplayName("Успешное открытие юзер профайла")
    @Story("JIRA-3")
    public void openProfile(){
        Users.User user = Users.RAMBLER_STANDARD_USER.getUser();
        Assertions.assertTrue(
                new LoginPage(driver.get())
                        .loginAs(user.getUsername(), user.getPassword())
                        .openProfile()
                        .isProfileOpened()
        );
    }
}
