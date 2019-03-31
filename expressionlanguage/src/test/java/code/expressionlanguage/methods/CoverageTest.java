package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.util.AbstractCoverageResult;
import code.expressionlanguage.methods.util.BooleanCoverageResult;
import code.expressionlanguage.methods.util.StandardCoverageResult;
import code.expressionlanguage.opers.util.MethodId;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringMap;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;

public final class CoverageTest extends ProcessMethodCommon {
    @Test
    public void coverage1Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(5, cont_.getCoverage().getCovers().size());
        assertEq(0, cont_.getCoverage().getCovers().getValue(0).size());
        assertEq(0, cont_.getCoverage().getCovers().getValue(1).size());
        assertEq(1, cont_.getCoverage().getCovers().getValue(2).size());
        assertEq(3, cont_.getCoverage().getCovers().getValue(3).size());
        assertTrue(cont_.getCoverage().getCovers().getValue(3).firstValue().isFullCovered());
        assertEq(4, cont_.getCoverage().getCovers().getValue(4).size());
    }
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
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(6, cont_.getCoverage().getCovers().size());
        assertEq(0, cont_.getCoverage().getCovers().getValue(0).size());
        assertEq(0, cont_.getCoverage().getCovers().getValue(1).size());
        assertEq(3, cont_.getCoverage().getCovers().getValue(2).size());
        assertEq(0, cont_.getCoverage().getCovers().getValue(3).size());
        assertEq(10, cont_.getCoverage().getCovers().getValue(4).size());
        assertTrue(cont_.getCoverage().getCovers().getValue(4).getValue(2).isFullCovered());
        assertTrue(cont_.getCoverage().getCovers().getValue(4).getValue(2).isPartialCovered());
        AbstractCoverageResult value_ = cont_.getCoverage().getCovers().getValue(4).getValue(3);
        assertTrue(value_ instanceof BooleanCoverageResult);
        assertTrue(!value_.isFullCovered());
        assertTrue(value_.isPartialCovered());
        assertTrue(((BooleanCoverageResult)value_).isCoverTrue());
        assertTrue(!((BooleanCoverageResult)value_).isCoverFalse());
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
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(6, cont_.getCoverage().getCovers().size());
        assertEq(0, cont_.getCoverage().getCovers().getValue(0).size());
        assertEq(0, cont_.getCoverage().getCovers().getValue(1).size());
        assertEq(3, cont_.getCoverage().getCovers().getValue(2).size());
        assertEq(0, cont_.getCoverage().getCovers().getValue(3).size());
        assertEq(10, cont_.getCoverage().getCovers().getValue(4).size());
        assertTrue(cont_.getCoverage().getCovers().getValue(4).getValue(2).isFullCovered());
        assertTrue(cont_.getCoverage().getCovers().getValue(4).getValue(2).isPartialCovered());
        AbstractCoverageResult value_ = cont_.getCoverage().getCovers().getValue(4).getValue(3);
        assertTrue(value_ instanceof BooleanCoverageResult);
        assertTrue(!value_.isFullCovered());
        assertTrue(value_.isPartialCovered());
        assertTrue(!((BooleanCoverageResult)value_).isCoverTrue());
        assertTrue(((BooleanCoverageResult)value_).isCoverFalse());
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
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(10, cont_.getCoverage().getCovers().size());
        assertEq(0, cont_.getCoverage().getCovers().getValue(0).size());
        assertEq(0, cont_.getCoverage().getCovers().getValue(1).size());
        assertEq(3, cont_.getCoverage().getCovers().getValue(2).size());
        assertEq(0, cont_.getCoverage().getCovers().getValue(3).size());
        assertEq(10, cont_.getCoverage().getCovers().getValue(7).size());
        assertTrue(cont_.getCoverage().getCovers().getValue(7).getValue(2).isFullCovered());
        assertTrue(cont_.getCoverage().getCovers().getValue(7).getValue(2).isPartialCovered());
        AbstractCoverageResult value_ = cont_.getCoverage().getCovers().getValue(7).getValue(3);
        assertTrue(value_ instanceof BooleanCoverageResult);
        assertTrue(value_.isFullCovered());
        assertTrue(value_.isPartialCovered());
        assertTrue(((BooleanCoverageResult)value_).isCoverTrue());
        assertTrue(((BooleanCoverageResult)value_).isCoverFalse());
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
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(1, cont_.getCoverage().getCoverLoops().size());
        BooleanCoverageResult value_ = cont_.getCoverage().getCoverLoops().firstValue();
        assertTrue(value_.isFullCovered());
        assertTrue(value_.isPartialCovered());
        assertTrue(value_.isCoverTrue());
        assertTrue(value_.isCoverFalse());
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
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(1, cont_.getCoverage().getCoverLoops().size());
        BooleanCoverageResult value_ = cont_.getCoverage().getCoverLoops().firstValue();
        assertTrue(!value_.isFullCovered());
        assertTrue(value_.isPartialCovered());
        assertTrue(value_.isCoverTrue());
        assertTrue(!value_.isCoverFalse());
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
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(1, cont_.getCoverage().getCoverLoops().size());
        BooleanCoverageResult value_ = cont_.getCoverage().getCoverLoops().firstValue();
        assertTrue(!value_.isFullCovered());
        assertTrue(value_.isPartialCovered());
        assertTrue(!value_.isCoverTrue());
        assertTrue(value_.isCoverFalse());
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
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(10, cont_.getCoverage().getCovers().size());
        assertEq(0, cont_.getCoverage().getCovers().getValue(0).size());
        assertEq(0, cont_.getCoverage().getCovers().getValue(1).size());
        assertEq(3, cont_.getCoverage().getCovers().getValue(2).size());
        assertEq(0, cont_.getCoverage().getCovers().getValue(3).size());
        assertEq(10, cont_.getCoverage().getCovers().getValue(7).size());
        AbstractCoverageResult value_ = cont_.getCoverage().getCovers().getValue(5).firstValue();
        assertTrue(value_ instanceof StandardCoverageResult);
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
        ContextEl cont_ = contextElCoverageEnDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        AbstractCoverageResult value_ = cont_.getCoverage().getCovers().getValue(7).getValue(0);
        assertTrue(!value_.isFullCovered());
        assertTrue(!value_.isPartialCovered());
    }

    @Test
    public void coverage10Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append("}\n");
        xml_.append("@MyAnnot\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $Annotation[] arr = $class(Ex).getAnnotations():\n");
        xml_.append("  $if (arr;.length != 1i){\n");
        xml_.append("   $return 3i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.) != $class($Annotation[])){\n");
        xml_.append("   $return 2i:\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr;.[0i]) != $class(MyAnnot)){\n");
        xml_.append("   $return 1i:\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(10, cont_.getCoverage().getCovers().size());
    }
    @Test
    public void coverage11Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int infoInt()1i:\n");
        xml_.append("}\n");
        xml_.append("@MyAnnot(2i)\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  java.lang.Object arr = $class(MyAnnot).getDeclaredMethods()[0i].getDefaultValue():\n");
        xml_.append("  $if ($static($Class).getClass(arr;.) != $class(java.lang.Integer)){\n");
        xml_.append("   $return 2i:\n");
        xml_.append("  }\n");
        xml_.append("  java.lang.Integer a = $(java.lang.Integer)arr;.:\n");
        xml_.append("  $if (a;. != 1i){\n");
        xml_.append("   $return 1i:\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(10, cont_.getCoverage().getCovers().size());
    }
    @Test
    public void coverage12Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int st = 0:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(6, cont_.getCoverage().getCovers().size());
        assertEq(3, cont_.getCoverage().getCovers().firstValue().size());
        assertTrue(cont_.getCoverage().getCovers().firstValue().firstValue().isFullCovered());
    }
    @Test
    public void coverage13Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $if(t;.<0){\n");
        xml_.append("   $return 1i/0i:\n");
        xml_.append("  }\n");
        xml_.append("  $else{\n");
        xml_.append("   t;.=1i:\n");
        xml_.append("  }\n");
        xml_.append("  $return t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(9, cont_.getCoverage().getCovers().size());
        assertEq(3, cont_.getCoverage().getCovers().getValue(4).size());
        AbstractCoverageResult value_ = cont_.getCoverage().getCovers().getValue(4).firstValue();
        assertTrue(value_ instanceof BooleanCoverageResult);
        assertTrue(!value_.isFullCovered());
        assertTrue(!((BooleanCoverageResult)value_).isCoverTrue());
        assertTrue(((BooleanCoverageResult)value_).isCoverFalse());
    }
    @Test
    public void coverage14Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $if(t;.>=0){\n");
        xml_.append("   t;.=1i:\n");
        xml_.append("   $return t;.:\n");
        xml_.append("  }\n");
        xml_.append("  $else{\n");
        xml_.append("   $return 1i/0i:\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(9, cont_.getCoverage().getCovers().size());
        assertEq(3, cont_.getCoverage().getCovers().getValue(4).size());
        AbstractCoverageResult value_ = cont_.getCoverage().getCovers().getValue(4).firstValue();
        assertTrue(value_ instanceof BooleanCoverageResult);
        assertTrue(!value_.isFullCovered());
        assertTrue(((BooleanCoverageResult)value_).isCoverTrue());
        assertTrue(!((BooleanCoverageResult)value_).isCoverFalse());
    }

    @Test
    public void coverage15Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=10:\n");
        xml_.append("  $switch(t;.){\n");
        xml_.append("   $case(10):\n");
        xml_.append("   $case(8){\n");
        xml_.append("    t;.=12:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(1, cont_.getCoverage().getCoverSwitchs().size());
        assertEq(1, cont_.getCoverage().getCoverNoDefSwitchs().size());
        IdMap<Block, StandardCoverageResult> map_ = cont_.getCoverage().getCoverSwitchs().firstValue();
        assertEq(2, map_.size());
        StandardCoverageResult value_ = map_.firstValue();
        assertTrue(value_.isFullCovered());
        value_ = map_.lastValue();
        assertTrue(!value_.isFullCovered());
        value_ = cont_.getCoverage().getCoverNoDefSwitchs().firstValue();
        assertTrue(!value_.isFullCovered());
    }

    @Test
    public void coverage16Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $switch(t;.){\n");
        xml_.append("   $case(10):\n");
        xml_.append("   $case(8){\n");
        xml_.append("    t;.=12:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(1, cont_.getCoverage().getCoverSwitchs().size());
        assertEq(1, cont_.getCoverage().getCoverNoDefSwitchs().size());
        IdMap<Block, StandardCoverageResult> map_ = cont_.getCoverage().getCoverSwitchs().firstValue();
        assertEq(2, map_.size());
        StandardCoverageResult value_ = map_.firstValue();
        assertTrue(!value_.isFullCovered());
        value_ = map_.lastValue();
        assertTrue(value_.isFullCovered());
        value_ = cont_.getCoverage().getCoverNoDefSwitchs().firstValue();
        assertTrue(!value_.isFullCovered());
    }

    @Test
    public void coverage17Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=9:\n");
        xml_.append("  $switch(t;.){\n");
        xml_.append("   $case(10):\n");
        xml_.append("   $case(8){\n");
        xml_.append("    t;.=16:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(1, cont_.getCoverage().getCoverSwitchs().size());
        assertEq(1, cont_.getCoverage().getCoverNoDefSwitchs().size());
        IdMap<Block, StandardCoverageResult> map_ = cont_.getCoverage().getCoverSwitchs().firstValue();
        assertEq(2, map_.size());
        StandardCoverageResult value_ = map_.firstValue();
        assertTrue(!value_.isFullCovered());
        value_ = map_.lastValue();
        assertTrue(!value_.isFullCovered());
        value_ = cont_.getCoverage().getCoverNoDefSwitchs().firstValue();
        assertTrue(value_.isFullCovered());
    }

    @Test
    public void coverage18Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=9:\n");
        xml_.append("  $switch(t;.){\n");
        xml_.append("   $case(10):\n");
        xml_.append("   $case(8){\n");
        xml_.append("    t;.=16:\n");
        xml_.append("   }\n");
        xml_.append("   $default{\n");
        xml_.append("    t;.=12:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(1, cont_.getCoverage().getCoverSwitchs().size());
        assertEq(0, cont_.getCoverage().getCoverNoDefSwitchs().size());
        IdMap<Block, StandardCoverageResult> map_ = cont_.getCoverage().getCoverSwitchs().firstValue();
        assertEq(3, map_.size());
        StandardCoverageResult value_ = map_.firstValue();
        assertTrue(!value_.isFullCovered());
        value_ = map_.getValue(1);
        assertTrue(!value_.isFullCovered());
        value_ = map_.lastValue();
        assertTrue(value_.isFullCovered());
    }

    @Test
    public void coverage19Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t:\n");
        xml_.append("  t;.=8:\n");
        xml_.append("  $switch(t;.){\n");
        xml_.append("   $case(10):\n");
        xml_.append("   $case(8){\n");
        xml_.append("    t;.=16:\n");
        xml_.append("   }\n");
        xml_.append("   $default{\n");
        xml_.append("    t;.=12:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(1, cont_.getCoverage().getCoverSwitchs().size());
        assertEq(0, cont_.getCoverage().getCoverNoDefSwitchs().size());
        IdMap<Block, StandardCoverageResult> map_ = cont_.getCoverage().getCoverSwitchs().firstValue();
        assertEq(3, map_.size());
        StandardCoverageResult value_ = map_.firstValue();
        assertTrue(!value_.isFullCovered());
        value_ = map_.getValue(1);
        assertTrue(value_.isFullCovered());
        value_ = map_.lastValue();
        assertTrue(!value_.isFullCovered());
    }
    @Test
    public void coverage20Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return call():\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int call(){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(4, cont_.getCoverage().getCovers().size());
        assertEq(1, cont_.getCoverage().getCovers().getValue(1).size());
        assertTrue(cont_.getCoverage().getCovers().getValue(1).firstValue().isFullCovered());
    }
    @Test
    public void coverage21Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int call(){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(2, cont_.getCoverage().getCalls().getVal("pkg.Ex").size());
        assertTrue(cont_.getCoverage().getCalls().getVal("pkg.Ex").firstValue());
        assertTrue(!cont_.getCoverage().getCalls().getVal("pkg.Ex").lastValue());
    }
    @Test
    public void coverage22Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Ex (pkg.Ex p, pkg.Ex q){\n");
        xml_.append(" pkg.Ex out = $new pkg.Ex():\n");
        xml_.append(" out;.field = p;.;field+q;.;field:\n");
        xml_.append(" $return out;.:\n");
        xml_.append("}\n");
        xml_.append("$operator- pkg.Ex (pkg.Ex p, pkg.Ex q){\n");
        xml_.append(" pkg.Ex out = $new pkg.Ex():\n");
        xml_.append(" out;.field = p;.;field-q;.;field:\n");
        xml_.append(" $return out;.:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field:\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  pkg.Ex one = $new pkg.Ex():\n");
        xml_.append("  pkg.Ex two = $new pkg.Ex():\n");
        xml_.append("  one;.field = 1:\n");
        xml_.append("  two;.field = 2:\n");
        xml_.append("  $return (one;.+two;.).field:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int call(){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(3, out_.getNumber());
        assertEq(2, cont_.getCoverage().getCalls().getVal("").size());
        assertTrue(cont_.getCoverage().getCalls().getVal("").firstValue());
        assertTrue(!cont_.getCoverage().getCalls().getVal("").lastValue());
    }
    @Test
    public void coverage23Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $try{\n");
        xml_.append("   $if(t;.>=0){\n");
        xml_.append("    $return 1i/0i:\n");
        xml_.append("   }\n");
        xml_.append("   $return 2:\n");
        xml_.append("  } $catch(Object o){\n");
        xml_.append("   t;.=1i:\n");
        xml_.append("   $return t;.:\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(1, cont_.getCoverage().getCatches().size());
        assertTrue(cont_.getCoverage().getCatches().firstValue());
    }
    @Test
    public void coverage24Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=-1i:\n");
        xml_.append("  $try{\n");
        xml_.append("   $if(t;.>=0){\n");
        xml_.append("    $return 1i/0i:\n");
        xml_.append("   }\n");
        xml_.append("   $return 2:\n");
        xml_.append("  } $catch(Object o){\n");
        xml_.append("   t;.=1i:\n");
        xml_.append("   $return t;.:\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElCoverage();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(1, cont_.getCoverage().getCatches().size());
        assertTrue(!cont_.getCoverage().getCatches().firstValue());
    }
}
