import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.collect.FluentIterable;
import com.google.common.hash.Funnels;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.common.io.CharSink;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;
import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by HenDiao on 2017/10/14.
 */
public class FilesTest {

    private final String SOURCE_FILE = "D:\\MyCode\\javacode\\guava_practise\\src\\main\\resource\\source.txt";
    private final String TARGET_FILE = "D:\\MyCode\\javacode\\guava_practise\\src\\main\\resource\\target.txt";

    /**
     * TODO sandy will finish this in the future,
     */

    @Test
    public void testCopyFileWithGuave() throws IOException {
        File targetFile = new File(TARGET_FILE);
        Files.copy(new File(SOURCE_FILE), new File(TARGET_FILE));
        assertThat(targetFile.exists(), equalTo(true));
    }

    @Test
    public void testCopyFileWithJdkNio2() throws IOException {
        java.nio.file.Files.copy(Paths.get("D:\\MyCode\\javacode\\guava_practise\\src\\main", "resource", "source.txt"),
                Paths.get("D:\\MyCode\\javacode\\guava_practise\\src\\main", "resource", "target.text"),
                StandardCopyOption.REPLACE_EXISTING);
    }

    //执行一个unit test后 会在动执行 After注解
    @After
    public void tearDown() {
        File file = new File(TARGET_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testMoveFile() throws IOException {
        File file = new File(TARGET_FILE);
        try {
            Files.move(new File(SOURCE_FILE), new File(TARGET_FILE));

            assertThat(file.exists(), equalTo(true));
        } finally {
            Files.move(new File(TARGET_FILE), new File(SOURCE_FILE));
        }
    }

    /**
     * 读取文件
     *
     * @throws IOException
     */
    @Test
    public void testToString() throws IOException {
        final String expectedString = "today we will share the guave io knowledge\n" +
                "but only for the basic usage. if you wangted to get the more details information\n" +
                "please read the guava document or source code\n" +
                "\n" +
                "The Guave source code is very cleanly and nice.";
        File source = new File(SOURCE_FILE);
        List<String> strings = Files.readLines(new File(SOURCE_FILE), Charsets.UTF_8);
        String charSource = Files.asCharSource(source, Charsets.UTF_8).read();
        String result = Joiner.on("\n").join(strings);
        assertThat(result, equalTo(expectedString));
        assertThat(charSource, equalTo(result));
    }

    @Test
    public void testToProcessString() throws IOException {
        LineProcessor<List<Integer>> lineProcessor = new LineProcessor<List<Integer>>() {
            private final List<Integer> lengthList = new ArrayList<>();

            @Override
            public boolean processLine(String line) throws IOException {
                if (line.length() == 0) return false;
                lengthList.add(line.length());
                return false;
            }

            @Override
            public List<Integer> getResult() {
                return lengthList;
            }
        };
        File file = new File(SOURCE_FILE);
        String charSource;
        //charSource = Files.asCharSource(file,Charsets.UTF_8).readLines(lineProcessor);
    }


    @Test
    public void testFileMd5() throws IOException {
        File file = new File(SOURCE_FILE);
        HashCode hash1 = Files.hash(file, Hashing.goodFastHash(128));
        System.out.println(hash1);
        HashCode hash = Files.asByteSource(file).hash(Hashing.sha256());
        System.out.println(hash);
    }

    @Test
    public void testWriteFile() throws IOException {
        final String localfile = "D:\\MyCode\\javacode\\guava_practise\\src\\main\\resource\\source1.txt";
        File file = new File(localfile);
        // file.deleteOnExit();
        String content = "content 1";
        Files.asCharSink(file, Charsets.UTF_8, FileWriteMode.APPEND).write(content);
        //Files.toString(file,Charsets.UTF_8);
        String read = Files.asCharSource(file, Charsets.UTF_8).read();
        assertThat(read, equalTo(content));
        String content2 = "content22";
        Files.asCharSink(file, Charsets.UTF_8, FileWriteMode.APPEND).write(content2);
        String read2 = Files.asCharSource(file, Charsets.UTF_8).read();
        assertThat(read2, equalTo(content2));
    }


    @Test
    public void testFileAppend() throws IOException {
        final String localfile = "D:\\MyCode\\javacode\\guava_practise\\src\\main\\resource\\source1.txt";
        File file = new File(localfile);
        //  file.deleteOnExit();
        CharSink charSink = Files.asCharSink(file, Charsets.UTF_8, FileWriteMode.APPEND);
        charSink.write("\rcontent1");
        String read2 = Files.asCharSource(file, Charsets.UTF_8).read();
        assertThat(read2, equalTo("content1"));
    }

    /**
     * 创建一个空文件
     * @throws IOException
     */
    @Test
    public void testTouch() throws IOException {
        final String localfile = "D:\\MyCode\\javacode\\guava_practise\\src\\main\\resource\\touch.txt";
        File touchFile = new File(localfile);
        Files.touch(touchFile);

    }

    @Test
    public void testRecursive() {
        final String folderPath = "D:\\MyCode\\javacode\\guava_practise\\build";
        File file = new File(folderPath);
        List<File> files = new ArrayList<>();
        recusiveList(file, files);
        files.forEach(System.out::println);

    }

    private void recusiveList(File root, List<File> fileList) {
        if (root.isHidden()) {
            return;
        }
        fileList.add(root);
        if (!root.isFile()) {
            File[] files = root.listFiles();
            for (File f : files) {
                recusiveList(f, fileList);
            }
        }
    }

    /**
     * 遍历文件夹和文件
     */
    @Test
    public  void testTreeFiles(){
        final String folderPath = "D:\\MyCode\\javacode\\guava_practise\\build";
        File root = new File(folderPath);
        ///获取目录下所有文件
        FluentIterable<File> files = Files.fileTreeTraverser().preOrderTraversal(root).filter(File::isFile);
        files.stream().forEach(System.out::println);
        Iterable<File> children = Files.fileTreeTraverser().children(root);
        children.spliterator().forEachRemaining(System.out::println);

        HashCode hashCode = Hashing.goodFastHash(64).hashString("6666", Charsets.UTF_8);
        HashCode hashCode1 = Hashing.sha256().hashString("6666", Charsets.UTF_8);

        System.out.println(hashCode);
        System.out.println(hashCode1);

    }
}
