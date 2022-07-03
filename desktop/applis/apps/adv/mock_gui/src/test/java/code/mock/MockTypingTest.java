package code.mock;

import code.gui.TextAnswerValue;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;
import org.junit.Test;

public final class MockTypingTest extends EquallableMockGuiUtil {

    @Test
    public void incr1() {
        assertEq(0,new MockConfirmDialogAnsAbs(NumberUtil.wrapIntArray()).input(null,null,null,null,0));
    }

    @Test
    public void incr2() {
        assertEq(1,new MockConfirmDialogAnsAbs(NumberUtil.wrapIntArray(1)).input(null,null,null,null,null,0));
    }

    @Test
    public void incr3() {
        MockConfirmDialogTextAbs c_ = new MockConfirmDialogTextAbs(pairs(new TextAnswerValue(1,"1"), new TextAnswerValue(2,"2")));
        TextAnswerValue first_ = c_.input(null,null,null,null,null);
        assertEq(1,first_.getAnswer());
        assertEq("1",first_.getTypedText());
        TextAnswerValue second_ = c_.input(null, null, null, null, null);
        assertEq(2,second_.getAnswer());
        assertEq("2",second_.getTypedText());
    }
    @Test
    public void incr4() {
        assertEq("",new MockFileFolerDialog(StringUtil.wrapStringArray()).input(null,null,null,false,null,null));
    }
    @Test
    public void incr5() {
        assertEq("",new MockFileFolerDialog(StringUtil.wrapStringArray()).input(null,null,false,null,null));
    }
    @Test
    public void incr6() {
        assertEq("",new MockFileFolerDialog(StringUtil.wrapStringArray()).input(null,null,false));
    }
}
