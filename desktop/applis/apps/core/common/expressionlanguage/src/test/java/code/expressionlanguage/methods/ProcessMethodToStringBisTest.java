package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.functionid.MethodId;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessMethodToStringBisTest extends ProcessMethodCommon {
    @Test
    public void calculate30Test() {
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
        xml_.append("  $for (Ex d = $null;;){\n");
        xml_.append("   res += d = f;\n");
        xml_.append("   $break;\n");
        xml_.append("  }\n");
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
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("15,1",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate36Test() {
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
        xml_.append("  $for (Ex d = e;;){\n");
        xml_.append("   res += d+=f;\n");
        xml_.append("   $break;\n");
        xml_.append("  }\n");
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
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("20,3",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate37Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static String test(){\n");
        xml_.append("  String p = \"\";\n");
        xml_.append("  $for (String o = \"\";;){\n");
        xml_.append("   p += o+=$new Ex();\n");
        xml_.append("   $break;\n");
        xml_.append("  }\n");
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
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("2,4",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate41Test() {
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
        xml_.append("  String p = \"\";\n");
        xml_.append("  $for(Ex f=e;;){\n");
        xml_.append("   p += \"\"+f++;\n");
        xml_.append("   $break;\n");
        xml_.append("  }\n");
        xml_.append("  $return p;\n");
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
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("5,0",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculate42Test() {
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
        xml_.append("  String p = \"\";\n");
        xml_.append("  $for(Ex f=e;;){\n");
        xml_.append("   p += \"\"+ ++f;\n");
        xml_.append("   $break;\n");
        xml_.append("  }\n");
        xml_.append("  $return p;\n");
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
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("6,1",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }

    @Test
    public void calculate51Test() {
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
        xml_.append("  $return (c[0]+=e)+';'+(c[1]+=f);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public String[] inst={\"str1:\",\"str2:\"};\n");
        xml_.append(" $public String $this($int p)\n");
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
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("test");
        assertEq("str1:5,1;str2:15,2",getString(calculateNormal("pkg.Apply", id_, cont_)));
    }
}
