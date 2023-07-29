package code.expressionlanguage.dbg;

import code.expressionlanguage.exec.StackCallReturnValue;
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
        StackCallReturnValue stack_ = dbgNormalInit("pkg.Ex", id_, cont_);
        assertFalse(stack_.isReturning());
        StackCallReturnValue next_ = dbgContinueNormalValueStepRet(stack_.getStack(), cont_.getContext());
        assertEq(1,next_.getVariables().size());
        assertEq(155,now(next_.getStack()));
        assertTrue(next_.isReturning());
    }
    @Test
    public void test2() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int v=maelle();return v;}public static int maelle(){int t = 8;int u = toutesLesMachinesOntUnCoeur();return Math.mod(t,u);}public static int toutesLesMachinesOntUnCoeur(){return 3;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",114,cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCallReturnValue stack_ = dbgNormalInit("pkg.Ex", id_, cont_);
        assertFalse(stack_.isReturning());
        StackCallReturnValue next_ = dbgContinueNormalValueStepRet(stack_.getStack(), cont_.getContext());
        StackCallReturnValue prev_ = dbgContinueNormalValueStepRet(next_.getStack(), cont_.getContext());
        assertEq(0,prev_.getVariables().size());
        assertEq(70,now(prev_.getStack()));
        assertTrue(prev_.isReturning());
    }
    @Test
    public void test3() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int v=maelle();return v;}public static int maelle(){int t = 8;int u = toutesLesMachinesOntUnCoeur();return Math.mod(t,u);}public static int toutesLesMachinesOntUnCoeur(){return 3;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",114,cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCallReturnValue stack_ = dbgNormalInit("pkg.Ex", id_, cont_);
        assertFalse(stack_.isReturning());
        StackCallReturnValue next_ = dbgContinueNormalValueStepRet(stack_.getStack(), cont_.getContext());
        StackCallReturnValue prev_ = dbgContinueNormalValueStepRet(next_.getStack(), cont_.getContext());
        assertEq(0,dbgContinueNormalValueStepRet(prev_.getStack(), cont_.getContext()).getVariables().size());
    }
    @Test
    public void test4() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int v=maelle()+flash();return v;}public static int maelle(){int t = 8;int u = toutesLesMachinesOntUnCoeur();return Math.mod(t,u);}public static int toutesLesMachinesOntUnCoeur(){return 3;}public static int flash(){return 4;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",122,cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCallReturnValue stack_ = dbgNormalInit("pkg.Ex", id_, cont_);
        assertFalse(stack_.isReturning());
        StackCallReturnValue next_ = dbgContinueNormalValueStepRet(stack_.getStack(), cont_.getContext());
        assertEq(1,next_.getVariables().size());
        assertEq(163,now(next_.getStack()));
        assertEq(54,nowBefore(next_.getStack()));
        assertTrue(next_.isReturning());
    }
    @Test
    public void test5() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int v=maelle()+flash();return v;}public static int maelle(){int t = 8;int u = toutesLesMachinesOntUnCoeur();return Math.mod(t,u);}public static int toutesLesMachinesOntUnCoeur(){return 3;}public static int flash(){return 4;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",114,cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCallReturnValue stack_ = dbgNormalInit("pkg.Ex", id_, cont_);
        assertFalse(stack_.isReturning());
        StackCallReturnValue next_ = dbgContinueNormalValueStepRet(stack_.getStack(), cont_.getContext());
        StackCallReturnValue prev_ = dbgContinueNormalValueStepRet(next_.getStack(), cont_.getContext());
        assertEq(1,prev_.getVariables().size());
        assertEq(63,nowBefore(prev_.getStack()));
        assertTrue(prev_.isReturning());
    }
    @Test
    public void test6() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int v=maelle()+flash();return v;}public static int maelle(){int t = 8;int u = toutesLesMachinesOntUnCoeur();return Math.mod(t,u);}public static int toutesLesMachinesOntUnCoeur(){return 3;}public static int flash(){return 4;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",114,cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCallReturnValue stack_ = dbgNormalInit("pkg.Ex", id_, cont_);
        assertFalse(stack_.isReturning());
        StackCallReturnValue next_ = dbgContinueNormalValueStepRet(stack_.getStack(), cont_.getContext());
        StackCallReturnValue prev_ = dbgContinueNormalValueStepRet(next_.getStack(), cont_.getContext());
        assertEq(0,dbgContinueNormalValueStepRet(prev_.getStack(), cont_.getContext()).getVariables().size());
        assertTrue(prev_.isReturning());
    }
}
