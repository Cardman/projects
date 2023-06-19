package code.expressionlanguage.dbg;

import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.options.ResultContext;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgNextInstrMethodTest extends ProcessDbgCommon {
    @Test
    public void test1() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int t = callee();int u = 3;return Math.mod(t,u);}public static int callee(){return 8;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",52,cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormalValueNextInstMethod(stack_, cont_.getContext());
        assertEq(1,next_.nbPages());
        assertEq(69,now(next_));
    }
    @Test
    public void test2() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int t = callee();int u = 3;return Math.mod(t,u);}public static int callee(){return 8;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",52,cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormalValueNextInstMethod(stack_, cont_.getContext());
        StackCall prev_ = dbgContinueNormalValueNextInstMethod(next_, cont_.getContext());
        assertEq(1,prev_.nbPages());
        assertEq(82,now(prev_));
    }
    @Test
    public void test3() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int t = callee();int u = 3;return Math.mod(t,u);}public static int callee(){return 8;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",52,cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormalValueNextInstMethod(stack_, cont_.getContext());
        StackCall prev_ = dbgContinueNormalValueNextInstMethod(next_, cont_.getContext());
        assertEq(0,dbgContinueNormalValueNextInstMethod(prev_, cont_.getContext()).nbPages());
    }
    @Test
    public void test4() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int t = callee();int u = 3;return Math.mod(t,u);}public static int callee(){return 8;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",131,cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormalValueNextInstMethod(stack_, cont_.getContext());
        assertEq(1,next_.nbPages());
        assertEq(69,now(next_));
    }
    @Test
    public void test5() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int t = callee();int u = 3;return Math.mod(t,u);}public static int callee(){return 8;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",131,cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormalValueNextInstMethod(stack_, cont_.getContext());
        StackCall prev_ = dbgContinueNormalValueNextInstMethod(next_, cont_.getContext());
        assertEq(1,prev_.nbPages());
        assertEq(82,now(prev_));
    }
    @Test
    public void test6() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int t = callee();int u = 3;return Math.mod(t,u);}public static int callee(){return 8;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",131,cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormalValueNextInstMethod(stack_, cont_.getContext());
        StackCall prev_ = dbgContinueNormalValueNextInstMethod(next_, cont_.getContext());
        assertEq(0,dbgContinueNormalValueNextInstMethod(prev_, cont_.getContext()).nbPages());
    }
}
