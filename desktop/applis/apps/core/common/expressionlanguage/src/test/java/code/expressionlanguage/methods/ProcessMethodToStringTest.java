package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.NullStruct;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessMethodToStringTest extends ProcessMethodCommon {
    @Test
    public void calculate0Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  Ex e = $new Ex();\n");
        xml_.append("  $return \"\"+e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return $null;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("", getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate1Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  Ex e = $new Ex();\n");
        xml_.append("  $return \"\"+e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst={2,4};\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("2,4",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate2Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  Ex e = $new Ex();\n");
        xml_.append("  $return \"\"+e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("pkg.Ex", getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate3Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  Ex e = $new Ex();\n");
        xml_.append("  $return e+\"\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst={2,4};\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("2,4",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate4Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  Ex e = $new Ex();\n");
        xml_.append("  String o = \"\";\n");
        xml_.append("  o += e;\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst={2,4};\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("2,4",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate5Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator++ pkg.Ex(pkg.Ex p) {\n");
        xml_.append("  pkg.Ex e = $new pkg.Ex();\n");
        xml_.append("  e[0] = p[0];\n");
        xml_.append("  e[0]++;\n");
        xml_.append("  e[1] = p[1];\n");
        xml_.append("  e[1]++;\n");
        xml_.append("  $return e;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  ExCont c = $new ExCont();\n");
        xml_.append("  Ex e = $new Ex();\n");
        xml_.append("  e[0] = 5;\n");
        xml_.append("  c[0] = e;\n");
        xml_.append("  String res = \"\"+c[0]++;\n");
        xml_.append("  $return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public Ex[] inst=$new Ex[]{$new Ex()};\n");
        xml_.append(" $public Ex $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = $value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst=$new $int[2];\n");
        xml_.append(" $public $int $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = $value;\n");
        xml_.append(" }\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("5,0",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate6Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator++ pkg.Ex(pkg.Ex p) {\n");
        xml_.append("  pkg.Ex e = $new pkg.Ex();\n");
        xml_.append("  e[0] = p[0];\n");
        xml_.append("  e[0]++;\n");
        xml_.append("  e[1] = p[1];\n");
        xml_.append("  e[1]++;\n");
        xml_.append("  $return e;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  ExCont c = $new ExCont();\n");
        xml_.append("  Ex e = $new Ex();\n");
        xml_.append("  e[0] = 5;\n");
        xml_.append("  c[0] = e;\n");
        xml_.append("  String res = \"\"+ ++c[0];\n");
        xml_.append("  $return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public Ex[] inst=$new Ex[]{$new Ex()};\n");
        xml_.append(" $public Ex $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = $value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst=$new $int[2];\n");
        xml_.append(" $public $int $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = $value;\n");
        xml_.append(" }\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("6,1",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate7Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  $return \"\"+$new Ex();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst={2,4};\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("2,4",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate8Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  String o = \"\";\n");
        xml_.append("  o += $new Ex();\n");
        xml_.append("  $return o;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst={2,4};\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("2,4",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate9Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Ex(pkg.Ex p,pkg.Ex q) {\n");
        xml_.append("  pkg.Ex e = $new pkg.Ex();\n");
        xml_.append("  e[0] = p[0];\n");
        xml_.append("  e[0]+=q[0];\n");
        xml_.append("  e[1] = p[1];\n");
        xml_.append("  e[1]+=q[1];\n");
        xml_.append("  $return e;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  ExCont c = $new ExCont();\n");
        xml_.append("  Ex e = $new Ex();\n");
        xml_.append("  Ex f = $new Ex();\n");
        xml_.append("  e[0] = 5;\n");
        xml_.append("  e[1] = 1;\n");
        xml_.append("  f[0] = 15;\n");
        xml_.append("  f[1] = 2;\n");
        xml_.append("  c[0] = e;\n");
        xml_.append("  String res = \"\";\n");
        xml_.append("  res += c[0]+=f;\n");
        xml_.append("  $return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public Ex[] inst=$new Ex[]{$new Ex()};\n");
        xml_.append(" $public Ex $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = $value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst=$new $int[2];\n");
        xml_.append(" $public $int $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = $value;\n");
        xml_.append(" }\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("20,3",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate10Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Ex(pkg.Ex p,pkg.Ex q) {\n");
        xml_.append("  pkg.Ex e = $new pkg.Ex();\n");
        xml_.append("  e[0] = p[0];\n");
        xml_.append("  e[0]+=q[0];\n");
        xml_.append("  e[1] = p[1];\n");
        xml_.append("  e[1]+=q[1];\n");
        xml_.append("  $return e;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  ExCont c = $new ExCont();\n");
        xml_.append("  Ex e = $new Ex();\n");
        xml_.append("  Ex f = $new Ex();\n");
        xml_.append("  e[0] = 5;\n");
        xml_.append("  e[1] = 1;\n");
        xml_.append("  f[0] = 15;\n");
        xml_.append("  f[1] = 2;\n");
        xml_.append("  c[0] = e;\n");
        xml_.append("  String res = \"\";\n");
        xml_.append("  res += e;\n");
        xml_.append("  $return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public Ex[] inst=$new Ex[]{$new Ex()};\n");
        xml_.append(" $public Ex $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = $value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst=$new $int[2];\n");
        xml_.append(" $public $int $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = $value;\n");
        xml_.append(" }\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("5,1",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate11Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator++ pkg.Ex(pkg.Ex p) {\n");
        xml_.append("  pkg.Ex e = $new pkg.Ex();\n");
        xml_.append("  e[0] = p[0];\n");
        xml_.append("  e[0]++;\n");
        xml_.append("  e[1] = p[1];\n");
        xml_.append("  e[1]++;\n");
        xml_.append("  $return e;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  Ex e = $new Ex();\n");
        xml_.append("  e[0] = 5;\n");
        xml_.append("  Ex[] c = {e};\n");
        xml_.append("  String res = \"\"+c[0]++;\n");
        xml_.append("  $return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public Ex[] inst=$new Ex[]{$new Ex()};\n");
        xml_.append(" $public Ex $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = $value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst=$new $int[2];\n");
        xml_.append(" $public $int $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = $value;\n");
        xml_.append(" }\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("5,0",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate12Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator++ pkg.Ex(pkg.Ex p) {\n");
        xml_.append("  pkg.Ex e = $new pkg.Ex();\n");
        xml_.append("  e[0] = p[0];\n");
        xml_.append("  e[0]++;\n");
        xml_.append("  e[1] = p[1];\n");
        xml_.append("  e[1]++;\n");
        xml_.append("  $return e;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  Ex e = $new Ex();\n");
        xml_.append("  e[0] = 5;\n");
        xml_.append("  Ex[] c = {e};\n");
        xml_.append("  String res = \"\"+ ++c[0];\n");
        xml_.append("  $return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public Ex[] inst=$new Ex[]{$new Ex()};\n");
        xml_.append(" $public Ex $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = $value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst=$new $int[2];\n");
        xml_.append(" $public $int $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = $value;\n");
        xml_.append(" }\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("6,1",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate13Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Ex(pkg.Ex p,pkg.Ex q) {\n");
        xml_.append("  pkg.Ex e = $new pkg.Ex();\n");
        xml_.append("  e[0] = p[0];\n");
        xml_.append("  e[0]+=q[0];\n");
        xml_.append("  e[1] = p[1];\n");
        xml_.append("  e[1]+=q[1];\n");
        xml_.append("  $return e;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  Ex e = $new Ex();\n");
        xml_.append("  e[0] = 5;\n");
        xml_.append("  e[1] = 3;\n");
        xml_.append("  Ex f = $new Ex();\n");
        xml_.append("  f[0] = 15;\n");
        xml_.append("  f[1] = 1;\n");
        xml_.append("  Ex[] c = {e};\n");
        xml_.append("  String res = \"\";\n");
        xml_.append("  res += c[0] += f;\n");
        xml_.append("  $return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public Ex[] inst=$new Ex[]{$new Ex()};\n");
        xml_.append(" $public Ex $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = $value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst=$new $int[2];\n");
        xml_.append(" $public $int $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = $value;\n");
        xml_.append(" }\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("20,4",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate14Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Ex(pkg.Ex p,pkg.Ex q) {\n");
        xml_.append("  pkg.Ex e = $new pkg.Ex();\n");
        xml_.append("  e[0] = p[0];\n");
        xml_.append("  e[0]+=q[0];\n");
        xml_.append("  e[1] = p[1];\n");
        xml_.append("  e[1]+=q[1];\n");
        xml_.append("  $return e;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  Ex e = $new Ex();\n");
        xml_.append("  e[0] = 5;\n");
        xml_.append("  e[1] = 3;\n");
        xml_.append("  Ex f = $new Ex();\n");
        xml_.append("  f[0] = 15;\n");
        xml_.append("  f[1] = 1;\n");
        xml_.append("  Ex[] c = {e};\n");
        xml_.append("  String res = \"\";\n");
        xml_.append("  res += c[0] = f;\n");
        xml_.append("  $return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public Ex[] inst=$new Ex[]{$new Ex()};\n");
        xml_.append(" $public Ex $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = $value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst=$new $int[2];\n");
        xml_.append(" $public $int $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = $value;\n");
        xml_.append(" }\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("15,1",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate15Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  Ex e = $new ExTwo();\n");
        xml_.append("  $return \"\"+e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst={2,4};\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo:Ex {\n");
        xml_.append(" $public $int[] instTwo={6,8};\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return $super.$toString()+\",\"+instTwo[0]+\",\"+instTwo[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("2,4,6,8",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate16Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  Object e = $new ExTwo();\n");
        xml_.append("  $return \"\"+e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst={2,4};\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo:Ex {\n");
        xml_.append(" $public $int[] instTwo={6,8};\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return $super.$toString()+\",\"+instTwo[0]+\",\"+instTwo[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("2,4,6,8",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate17Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  Object e = $new ExTwo();\n");
        xml_.append("  $return \"\"+e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $abstract $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst={2,4};\n");
        xml_.append(" $public $abstract String $toString();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo:Ex {\n");
        xml_.append(" $public $int[] instTwo={6,8};\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1]+\",\"+instTwo[0]+\",\"+instTwo[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("2,4,6,8",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate18Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  Ex e = $new ExTwo();\n");
        xml_.append("  $return \"\"+e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $abstract $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst={2,4};\n");
        xml_.append(" $public $abstract String $toString();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo:Ex {\n");
        xml_.append(" $public $int[] instTwo={6,8};\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1]+\",\"+instTwo[0]+\",\"+instTwo[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("2,4,6,8",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate19Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  ExInt e = $new ExTwo();\n");
        xml_.append("  $return \"\"+e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $abstract $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst={2,4};\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExInt {\n");
        xml_.append(" String $toString();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo:Ex:ExInt {\n");
        xml_.append(" $public $int[] instTwo={6,8};\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1]+\",\"+instTwo[0]+\",\"+instTwo[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("2,4,6,8",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate20Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  ExInt e = $new ExTwo();\n");
        xml_.append("  $return \"\"+e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $abstract $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst={2,4};\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExInt {\n");
        xml_.append(" String $toString();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo:Ex:ExInt {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("2,4",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate21Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  ExInt e = $new ExTwo();\n");
        xml_.append("  $return \"\"+e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Ex {\n");
        xml_.append(" $public $int[] inst={2,4};\n");
        xml_.append(" $public $final String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExInt {\n");
        xml_.append(" $public $normal String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo:Ex:ExInt {\n");
        xml_.append(" $public ExTwo() {\n");
        xml_.append("  $interfaces(Ex)();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("2,4",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate22Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  ExInt e = $new ExTwo();\n");
        xml_.append("  $return \"\"+e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $abstract $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst={2,4};\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExInt {\n");
        xml_.append(" $public $normal String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo:Ex:ExInt {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("2,4",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate23Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  $return ExTwo.$toString();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo{\n");
        xml_.append(" $public $static String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"static\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("static", getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate24Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  $var toSpecString = \"\";\n");
        xml_.append("  $return toSpecString+ExTwo.toSpecString();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo{\n");
        xml_.append(" $public $static String toSpecString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"static\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElToString(files_);
        MethodId id_ = getMethodId("test");
        assertEq("static", getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate24_Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  $var toSpecString = \"\";\n");
        xml_.append("  $return toSpecString+$staticCall(ExTwo).toSpecString();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo{\n");
        xml_.append(" $public $staticCall String toSpecString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"static call\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElToString(files_);
        MethodId id_ = getMethodId("test");
        assertEq("static call", getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate25Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  $boolean one = $false;\n");
        xml_.append("  $boolean two = $true;\n");
        xml_.append("  $return \"\"+(one&&two);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo{\n");
        xml_.append(" $public $static String toSpecString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"static\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElToString(files_);
        MethodId id_ = getMethodId("test");
        assertEq("false", getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate26Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  $boolean one = $true;\n");
        xml_.append("  $boolean two = $false;\n");
        xml_.append("  $return \"\"+(one||two);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo{\n");
        xml_.append(" $public $static String toSpecString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"static\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElToString(files_);
        MethodId id_ = getMethodId("test");
        assertEq("true", getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate27Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator++ pkg.Ex(pkg.Ex p) {\n");
        xml_.append("  pkg.Ex e = $new pkg.Ex();\n");
        xml_.append("  e[0] = p[0];\n");
        xml_.append("  e[0]++;\n");
        xml_.append("  e[1] = p[1];\n");
        xml_.append("  e[1]++;\n");
        xml_.append("  $return e;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  ExCont c = $new ExCont();\n");
        xml_.append("  Ex e = $new Ex();\n");
        xml_.append("  e[0] = 5;\n");
        xml_.append("  String res = \"\";\n");
        xml_.append("  res += c[0] = e;\n");
        xml_.append("  $return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public Ex[] inst=$new Ex[]{$new Ex()};\n");
        xml_.append(" $public Ex $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = $value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst=$new $int[2];\n");
        xml_.append(" $public $int $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = $value;\n");
        xml_.append(" }\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("5,0",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate28Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Ex(pkg.Ex p,pkg.Ex q) {\n");
        xml_.append("  pkg.Ex e = $new pkg.Ex();\n");
        xml_.append("  e[0] = p[0];\n");
        xml_.append("  e[0]+=q[0];\n");
        xml_.append("  e[1] = p[1];\n");
        xml_.append("  e[1]+=q[1];\n");
        xml_.append("  $return e;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  Ex e = $new Ex();\n");
        xml_.append("  e[0] = 5;\n");
        xml_.append("  e[1] = 3;\n");
        xml_.append("  Ex f = $new Ex();\n");
        xml_.append("  f[0] = 15;\n");
        xml_.append("  f[1] = 1;\n");
        xml_.append("  Ex c = $null;\n");
        xml_.append("  String res = \"\";\n");
        xml_.append("  res += c = f;\n");
        xml_.append("  $return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public Ex[] inst=$new Ex[]{$new Ex()};\n");
        xml_.append(" $public Ex $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = $value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst=$new $int[2];\n");
        xml_.append(" $public $int $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = $value;\n");
        xml_.append(" }\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("15,1",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate29Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Ex(pkg.Ex p,pkg.Ex q) {\n");
        xml_.append("  pkg.Ex e = $new pkg.Ex();\n");
        xml_.append("  e[0] = p[0];\n");
        xml_.append("  e[0]+=q[0];\n");
        xml_.append("  e[1] = p[1];\n");
        xml_.append("  e[1]+=q[1];\n");
        xml_.append("  $return e;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $static Ex c;\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  Ex e = $new Ex();\n");
        xml_.append("  e[0] = 5;\n");
        xml_.append("  e[1] = 3;\n");
        xml_.append("  Ex f = $new Ex();\n");
        xml_.append("  f[0] = 15;\n");
        xml_.append("  f[1] = 1;\n");
        xml_.append("  String res = \"\";\n");
        xml_.append("  res += c = f;\n");
        xml_.append("  $return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public Ex[] inst=$new Ex[]{$new Ex()};\n");
        xml_.append(" $public Ex $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = $value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst=$new $int[2];\n");
        xml_.append(" $public $int $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = $value;\n");
        xml_.append(" }\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("15,1",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate31Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $final String field = \"\"+$new code.util.Replacement(\"\",\"\");\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("code.util.Replacement", getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate32Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $final String field = $new code.util.Replacement(\"\",\"\")+\"\";\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("code.util.Replacement", getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate33Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  String o = \"\";\n");
        xml_.append("  String p = \"\";\n");
        xml_.append("  p += o += $new Ex();\n");
        xml_.append("  $return p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst={2,4};\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("2,4",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate34Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Ex(pkg.Ex p,pkg.Ex q) {\n");
        xml_.append("  pkg.Ex e = $new pkg.Ex();\n");
        xml_.append("  e[0] = p[0];\n");
        xml_.append("  e[0]+=q[0];\n");
        xml_.append("  e[1] = p[1];\n");
        xml_.append("  e[1]+=q[1];\n");
        xml_.append("  $return e;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  Ex c = $null;\n");
        xml_.append("  Ex e = $new Ex();\n");
        xml_.append("  Ex f = $new Ex();\n");
        xml_.append("  e[0] = 5;\n");
        xml_.append("  e[1] = 1;\n");
        xml_.append("  f[0] = 15;\n");
        xml_.append("  f[1] = 2;\n");
        xml_.append("  c = e;\n");
        xml_.append("  String res = \"\";\n");
        xml_.append("  res += c+=f;\n");
        xml_.append("  $return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public Ex[] inst=$new Ex[]{$new Ex()};\n");
        xml_.append(" $public Ex $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = $value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst=$new $int[2];\n");
        xml_.append(" $public $int $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = $value;\n");
        xml_.append(" }\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("20,3",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate35Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ pkg.Ex(pkg.Ex p,pkg.Ex q) {\n");
        xml_.append("  pkg.Ex e = $new pkg.Ex();\n");
        xml_.append("  e[0] = p[0];\n");
        xml_.append("  e[0]+=q[0];\n");
        xml_.append("  e[1] = p[1];\n");
        xml_.append("  e[1]+=q[1];\n");
        xml_.append("  $return e;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Ex inst = $new Ex();\n");
        xml_.append(" $static{\n");
        xml_.append("  inst[0] = 5;\n");
        xml_.append("  inst[1] = 1;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  Ex f = $new Ex();\n");
        xml_.append("  f[0] = 15;\n");
        xml_.append("  f[1] = 2;\n");
        xml_.append("  String res = \"\";\n");
        xml_.append("  res += inst+=f;\n");
        xml_.append("  $return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public Ex[] inst=$new Ex[]{$new Ex()};\n");
        xml_.append(" $public Ex $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = $value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst=$new $int[2];\n");
        xml_.append(" $public $int $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = $value;\n");
        xml_.append(" }\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("20,3",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate38Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String str = \"\";\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  String p = \"\";\n");
        xml_.append("  p += str+=$new Ex();\n");
        xml_.append("  $return p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst={2,4};\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("2,4",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate39Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator++ pkg.Ex(pkg.Ex p) {\n");
        xml_.append("  pkg.Ex e = $new pkg.Ex();\n");
        xml_.append("  e[0] = p[0];\n");
        xml_.append("  e[0]++;\n");
        xml_.append("  e[1] = p[1];\n");
        xml_.append("  e[1]++;\n");
        xml_.append("  $return e;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  Ex e = $new Ex();\n");
        xml_.append("  e[0] = 5;\n");
        xml_.append("  String res = \"\"+e++;\n");
        xml_.append("  $return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public Ex[] inst=$new Ex[]{$new Ex()};\n");
        xml_.append(" $public Ex $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = $value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst=$new $int[2];\n");
        xml_.append(" $public $int $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = $value;\n");
        xml_.append(" }\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("5,0",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate40Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator++ pkg.Ex(pkg.Ex p) {\n");
        xml_.append("  pkg.Ex e = $new pkg.Ex();\n");
        xml_.append("  e[0] = p[0];\n");
        xml_.append("  e[0]++;\n");
        xml_.append("  e[1] = p[1];\n");
        xml_.append("  e[1]++;\n");
        xml_.append("  $return e;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  Ex e = $new Ex();\n");
        xml_.append("  e[0] = 5;\n");
        xml_.append("  String res = \"\"+ ++e;\n");
        xml_.append("  $return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public Ex[] inst=$new Ex[]{$new Ex()};\n");
        xml_.append(" $public Ex $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = $value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst=$new $int[2];\n");
        xml_.append(" $public $int $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = $value;\n");
        xml_.append(" }\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("6,1",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
   
    @Test
    public void calculate43Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator++ pkg.Ex(pkg.Ex p) {\n");
        xml_.append("  pkg.Ex e = $new pkg.Ex();\n");
        xml_.append("  e[0] = p[0];\n");
        xml_.append("  e[0]++;\n");
        xml_.append("  e[1] = p[1];\n");
        xml_.append("  e[1]++;\n");
        xml_.append("  $return e;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Ex inst = $new Ex();\n");
        xml_.append(" $static{\n");
        xml_.append("  inst[0] = 5;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  String res = \"\"+inst++;\n");
        xml_.append("  $return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public Ex[] inst=$new Ex[]{$new Ex()};\n");
        xml_.append(" $public Ex $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = $value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst=$new $int[2];\n");
        xml_.append(" $public $int $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = $value;\n");
        xml_.append(" }\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("5,0",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate44Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator++ pkg.Ex(pkg.Ex p) {\n");
        xml_.append("  pkg.Ex e = $new pkg.Ex();\n");
        xml_.append("  e[0] = p[0];\n");
        xml_.append("  e[0]++;\n");
        xml_.append("  e[1] = p[1];\n");
        xml_.append("  e[1]++;\n");
        xml_.append("  $return e;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Ex inst = $new Ex();\n");
        xml_.append(" $static{\n");
        xml_.append("  inst[0] = 5;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  String res = \"\"+ ++inst;\n");
        xml_.append("  $return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public Ex[] inst=$new Ex[]{$new Ex()};\n");
        xml_.append(" $public Ex $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = $value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst=$new $int[2];\n");
        xml_.append(" $public $int $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = $value;\n");
        xml_.append(" }\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("6,1",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate45Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator-- pkg.Ex(pkg.Ex p) {\n");
        xml_.append("  pkg.Ex e = $new pkg.Ex();\n");
        xml_.append("  e[0] = p[0];\n");
        xml_.append("  e[0]--;\n");
        xml_.append("  e[1] = p[1];\n");
        xml_.append("  e[1]--;\n");
        xml_.append("  $return e;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Ex inst = $new Ex();\n");
        xml_.append(" $static{\n");
        xml_.append("  inst[0] = 5;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  String res = \"\"+--inst;\n");
        xml_.append("  $return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public Ex[] inst=$new Ex[]{$new Ex()};\n");
        xml_.append(" $public Ex $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = $value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst=$new $int[2];\n");
        xml_.append(" $public $int $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = $value;\n");
        xml_.append(" }\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("4,-1",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate46Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  $return \"\"+$new $int[]{};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst={2,4};\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("[$int", getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate47Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  $return $StringUtil.valueOf(\"string\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("string", getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate48Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  $return $StringUtil.valueOf($new Ex());\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst={2,4};\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("2,4",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate49Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  $return $new ExTwo(\"static\").toSpecString(4);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo{\n");
        xml_.append(" $public String instance;\n");
        xml_.append(" $public ExTwo(String p){\n");
        xml_.append("  instance = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public String toSpecString($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return instance+\":\"+p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElToString(files_);
        MethodId id_ = getMethodId("test");
        assertEq("static:4", getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate50Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  $return \"\"+$new ExTwo(\"static\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo{\n");
        xml_.append(" $public String instance;\n");
        xml_.append(" $public ExTwo(String p){\n");
        xml_.append("  instance = p;\n");
        xml_.append(" }\n");
        xml_.append(" $public String toSpecString()\n");
        xml_.append(" {\n");
        xml_.append("  $return instance+\":\";\n");
        xml_.append(" }\n");
        xml_.append(" $public String toSpecString($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return instance+\":\"+p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElToString(files_);
        MethodId id_ = getMethodId("test");
        assertEq("static:", getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate52Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.ExEnum {\n");
        xml_.append("ONE\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  $return \"\"+ExEnum.ONE;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("ONE", getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate53Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.ExEnum {\n");
        xml_.append("ONE;\n");
        xml_.append(" $public $int[] inst={2,4};\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  $return \"\"+ExEnum.ONE;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("2,4",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate54Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.ExEnum {\n");
        xml_.append("ONE{}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  $return \"\"+ExEnum.ONE;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("ONE", getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate55Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.ExEnum {\n");
        xml_.append("ONE{};\n");
        xml_.append(" $public $int[] inst={2,4};\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  $return \"\"+ExEnum.ONE;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("2,4",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate56Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $final Integer nulInt = $null;\n");
        xml_.append(" $public $static $final $int field = nulInt+0;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        assertSame(NullStruct.NULL_VALUE,getStaticField(cont_,new ClassField("pkg.Apply","nulInt")));
    }
    @Test
    public void calculate57Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $final Integer nulInt = $null;\n");
        xml_.append(" $public $static $final $int field = 0+nulInt;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        assertSame(NullStruct.NULL_VALUE,getStaticField(cont_,new ClassField("pkg.Apply","nulInt")));
    }
    @Test
    public void calculate58Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  ExInt e = $new ExTwo();\n");
        xml_.append("  $return \"\"+e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $abstract $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst={2,4};\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.ExInt {\n");
        xml_.append(" $public $normal String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"2,4\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo:Ex:ExInt {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("2,4",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate59Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  Ex e = $new Ex();\n");
        xml_.append("  $return \"\"+e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst={2,4};\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append(" $public $static String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"static\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("2,4",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate60Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  Ex e = $new ExTwo();\n");
        xml_.append("  $return \"\"+e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Ex {\n");
        xml_.append(" $public $int[] inst={2,4};\n");
        xml_.append(" $public $normal String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo:Ex {\n");
        xml_.append(" $public $int[] instTwo={6,8};\n");
        xml_.append(" $public ExTwo(){\n");
        xml_.append("  $interfaces(Ex)();\n");
        xml_.append(" }\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return $super.$toString()+\",\"+instTwo[0]+\",\"+instTwo[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("2,4,6,8",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate61Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  Ex<$int> e = $new ExTwo<>();\n");
        xml_.append("  $return \"\"+e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Ex<T> {\n");
        xml_.append(" $public $int[] inst={2,4};\n");
        xml_.append(" $public $normal String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<S>:Ex<S> {\n");
        xml_.append(" $public $int[] instTwo={6,8};\n");
        xml_.append(" $public ExTwo(){\n");
        xml_.append("  $interfaces(Ex)();\n");
        xml_.append(" }\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return $super.$toString()+\",\"+instTwo[0]+\",\"+instTwo[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("2,4,6,8",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate62Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  Ex<$int> e = $new ExTwo<>();\n");
        xml_.append("  $return \"\"+e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Ex<T> {\n");
        xml_.append(" $public $int[] inst={2,4};\n");
        xml_.append(" $public $normal String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<S>:Ex<S> {\n");
        xml_.append(" $public ExTwo(){\n");
        xml_.append("  $interfaces(Ex)();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("2,4",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate62_Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  Ex<$int> e = $new ExTwo<>();\n");
        xml_.append("  $return \"\"+e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Ex<T> {\n");
        xml_.append(" $public $int[] inst={2,4};\n");
        xml_.append(" $public $normal String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Mid<U>:Ex<U> {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<S>:Mid<S> {\n");
        xml_.append(" $public ExTwo(){\n");
        xml_.append("  $interfaces(Ex)();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("2,4",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate62__Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  Ex<$int> e = $new ExTwo<>();\n");
        xml_.append("  $return \"\"+e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Ex<T> {\n");
        xml_.append(" $public $int[] inst={2,4};\n");
        xml_.append(" $public $normal String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  T[] arr = $new T[2];\n");
        xml_.append("  Object[] arro = arr;\n");
        xml_.append("  arro[0]=0;\n");
        xml_.append("  arro[1]=1;\n");
        xml_.append("  $return \"\"+inst[($int)arro[0]]+\",\"+inst[($int)arro[1]];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<S>:Ex<S> {\n");
        xml_.append(" $public ExTwo(){\n");
        xml_.append("  $interfaces(Ex)();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("2,4",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate63Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  Ex<$int> e = $new ExTwo<>();\n");
        xml_.append("  $return \"\"+e;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $int[] inst={2,4};\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"\"+inst[0]+\",\"+inst[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<S>:Ex<S> {\n");
        xml_.append(" $public $int[] instTwo={6,8};\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return $super.$toString()+\",\"+instTwo[0]+\",\"+instTwo[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("2,4,6,8",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate64Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.Annot {}\n");
        xml_.append("@Annot\n");
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  $return \"\"+$class(Apply).getAnnotations()[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("@pkg.Annot()", getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate65Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.Annot {}\n");
        xml_.append("@Annot\n");
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  $return \"\"+$class(Apply);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("pkg.Apply", getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate66Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.Annot {}\n");
        xml_.append("@Annot\n");
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  $return \"\"+$class(Apply[]);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("[pkg.Apply", getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate67Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.Annot {}\n");
        xml_.append("@Annot\n");
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  $return \"\"+$class(Apply).getDeclaredConstructors()[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("pkg.Apply;pkg.Apply()", getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate68Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.Annot {}\n");
        xml_.append("@Annot\n");
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  $return \"\"+$class(Apply).getDeclaredMethods()[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("pkg.Apply.$static test()", getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate69Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.Annot {}\n");
        xml_.append("@Annot\n");
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test;\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  $return \"\"+$class(Apply).getDeclaredFields()[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("pkg.Apply.test", getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate70Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.Annot {}\n");
        xml_.append("@Annot\n");
        xml_.append("$public $class pkg.Apply<T> {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  $return \"\"+$class(Apply<String>).getTypeParameters()[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("pkg.Apply<java.lang.String>;#T", getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate71Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $final String field = \"\"+c();\n");
        xml_.append(" $public $static code.util.Replacement c(){\n");
        xml_.append("  $return $new code.util.Replacement(\"\",\"\");\n");
        xml_.append(" }\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("code.util.Replacement", getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate72Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $final String field = java.lang.$StringUtil.valueOf(c());\n");
        xml_.append(" $public $static code.util.Replacement c(){\n");
        xml_.append("  $return $new code.util.Replacement(\"\",\"\");\n");
        xml_.append(" }\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  $return field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("code.util.Replacement", getString(calculateNormal("pkg.Apply", id_, cont_)));
    }

}
