import com.google.common.io.BaseEncoding;
import com.google.common.io.CharSource;
import guaveio.Base64;
import org.junit.Test;

/**
 * Created by HenDiao on 2017/11/25.
 */
public class BaseEncodingTest {

    @Test
    public void testBase64Encod(){
        BaseEncoding baseEncoding = BaseEncoding.base64();
        String encode = baseEncoding.encode("Hello".getBytes());
        System.out.println(encode);
    }

    @Test
    public void testBase64Dencod(){
        BaseEncoding baseEncoding = BaseEncoding.base64();
        System.out.println(new String( baseEncoding.decode("SGVsbG8=")));

    }


    @Test
    public void testMyBase64Encod(){
        BaseEncoding baseEncoding = BaseEncoding.base64();
        String encode = baseEncoding.encode("hello".getBytes());
        System.out.println(encode);
        String hello = Base64.encode("hello");
        System.out.println(hello);

    }

}
