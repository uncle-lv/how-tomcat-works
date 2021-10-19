import org.junit.jupiter.api.Test;

public class HttpServerTest {

    @Test
    public void testServletProcessor1() {
        new HttpServer(8080, new ServletProcessor1()).await();
    }

    @Test
    public void testServletProcessor2() {
        new HttpServer(8080, new ServletProcessor2()).await();
    }

    @Test
    public void testStaticResourceProcessor() {
        new HttpServer(8080, new StaticResourceProcessor()).await();
    }
}
