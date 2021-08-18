package core.api.authService;

import core.api.Service;
import core.api.entities.BoardResponse;
import lombok.SneakyThrows;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.net.URIBuilder;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

public class BoardService extends Service {


    @SneakyThrows
    public static BoardResponse[] getBoards(String token) {
        var path = "/1/members/" + MEMBER_ID + "/boards";
        var uri = new URIBuilder()
                .setScheme(SCHEME)
                .setHost(API_HOST)
                .setPath(path)
                .setParameter("token", token)
                .setParameter("key", API_KEY)
                .build();
        var request = HttpRequest.newBuilder().uri(uri).build();
        var response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return MAPPER.readValue(response.body(), BoardResponse[].class);
    }

    @SneakyThrows
    public static void deleteBoards(String token, List boardIdList) {
        boardIdList.stream().forEach(id -> deleteBoard(token, id.toString()));
    }

    @SneakyThrows
    public static void deleteBoard(String token, String boardId) {
        var path = "/1/boards/" + boardId;
        var uri = new URIBuilder()
                .setScheme(SCHEME)
                .setHost(API_HOST)
                .setPath(path)
                .setParameter("token", token)
                .setParameter("key", API_KEY)
                .build();
        var request = HttpRequest.newBuilder().DELETE().uri(uri).build();
        var response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        checkResponseCode(response);
    }

    @SneakyThrows
    public static void createBoard(String token, String boardName) {
        var path = "/1/boards/";
        var uri = new URIBuilder()
                .setScheme(SCHEME)
                .setHost(API_HOST)
                .setPath(path)
                .setParameter("token", token)
                .setParameter("key", API_KEY)
                .setParameter("name", boardName)
                .build();
        var request = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString("")).uri(uri).build();
        var response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        checkResponseCode(response);
    }

    @SneakyThrows
    public static void updateBoard(String token, String boardId, List<NameValuePair> params) {
        var path = "/1/boards/" + boardId;
        var uri = new URIBuilder()
                .setScheme(SCHEME)
                .setHost(API_HOST)
                .setPath(path)
                .setParameters(params)
                .setParameter("token", token)
                .setParameter("key", API_KEY)
                .build();
        var request = HttpRequest.newBuilder().PUT(HttpRequest.BodyPublishers.ofString("")).uri(uri).build();
        var response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        checkResponseCode(response);
    }

}
