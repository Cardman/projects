package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;

import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.ExecForIterativeLoop;
import code.expressionlanguage.exec.coverage.*;
import code.expressionlanguage.functionid.MethodId;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringMap;
import org.junit.Test;


public final class CoverageTest extends ProcessMethodCommon {
    private static final String CUST_ITER_PATH = "src/pkg/CustIter";
    private static final String CUST_LIST_PATH = "src/pkg/CustList";
    private static final String CUST_ITER_TABLE_PATH = "src/pkg/CustIterTable";
    private static final String CUST_PAIR_PATH = "src/pkg/CustPair";
    private static final String CUST_TABLE_PATH = "src/pkg/CustTable";
    @Test
    public void coverage2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  int[] t = s == 1 ?{4i}:{6i};\n");
        xml_.append("  return t[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = covValEn(files_);
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, cont_);
        assertEq(6, sizeCovers(cont_));
        assertEq(0, sizeCovers(cont_,0));
        assertEq(0, sizeCovers(cont_,1));
        assertEq(3, sizeCovers(cont_,2));
        assertEq(0, sizeCovers(cont_,3));
        assertEq(10, sizeCovers(cont_,4));
        assertTrue(covers(cont_,4,2).isFullCovered());
        assertTrue(covers(cont_,4,2).isPartialCovered());
        AbstractCoverageResult value_ = covers(cont_,4,3);
        assertTrue(value_ instanceof AbstractCoverageResult);
        assertTrue(!value_.isFullCovered());
        assertTrue(value_.isPartialCovered());
        assertTrue(((BooleanCoverageResult)value_).isCoverBcTrue());
        assertTrue(!((BooleanCoverageResult)value_).isCoverBcFalse());
    }
    @Test
    public void coverage3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  int[] t = s == 0 ?{4i}:{6i};\n");
        xml_.append("  return t[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = covValEn(files_);
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, cont_);
        assertEq(6, sizeCovers(cont_));
        assertEq(0, sizeCovers(cont_,0));
        assertEq(0, sizeCovers(cont_,1));
        assertEq(3, sizeCovers(cont_,2));
        assertEq(0, sizeCovers(cont_,3));
        assertEq(10, sizeCovers(cont_,4));
        assertTrue(covers(cont_,4,2).isFullCovered());
        assertTrue(covers(cont_,4,2).isPartialCovered());
        AbstractCoverageResult value_ = covers(cont_,4,3);
        assertTrue(value_ instanceof AbstractCoverageResult);
        assertTrue(!value_.isFullCovered());
        assertTrue(value_.isPartialCovered());
        assertTrue(!((BooleanCoverageResult)value_).isCoverBcTrue());
        assertTrue(((BooleanCoverageResult)value_).isCoverBcFalse());
    }
    @Test
    public void coverage4Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  for (int j:{0,1}){\n");
        xml_.append("   int[] t = s == j ?{4i}:{6i};\n");
        xml_.append("   sum += t[0];\n");
        xml_.append("  }\n");
        xml_.append("  return sum;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = covValEn(files_);
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, cont_);
        assertEq(10, sizeCovers(cont_));
        assertEq(0, sizeCovers(cont_,0));
        assertEq(0, sizeCovers(cont_,1));
        assertEq(3, sizeCovers(cont_,2));
        assertEq(0, sizeCovers(cont_,3));
        assertEq(10, sizeCovers(cont_,7));
        assertTrue(covers(cont_,7,2).isFullCovered());
        assertTrue(covers(cont_,7,2).isPartialCovered());
        AbstractCoverageResult value_ = covers(cont_,7,3);
        assertTrue(value_ instanceof AbstractCoverageResult);
        assertTrue(value_.isFullCovered());
        assertTrue(value_.isPartialCovered());
        assertTrue(((BooleanCoverageResult)value_).isCoverBcTrue());
        assertTrue(((BooleanCoverageResult)value_).isCoverBcFalse());
    }
    @Test
    public void coverage5Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  for (int j:{0,1}){\n");
        xml_.append("   int[] t = s == j ?{4i}:{6i};\n");
        xml_.append("   sum += t[0];\n");
        xml_.append("  }\n");
        xml_.append("  return sum;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = covValEn(files_);
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getCoverLoops(cont_).size());
        AbstractCoverageResult value_ = getCoverLoops(cont_).firstValue();
        assertTrue(value_.isFullCovered());
        assertTrue(value_.isPartialCovered());
        assertTrue(((BooleanCoverageResult)value_).isCoverBcTrue());
        assertTrue(((BooleanCoverageResult)value_).isCoverBcFalse());
    }
    @Test
    public void coverage6Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  for (int j:{0,1}){\n");
        xml_.append("   int[] t = s == j ?{4i}:{6i};\n");
        xml_.append("   sum += t[0];\n");
        xml_.append("   break;\n");
        xml_.append("  }\n");
        xml_.append("  return sum;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = covValEn(files_);
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getCoverLoops(cont_).size());
        AbstractCoverageResult value_ = getCoverLoops(cont_).firstValue();
        assertTrue(!value_.isFullCovered());
        assertTrue(value_.isPartialCovered());
        assertTrue(((BooleanCoverageResult)value_).isCoverBcTrue());
        assertTrue(!((BooleanCoverageResult)value_).isCoverBcFalse());
    }
    @Test
    public void coverage7Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  for (int j:{}){\n");
        xml_.append("   int[] t = s == j ?{4i}:{6i};\n");
        xml_.append("   sum += t[0];\n");
        xml_.append("  }\n");
        xml_.append("  return sum;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = covValEn(files_);
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getCoverLoops(cont_).size());
        AbstractCoverageResult value_ = getCoverLoops(cont_).firstValue();
        assertTrue(!value_.isFullCovered());
        assertTrue(value_.isPartialCovered());
        assertTrue(!((BooleanCoverageResult)value_).isCoverBcTrue());
        assertTrue(((BooleanCoverageResult)value_).isCoverBcFalse());
    }
    @Test
    public void coverage8Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  while (true){\n");
        xml_.append("   int[] t = s == 0 ?{4i}:{6i};\n");
        xml_.append("   sum += t[0];\n");
        xml_.append("   return sum;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = covValEn(files_);
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, cont_);
        assertEq(10, sizeCovers(cont_));
        assertEq(0, sizeCovers(cont_,0));
        assertEq(0, sizeCovers(cont_,1));
        assertEq(3, sizeCovers(cont_,2));
        assertEq(0, sizeCovers(cont_,3));
        assertEq(10, sizeCovers(cont_,7));
        AbstractCoverageResult value_ = covers(cont_,5,0);
        assertTrue(value_ instanceof AbstractCoverageResult);
        assertTrue(value_.isFullCovered());
        assertTrue(value_.isPartialCovered());
    }
    @Test
    public void coverage9Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  for (int j:{}){\n");
        xml_.append("   int[] t = s == j ?{4i}:{6i};\n");
        xml_.append("   sum += t[0];\n");
        xml_.append("  }\n");
        xml_.append("  return sum;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = covValEn(files_);
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, cont_);
        AbstractCoverageResult value_ = covers(cont_,7,0);
        assertTrue(!value_.isFullCovered());
        assertTrue(!value_.isPartialCovered());
    }

    @Test
    public void coverage15Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=10;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case 10;\n");
        xml_.append("   $case 8;\n");
        xml_.append("    t=12;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = covVal2(files_);
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getCoverSwitchs(cont_).size());
        IdMap<ExecBlock, CustList<AbstractCoverageResult>> map_ = getCoverSwitchs(cont_).firstValue().getChildren();
        assertEq(2, map_.size());
        AbstractCoverageResult value_ = map_.firstValue().first();
        assertTrue(value_.isFullCovered());
        value_ = map_.lastValue().first();
        assertTrue(!value_.isFullCovered());
        value_ = getCoverSwitchs(cont_).firstValue().getResultNoDef();
        assertTrue(!value_.isFullCovered());
    }

    @Test
    public void coverage16Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case 10;\n");
        xml_.append("   $case 8;\n");
        xml_.append("    t=12;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = covVal2(files_);
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getCoverSwitchs(cont_).size());
        IdMap<ExecBlock, CustList<AbstractCoverageResult>> map_ = getCoverSwitchs(cont_).firstValue().getChildren();
        assertEq(2, map_.size());
        AbstractCoverageResult value_ = map_.firstValue().first();
        assertTrue(!value_.isFullCovered());
        value_ = map_.lastValue().first();
        assertTrue(value_.isFullCovered());
        value_ = getCoverSwitchs(cont_).firstValue().noDefault();
        assertTrue(!value_.isFullCovered());
    }

    @Test
    public void coverage17Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=9;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case 10;\n");
        xml_.append("   $case 8;\n");
        xml_.append("    t=16;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = covVal2(files_);
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getCoverSwitchs(cont_).size());
        IdMap<ExecBlock, CustList<AbstractCoverageResult>> map_ = getCoverSwitchs(cont_).firstValue().getChildren();
        assertEq(2, map_.size());
        AbstractCoverageResult value_ = map_.firstValue().first();
        assertTrue(!value_.isFullCovered());
        value_ = map_.lastValue().first();
        assertTrue(!value_.isFullCovered());
        value_ = getCoverSwitchs(cont_).firstValue().noDefault();
        assertTrue(value_.isFullCovered());
    }

    @Test
    public void coverage18Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=9;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case 10;\n");
        xml_.append("   $case 8;\n");
        xml_.append("    t=16;\n");
        xml_.append("   $default;\n");
        xml_.append("    t=12;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = covVal2(files_);
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getCoverSwitchs(cont_).size());
        IdMap<ExecBlock, CustList<AbstractCoverageResult>> map_ = getCoverSwitchs(cont_).firstValue().getChildren();
        assertEq(3, map_.size());
        AbstractCoverageResult value_ = map_.firstValue().first();
        assertTrue(!value_.isFullCovered());
        value_ = map_.getValue(1).first();
        assertTrue(!value_.isFullCovered());
        value_ = map_.lastValue().first();
        assertTrue(value_.isFullCovered());
    }

    @Test
    public void coverage19Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $switch(t){\n");
        xml_.append("   $case 10;\n");
        xml_.append("   $case 8;\n");
        xml_.append("    t=16;\n");
        xml_.append("   $default;\n");
        xml_.append("    t=12;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = covVal2(files_);
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getCoverSwitchs(cont_).size());
        IdMap<ExecBlock, CustList<AbstractCoverageResult>> map_ = getCoverSwitchs(cont_).firstValue().getChildren();
        assertEq(3, map_.size());
        AbstractCoverageResult value_ = map_.firstValue().first();
        assertTrue(!value_.isFullCovered());
        value_ = map_.getValue(1).first();
        assertTrue(value_.isFullCovered());
        value_ = map_.lastValue().first();
        assertTrue(!value_.isFullCovered());
    }

    @Test
    public void coverage40Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static pkg.CustList<java.lang.Number> inst=$new pkg.CustList<java.lang.Number>();\n");
        xml_.append(" $public $static $int res;\n");
        xml_.append(" $static {\n");
        xml_.append("  inst.add(3i);\n");
        xml_.append("  inst.add(1i);\n");
        xml_.append("  inst.add(2i);\n");
        xml_.append("  $foreach(java.lang.Number e:inst){\n");
        xml_.append("   res+=e.intValue();\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        ContextEl cont_ = covVal(files_);
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getCoverLoops2(cont_).size());
        AbstractCoverageResult value_ = getCoverLoops2(cont_).firstValue();
        assertTrue(value_.isFullCovered());
        assertTrue(((BooleanCoverageResult)value_).isCoverBcTrue());
        assertTrue(((BooleanCoverageResult)value_).isCoverBcFalse());
    }
    @Test
    public void coverage41Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static pkg.CustList<java.lang.Number> inst=$new pkg.CustList<java.lang.Number>();\n");
        xml_.append(" $public $static $int res;\n");
        xml_.append(" $static {\n");
        xml_.append("  inst.add(3i);\n");
        xml_.append("  inst.add(1i);\n");
        xml_.append("  inst.add(2i);\n");
        xml_.append("  $foreach(java.lang.Number e:inst){\n");
        xml_.append("   res+=e.intValue();\n");
        xml_.append("   $break;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        ContextEl cont_ = covVal(files_);
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getCoverLoops2(cont_).size());
        AbstractCoverageResult value_ = getCoverLoops2(cont_).firstValue();
        assertTrue(!value_.isFullCovered());
        assertTrue(value_.isPartialCovered());
        assertTrue(((BooleanCoverageResult)value_).isCoverBcTrue());
        assertTrue(!((BooleanCoverageResult)value_).isCoverBcFalse());
    }
    @Test
    public void coverage42Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static pkg.CustList<java.lang.Number> inst=$new pkg.CustList<java.lang.Number>();\n");
        xml_.append(" $public $static $int res;\n");
        xml_.append(" $static {\n");
        xml_.append("  $foreach(java.lang.Number e:inst){\n");
        xml_.append("   res+=e.intValue();\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        ContextEl cont_ = covVal(files_);
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getCoverLoops2(cont_).size());
        AbstractCoverageResult value_ = getCoverLoops2(cont_).firstValue();
        assertTrue(!value_.isFullCovered());
        assertTrue(value_.isPartialCovered());
        assertTrue(!((BooleanCoverageResult)value_).isCoverBcTrue());
        assertTrue(((BooleanCoverageResult)value_).isCoverBcFalse());
    }
    @Test
    public void coverage43Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static pkg.CustList<java.lang.Number> inst=$new pkg.CustList<java.lang.Number>();\n");
        xml_.append(" $public $static $int res;\n");
        xml_.append(" $static {\n");
        xml_.append("  $if (res == 0) {\n");
        xml_.append("   $return;\n");
        xml_.append("  }\n");
        xml_.append("  $foreach(java.lang.Number e:inst){\n");
        xml_.append("   res+=e.intValue();\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("src/pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        ContextEl cont_ = covVal(files_);
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getCoverLoops2(cont_).size());
        AbstractCoverageResult value_ = getCoverLoops2(cont_).firstValue();
        assertTrue(!value_.isFullCovered());
        assertTrue(!value_.isPartialCovered());
        assertTrue(!((BooleanCoverageResult)value_).isCoverBcTrue());
        assertTrue(!((BooleanCoverageResult)value_).isCoverBcFalse());
    }
    @Test
    public void coverage44Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static CustTable<Number,Number> inst=$new CustTable<Number,Number>();\n");
        xml_.append(" $public $static $int res;\n");
        xml_.append(" $static {\n");
        xml_.append("  inst.add(3,5);\n");
        xml_.append("  inst.add(8,1);\n");
        xml_.append("  inst.add(2,6);\n");
        xml_.append("  $for(Number f, Number s: inst){\n");
        xml_.append("   res += f.intValue()+s.intValue();\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        ContextEl cont_ = covVal(files_);
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getCoverLoops5(cont_).size());
        AbstractCoverageResult value_ = getCoverLoops5(cont_).firstValue();
        assertTrue(value_.isFullCovered());
        assertTrue(((BooleanCoverageResult)value_).isCoverBcTrue());
        assertTrue(((BooleanCoverageResult)value_).isCoverBcFalse());
    }

    @Test
    public void coverage45Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static CustTable<Number,Number> inst=$new CustTable<Number,Number>();\n");
        xml_.append(" $public $static $int res;\n");
        xml_.append(" $static {\n");
        xml_.append("  inst.add(3,5);\n");
        xml_.append("  inst.add(8,1);\n");
        xml_.append("  inst.add(2,6);\n");
        xml_.append("  $for(Number f, Number s: inst){\n");
        xml_.append("   res += f.intValue()+s.intValue();\n");
        xml_.append("   $break;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        ContextEl cont_ = covVal(files_);
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getCoverLoops5(cont_).size());
        AbstractCoverageResult value_ = getCoverLoops5(cont_).firstValue();
        assertTrue(!value_.isFullCovered());
        assertTrue(value_.isPartialCovered());
        assertTrue(((BooleanCoverageResult)value_).isCoverBcTrue());
        assertTrue(!((BooleanCoverageResult)value_).isCoverBcFalse());
    }

    @Test
    public void coverage46Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static CustTable<Number,Number> inst=$new CustTable<Number,Number>();\n");
        xml_.append(" $public $static $int res;\n");
        xml_.append(" $static {\n");
        xml_.append("  $for(Number f, Number s: inst){\n");
        xml_.append("   res += f.intValue()+s.intValue();\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        ContextEl cont_ = covVal(files_);
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getCoverLoops5(cont_).size());
        AbstractCoverageResult value_ = getCoverLoops5(cont_).firstValue();
        assertTrue(!value_.isFullCovered());
        assertTrue(value_.isPartialCovered());
        assertTrue(!((BooleanCoverageResult)value_).isCoverBcTrue());
        assertTrue(((BooleanCoverageResult)value_).isCoverBcFalse());
    }

    @Test
    public void coverage47Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static CustTable<Number,Number> inst=$new CustTable<Number,Number>();\n");
        xml_.append(" $public $static $int res;\n");
        xml_.append(" $static {\n");
        xml_.append("  $if (res == 0) {\n");
        xml_.append("   $return;\n");
        xml_.append("  }\n");
        xml_.append("  $for(Number f, Number s: inst){\n");
        xml_.append("   res += f.intValue()+s.intValue();\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("src/pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        ContextEl cont_ = covVal(files_);
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getCoverLoops5(cont_).size());
        AbstractCoverageResult value_ = getCoverLoops5(cont_).firstValue();
        assertTrue(!value_.isFullCovered());
        assertTrue(!value_.isPartialCovered());
        assertTrue(!((BooleanCoverageResult)value_).isCoverBcTrue());
        assertTrue(!((BooleanCoverageResult)value_).isCoverBcFalse());
    }
    @Test
    public void coverage48Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  iter (int j=0;2;1){\n");
        xml_.append("   int[] t = s == j ?{4i}:{6i};\n");
        xml_.append("   sum += t[0];\n");
        xml_.append("  }\n");
        xml_.append("  return sum;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = covValEn(files_);
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getCoverLoops(cont_).size());
        assertTrue(getCoverLoops(cont_).firstKey() instanceof ExecForIterativeLoop);
        AbstractCoverageResult value_ = getCoverLoops(cont_).firstValue();
        assertTrue(value_.isFullCovered());
        assertTrue(value_.isPartialCovered());
        assertTrue(((BooleanCoverageResult)value_).isCoverBcTrue());
        assertTrue(((BooleanCoverageResult)value_).isCoverBcFalse());
    }
    @Test
    public void coverage49Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  iter (int j=0;2;1){\n");
        xml_.append("   int[] t = s == j ?{4i}:{6i};\n");
        xml_.append("   sum += t[0];\n");
        xml_.append("   break;\n");
        xml_.append("  }\n");
        xml_.append("  return sum;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = covValEn(files_);
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getCoverLoops(cont_).size());
        assertTrue(getCoverLoops(cont_).firstKey() instanceof ExecForIterativeLoop);
        AbstractCoverageResult value_ = getCoverLoops(cont_).firstValue();
        assertTrue(!value_.isFullCovered());
        assertTrue(value_.isPartialCovered());
        assertTrue(((BooleanCoverageResult)value_).isCoverBcTrue());
        assertTrue(!((BooleanCoverageResult)value_).isCoverBcFalse());
    }
    @Test
    public void coverage50Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  iter (int j=0;0;1){\n");
        xml_.append("   int[] t = s == j ?{4i}:{6i};\n");
        xml_.append("   sum += t[0];\n");
        xml_.append("  }\n");
        xml_.append("  return sum;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = covValEn(files_);
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getCoverLoops(cont_).size());
        assertTrue(getCoverLoops(cont_).firstKey() instanceof ExecForIterativeLoop);
        AbstractCoverageResult value_ = getCoverLoops(cont_).firstValue();
        assertTrue(!value_.isFullCovered());
        assertTrue(value_.isPartialCovered());
        assertTrue(!((BooleanCoverageResult)value_).isCoverBcTrue());
        assertTrue(((BooleanCoverageResult)value_).isCoverBcFalse());
    }
    @Test
    public void coverage51Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  int sum = 0;\n");
        xml_.append("  if (sum == 0){\n");
        xml_.append("   return s;\n");
        xml_.append("  }\n");
        xml_.append("  iter (int j=0;0;1){\n");
        xml_.append("   int[] t = s == j ?{4i}:{6i};\n");
        xml_.append("   sum += t[0];\n");
        xml_.append("  }\n");
        xml_.append("  return sum;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = covValEn(files_);
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, cont_);
        assertEq(1, getCoverLoops(cont_).size());
        assertTrue(getCoverLoops(cont_).firstKey() instanceof ExecForIterativeLoop);
        AbstractCoverageResult value_ = getCoverLoops(cont_).firstValue();
        assertTrue(!value_.isFullCovered());
        assertTrue(!value_.isPartialCovered());
        assertTrue(!((BooleanCoverageResult)value_).isCoverBcTrue());
        assertTrue(!((BooleanCoverageResult)value_).isCoverBcFalse());
    }

    private static IdMap<ExecBlock, SwitchCoverageResult> getCoverSwitchs(ContextEl _cont) {
        CustList<TypeCoverageResult> types_ = _cont.getCoverage().getTypes();
        return types_.last().getFunctions().first().getCoverSwitchs();
    }

    private static IdMap<ExecBlock, AbstractCoverageResult> getCoverLoops(ContextEl _cont) {

        CustList<TypeCoverageResult> types_ = _cont.getCoverage().getTypes();
        return types_.last().getFunctions().first().getCoverLoops();
    }

    private static IdMap<ExecBlock, AbstractCoverageResult> getCoverLoops2(ContextEl _cont) {

        CustList<TypeCoverageResult> types_ = _cont.getCoverage().getTypes();
        return types_.get(types_.size()-3).getFunctionsStat().first().getCoverLoops();
    }

    private static IdMap<ExecBlock, AbstractCoverageResult> getCoverLoops5(ContextEl _cont) {

        CustList<TypeCoverageResult> types_ = _cont.getCoverage().getTypes();
        return types_.get(types_.size()-6).getFunctionsStat().first().getCoverLoops();
    }

    private static int sizeCovers(ContextEl _cont) {
        CustList<BlockCoverageResult> blocks_ = blocks(_cont);
        return blocks_.size();
    }
    private static int sizeCovers(ContextEl _cont, int _index) {
        CustList<BlockCoverageResult> blocks_ = blocks(_cont);
        return blocks_.get(_index).getCovers().size();
    }
    private static AbstractCoverageResult covers(ContextEl _cont, int _index, int _i) {
        CustList<BlockCoverageResult> blocks_ = blocks(_cont);
        return blocks_.get(_index).getCovers().get(_i);
    }

    private static CustList<BlockCoverageResult> blocks(ContextEl _cont) {
        CustList<TypeCoverageResult> types_ = _cont.getCoverage().getTypes();
        return types_.last().getFunctions().first().getBlocks();
    }

    private static String getCustomPair() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustPair<U,V> :$pair<U,V>{\n");
        xml_.append(" $private U first;\n");
        xml_.append(" $private V second;\n");
        xml_.append(" $public CustPair(){\n");
        xml_.append(" }\n");
        xml_.append(" $public CustPair(U f,V s){\n");
        xml_.append("  first = f;\n");
        xml_.append("  second = s;\n");
        xml_.append(" }\n");
        xml_.append(" $public U getFirst(){\n");
        xml_.append("  $return first;\n");
        xml_.append(" }\n");
        xml_.append(" $public V getSecond(){\n");
        xml_.append("  $return second;\n");
        xml_.append(" }\n");
        xml_.append(" $public $void setFirst(U f){\n");
        xml_.append("  first = f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
    private static String getCustomTable() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustTable<U,V> :$iterableTable<U,V>{\n");
        xml_.append(" $private CustList<CustPair<U,V>> list;\n");
        xml_.append(" $public (){\n");
        xml_.append("  list=$new CustList<CustPair<U,V>>();\n");
        xml_.append(" }\n");
        xml_.append(" $public $void add(U f,V s){\n");
        xml_.append("  list.add($new CustPair<U,V>(f,s));\n");
        xml_.append(" }\n");
        xml_.append(" $public $void add(CustPair<U,V> p){\n");
        xml_.append("  list.add(p);\n");
        xml_.append(" }\n");
        xml_.append(" $public $int size(){\n");
        xml_.append("  $return list.size();\n");
        xml_.append(" }\n");
        xml_.append(" $public CustPair<U,V> get($int index){\n");
        xml_.append("  $return list.get(index);\n");
        xml_.append(" }\n");
        xml_.append(" $public $iteratorTable<U,V> iteratorTable(){\n");
        xml_.append("  $return $new CustIterTable<U,V>($this);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
    private static String getCustomIteratorTable() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustIterTable<U,V> :$iteratorTable<U,V>{\n");
        xml_.append(" $private CustTable<U,V> list;\n");
        xml_.append(" $private $int length;\n");
        xml_.append(" $private $int index;\n");
        xml_.append(" $public CustIterTable(CustTable<U,V> i){\n");
        xml_.append("  list=i;\n");
        xml_.append("  length=list.size();\n");
        xml_.append(" }\n");
        xml_.append(" $public CustPair<U,V> nextPair(){\n");
        xml_.append("  CustPair<U,V> out=list.get(index);\n");
        xml_.append("  index++;\n");
        xml_.append("  $return out;\n");
        xml_.append(" }\n");
        xml_.append(" $public $boolean hasNextPair(){\n");
        xml_.append("  $return index<length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
    private static String getCustomList() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustList<U> :$iterable<U>{\n");
        xml_.append(" $private U[] list;\n");
        xml_.append(" $private $int length;\n");
        xml_.append(" $public (){\n");
        xml_.append("  list=$new U[0i];\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void add(U elt){\n");
        xml_.append("  add(length,elt);\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void add($int index,U elt){\n");
        xml_.append("  U[] newlist=$new U[length+1i];\n");
        xml_.append("  $iter($int i=0i;index;1i){\n");
        xml_.append("   newlist[i]=list[i];\n");
        xml_.append("  }\n");
        xml_.append("  newlist[index]=elt;\n");
        xml_.append("  $iter($int i=index+1i;length+1i;1i){\n");
        xml_.append("   newlist[i]=list[i-1i];\n");
        xml_.append("  }\n");
        xml_.append("  length++;\n");
        xml_.append("  list=newlist;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int size(){\n");
        xml_.append("  $return length;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal U get($int index){\n");
        xml_.append("  $return list[index];\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void set($int index,U elt){\n");
        xml_.append("  list[index]=elt;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void remove($int index){\n");
        xml_.append("  $iter($int i=index;length-1i;1i){\n");
        xml_.append("   list[i]=list[i+1i];\n");
        xml_.append("  }\n");
        xml_.append("  list[length-1i]=$null;\n");
        xml_.append("  length--;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $iterator<U> iterator(){\n");
        xml_.append("  $return $new pkg.CustIter<U>($this);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
    private static String getCustomIterator() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustIter<T> :$iterator<T>{\n");
        xml_.append(" $private pkg.CustList<T> list;\n");
        xml_.append(" $private $int length;\n");
        xml_.append(" $private $int index;\n");
        xml_.append(" $public (pkg.CustList<T> i){\n");
        xml_.append("  list=i;\n");
        xml_.append("  length=list.size();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal T next(){\n");
        xml_.append("  T out=list.get(index);\n");
        xml_.append("  index++;\n");
        xml_.append("  $return out;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $boolean hasNext(){\n");
        xml_.append("  $return index<length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
}
