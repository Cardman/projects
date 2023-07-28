package code.expressionlanguage.dbg;

import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.options.ResultContext;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgStepNextBlockTest extends ProcessDbgCommon {
    @Test
    public void test1() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int v=maelle();return v;}public static int maelle(){int t = 8;int u = toutesLesMachinesOntUnCoeur();return Math.mod(t,u);}public static int toutesLesMachinesOntUnCoeur(){return 3;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",114,cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormalValueStepBlock(stack_, cont_.getContext());
        assertEq(1,next_.nbPages());
        assertEq(70,now(next_));
    }
    @Test
    public void test2() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int v=maelle();return v;}public static int maelle(){int t = 8;int u = toutesLesMachinesOntUnCoeur();return Math.mod(t,u);}public static int toutesLesMachinesOntUnCoeur(){return 3;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",114,cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormalValueStepBlock(stack_, cont_.getContext());
        StackCall prev_ = dbgContinueNormalValueStepBlock(next_, cont_.getContext());
        assertEq(0,prev_.nbPages());
    }
    @Test
    public void test3() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int maelle(){int u = 50;iter(int i=0;1;1){int t = 8;iter(int j=0;1;1){t += toutesLesMachinesOntUnCoeur();}u+=t;}return u;}public static int toutesLesMachinesOntUnCoeur(){return 3;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",105,cont_);
        MethodId id_ = getMethodId("maelle");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormalValueStepBlock(stack_, cont_.getContext());
        assertEq(1,next_.nbPages());
        assertEq(141,now(next_));
    }
    @Test
    public void test4() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int maelle(){int u = 50;iter(int i=0;1;1){int t = 8;iter(int j=0;1;1){t += toutesLesMachinesOntUnCoeur();}u+=t;}return u;}public static int toutesLesMachinesOntUnCoeur(){return 3;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",105,cont_);
        MethodId id_ = getMethodId("maelle");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormalValueStepBlock(stack_, cont_.getContext());
        StackCall prev_ = dbgContinueNormalValueStepBlock(next_, cont_.getContext());
        assertEq(1,prev_.nbPages());
        assertEq(154,now(prev_));
    }
    @Test
    public void test5() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int maelle(){int u = 50;iter(int i=0;1;1){int t = 8;iter(int j=0;1;1){t += toutesLesMachinesOntUnCoeur();}u+=t;}return u;}public static int toutesLesMachinesOntUnCoeur(){return 3;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",105,cont_);
        MethodId id_ = getMethodId("maelle");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormalValueStepBlock(stack_, cont_.getContext());
        StackCall prev_ = dbgContinueNormalValueStepBlock(next_, cont_.getContext());
        assertEq(0,dbgContinueNormalValueStepBlock(prev_, cont_.getContext()).nbPages());
    }
}
