import org.apache.commons.io.FileUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Locale;

public class Response implements ServletResponse {

    Request request;
    OutputStream output;
    PrintWriter writer;

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
        if ("/".equals(uri)) {
            uri = "/index.html";
        }

        try {
            File file = new File(Constants.WEB_ROOT, uri);
            if (file.exists()) {
                String response = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: text/html\r\n"
                        + "Content-Length: " + file.length() + "\r\n" + "\r\n"
                        + FileUtils.readFileToString(file, Charset.forName("UTF-8"));

                output.write(response.getBytes());
            } else {
                file = new File(Constants.WEB_ROOT, "/404.html");
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

    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        writer = new PrintWriter(output, true);
        return writer;
    }

    @Override
    public void setCharacterEncoding(String s) {

    }

    @Override
    public void setContentLength(int i) {

    }

    @Override
    public void setContentLengthLong(long l) {

    }

    @Override
    public void setContentType(String s) {

    }

    @Override
    public void setBufferSize(int i) {

    }

    @Override
    public int getBufferSize() {
        return 0;
    }

    @Override
    public void flushBuffer() throws IOException {

    }

    @Override
    public void resetBuffer() {

    }

    @Override
    public boolean isCommitted() {
        return false;
    }

    @Override
    public void reset() {

    }

    @Override
    public void setLocale(Locale locale) {

    }

    @Override
    public Locale getLocale() {
        return null;
    }
}
