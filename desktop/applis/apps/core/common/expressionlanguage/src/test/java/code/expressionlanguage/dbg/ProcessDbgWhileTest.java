package code.expressionlanguage.dbg;

import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.structs.FieldableStruct;
import code.expressionlanguage.structs.Struct;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessDbgWhileTest extends ProcessDbgCommon {
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
        xml_.append("  var v = inst.iterator();\n");
        xml_.append("  while (v.hasNext()) {\n");
        xml_.append("   int e=v.next();\n");
        xml_.append("   res+=e.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",185);
        MethodId id_ = getMethodId("m");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(184, now(stack_));
        FieldableStruct s_ = (FieldableStruct) stack_.getLastPage().getContentEx().getRefParams().getVal("v").getValue(stack_, cont_.getContext());
        Struct v_ = s_.getEntryStruct(new ClassField("pkg.CustIter", "index")).getStruct();
        assertEq(0, toInt(v_));
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
        xml_.append("  var v = inst.iterator();\n");
        xml_.append("  while (v.hasNext()) {\n");
        xml_.append("   int e=v.next();\n");
        xml_.append("   res+=e.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",185);
        MethodId id_ = getMethodId("m");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertEq(1, next_.nbPages());
        assertEq(184, now(next_));
        FieldableStruct s_ = (FieldableStruct) next_.getLastPage().getContentEx().getRefParams().getVal("v").getValue(next_, cont_.getContext());
        Struct v_ = s_.getEntryStruct(new ClassField("pkg.CustIter", "index")).getStruct();
        assertEq(1, toInt(v_));
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
        xml_.append("  var v = inst.iterator();\n");
        xml_.append("  while (v.hasNext()) {\n");
        xml_.append("   int e=v.next();\n");
        xml_.append("   res+=e.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",185);
        MethodId id_ = getMethodId("m");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(dbgContinueNormal(stack_, cont_.getContext()), cont_.getContext());
        assertEq(1, next_.nbPages());
        assertEq(184, now(next_));
        FieldableStruct s_ = (FieldableStruct) next_.getLastPage().getContentEx().getRefParams().getVal("v").getValue(next_, cont_.getContext());
        Struct v_ = s_.getEntryStruct(new ClassField("pkg.CustIter", "index")).getStruct();
        assertEq(2, toInt(v_));
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
        xml_.append("  var v = inst.iterator();\n");
        xml_.append("  while (v.hasNext()) {\n");
        xml_.append("   int e=v.next();\n");
        xml_.append("   res+=e.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",185);
        MethodId id_ = getMethodId("m");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(dbgContinueNormal(dbgContinueNormal(stack_, cont_.getContext()), cont_.getContext()), cont_.getContext());
        assertEq(1, next_.nbPages());
        assertEq(184, now(next_));
        FieldableStruct s_ = (FieldableStruct) next_.getLastPage().getContentEx().getRefParams().getVal("v").getValue(next_, cont_.getContext());
        Struct v_ = s_.getEntryStruct(new ClassField("pkg.CustIter", "index")).getStruct();
        assertEq(3, toInt(v_));
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
        xml_.append("  var v = inst.iterator();\n");
        xml_.append("  while (v.hasNext()) {\n");
        xml_.append("   int e=v.next();\n");
        xml_.append("   res+=e.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",185);
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
        xml_.append("  var v = inst.iterator();\n");
        xml_.append("  while (v.hasNext()) {\n");
        xml_.append("   int e=v.next();\n");
        xml_.append("   res+=e.intValue();continue;\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",185);
        MethodId id_ = getMethodId("m");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(184, now(stack_));
        FieldableStruct s_ = (FieldableStruct) stack_.getLastPage().getContentEx().getRefParams().getVal("v").getValue(stack_, cont_.getContext());
        Struct v_ = s_.getEntryStruct(new ClassField("pkg.CustIter", "index")).getStruct();
        assertEq(0, toInt(v_));
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
        xml_.append("  var v = inst.iterator();\n");
        xml_.append("  while (v.hasNext()) {\n");
        xml_.append("   int e=v.next();\n");
        xml_.append("   res+=e.intValue();continue;\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",185);
        MethodId id_ = getMethodId("m");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertEq(1, next_.nbPages());
        assertEq(184, now(next_));
        FieldableStruct s_ = (FieldableStruct) next_.getLastPage().getContentEx().getRefParams().getVal("v").getValue(next_, cont_.getContext());
        Struct v_ = s_.getEntryStruct(new ClassField("pkg.CustIter", "index")).getStruct();
        assertEq(1, toInt(v_));
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
        xml_.append("  var v = inst.iterator();\n");
        xml_.append("  while (v.hasNext()) {\n");
        xml_.append("   int e=v.next();\n");
        xml_.append("   res+=e.intValue();continue;\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",185);
        MethodId id_ = getMethodId("m");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(dbgContinueNormal(stack_, cont_.getContext()), cont_.getContext());
        assertEq(1, next_.nbPages());
        assertEq(184, now(next_));
        FieldableStruct s_ = (FieldableStruct) next_.getLastPage().getContentEx().getRefParams().getVal("v").getValue(next_, cont_.getContext());
        Struct v_ = s_.getEntryStruct(new ClassField("pkg.CustIter", "index")).getStruct();
        assertEq(2, toInt(v_));
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
        xml_.append("  var v = inst.iterator();\n");
        xml_.append("  while (v.hasNext()) {\n");
        xml_.append("   int e=v.next();\n");
        xml_.append("   res+=e.intValue();continue;\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",185);
        MethodId id_ = getMethodId("m");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(dbgContinueNormal(dbgContinueNormal(stack_, cont_.getContext()), cont_.getContext()), cont_.getContext());
        assertEq(1, next_.nbPages());
        assertEq(184, now(next_));
        FieldableStruct s_ = (FieldableStruct) next_.getLastPage().getContentEx().getRefParams().getVal("v").getValue(next_, cont_.getContext());
        Struct v_ = s_.getEntryStruct(new ClassField("pkg.CustIter", "index")).getStruct();
        assertEq(3, toInt(v_));
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
        xml_.append("  var v = inst.iterator();\n");
        xml_.append("  while (v.hasNext()) {\n");
        xml_.append("   int e=v.next();\n");
        xml_.append("   res+=e.intValue();continue;\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put("pkg/Ex", xml_.toString());
        ResultContext cont_ = ctxLgReadOnlyOkQuick("en",files_);
        cont_.toggleBreakPoint("pkg/Ex",185);
        MethodId id_ = getMethodId("m");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(dbgContinueNormal(dbgContinueNormal(dbgContinueNormal(stack_, cont_.getContext()), cont_.getContext()), cont_.getContext()), cont_.getContext());
        assertEq(0, next_.nbPages());
    }
}
