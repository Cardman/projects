package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.coverage.*;
import code.expressionlanguage.functionid.MethodId;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringMap;
import code.util.core.BoolVal;
import org.junit.Test;

public final class CoverageBisTest extends ProcessMethodCommon {
    @Test
    public void coverage1Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = covVal2(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(5, getCovers(cont_).size());
        assertEq(0, getCovers(cont_).get(0).size());
        assertEq(0, getCovers(cont_).get(1).size());
        assertEq(1, getCovers(cont_).get(2).size());
        assertEq(3, getCovers(cont_).get(3).size());
        assertTrue(getCovers(cont_).get(3).first().isFullCovered());
        assertEq(4, getCovers(cont_).get(4).size());
    }

    @Test
    public void coverage10Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append("}\n");
        xml_.append("@MyAnnot\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $Annotation[] arr = $class(Ex).getAnnotations();\n");
        xml_.append("  $if (arr.length != 1i){\n");
        xml_.append("   $return 3i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr) != $class($Annotation[])){\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  $if ($static($Class).getClass(arr[0i]) != $class(MyAnnot)){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = covVal2(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getSecCovers(cont_).size());
    }
    @Test
    public void coverage11Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.MyAnnot {\n");
        xml_.append(" $int infoInt()1i;\n");
        xml_.append("}\n");
        xml_.append("@MyAnnot(2i)\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  java.lang.Object arr = $class(MyAnnot).getDeclaredMethods()[0i].getDefaultValue();\n");
        xml_.append("  $if ($static($Class).getClass(arr) != $class(java.lang.Integer)){\n");
        xml_.append("   $return 2i;\n");
        xml_.append("  }\n");
        xml_.append("  java.lang.Integer a = $(java.lang.Integer)arr;\n");
        xml_.append("  $if (a != 1i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = covVal2(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getSecCovers(cont_).size());
    }
    @Test
    public void coverage12Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int st = 0;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = covVal2(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getFieldCovers(cont_).size());
        assertEq(3, getFieldCovers(cont_).first().size());
        assertTrue(getFieldCovers(cont_).first().first().isFullCovered());
    }
    @Test
    public void coverage13Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $if(t<0){\n");
        xml_.append("   $return 1i/0i;\n");
        xml_.append("  }\n");
        xml_.append("  $else{\n");
        xml_.append("   t=1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = covVal2(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(9, getCovers(cont_).size());
        assertEq(3, getCovers(cont_).get(4).size());
        AbstractCoverageResult value_ = getCovers(cont_).get(4).first();
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
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $if(t>=0){\n");
        xml_.append("   t=1i;\n");
        xml_.append("   $return t;\n");
        xml_.append("  }\n");
        xml_.append("  $else{\n");
        xml_.append("   $return 1i/0i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = covVal2(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(9, getCovers(cont_).size());
        assertEq(3, getCovers(cont_).get(4).size());
        AbstractCoverageResult value_ = getCovers(cont_).get(4).first();
        assertTrue(value_ instanceof BooleanCoverageResult);
        assertTrue(!value_.isFullCovered());
        assertTrue(((BooleanCoverageResult)value_).isCoverTrue());
        assertTrue(!((BooleanCoverageResult)value_).isCoverFalse());
    }
    @Test
    public void coverage20Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return call();\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int call(){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = covVal2(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getCovers(cont_).size());
        assertEq(1, getCovers(cont_).get(1).size());
        assertTrue(getCovers(cont_).get(1).first().isFullCovered());
    }
    @Test
    public void coverage21Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int call(){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = covVal2(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getCalls(cont_).getVal("pkg.Ex").size());
        assertTrue(getCalls(cont_).getVal("pkg.Ex").first());
        assertTrue(!getCalls(cont_).getVal("pkg.Ex").last());
    }
    @Test
    public void coverage22Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Ex (pkg.Ex p, pkg.Ex q){\n");
        xml_.append(" pkg.Ex out = $new pkg.Ex();\n");
        xml_.append(" out.field = p.field+q.field;\n");
        xml_.append(" $return out;\n");
        xml_.append("}\n");
        xml_.append("$operator- pkg.Ex (pkg.Ex p, pkg.Ex q){\n");
        xml_.append(" pkg.Ex out = $new pkg.Ex();\n");
        xml_.append(" out.field = p.field-q.field;\n");
        xml_.append(" $return out;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int field;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  pkg.Ex one = $new pkg.Ex();\n");
        xml_.append("  pkg.Ex two = $new pkg.Ex();\n");
        xml_.append("  one.field = 1;\n");
        xml_.append("  two.field = 2;\n");
        xml_.append("  $return (one+two).field;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int call(){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = covVal2(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument out_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(3, getNumber(out_));
        assertEq(2, getCallsTwo(cont_).getVal("").size());
        assertTrue(getCallsTwo(cont_).getVal("").first());
        assertTrue(!getCallsTwo(cont_).getVal("").last());
    }
    @Test
    public void coverage23Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $try{\n");
        xml_.append("   $if(t>=0){\n");
        xml_.append("    $return 1i/0i;\n");
        xml_.append("   }\n");
        xml_.append("   $return 2;\n");
        xml_.append("  } $catch(Object o){\n");
        xml_.append("   t=1i;\n");
        xml_.append("   $return t;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = covVal2(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getCatches(cont_).size());
        assertSame(BoolVal.TRUE, getCatches(cont_).firstValue());
    }
    @Test
    public void coverage24Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=-1i;\n");
        xml_.append("  $try{\n");
        xml_.append("   $if(t>=0){\n");
        xml_.append("    $return 1i/0i;\n");
        xml_.append("   }\n");
        xml_.append("   $return 2;\n");
        xml_.append("  } $catch(Object o){\n");
        xml_.append("   t=1i;\n");
        xml_.append("   $return t;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = covVal2(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getCatches(cont_).size());
        assertSame(BoolVal.FALSE, getCatches(cont_).firstValue());
    }

    private static StringMap<CustList<Boolean>> getCalls(ContextEl _cont) {
        StringMap<CustList<Boolean>> ret_ = new StringMap<CustList<Boolean>>();
        CustList<Boolean> v_ = new CustList<Boolean>();
        for (FunctionCoverageResult f: _cont.getCoverage().getTypes().last().getFunctions()) {
            v_.add(f.isCalled());
        }
        ret_.addEntry("pkg.Ex", v_);
        return ret_;
    }


    private static StringMap<CustList<Boolean>> getCallsTwo(ContextEl _cont) {
        StringMap<CustList<Boolean>> ret_ = new StringMap<CustList<Boolean>>();
        CustList<Boolean> v_ = new CustList<Boolean>();
        for (FunctionCoverageResult e: _cont.getCoverage().getOperators()) {
            v_.add(e.isCalled());
        }
        ret_.addEntry("", v_);
        return ret_;
    }

    private static IdMap<ExecBlock, BoolVal> getCatches(ContextEl _cont) {
        CustList<TypeCoverageResult> types_ = _cont.getCoverage().getTypes();
        return types_.last().getFunctions().first().getCatches();
    }

    private static CustList<CustList<AbstractCoverageResult>> getCovers(ContextEl _cont) {
        CustList<TypeCoverageResult> types_ = _cont.getCoverage().getTypes();
        CustList<BlockCoverageResult> blocks_ = types_.last().getFunctions().first().getBlocks();
        CustList<CustList<AbstractCoverageResult>> m_ = new CustList<CustList<AbstractCoverageResult>>();
        for (BlockCoverageResult e: blocks_) {
            m_.add(e.getCovers());
        }
        return m_;
    }

    private static CustList<CustList<AbstractCoverageResult>> getFieldCovers(ContextEl _cont) {
        CustList<TypeCoverageResult> types_ = _cont.getCoverage().getTypes();
        CustList<BlockCoverageResult> blocks_ = types_.last().getFields();
        CustList<CustList<AbstractCoverageResult>> m_ = new CustList<CustList<AbstractCoverageResult>>();
        for (BlockCoverageResult e: blocks_) {
            m_.add(e.getCovers());
        }
        return m_;
    }
    private static CustList<CustList<AbstractCoverageResult>> getSecCovers(ContextEl _cont) {
        CustList<TypeCoverageResult> types_ = _cont.getCoverage().getTypes();
        CustList<BlockCoverageResult> blocks_ = types_.last().getFunctions().first().getBlocks();
        CustList<CustList<AbstractCoverageResult>> m_ = new CustList<CustList<AbstractCoverageResult>>();
        for (BlockCoverageResult e: blocks_) {
            m_.add(e.getCovers());
        }
        return m_;
    }

}
