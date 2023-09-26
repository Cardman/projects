package code.expressionlanguage.dbg;

import code.expressionlanguage.DefContextGenerator;
import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.dbg.BreakPointBlockPair;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.structs.Struct;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgGeneForTest extends ProcessDbgCommon {
    @Test
    public void test1() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  int t=0;\n");
        xml_.append("  for(int i=4;i>0;i--){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",74);
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(74, now(stack_));
        Struct s_ = stack_.getLastPage().getContentEx().getRefParams().getVal("t").getValue(stack_, cont_.getContext());
        assertEq(0, toInt(s_));
    }
    @Test
    public void test2() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  int t=0;\n");
        xml_.append("  for(int i=4;i>0;i--){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",74);
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_,cont_.getContext()).nbPages());
    }
    @Test
    public void test3() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  int t=0;\n");
        xml_.append("  for(int i=4;i>0;i--){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",82);
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(82, now(stack_));
        Struct s_ = stack_.getLastPage().getContentEx().getRefParams().getVal("t").getValue(stack_, cont_.getContext());
        assertEq(4, toInt(s_));
    }
    @Test
    public void test4() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  int t=0;\n");
        xml_.append("  for(int i=4;i>0;i--){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",82);
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertEq(1, next_.nbPages());
        assertEq(82, now(next_));
        Struct s_ = next_.getLastPage().getContentEx().getRefParams().getVal("t").getValue(next_, cont_.getContext());
        assertEq(7, toInt(s_));
    }
    @Test
    public void test5() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  int t=0;\n");
        xml_.append("  for(int i=4;i>0;i--){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",82);
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(dbgContinueNormal(stack_, cont_.getContext()), cont_.getContext());
        assertEq(1, next_.nbPages());
        assertEq(82, now(next_));
        Struct s_ = next_.getLastPage().getContentEx().getRefParams().getVal("t").getValue(next_, cont_.getContext());
        assertEq(9, toInt(s_));
    }
    @Test
    public void test6() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  int t=0;\n");
        xml_.append("  for(int i=4;i>0;i--){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",82);
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(dbgContinueNormal(dbgContinueNormal(stack_, cont_.getContext()), cont_.getContext()), cont_.getContext());
        assertEq(1, next_.nbPages());
        assertEq(82, now(next_));
        Struct s_ = next_.getLastPage().getContentEx().getRefParams().getVal("t").getValue(next_, cont_.getContext());
        assertEq(10, toInt(s_));
    }
    @Test
    public void test7() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  int t=0;\n");
        xml_.append("  for(int i=4;i>0;i--){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",82);
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(dbgContinueNormal(dbgContinueNormal(dbgContinueNormal(stack_, cont_.getContext()), cont_.getContext()), cont_.getContext()), cont_.getContext());
        assertEq(0, next_.nbPages());
    }
    @Test
    public void test8() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  int t=0;\n");
        xml_.append("  for(int i=4;i>0;i--){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",78);
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(78, now(stack_));
        Struct s_ = stack_.getLastPage().getContentEx().getRefParams().getVal("t").getValue(stack_, cont_.getContext());
        assertEq(0, toInt(s_));
    }
    @Test
    public void test9() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  int t=0;\n");
        xml_.append("  for(int i=4;i>0;i--){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",78);
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertEq(1, next_.nbPages());
        assertEq(78, now(next_));
        Struct s_ = next_.getLastPage().getContentEx().getRefParams().getVal("t").getValue(next_, cont_.getContext());
        assertEq(4, toInt(s_));
    }
    @Test
    public void test10() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  int t=0;\n");
        xml_.append("  for(int i=4;i>0;i--){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",78);
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(dbgContinueNormal(stack_, cont_.getContext()), cont_.getContext());
        assertEq(1, next_.nbPages());
        assertEq(78, now(next_));
        Struct s_ = next_.getLastPage().getContentEx().getRefParams().getVal("t").getValue(next_, cont_.getContext());
        assertEq(7, toInt(s_));
    }
    @Test
    public void test11() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  int t=0;\n");
        xml_.append("  for(int i=4;i>0;i--){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",78);
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(dbgContinueNormal(dbgContinueNormal(stack_, cont_.getContext()), cont_.getContext()), cont_.getContext());
        assertEq(1, next_.nbPages());
        assertEq(78, now(next_));
        Struct s_ = next_.getLastPage().getContentEx().getRefParams().getVal("t").getValue(next_, cont_.getContext());
        assertEq(9, toInt(s_));
    }
    @Test
    public void test12() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  int t=0;\n");
        xml_.append("  for(int i=4;i>0;i--){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",78);
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(dbgContinueNormal(dbgContinueNormal(dbgContinueNormal(stack_, cont_.getContext()), cont_.getContext()), cont_.getContext()), cont_.getContext());
        assertEq(1, next_.nbPages());
        assertEq(78, now(next_));
        Struct s_ = next_.getLastPage().getContentEx().getRefParams().getVal("t").getValue(next_, cont_.getContext());
        assertEq(10, toInt(s_));
    }
    @Test
    public void test13() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  int t=0;\n");
        xml_.append("  for(int i=4;i>0;i--){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",78);
        MethodId id_ = getMethodId("catching");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(dbgContinueNormal(dbgContinueNormal(dbgContinueNormal(dbgContinueNormal(stack_, cont_.getContext()), cont_.getContext()), cont_.getContext()), cont_.getContext()), cont_.getContext());
        assertEq(0, next_.nbPages());
    }

    @Test
    public void test14() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(3,4);}public static int exmeth(int t, int u){int v = 1;for(int i=t-1;i<u-1;i++){v+=i;}return v;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",130);
        analyze(cont_,"i==2&&v==1","pkg/Ex",130);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(130, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }


    @Test
    public void test15() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(3,4);}public static int exmeth(int t, int u){int v = 1;for(int i=t-1;i<u-1;i++){v+=i;}return v;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",136);
        analyze(cont_,"i==2&&v==1","pkg/Ex",136);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(136, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test16() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(3,4);}public static int exmeth(int t, int u){int v = 1;for(int i=t-1;i<u-1;i++){v+=i;}return v;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",140);
        analyze(cont_,"i==3&&v==3","pkg/Ex",140);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(140, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test17() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(3,4);}public static int exmeth(int t, int u){int v = 1;for(int i=t-1;i<u-1;i++){v+=i;}return v;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",130);
        analyze(cont_,"i==3","pkg/Ex",130);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test18() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(3,4);}public static int exmeth(int t, int u){int v = 1;for(int i=t-1;i<u-1;i++){v+=i;}return v;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",136);
        analyze(cont_,"i==5","pkg/Ex",136);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test19() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(3,4);}public static int exmeth(int t, int u){int v = 1;for(int i=t-1;i<u-1;i++){v+=i;}return v;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",140);
        analyze(cont_,"i==5","pkg/Ex",140);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }

    @Test
    public void test20() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(3,4);}public static int exmeth(int t, int u){int v = 1;for(int i=t-1;i<u-1;i++){v+=i;}return v;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",136);
        analyze(cont_,"i==3","pkg/Ex",136);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(136, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test21() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return exmeth(3,4);}public static int exmeth(int t, int u){int v = 1;for(int i=t-1;i<u-1;i++){v+=i;}return v;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",140);
        analyze(cont_,"i==3","pkg/Ex",140);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(140, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }

    @Test
    public void test22() {
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", "public class pkg.Ex {public static int exmeth(){return 0;}public static int exmeth(int t, int u){int v = 1;for(int i=t-1;i<u-1;i++){v+=i;}return v;}}");
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",107);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    static void analyze(ResultContext _cont, String _cond, String _file, int _caret) {
        FileBlock bl_ = _cont.getPageEl().getPreviousFilesBodies().getVal(_file);
        BreakPointBlockPair pair_ = _cont.getPair(_cont.getFiles().getVal(bl_), _caret);
        pair_.getValue().getResultStd().analyze(pair_,_cond,"","",_cont,new DefContextGenerator());
    }
}
