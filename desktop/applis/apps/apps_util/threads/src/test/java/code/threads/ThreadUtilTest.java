package code.threads;

import code.maths.montecarlo.*;
import code.mock.*;
import org.junit.Test;

public final class ThreadUtilTest extends EquallableThreadUtil {
    @Test
    public void sleep1() {
        assertFalse(ThreadUtil.sleep(new MockThreadFactory(DefaultGenerator.oneElt(),fileSet("/")),-1));
    }
    @Test
    public void sleep2() {
        assertFalse(ThreadUtil.sleep(new MockThreadFactory(DefaultGenerator.oneElt(),fileSet("/")),0));
    }
    @Test
    public void sleep3() {
        assertFalse(ThreadUtil.sleep(new MockThreadFactory(gene(0.25),fileSet("/")),1));
    }
    @Test
    public void sleep4() {
        assertTrue(ThreadUtil.sleep(new MockThreadFactory(gene(0.75),fileSet("/")),1));
    }
}
