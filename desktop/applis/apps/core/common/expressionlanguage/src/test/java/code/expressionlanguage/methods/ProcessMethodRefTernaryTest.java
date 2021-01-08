package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.functionid.MethodId;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;

public final class ProcessMethodRefTernaryTest extends ProcessMethodCommon {
    @Test
    public void calculateArgument0Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return exmeth($true);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth($boolean b){\n");
        xml_.append("  $return $that(b?$that(p1):$that(p2));\n");
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
    public void calculateArgument1Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return exmeth($false);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth($boolean b){\n");
        xml_.append("  $return $that(b?$that(p1):$that(p2));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(18, getNumber(ret_));
    }
    @Test
    public void calculateArgument2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  exmeth($true)=16;\n");
        xml_.append("  $return p1;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth($boolean b){\n");
        xml_.append("  $return $that(b?$that(p1):$that(p2));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(16, getNumber(ret_));
    }
    @Test
    public void calculateArgument3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  exmeth($false)=16;\n");
        xml_.append("  $return p2;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth($boolean b){\n");
        xml_.append("  $return $that(b?$that(p1):$that(p2));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(16, getNumber(ret_));
    }
    @Test
    public void calculateArgument4Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  exmeth($true)=16;\n");
        xml_.append("  $return p2;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth($boolean b){\n");
        xml_.append("  $return $that(b?$that(p1):$that(p2));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(18, getNumber(ret_));
    }
    @Test
    public void calculateArgument5Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  exmeth($false)=16;\n");
        xml_.append("  $return p1;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth($boolean b){\n");
        xml_.append("  $return $that(b?$that(p1):$that(p2));\n");
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
    public void calculateArgument6Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  exmeth($new ExBool(1))=16;\n");
        xml_.append("  $return p1;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth(ExBool b){\n");
        xml_.append("  $return $that(b?$that(p1):$that(p2));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExBool {\n");
        xml_.append(" $int f;\n");
        xml_.append(" ExBool($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $boolean $true(ExBool v){\n");
        xml_.append("  $return v.f == 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(16, getNumber(ret_));
    }
    @Test
    public void calculateArgument7Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  exmeth($new ExBool(0))=16;\n");
        xml_.append("  $return p2;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth(ExBool b){\n");
        xml_.append("  $return $that(b?$that(p1):$that(p2));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExBool {\n");
        xml_.append(" $int f;\n");
        xml_.append(" ExBool($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $boolean $true(ExBool v){\n");
        xml_.append("  $return v.f == 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(16, getNumber(ret_));
    }
    @Test
    public void calculateArgument8Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  exmeth($new ExBool(1))=16;\n");
        xml_.append("  $return p1;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth(ExBool b){\n");
        xml_.append("  $return $that(b?$that(p1):$that(p2));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExBool {\n");
        xml_.append(" $int f;\n");
        xml_.append(" ExBool($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $boolean $(ExBool v){\n");
        xml_.append("  $return v.f == 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(16, getNumber(ret_));
    }
    @Test
    public void calculateArgument9Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  exmeth($new ExBool(0))=16;\n");
        xml_.append("  $return p2;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth(ExBool b){\n");
        xml_.append("  $return $that(b?$that(p1):$that(p2));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExBool {\n");
        xml_.append(" $int f;\n");
        xml_.append(" ExBool($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $boolean $(ExBool v){\n");
        xml_.append("  $return v.f == 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(16, getNumber(ret_));
    }
    @Test
    public void calculateArgument10Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return exmeth($true);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth($boolean b){\n");
        xml_.append("  $return $that($bool(b,$that(p1),$that(p2)));\n");
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
    public void calculateArgument11Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return exmeth($false);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth($boolean b){\n");
        xml_.append("  $return $that($bool(b,$that(p1),$that(p2)));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(18, getNumber(ret_));
    }
}
