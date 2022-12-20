package code.mock;

import code.threads.ThState;
import org.junit.Test;

public final class MockThreadTest extends EquallableMockGuiUtil {
    @Test
    public void t1() {
        MockThread th_ = new MockThread(null, false, new MockAtomicLong());
        assertEq(0,th_.getId());
    }
    @Test
    public void t2() {
        MockThread th_ = new MockThread(null, false, new MockAtomicLong());
        assertTrue(th_.setPriority(2));
        assertEq(2,th_.getPriority());
    }
    @Test
    public void t4() {
        MockThread th_ = new MockThread(null, false, new MockAtomicLong());
        th_.start();
        assertFalse(th_.isInvoked());
    }
    @Test
    public void t5() {
        MockThread th_ = new MockThread(new MockRunnable(), false, new MockAtomicLong());
        th_.start();
        assertFalse(th_.isInvoked());
    }
    @Test
    public void t6() {
        MockThread th_ = new MockThread(null, true, new MockAtomicLong());
        th_.start();
        assertFalse(th_.isInvoked());
    }
    @Test
    public void t7() {
        MockThread th_ = new MockThread(new MockRunnable(), true, new MockAtomicLong());
        th_.start();
        assertTrue(th_.isInvoked());
    }
    @Test
    public void t8() {
        MockThread th_ = new MockThread(new MockRunnable(), true, new MockAtomicLong());
        th_.setAlive(true);
        assertFalse(th_.setPriority(2));
        assertEq(2,th_.getPriority());
    }
    @Test
    public void t9() {
        MockThread th_ = new MockThread(null, false, new MockAtomicLong());
        th_.start();
        assertSame(ThState.ENDED,th_.join());
        assertFalse(th_.isInvoked());
    }
    @Test
    public void t10() {
        MockThread th_ = new MockThread(new MockRunnable(), false, new MockAtomicLong());
        th_.start();
        assertSame(ThState.ENDED,th_.join());
        assertTrue(th_.isInvoked());
    }
    @Test
    public void t11() {
        MockThread th_ = new MockThread(null, true, new MockAtomicLong());
        th_.start();
        assertSame(ThState.ENDED,th_.join());
        assertFalse(th_.isInvoked());
    }
    @Test
    public void t12() {
        MockThread th_ = new MockThread(new MockRunnable(), true, new MockAtomicLong());
        th_.start();
        assertSame(ThState.ENDED,th_.join());
        assertTrue(th_.isInvoked());
    }
    @Test
    public void t13() {
        MockThread th_ = new MockThread(new MockRunnable(), true, new MockAtomicLong());
        th_.setAlive(true);
        assertSame(ThState.ALIVE,th_.join());
    }
    @Test
    public void t14() {
        MockRunnable r_ = new MockRunnable();
        MockThread th_ = new MockThread(r_, false, new MockAtomicLong());
        th_.getThread().run();
        assertTrue(r_.isStarted());
    }
    @Test
    public void t15() {
        MockThread th_ = new MockThread(new MockRunnable(), true, new MockAtomicLong());
        th_.setInterrupted(true);
        assertTrue(th_.isInterrupted());
    }
    @Test
    public void t16() {
        MockThread th_ = new MockThread(new MockRunnable(), true, new MockAtomicLong());
        th_.setInterrupted(false);
        assertFalse(th_.isInterrupted());
    }
}
