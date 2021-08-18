package core.api.entities;

import lombok.Builder;
import lombok.SneakyThrows;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.message.BasicNameValuePair;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Builder
public class BoardQueryParams {

    public String name;
    public String desc;
    public boolean closed;
    public String subscribed;
    public String idOrganization;

    @SneakyThrows
    public List<NameValuePair> makeQueryString() {
        List<NameValuePair> params = new ArrayList<>();
        Field[] fields = BoardQueryParams.class.getDeclaredFields();
        for (Field field : fields) {
            if (field.get(this) != null) {
                field.setAccessible(true);
                Object value = field.get(this);
                params.add(new BasicNameValuePair(field.getName(), value.toString()));
            }
        }
        return params;
    }
}
