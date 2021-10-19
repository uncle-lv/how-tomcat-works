import org.junit.jupiter.api.Test;

public class HttpServer1Test {

    @Test
    public void testHttpServer1() {
        new HttpServer1(8080).await();
    }
}
