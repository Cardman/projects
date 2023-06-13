package code.expressionlanguage.dbg;

import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.StackCallReturnValue;
import code.expressionlanguage.exec.variables.ViewVariable;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.options.ResultContext;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgViewVariablesTest extends ProcessDbgCommon {
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",87,cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCallReturnValue i_ = dbgNormalInfo("pkg.Ex", id_, cont_,null);
        StackCall stack_ = i_.getStack();
        assertEq(1, stack_.nbPages());
        assertEq(1, i_.getVariables().size());
        CustList<ViewVariable> v_ = i_.getVariables().get(0).getVars();
        assertEq(2, v_.size());
        assertEq("t", v_.get(0).getName());
        assertEq(-1, v_.get(0).getDeep());
        assertEq("", v_.get(0).getIndexClassName());
        assertEq(-1, v_.get(0).getIndex());
        assertEq("int", v_.get(0).getClassName());
        assertEq(8, toInt(v_.get(0).getWrapper().getValue()));
        assertEq("u", v_.get(1).getName());
        assertEq(-1, v_.get(1).getDeep());
        assertEq("", v_.get(1).getIndexClassName());
        assertEq(-1, v_.get(1).getIndex());
        assertEq("int", v_.get(1).getClassName());
        assertEq(3, toInt(v_.get(1).getWrapper().getValue()));
        assertEq("pkg.Ex",i_.getVariables().get(0).getInstance().getClassName());
        assertEq("",i_.getVariables().get(0).getInstance().getEval().getClassName(cont_.getContext()));
    }

    @Test
    public void test2() {
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",164,cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCallReturnValue i_ = dbgNormalInfo("pkg.Ex", id_, cont_,null);
        StackCall stack_ = i_.getStack();
        assertEq(4, stack_.nbPages());
        assertEq(4, i_.getVariables().size());
        CustList<ViewVariable> v_ = i_.getVariables().get(3).getVars();
        assertEq(5, v_.size());
        assertEq("fct", v_.get(0).getName());
        assertEq(0, v_.get(0).getDeep());
        assertEq("", v_.get(0).getIndexClassName());
        assertEq(-1, v_.get(0).getIndex());
        assertEq("$core.Fct<int,int,int>", v_.get(0).getClassName());
        assertEq("$core.Fct<int,int,int>", v_.get(0).getWrapper().getValue().getClassName(cont_.getContext()));
        assertEq("t", v_.get(1).getName());
        assertEq(0, v_.get(1).getDeep());
        assertEq("", v_.get(1).getIndexClassName());
        assertEq(-1, v_.get(1).getIndex());
        assertEq("int", v_.get(1).getClassName());
        assertEq(2, toInt(v_.get(1).getWrapper().getValue()));
        assertEq("u", v_.get(2).getName());
        assertEq(0, v_.get(2).getDeep());
        assertEq("", v_.get(2).getIndexClassName());
        assertEq(-1, v_.get(2).getIndex());
        assertEq("int", v_.get(2).getClassName());
        assertEq(3, toInt(v_.get(2).getWrapper().getValue()));
        assertEq("t", v_.get(3).getName());
        assertEq(-1, v_.get(3).getDeep());
        assertEq("", v_.get(3).getIndexClassName());
        assertEq(-1, v_.get(3).getIndex());
        assertEq("int", v_.get(3).getClassName());
        assertEq(19, toInt(v_.get(3).getWrapper().getValue()));
        assertEq("u", v_.get(4).getName());
        assertEq(-1, v_.get(4).getDeep());
        assertEq("", v_.get(4).getIndexClassName());
        assertEq(-1, v_.get(4).getIndex());
        assertEq("int", v_.get(4).getClassName());
        assertEq(13, toInt(v_.get(4).getWrapper().getValue()));
        assertEq("pkg.Ex",i_.getVariables().get(3).getInstance().getClassName());
        assertEq("pkg.Ex",i_.getVariables().get(3).getInstance().getEval().getClassName(cont_.getContext()));
    }
    @Test
    public void test3() {
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
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",184,cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCallReturnValue i_ = dbgNormalInfo("pkg.Ex", id_, cont_,null);
        StackCall stack_ = i_.getStack();
        assertEq(4, stack_.nbPages());
        assertEq(4, i_.getVariables().size());
        CustList<ViewVariable> v_ = i_.getVariables().get(3).getVars();
        assertEq(7, v_.size());
        assertEq("t", v_.get(0).getName());
        assertEq(1, v_.get(0).getDeep());
        assertEq("", v_.get(0).getIndexClassName());
        assertEq(-1, v_.get(0).getIndex());
        assertEq("int", v_.get(0).getClassName());
        assertEq(2, toInt(v_.get(0).getWrapper().getValue()));
        assertEq("u", v_.get(1).getName());
        assertEq(1, v_.get(1).getDeep());
        assertEq("", v_.get(1).getIndexClassName());
        assertEq(-1, v_.get(1).getIndex());
        assertEq("int", v_.get(1).getClassName());
        assertEq(3, toInt(v_.get(1).getWrapper().getValue()));
        assertEq("fct", v_.get(2).getName());
        assertEq(0, v_.get(2).getDeep());
        assertEq("", v_.get(2).getIndexClassName());
        assertEq(-1, v_.get(2).getIndex());
        assertEq("$core.Fct<int,int,$core.Fct<int,int,int>>", v_.get(2).getClassName());
        assertEq("$core.Fct<int,int,$core.Fct<int,int,int>>", v_.get(2).getWrapper().getValue().getClassName(cont_.getContext()));
        assertEq("t", v_.get(3).getName());
        assertEq(0, v_.get(3).getDeep());
        assertEq("", v_.get(3).getIndexClassName());
        assertEq(-1, v_.get(3).getIndex());
        assertEq("int", v_.get(3).getClassName());
        assertEq(19, toInt(v_.get(3).getWrapper().getValue()));
        assertEq("u", v_.get(4).getName());
        assertEq(0, v_.get(4).getDeep());
        assertEq("", v_.get(4).getIndexClassName());
        assertEq(-1, v_.get(4).getIndex());
        assertEq("int", v_.get(4).getClassName());
        assertEq(13, toInt(v_.get(4).getWrapper().getValue()));
        assertEq("t", v_.get(5).getName());
        assertEq(-1, v_.get(5).getDeep());
        assertEq("", v_.get(5).getIndexClassName());
        assertEq(-1, v_.get(5).getIndex());
        assertEq("int", v_.get(5).getClassName());
        assertEq(15, toInt(v_.get(5).getWrapper().getValue()));
        assertEq("u", v_.get(6).getName());
        assertEq(-1, v_.get(6).getDeep());
        assertEq("", v_.get(6).getIndexClassName());
        assertEq(-1, v_.get(6).getIndex());
        assertEq("int", v_.get(6).getClassName());
        assertEq(17, toInt(v_.get(6).getWrapper().getValue()));
        assertEq("pkg.Ex",i_.getVariables().get(3).getInstance().getClassName());
        assertEq("pkg.Ex",i_.getVariables().get(3).getInstance().getEval().getClassName(cont_.getContext()));
    }
    @Test
    public void test4() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t = 4;\n");
        xml_.append("  iter(int i=2;3;1){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",88,cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCallReturnValue i_ = dbgNormalInfo("pkg.Ex", id_, cont_,null);
        StackCall stack_ = i_.getStack();
        assertEq(1, stack_.nbPages());
        assertEq(1, i_.getVariables().size());
        CustList<ViewVariable> v_ = i_.getVariables().get(0).getVars();
        assertEq(2, v_.size());
        assertEq("i", v_.get(0).getName());
        assertEq(-1, v_.get(0).getDeep());
        assertEq("int", v_.get(0).getIndexClassName());
        assertEq(0, v_.get(0).getIndex());
        assertEq("int", v_.get(0).getClassName());
        assertEq(2, toInt(v_.get(0).getWrapper().getValue()));
        assertEq("t", v_.get(1).getName());
        assertEq(-1, v_.get(1).getDeep());
        assertEq("", v_.get(1).getIndexClassName());
        assertEq(-1, v_.get(1).getIndex());
        assertEq("int", v_.get(1).getClassName());
        assertEq(4, toInt(v_.get(1).getWrapper().getValue()));
        assertEq("pkg.Ex",i_.getVariables().get(0).getInstance().getClassName());
        assertEq("",i_.getVariables().get(0).getInstance().getEval().getClassName(cont_.getContext()));
    }
    @Test
    public void test5() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t = 4;\n");
        xml_.append("  iter(int i=2;3;1){\n");
        xml_.append("   Fct<int,int> fct=u->{\n");
        xml_.append("    int s = 15;\n");
        xml_.append("    iter(int i=8;10;1){\n");
        xml_.append("     s+=i+#i;\n");
        xml_.append("    }\n");
        xml_.append("    return s;\n");
        xml_.append("   };\n");
        xml_.append("   t+=fct.call(i+45);\n");
        xml_.append("  }\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",155,cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCallReturnValue i_ = dbgNormalInfo("pkg.Ex", id_, cont_,null);
        StackCall stack_ = i_.getStack();
        assertEq(3, stack_.nbPages());
        assertEq(3, i_.getVariables().size());
        CustList<ViewVariable> v_ = i_.getVariables().get(2).getVars();
        assertEq(6, v_.size());
        assertEq("fct", v_.get(0).getName());
        assertEq(0, v_.get(0).getDeep());
        assertEq("", v_.get(0).getIndexClassName());
        assertEq(-1, v_.get(0).getIndex());
        assertEq("$core.Fct<int,int>", v_.get(0).getClassName());
        assertEq("$core.Fct<int,int>", v_.get(0).getWrapper().getValue().getClassName(cont_.getContext()));
        assertEq("i", v_.get(1).getName());
        assertEq(0, v_.get(1).getDeep());
        assertEq("int", v_.get(1).getIndexClassName());
        assertEq(0, v_.get(1).getIndex());
        assertEq("int", v_.get(1).getClassName());
        assertEq(2, toInt(v_.get(1).getWrapper().getValue()));
        assertEq("t", v_.get(2).getName());
        assertEq(0, v_.get(2).getDeep());
        assertEq("", v_.get(2).getIndexClassName());
        assertEq(-1, v_.get(2).getIndex());
        assertEq("int", v_.get(2).getClassName());
        assertEq(4, toInt(v_.get(2).getWrapper().getValue()));
        assertEq("i", v_.get(3).getName());
        assertEq(-1, v_.get(3).getDeep());
        assertEq("int", v_.get(3).getIndexClassName());
        assertEq(0, v_.get(3).getIndex());
        assertEq("int", v_.get(3).getClassName());
        assertEq(8, toInt(v_.get(3).getWrapper().getValue()));
        assertEq("s", v_.get(4).getName());
        assertEq(-1, v_.get(4).getDeep());
        assertEq("", v_.get(4).getIndexClassName());
        assertEq(-1, v_.get(4).getIndex());
        assertEq("int", v_.get(4).getClassName());
        assertEq(15, toInt(v_.get(4).getWrapper().getValue()));
        assertEq("u", v_.get(5).getName());
        assertEq(-1, v_.get(5).getDeep());
        assertEq("", v_.get(5).getIndexClassName());
        assertEq(-1, v_.get(5).getIndex());
        assertEq("int", v_.get(5).getClassName());
        assertEq(47, toInt(v_.get(5).getWrapper().getValue()));
        assertEq("pkg.Ex",i_.getVariables().get(2).getInstance().getClassName());
        assertEq("",i_.getVariables().get(2).getInstance().getEval().getClassName(cont_.getContext()));
    }
    @Test
    public void test6() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int t = 4;\n");
        xml_.append("  iter(int i=2;3;1){\n");
        xml_.append("   Fct<int,int> fct=u->{\n");
        xml_.append("    int s = 15;\n");
        xml_.append("    iter(int i=8;10;1){\n");
        xml_.append("     s+=i+#i;\n");
        xml_.append("    }\n");
        xml_.append("    return s;\n");
        xml_.append("   };\n");
        xml_.append("   t+=fct.call(i+45);\n");
        xml_.append("  }\n");
        xml_.append("  return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.getContext().getClasses().getDebugMapping().getBreakPointsBlock().toggleBreakPoint("pkg/Ex",155,cont_);
        MethodId id_ = getMethodId("exmeth");
        StackCallReturnValue i_ = dbgContinueNormalValue(dbgNormal("pkg.Ex", id_, cont_), cont_.getContext());
        StackCall stack_ = i_.getStack();
        assertEq(3, stack_.nbPages());
        assertEq(3, i_.getVariables().size());
        CustList<ViewVariable> v_ = i_.getVariables().get(2).getVars();
        assertEq(6, v_.size());
        assertEq("fct", v_.get(0).getName());
        assertEq(0, v_.get(0).getDeep());
        assertEq("", v_.get(0).getIndexClassName());
        assertEq(-1, v_.get(0).getIndex());
        assertEq("$core.Fct<int,int>", v_.get(0).getClassName());
        assertEq("$core.Fct<int,int>", v_.get(0).getWrapper().getValue().getClassName(cont_.getContext()));
        assertEq("i", v_.get(1).getName());
        assertEq(0, v_.get(1).getDeep());
        assertEq("int", v_.get(1).getIndexClassName());
        assertEq(0, v_.get(1).getIndex());
        assertEq("int", v_.get(1).getClassName());
        assertEq(2, toInt(v_.get(1).getWrapper().getValue()));
        assertEq("t", v_.get(2).getName());
        assertEq(0, v_.get(2).getDeep());
        assertEq("", v_.get(2).getIndexClassName());
        assertEq(-1, v_.get(2).getIndex());
        assertEq("int", v_.get(2).getClassName());
        assertEq(4, toInt(v_.get(2).getWrapper().getValue()));
        assertEq("i", v_.get(3).getName());
        assertEq(-1, v_.get(3).getDeep());
        assertEq("int", v_.get(3).getIndexClassName());
        assertEq(1, v_.get(3).getIndex());
        assertEq("int", v_.get(3).getClassName());
        assertEq(9, toInt(v_.get(3).getWrapper().getValue()));
        assertEq("s", v_.get(4).getName());
        assertEq(-1, v_.get(4).getDeep());
        assertEq("", v_.get(4).getIndexClassName());
        assertEq(-1, v_.get(4).getIndex());
        assertEq("int", v_.get(4).getClassName());
        assertEq(25, toInt(v_.get(4).getWrapper().getValue()));
        assertEq("u", v_.get(5).getName());
        assertEq(-1, v_.get(5).getDeep());
        assertEq("", v_.get(5).getIndexClassName());
        assertEq(-1, v_.get(5).getIndex());
        assertEq("int", v_.get(5).getClassName());
        assertEq(47, toInt(v_.get(5).getWrapper().getValue()));
        assertEq("pkg.Ex",i_.getVariables().get(2).getInstance().getClassName());
        assertEq("",i_.getVariables().get(2).getInstance().getEval().getClassName(cont_.getContext()));
    }
}
