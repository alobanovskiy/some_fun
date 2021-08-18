import core.api.authService.BoardService;
import core.api.entities.BoardQueryParams;
import core.api.entities.BoardResponse;
import org.testng.annotations.*;
import java.util.Arrays;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;
import static core.ui.pages.LoginPage.TOKEN;
import static core.ui.pages.Pages.*;
import static java.util.stream.Collectors.toList;

public class HeaderOptionsTests {

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
        var boardsList = Arrays.stream(BoardService.getBoards(TOKEN)).filter(board -> board.closed == false).collect(toList());
        boardForTests = boardsList.get(0);
        var params = BoardQueryParams
                .builder()
                .name("new_name")
                .desc("some description")
                .build();
        BoardService.updateBoard(TOKEN, boardForTests.id, params.makeQueryString());
    }

    @Test
    void testNotificationButton() {
        LOGIN_PAGE.loginFromUi();
        open(boardForTests.shortUrl);
        BOARD_HEADER
                .notificationButtonClick()
                .getPopoverTitle().shouldHave(exactText("Уведомления"));
    }

    @Test
    void testInfoButton() {
        LOGIN_PAGE.loginFromUi();
        open(boardForTests.shortUrl);
        BOARD_HEADER
                .infoButtonClick()
                .getPopoverTitle().shouldHave(exactText("Информация"));
    }

    @Test
    void testProfileButton() {
        LOGIN_PAGE.loginFromUi();
        open(boardForTests.shortUrl);
        BOARD_HEADER
                .profileButtonClick()
                .getPopoverTitle().shouldHave(exactText("Учетная запись"));
    }

    @Test
    void otherProductsButton() {
        LOGIN_PAGE.loginFromUi();
        open(boardForTests.shortUrl);
        BOARD_HEADER
                .otherAtlassianProductsClick()
                .getPopoverTitle().shouldHave(exactText("Другие продукты Atlassian"));
    }
}
