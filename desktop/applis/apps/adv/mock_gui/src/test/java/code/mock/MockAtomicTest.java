package code.mock;

import org.junit.Test;

public final class MockAtomicTest extends EquallableMockGuiUtil {
    @Test
    public void b1() {
        assertFalse(new MockAtomicBoolean().get());
    }
    @Test
    public void b2() {
        assertTrue(new MockAtomicBoolean(true).get());
    }
    @Test
    public void b3() {
        assertFalse(new MockAtomicBoolean(false).get());
    }
    @Test
    public void b4() {
        MockAtomicBoolean at_ = new MockAtomicBoolean();
        at_.set(true);
        assertTrue(at_.get());
    }
    @Test
    public void b5() {
        MockAtomicBoolean at_ = new MockAtomicBoolean();
        at_.lazySet(true);
        assertTrue(at_.get());
    }
    @Test
    public void b6() {
        MockAtomicBoolean at_ = new MockAtomicBoolean();
        assertFalse(at_.getAndSet(true));
        assertTrue(at_.get());
    }
    @Test
    public void b7() {
        MockAtomicBoolean at_ = new MockAtomicBoolean(true);
        assertTrue(at_.getAndSet(false));
        assertFalse(at_.get());
    }
    @Test
    public void b8() {
        MockAtomicBoolean at_ = new MockAtomicBoolean(true);
        assertFalse(at_.compareAndSet(false,true));
        assertTrue(at_.get());
    }
    @Test
    public void b9() {
        MockAtomicBoolean at_ = new MockAtomicBoolean(true);
        assertTrue(at_.compareAndSet(true,false));
        assertFalse(at_.get());
    }
    @Test
    public void i1() {
        assertEq(0,new MockAtomicInteger().get());
    }
    @Test
    public void i2() {
        assertEq(1,new MockAtomicInteger(1).get());
    }
    @Test
    public void i3() {
        assertEq(0,new MockAtomicInteger(0).get());
    }
    @Test
    public void i4() {
        MockAtomicInteger at_ = new MockAtomicInteger();
        at_.set(1);
        assertEq(1,at_.get());
    }
    @Test
    public void i5() {
        MockAtomicInteger at_ = new MockAtomicInteger();
        at_.lazySet(1);
        assertEq(1,at_.get());
    }
    @Test
    public void i6() {
        MockAtomicInteger at_ = new MockAtomicInteger();
        assertEq(0,at_.getAndSet(1));
        assertEq(1,at_.get());
    }
    @Test
    public void i7() {
        MockAtomicInteger at_ = new MockAtomicInteger(1);
        assertFalse(at_.compareAndSet(0,1));
        assertEq(1,at_.get());
    }
    @Test
    public void i8() {
        MockAtomicInteger at_ = new MockAtomicInteger(1);
        assertTrue(at_.compareAndSet(1,0));
        assertEq(0,at_.get());
    }
    @Test
    public void i9() {
        MockAtomicInteger at_ = new MockAtomicInteger(1);
        assertEq(1,at_.getAndAdd(2));
        assertEq(3,at_.get());
    }
    @Test
    public void i10() {
        MockAtomicInteger at_ = new MockAtomicInteger(1);
        assertEq(3,at_.addAndGet(2));
        assertEq(3,at_.get());
    }
    @Test
    public void i11() {
        MockAtomicInteger at_ = new MockAtomicInteger(2);
        assertEq(2,at_.getAndIncrement());
        assertEq(3,at_.get());
    }
    @Test
    public void i12() {
        MockAtomicInteger at_ = new MockAtomicInteger(2);
        assertEq(3,at_.incrementAndGet());
        assertEq(3,at_.get());
    }
    @Test
    public void i13() {
        MockAtomicInteger at_ = new MockAtomicInteger(-2);
        assertEq(-2,at_.getAndDecrement());
        assertEq(-3,at_.get());
    }
    @Test
    public void i14() {
        MockAtomicInteger at_ = new MockAtomicInteger(-2);
        assertEq(-3,at_.decrementAndGet());
        assertEq(-3,at_.get());
    }
    @Test
    public void l1() {
        assertEq(0,new MockAtomicLong().get());
    }
    @Test
    public void l2() {
        assertEq(1,new MockAtomicLong(1).get());
    }
    @Test
    public void l3() {
        assertEq(0,new MockAtomicLong(0).get());
    }
    @Test
    public void l4() {
        MockAtomicLong at_ = new MockAtomicLong();
        at_.set(1);
        assertEq(1,at_.get());
    }
    @Test
    public void l5() {
        MockAtomicLong at_ = new MockAtomicLong();
        at_.lazySet(1);
        assertEq(1,at_.get());
    }
    @Test
    public void l6() {
        MockAtomicLong at_ = new MockAtomicLong();
        assertEq(0,at_.getAndSet(1));
        assertEq(1,at_.get());
    }
    @Test
    public void l7() {
        MockAtomicLong at_ = new MockAtomicLong(1);
        assertFalse(at_.compareAndSet(0,1));
        assertEq(1,at_.get());
    }
    @Test
    public void l8() {
        MockAtomicLong at_ = new MockAtomicLong(1);
        assertTrue(at_.compareAndSet(1,0));
        assertEq(0,at_.get());
    }
    @Test
    public void l9() {
        MockAtomicLong at_ = new MockAtomicLong(1);
        assertEq(1,at_.getAndAdd(2));
        assertEq(3,at_.get());
    }
    @Test
    public void l10() {
        MockAtomicLong at_ = new MockAtomicLong(1);
        assertEq(3,at_.addAndGet(2));
        assertEq(3,at_.get());
    }
    @Test
    public void l11() {
        MockAtomicLong at_ = new MockAtomicLong(2);
        assertEq(2,at_.getAndIncrement());
        assertEq(3,at_.get());
    }
    @Test
    public void l12() {
        MockAtomicLong at_ = new MockAtomicLong(2);
        assertEq(3,at_.incrementAndGet());
        assertEq(3,at_.get());
    }
    @Test
    public void l13() {
        MockAtomicLong at_ = new MockAtomicLong(-2);
        assertEq(-2,at_.getAndDecrement());
        assertEq(-3,at_.get());
    }
    @Test
    public void l14() {
        MockAtomicLong at_ = new MockAtomicLong(-2);
        assertEq(-3,at_.decrementAndGet());
        assertEq(-3,at_.get());
    }
}
