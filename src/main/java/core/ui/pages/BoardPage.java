package core.ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.*;

public class BoardPage {
    @Getter
    private ElementsCollection cardLists = $$(".js-list");
    private static ElementsCollection cards = $$(".js-card-name");
    private static ElementsCollection createCardButtons = $$(".js-add-a-card");
    private static SelenideElement cardTitleTextArea = $(".list-card-composer-textarea");
    private static SelenideElement confirmSelector = $(".confirm");

    public ElementsCollection getCardsFromList(int listNumber) {
        return cardLists.get(listNumber).findAll(".js-card-name");
    }

    public BoardPage createCardButtonClick(int listNumber) {
        createCardButtons.get(listNumber).click();
        return this;
    }

    public BoardPage typeCardTitle(String text) {
        cardTitleTextArea.sendKeys(text);
        return this;
    }

    public BoardPage addCardButtonClick(){
        confirmSelector.click();
        return this;
    }

    public BoardPage moveCardNextList(int cardNumber, int listNumber){
        actions().dragAndDrop(cards.get(cardNumber), cardLists.get(listNumber)).perform();
        return this;
    }

    public BoardPage() {
    };
}
