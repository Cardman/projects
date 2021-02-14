package code.adv;

import org.junit.Test;

public final class ValueChangingSecTest extends EquallableExUtil {
    @Test
    public void act1() {
        ValChangingSecImpl v_ = new ValChangingSecImpl();
        ValueChangingSecondUtil.act(v_,0);
        assertEq(" ",v_.getAction());
    }
    @Test
    public void act2() {
        ValChangingSecImpl v_ = new ValChangingSecImpl();
        ValueChangingSecondUtil.act(v_,1);
        assertEq("",v_.getAction());
    }
}
