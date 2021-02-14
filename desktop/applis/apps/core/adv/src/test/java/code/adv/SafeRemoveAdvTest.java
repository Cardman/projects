package code.adv;

import org.junit.Test;

public class SafeRemoveAdvTest extends EquallableExUtil {
    @Test
    public void test1() {
        SafeRemAdvImpl s_ = new SafeRemAdvImpl();
        s_.getList().add("ELEMENT");
        SafeRemoveAdvUtil.safeRemove(s_,-1);
        assertEq(1,s_.getList().size());
    }
    @Test
    public void test2() {
        SafeRemAdvImpl s_ = new SafeRemAdvImpl();
        s_.getList().add("ELEMENT");
        SafeRemoveAdvUtil.safeRemove(s_,1);
        assertEq(1,s_.getList().size());
    }
    @Test
    public void test3() {
        SafeRemAdvImpl s_ = new SafeRemAdvImpl();
        s_.getList().add("ELEMENT");
        SafeRemoveAdvUtil.safeRemove(s_,0);
        assertEq(0,s_.getList().size());
    }
}
