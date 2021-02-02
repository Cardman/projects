package code.util;

import org.junit.Test;

public final class EntryCustTest extends EquallableExUtil {
    @Test
    public void simpleTest() {
        EntryCust<Integer,Integer> e_ = new EntryCust<Integer, Integer>(1,2);
        assertEq(1, e_.getKey());
        assertEq(2, e_.getValue());
    }
}
