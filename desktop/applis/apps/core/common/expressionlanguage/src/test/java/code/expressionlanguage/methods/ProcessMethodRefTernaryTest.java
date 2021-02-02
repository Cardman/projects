package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.functionid.MethodId;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

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
    @Test
    public void calculateArgument12Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return exmeth($true);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth($boolean b){\n");
        xml_.append("  $return $that((b?$that(p1):$that(p2)));\n");
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
    public void calculateArgument13Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return exmeth($false);\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $that $int exmeth($boolean b){\n");
        xml_.append("  $return $that((b?$that(p1):$that(p2)));\n");
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
    public void calculateArgument14Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ($true?$that(p1):$that(p2))=16;\n");
        xml_.append("  $return p1;\n");
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
    public void calculateArgument15Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ($false?$that(p1):$that(p2))=16;\n");
        xml_.append("  $return p2;\n");
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
    public void calculateArgument16Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $int p3;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  p3 = $true?p1:p2;\n");
        xml_.append("  $return p3;\n");
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
    public void calculateArgument17Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $int p3;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  p3 = $false?p1:p2;\n");
        xml_.append("  $return p3;\n");
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
    public void calculateArgument18Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $int p3;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  p3 = ($true?p1:p2);\n");
        xml_.append("  $return p3;\n");
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
    public void calculateArgument19Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $int p3;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  p3 = ($false?p1:p2);\n");
        xml_.append("  $return p3;\n");
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
    public void calculateArgument20Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $bool($true,$that(p1),$that(p2))=16;\n");
        xml_.append("  $return p1;\n");
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
    public void calculateArgument21Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $bool($false,$that(p1),$that(p2))=16;\n");
        xml_.append("  $return p2;\n");
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
    public void calculateArgument22Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $int p3;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  p3 = ($true?$that(p1):$that(p2))=16;\n");
        xml_.append("  $return p3;\n");
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
    public void calculateArgument23Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $int p3;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  p3 = ($false?$that(p1):$that(p2))=16;\n");
        xml_.append("  $return p3;\n");
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
    public void calculateArgument24Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ($true?$that(p1):$that(p2))=16;\n");
        xml_.append("  $return p2;\n");
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
    public void calculateArgument25Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ($false?$that(p1):$that(p2))=16;\n");
        xml_.append("  $return p1;\n");
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
    public void calculateArgument26Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ($true?$that(p1):$that(p2))+=16;\n");
        xml_.append("  $return p1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(31, getNumber(ret_));
    }
    @Test
    public void calculateArgument27Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ($false?$that(p1):$that(p2))+=16;\n");
        xml_.append("  $return p2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(34, getNumber(ret_));
    }
    @Test
    public void calculateArgument28Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ($true?$that(p1):$that(p2))++;\n");
        xml_.append("  $return p1;\n");
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
    public void calculateArgument29Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ($false?$that(p1):$that(p2))++;\n");
        xml_.append("  $return p2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(19, getNumber(ret_));
    }
    @Test
    public void calculateArgument30Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ++($true?$that(p1):$that(p2));\n");
        xml_.append("  $return p1;\n");
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
    public void calculateArgument31Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ++($false?$that(p1):$that(p2));\n");
        xml_.append("  $return p2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(19, getNumber(ret_));
    }
    @Test
    public void calculateArgument32Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String p1 = \"15\";\n");
        xml_.append(" $public $static String p2 = \"18\";\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  ($true?$that(p1):$that(p2))+=\"16\";\n");
        xml_.append("  $return p1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("1516", getString(ret_));
    }
    @Test
    public void calculateArgument33Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String p1 = \"15\";\n");
        xml_.append(" $public $static String p2 = \"18\";\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  ($false?$that(p1):$that(p2))+=\"16\";\n");
        xml_.append("  $return p2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("1816", getString(ret_));
    }
    @Test
    public void calculateArgument34Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Compo p1 = $new Compo(15);\n");
        xml_.append(" $public $static Compo p2 = $new Compo(18);\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ($true?$that(p1):$that(p2))+=$new Compo(16);\n");
        xml_.append("  $return p1.f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Compo {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Compo($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $operator+ Compo (Compo a, Compo b){\n");
        xml_.append("  $return $new Compo(a.f+b.f);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(31, getNumber(ret_));
    }
    @Test
    public void calculateArgument35Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Compo p1 = $new Compo(15);\n");
        xml_.append(" $public $static Compo p2 = $new Compo(18);\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ($false?$that(p1):$that(p2))+=$new Compo(16);\n");
        xml_.append("  $return p2.f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Compo {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Compo($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $operator+ Compo (Compo a, Compo b){\n");
        xml_.append("  $return $new Compo(a.f+b.f);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(34, getNumber(ret_));
    }
    @Test
    public void calculateArgument36Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Compo p1 = $new Compo(15);\n");
        xml_.append(" $public $static Compo p2 = $new Compo(18);\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ($true?$that(p1):$that(p2))++;\n");
        xml_.append("  $return p1.f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Compo {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Compo($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $operator++ Compo (Compo a){\n");
        xml_.append("  $return $new Compo(a.f+1);\n");
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
    public void calculateArgument37Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Compo p1 = $new Compo(15);\n");
        xml_.append(" $public $static Compo p2 = $new Compo(18);\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ($false?$that(p1):$that(p2))++;\n");
        xml_.append("  $return p2.f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Compo {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Compo($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $operator++ Compo (Compo a){\n");
        xml_.append("  $return $new Compo(a.f+1);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(19, getNumber(ret_));
    }
    @Test
    public void calculateArgument38Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Compo p1 = $new Compo(15);\n");
        xml_.append(" $public $static Compo p2 = $new Compo(18);\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ++($true?$that(p1):$that(p2));\n");
        xml_.append("  $return p1.f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Compo {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Compo($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $operator++ Compo (Compo a){\n");
        xml_.append("  $return $new Compo(a.f+1);\n");
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
    public void calculateArgument39Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Compo p1 = $new Compo(15);\n");
        xml_.append(" $public $static Compo p2 = $new Compo(18);\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  ++($false?$that(p1):$that(p2));\n");
        xml_.append("  $return p2.f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Compo {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Compo($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $operator++ Compo (Compo a){\n");
        xml_.append("  $return $new Compo(a.f+1);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(19, getNumber(ret_));
    }
    @Test
    public void calculateArgument40Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return ($true?$that(p1):$that(p2))++;\n");
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
    public void calculateArgument41Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return ($false?$that(p1):$that(p2))++;\n");
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
    public void calculateArgument42Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return ++($true?$that(p1):$that(p2));\n");
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
    public void calculateArgument43Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return ++($false?$that(p1):$that(p2));\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(19, getNumber(ret_));
    }
    @Test
    public void calculateArgument44Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Compo p1 = $new Compo(15);\n");
        xml_.append(" $public $static Compo p2 = $new Compo(18);\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return (($true?$that(p1):$that(p2))++).f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Compo {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Compo($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $operator++ Compo (Compo a){\n");
        xml_.append("  $return $new Compo(a.f+1);\n");
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
        xml_.append(" $public $static Compo p1 = $new Compo(15);\n");
        xml_.append(" $public $static Compo p2 = $new Compo(18);\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return (($false?$that(p1):$that(p2))++).f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Compo {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Compo($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $operator++ Compo (Compo a){\n");
        xml_.append("  $return $new Compo(a.f+1);\n");
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
    public void calculateArgument46Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Compo p1 = $new Compo(15);\n");
        xml_.append(" $public $static Compo p2 = $new Compo(18);\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return (++($true?$that(p1):$that(p2))).f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Compo {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Compo($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $operator++ Compo (Compo a){\n");
        xml_.append("  $return $new Compo(a.f+1);\n");
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
    public void calculateArgument47Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Compo p1 = $new Compo(15);\n");
        xml_.append(" $public $static Compo p2 = $new Compo(18);\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return (++($false?$that(p1):$that(p2))).f;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Compo {\n");
        xml_.append(" $public $int f;\n");
        xml_.append(" $public Compo($int p){\n");
        xml_.append("  f = p;\n");
        xml_.append(" }\n");
        xml_.append(" $operator++ Compo (Compo a){\n");
        xml_.append("  $return $new Compo(a.f+1);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(19, getNumber(ret_));
    }
    @Test
    public void calculateArgument48Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" static ExOhter sec = new ExOhter();\n");
        xml_.append(" static ExOhter third = new ExOhter();\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  sec.field=5;\n");
        xml_.append("  ExSub2 e = (true?that(sec):that(third))+=10;\n");
        xml_.append("  if (third.field != 0){\n");
        xml_.append("   return -1;\n");
        xml_.append("  }\n");
        xml_.append("  if (sec.field != 15){\n");
        xml_.append("   return -1;\n");
        xml_.append("  }\n");
        xml_.append("  return e.field;\n");
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
    public void calculateArgument49Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" static ExOhter sec = new ExOhter();\n");
        xml_.append(" static ExOhter third = new ExOhter();\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  sec.field=5;\n");
        xml_.append("  ExSub2 e = (false?that(third):that(sec))+=10;\n");
        xml_.append("  if (third.field != 0){\n");
        xml_.append("   return -1;\n");
        xml_.append("  }\n");
        xml_.append("  if (sec.field != 15){\n");
        xml_.append("   return -1;\n");
        xml_.append("  }\n");
        xml_.append("  return e.field;\n");
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
    public void calculateArgument50Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" static ExOhter sec = new ExOhter();\n");
        xml_.append(" static ExOhter third = new ExOhter();\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  sec.field=5;\n");
        xml_.append("  ExSub2 e = (true?that(sec):that(third))+=10;\n");
        xml_.append("  if (third.field != 0){\n");
        xml_.append("   return -1;\n");
        xml_.append("  }\n");
        xml_.append("  if (sec.field != 15){\n");
        xml_.append("   return -1;\n");
        xml_.append("  }\n");
        xml_.append("  return sec.field;\n");
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
    public void calculateArgument51Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Apply {\n");
        xml_.append(" static ExOhter sec = new ExOhter();\n");
        xml_.append(" static ExOhter third = new ExOhter();\n");
        xml_.append(" public static int method(){\n");
        xml_.append("  sec.field=5;\n");
        xml_.append("  ExSub2 e = (false?that(third):that(sec))+=10;\n");
        xml_.append("  if (third.field != 0){\n");
        xml_.append("   return -1;\n");
        xml_.append("  }\n");
        xml_.append("  if (sec.field != 15){\n");
        xml_.append("   return -1;\n");
        xml_.append("  }\n");
        xml_.append("  return sec.field;\n");
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
    public void calculateArgument52Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return ($true?$that(p1):$that(p2))+=16;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(31, getNumber(ret_));
    }
    @Test
    public void calculateArgument53Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int p1 = 15;\n");
        xml_.append(" $public $static $int p2 = 18;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return ($false?$that(p1):$that(p2))+=16;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(34, getNumber(ret_));
    }
}
