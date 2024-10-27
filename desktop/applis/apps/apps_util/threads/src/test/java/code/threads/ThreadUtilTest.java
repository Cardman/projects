package code.threads;

import code.mock.*;
import org.junit.Test;

public final class ThreadUtilTest extends EquallableThreadUtil {
    @Test
    public void sleep1() {
        assertFalse(ThreadUtil.sleep(new MockThreadFactory(new MockTrueRand(),fileSet("/")),-1));
    }
    @Test
    public void sleep2() {
        assertFalse(ThreadUtil.sleep(new MockThreadFactory(new MockTrueRand(),fileSet("/")),0));
    }
    @Test
    public void sleep3() {
        assertFalse(ThreadUtil.sleep(new MockThreadFactory(new MockFalseRand(),fileSet("/")),1));
    }
    @Test
    public void sleep4() {
        assertTrue(ThreadUtil.sleep(new MockThreadFactory(new MockTrueRand(),fileSet("/")),1));
    }
}
