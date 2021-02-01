package code.util;

import org.junit.Test;

import static code.util.EquallableExUtil.assertEq;
import static org.junit.Assert.assertNotNull;

public final class EntryCustTest {
    @Test
    public void simpleTest() {
        EntryCust<Integer,Integer> e_ = new EntryCust<Integer, Integer>(1,2);
        assertEq(1, e_.getKey());
        assertEq(2, e_.getValue());
    }
}
