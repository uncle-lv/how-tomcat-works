package servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class PrimitiveServlet implements Servlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        log.info("{}: init...", getSimpleClassName());
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    // I tried sending the content with the code in the book, but got a invalid http response error.
    // So, I replaced it with a standard http response.
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        String response = "HTTP/1.1 200 OK\n\r"
                + "Content-Type: text/html\n\r"
                + "Content-Length: 17\n\r\n\r"
                + "PrimitiveServlet";

        log.info("{}: service...", getSimpleClassName());
        PrintWriter out = servletResponse.getWriter();
        out.println(response);
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        log.info("{}: destroy...", getSimpleClassName());
    }

    private static String getSimpleClassName() {
        return PrimitiveServlet.class.getSimpleName();
    }
}
