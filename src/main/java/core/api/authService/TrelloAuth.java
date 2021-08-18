package core.api.authService;

import core.api.Service;
import lombok.SneakyThrows;
import org.apache.hc.core5.net.URIBuilder;

import java.net.URI;

public class TrelloAuth extends Service {

    @SneakyThrows
    protected static URI getAuthURI() {
        var path = "/1/authorize";
        return new URIBuilder()
                .setScheme(SCHEME)
                .setHost(HOST)
                .setPath(path)
                .setParameter("expiration", "never")
                .setParameter("name", "MyPersonalToken")
                .setParameter("scope", "read,write,account")
                .setParameter("response_type", "token")
                .setParameter("key", API_KEY)
                .build();
    }
}
