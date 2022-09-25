package code.util.opers;

import code.util.EquallableExUtil;
import code.util.classestest.SafeRemImpl;
import org.junit.Test;

public final class SafeRemoveTest extends EquallableExUtil {
    @Test
    public void test1() {
        SafeRemImpl<String> s_ = new SafeRemImpl<String>();
        s_.getList().add("ELEMENT");
        SafeRemoveUtil.safeRemove(s_,-1);
        assertEq(1,s_.getList().size());
    }
    @Test
    public void test2() {
        SafeRemImpl<String> s_ = new SafeRemImpl<String>();
        s_.getList().add("ELEMENT");
        SafeRemoveUtil.safeRemove(s_,1);
        assertEq(1,s_.getList().size());
    }
    @Test
    public void test3() {
        SafeRemImpl<String> s_ = new SafeRemImpl<String>();
        s_.getList().add("ELEMENT");
        SafeRemoveUtil.safeRemove(s_,0);
        assertEq(0,s_.getList().size());
    }
}
