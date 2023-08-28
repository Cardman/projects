package code.expressionlanguage.dbg;

import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.options.ResultContext;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgIterLoopTest extends ProcessDbgCommon {
    @Test
    public void test1() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  pkg.CustList<int> inst=new pkg.CustList<int>();\n");
        xml_.append("  int res;\n");
        xml_.append("  inst.add(3i);\n");
        xml_.append("  inst.add(1i);\n");
        xml_.append("  inst.add(2i);\n");
        xml_.append("  for(int e:inst){\n");
        xml_.append("   res+=e.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",159);
        MethodId id_ = getMethodId("m");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(159, now(stack_));
    }
    @Test
    public void test2() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  pkg.CustList<int> inst=new pkg.CustList<int>();\n");
        xml_.append("  int res;\n");
        xml_.append("  inst.add(3i);\n");
        xml_.append("  inst.add(1i);\n");
        xml_.append("  inst.add(2i);\n");
        xml_.append("  for(int e:inst){\n");
        xml_.append("   res+=e.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",159);
        MethodId id_ = getMethodId("m");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertEq(1, next_.nbPages());
        assertEq(159, now(next_));
    }
    @Test
    public void test3() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  pkg.CustList<int> inst=new pkg.CustList<int>();\n");
        xml_.append("  int res;\n");
        xml_.append("  inst.add(3i);\n");
        xml_.append("  inst.add(1i);\n");
        xml_.append("  inst.add(2i);\n");
        xml_.append("  for(int e:inst){\n");
        xml_.append("   res+=e.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",159);
        MethodId id_ = getMethodId("m");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(dbgContinueNormal(stack_, cont_.getContext()), cont_.getContext());
        assertEq(1, next_.nbPages());
        assertEq(159, now(next_));
    }
    @Test
    public void test4() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  pkg.CustList<int> inst=new pkg.CustList<int>();\n");
        xml_.append("  int res;\n");
        xml_.append("  inst.add(3i);\n");
        xml_.append("  inst.add(1i);\n");
        xml_.append("  inst.add(2i);\n");
        xml_.append("  for(int e:inst){\n");
        xml_.append("   res+=e.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",159);
        MethodId id_ = getMethodId("m");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(dbgContinueNormal(dbgContinueNormal(stack_, cont_.getContext()), cont_.getContext()), cont_.getContext());
        assertEq(1, next_.nbPages());
        assertEq(159, now(next_));
    }
    @Test
    public void test5() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  pkg.CustList<int> inst=new pkg.CustList<int>();\n");
        xml_.append("  int res;\n");
        xml_.append("  inst.add(3i);\n");
        xml_.append("  inst.add(1i);\n");
        xml_.append("  inst.add(2i);\n");
        xml_.append("  for(int e:inst){\n");
        xml_.append("   res+=e.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",159);
        MethodId id_ = getMethodId("m");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(dbgContinueNormal(dbgContinueNormal(dbgContinueNormal(stack_, cont_.getContext()), cont_.getContext()), cont_.getContext()), cont_.getContext());
        assertEq(0, next_.nbPages());
    }
    @Test
    public void test6() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  pkg.CustList<int> inst=new pkg.CustList<int>();\n");
        xml_.append("  int res;\n");
        xml_.append("  inst.add(3i);\n");
        xml_.append("  inst.add(1i);\n");
        xml_.append("  inst.add(2i);\n");
        xml_.append("  for(int e:inst){\n");
        xml_.append("   res+=e.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",160);
        MethodId id_ = getMethodId("m");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(160, now(stack_));
        assertEq(1, stack_.getLastPage().sizeEl());
    }
    @Test
    public void test7() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  pkg.CustList<int> inst=new pkg.CustList<int>();\n");
        xml_.append("  int res;\n");
        xml_.append("  inst.add(3i);\n");
        xml_.append("  inst.add(1i);\n");
        xml_.append("  inst.add(2i);\n");
        xml_.append("  for(int e:inst){\n");
        xml_.append("   res+=e.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",160);
        MethodId id_ = getMethodId("m");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
    @Test
    public void test8() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  pkg.CustList<int> inst=new pkg.CustList<int>();\n");
        xml_.append("  int res;\n");
        xml_.append("  inst.add(3i);\n");
        xml_.append("  inst.add(1i);\n");
        xml_.append("  inst.add(2i);\n");
        xml_.append("  for(int e:inst){\n");
        xml_.append("   res+=e.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",158);
        MethodId id_ = getMethodId("m");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(158, now(stack_));
    }
    @Test
    public void test9() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  pkg.CustList<int> inst=new pkg.CustList<int>();\n");
        xml_.append("  int res;\n");
        xml_.append("  inst.add(3i);\n");
        xml_.append("  inst.add(1i);\n");
        xml_.append("  inst.add(2i);\n");
        xml_.append("  for(int e:inst){\n");
        xml_.append("   res+=e.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",158);
        MethodId id_ = getMethodId("m");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertEq(1, next_.nbPages());
        assertEq(158, now(next_));
    }
    @Test
    public void test10() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  pkg.CustList<int> inst=new pkg.CustList<int>();\n");
        xml_.append("  int res;\n");
        xml_.append("  inst.add(3i);\n");
        xml_.append("  inst.add(1i);\n");
        xml_.append("  inst.add(2i);\n");
        xml_.append("  for(int e:inst){\n");
        xml_.append("   res+=e.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",158);
        MethodId id_ = getMethodId("m");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(dbgContinueNormal(stack_, cont_.getContext()), cont_.getContext());
        assertEq(1, next_.nbPages());
        assertEq(158, now(next_));
    }
    @Test
    public void test11() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  pkg.CustList<int> inst=new pkg.CustList<int>();\n");
        xml_.append("  int res;\n");
        xml_.append("  inst.add(3i);\n");
        xml_.append("  inst.add(1i);\n");
        xml_.append("  inst.add(2i);\n");
        xml_.append("  for(int e:inst){\n");
        xml_.append("   res+=e.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",158);
        MethodId id_ = getMethodId("m");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(dbgContinueNormal(dbgContinueNormal(stack_, cont_.getContext()), cont_.getContext()), cont_.getContext());
        assertEq(0, next_.nbPages());
    }
    @Test
    public void test12() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  pkg.CustList<int> inst=new pkg.CustList<int>();\n");
        xml_.append("  int res;\n");
        xml_.append("  inst.add(3i);\n");
        xml_.append("  inst.add(1i);\n");
        xml_.append("  inst.add(2i);\n");
        xml_.append("  for(int e:inst){\n");
        xml_.append("   res+=e.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append(" static int n(){\n");
        xml_.append("  return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",150);
        MethodId id_ = getMethodId("n");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test13() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  pkg.CustList<int> inst=new pkg.CustList<int>();\n");
        xml_.append("  int res;\n");
        xml_.append("  inst.add(3i);\n");
        xml_.append("  inst.add(1i);\n");
        xml_.append("  inst.add(2i);\n");
        xml_.append("  for(int e:inst){\n");
        xml_.append("   res+=e.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",150);
        MethodId id_ = getMethodId("m");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertTrue(stack_.getLastPage().noBlock());
        assertEq(2, stack_.getLastPage().sizeEl());
        assertEq("pkg.CustList<int>",stack_.getLastPage().getInternVars().getVal(cont_.getContext().getClasses().getIteratorVarCust()).getStruct().getClassName(cont_.getContext()));
    }
    @Test
    public void test14() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" static int m(){\n");
        xml_.append("  pkg.CustList<int> inst=new pkg.CustList<int>();\n");
        xml_.append("  int res;\n");
        xml_.append("  inst.add(3i);\n");
        xml_.append("  inst.add(1i);\n");
        xml_.append("  inst.add(2i);\n");
        xml_.append("  for(int e:inst){\n");
        xml_.append("   res+=e.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",150);
        MethodId id_ = getMethodId("m");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
}
