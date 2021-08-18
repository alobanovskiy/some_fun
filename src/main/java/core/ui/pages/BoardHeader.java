package core.ui.pages;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.*;

public class BoardHeader {

    private static SelenideElement profileHeaderButton = $x("//*[@id=\"header\"]/div[2]/button[4]");
    private static SelenideElement notificationHeaderButton = $x("//*[@id=\"header\"]/div[2]/button[3]");
    private static SelenideElement infoHeaderButton = $x("//*[@id=\"header\"]/div[2]/button[2]");
    private static SelenideElement createHeaderButton = $x("//*[@id=\"header\"]/div[2]/button[1]");
    private static SelenideElement otherAtlassianProducts = $x("//*[@id=\"header\"]/div[1]/div[1]/div/div/button");
    @Getter
    private SelenideElement popoverTitle = $("section").find("header");

    public BoardHeader profileButtonClick() {
        profileHeaderButton.click();
        return this;
    }

    public BoardHeader notificationButtonClick() {
        notificationHeaderButton.click();
        return this;
    }

    public BoardHeader infoButtonClick() {
        infoHeaderButton.click();
        return this;
    }

    public BoardHeader otherAtlassianProductsClick() {
        otherAtlassianProducts.click();
        return this;
    }

    BoardHeader() {
    }
}
