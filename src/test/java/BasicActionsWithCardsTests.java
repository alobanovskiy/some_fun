import core.api.authService.BoardService;
import core.api.entities.BoardQueryParams;
import core.api.entities.BoardResponse;
import lombok.SneakyThrows;
import org.testng.annotations.*;

import java.util.Arrays;

import static com.codeborne.selenide.Selenide.*;
import static core.ui.pages.LoginPage.TOKEN;
import static core.ui.pages.Pages.*;
import static java.util.stream.Collectors.toList;

public class BasicActionsWithCardsTests {

    private static BoardResponse boardForTests;

    @AfterMethod
    private void afterTest() {
        closeWebDriver();
    }

    @BeforeClass
    private void beforeClass() {
        BoardResponse[] boards = BoardService.getBoards(TOKEN);
        var boardIdList = Arrays.stream(boards)
                .filter(board->!board.name.equals("my dear board"))
                .map(board -> board.id)
                .collect(toList());
        BoardService.deleteBoards(TOKEN, boardIdList);
        BoardService.createBoard(TOKEN, "test Board");
        var boardsList = Arrays.stream(BoardService.getBoards(TOKEN))
                .filter(board->!board.name.equals("my dear board"))
                .filter(board -> board.closed == false)
                .collect(toList());
        boardForTests = boardsList.get(0);
        var params = BoardQueryParams
                .builder()
                .name("new_name")
                .desc("some description")
                .build();
        BoardService.updateBoard(TOKEN, boardForTests.id, params.makeQueryString());
    }

    @SneakyThrows
    @Test
    void test() {
        LOGIN_PAGE.loginFromUi();
        open(boardForTests.shortUrl);
        BOARD_PAGE
                .getCardLists()
                .shouldHaveSize(3);
        BOARD_PAGE
                .getCardsFromList(0)
                .shouldHaveSize(0);
        BOARD_PAGE
                .createCardButtonClick(0)
                .typeCardTitle("ttes card")
                .addCardButtonClick()
                .getCardsFromList(0)
                .shouldHaveSize(1);
        BOARD_PAGE
                .moveCardNextList(0, 1)
                .getCardsFromList(0)
                .shouldHaveSize(0);
        BOARD_PAGE
                .moveCardNextList(0, 1)
                .getCardsFromList(1)
                .shouldHaveSize(1);

    }
}
