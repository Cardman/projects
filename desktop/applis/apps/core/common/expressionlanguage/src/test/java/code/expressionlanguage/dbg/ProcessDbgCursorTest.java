package code.expressionlanguage.dbg;

import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.options.ResultContext;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgCursorTest extends ProcessDbgCommon {
    @Test
    public void test1() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int t = callee();int u = 3;return Math.mod(t,u);}public static int callee(){return 8;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",52);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormalValueCursor(stack_, cont_, 82);
        assertEq(1,next_.nbPages());
        assertEq(82,now(next_));
    }
    @Test
    public void test2() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int t = callee();int u = 3;return Math.mod(t,u);}public static int callee(){return 8;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",52);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormalValueCursor(stack_, cont_, 82);
        assertEq(0, dbgContinueNormalValueNextInstMethod(next_, cont_.getContext()).nbPages());
    }
}
