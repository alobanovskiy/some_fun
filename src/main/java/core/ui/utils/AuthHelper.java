package core.ui.utils;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import core.api.authService.TrelloAuth;
import org.testng.Assert;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static core.ui.pages.Pages.LOGIN_PAGE;

public class AuthHelper extends TrelloAuth {

    static SelenideElement approveButton = $(byId("approveButton"));
    static SelenideElement tokenField = $(byCssSelector("pre"));

    public static String getTokenForApp() {
        LOGIN_PAGE.loginFromUi();
        var uri = TrelloAuth.getAuthURI();
        open(uri.toString());
        approveButton.waitUntil(Condition.enabled, 5000);
        approveButton.scrollIntoView(true).click();
        tokenField.waitUntil(Condition.visible, 5000);
        Assert.assertFalse(tokenField.getText().equals(""));
        return tokenField.getText();
    }
}
