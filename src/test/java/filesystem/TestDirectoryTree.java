/*
 In-memory Filesystem
 Scott Rice
*/

package filesystem;

import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.testng.Assert.*;

public class TestDirectoryTree {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Test
    public void TestSampleInput() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();

        DirectoryTree.main(new File(s + "/src/test/java/filesystem/test-input"));
        assertEquals("/school\n" +
                "math history spanish\n" +
                "/school/homework\n" +
                "homework cheatsheet\n" +
                "/", outContent.toString());

        System.setOut(null);
        System.setErr(null);
    }
}
