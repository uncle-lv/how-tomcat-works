import org.junit.jupiter.api.Test;

public class HttpServer2Test {

    @Test
    public void testHttpServer2() {
        new HttpServer2(8080).await();
    }
}
