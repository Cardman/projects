package code.threads;

import code.util.EquallableExUtil;
import org.junit.Test;

public final class AbstractAtomicTest extends EquallableExUtil {
    @Test
    public void b1() {
        assertFalse(new ConcreteBoolean().get());
    }
    @Test
    public void b2() {
        assertTrue(new ConcreteBoolean(true).get());
    }
    @Test
    public void b3() {
        assertFalse(new ConcreteBoolean(false).get());
    }
    @Test
    public void b4() {
        ConcreteBoolean at_ = new ConcreteBoolean();
        at_.set(true);
        assertTrue(at_.get());
    }
    @Test
    public void b5() {
        ConcreteBoolean at_ = new ConcreteBoolean();
        at_.lazySet(true);
        assertTrue(at_.get());
    }
    @Test
    public void b6() {
        ConcreteBoolean at_ = new ConcreteBoolean();
        assertFalse(at_.getAndSet(true));
        assertTrue(at_.get());
    }
    @Test
    public void b7() {
        ConcreteBoolean at_ = new ConcreteBoolean(true);
        assertTrue(at_.getAndSet(false));
        assertFalse(at_.get());
    }
    @Test
    public void b8() {
        ConcreteBoolean at_ = new ConcreteBoolean(true);
        assertFalse(at_.compareAndSet(false,true));
        assertTrue(at_.get());
    }
    @Test
    public void b9() {
        ConcreteBoolean at_ = new ConcreteBoolean(true);
        assertTrue(at_.compareAndSet(true,false));
        assertFalse(at_.get());
    }

    @Test
    public void i1() {
        assertEq(0,new ConcreteInteger().get());
    }
    @Test
    public void i2() {
        assertEq(1,new ConcreteInteger(1).get());
    }
    @Test
    public void i3() {
        assertEq(0,new ConcreteInteger(0).get());
    }
    @Test
    public void i4() {
        ConcreteInteger at_ = new ConcreteInteger();
        at_.set(1);
        assertEq(1,at_.get());
    }
    @Test
    public void i5() {
        ConcreteInteger at_ = new ConcreteInteger();
        at_.lazySet(1);
        assertEq(1,at_.get());
    }
    @Test
    public void i6() {
        ConcreteInteger at_ = new ConcreteInteger();
        assertEq(0,at_.getAndSet(1));
        assertEq(1,at_.get());
    }
    @Test
    public void i7() {
        ConcreteInteger at_ = new ConcreteInteger(1);
        assertFalse(at_.compareAndSet(0,1));
        assertEq(1,at_.get());
    }
    @Test
    public void i8() {
        ConcreteInteger at_ = new ConcreteInteger(1);
        assertTrue(at_.compareAndSet(1,0));
        assertEq(0,at_.get());
    }
    @Test
    public void i9() {
        ConcreteInteger at_ = new ConcreteInteger(1);
        assertEq(1,at_.getAndAdd(2));
        assertEq(3,at_.get());
    }
    @Test
    public void i10() {
        ConcreteInteger at_ = new ConcreteInteger(1);
        assertEq(3,at_.addAndGet(2));
        assertEq(3,at_.get());
    }
    @Test
    public void i11() {
        ConcreteInteger at_ = new ConcreteInteger(2);
        assertEq(2,at_.getAndIncrement());
        assertEq(3,at_.get());
    }
    @Test
    public void i12() {
        ConcreteInteger at_ = new ConcreteInteger(2);
        assertEq(3,at_.incrementAndGet());
        assertEq(3,at_.get());
    }
    @Test
    public void i13() {
        ConcreteInteger at_ = new ConcreteInteger(-2);
        assertEq(-2,at_.getAndDecrement());
        assertEq(-3,at_.get());
    }
    @Test
    public void i14() {
        ConcreteInteger at_ = new ConcreteInteger(-2);
        assertEq(-3,at_.decrementAndGet());
        assertEq(-3,at_.get());
    }

    @Test
    public void l1() {
        assertEq(0,new ConcreteLong().get());
    }
    @Test
    public void l2() {
        assertEq(1,new ConcreteLong(1).get());
    }
    @Test
    public void l3() {
        assertEq(0,new ConcreteLong(0).get());
    }
    @Test
    public void l4() {
        ConcreteLong at_ = new ConcreteLong();
        at_.set(1);
        assertEq(1,at_.get());
    }
    @Test
    public void l5() {
        ConcreteLong at_ = new ConcreteLong();
        at_.lazySet(1);
        assertEq(1,at_.get());
    }
    @Test
    public void l6() {
        ConcreteLong at_ = new ConcreteLong();
        assertEq(0,at_.getAndSet(1));
        assertEq(1,at_.get());
    }
    @Test
    public void l7() {
        ConcreteLong at_ = new ConcreteLong(1);
        assertFalse(at_.compareAndSet(0,1));
        assertEq(1,at_.get());
    }
    @Test
    public void l8() {
        ConcreteLong at_ = new ConcreteLong(1);
        assertTrue(at_.compareAndSet(1,0));
        assertEq(0,at_.get());
    }
    @Test
    public void l9() {
        ConcreteLong at_ = new ConcreteLong(1);
        assertEq(1,at_.getAndAdd(2));
        assertEq(3,at_.get());
    }
    @Test
    public void l10() {
        ConcreteLong at_ = new ConcreteLong(1);
        assertEq(3,at_.addAndGet(2));
        assertEq(3,at_.get());
    }
    @Test
    public void l11() {
        ConcreteLong at_ = new ConcreteLong(2);
        assertEq(2,at_.getAndIncrement());
        assertEq(3,at_.get());
    }
    @Test
    public void l12() {
        ConcreteLong at_ = new ConcreteLong(2);
        assertEq(3,at_.incrementAndGet());
        assertEq(3,at_.get());
    }
    @Test
    public void l13() {
        ConcreteLong at_ = new ConcreteLong(-2);
        assertEq(-2,at_.getAndDecrement());
        assertEq(-3,at_.get());
    }
    @Test
    public void l14() {
        ConcreteLong at_ = new ConcreteLong(-2);
        assertEq(-3,at_.decrementAndGet());
        assertEq(-3,at_.get());
    }
}
