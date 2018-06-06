import com.google.common.collect.ImmutableList;
import com.google.common.io.CharSource;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class CharSourceTest {


    @Test
    public void  testChatSource() throws IOException {

        CharSource charSource=CharSource.wrap("are you ok, yes iam ");
        String resultAsRead=charSource.read();
        assertThat(resultAsRead,equalTo("are you ok, yes iam "));
        ImmutableList<String> strings = charSource.readLines();
        assertThat(strings.size(),equalTo(1));
        assertThat(charSource.length(),equalTo(20L));
        assertThat(charSource.isEmpty(),equalTo(false));
        assertThat(charSource.lengthIfKnown().get(),equalTo(20L));

    }


    @Test
    public void testConcat() throws IOException {
        CharSource concat = CharSource.concat(

                CharSource.wrap("1、are you ok, yes iam "),
                CharSource.wrap("2、 are you ok, yes iam ")

        );
        System.out.println(concat.readLines().size());
            concat.lines().forEach(System.out::println);

    }
}
