package Utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
    int statusCode;
    String responseBody;
    String statusLine;
}
