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

public final class ProcessDbgReturnTest extends ProcessDbgCommon {
    @Test
    public void test1() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t = 8;\n");
        xml_.append("  int u = 3;\n");
        xml_.append("  return Math.mod(t,u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",87);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(86, now(stack_));
    }
    @Test
    public void test2() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t = 8;\n");
        xml_.append("  int u = 3;\n");
        xml_.append("  return Math.mod(t,u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",87);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
    @Test
    public void test3() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static void exmeth(){\n");
        xml_.append("  int t = 8;\n");
        xml_.append("  int u = 3;\n");
        xml_.append("  return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",86);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(86, now(stack_));
    }
    @Test
    public void test4() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static void exmeth(){\n");
        xml_.append("  int t = 8;\n");
        xml_.append("  int u = 3;\n");
        xml_.append("  return;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",86);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
    @Test
    public void test5() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int v = 2;\n");
        xml_.append(" public static void exmeth(){\n");
        xml_.append("  exmethRef() = 3;\n");
        xml_.append(" }\n");
        xml_.append(" public static that int exmethRef(){\n");
        xml_.append("  return that(v);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",146);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(146, now(stack_));
    }
    @Test
    public void test6() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int v = 2;\n");
        xml_.append(" public static void exmeth(){\n");
        xml_.append("  exmethRef() = 3;\n");
        xml_.append(" }\n");
        xml_.append(" public static that int exmethRef(){\n");
        xml_.append("  return that(v);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",146);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
    @Test
    public void test7() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int field=8;\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return new Ex().fct(2,3);\n");
        xml_.append(" }\n");
        xml_.append(" public int fct(int t, int u){\n");
        xml_.append("  Fct<int,int,int> fct=(t,u)->field+t+u+#t+#u;\n");
        xml_.append("  return fct.call(19,13);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",164);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(4, stack_.nbPages());
        assertEq(164, now(stack_));
        assertEq(2,valueCache(stack_,cont_,"t",0));
        assertEq(3,valueCache(stack_,cont_,"u",0));
        assertEq(19,value(stack_,cont_,"t"));
        assertEq(13,value(stack_,cont_,"u"));
    }
    @Test
    public void test8() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int field=8;\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return new Ex().fct(2,3);\n");
        xml_.append(" }\n");
        xml_.append(" public int fct(int t, int u){\n");
        xml_.append("  Fct<int,int,int> fct=(t,u)->field+t+u+#t+#u;\n");
        xml_.append("  return fct.call(19,13);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",164);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
    @Test
    public void test9() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int field=8;\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return new Ex().fct(2,3);\n");
        xml_.append(" }\n");
        xml_.append(" public int fct(int t, int u){\n");
        xml_.append("  Fct<int,int,Fct<int,int,int>> fct=(t,u)->(t,u)->field+t+u+#t+#u+##t+##u;\n");
        xml_.append("  return fct.call(19,13).call(15,17);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",184);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(4, stack_.nbPages());
        assertEq(184, now(stack_));
        assertEq(2,valueCache(stack_,cont_,"t",1));
        assertEq(3,valueCache(stack_,cont_,"u",1));
        assertEq(19,valueCache(stack_,cont_,"t",0));
        assertEq(13,valueCache(stack_,cont_,"u",0));
        assertEq(15,value(stack_,cont_,"t"));
        assertEq(17,value(stack_,cont_,"u"));
    }
    @Test
    public void test10() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public int field=8;\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  return new Ex().fct(2,3);\n");
        xml_.append(" }\n");
        xml_.append(" public int fct(int t, int u){\n");
        xml_.append("  Fct<int,int,Fct<int,int,int>> fct=(t,u)->(t,u)->field+t+u+#t+#u+##t+##u;\n");
        xml_.append("  return fct.call(19,13).call(15,17);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",184);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
    @Test
    public void test11() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){int t = 8;int u = 3;return Math.mod(t,u);}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",88);
        analyze(cont_,"t==8&&u==3","pkg/Ex",88);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(88, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
    @Test
    public void test12() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){int t = 8;int u = 3;return Math.mod(t,u);}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",88);
        analyze(cont_,"t==7","pkg/Ex",88);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test13() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){exmeth() = 10;return 0;}public static that int exmeth(){int t = 8;return that(t);}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",128);
        analyze(cont_,"t==8","pkg/Ex",128);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(2, stack_.nbPages());
        assertEq(128, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
    @Test
    public void test14() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int exmeth(){exmeth() = 10;return 0;}public static that int exmeth(){int t = 8;return that(t);}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",128);
        analyze(cont_,"t==7","pkg/Ex",128);
        MethodId id_ = getMethodId("exmeth");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    private static int value(StackCall _stack, ResultContext _cont, String _v) {
        Struct s_ = _stack.getLastPage().getContentEx().getRefParams().getVal(_v).getValue(_stack, _cont.getContext());
        return toInt(s_);
    }

    private static int valueCache(StackCall _stack, ResultContext _cont, String _v, int _d) {
        Struct s_ = _stack.getLastPage().getContentEx().getCache().getLocalWrapper(_v,_d).getValue(_stack, _cont.getContext());
        return toInt(s_);
    }

    static void analyze(ResultContext _cont, String _cond, String _file, int _caret) {
        FileBlock bl_ = _cont.getPageEl().getPreviousFilesBodies().getVal(_file);
        BreakPointBlockPair pair_ = _cont.getPair(_cont.getFiles().getVal(bl_), _caret);
        pair_.getValue().getResultStd().analyze(pair_,_cond,"","",_cont,new DefContextGenerator());
    }
}
