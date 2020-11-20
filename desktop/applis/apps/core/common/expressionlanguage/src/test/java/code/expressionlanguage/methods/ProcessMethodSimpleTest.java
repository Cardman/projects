package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.*;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertSame;


public final class ProcessMethodSimpleTest extends ProcessMethodCommon {
    @Test
    public void calculateArgument0Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth (){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
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
        assertEq(9, getNumber(ret_));
    }
    @Test
    public void calculateArgument1Test() {
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
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(9, getNumber(ret_));
    }

    @Test
    public void calculateArgument2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(9, getNumber(ret_));
    }

    @Test
    public void calculateArgument3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+($int)(t);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(9, getNumber(ret_));
    }

    @Test
    public void calculateArgument4Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(9, getNumber(ret_));
    }

    @Test
    public void calculateArgument5Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int[] t = $new $int[]{4i};\n");
        xml_.append("  t[0]=8;\n");
        xml_.append("  $return 1i+t[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(9, getNumber(ret_));
    }
    @Test
    public void calculateArgument6Test() {
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
        ContextEl cont_ = ctxLgOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }
    @Test
    public void calculateArgument7Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int exmeth(){\n");
        xml_.append("  int s = 1;\n");
        xml_.append("  int[] t = s == 2 ?{4i}:{6i};\n");
        xml_.append("  return t[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxLgOk("en",files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(6, getNumber(ret_));
    }
    @Test
    public void calculateArgument14Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int[] l;\n");
        xml_.append("  l=$new $int[2];\n");
        xml_.append("  l[0]=8i;\n");
        xml_.append("  l[1]=2i;\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
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
        assertEq(0, getNumber(ret_));
    }

    @Test
    public void calculateArgument15Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t=8;\n");
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
        assertEq(9, getNumber(ret_));
    }

    @Test
    public void calculateArgument42Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return Resources.readNames().length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> srcFiles_ = new StringMap<String>();
        srcFiles_.put("pkg/Ex", xml_.toString());
        StringMap<String> others_ = new StringMap<String>();
        others_.put("pkg/hello_res.txt", "content");
        StringMap<String> all_ = new StringMap<String>();
        all_.putAllMap(srcFiles_);
        all_.putAllMap(others_);
        ContextEl cont_ = ctxResOk(srcFiles_, all_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }

    @Test
    public void calculateArgument42_Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return Resources.nbNames();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> srcFiles_ = new StringMap<String>();
        srcFiles_.put("pkg/Ex", xml_.toString());
        StringMap<String> others_ = new StringMap<String>();
        others_.put("pkg/hello_res.txt", "content");
        StringMap<String> all_ = new StringMap<String>();
        all_.putAllMap(srcFiles_);
        all_.putAllMap(others_);
        ContextEl cont_ = ctxResOk(srcFiles_, all_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateArgument43Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return Resources.readNames()[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> srcFiles_ = new StringMap<String>();
        srcFiles_.put("pkg/Ex", xml_.toString());
        StringMap<String> others_ = new StringMap<String>();
        others_.put("pkg/hello_res.txt", "content");
        StringMap<String> all_ = new StringMap<String>();
        all_.putAllMap(srcFiles_);
        all_.putAllMap(others_);
        ContextEl cont_ = ctxResOk(srcFiles_, all_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("pkg/hello_res.txt", getString(ret_));
    }
    @Test
    public void calculateArgument43_Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return Resources.index(1);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> srcFiles_ = new StringMap<String>();
        srcFiles_.put("pkg/Ex", xml_.toString());
        StringMap<String> others_ = new StringMap<String>();
        others_.put("pkg/hello_res.txt", "content");
        StringMap<String> all_ = new StringMap<String>();
        all_.putAllMap(srcFiles_);
        all_.putAllMap(others_);
        ContextEl cont_ = ctxResOk(srcFiles_, all_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("pkg/hello_res.txt", getString(ret_));
    }
    @Test
    public void calculateArgument43_0Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return Resources.index(-1);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> srcFiles_ = new StringMap<String>();
        srcFiles_.put("pkg/Ex", xml_.toString());
        StringMap<String> others_ = new StringMap<String>();
        others_.put("pkg/hello_res.txt", "content");
        StringMap<String> all_ = new StringMap<String>();
        all_.putAllMap(srcFiles_);
        all_.putAllMap(others_);
        ContextEl cont_ = ctxResOk(srcFiles_, all_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertSame(NullStruct.NULL_VALUE, ret_.getStruct());
    }
    @Test
    public void calculateArgument43_1Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $var i = 1;\n");
        xml_.append("  $return Resources.index(i);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> srcFiles_ = new StringMap<String>();
        srcFiles_.put("pkg/Ex", xml_.toString());
        StringMap<String> others_ = new StringMap<String>();
        others_.put("pkg/hello_res.txt", "content");
        StringMap<String> all_ = new StringMap<String>();
        all_.putAllMap(srcFiles_);
        all_.putAllMap(others_);
        ContextEl cont_ = ctxResOk(srcFiles_, all_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("pkg/hello_res.txt", getString(ret_));
    }
    @Test
    public void calculateArgument44Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return Resources.readContent(Resources.readNames()[1]);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> srcFiles_ = new StringMap<String>();
        srcFiles_.put("pkg/Ex", xml_.toString());
        StringMap<String> others_ = new StringMap<String>();
        others_.put("pkg/hello_res.txt", "content");
        StringMap<String> all_ = new StringMap<String>();
        all_.putAllMap(srcFiles_);
        all_.putAllMap(others_);
        ContextEl cont_ = ctxResOk(srcFiles_, all_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("content", getString(ret_));
    }
    @Test
    public void calculateArgument45Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return Resources.readNames()[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> srcFiles_ = new StringMap<String>();
        srcFiles_.put("pkg/Ex", xml_.toString());
        StringMap<String> others_ = new StringMap<String>();
        others_.put("pkg/hello_res.txt", "content");
        StringMap<String> all_ = new StringMap<String>();
        all_.putAllMap(srcFiles_);
        all_.putAllMap(others_);
        ContextEl cont_ = ctxResOk(srcFiles_, all_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("pkg/Ex", getString(ret_));
    }
    @Test
    public void calculateArgument46Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return Resources.readContent(Resources.readNames()[0]);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> srcFiles_ = new StringMap<String>();
        srcFiles_.put("pkg/Ex", xml_.toString());
        StringMap<String> others_ = new StringMap<String>();
        others_.put("pkg/hello_res.txt", "content");
        StringMap<String> all_ = new StringMap<String>();
        all_.putAllMap(srcFiles_);
        all_.putAllMap(others_);
        ContextEl cont_ = ctxResOk(srcFiles_, all_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(xml_.toString(), getString(ret_));
    }
    @Test
    public void calculateArgument47Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String exmeth(){\n");
        xml_.append("  $return Resources.readContent(\"\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> srcFiles_ = new StringMap<String>();
        srcFiles_.put("pkg/Ex", xml_.toString());
        StringMap<String> others_ = new StringMap<String>();
        others_.put("pkg/hello_res.txt", "content");
        StringMap<String> all_ = new StringMap<String>();
        all_.putAllMap(srcFiles_);
        all_.putAllMap(others_);
        ContextEl cont_ = ctxResOk(srcFiles_, all_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isNull());
    }
    @Test
    public void calculate48Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String meth(){\n");
        xml_.append("  $final code.util.Replacement inst = $new code.util.Replacement(\"old\",\"new\");\n");
        xml_.append("  $return inst.getOldString();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("meth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("old", getString(ret_));
    }
    @Test
    public void calculate49Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String meth(){\n");
        xml_.append("  $final code.util.Replacement inst = $new code.util.Replacement(\"old\",\"new\");\n");
        xml_.append("  $return inst.getNewString();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("meth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("new", getString(ret_));
    }
    @Test
    public void calculate50Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String meth(){\n");
        xml_.append("  $final code.util.Replacement inst = $new code.util.Replacement($null,\"new\");\n");
        xml_.append("  $return inst.getOldString();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("meth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isNull());
    }
    @Test
    public void calculate51Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String meth(){\n");
        xml_.append("  $final code.util.Replacement inst = $new code.util.Replacement(\"old\",$null);\n");
        xml_.append("  $return inst.getNewString();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("meth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isNull());
    }

    @Test
    public void calculateArgument75Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int u;\n");
        xml_.append("  u=4;\n");
        xml_.append("  $return (u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }
    @Test
    public void calculateArgument76Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($int p){\n");
        xml_.append("  $return (p) * 2;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int u;\n");
        xml_.append("  u=4;\n");
        xml_.append("  $return exmeth(u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument77Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($int p){\n");
        xml_.append("  $int sum = 0;\n");
        xml_.append("  $for ($int i = 0; i <= p; i++){\n");
        xml_.append("   sum += (i);\n");
        xml_.append("  }\n");
        xml_.append("  $return sum;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int u;\n");
        xml_.append("  u=4;\n");
        xml_.append("  $return exmeth(u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument78Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($int p){\n");
        xml_.append("  $int sum = 0;\n");
        xml_.append("  $for ($int i = 0; i <= p; i++){\n");
        xml_.append("   sum += i;\n");
        xml_.append("  }\n");
        xml_.append("  $throw sum;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int u;\n");
        xml_.append("  u=4;\n");
        xml_.append("  $try {\n");
        xml_.append("   $return exmeth (u);\n");
        xml_.append("  } $catch(Integer i) {\n");
        xml_.append("   $return (i);\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument79Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int sum = 0;\n");
        xml_.append("  $for ($int i : $new $int[]{1,2,3,4}){\n");
        xml_.append("   sum += (i);\n");
        xml_.append("  }\n");
        xml_.append("  $return sum;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument80Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($int p){\n");
        xml_.append("  $int sum = 0;\n");
        xml_.append("  $for ($int i = 0; i <= p; i++){\n");
        xml_.append("   sum += i;\n");
        xml_.append("  }\n");
        xml_.append("  $throw sum;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int a = 2;\n");
        xml_.append(" $public $static $int b = 2;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int u;\n");
        xml_.append("  u=a+b;\n");
        xml_.append("  $try {\n");
        xml_.append("   $return exmeth (u);\n");
        xml_.append("  } $catch(Integer i) {\n");
        xml_.append("   $return (i);\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument81Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($int p){\n");
        xml_.append("  $int sum = 0;\n");
        xml_.append("  $for ($int i = 0; i <= p; i++){\n");
        xml_.append("   sum += i;\n");
        xml_.append("  }\n");
        xml_.append("  $throw sum;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int a = 2;\n");
        xml_.append(" $public $static $int b = 2;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int u;\n");
        xml_.append("  u=a+b;\n");
        xml_.append("  $try {\n");
        xml_.append("   $return exmeth (u);\n");
        xml_.append("  } $catch(Integer i) {\n");
        xml_.append("   $return (i);\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument82Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int u;\n");
        xml_.append("  u=4;\n");
        xml_.append("  $return (u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }
    @Test
    public void calculateArgument83Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($int p){\n");
        xml_.append("  $return (p) * 2;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int u;\n");
        xml_.append("  u=4;\n");
        xml_.append("  $return exmeth(u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument84Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($int p){\n");
        xml_.append("  $int sum = 0;\n");
        xml_.append("  $for ($int i = 0; i <= p; i++){\n");
        xml_.append("   sum += (i);\n");
        xml_.append("  }\n");
        xml_.append("  $return sum;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int u;\n");
        xml_.append("  u=4;\n");
        xml_.append("  $return exmeth(u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument85Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($int p){\n");
        xml_.append("  $int sum = 0;\n");
        xml_.append("  $for ($int i = 0; i <= p; i++){\n");
        xml_.append("   sum += i;\n");
        xml_.append("  }\n");
        xml_.append("  $throw sum;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int u;\n");
        xml_.append("  u=4;\n");
        xml_.append("  $try {\n");
        xml_.append("   $return exmeth (u);\n");
        xml_.append("  } $catch(Integer i) {\n");
        xml_.append("   $return (i);\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument86Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int sum = 0;\n");
        xml_.append("  $for ($int i : $new $int[]{1,2,3,4}){\n");
        xml_.append("   sum += (i);\n");
        xml_.append("  }\n");
        xml_.append("  $return sum;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument87Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($int p){\n");
        xml_.append("  $int sum = 0;\n");
        xml_.append("  $for ($int i = 0; i <= p; i++){\n");
        xml_.append("   sum += i;\n");
        xml_.append("  }\n");
        xml_.append("  $throw sum;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int a = 2;\n");
        xml_.append(" $public $static $int b = 2;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int u;\n");
        xml_.append("  u=a+b;\n");
        xml_.append("  $try {\n");
        xml_.append("   $return exmeth (u);\n");
        xml_.append("  } $catch(Integer i) {\n");
        xml_.append("   $return (i);\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument88Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($int p){\n");
        xml_.append("  $int sum = 0;\n");
        xml_.append("  $iter ($int i = 0; p;; 1){\n");
        xml_.append("   sum += ([i]);\n");
        xml_.append("  }\n");
        xml_.append("  $return sum;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int u;\n");
        xml_.append("  u=4;\n");
        xml_.append("  $return exmeth(u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument89Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($int p){\n");
        xml_.append("  $int sum = 0;\n");
        xml_.append("  $iter ($int i = 0; p;; 1){\n");
        xml_.append("   sum += ([i]);\n");
        xml_.append("  }\n");
        xml_.append("  $return sum;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int u;\n");
        xml_.append("  u=4;\n");
        xml_.append("  $return exmeth(u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument90Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int sum;\n");
        xml_.append(" $public $static $int exmeth($int p){\n");
        xml_.append("  $int sum = 0;\n");
        xml_.append("  $for ($int i = 0; i <= p; i++){\n");
        xml_.append("   sum += i;\n");
        xml_.append("  }\n");
        xml_.append("  Ex out = $new Ex();\n");
        xml_.append("  out.sum = sum;\n");
        xml_.append("  $throw out;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int a = 2;\n");
        xml_.append(" $public $static $int b = 2;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int u;\n");
        xml_.append("  u=a+b;\n");
        xml_.append("  $try {\n");
        xml_.append("   $return exmeth (u);\n");
        xml_.append("  } $catch(Ex i) {\n");
        xml_.append("   $return (i.sum);\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument91Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int sum;\n");
        xml_.append(" $public $int sum(){$return sum;}\n");
        xml_.append(" $public $static $int exmeth($int p){\n");
        xml_.append("  $int sum = 0;\n");
        xml_.append("  $for ($int i = 0; i <= p; i++){\n");
        xml_.append("   sum += i;\n");
        xml_.append("  }\n");
        xml_.append("  Ex out = $new Ex();\n");
        xml_.append("  out.sum = sum;\n");
        xml_.append("  $throw out;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int a = 2;\n");
        xml_.append(" $public $static $int b = 2;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int u;\n");
        xml_.append("  u=a+b;\n");
        xml_.append("  $try {\n");
        xml_.append("   $return exmeth (u);\n");
        xml_.append("  } $catch(Ex i) {\n");
        xml_.append("   $return (i.sum());\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument92Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int sum;\n");
        xml_.append(" $public $int sum(){$return sum;}\n");
        xml_.append(" $public $static $int exmeth($int p){\n");
        xml_.append("  $int sum = 0;\n");
        xml_.append("  $for ($int i = 0; i <= p; i++){\n");
        xml_.append("   sum += i;\n");
        xml_.append("  }\n");
        xml_.append("  Ex out = $new Ex();\n");
        xml_.append("  out.sum = sum;\n");
        xml_.append("  $throw out;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int a = 2;\n");
        xml_.append(" $public $static $int b = 2;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int u;\n");
        xml_.append("  u=a+b;\n");
        xml_.append("  $try {\n");
        xml_.append("   $return exmeth (u);\n");
        xml_.append("  } $catch(Ex i) {\n");
        xml_.append("   $return (i.sum());\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument93Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int sum;\n");
        xml_.append(" $public $int sum(){$return $this.sum;}\n");
        xml_.append(" $public $static $int exmeth($int p){\n");
        xml_.append("  $int sum = 0;\n");
        xml_.append("  $for ($int i = 0; i <= p; i++){\n");
        xml_.append("   sum += i;\n");
        xml_.append("  }\n");
        xml_.append("  Ex out = $new Ex();\n");
        xml_.append("  out.sum = sum;\n");
        xml_.append("  $throw out;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int a = 2;\n");
        xml_.append(" $public $static $int b = 2;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int u;\n");
        xml_.append("  u=a+b;\n");
        xml_.append("  $try {\n");
        xml_.append("   $return exmeth (u);\n");
        xml_.append("  } $catch(Ex i) {\n");
        xml_.append("   $return (i.sum());\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument94Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int sum;\n");
        xml_.append(" $public $int sum(){$return $this.sum;}\n");
        xml_.append(" $public $static $int exmeth($int p){\n");
        xml_.append("  $int sum = 0;\n");
        xml_.append("  $for ($int i = 0; i <= p; i++){\n");
        xml_.append("   sum += i;\n");
        xml_.append("  }\n");
        xml_.append("  Ex out = $new Ex();\n");
        xml_.append("  out.sum = sum;\n");
        xml_.append("  $throw out;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int a = 2;\n");
        xml_.append(" $public $static $int b = 2;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int u;\n");
        xml_.append("  u=a+b;\n");
        xml_.append("  $try {\n");
        xml_.append("   $return exmeth (u);\n");
        xml_.append("  } $catch(Ex i) {\n");
        xml_.append("   $return (i.sum());\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument95Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int sum;\n");
        xml_.append(" $public $int othersum(){$return $this.sum;}\n");
        xml_.append(" $public $int sum(){$return $this.othersum();}\n");
        xml_.append(" $public $static $int exmeth($int p){\n");
        xml_.append("  $int sum = 0;\n");
        xml_.append("  $for ($int i = 0; i <= p; i++){\n");
        xml_.append("   sum += i;\n");
        xml_.append("  }\n");
        xml_.append("  Ex out = $new Ex();\n");
        xml_.append("  out.sum = sum;\n");
        xml_.append("  $throw out;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int a = 2;\n");
        xml_.append(" $public $static $int b = 2;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int u;\n");
        xml_.append("  u=a+b;\n");
        xml_.append("  $try {\n");
        xml_.append("   $return exmeth (u);\n");
        xml_.append("  } $catch(Ex i) {\n");
        xml_.append("   $return (i.sum());\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument96Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int sum;\n");
        xml_.append(" $public $int othersum(){$return $this.sum;}\n");
        xml_.append(" $public $int sum(){$return $this.othersum();}\n");
        xml_.append(" $public $static $int exmeth($int p){\n");
        xml_.append("  $int sum = 0;\n");
        xml_.append("  $for ($int i = 0; i <= p; i++){\n");
        xml_.append("   sum += i;\n");
        xml_.append("  }\n");
        xml_.append("  Ex out = $new Ex();\n");
        xml_.append("  out.sum = sum;\n");
        xml_.append("  $throw out;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int a = 2;\n");
        xml_.append(" $public $static $int b = 2;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int u;\n");
        xml_.append("  u=a+b;\n");
        xml_.append("  $try {\n");
        xml_.append("   $return exmeth (u);\n");
        xml_.append("  } $catch(Ex i) {\n");
        xml_.append("   $return (i.sum());\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument97Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int sum;\n");
        xml_.append(" $public $int sum(){$return sum;}\n");
        xml_.append(" $public $static $int exmeth($int p){\n");
        xml_.append("  $int sum = 0;\n");
        xml_.append("  $for ($int i = 0; i <= p; i++){\n");
        xml_.append("   sum += i;\n");
        xml_.append("  }\n");
        xml_.append("  Ex out = $new Ex();\n");
        xml_.append("  out.sum = sum;\n");
        xml_.append("  $throw out;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int a = 2;\n");
        xml_.append(" $public $static $int b = 2;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int u;\n");
        xml_.append("  u=a+b;\n");
        xml_.append("  $try {\n");
        xml_.append("   $return exmeth (u);\n");
        xml_.append("  } $catch(Ex i) {\n");
        xml_.append("   $return (i.sum());\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument98Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int sum;\n");
        xml_.append(" $public Ex cur;\n");
        xml_.append(" $public Ex(){\n");
        xml_.append("  cur = $this;\n");
        xml_.append("  cur.cur = $this;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int sum(){$return sum;}\n");
        xml_.append(" $public $static $int exmeth($int p){\n");
        xml_.append("  $int sum = 0;\n");
        xml_.append("  $for ($int i = 0; i <= p; i++){\n");
        xml_.append("   sum += i;\n");
        xml_.append("  }\n");
        xml_.append("  Ex out = $new Ex();\n");
        xml_.append("  out.sum = sum;\n");
        xml_.append("  $throw out;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int a = 2;\n");
        xml_.append(" $public $static $int b = 2;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int u;\n");
        xml_.append("  u=a+b;\n");
        xml_.append("  $try {\n");
        xml_.append("   $return exmeth (u);\n");
        xml_.append("  } $catch(Ex i) {\n");
        xml_.append("   $return (i.sum());\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument99Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int sum;\n");
        xml_.append(" $public Ex cur;\n");
        xml_.append(" $public Ex(){\n");
        xml_.append("  cur = $this;\n");
        xml_.append("  cur.cur = $this;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int sum(){$return sum;}\n");
        xml_.append(" $public $static $int exmeth($int p){\n");
        xml_.append("  $int sum = 0;\n");
        xml_.append("  $for ($int i = 0; i <= p; i++){\n");
        xml_.append("   sum += i;\n");
        xml_.append("  }\n");
        xml_.append("  Ex out = $new Ex();\n");
        xml_.append("  out.sum = sum;\n");
        xml_.append("  $throw out;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int a = 2;\n");
        xml_.append(" $public $static $int b = 2;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int u;\n");
        xml_.append("  u=a+b;\n");
        xml_.append("  $try {\n");
        xml_.append("   $return exmeth (u);\n");
        xml_.append("  } $catch(Ex i) {\n");
        xml_.append("   $return (i.sum());\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument100Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($int p){\n");
        xml_.append("  $int sum = 0;\n");
        xml_.append("  $iter ($int i = 0; p;; 1){\n");
        xml_.append("   sum += ([i]);\n");
        xml_.append("  }\n");
        xml_.append("  $return sum;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int u;\n");
        xml_.append("  u=4;\n");
        xml_.append("  $return exmeth(u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void processEl101Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$class pkgtwo.ExClass {\n");
        xml_.append(" $private $static String out;\n");
        xml_.append(" $static {\n");
        xml_.append("  $for(String multi=`\n");
        xml_.append("  static {``next\"// /*\t)`,line=`now\nreturn to line\n``but capture all`+' '+'\\\\'+\" \"+\"\\\"\";;){\n");
        xml_.append("   out = multi + line;\n");
        xml_.append("   $return;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $static {\n");
        xml_.append("  $for(String single=\"`\";;){\n");
        xml_.append("   out += single;\n");
        xml_.append("   $return;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkgtwo.ExClass"));
        Struct out_ = getStaticField(cont_, new ClassField("pkgtwo.ExClass", "out"));
        assertEq("\n  static {`next\"// /*\t)now\nreturn to line\n`but capture all \\ \"`",((StringStruct)out_).getInstance());
    }
    @Test
    public void processEl102Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$class pkgtwo.ExClass {\n");
        xml_.append(" $private $static String out = m();\n");
        xml_.append(" $private $static String m(){\n");
        xml_.append("  String v = `\t`; $final $stack[] st = $stack.current();\n");
        xml_.append("  $return st[0].toString()+st[1].toString();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkgtwo.ExClass"));
        Struct out_ = getStaticField(cont_, new ClassField("pkgtwo.ExClass", "out"));
        assertEq("pkg/Ex:2,32:55\npkgtwo.ExClass.pkg/Ex:4,48:136\npkgtwo.ExClass.$static m()",((StringStruct)out_).getInstance());
    }
    @Test
    public void processEl1021Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$class pkgtwo.ExClass {\n");
        xml_.append(" $private $static String out = m();\n");
        xml_.append(" $private $static String m(){\n");
        xml_.append("  String v = `\t`; $final $stack[] st = $stack.currentFull();\n");
        xml_.append("  $return st[0].toString()+st[1].toString();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkgtwo.ExClass"));
        Struct out_ = getStaticField(cont_, new ClassField("pkgtwo.ExClass", "out"));
        assertEq("pkg/Ex:2,32:55\npkgtwo.ExClass.pkg/Ex:4,48:136\npkgtwo.ExClass.$static m()",((StringStruct)out_).getInstance());
    }
    @Test
    public void processEl1022Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$class pkgtwo.ExClass {\n");
        xml_.append(" $private $static String out = m();\n");
        xml_.append(" $private $static String m(){\r\n");
        xml_.append("  String v = `\t`; $final $stack[] st = $stack.current();\n");
        xml_.append("  $return st[0].toString()+st[1].toString();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkgtwo.ExClass"));
        Struct out_ = getStaticField(cont_, new ClassField("pkgtwo.ExClass", "out"));
        assertEq("pkg/Ex:2,32:55\npkgtwo.ExClass.pkg/Ex:4,48:137\npkgtwo.ExClass.$static m()",((StringStruct)out_).getInstance());
    }
    @Test
    public void processEl1023Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$class pkgtwo.ExClass {\n");
        xml_.append(" $private $static String out = m();\n");
        xml_.append(" $private $static String m(){\n");
        xml_.append("  String v = `\t`;\r $final $stack[] st = $stack.current();\n");
        xml_.append("  $return st[0].toString()+st[1].toString();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkgtwo.ExClass"));
        Struct out_ = getStaticField(cont_, new ClassField("pkgtwo.ExClass", "out"));
        assertEq("pkg/Ex:2,32:55\npkgtwo.ExClass.pkg/Ex:5,30:137\npkgtwo.ExClass.$static m()",((StringStruct)out_).getInstance());
    }

    @Test
    public void processEl104Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$class pkgtwo.ExClass {\n");
        xml_.append(" $private $static String out = m();\n");
        xml_.append(" $private $static String m(){\n");
        xml_.append("  String v = \"\t\"; $final $stack[] st = $stack.current();\n");
        xml_.append("  $return st[0].toString()+st[1].toString();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkgtwo.ExClass"));
        Struct out_ = getStaticField(cont_, new ClassField("pkgtwo.ExClass", "out"));
        assertEq("pkg/Ex:2,32:55\npkgtwo.ExClass.pkg/Ex:4,48:136\npkgtwo.ExClass.$static m()",((StringStruct)out_).getInstance());
    }
    @Test
    public void processEl105Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$class pkgtwo.ExClass {\n");
        xml_.append(" $private $static String out = m();\n");
        xml_.append(" $private $static String m(){\n");
        xml_.append("  String v = \"\n\"; $final $stack[] st = $stack.current();\n");
        xml_.append("  $return st[0].toString()+st[1].toString();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkgtwo.ExClass"));
        Struct out_ = getStaticField(cont_, new ClassField("pkgtwo.ExClass", "out"));
        assertEq("pkg/Ex:2,32:55\npkgtwo.ExClass.pkg/Ex:5,32:136\npkgtwo.ExClass.$static m()",((StringStruct)out_).getInstance());
    }
    @Test
    public void processEl108Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$class pkgtwo.ExClass {\n");
        xml_.append(" $private $static String out = m();\n");
        xml_.append(" $private $static String m(){\n");
        xml_.append("  $char v = '\t'; $final $stack[] st = $stack.current();\n");
        xml_.append("  $return st[0].toString()+st[1].toString();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkgtwo.ExClass"));
        Struct out_ = getStaticField(cont_, new ClassField("pkgtwo.ExClass", "out"));
        assertEq("pkg/Ex:2,32:55\npkgtwo.ExClass.pkg/Ex:4,48:135\npkgtwo.ExClass.$static m()",((StringStruct)out_).getInstance());
    }
    @Test
    public void processEl109Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$class pkgtwo.ExClass {\n");
        xml_.append(" $private $static String out = m();\n");
        xml_.append(" $private $static String m(){\n");
        xml_.append("  $char v = '\n'; $final $stack[] st = $stack.current();\n");
        xml_.append("  $return st[0].toString()+st[1].toString();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkgtwo.ExClass"));
        Struct out_ = getStaticField(cont_, new ClassField("pkgtwo.ExClass", "out"));
        assertEq("pkg/Ex:2,32:55\npkgtwo.ExClass.pkg/Ex:5,32:135\npkgtwo.ExClass.$static m()",((StringStruct)out_).getInstance());
    }
    @Test
    public void processEl103Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$class pkgtwo.ExClass {");
        xml_.append(" $private $static String out = m();");
        xml_.append(" $private $static String m(){");
        xml_.append("  String v = `\t`; $final $stack[] st = $stack.current();");
        xml_.append("  $return st[0].toString()+st[1].toString();");
        xml_.append(" }");
        xml_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkgtwo.ExClass"));
        Struct out_ = getStaticField(cont_, new ClassField("pkgtwo.ExClass", "out"));
        assertEq("pkg/Ex:1,55:54\npkgtwo.ExClass.pkg/Ex:1,136:133\npkgtwo.ExClass.$static m()",((StringStruct)out_).getInstance());
    }

    @Test
    public void calculateArgument104Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $final $int u;\n");
        xml_.append("  $final $int v = $bool(t==8,u=10,u=11)+1;\n");
        xml_.append("  $return u+v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(21, getNumber(ret_));
    }

    @Test
    public void calculateArgument105Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $final $boolean u;\n");
        xml_.append("  $final $boolean v = $bool(t==8,u=$false,u=$true);\n");
        xml_.append("  $return u;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isFalse());
    }
    @Test
    public void processEl106Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$class pkgtwo.ExClass {");
        xml_.append(" $private $static ExClass instance = $new ExClass();");
        xml_.append(" $private $static String value = instance.out;");
        xml_.append(" $private String out;");
        xml_.append(" $private ExClass(){");
        xml_.append("  String v = `\t`; $final $stack[] st = $stack.current();");
        xml_.append("  out = st[0].toString()+st[1].toString();");
        xml_.append(" }");
        xml_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkgtwo.ExClass"));
        Struct out_ = getStaticField(cont_, new ClassField("pkgtwo.ExClass", "value"));
        assertEq("pkg/Ex:1,61:60\npkgtwo.ExClass.pkg/Ex:1,212:208\npkgtwo.ExClass.()",((StringStruct)out_).getInstance());
    }
    @Test
    public void processEl107Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$class pkgtwo.ExClass {");
        xml_.append(" $private $static ExClass instance = $new ExClass();");
        xml_.append(" $private $static String value = instance.out;");
        xml_.append(" $private String out;");
        xml_.append(" $private ExClass($int... p){");
        xml_.append("  String v = `\t`; $final $stack[] st = $stack.current();");
        xml_.append("  out = st[0].toString()+st[1].toString();");
        xml_.append(" }");
        xml_.append("}");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkgtwo.ExClass"));
        Struct out_ = getStaticField(cont_, new ClassField("pkgtwo.ExClass", "value"));
        assertEq("pkg/Ex:1,61:60\npkgtwo.ExClass.pkg/Ex:1,220:217\npkgtwo.ExClass.($int...)",((StringStruct)out_).getInstance());
    }

    @Test
    public void calculateArgument108Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $final $int u;\n");
        xml_.append("  $final $int v = $bool($true,u=10,u=11)+1;\n");
        xml_.append("  $return u+v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = checkWarn(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(21, getNumber(ret_));
    }

    @Test
    public void calculateArgument109Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  StringBuilder a = $new StringBuilder();\n");
        xml_.append("  StringBuilder b = $new StringBuilder();\n");
        xml_.append("  $int v = $bool(StringBuilder.same(a,b),0,2);\n");
        xml_.append("  $return v;\n");
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
    public void calculateArgument110Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  StringBuilder a = $new StringBuilder();\n");
        xml_.append("  StringBuilder b = a;\n");
        xml_.append("  $int v = $bool(StringBuilder.same(a,b),2,0);\n");
        xml_.append("  $return v;\n");
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
    public void calculateArgument111Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  StringBuilder a = $new StringBuilder();\n");
        xml_.append("  StringBuilder b = a;\n");
        xml_.append("  $int v = $bool(a==b,2,1);\n");
        xml_.append("  $return v;\n");
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
    public void calculateArgument112Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  StringBuilder a = $new StringBuilder();\n");
        xml_.append("  StringBuilder b = $new StringBuilder();\n");
        xml_.append("  $int v = $bool(a==b,2,1);\n");
        xml_.append("  $return v;\n");
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
    public void calculateArgument113Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  StringBuilder a = $new StringBuilder();\n");
        xml_.append("  StringBuilder b = $new StringBuilder();\n");
        xml_.append("  $int v = $bool(CharSequence.equals(a,b),2,1);\n");
        xml_.append("  $return v;\n");
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
    public void calculateArgument114Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int v = $bool(CharSequence.equals(\"\",$null),2,1);\n");
        xml_.append("  $return v;\n");
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
    public void calculateArgument115Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int v = $bool(CharSequence.equals($null,\"\"),2,1);\n");
        xml_.append("  $return v;\n");
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
    public void calculateArgument116Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int v = $bool(CharSequence.equals($null,$null),2,1);\n");
        xml_.append("  $return v;\n");
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
    public void calculateArgument117Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  StringBuilder a = $new StringBuilder(\"hello\");\n");
        xml_.append("  StringBuilder b = $new StringBuilder(\"world\");\n");
        xml_.append("  $int v = $bool(CharSequence.equals(a,b),2,1);\n");
        xml_.append("  $return v;\n");
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
    public void calculateArgument1FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  $long u=8;\n");
        xml_.append("  t=u;\n");
        xml_.append("  $return 1i+t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void calculateArgument2FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int[] t = $new $int[]{4i};\n");
        xml_.append("  $long u=8;\n");
        xml_.append("  t[0]=u;\n");
        xml_.append("  $return 1i+t[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    
    @Test
    public void calculateArgument7FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int sum;\n");
        xml_.append(" $public Ex cur;\n");
        xml_.append(" $public Ex(){\n");
        xml_.append("  cur = $this;\n");
        xml_.append("  cur.cur = $this;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int sum(){$return sum;}\n");
        xml_.append(" $public $static $int exmeth($int p){\n");
        xml_.append("  $int sum = 0;\n");
        xml_.append("  $for ($int i = 0; i <= p; i++){\n");
        xml_.append("   sum; += ([a]).b;\n");
        xml_.append("  }\n");
        xml_.append("  Ex out = $new Ex();\n");
        xml_.append("  out.sum = sum;\n");
        xml_.append("  $throw out;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int a = 2;\n");
        xml_.append(" $public $static $int b = 2;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int u;\n");
        xml_.append("  u=a+b;\n");
        xml_.append("  $try {\n");
        xml_.append("   $return exmeth (u);\n");
        xml_.append("  } $catch(Ex i) {\n");
        xml_.append("   $return (i.sum());\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void calculateArgument118Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $boolean t;\n");
        xml_.append("  t=$true;\n");
        xml_.append("  t&&=$true;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isTrue());
    }
    @Test
    public void calculateArgument119Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $boolean t;\n");
        xml_.append("  t=$false;\n");
        xml_.append("  t&&=1>1/0;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isFalse());
    }
    @Test
    public void calculateArgument120Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $boolean t;\n");
        xml_.append("  t=$false;\n");
        xml_.append("  t||=$false;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isFalse());
    }
    @Test
    public void calculateArgument121Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $boolean t;\n");
        xml_.append("  t=$true;\n");
        xml_.append("  t||=1>1/0;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isTrue());
    }
    @Test
    public void calculateArgument122Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $boolean t;\n");
        xml_.append("  t=$true;\n");
        xml_.append("  t&&=$false;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isFalse());
    }
    @Test
    public void calculateArgument123Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $boolean t;\n");
        xml_.append("  t=$false;\n");
        xml_.append("  t||=$true;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isTrue());
    }
    @Test
    public void calculateArgument124Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $boolean t;\n");
        xml_.append("  t=$false;\n");
        xml_.append("  $var res = t&&=1>1/0;\n");
        xml_.append("  $return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isFalse());
    }
    @Test
    public void calculateArgument125Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $boolean t;\n");
        xml_.append("  t=$true;\n");
        xml_.append("  $var res = t||=1>1/0;\n");
        xml_.append("  $return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isTrue());
    }
    @Test
    public void calculateArgument0FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Boolean t;\n");
        xml_.append("  t=$true;\n");
        xml_.append("  $return ((boolean)t) == $true?2;0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void calculateArgument52Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0;\n");
        xml_.append("  $return t^0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calculateArgument53Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0;\n");
        xml_.append("  $return t&0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calculateArgument54Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0;\n");
        xml_.append("  $return t|0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calculateArgument55Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0;\n");
        xml_.append("  $return t<<0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calculateArgument56Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0;\n");
        xml_.append("  $return t>>0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calculateArgument57Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0;\n");
        xml_.append("  $return t<<<0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calculateArgument58Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0;\n");
        xml_.append("  $return t>>>0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calculateArgument59Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0;\n");
        xml_.append("  $return t<<<<0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calculateArgument60Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0;\n");
        xml_.append("  $return t>>>>0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calculateArgument61Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0;\n");
        xml_.append("  $return ~t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(-1, getNumber(ret_));
    }
    @Test
    public void calculateArgument62Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0;\n");
        xml_.append("  $return +t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calculateArgument63Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0;\n");
        xml_.append("  $return -t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calculateArgument64Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  String t;\n");
        xml_.append("  t=\"0\";\n");
        xml_.append("  $return t<\"0\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isFalse());
    }
    @Test
    public void calculateArgument65Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  String t;\n");
        xml_.append("  t=\"0\";\n");
        xml_.append("  $return t<=\"0\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isTrue());
    }
    @Test
    public void calculateArgument66Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  String t;\n");
        xml_.append("  t=\"0\";\n");
        xml_.append("  $return t>\"0\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isFalse());
    }
    @Test
    public void calculateArgument67Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  String t;\n");
        xml_.append("  t=\"0\";\n");
        xml_.append("  $return t>=\"0\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isTrue());
    }
    @Test
    public void calculateArgument68Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  $int u;\n");
        xml_.append("  t=5;\n");
        xml_.append("  u=4;\n");
        xml_.append("  $return t+--u;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument69Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int u;\n");
        xml_.append("  u=4;\n");
        xml_.append("  $return (u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }
    @Test
    public void calculateArgument70Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($int p){\n");
        xml_.append("  $return (p) * 2;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int u;\n");
        xml_.append("  u=4;\n");
        xml_.append("  $return exmeth(u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(8, getNumber(ret_));
    }
    @Test
    public void calculateArgument71Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($int p){\n");
        xml_.append("  $int sum = 0;\n");
        xml_.append("  $for ($int i = 0; i <= p; i++){\n");
        xml_.append("   sum += (i);\n");
        xml_.append("  }\n");
        xml_.append("  $return sum;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int u;\n");
        xml_.append("  u=4;\n");
        xml_.append("  $return exmeth(u);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument72Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($int p){\n");
        xml_.append("  $int sum = 0;\n");
        xml_.append("  $for ($int i = 0; i <= p; i++){\n");
        xml_.append("   sum += i;\n");
        xml_.append("  }\n");
        xml_.append("  $throw sum;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int u;\n");
        xml_.append("  u=4;\n");
        xml_.append("  $try {\n");
        xml_.append("   $return exmeth (u);\n");
        xml_.append("  } $catch(Integer i) {\n");
        xml_.append("   $return (i);\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument73Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int sum = 0;\n");
        xml_.append("  $for ($int i : $new $int[]{1,2,3,4}){\n");
        xml_.append("   sum += (i);\n");
        xml_.append("  }\n");
        xml_.append("  $return sum;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }
    @Test
    public void calculateArgument73FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int sum = 0;\n");
        xml_.append("  $for ($int i ; ){\n");
        xml_.append("   sum += (i);\n");
        xml_.append("  }\n");
        xml_.append("  $return sum;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument74Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth($int p){\n");
        xml_.append("  $int sum = 0;\n");
        xml_.append("  $for ($int i = 0; i <= p; i++){\n");
        xml_.append("   sum += i;\n");
        xml_.append("  }\n");
        xml_.append("  $throw sum;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int a = 2;\n");
        xml_.append(" $public $static $int b = 2;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int u;\n");
        xml_.append("  u=a+b;\n");
        xml_.append("  $try {\n");
        xml_.append("   $return exmeth (u);\n");
        xml_.append("  } $catch(Integer i) {\n");
        xml_.append("   $return (i);\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(10, getNumber(ret_));
    }

    @Test
    public void calculateArgument14FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=5;\n");
        xml_.append("  t&&=$true;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }

    @Test
    public void calculateArgument15FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $boolean t;\n");
        xml_.append("  t=$true;\n");
        xml_.append("  t&&=5;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }

    @Test
    public void calculateArgument16FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=5;\n");
        xml_.append("  t||=$true;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }

    @Test
    public void calculateArgument17FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $boolean t;\n");
        xml_.append("  t=$true;\n");
        xml_.append("  t||=5;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }

    @Test
    public void calculateArgument18FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=5;\n");
        xml_.append("  t&&&=$true;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }

    @Test
    public void calculateArgument19FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $boolean t;\n");
        xml_.append("  t=$true;\n");
        xml_.append("  t&&&=5;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }

    @Test
    public void calculateArgument20FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=5;\n");
        xml_.append("  t|||=$true;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }

    @Test
    public void calculateArgument21FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $boolean t;\n");
        xml_.append("  t=$true;\n");
        xml_.append("  t|||=5;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }

    @Test
    public void calculateArgument22FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  t&&&=;\n");
        xml_.append("  $return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }

    @Test
    public void calculateArgument23FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  t|||=;\n");
        xml_.append("  $return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }
    @Test
    public void calculateArgument24FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  t&&&0;\n");
        xml_.append("  $return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }
    @Test
    public void calculateArgument25FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  t&&&;\n");
        xml_.append("  $return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }
    @Test
    public void calculateArgument26FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  t|||t;\n");
        xml_.append("  $return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }
    @Test
    public void calculateArgument27FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  t|||;\n");
        xml_.append("  $return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }

    @Test
    public void calculateArgument28FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  t???=;\n");
        xml_.append("  $return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }

    @Test
    public void calculateArgument29FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  t???t;\n");
        xml_.append("  $return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }

    @Test
    public void calculateArgument30FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  t???;\n");
        xml_.append("  $return 0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }
    @Test
    public void calculateArgument16Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(9, getNumber(ret_));
    }
    @Test
    public void calculateArgument17Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return t>7?1i+($int)t:0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(9, getNumber(ret_));
    }
    @Test
    public void calculateArgument18Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return t<=7?0:1i+($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(9, getNumber(ret_));
    }
    @Test
    public void calculateArgument19Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return t<=7?0:t=9;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(9, getNumber(ret_));
    }
    @Test
    public void calculateArgument20Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return t<=7?0:t+=9;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(17, getNumber(ret_));
    }
    @Test
    public void calculateArgument21Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  t<<=2;\n");
        xml_.append("  $return 1i+($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(33, getNumber(ret_));
    }
    @Test
    public void calculateArgument22Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  t>>=2;\n");
        xml_.append("  $return 1i+($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(3, getNumber(ret_));
    }
    @Test
    public void calculateArgument23Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  t<<=66l;\n");
        xml_.append("  $return 1i+($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(33, getNumber(ret_));
    }
    @Test
    public void calculateArgument24Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  t>>=66l;\n");
        xml_.append("  $return 1i+($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(3, getNumber(ret_));
    }
    @Test
    public void calculateArgument25Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return t>7?t<7?1i+($int)t:5:0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(5, getNumber(ret_));
    }
    @Test
    public void calculateArgument26Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return t<7?4:t>7?1i+($int)t:0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(9, getNumber(ret_));
    }
    @Test
    public void calculateArgument27Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $int out = t<7?4:t>7?1i+($int)t:0;\n");
        xml_.append("  $return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(9, getNumber(ret_));
    }
    @Test
    public void calculateArgument28Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $int out = (t>7?t%2==0:t<5)?1i+($int)t:0;\n");
        xml_.append("  $return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(9, getNumber(ret_));
    }
    @Test
    public void calculateArgument29Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return (t>7?t%2==0:t<5)?1i+($int)t:0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(9, getNumber(ret_));
    }
    @Test
    public void calculateArgument30Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  t<<<=2;\n");
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
        assertEq(32, getNumber(ret_));
    }
    @Test
    public void calculateArgument31Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  t>>>=2;\n");
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
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateArgument32Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  t<<<<=2;\n");
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
        assertEq(32, getNumber(ret_));
    }
    @Test
    public void calculateArgument33Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  t>>>>=2;\n");
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
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateArgument34Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Integer t;\n");
        xml_.append("  t=$null;\n");
        xml_.append("  $return ((Boolean)t) == $null?2:0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateArgument35Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Boolean t;\n");
        xml_.append("  t=$true;\n");
        xml_.append("  $return (($boolean)t) == $true?2:0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateArgument36Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return (($boolean)1) == $true?2:0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument arg_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq("code.expressionlanguage.exceptions.DynamicCastClassException",arg_.getStruct().getClassName(cont_));
    }
    @Test
    public void calculateArgument37Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return (($int)$true) == $true?2:0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument arg_ = calculateError("pkg.Ex", id_, args_, cont_);
        assertEq("code.expressionlanguage.exceptions.DynamicCastClassException", arg_.getStruct().getClassName(cont_));
    }
    @Test
    public void calculateArgument38Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Boolean t;\n");
        xml_.append("  t=$true;\n");
        xml_.append("  $return (!t) == $false?2:0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateArgument39Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  Boolean t;\n");
        xml_.append("  t=$false;\n");
        xml_.append("  $return (!t) == $true?2:0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculateArgument40Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $double exmeth(){\n");
        xml_.append("  $return $math.random();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        NumberStruct res_ = (NumberStruct) ret_.getStruct();
        assertTrue(res_ instanceof DoubleStruct);
        assertTrue(res_.doubleStruct() >= 0.0d);
        assertTrue(res_.doubleStruct() < 1.0d);
    }
    @Test
    public void calculateArgument41Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $long exmeth(){\n");
        xml_.append("  $return $math.random(8l);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        NumberStruct res_ = (NumberStruct) ret_.getStruct();
        assertTrue(res_ instanceof LongStruct);
        assertTrue(res_.longStruct() >= 0);
        assertTrue(res_.longStruct() < 8);
    }
    @Test
    public void calculateArgument48Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $final Object[] arr = ($int[]){1,2};\n");
        xml_.append(" $public $static Object exmeth (){\n");
        xml_.append("  $return arr;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        Struct arr_ = ret_.getStruct();
        assertEq("[$int", arr_.getClassName(cont_));
        assertEq(2, ((ArrayStruct)arr_).getInstance().length);
        assertEq(1, ((IntStruct)((ArrayStruct)arr_).getInstance()[0]).intStruct());
        assertEq(2, ((IntStruct)((ArrayStruct)arr_).getInstance()[1]).intStruct());
    }
    @Test
    public void calculateArgument49Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $final Object[] arr = (ExTwo<$int>[])$new ExTwo<>[2];\n");
        xml_.append(" $static {arr[0]=(ExTwo<$int>)$new ExTwo<>(1);arr[1]=(ExTwo<$int>)$new ExTwo<>(2);}\n");
        xml_.append(" $public $static Object exmeth (){\n");
        xml_.append("  $return arr;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $private T v;\n");
        xml_.append(" $public ExTwo(T v){\n");
        xml_.append("  $this.v=v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        Struct arr_ = ret_.getStruct();
        assertEq("[pkg.ExTwo<$int>", arr_.getClassName(cont_));
        assertEq(2, ((ArrayStruct)arr_).getInstance().length);
        Struct first_ = ((ArrayStruct) arr_).getInstance()[0];
        Struct second_ = ((ArrayStruct) arr_).getInstance()[1];
        assertEq("pkg.ExTwo<$int>", first_.getClassName(cont_));
        assertEq("pkg.ExTwo<$int>", second_.getClassName(cont_));
        assertEq(1, ((IntStruct) getField(first_, new ClassField("pkg.ExTwo", "v"))).intStruct());
        assertEq(2, ((IntStruct) getField(second_, new ClassField("pkg.ExTwo", "v"))).intStruct());
    }

    @Test
    public void calculateArgument50Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $final Object[] arr = (ExTwo<$int>[]){$new ExTwo<>(1),$new ExTwo<>(2)};\n");
        xml_.append(" $public $static Object exmeth (){\n");
        xml_.append("  $return arr;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $private T v;\n");
        xml_.append(" $public ExTwo(T v){\n");
        xml_.append("  $this.v=v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        Struct arr_ = ret_.getStruct();
        assertEq("[pkg.ExTwo<$int>", arr_.getClassName(cont_));
        assertEq(2, ((ArrayStruct)arr_).getInstance().length);
        Struct first_ = ((ArrayStruct) arr_).getInstance()[0];
        Struct second_ = ((ArrayStruct) arr_).getInstance()[1];
        assertEq("pkg.ExTwo<$int>", first_.getClassName(cont_));
        assertEq("pkg.ExTwo<$int>", second_.getClassName(cont_));
        assertEq(1, ((IntStruct) getField(first_, new ClassField("pkg.ExTwo", "v"))).intStruct());
        assertEq(2, ((IntStruct) getField(second_, new ClassField("pkg.ExTwo", "v"))).intStruct());
    }
    @Test
    public void calculateArgument51Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth (){\n");
        xml_.append("  $return ($int)($true?1:2);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1,getNumber(ret_));
    }
    @Test
    public void calculateArgument52__Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $double exmeth (){\n");
        xml_.append("  $return ($double).5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0.5,getDouble(ret_));
    }
    @Test
    public void calculateArgument53__Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$class pkgtwo.ExClass {\n");
        xml_.append(" $private $static String out;\n");
        xml_.append(" $static{\n");
        xml_.append("  String v = `\t`; $final $stack[] st = $stack.current();\n");
        xml_.append("  out = st[0].toString()+st[1].toString();\n");
        xml_.append(" }\n");
        xml_.append(" $private $static String m(){\n");
        xml_.append("  $return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkgtwo.ExClass"));
        Struct out_ = getStaticField(cont_, new ClassField("pkgtwo.ExClass", "out"));
        assertEq("pkg/Ex:3,2:55\n" +
                "pkgtwo.ExClass.pkg/Ex:4,48:110\n" +
                "pkgtwo.ExClass.$static 0()",((StringStruct)out_).getInstance());
    }
    @Test
    public void calculateArgument54__Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$class pkgtwo.ExClass {\n");
        xml_.append(" $private String out;\n");
        xml_.append(" {\n");
        xml_.append("  String v = `\t`; $final $stack[] st = $stack.current();\n");
        xml_.append("  out = st[0].toString()+st[1].toString()+st[2].toString()+st[3].toString();\n");
        xml_.append(" }\n");
        xml_.append(" $private $static String m(){\n");
        xml_.append("  $return $new ExClass().out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkgtwo.ExClass"));
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_;
        ret_ = calculateNormal("pkgtwo.ExClass", id_, args_, cont_);
         assertEq("pkg/Ex:8,11:226\n" +
                 "pkgtwo.ExClass.$static m()pkg/Ex:1,1:0\n" +
                 "pkgtwo.ExClass.pkg/Ex:2,22:45\n" +
                 "pkgtwo.ExClass.pkg/Ex:4,48:95\n" +
                 "pkgtwo.ExClass.0()",getString(ret_));
    }
    @Test
    public void calculateArgument130Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String meth(){\n");
        xml_.append("  $final String a = \"old\";\n");
        xml_.append("  $final String b = \"new\";\n");
        xml_.append("  $final code.util.Replacement inst = $new code.util.Replacement(a,b);\n");
        xml_.append("  $return inst.getOldString();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("meth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("old", getString(ret_));
    }
    @Test
    public void calculateArgument131Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String meth(){\n");
        xml_.append("  $final String a = \"old\";\n");
        xml_.append("  $final String b = \"new\";\n");
        xml_.append("  $final code.util.Replacement inst = $new code.util.Replacement(a,b);\n");
        xml_.append("  $return inst.getNewString();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("meth");
        Argument ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq("new", getString(ret_));
    }
    @Test
    public void calculateArgument132Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$class pkgtwo.ExClassTwo {\n");
        xml_.append(" $private $static $boolean out = ExClass.m();\n");
        xml_.append(" $private $static $boolean m(){\n");
        xml_.append("  $return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$class pkgtwo.ExClass {\n");
        xml_.append(" $static $boolean m(){\n");
        xml_.append("  String v = `\t`; $final $stack[] st = $stack.current();\n");
        xml_.append("  $return st[0]==st[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        ContextEl cont_ = ctxOk(files_,"pkgtwo.ExClassTwo");
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkgtwo.ExClassTwo", id_, args_, cont_);
        assertTrue(ret_.isFalse());
    }
    @Test
    public void calculateArgument133Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$class pkgtwo.ExClass {\n");
        xml_.append(" $static $int m(){\n");
        xml_.append("  $int res = -1;\n");
        xml_.append("  $iter ($char c = Character.MIN_VALUE;Character.MAX_VALUE;;1c){\n");
        xml_.append("   res = c;\n");
        xml_.append("  }\n");
        xml_.append("  $return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("m");
        Argument ret_ = calculateNormal("pkgtwo.ExClass", id_, args_, cont_);
        assertEq(65535,getNumber(ret_));
    }

    @Test
    public void calculateArgument134Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $boolean t;\n");
        xml_.append("  t=$true;\n");
        xml_.append("  t&&&=$true;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isTrue());
    }
    @Test
    public void calculateArgument135Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $boolean t;\n");
        xml_.append("  t=$false;\n");
        xml_.append("  t&&&=1>1/0;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isFalse());
    }
    @Test
    public void calculateArgument136Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $boolean t;\n");
        xml_.append("  t=$false;\n");
        xml_.append("  t|||=$false;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isFalse());
    }
    @Test
    public void calculateArgument137Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $boolean t;\n");
        xml_.append("  t=$true;\n");
        xml_.append("  t|||=1>1/0;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isTrue());
    }
    @Test
    public void calculateArgument138Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $boolean t;\n");
        xml_.append("  t=$true;\n");
        xml_.append("  t&&&=$false;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isFalse());
    }
    @Test
    public void calculateArgument139Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $boolean t;\n");
        xml_.append("  t=$false;\n");
        xml_.append("  t|||=$true;\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isTrue());
    }
    @Test
    public void calculateArgument140Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $boolean t;\n");
        xml_.append("  t=$false;\n");
        xml_.append("  $var res = t&&&=1>1/0;\n");
        xml_.append("  $return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isFalse());
    }
    @Test
    public void calculateArgument141Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static Object exmeth(){\n");
        xml_.append("  $boolean t;\n");
        xml_.append("  t=$true;\n");
        xml_.append("  $var res = t|||=1>1/0;\n");
        xml_.append("  $return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertTrue(ret_.isTrue());
    }

    @Test
    public void calculateArgument3FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $boolean exmeth(){\n");
        xml_.append("  Boolean t;\n");
        xml_.append("  t=$true;\n");
        xml_.append("  $return 1&(((boolean)t) == $true);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument4FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $true?2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument5FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $true?(2;8);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void calculateArgument6FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $true;8;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void calculateArgument8FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $void exmeth($int[] p){\n");
        xml_.append("  $int z=();\n");
        xml_.append("  p={};\n");
        xml_.append("  $($int[]){};\n");
        xml_.append("  a=$($int[]){};\n");
        xml_.append("  $new CharSequence[]{};\n");
        xml_.append("  $new CharSequence(){};\n");
        xml_.append("  $new Ex(){};\n");
        xml_.append("  ($new CharSequence(){});\n");
        xml_.append("  $new $iterable<>(){};\n");
        xml_.append("  $class($int)+{};\n");
        xml_.append("  $throw {};\n");
        xml_.append("  $break {};\n");
        xml_.append("  $continue {};\n");
        xml_.append("  $return {};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void calculateArgument9FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $void exmeth($int[] p){\n");
        xml_.append("  $new CharSequence{}{};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void calculateArgument10FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $void exmeth($int[] p){\n");
        xml_.append("  $final{};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void calculateArgument11FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $void exmeth(){\n");
        xml_.append("  $int t=0;\n");
        xml_.append("  $long u=8;\n");
        xml_.append("  (t,u)=$null;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
}
