import com.google.common.io.ByteSource;
import com.google.common.io.Closer;
import com.google.common.io.Files;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class CloserTest {
    private final  String path="C:\\Users\\HenDiao\\Desktop\\winmine.exe";
    @Test
    public void testCloser() throws IOException {
        File file=new File(path);
        ByteSource byteSource = Files.asByteSource(file);
        Closer closer=Closer.create();
        try {
            InputStream inputStream=closer.register(byteSource.openStream());
        } catch (IOException e) {
            throw  closer.rethrow(e);
        }finally {
            closer.close();
        }

    }
    @Test(expected = RuntimeException.class)
    public void testTryCatchFinally(){
        try {
            System.out.println("work area :try");
            throw  new IllegalArgumentException();
        }catch (Exception e){
            System.out.println("work area :exception");
            throw  new RuntimeException();
        }
        finally {
            System.out.println("work area :Finally");
        }



    }

    @Test
    public void testTryCatch(){
        Throwable t=null;
        try {
            System.out.println("work area :try");
            throw  new RuntimeException("1");
        }catch (Exception e){
            System.out.println("work area :exception");
            t=e;
            throw  e;
        }finally {
            RuntimeException runtimeException =new RuntimeException("2");
            runtimeException.addSuppressed(t);
            throw  new RuntimeException("1");
        }



    }
}


