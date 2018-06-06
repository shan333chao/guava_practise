import com.google.common.io.ByteSink;
import com.google.common.io.Files;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ByteSinkTest {
    private final  String path="C:\\Users\\HenDiao\\Desktop\\winmine.exe2";

    @Test
    public  void testByteSink() throws IOException {
        File  file =new File(path);
        file.deleteOnExit();
        ByteSink byteSink= Files.asByteSink(file);
        byte[] bytes={0x01,0x02};
        byteSink.write(bytes);
        byte[] bytes1 = Files.toByteArray(file);
        assertThat(bytes1,is(bytes));

    }
}
