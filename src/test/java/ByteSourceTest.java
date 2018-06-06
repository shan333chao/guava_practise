import com.google.common.io.ByteSource;
import com.google.common.io.Files;
import org.junit.Test;

import java.io.File;
import java.io.IOException;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ByteSourceTest {
    private final  String path="C:\\Users\\HenDiao\\Desktop\\winmine.exe";
    @Test
    public void testAsByteSource() throws IOException {
    File file=new File(path);
        ByteSource byteSource = Files.asByteSource(file);
        byte[] read = byteSource.read();
        assertThat(read,is(Files.toByteArray(file)));


    }


    @Test
    public void  testSliceByteSource() throws IOException {
        ByteSource wrap = ByteSource.wrap(new byte[]{ 0x01,0x04,0x05,0x7,0x07,0x08,0x09});
        ByteSource slice = wrap.slice(5, 7);
        byte[] read = slice.read();
        for ( byte b :read){
            System.out.println(b);
        }


    }
}
