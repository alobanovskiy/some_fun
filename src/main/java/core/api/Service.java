package core.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import static com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT;

public class Service {

    public static final String SCHEME = "https";
    public static final String API_HOST = "api.trello.com";
    public static final String HOST = "trello.com";
    public static final String API_KEY = "668d40dcdbbae1aca49d49025501071e";
    public static final String MEMBER_ID = "aleksandr956";
    protected static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();
    protected static final ObjectMapper MAPPER = new ObjectMapper().configure(INDENT_OUTPUT, true);

    protected static void checkResponseCode(HttpResponse response) {
        if (response.statusCode() >= 200 && response.statusCode() < 210) {
            return;
        }
        System.out.println("you got incorrect response: " + response.body() + " " + response.statusCode());
        throw new RuntimeException();
    }
}
