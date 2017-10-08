import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import org.junit.Test;

import java.nio.charset.Charset;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public class StringsTest {
    @Test
    public void testStringsMethod() {
        ///把empty 设成null
       assertThat(Strings.emptyToNull(""),nullValue());
       String aa="123";
       ///把null设为empty
        System.out.println(Strings.nullToEmpty(aa));
        aa=null;
        System.out.println("nullStr"+ Strings.nullToEmpty(null));
        ///查找公共前缀
        assertThat(Strings.commonPrefix("sandyshan","sHitshan"),equalTo("s"));
        ///查找公共后缀
        assertThat(Strings.commonSuffix("sandyshan","Hitshan"),equalTo("shan"));
        ///生成重复字符
        assertThat(Strings.repeat("shan",3),equalTo("shanshanshan"));
        assertThat(Strings.isNullOrEmpty(""),equalTo(true));
        assertThat(Strings.padStart("baramongan",12,'。'),equalTo("。。baramongan"));
        assertThat(Strings.padEnd("baramongan",12,'。'),equalTo("baramongan。。"));

    }
    @Test
    public  void testChatsets(){
        Charset chatset=Charset.forName("UTF-8");
        assertThat(Charsets.UTF_8,equalTo(chatset));
    }
    @Test
    public  void testCharMatcher(){
        assertThat( CharMatcher.javaDigit().matches('3'),equalTo(true));
        assertThat( CharMatcher.javaDigit().matches('a'),equalTo(false));
        /// 计算一个字母在一个字符串中出现的次数
        int countChar = CharMatcher.is('a').countIn("baramongan learning guava lib");
        assertThat(countChar,equalTo(6));
        ///空格中替换成指定字符 只替换一次
        String s = CharMatcher.breakingWhitespace().collapseFrom(" baramongan   learning guava    lib ", '*');
        System.out.println(s);
        assertThat(s,equalTo("*baramongan*learning*guava*lib*"));
        ///去除空格和数字removeFrom
        String s1 = CharMatcher.javaDigit().or(CharMatcher.whitespace()).removeFrom(" baramongan 123  learning 456 guava  ");
        assertThat(s1,equalTo("baramonganlearningguava"));
        ///保留大写字母和数字retainFrom
        String s2 = CharMatcher.javaDigit().or(CharMatcher.javaUpperCase()).retainFrom("Baramongan 123  Learning 456 Guava");
        assertThat(s2,equalTo("B123L456G"));
    }



}
