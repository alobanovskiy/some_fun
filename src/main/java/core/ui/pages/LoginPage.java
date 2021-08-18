package core.ui.pages;

import com.codeborne.selenide.*;
import lombok.SneakyThrows;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static core.api.Service.MEMBER_ID;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class LoginPage {

    private static final String LOGIN_URL = "https://trello.com/login";
    private static final String LOGIN = "lobanovsky.a.o@gmail.com";
    public static final String TOKEN = "b896236d87840cda0c6ba41754e5853751ced30bec0a90e8e072736cdb4c2df8";
    private static final String PASSWORD = "Test1234";
    
    private SelenideElement emailField = $(byId("user"));
    private SelenideElement passwordField = $(byId("password"));
    private SelenideElement moveToAtlassianIdButton = $(byId("login"));
    private SelenideElement loginButton = $(byId("login-submit"));

    @SneakyThrows
    public void loginFromUi() {
        open(LOGIN_URL);
        emailField.sendKeys(LOGIN);
        moveToAtlassianIdButton.click();
        Thread.sleep(1000);
        passwordField.waitUntil(exist, 5000).sendKeys(PASSWORD);
        loginButton.click();
        Wait().until(urlContains(MEMBER_ID));
    }

    LoginPage() {
    }

}
