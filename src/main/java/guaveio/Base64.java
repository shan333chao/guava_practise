package guaveio;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;

import java.util.List;

/**
 * Created by HenDiao on 2017/11/25.
 */
public final class Base64 {

    private final static String CODE_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    private final static char[] CODE_DICT = CODE_STRING.toCharArray();

    private Base64() {
    }

    public static String encode(String plain) {
        Preconditions.checkNotNull(plain);
        StringBuilder builder = new StringBuilder();
        String s = toBinary(plain);
        List<String> strings = Splitter.fixedLength(6).splitToList(s);
        for (String item : strings) {
            if (item.length() < 6) {
                item = Strings.padEnd(item, 6, '0');
            }
            char encodeChat = CODE_DICT[Integer.valueOf(item, 2)];
            builder.append(encodeChat);
        }
        int delData = 6 - strings.get(strings.size() - 1).length();
        if (delData != 6) {
            for (int i = 0; i < delData / 2; i++) {
                builder.append("=");
            }
        }
        return builder.toString();

    }



    private static String toBinary(final String source) {
        final StringBuilder binaryResult = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {

            String charBinary = Integer.toBinaryString(source.charAt(i));
            binaryResult.append(Strings.padStart(charBinary, 6, '0'));

        }

        return binaryResult.toString();

    }
}
