package functional;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Preconditions;

import javax.annotation.Nullable;

/**
 * Created by HenDiao on 2017/10/12.
 */
public class FunctionException {
    public static void main(String[] args) {
        Function<String, Integer> function = new Function<String, Integer>() {
            @Nullable
            @Override
            public Integer apply(@Nullable String input) {
                Preconditions.checkNotNull(input, "input stream should not be null");
                return input.length();
            }
        };
        System.out.println(function.apply("2222"));

        Function<String, Double> compose = Functions.compose(new Function<Integer, Double>() {
                                                               @Nullable
                                                               @Override
                                                               public Double apply(@Nullable Integer input) {

                                                                   return  input*1d;
                                                               }
                                                           }, new Function<String, Integer>() {
                                                               @Nullable
                                                               @Override
                                                               public Integer apply(@Nullable String input) {
                                                                   return input.length();
                                                               }
                                                           }
        );
        ;
        System.out.println( compose.apply("33"));
    }
}
