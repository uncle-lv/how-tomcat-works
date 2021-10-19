import org.junit.jupiter.api.Test;

public class HttpServerTest {

    @Test
    public void testHttpServer() {
        new HttpServer(8080).await();
    }
}
