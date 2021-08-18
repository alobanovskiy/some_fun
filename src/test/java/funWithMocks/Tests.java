package funWithMocks;

import com.codeborne.selenide.Selenide;
import com.github.tomakehurst.wiremock.WireMockServer;
import core.api.authService.BoardService;
import core.api.entities.BoardQueryParams;
import core.api.entities.BoardResponse;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.testng.annotations.*;


import java.util.Arrays;

import static com.codeborne.selenide.Selenide.*;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static core.ui.pages.LoginPage.TOKEN;
import static core.ui.pages.Pages.*;
import static java.util.stream.Collectors.toList;
import static org.testng.Assert.assertTrue;

public class
Tests {

    private static BoardResponse boardForTests;

    @BeforeMethod
    public void createMocks() {
        WireMockServer wireMockServer = new WireMockServer(wireMockConfig().port(8080).httpsPort(8089)); //No-args constructor will start on port 8080, no HTTPS
        wireMockServer.start();
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

    @Test
    public void exampleTest() {
        LOGIN_PAGE.loginFromUi();
        stubFor(get("/1/Members/me?boards=open")
                .withHeader("Content-Type", containing("application/json"))
                .willReturn(badRequest()
                        .withHeader("Content-Type", "text/xml")
                        .withBody("{some sheet}")));
        open(boardForTests.shortUrl);
        BOARD_PAGE
                .createCardButtonClick(0)
                .typeCardTitle("ttes card")
                .addCardButtonClick()
                .getCardsFromList(0)
                .shouldHaveSize(1);
        verify(postRequestedFor(urlPathMatching("https://api-gateway.trello.com/gateway/api/gasv3/api/v1/batch")));
    }

    @SneakyThrows
    @Test
    public void example1() {
        open("https://www.youtube.com/");
        $(By.id("search")).sendKeys("редакция");
        $(By.id("search-icon-legacy")).click();
        stubFor(post("/youtubei/v1/search.*")
                .withHeader("Content-Type", containing("application/json"))
                .willReturn(badRequest()
                        .withHeader("Content-Type", "text/xml")
                        .withBody("{some sheet}")));
        verify(postRequestedFor(urlPathMatching("/youtubei/v1/search?key=AIzaSyAO_FJ2SlqU8Q4STEHLGCilw_Y9_11qcW8")));
    }
}
