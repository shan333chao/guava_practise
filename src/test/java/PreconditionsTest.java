import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import jdk.nashorn.internal.ir.annotations.Immutable;
import org.junit.Test;

import java.util.List;
import java.util.Objects;

/**
 * Preconditions
 * Objects
 * assert key word (statement)
 */
public class PreconditionsTest {

    public void testCheckNotNull(final List<String> list) {
        Preconditions.checkNotNull(list);

    }

    @Test(expected = NullPointerException.class)
    public void testCheckNotNull(){
        testCheckNotNull(null);
    }

    public void testCheckNotNullWithMsg(final List<String> list) {
      //  Objects.requireNonNull(list,"Object is null");
         Preconditions.checkNotNull(list,"list must be not empty");
    }
    @Test
    public void testCheckNotNullWithMsg(){

        testCheckNotNullWithMsg(null);
    }


    public void  checkNotNullWithFormatMsg(final List<String> list) {

        Preconditions.checkNotNull(list,"list should not be null and the size must be %s",2);
    }

    @Test
    public void testCheckNotNullWithFormatMsg(){
        checkNotNullWithFormatMsg(null);
    }


    @Test
    public void testCheckArguments(){
        final String type="555";
       Preconditions.checkArgument(type.equals("666"));
    }

    @Test
    public void testCheckStatus(){
        final String status="a";
        Preconditions.checkState(status.equals("v"),"the state is illegal.");
    }


    @Test
    public void testCheckIndex(){
        final String status="a";
        List<Integer> list= ImmutableList.of(10,11);
        Preconditions.checkElementIndex(10,list.size());
    }

}
