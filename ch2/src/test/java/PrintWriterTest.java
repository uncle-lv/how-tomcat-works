import org.junit.jupiter.api.Test;

import java.io.PrintWriter;

// Attention: The parameter autoFlush doesn't flush the output buffer of the methods print() and write().
// There is the Javadocs: https://docs.oracle.com/javase/6/docs/api/java/io/PrintWriter.html#PrintWriter(java.io.OutputStream,%20boolean)
public class PrintWriterTest {

    private static PrintWriter printWriter = new PrintWriter(System.out, true);

    @Test
    public void testPrint() {
        printWriter.print("print");
    }

    @Test
    public void testPrintln() {
        printWriter.println("println");
    }

    @Test
    public void testPrintf() {
        printWriter.printf("%s\n", "printf");
    }

    @Test
    public void testFormat() {
        printWriter.format("%s\n", "format");
    }

    @Test
    public void testWrite() {
        printWriter.write("write");
    }

    @Test
    public void testPrintWithFlush() {
        printWriter.print("print");
        printWriter.flush();
    }

    @Test
    public void testWriteWithFlush() {
        printWriter.write("write");
        printWriter.flush();
    }
}
