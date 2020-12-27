package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NullStruct;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public final class ProcessMethodRefLambdaTest extends ProcessMethodCommon {
    @Test
    public void calculateArgument0Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t = 7;\n");
        xml_.append("  $lambda(Ex,exmeth,~$int).call($that(t));\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int t){\n");
        xml_.append("  t=8;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($int t){\n");
        xml_.append("  $return t+8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument1Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $int t = 7;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $that $int u = $that($lambda(Ex,exmeth2).call());\n");
        xml_.append("  u = 8;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth2(){\n");
        xml_.append("  $return $that(t);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $int t = 7;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $lambda(Ex,exmeth2).call() = 8;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth2(){\n");
        xml_.append("  $return $that(t);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t = 7;\n");
        xml_.append("  ($that $int u:$void)->{u=8;}.call($that(t));\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int t){\n");
        xml_.append("  t=8;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($int t){\n");
        xml_.append("  $return t+8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument4Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $int t = 7;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  (:$that $int)->{$return $that(t);}.call()=8;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int t){\n");
        xml_.append("  t=8;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($int t){\n");
        xml_.append("  $return t+8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument5Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $int t = 7;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ($int p:$that $int)->{$return $that(t);}.call(1)=8;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int t){\n");
        xml_.append("  t=8;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($int t){\n");
        xml_.append("  $return t+8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument6Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int stField = out();\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return stField;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int out(){\n");
        xml_.append("  $Fct<Rec> l = $lambda(Rec,$new);\n");
        xml_.append("  $return l.call().field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Rec {\n");
        xml_.append(" $int field=10;\n");
        xml_.append(" $static $int stField=Ex.stField++;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_,"pkg.Ex");
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument7Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int stField = out();\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return stField;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int out(){\n");
        xml_.append("  $Fct<$int> l = $lambda(Rec,meth);\n");
        xml_.append("  $return l.call();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Rec {\n");
        xml_.append(" $int field;\n");
        xml_.append(" $static $int stField=Ex.stField++;\n");
        xml_.append(" $static $int meth(){\n");
        xml_.append("  $return 10;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_,"pkg.Ex");
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument8Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String stField = out();\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return stField;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static String out(){\n");
        xml_.append("  $Fct<String,$boolean,$Class> l = $lambda($Class,forName,String,$boolean);\n");
        xml_.append("  $return l.call(\"pkg.Rec\",$true).getName();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Rec {\n");
        xml_.append(" $int field;\n");
        xml_.append(" $static String stField=Ex.stField+=\" \";\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_,"pkg.Ex");
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("pkg.Rec", getString(ret_));
    }
    @Test
    public void calculateArgument9Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t = 7;\n");
        xml_.append("  Int i = (Int)$lambda(Ex,exmeth,~$int);\n");
        xml_.append("  i.exmeth($that(t));\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int t){\n");
        xml_.append("  t=8;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($int t){\n");
        xml_.append("  $return t+8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Int {\n");
        xml_.append(" $void exmeth($that $int t);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_,"pkg.Ex");
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument10Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $int t = 7;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Int i = (Int)$lambda(Ex,exmeth2);\n");
        xml_.append("  i.exmeth()=8;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int t){\n");
        xml_.append("  t=8;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth2(){\n");
        xml_.append("  $return $that(t);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Int {\n");
        xml_.append(" $that $int exmeth();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_,"pkg.Ex");
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument11Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<?,$void> f = $lambda(Ex,exmeth,~$int);\n");
        xml_.append("  f.call(0);\n");
        xml_.append("  $return 0;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int t){\n");
        xml_.append("  t=8;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($int t){\n");
        xml_.append("  $return t+8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq("code.expressionlanguage.exceptions.BadArgNumber",ret_.getStruct().getClassName(cont_));
    }
    @Test
    public void calculateArgument12Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $Fct<?,$void> f = $lambda(Ex,exmeth,$int);\n");
        xml_.append("  $int t = 7;\n");
        xml_.append("  f.call($that(t));\n");
        xml_.append("  $return 0;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int t){\n");
        xml_.append("  t=8;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($int t){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq("code.expressionlanguage.exceptions.BadArgNumber",ret_.getStruct().getClassName(cont_));
    }
    @Test
    public void calculateArgument13Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t = 7;\n");
        xml_.append("  $Fct<?,?,$void> f = $lambda(Ex,exmeth,~$int,$int);\n");
        xml_.append("  f.call(0,$that(t));\n");
        xml_.append("  $return 0;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int t, $int u){\n");
        xml_.append("  t=8;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($int t){\n");
        xml_.append("  $return t+8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq("code.expressionlanguage.exceptions.DynamicCastClassException",ret_.getStruct().getClassName(cont_));
    }
    @Test
    public void calculateArgument14Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t = 7;\n");
        xml_.append("  $Fct<?,?,$void> f = $lambda(Ex,exmeth,$int,~$int);\n");
        xml_.append("  f.call($that(t),0);\n");
        xml_.append("  $return 0;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($int u,$that $int t){\n");
        xml_.append("  t=8;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($int t){\n");
        xml_.append("  $return t+8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq("code.expressionlanguage.exceptions.DynamicCastClassException",ret_.getStruct().getClassName(cont_));
    }
    @Test
    public void calculateArgument15Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Object t = \"7\";\n");
        xml_.append("  $Fct<?,?,$void> f = $lambda(Ex,exmeth,$int,~$int);\n");
        xml_.append("  f.call(0,$that(t));\n");
        xml_.append("  $return 0;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($int u,$that $int t){\n");
        xml_.append("  t=8;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($int t){\n");
        xml_.append("  $return t+8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq("code.expressionlanguage.exceptions.DynamicCastClassException",ret_.getStruct().getClassName(cont_));
    }
    @Test
    public void calculateArgument16Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $Fct<$Fct<~$int[],$int>,java.lang.Object[],java.lang.Object> f = $lambda($Fct<~$int[],$int>,call,java.lang.Object...);\n");
        xml_.append("  $Fct<~$int[],$int> g = $static().$lambda(Ex,exmethtwo,~$int...);\n");
        xml_.append("  $return $($int) f.call(g,$new java.lang.Object[]{$new $int[]{5i,13i}});\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($that $int... p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $foreach($int i:p){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(27, getNumber(ret_));
    }
    @Test
    public void calculateArgument17Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t = 7;\n");
        xml_.append("  Int i = (Int)$lambda(Ex,exmeth,~$int);\n");
        xml_.append("  $var v = $lambda(Int,exmeth,~$int);\n");
        xml_.append("  v.call(i,$that(t));\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int t){\n");
        xml_.append("  t=8;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($int t){\n");
        xml_.append("  $return t+8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Int {\n");
        xml_.append(" $void exmeth($that $int t);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_,"pkg.Ex");
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument18Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $int t = 7;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Int i = (Int)$lambda(Ex,exmeth2);\n");
        xml_.append("  $var v = $lambda(Int,exmeth);\n");
        xml_.append("  v.call(i)=8;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int t){\n");
        xml_.append("  t=8;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth2(){\n");
        xml_.append("  $return $that(t);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Int {\n");
        xml_.append(" $that $int exmeth();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_,"pkg.Ex");
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument19Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t = 7;\n");
        xml_.append("  Int i = (Int)$lambda(Ex,exmeth,~$int);\n");
        xml_.append("  $Fct<Int,~$int,$void> v = $lambda(Int,exmeth,~$int);\n");
        xml_.append("  v.call(i,$that(t));\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int t){\n");
        xml_.append("  t=8;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($int t){\n");
        xml_.append("  $return t+8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Int {\n");
        xml_.append(" $void exmeth($that $int t);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_,"pkg.Ex");
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument20Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $int t = 7;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Int i = (Int)$lambda(Ex,exmeth2);\n");
        xml_.append("  $Fct<Int,~$int> v = $lambda(Int,exmeth);\n");
        xml_.append("  v.call(i)=8;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int t){\n");
        xml_.append("  t=8;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth2(){\n");
        xml_.append("  $return $that(t);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Int {\n");
        xml_.append(" $that $int exmeth();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_,"pkg.Ex");
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument21Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $int t = 7;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $lambda(Ex,exmeth2).call() += 8;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth2(){\n");
        xml_.append("  $return $that(t);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(15, getNumber(ret_));
    }
    @Test
    public void calculateArgument22Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static String t = \"7\";\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $lambda(Ex,exmeth2).call() += \"8\";\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that String exmeth2(){\n");
        xml_.append("  $return $that(t);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("78", getString(ret_));
    }
    @Test
    public void calculateArgument23Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $int t = 7;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ($lambda(Ex,exmeth2).call()) += 8;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth2(){\n");
        xml_.append("  $return $that(t);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(15, getNumber(ret_));
    }
    @Test
    public void calculateArgument24Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $int t = 7;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ($lambda(Ex,exmeth2).call()) = 8;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth2(){\n");
        xml_.append("  $return $that(t);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument25Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $int t = 7;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $lambda(Ex,exmeth2).call() ++;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth2(){\n");
        xml_.append("  $return $that(t);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument26Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $int t = 7;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ++ $lambda(Ex,exmeth2).call();\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth2(){\n");
        xml_.append("  $return $that(t);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument27Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $int t = 7;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $lambda(Ex,exmeth2).call() ++;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth2(){\n");
        xml_.append("  $return $that(t);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(7, getNumber(ret_));
    }
    @Test
    public void calculateArgument28Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $int t = 7;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return ++ $lambda(Ex,exmeth2).call();\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth2(){\n");
        xml_.append("  $return $that(t);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument29Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" static ExOhter sec = new ExOhter();\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  sec.field=5;\n");
        xml_.append("  Fct<~ExOhter> f = $lambda(Apply,method2);\n");
        xml_.append("  ExSub2 e = f.call()+=10;\n");
        xml_.append("  if (sec.field != 15){\n");
        xml_.append("   return -1;\n");
        xml_.append("  }\n");
        xml_.append("  return e.field;\n");
        xml_.append(" }\n");
        xml_.append(" public static that ExOhter method2(){\n");
        xml_.append("  return that(sec);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExRight {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExRight $(int i){\n");
        xml_.append("  ExRight e = new ExRight();\n");
        xml_.append("  e.field=i;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSub {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass $(ExSub i){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field=i.field;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExOhter {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExClass $(ExOhter i){\n");
        xml_.append("  ExClass e = new ExClass();\n");
        xml_.append("  e.field=i.field;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append(" public static ExOhter $(ExSub i){\n");
        xml_.append("  ExOhter e = new ExOhter();\n");
        xml_.append("  e.field=i.field;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExSub2 {\n");
        xml_.append(" public int field;\n");
        xml_.append(" public static ExSub2 $(ExOhter i){\n");
        xml_.append("  ExSub2 e = new ExSub2();\n");
        xml_.append("  e.field=i.field;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("public class pkg.ExClass {\n");
        xml_.append(" public int field;\n");
        xml_.append(" operator+ ExSub(ExClass i, ExRight j){\n");
        xml_.append("  ExSub e = new ExSub();\n");
        xml_.append("  e.field=i.field+j.field;\n");
        xml_.append("  return e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgReadOnlyOk("en", files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("method");
        Argument ret_;
        ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(15, getNumber(ret_));
    }
    @Test
    public void calculateArgument30Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t = 7;\n");
        xml_.append("  app(($that u:$void)->{u=8;},$that(t));\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void app($Fct<~$int,$void> f,$that $int u){\n");
        xml_.append("  f.call($that(u));\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int t){\n");
        xml_.append("  t=8;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($int t){\n");
        xml_.append("  $return t+8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument31Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $int t = 7;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  app((:$that)->{$return $that(t);},8);\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void app($Fct<~$int> f,$int u){\n");
        xml_.append("  f.call()=u;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int t){\n");
        xml_.append("  t=8;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($int t){\n");
        xml_.append("  $return t+8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument32Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $Fct<$Fct<~$int[],$int>,java.lang.Object[],java.lang.Object> f = $lambda($Fct<~$int[],$int>,call,java.lang.Object...);\n");
        xml_.append("  $Fct<~$int[],$int> g = $static().$lambda(Ex,exmethtwo,~$int...);\n");
        xml_.append("  $return $($int) f.call(g,$new java.lang.Object[]{$new $int[]{5i,13i}});\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($that $int... p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $foreach($int i:p){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int... p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $foreach($int i:p){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(27, getNumber(ret_));
    }
    @Test
    public void calculateArgument33Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $Fct<$Fct<$int[],$int>,java.lang.Object[],java.lang.Object> f = $lambda($Fct<$int[],$int>,call,java.lang.Object...);\n");
        xml_.append("  $Fct<$int[],$int> g = $static().$lambda(Ex,exmethtwo,$int...);\n");
        xml_.append("  $return $($int) f.call(g,$new java.lang.Object[]{$new $int[]{5i,13i}});\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int... p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $foreach($int i:p){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($that $int... p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $foreach($int i:p){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(27, getNumber(ret_));
    }
    @Test
    public void calculateArgument34Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $Fct<$Fct<~$int[],$int>,java.lang.Object[],java.lang.Object> f = $lambda($Fct<~$int[],$int>,call,java.lang.Object...);\n");
        xml_.append("  $Fct<~$int[],$int> g = $static().$lambda(Ex,exmethtwo,~$int,~$int);\n");
        xml_.append("  $return $($int) f.call(g,$new java.lang.Object[]{$new $int[]{5i,13i}});\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($that $int... p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $foreach($int i:p){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int... p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $foreach($int i:p){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(27, getNumber(ret_));
    }
    @Test
    public void calculateArgument35Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $Fct<$Fct<$int[],$int>,java.lang.Object[],java.lang.Object> f = $lambda($Fct<$int[],$int>,call,java.lang.Object...);\n");
        xml_.append("  $Fct<$int[],$int> g = $static().$lambda(Ex,exmethtwo,$int,$int);\n");
        xml_.append("  $return $($int) f.call(g,$new java.lang.Object[]{$new $int[]{5i,13i}});\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($int... p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $foreach($int i:p){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo($that $int... p){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $foreach($int i:p){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(27, getNumber(ret_));
    }
    @Test
    public void calculateArgument36Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t = 7;\n");
        xml_.append("  $lambda(Ex,exmeth,$id,$static,~$int).call($that(t));\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int t){\n");
        xml_.append("  t=8;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($int t){\n");
        xml_.append("  $return t+8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument37Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $int t = 7;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $that $int u = $that($lambda(Ex,exmeth2,$id,~,$static).call());\n");
        xml_.append("  u = 8;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth2(){\n");
        xml_.append("  $return $that(t);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument38Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t = 7;\n");
        xml_.append("  $static().$lambda(Ex,exmeth,$id,Ex,~$int).call($that(t));\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int t){\n");
        xml_.append("  t=8;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($int t){\n");
        xml_.append("  $return t+8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument39Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $int t = 7;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $that $int u = $that($static().$lambda(Ex,exmeth2,$id,Ex,~).call());\n");
        xml_.append("  u = 8;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth2(){\n");
        xml_.append("  $return $that(t);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument40Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t = 7;\n");
        xml_.append("  $new Ex().$lambda(Ex,exmeth,$id,$static,~$int).call($that(t));\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int t){\n");
        xml_.append("  t=8;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($int t){\n");
        xml_.append("  $return t+8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument41Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $int t = 7;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $that $int u = $that($new Ex().$lambda(Ex,exmeth2,$id,~,$static).call());\n");
        xml_.append("  u = 8;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth2(){\n");
        xml_.append("  $return $that(t);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument42Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int f = 0;\n");
        xml_.append("  $lambda(Compo,$new,~$int).call($that(f));\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Compo {\n");
        xml_.append(" $public $int f=15;\n");
        xml_.append(" $public Compo($that $int p){\n");
        xml_.append("  p = f;\n");
        xml_.append(" }\n");
        xml_.append(" $public Compo($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(15, getNumber(ret_));
    }
    @Test
    public void calculateArgument43Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int f = 0;\n");
        xml_.append("  $new Outer().$lambda(Compo,$new,~$int).call($that(f));\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append(" $public $class Compo {\n");
        xml_.append("  $public $int f=15;\n");
        xml_.append("  $public Compo($that $int p){\n");
        xml_.append("   p = f;\n");
        xml_.append("  }\n");
        xml_.append("  $public Compo($int p){\n");
        xml_.append("   f = p;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(15, getNumber(ret_));
    }
    @Test
    public void calculateArgument44Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int f = 0;\n");
        xml_.append("  $lambda(Compo,$new,$id,~$int).call($that(f));\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Compo {\n");
        xml_.append(" $public $int f=15;\n");
        xml_.append(" $public Compo($that $int p){\n");
        xml_.append("  p = f;\n");
        xml_.append(" }\n");
        xml_.append(" $public Compo($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(15, getNumber(ret_));
    }
    @Test
    public void calculateArgument45Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int f = 0;\n");
        xml_.append("  $new Outer().$lambda(Outer.Compo,$new,$id,~$int).call($that(f));\n");
        xml_.append("  $return f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append(" $public $class Compo {\n");
        xml_.append("  $public $int f=15;\n");
        xml_.append("  $public Compo($that $int p){\n");
        xml_.append("   p = f;\n");
        xml_.append("  }\n");
        xml_.append("  $public Compo($int p){\n");
        xml_.append("   f = p;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(15, getNumber(ret_));
    }
    @Test
    public void calculateArgument46Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Static($that pkg.Static a,$that pkg.Static b) {\n");
        xml_.append(" pkg.Static o = $new pkg.Static();\n");
        xml_.append(" o.field = a.field + b.field;\n");
        xml_.append(" $return o;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Static {\n");
        xml_.append(" $public $int field;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Static s = $new Static();\n");
        xml_.append("  s.field=14i;\n");
        xml_.append("  Static t = $new Static();\n");
        xml_.append("  t.field=10i;\n");
        xml_.append("  $Fct<~Static,~Static,Static> f = $lambda($operator,+,$id,~Static,~Static);\n");
        xml_.append("  $return f.call($that(s),$that(t)).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(24, getNumber(ret_));
    }
    @Test
    public void calculateArgument47Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ $that pkg.Static($that pkg.Static a,$that pkg.Static b) {\n");
        xml_.append(" pkg.Static o = $new pkg.Static();\n");
        xml_.append(" o.field = a.field + b.field;\n");
        xml_.append(" $return $that(o);\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Static {\n");
        xml_.append(" $public $int field;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Static s = $new Static();\n");
        xml_.append("  s.field=14i;\n");
        xml_.append("  Static t = $new Static();\n");
        xml_.append("  t.field=10i;\n");
        xml_.append("  $Fct<~Static,~Static,~Static> f = $lambda($operator,+,$id,~,$static,~Static,~Static);\n");
        xml_.append("  $return f.call($that(s),$that(t)).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(24, getNumber(ret_));
    }
    @Test
    public void calculateArgument48Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Static(pkg.Static a,$that pkg.Static b) {\n");
        xml_.append(" pkg.Static o = $new pkg.Static();\n");
        xml_.append(" o.field = a.field + b.field;\n");
        xml_.append(" $return o;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Static {\n");
        xml_.append(" $public $int field;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Static s = $new Static();\n");
        xml_.append("  s.field=14i;\n");
        xml_.append("  Static t = $new Static();\n");
        xml_.append("  t.field=10i;\n");
        xml_.append("  $Fct<~Static,Static> f = s.$lambda($operator,+,$id,~Static);\n");
        xml_.append("  $return f.call($that(t)).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(24, getNumber(ret_));
    }
    @Test
    public void calculateArgument49Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ $that pkg.Static(pkg.Static a,$that pkg.Static b) {\n");
        xml_.append(" pkg.Static o = $new pkg.Static();\n");
        xml_.append(" o.field = a.field + b.field;\n");
        xml_.append(" $return $that(o);\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Static {\n");
        xml_.append(" $public $int field;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Static s = $new Static();\n");
        xml_.append("  s.field=14i;\n");
        xml_.append("  Static t = $new Static();\n");
        xml_.append("  t.field=10i;\n");
        xml_.append("  $Fct<~Static,~Static> f = s.$lambda($operator,+,$id,~,$static,~Static);\n");
        xml_.append("  $return f.call($that(t)).field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(24, getNumber(ret_));
    }
    @Test
    public void calculateArgument50Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t = 7;\n");
        xml_.append("  app($id(Ex,$Fct<~$int,$void>,~$int),($that u:$void)->{u=8;},$that(t));\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void app($Fct<~$int,$void> f,$that $int u){\n");
        xml_.append("  f.call($that(u));\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int t){\n");
        xml_.append("  t=8;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($int t){\n");
        xml_.append("  $return t+8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument51Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $Class.forName(\"java.lang.$Fct<~$int>\").getName();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("java.lang.$Fct<~$int>", getString(ret_));
    }
    @Test
    public void calculateArgument52Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $Class.forName(\"java.lang.$Fct<~>\").getName();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq("java.lang.$classNotFound",ret_.getStruct().getClassName(cont_));
    }
    @Test
    public void calculateArgument53Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $Class.forName(\"pkg.Param<~$int>\").getName();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Param<T> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq("java.lang.$classNotFound",ret_.getStruct().getClassName(cont_));
    }
    @Test
    public void calculateArgument54Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $Class.forName(\"~$int\").getName();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Param<T> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq("java.lang.$classNotFound",ret_.getStruct().getClassName(cont_));
    }
    @Test
    public void calculateArgument55Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $Class.forName(\"java.lang.$Fct<~$int>\").getActualTypeArguments().length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }
    @Test
    public void calculateArgument56Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $Class.forName(\"java.lang.$Fct<~$int>\").getPrettyName();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("java.lang.$Fct<~$int>", getString(ret_));
    }
    @Test
    public void calculateArgument57Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $Class.forName(\"java.lang.$Fct<~$int>\").getPrettySingleName();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("java.lang.$Fct<~$int>", getString(ret_));
    }
    @Test
    public void calculateArgument58Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $Class.forName(\"java.lang.$Fct<~$int>\").getActualTypeArguments()[0].isRefType();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(BooleanStruct.isTrue(ret_.getStruct()));
    }
    @Test
    public void calculateArgument59Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $Class.forName(\"java.lang.$Fct<$int>\").getActualTypeArguments()[0].isRefType();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(BooleanStruct.isFalse(ret_.getStruct()));
    }
    @Test
    public void calculateArgument60Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $return $Class.forName(\"java.lang.$Fct<~$int>\").getActualTypeArguments()[0].newArrayInstance();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq("code.expressionlanguage.exceptions.IllegalArgument",ret_.getStruct().getClassName(cont_));
    }
    @Test
    public void calculateArgument61Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class($int).makeRefType($true).getName();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("~$int", getString(ret_));
    }
    @Test
    public void calculateArgument62Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class($int).makeRefType($false).getName();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("$int", getString(ret_));
    }
    @Test
    public void calculateArgument63Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $Class.forName(\"java.lang.$Fct<~$int>\").getActualTypeArguments()[0].makeRefType($true).getName();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("~$int", getString(ret_));
    }
    @Test
    public void calculateArgument64Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $Class.forName(\"java.lang.$Fct<~$int>\").getActualTypeArguments()[0].makeRefType($false).getName();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("$int", getString(ret_));
    }
    @Test
    public void calculateArgument65Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $Class.forName(\"java.lang.$Fct<~$int>\").getActualTypeArguments()[0].makeArray().getName();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("~[$int", getString(ret_));
    }
    @Test
    public void calculateArgument66Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $Class.forName(\"java.lang.$Fct<~$int[]>\").getActualTypeArguments()[0].getComponentType().getName();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("~$int", getString(ret_));
    }
    @Test
    public void calculateArgument67Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $return $class($void).makeRefType($true) == $null;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(BooleanStruct.isTrue(ret_.getStruct()));
    }
    @Test
    public void calculateArgument68Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class(ExParam<?>).makeGeneric($class($int).makeRefType($true)).getName();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExParam<T> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("pkg.ExParam<$int>", getString(ret_));
    }
    @Test
    public void calculateArgument69Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return $class($Fct).makeGeneric($class($int).makeRefType($true)).getName();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExParam<T> {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("java.lang.$Fct<~$int>", getString(ret_));
    }
    @Test
    public void calculateArgument70Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t = 7;\n");
        xml_.append("  $that $int r = $that(t);\n");
        xml_.append("  (:$void)->{r=8;}.call();\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument71Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t = 7;\n");
        xml_.append("  exmeth($that(t));\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int r){\n");
        xml_.append("  (:$void)->{r=8;}.call();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument72Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $var v = $class(Ex).getDeclaredMethods()[1].getDeclaredAnonymousLambda()[0];\n");
        xml_.append("  v.getDeclaredAnonymousLambdaWrapVars(\"r\",(Object)8);\n");
        xml_.append("  $return ($int)v.invoke($null);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($that $int r){\n");
        xml_.append("  $return (:$int)->{$return r+=7;}.call();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(15, getNumber(ret_));
    }
    @Test
    public void calculateArgument73Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t = 7;\n");
        xml_.append("  $that $int r = $that(t);\n");
        xml_.append("  ($int r:$void)->{#r=8;}.call(0);\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument74Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t = 7;\n");
        xml_.append("  exmeth($that(t));\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int r){\n");
        xml_.append("  ($int r:$void)->{#r=8;}.call(0);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument75Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $var v = $class(Ex).getDeclaredMethods()[1].getDeclaredAnonymousLambda()[0];\n");
        xml_.append("  v.getDeclaredAnonymousLambdaWrapVars(\"r\",(Object)\"8\");\n");
        xml_.append("  $return ($int)v.invoke($null);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($that $int r){\n");
        xml_.append("  $return (:$int)->{$return r+=7;}.call();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq("code.expressionlanguage.exceptions.DynamicCastClassException", ret_.getStruct().getClassName(cont_));
    }
    @Test
    public void calculateArgument76Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t = 7;\n");
        xml_.append("  $that $int r = $that(t);\n");
        xml_.append("  $Method v = ($Method)(:$void)->{r=8;}.metaInfo();\n");
        xml_.append("  v.getDeclaredAnonymousLambdaWrapVars(\"r\",(Object)\"8\");\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq("code.expressionlanguage.exceptions.DynamicCastClassException", ret_.getStruct().getClassName(cont_));
    }
    @Test
    public void calculateArgument77Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t = 7;\n");
        xml_.append("  exmeth($that(t));\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int r){\n");
        xml_.append("  $Method v = ($Method)(:$void)->{r=8;}.metaInfo();\n");
        xml_.append("  v.getDeclaredAnonymousLambdaWrapVars(\"r\",(Object)\"8\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq("code.expressionlanguage.exceptions.DynamicCastClassException", ret_.getStruct().getClassName(cont_));
    }
    @Test
    public void calculateArgument78Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t = 7;\n");
        xml_.append("  $that $int r = $that(t);\n");
        xml_.append("  (:$void)->{(:$void)->{r=8;}.call();}.call();\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument79Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t = 7;\n");
        xml_.append("  $that $int r = $that(t);\n");
        xml_.append("  (:$void)->{exmeth($that(r));}.call();\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int s){\n");
        xml_.append("  s = 8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument80Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t = 7;\n");
        xml_.append("  $that $int r = $that(t);\n");
        xml_.append("  ($int r:$void)->{exmeth($that(#r),r);}.call(8);\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int s,$int u){\n");
        xml_.append("  s = u;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument81Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t = 7;\n");
        xml_.append("  $int t2 = 7;\n");
        xml_.append("  $that $int r = $that(t);\n");
        xml_.append("  $that $int r2 = $that(t2);\n");
        xml_.append("  ($int r,$int r2:$void)->{exmeth($that(#r),$that(#r2),r,r2);}.call(8,9);\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int s,$that $int s2,$int u,$int u2){\n");
        xml_.append("  s = u;\n");
        xml_.append("  s2 += u;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument82Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t = 7;\n");
        xml_.append("  $int t2 = 8;\n");
        xml_.append("  $that $int r = $that(t);\n");
        xml_.append("  ($that $int r:$void)->{exmeth($that(#r),r);}.call($that(t2));\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void exmeth($that $int s,$int u){\n");
        xml_.append("  s = u;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument83Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $var v = $class(Ex).getDeclaredMethods()[1].getDeclaredAnonymousLambda()[0];\n");
        xml_.append("  v.getDeclaredAnonymousLambdaWrapVars(\"r\",(Object)8);\n");
        xml_.append("  v.getDeclaredAnonymousLambdaWrapVars(\"s\",(Object)\"8\");\n");
        xml_.append("  $return ($int)v.invoke($null);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($that $int r){\n");
        xml_.append("  $return (:$int)->{$return r+=7;}.call();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(15, getNumber(ret_));
    }
    @Test
    public void calculateArgument84Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $var v = $class(Ex).getDeclaredMethods()[1].getDeclaredAnonymousLambda()[0];\n");
        xml_.append("  v.getDeclaredAnonymousLambdaWrapVars(\"r\",0,(Object)8);\n");
        xml_.append("  $return ($int)v.invoke($null);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($that $int r){\n");
        xml_.append("  $return (:$int)->{$return r+=7;}.call();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(15, getNumber(ret_));
    }
    @Test
    public void calculateArgument85Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $var v = $class(Ex).getDeclaredMethods()[1].getDeclaredAnonymousLambda()[0];\n");
        xml_.append("  v.getDeclaredAnonymousLambdaWrapVars(\"r\",0,(Object)8);\n");
        xml_.append("  $return ($int)v.invoke($null);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($that $int r){\n");
        xml_.append("  $return (:$int)->{$int v = 12;$return($that $int r:$int)->{$return #r+=r;}.call($that(v));}.call();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(20, getNumber(ret_));
    }
    @Test
    public void calculateArgument86Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $var v = $class(Ex).getDeclaredMethods()[1].getDeclaredAnonymousLambda()[0].getDeclaredAnonymousLambda()[0];\n");
        xml_.append("  v.getDeclaredAnonymousLambdaWrapVars(\"r\",0,(Object)8);\n");
        xml_.append("  v.getDeclaredAnonymousLambdaWrapVars(\"r\",1,(Object)8);\n");
        xml_.append("  v.getDeclaredAnonymousLambdaLocVars(\"v2\",0,(Object)0);\n");
        xml_.append("  v.getDeclaredAnonymousLambdaWrapVars(\"v3\",0,(Object)0);\n");
        xml_.append("  $return ($int)v.invoke($null);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($that $int r){\n");
        xml_.append("  $return (:$int)->{$int v2 = 12; $that $int r = $that(v2); $return(:$int)->{$int v = 12;$return($that $int r:$int)->{$return #r+=r;}.call($that(v));}.call();}.call();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(20, getNumber(ret_));
    }
    @Test
    public void calculateArgument87Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $var v = $class(Ex).getDeclaredMethods()[1].getDeclaredAnonymousLambda()[0].getDeclaredAnonymousLambda()[0];\n");
        xml_.append("  v.getDeclaredAnonymousLambdaWrapVars(\"r\",0,(Object)8);\n");
        xml_.append("  v.getDeclaredAnonymousLambdaWrapVars(\"r\",1,(Object)10);\n");
        xml_.append("  $return ($int)v.getDeclaredAnonymousLambdaWrapVars(\"r\",0);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($that $int r){\n");
        xml_.append("  $return (:$int)->{$int v2 = 12; $that $int r = $that(v2); $return(:$int)->{$int v = 12;$return($that $int r:$int)->{$return #r+=r;}.call($that(v));}.call();}.call();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument88Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $var v = $class(Ex).getDeclaredMethods()[1].getDeclaredAnonymousLambda()[0].getDeclaredAnonymousLambda()[0];\n");
        xml_.append("  v.getDeclaredAnonymousLambdaWrapVars(\"r\",0,(Object)8);\n");
        xml_.append("  v.getDeclaredAnonymousLambdaWrapVars(\"r\",1,(Object)10);\n");
        xml_.append("  $return ($int)v.getDeclaredAnonymousLambdaWrapVars(\"r\",1);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($that $int r){\n");
        xml_.append("  $return (:$int)->{$int v2 = 12; $that $int r = $that(v2); $return(:$int)->{$int v = 12;$return($that $int r:$int)->{$return #r+=r;}.call($that(v));}.call();}.call();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument89Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $var v = $class(Ex).getDeclaredMethods()[1].getDeclaredAnonymousLambda()[0].getDeclaredAnonymousLambda()[0];\n");
        xml_.append("  v.getDeclaredAnonymousLambdaWrapVars(\"r\",0,(Object)8);\n");
        xml_.append("  v.getDeclaredAnonymousLambdaWrapVars(\"r\",1,(Object)10);\n");
        xml_.append("  $return v.getDeclaredAnonymousLambdaWrapVars(\"r\",2);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($that $int r){\n");
        xml_.append("  $return (:$int)->{$int v2 = 12; $that $int r = $that(v2); $return(:$int)->{$int v = 12;$return($that $int r:$int)->{$return #r+=r;}.call($that(v));}.call();}.call();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertSame(NullStruct.NULL_VALUE,ret_.getStruct());
    }
    @Test
    public void calculateArgument90Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $var v = $class(Ex).getDeclaredMethods()[1].getDeclaredAnonymousLambda()[0].getDeclaredAnonymousLambda()[0];\n");
        xml_.append("  v.getDeclaredAnonymousLambdaWrapVars(\"r\",0,(Object)8);\n");
        xml_.append("  v.getDeclaredAnonymousLambdaWrapVars(\"r\",1,(Object)10);\n");
        xml_.append("  $return v.getDeclaredAnonymousLambdaWrapVars(\"s\",0);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($that $int r){\n");
        xml_.append("  $return (:$int)->{$int v2 = 12; $that $int r = $that(v2); $return(:$int)->{$int v = 12;$return($that $int r:$int)->{$return #r+=r;}.call($that(v));}.call();}.call();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertSame(NullStruct.NULL_VALUE,ret_.getStruct());
    }
    @Test
    public void calculateArgument91Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $var v = $class(Ex).getDeclaredMethods()[1].getDeclaredAnonymousLambda()[0].getDeclaredAnonymousLambda()[0];\n");
        xml_.append("  v.getDeclaredAnonymousLambdaWrapVars(\"r\",0,(Object)8);\n");
        xml_.append("  v.getDeclaredAnonymousLambdaWrapVars(\"r\",1,(Object)10);\n");
        xml_.append("  $return v.getDeclaredAnonymousLambdaWrapVars().length;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($that $int r){\n");
        xml_.append("  $return (:$int)->{$int v2 = 12; $that $int r = $that(v2); $return(:$int)->{$int v = 12;$return($that $int r:$int)->{$return #r+=r;}.call($that(v));}.call();}.call();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateArgument92Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $var v = $class(Ex).getDeclaredMethods()[1].getDeclaredAnonymousLambda()[0].getDeclaredAnonymousLambda()[0];\n");
        xml_.append("  v.getDeclaredAnonymousLambdaWrapVars(\"r\",0,(Object)8);\n");
        xml_.append("  v.getDeclaredAnonymousLambdaWrapVars(\"r\",1,(Object)10);\n");
        xml_.append("  $return ($int)v.getDeclaredAnonymousLambdaWrapVars(\"r\");\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($that $int r){\n");
        xml_.append("  $return (:$int)->{$int v2 = 12; $that $int r = $that(v2); $return(:$int)->{$int v = 12;$return($that $int r:$int)->{$return #r+=r;}.call($that(v));}.call();}.call();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument93Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $var v = $class(Ex).getDeclaredMethods()[1];\n");
        xml_.append("  $return v.getDeclaredAnonymousLambdaWrapVars();\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($that $int r){\n");
        xml_.append("  $return (:$int)->{$int v2 = 12; $that $int r = $that(v2); $return(:$int)->{$int v = 12;$return($that $int r:$int)->{$return #r+=r;}.call($that(v));}.call();}.call();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertSame(NullStruct.NULL_VALUE,ret_.getStruct());
    }
    @Test
    public void calculateArgument94Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $var v = $class(Ex).getDeclaredMethods()[1];\n");
        xml_.append("  $return v.getDeclaredAnonymousLambdaWrapVars(\"r\");\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($that $int r){\n");
        xml_.append("  $return (:$int)->{$int v2 = 12; $that $int r = $that(v2); $return(:$int)->{$int v = 12;$return($that $int r:$int)->{$return #r+=r;}.call($that(v));}.call();}.call();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertSame(NullStruct.NULL_VALUE,ret_.getStruct());
    }
    @Test
    public void calculateArgument95Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $var v = $class(Ex).getDeclaredMethods()[1];\n");
        xml_.append("  $return v.getDeclaredAnonymousLambdaWrapVars(\"r\",0);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($that $int r){\n");
        xml_.append("  $return (:$int)->{$int v2 = 12; $that $int r = $that(v2); $return(:$int)->{$int v = 12;$return($that $int r:$int)->{$return #r+=r;}.call($that(v));}.call();}.call();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertSame(NullStruct.NULL_VALUE,ret_.getStruct());
    }
    @Test
    public void calculateArgument96Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $var v = $class(Ex).getDeclaredMethods()[1];\n");
        xml_.append("  v.getDeclaredAnonymousLambdaWrapVars(\"r\",0,(Object)0);\n");
        xml_.append("  $return $null;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($that $int r){\n");
        xml_.append("  $return (:$int)->{$int v2 = 12; $that $int r = $that(v2); $return(:$int)->{$int v = 12;$return($that $int r:$int)->{$return #r+=r;}.call($that(v));}.call();}.call();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertSame(NullStruct.NULL_VALUE,ret_.getStruct());
    }
    @Test
    public void calculateArgument97Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $var v = $class(Ex).getDeclaredMethods()[1];\n");
        xml_.append("  v.getDeclaredAnonymousLambdaWrapVars(\"r\",(Object)0);\n");
        xml_.append("  $return $null;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth($that $int r){\n");
        xml_.append("  $return (:$int)->{$int v2 = 12; $that $int r = $that(v2); $return(:$int)->{$int v = 12;$return($that $int r:$int)->{$return #r+=r;}.call($that(v));}.call();}.call();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertSame(NullStruct.NULL_VALUE,ret_.getStruct());
    }
}
