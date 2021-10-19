import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class Request {

    private static final int BUFFER_SIZE = 2048;

    private InputStream input;
    private String method;
    private String uri;
    private String protocol;

    public Request(InputStream input) {
        this.input = input;
    }

    public String getUri() {
        return uri;
    }

    public void parse() {
        StringBuffer request = new StringBuffer(BUFFER_SIZE);
        int i;
        byte[] buffer = new byte[BUFFER_SIZE];

        try {
            i = input.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            i = -1;
        }

        for (int j = 0; j < i; j++) {
            request.append((char) buffer[j]);
        }

        // Comment out the following line, if you want to print requests
        // log.info("\n{}", request.toString());
        String[] requestLineInfo = parseRequestLine(request.toString());
        method = requestLineInfo[0];
        uri = requestLineInfo[1];
        protocol = requestLineInfo[2];

        log.info("{} - {} - {}", method, uri, protocol);
    }

    // This is the parse method of How Tomcat works, but I don't use it, because it only parse the uri
    private String parseUri(String requestStr) {
        int firstBlank, secondBlank;

        firstBlank = requestStr.indexOf(' ');
        if (firstBlank != -1) {
            secondBlank = requestStr.indexOf(' ', firstBlank + 1);
            if (secondBlank > firstBlank) {
                return requestStr.substring(firstBlank + 1, secondBlank);
            }
        }
        return null;
    }

    // This is the parse method which I defined, it parses the request line
    private String[] parseRequestLine(String requestStr) {
        String requestLine = requestStr.split("\r\n")[0];
        String[] requestLineInfo = requestLine.split(" ");
        return requestLineInfo;
    }
}
