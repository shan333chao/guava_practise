import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.Files;
import org.junit.Test;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class JoinerTest {

    private static final List<String> list = Arrays.asList("Sandy", "joyin", "Rick", "Andy", "sara");
    private static final List<String> listWithNull = Arrays.asList("Sandy", "joyin", "Rick", null, "sara");
    private static final  String targetFileName="D:\\MyCode\\joinertest.txt";
    public static final String  targetFileNameToMap="D:\\MyCode\\joinerMaptest.txt";
    public static final Map<String,String> keyValue= ImmutableMap.of("Sandy", "joyin", "Rick", "Andy", "sara","333");
    public static final String DEFAULT_VALUE="default";
    /**
     * 把数组按分隔符拼接成字符串
     */
    @Test
    public void testJoinOnJoin() {
        String result = Joiner.on(",").join(list);
        System.out.println(result);
        assertThat(result, equalTo("Sandy,joyin,Rick,Andy,sara"));
    }

    /**
     * 把数组按分隔符拼接并排除null 值skipNulls
     */
    @Test
    public void testJoinOnJoinButSkipNull() {
        String result = Joiner.on(",").skipNulls().join(listWithNull);
        System.out.println(result);
        assertThat(result, equalTo("Sandy,joyin,Rick,sara"));
    }

    /**
     * 把数组按分隔符拼接 并为null设置默认值useForNull
     */
    @Test
    public void testJoinOnJoinWithDefaultValueForNull() {
        String result = Joiner.on(",").useForNull(DEFAULT_VALUE).join(listWithNull);
        System.out.println(result);
        assertThat(result, equalTo("Sandy,joyin,Rick,default,sara"));
    }


    @Test
    public void testJoin_On_Append_To_StringBuilder() throws IOException {
        final StringBuffer builder = new StringBuffer();
        StringBuffer resultBuilder = Joiner.on(",").useForNull(DEFAULT_VALUE).appendTo(builder, listWithNull);
        System.out.println(resultBuilder.toString());
        assertThat(resultBuilder, sameInstance(builder));
        assertThat(resultBuilder.toString(), equalTo("Sandy,joyin,Rick,default,sara"));
    }

    /**
     * Append to FileWriter
     */
    @Test
    public void testJoin_on_Append_toWriter(){
        try (FileWriter writer=new FileWriter(new File(targetFileName))){
            Joiner.on("#").useForNull(DEFAULT_VALUE).appendTo(writer,listWithNull);
            assertThat(Files.isFile().test(new File(targetFileName)),equalTo(true));
        } catch (IOException e) {
            e.printStackTrace();
            fail("append to the writer occur fetal error.");
        }
    }
    @Test
    public void testJoiningByStream(){
        String collect = listWithNull.stream().filter(p -> p != null&&!p.isEmpty()).collect(Collectors.joining(","));
        System.out.println(collect);
        assertThat(collect, equalTo("Sandy,joyin,Rick,sara"));

    }
    @Test
    public void testJoiningByStreamWithDefaultValueForNull(){
        String collect = listWithNull.stream().map(s->(s==null||s.isEmpty())?DEFAULT_VALUE:s).collect(Collectors.joining(","));
        System.out.println(collect);
        assertThat(collect, equalTo("Sandy,joyin,Rick,default,sara"));

    }

    @Test
    public void testJoinOnWithMap(){
        try (FileWriter writer=new FileWriter(new File(targetFileNameToMap))){
            Joiner.on(",").withKeyValueSeparator(":").appendTo(writer,keyValue);
            assertThat(Files.isFile().test(new File(targetFileNameToMap)),equalTo(true));
        } catch (IOException e) {
            e.printStackTrace();
            fail("append to the writer occur fetal error.");
        }

    }

}
