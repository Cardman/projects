package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.coverage.*;
import code.expressionlanguage.functionid.MethodId;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringMap;
import code.util.comparators.ComparatorBoolean;
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
        assertEq(5, sizeCovers(cont_));
        assertEq(0, sizeCovers(cont_,0));
        assertEq(0, sizeCovers(cont_,1));
        assertEq(1, sizeCovers(cont_,2));
        assertEq(3, sizeCovers(cont_,3));
        assertTrue(covers(cont_,3,0).isFullCovered());
        assertEq(4, sizeCovers(cont_,4));
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
        assertEq(10, getSecCovers(cont_));
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
        assertEq(10, getSecCovers(cont_));
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
        assertEq(1, fieldCovers(cont_).size());
        assertEq(3, fieldCovers(cont_).first().getCovers().size());
        assertTrue(fieldCovers(cont_).first().getCovers().first().isFullCovered());
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
        assertEq(9, sizeCovers(cont_));
        assertEq(3, sizeCovers(cont_,4));
        AbstractCoverageResult value_ = covers(cont_,4,0);
        assertTrue(value_ instanceof BooleanCoverageResult);
        assertTrue(!value_.isFullCovered());
        assertTrue(!((BooleanCoverageResult)value_).isCoverBcTrue());
        assertTrue(((BooleanCoverageResult)value_).isCoverBcFalse());
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
        assertEq(9, sizeCovers(cont_));
        assertEq(3, sizeCovers(cont_,4));
        AbstractCoverageResult value_ = covers(cont_,4,0);
        assertTrue(value_ instanceof BooleanCoverageResult);
        assertTrue(!value_.isFullCovered());
        assertTrue(((BooleanCoverageResult)value_).isCoverBcTrue());
        assertTrue(!((BooleanCoverageResult)value_).isCoverBcFalse());
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
        assertEq(2, sizeCovers(cont_));
        assertEq(1, sizeCovers(cont_,1));
        assertTrue(covers(cont_,1,0).isFullCovered());
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
        assertEq(2, sizeFunction(cont_));
        assertSame(BoolVal.TRUE, callFunction(cont_,0));
        assertSame(BoolVal.FALSE, callFunction(cont_, 1));
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
        assertEq(2, sizeFunction(cont_));
        assertSame(BoolVal.TRUE, callFunction(cont_,0));
        assertSame(BoolVal.FALSE, callFunction(cont_,1));
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

    private static CustList<FunctionCoverageResult> getFunctions(ContextEl _cont) {
        return _cont.getCoverage().getTypes().last().getFunctions();
    }

    private static int sizeFunction(ContextEl _cont) {
        return getFunctions(_cont).size();
    }
    private static BoolVal callFunction(ContextEl _cont, int _index) {
        return ComparatorBoolean.of(getFunctions(_cont).get(_index).isCalled());
    }

    private static IdMap<ExecBlock, BoolVal> getCatches(ContextEl _cont) {
        CustList<TypeCoverageResult> types_ = _cont.getCoverage().getTypes();
        return types_.last().getFunctions().first().getCatches();
    }

    private static int sizeCovers(ContextEl _cont) {
        CustList<BlockCoverageResult> blocks_ = covers(_cont);
        return blocks_.size();
    }

    private static int sizeCovers(ContextEl _cont, int _index) {
        CustList<BlockCoverageResult> blocks_ = covers(_cont);
        return blocks_.get(_index).getCovers().size();
    }

    private static AbstractCoverageResult covers(ContextEl _cont, int _index, int _i) {
        CustList<BlockCoverageResult> blocks_ = covers(_cont);
        return blocks_.get(_index).getCovers().get(_i);
    }
    private static CustList<BlockCoverageResult> covers(ContextEl _cont) {
        CustList<TypeCoverageResult> types_ = _cont.getCoverage().getTypes();
        return types_.last().getFunctions().first().getBlocks();
    }

    private static CustList<BlockCoverageResult> fieldCovers(ContextEl _cont) {
        CustList<TypeCoverageResult> types_ = _cont.getCoverage().getTypes();
        return types_.last().getFields();
    }

    private static int getSecCovers(ContextEl _cont) {
        CustList<BlockCoverageResult> blocks_ = secCovers(_cont);
        return blocks_.size();
    }

    private static CustList<BlockCoverageResult> secCovers(ContextEl _cont) {
        CustList<TypeCoverageResult> types_ = _cont.getCoverage().getTypes();
        return types_.last().getFunctions().first().getBlocks();
    }

}
