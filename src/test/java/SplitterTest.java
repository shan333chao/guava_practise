import com.google.common.base.Splitter;
import org.junit.Test;

import java.util.List;
import java.util.Map;


import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class SplitterTest {
    @Test
    public void testSplitter() {
        List<String> strings = Splitter.on("|").splitToList("Hello|World");
        System.out.println(strings);
        assertThat(strings, notNullValue());
        assertThat(strings.size(), equalTo(2));
        assertThat(strings.get(0), equalTo("Hello"));
        assertThat(strings.get(1), equalTo("World"));
    }

    /**
     * 拆分过滤空字符
     */

    @Test
    public void testSplit_On_Split_OmitEmpty() {

        List<String> strings = Splitter.on("|").omitEmptyStrings().splitToList("Hello|World|| | |");
        System.out.println(strings);
        assertThat(strings, notNullValue());
        assertThat(strings.size(), equalTo(4));
        assertThat(strings.get(0), equalTo("Hello"));
        assertThat(strings.get(1), equalTo("World"));
    }

    /**
     * 拆分后trim 过滤空字符
     */
    @Test
    public void testSplit_On_Split_TrimOmitEmpty() {

        List<String> strings = Splitter.on("|").trimResults().omitEmptyStrings().splitToList("Hello|World|| | |");
        System.out.println(strings);
        assertThat(strings, notNullValue());
        assertThat(strings.size(), equalTo(2));
        assertThat(strings.get(0), equalTo("Hello"));
        assertThat(strings.get(1), equalTo("World"));
    }

    /**
     * 按长度 拆分
     */
    @Test
    public void testSplitFixLength() {
        List<String> result = Splitter.fixedLength(2).splitToList("aaaabbbbccccdddd");
        System.out.println(result);
        assertThat(result, notNullValue());
        assertThat(result.size(), equalTo(8));
        assertThat(result.get(0), equalTo("aa"));
        assertThat(result.get(3), equalTo("bb"));
    }

    @Test
    public void testSplitOnLimit() {
        List<String> result = Splitter.on("@").limit(3).splitToList("123@456@789#@097@ddd@ttt");
        System.out.println(result);
        assertThat(result, notNullValue());
        assertThat(result.size(), equalTo(3));
        assertThat(result.get(0), equalTo("123"));
        assertThat(result.get(1), equalTo("456"));
    }

    @Test
    public void testSplitOnPatternString() {
        List<String> result = Splitter.onPattern("\\s").limit(4).omitEmptyStrings().splitToList("123 456    789 c097 @ddd @ttt");
        System.out.println(result);
        assertThat(result, notNullValue());

    }


    @Test
    public void testSplitOnSplitToMap() {
        Map<String, String> split = Splitter.on(",").omitEmptyStrings().withKeyValueSeparator(":").split("name:haha,age:123,pass:ddd,status:11");
        System.out.println(split);
        assertThat(split, notNullValue());

    }

}
