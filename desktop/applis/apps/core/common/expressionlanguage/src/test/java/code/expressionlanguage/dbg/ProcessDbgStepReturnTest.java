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
        cont_.toggleBreakPoint("pkg/Ex",114);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormalValueStepRet(stack_, cont_.getContext());
        assertEq(1,next_.nbPages());
        assertEq(54,now(next_));
    }
    @Test
    public void test2() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int v=maelle();return v;}public static int maelle(){int t = 8;int u = toutesLesMachinesOntUnCoeur();return Math.mod(t,u);}public static int toutesLesMachinesOntUnCoeur(){return 3;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",114);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
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
        cont_.toggleBreakPoint("pkg/Ex",114);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormalValueStepRet(stack_, cont_.getContext());
        StackCall prev_ = dbgContinueNormalValueStepRet(next_, cont_.getContext());
        assertEq(0,dbgContinueNormalValueStepRet(prev_, cont_.getContext()).nbPages());
    }
    @Test
    public void test4() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int v=maelle()+flash();return v;}public static int maelle(){int t = 8;int u = toutesLesMachinesOntUnCoeur();return Math.mod(t,u);}public static int toutesLesMachinesOntUnCoeur(){return 3;}public static int flash(){return 4;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",122);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormalValueStepRet(stack_, cont_.getContext());
        assertEq(1,next_.nbPages());
        assertEq(54,now(next_));
    }
    @Test
    public void test5() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int v=maelle()+flash();return v;}public static int maelle(){int t = 8;int u = toutesLesMachinesOntUnCoeur();return Math.mod(t,u);}public static int toutesLesMachinesOntUnCoeur(){return 3;}public static int flash(){return 4;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",114);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormalValueStepRet(stack_, cont_.getContext());
        StackCall prev_ = dbgContinueNormalValueStepRet(next_, cont_.getContext());
        assertEq(1,prev_.nbPages());
        assertEq(78,now(prev_));
    }
    @Test
    public void test6() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int v=maelle()+flash();return v;}public static int maelle(){int t = 8;int u = toutesLesMachinesOntUnCoeur();return Math.mod(t,u);}public static int toutesLesMachinesOntUnCoeur(){return 3;}public static int flash(){return 4;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",114);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormalValueStepRet(stack_, cont_.getContext());
        StackCall prev_ = dbgContinueNormalValueStepRet(next_, cont_.getContext());
        assertEq(0,dbgContinueNormalValueStepRet(prev_, cont_.getContext()).nbPages());
    }
    @Test
    public void test7() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int v=maelle()+flash();return v;}public static int maelle(){int t = 8;int u = toutesLesMachinesOntUnCoeur();return Math.mod(t,u);}public static int toutesLesMachinesOntUnCoeur(){return 3;}public static int flash(){return 4+enAttendant();}public static int enAttendant(){return 6;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",122);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormalValueStepRet(stack_, cont_.getContext());
        assertEq(1,next_.nbPages());
        assertEq(54,now(next_));
    }
    @Test
    public void test8() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int v=maelle()+flash();return v;}public static int maelle(){int t = 8;int u = toutesLesMachinesOntUnCoeur();return Math.mod(t,u);}public static int toutesLesMachinesOntUnCoeur(){return 3;}public static int flash(){return 4+enAttendant();}public static int enAttendant(){return 6;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",114);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormalValueStepRet(stack_, cont_.getContext());
        StackCall prev_ = dbgContinueNormalValueStepRet(next_, cont_.getContext());
        assertEq(1,prev_.nbPages());
        assertEq(78,now(prev_));
    }
    @Test
    public void test9() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){int v=maelle()+flash();return v;}public static int maelle(){int t = 8;int u = toutesLesMachinesOntUnCoeur();return Math.mod(t,u);}public static int toutesLesMachinesOntUnCoeur(){return 3;}public static int flash(){return 4+enAttendant();}public static int enAttendant(){return 6;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",114);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormalValueStepRet(stack_, cont_.getContext());
        StackCall prev_ = dbgContinueNormalValueStepRet(next_, cont_.getContext());
        assertEq(0,dbgContinueNormalValueStepRet(prev_, cont_.getContext()).nbPages());
    }
    @Test
    public void test10() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public int v;public(int v){this.v=v;}public static int $(Ex e){return e.v;}public static int exmeth(){int v=maelle();return v;}public static Ex maelle(){int t = 8;int u = toutesLesMachinesOntUnCoeur();return new Ex(Math.mod(t,u));}public static int toutesLesMachinesOntUnCoeur(){return 3;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",188);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormalValueStepRet(stack_, cont_.getContext());
        assertEq(1,next_.nbPages());
        assertEq(129,now(next_));
    }
    @Test
    public void test11() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public int v;public(int v){this.v=v;}public static int $(Ex e){return e.v;}public static int exmeth(){int v=maelle();return v;}public static Ex maelle(){int t = 8;int u = toutesLesMachinesOntUnCoeur();return new Ex(Math.mod(t,u));}public static int toutesLesMachinesOntUnCoeur(){return 3;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",188);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormalValueStepRet(stack_, cont_.getContext());
        StackCall prev_ = dbgContinueNormalValueStepRet(next_, cont_.getContext());
        assertEq(1,prev_.nbPages());
        assertEq(145,now(prev_));
    }
    @Test
    public void test12() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public int v;public(int v){this.v=v;}public static int $(Ex e){return e.v;}public static int exmeth(){int v=maelle();return v;}public static Ex maelle(){int t = 8;int u = toutesLesMachinesOntUnCoeur();return new Ex(Math.mod(t,u));}public static int toutesLesMachinesOntUnCoeur(){return 3;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",188);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormalValueStepRet(stack_, cont_.getContext());
        StackCall prev_ = dbgContinueNormalValueStepRet(next_, cont_.getContext());
        assertEq(0,dbgContinueNormalValueStepRet(prev_, cont_.getContext()).nbPages());
    }
    @Test
    public void test13() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public int v;public(int v){this.v=v;}public static int $(Ex e){return e.v;}public static int exmeth(){int v=maelle();return v;}public static Ex maelle(){int t = 8;int u = toutesLesMachinesOntUnCoeur();return new Ex(Math.mod(t,u));}public static int toutesLesMachinesOntUnCoeur(){return 3;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",188);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormalValueStepRet(stack_, cont_.getContext());
        StackCall prev_ = dbgContinueNormalValueNextInst(next_, cont_.getContext());
        StackCall next2_ = dbgContinueNormalValueStepRet(prev_, cont_.getContext());
        assertEq(1,next2_.nbPages());
        assertEq(129,now(next2_));
    }
    @Test
    public void test14() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public int v;public(int v){this.v=v;}public static int $(Ex e){return e.v;}public static int exmeth(){int v=maelle();return v;}public static Ex maelle(){int t = 8;int u = toutesLesMachinesOntUnCoeur();return new Ex(Math.mod(t,u));}public static int toutesLesMachinesOntUnCoeur(){return 3;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",188);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormalValueStepRet(stack_, cont_.getContext());
        StackCall prev_ = dbgContinueNormalValueNextInst(next_, cont_.getContext());
        StackCall next2_ = dbgContinueNormalValueStepRet(prev_, cont_.getContext());
        assertEq(1,next2_.nbPages());
        assertEq(145,now(dbgContinueNormalValueStepRet(prev_, cont_.getContext())));
    }
    @Test
    public void test15() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public int v;public(int v){this.v=v;}public static int $(Ex e){return e.v;}public static int exmeth(){int v=maelle();return v;}public static Ex maelle(){int t = 8;int u = toutesLesMachinesOntUnCoeur();return new Ex(Math.mod(t,u));}public static int toutesLesMachinesOntUnCoeur(){return 3;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",188);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormalValueStepRet(stack_, cont_.getContext());
        StackCall prev_ = dbgContinueNormalValueNextInst(next_, cont_.getContext());
        StackCall next2_ = dbgContinueNormalValueStepRet(prev_, cont_.getContext());
        assertEq(1,next2_.nbPages());
        assertEq(0,dbgContinueNormalValueStepRet(dbgContinueNormalValueStepRet(prev_, cont_.getContext()), cont_.getContext()).nbPages());
    }
}
