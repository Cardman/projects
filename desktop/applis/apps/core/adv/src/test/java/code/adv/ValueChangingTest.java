package code.adv;

import org.junit.Test;

public final class ValueChangingTest extends EquallableExUtil {
    @Test
    public void act1() {
        ValChangingImpl v_ = new ValChangingImpl(false);
        ValueChangingUtil.act(v_);
        assertEq(" ",v_.getAction());
    }
    @Test
    public void act2() {
        ValChangingImpl v_ = new ValChangingImpl(true);
        ValueChangingUtil.act(v_);
        assertEq("",v_.getAction());
    }
}
