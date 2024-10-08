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

public final class ProcessDbgIterableTableTest extends ProcessDbgCommon {
    @Test
    public void test1() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  CustTable<Number,Number> inst=new CustTable<Number,Number>();\n");
        xml_.append("  int res;\n");
        xml_.append("  inst.add(3,5);\n");
        xml_.append("  inst.add(8,1);\n");
        xml_.append("  inst.add(2,6);\n");
        xml_.append("  for(Number f , Number s: inst){\n");
        xml_.append("   res += f.intValue()+s.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",187);
        MethodId id_ = getMethodId("m");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(187, now(stack_));
        Struct s_ = stack_.getLastPage().getContentEx().getRefParams().getVal("res").getValue(stack_, cont_.getContext());
        assertEq(0, toInt(s_));
    }
    @Test
    public void test2() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  CustTable<Number,Number> inst=new CustTable<Number,Number>();\n");
        xml_.append("  int res;\n");
        xml_.append("  inst.add(3,5);\n");
        xml_.append("  inst.add(8,1);\n");
        xml_.append("  inst.add(2,6);\n");
        xml_.append("  for(Number f , Number s: inst){\n");
        xml_.append("   res += f.intValue()+s.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",187);
        MethodId id_ = getMethodId("m");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertEq(1, next_.nbPages());
        assertEq(187, now(next_));
        Struct s_ = next_.getLastPage().getContentEx().getRefParams().getVal("res").getValue(next_, cont_.getContext());
        assertEq(8, toInt(s_));
    }
    @Test
    public void test3() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  CustTable<Number,Number> inst=new CustTable<Number,Number>();\n");
        xml_.append("  int res;\n");
        xml_.append("  inst.add(3,5);\n");
        xml_.append("  inst.add(8,1);\n");
        xml_.append("  inst.add(2,6);\n");
        xml_.append("  for(Number f , Number s: inst){\n");
        xml_.append("   res += f.intValue()+s.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",187);
        MethodId id_ = getMethodId("m");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(dbgContinueNormal(stack_, cont_.getContext()), cont_.getContext());
        assertEq(1, next_.nbPages());
        assertEq(187, now(next_));
        Struct s_ = next_.getLastPage().getContentEx().getRefParams().getVal("res").getValue(next_, cont_.getContext());
        assertEq(17, toInt(s_));
    }
    @Test
    public void test4() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  CustTable<Number,Number> inst=new CustTable<Number,Number>();\n");
        xml_.append("  int res;\n");
        xml_.append("  inst.add(3,5);\n");
        xml_.append("  inst.add(8,1);\n");
        xml_.append("  inst.add(2,6);\n");
        xml_.append("  for(Number f , Number s: inst){\n");
        xml_.append("   res += f.intValue()+s.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",187);
        MethodId id_ = getMethodId("m");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(dbgContinueNormal(dbgContinueNormal(stack_, cont_.getContext()), cont_.getContext()), cont_.getContext());
        assertEq(0, next_.nbPages());
    }
    @Test
    public void test5() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  CustTable<Number,Number> inst=new CustTable<Number,Number>();\n");
        xml_.append("  int res;\n");
        xml_.append("  inst.add(3,5);\n");
        xml_.append("  inst.add(8,1);\n");
        xml_.append("  inst.add(2,6);\n");
        xml_.append("  for(Number f , Number s: inst){\n");
        xml_.append("   res += f.intValue()+s.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",197);
        MethodId id_ = getMethodId("m");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(197, now(stack_));
        Struct s_ = stack_.getLastPage().getContentEx().getRefParams().getVal("res").getValue(stack_, cont_.getContext());
        assertEq(0, toInt(s_));
    }
    @Test
    public void test6() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  CustTable<Number,Number> inst=new CustTable<Number,Number>();\n");
        xml_.append("  int res;\n");
        xml_.append("  inst.add(3,5);\n");
        xml_.append("  inst.add(8,1);\n");
        xml_.append("  inst.add(2,6);\n");
        xml_.append("  for(Number f , Number s: inst){\n");
        xml_.append("   res += f.intValue()+s.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",197);
        MethodId id_ = getMethodId("m");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertEq(1, next_.nbPages());
        assertEq(197, now(next_));
        Struct s_ = next_.getLastPage().getContentEx().getRefParams().getVal("res").getValue(next_, cont_.getContext());
        assertEq(8, toInt(s_));
    }
    @Test
    public void test7() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  CustTable<Number,Number> inst=new CustTable<Number,Number>();\n");
        xml_.append("  int res;\n");
        xml_.append("  inst.add(3,5);\n");
        xml_.append("  inst.add(8,1);\n");
        xml_.append("  inst.add(2,6);\n");
        xml_.append("  for(Number f , Number s: inst){\n");
        xml_.append("   res += f.intValue()+s.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",197);
        MethodId id_ = getMethodId("m");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(dbgContinueNormal(stack_, cont_.getContext()), cont_.getContext());
        assertEq(1, next_.nbPages());
        assertEq(197, now(next_));
        Struct s_ = next_.getLastPage().getContentEx().getRefParams().getVal("res").getValue(next_, cont_.getContext());
        assertEq(17, toInt(s_));
    }
    @Test
    public void test8() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  CustTable<Number,Number> inst=new CustTable<Number,Number>();\n");
        xml_.append("  int res;\n");
        xml_.append("  inst.add(3,5);\n");
        xml_.append("  inst.add(8,1);\n");
        xml_.append("  inst.add(2,6);\n");
        xml_.append("  for(Number f , Number s: inst){\n");
        xml_.append("   res += f.intValue()+s.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",197);
        MethodId id_ = getMethodId("m");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(dbgContinueNormal(dbgContinueNormal(stack_, cont_.getContext()), cont_.getContext()), cont_.getContext());
        assertEq(1, next_.nbPages());
        assertEq(197, now(next_));
        Struct s_ = next_.getLastPage().getContentEx().getRefParams().getVal("res").getValue(next_, cont_.getContext());
        assertEq(25, toInt(s_));
    }
    @Test
    public void test9() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  CustTable<Number,Number> inst=new CustTable<Number,Number>();\n");
        xml_.append("  int res;\n");
        xml_.append("  inst.add(3,5);\n");
        xml_.append("  inst.add(8,1);\n");
        xml_.append("  inst.add(2,6);\n");
        xml_.append("  for(Number f , Number s: inst){\n");
        xml_.append("   res += f.intValue()+s.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",197);
        MethodId id_ = getMethodId("m");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(dbgContinueNormal(dbgContinueNormal(dbgContinueNormal(stack_, cont_.getContext()), cont_.getContext()), cont_.getContext()), cont_.getContext());
        assertEq(0, next_.nbPages());
    }
    @Test
    public void test10() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  CustTable<Number,Number> inst=new CustTable<Number,Number>();\n");
        xml_.append("  int res;\n");
        xml_.append("  inst.add(3,5);\n");
        xml_.append("  inst.add(8,1);\n");
        xml_.append("  inst.add(2,6);\n");
        xml_.append("  for(Number f , Number s: inst){\n");
        xml_.append("   res += f.intValue()+s.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",185);
        MethodId id_ = getMethodId("m");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(185, now(stack_));
        Struct s_ = stack_.getLastPage().getContentEx().getRefParams().getVal("res").getValue(stack_, cont_.getContext());
        assertEq(0, toInt(s_));
    }
    @Test
    public void test11() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  CustTable<Number,Number> inst=new CustTable<Number,Number>();\n");
        xml_.append("  int res;\n");
        xml_.append("  inst.add(3,5);\n");
        xml_.append("  inst.add(8,1);\n");
        xml_.append("  inst.add(2,6);\n");
        xml_.append("  for(Number f , Number s: inst){\n");
        xml_.append("   res += f.intValue()+s.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",185);
        MethodId id_ = getMethodId("m");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertEq(1, next_.nbPages());
        assertEq(185, now(next_));
        Struct s_ = next_.getLastPage().getContentEx().getRefParams().getVal("res").getValue(next_, cont_.getContext());
        assertEq(8, toInt(s_));
    }
    @Test
    public void test12() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  CustTable<Number,Number> inst=new CustTable<Number,Number>();\n");
        xml_.append("  int res;\n");
        xml_.append("  inst.add(3,5);\n");
        xml_.append("  inst.add(8,1);\n");
        xml_.append("  inst.add(2,6);\n");
        xml_.append("  for(Number f , Number s: inst){\n");
        xml_.append("   res += f.intValue()+s.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",185);
        MethodId id_ = getMethodId("m");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(dbgContinueNormal(stack_, cont_.getContext()), cont_.getContext());
        assertEq(1, next_.nbPages());
        assertEq(185, now(next_));
        Struct s_ = next_.getLastPage().getContentEx().getRefParams().getVal("res").getValue(next_, cont_.getContext());
        assertEq(17, toInt(s_));
    }
    @Test
    public void test13() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  CustTable<Number,Number> inst=new CustTable<Number,Number>();\n");
        xml_.append("  int res;\n");
        xml_.append("  inst.add(3,5);\n");
        xml_.append("  inst.add(8,1);\n");
        xml_.append("  inst.add(2,6);\n");
        xml_.append("  for(Number f , Number s: inst){\n");
        xml_.append("   res += f.intValue()+s.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",185);
        MethodId id_ = getMethodId("m");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(dbgContinueNormal(dbgContinueNormal(stack_, cont_.getContext()), cont_.getContext()), cont_.getContext());
        assertEq(0, next_.nbPages());
    }
    @Test
    public void test14() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  CustTable<Number,Number> inst=new CustTable<Number,Number>();\n");
        xml_.append("  int res;\n");
        xml_.append("  inst.add(3,5);\n");
        xml_.append("  inst.add(8,1);\n");
        xml_.append("  inst.add(2,6);\n");
        xml_.append("  for(Number f , Number s: inst){\n");
        xml_.append("   res += f.intValue()+s.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",196);
        MethodId id_ = getMethodId("m");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(196, now(stack_));
        Struct s_ = stack_.getLastPage().getContentEx().getRefParams().getVal("res").getValue(stack_, cont_.getContext());
        assertEq(0, toInt(s_));
    }
    @Test
    public void test15() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  CustTable<Number,Number> inst=new CustTable<Number,Number>();\n");
        xml_.append("  int res;\n");
        xml_.append("  inst.add(3,5);\n");
        xml_.append("  inst.add(8,1);\n");
        xml_.append("  inst.add(2,6);\n");
        xml_.append("  for(Number f , Number s: inst){\n");
        xml_.append("   res += f.intValue()+s.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",196);
        MethodId id_ = getMethodId("m");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertEq(1, next_.nbPages());
        assertEq(196, now(next_));
        Struct s_ = next_.getLastPage().getContentEx().getRefParams().getVal("res").getValue(next_, cont_.getContext());
        assertEq(8, toInt(s_));
    }
    @Test
    public void test16() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  CustTable<Number,Number> inst=new CustTable<Number,Number>();\n");
        xml_.append("  int res;\n");
        xml_.append("  inst.add(3,5);\n");
        xml_.append("  inst.add(8,1);\n");
        xml_.append("  inst.add(2,6);\n");
        xml_.append("  for(Number f , Number s: inst){\n");
        xml_.append("   res += f.intValue()+s.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",196);
        MethodId id_ = getMethodId("m");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(dbgContinueNormal(stack_, cont_.getContext()), cont_.getContext());
        assertEq(1, next_.nbPages());
        assertEq(196, now(next_));
        Struct s_ = next_.getLastPage().getContentEx().getRefParams().getVal("res").getValue(next_, cont_.getContext());
        assertEq(17, toInt(s_));
    }
    @Test
    public void test17() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  CustTable<Number,Number> inst=new CustTable<Number,Number>();\n");
        xml_.append("  int res;\n");
        xml_.append("  inst.add(3,5);\n");
        xml_.append("  inst.add(8,1);\n");
        xml_.append("  inst.add(2,6);\n");
        xml_.append("  for(Number f , Number s: inst){\n");
        xml_.append("   res += f.intValue()+s.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",196);
        MethodId id_ = getMethodId("m");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(dbgContinueNormal(dbgContinueNormal(stack_, cont_.getContext()), cont_.getContext()), cont_.getContext());
        assertEq(0, next_.nbPages());
    }
    @Test
    public void test18() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  CustTable<Number,Number> inst=new CustTable<Number,Number>();\n");
        xml_.append("  int res;\n");
        xml_.append("  inst.add(3,5);\n");
        xml_.append("  inst.add(8,1);\n");
        xml_.append("  inst.add(2,6);\n");
        xml_.append("  for(Number f , Number s: inst){\n");
        xml_.append("   res += f.intValue()+s.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",199);
        MethodId id_ = getMethodId("m");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(199, now(stack_));
        assertEq(1, stack_.getLastPage().sizeEl());
        Struct s_ = stack_.getLastPage().getContentEx().getRefParams().getVal("res").getValue(stack_, cont_.getContext());
        assertEq(0, toInt(s_));
    }
    @Test
    public void test19() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  CustTable<Number,Number> inst=new CustTable<Number,Number>();\n");
        xml_.append("  int res;\n");
        xml_.append("  inst.add(3,5);\n");
        xml_.append("  inst.add(8,1);\n");
        xml_.append("  inst.add(2,6);\n");
        xml_.append("  for(Number f , Number s: inst){\n");
        xml_.append("   res += f.intValue()+s.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",199);
        MethodId id_ = getMethodId("m");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertEq(0, next_.nbPages());
    }
    @Test
    public void test20() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  CustTable<Number,Number> inst=new CustTable<Number,Number>();\n");
        xml_.append("  int res;\n");
        xml_.append("  inst.add(3,5);\n");
        xml_.append("  inst.add(8,1);\n");
        xml_.append("  inst.add(2,6);\n");
        xml_.append("  for(Number f , Number s: inst){\n");
        xml_.append("   res += f.intValue()+s.intValue();\n");
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
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",174);
        MethodId id_ = getMethodId("n");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(0, stack_.nbPages());
    }
    @Test
    public void test21() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  CustTable<Number,Number> inst=new CustTable<Number,Number>();\n");
        xml_.append("  int res;\n");
        xml_.append("  inst.add(3,5);\n");
        xml_.append("  inst.add(8,1);\n");
        xml_.append("  inst.add(2,6);\n");
        xml_.append("  for(Number f , Number s: inst){\n");
        xml_.append("   res += f.intValue()+s.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",174);
        MethodId id_ = getMethodId("m");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertTrue(stack_.getLastPage().noBlock());
        assertEq(2, stack_.getLastPage().sizeEl());
        assertEq("pkg.CustTable<$core.Number,$core.Number>",stack_.getLastPage().getInternVars().getVal(cont_.getContext().getClasses().getIteratorTableVarCust()).getStruct().getClassName(cont_.getContext()));
        Struct s_ = stack_.getLastPage().getContentEx().getRefParams().getVal("res").getValue(stack_, cont_.getContext());
        assertEq(0, toInt(s_));
    }
    @Test
    public void test22() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int m(){\n");
        xml_.append("  CustTable<Number,Number> inst=new CustTable<Number,Number>();\n");
        xml_.append("  int res;\n");
        xml_.append("  inst.add(3,5);\n");
        xml_.append("  inst.add(8,1);\n");
        xml_.append("  inst.add(2,6);\n");
        xml_.append("  for(Number f , Number s: inst){\n");
        xml_.append("   res += f.intValue()+s.intValue();\n");
        xml_.append("  }\n");
        xml_.append("  return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",174);
        MethodId id_ = getMethodId("m");
        StackCall stack_ = dbgNormal("pkg.Ex", id_, cont_);
        StackCall next_ = dbgContinueNormal(stack_, cont_.getContext());
        assertEq(0, next_.nbPages());
    }
    @Test
    public void test231() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {public static int m(){CustTable<Number,Number> inst=new CustTable<Number,Number>();int res;inst.add(3,5);inst.add(8,1);inst.add(2,6);for(Number f , Number s: inst){res += f.intValue()+s.intValue();}return res;}}\n");
        files_.put("pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        ResultContext cont_ = ctxLgReadOnlyOkQuick(files_);
        cont_.toggleBreakPoint("pkg/Ex",183);
        analyze(cont_,"res == 0","pkg/Ex",183);
        MethodId id_ = getMethodId("m");
        StackCall stack_ = dbgNormalCheck("pkg.Ex", id_, cont_);
        assertEq(1, stack_.nbPages());
        assertEq(183, now(stack_));
        assertEq(0, dbgContinueNormal(stack_, cont_.getContext()).nbPages());
    }
    static void analyze(ResultContext _cont, String _cond, String _file, int _caret) {
        FileBlock bl_ = _cont.getPageEl().getPreviousFilesBodies().getVal(_file);
        BreakPointBlockPair pair_ = _cont.getPair(_cont.getFiles().getVal(bl_), _caret);
        pair_.getValue().getResultStd().analyze(pair_,_cond,"","",_cont,new DefContextGenerator());
    }
}
