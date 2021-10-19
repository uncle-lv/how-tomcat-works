import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

@Slf4j
public class Response {

    Request request;
    OutputStream output;

    public Response(OutputStream output) {
        this.output = output;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    // I modified this method with Apache Commons IO to make it more easily to read
    public void sendStaticResource() throws IOException {
        FileInputStream fileInputStream = null;

        String uri = request.getUri();

        switch (uri) {
            case "/":
                uri = "/index.html";
                break;
            case "/SHUTDOWN":
                uri = "shutdown.html";
                break;
            default:
                ;
        }

        try {
            File file = new File(HttpServer.WEB_ROOT, uri);
            if (file.exists()) {
                String response = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: text/html\r\n"
                        + "Content-Length: " + file.length() + "\r\n" + "\r\n"
                        + FileUtils.readFileToString(file, Charset.forName("UTF-8"));

                output.write(response.getBytes());
            } else {
                file = new File(HttpServer.WEB_ROOT, "/404.html");
                String errMsg = "HTTP/1.1 404 Not Found\r\n"
                        + "Content-Type: text/html\r\n"
                        + "Content-Length: " + file.length() + "\r\n" + "\r\n"
                        + FileUtils.readFileToString(file, Charset.forName("UTF-8"));

                output.write(errMsg.getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        }
    }
}
