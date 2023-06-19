package code.expressionlanguage.dbg;

import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.options.ResultContext;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgStepReturnTest extends ProcessDbgCommon {
    @Test
    public void test1() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int v=maelle();return v;}public static int maelle(){int t = 8;int u = toutesLesMachinesOntUnCoeur();return Math.mod(t,u);}public static int toutesLesMachinesOntUnCoeur(){return 3;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",114,cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormalValueStepRet(stack_, cont_.getContext());
        assertEq(2,next_.nbPages());
        assertEq(155,now(next_));
    }
    @Test
    public void test2() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int v=maelle();return v;}public static int maelle(){int t = 8;int u = toutesLesMachinesOntUnCoeur();return Math.mod(t,u);}public static int toutesLesMachinesOntUnCoeur(){return 3;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",114,cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormalValueStepRet(stack_, cont_.getContext());
        StackCall prev_ = dbgContinueNormalValueStepRet(next_, cont_.getContext());
        assertEq(1,prev_.nbPages());
        assertEq(70,now(prev_));
    }
    @Test
    public void test3() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int v=maelle();return v;}public static int maelle(){int t = 8;int u = toutesLesMachinesOntUnCoeur();return Math.mod(t,u);}public static int toutesLesMachinesOntUnCoeur(){return 3;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",114,cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormalValueStepRet(stack_, cont_.getContext());
        StackCall prev_ = dbgContinueNormalValueStepRet(next_, cont_.getContext());
        assertEq(0,dbgContinueNormalValueStepRet(prev_, cont_.getContext()).nbPages());
    }
}
