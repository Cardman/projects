package code.expressionlanguage.dbg;

import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.options.ResultContext;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgNextInstrTest extends ProcessDbgCommon {
    @Test
    public void test1() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int t = 8;int u = 3;return Math.mod(t,u);}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",52);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormalValueNextInst(stack_, cont_.getContext());
        assertEq(1,next_.nbPages());
        assertEq(62,now(next_));
    }
    @Test
    public void test2() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int t = 8;int u = 3;return Math.mod(t,u);}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",52);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormalValueNextInst(stack_, cont_.getContext());
        StackCall prev_ = dbgContinueNormalValueNextInst(next_, cont_.getContext());
        assertEq(1,prev_.nbPages());
        assertEq(75,now(prev_));
    }
    @Test
    public void test3() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int t = 8;int u = 3;return Math.mod(t,u);}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",52);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormalValueNextInst(stack_, cont_.getContext());
        StackCall prev_ = dbgContinueNormalValueNextInst(next_, cont_.getContext());
        assertEq(0,dbgContinueNormalValueNextInst(prev_, cont_.getContext()).nbPages());
    }
    @Test
    public void test4() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int t = 8;int u = 3;return Math.mod(t,u);}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",52);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        cont_.getContext().pausedLoop().set(true);
        StackCall next_ = dbgContinueNormalValueStepRet(stack_, cont_.getContext());
        assertEq(1,next_.nbPages());
        assertEq(52,now(next_));
    }
}
