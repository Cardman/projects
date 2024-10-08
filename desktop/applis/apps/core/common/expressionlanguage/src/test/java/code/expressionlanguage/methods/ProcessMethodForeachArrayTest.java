package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.functionid.MethodId;
import code.util.StringMap;
import org.junit.Test;


public final class ProcessMethodForeachArrayTest extends ProcessMethodCommon {

    @Test
    public void calculateArgument42Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $int[] a;\n");
        xml_.append("  a=$new $int[2i];\n");
        xml_.append("  a[0i]=8i;\n");
        xml_.append("  a[1i]=16i;\n");
        xml_.append("  $foreach($int i:a){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(24, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument43Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $int[] a;\n");
        xml_.append("  a=$new $int[0i];\n");
        xml_.append("  $foreach($int i:a){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(0, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument70Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $int[] a;\n");
        xml_.append("  a=$new $int[2i];\n");
        xml_.append("  a[0i]=8y;\n");
        xml_.append("  a[1i]=16y;\n");
        xml_.append("  $foreach($int i:a){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(24, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument71Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  java.lang.Integer[] a;\n");
        xml_.append("  a=$new java.lang.Integer[4i];\n");
        xml_.append("  a[0i]=$null;\n");
        xml_.append("  a[1i]=1i;\n");
        xml_.append("  a[2i]=$null;\n");
        xml_.append("  a[3i]=$null;\n");
        xml_.append("  $foreach(java.lang.Integer i:a){\n");
        xml_.append("   $if(i==$null){\n");
        xml_.append("    t+=([i]);\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(5, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument72Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $int[] a;\n");
        xml_.append("  a=$new $int[2i];\n");
        xml_.append("  a[0i]=8i;\n");
        xml_.append("  a[0i]+=6i;\n");
        xml_.append("  a[1i]=16i;\n");
        xml_.append("  $foreach($int i:a){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(30, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument73Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  java.lang.Integer[] a;\n");
        xml_.append("  a=$new java.lang.Integer[4i];\n");
        xml_.append("  a[0i]=$null;\n");
        xml_.append("  a[1i]=1i;\n");
        xml_.append("  a[2i]=$null;\n");
        xml_.append("  a[3i]=$null;\n");
        xml_.append("  $foreach(java.lang.Integer i:a){\n");
        xml_.append("   $if(i==$null){\n");
        xml_.append("    t+=([i]);\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(5, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument74Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $final Integer valueField = $null;\n");
        xml_.append(" $public $static $final $int[] aField = $new $int[]{valueField};\n");
        xml_.append(" $static{\n");
        xml_.append("  Integer value = $null;\n");
        xml_.append("  $int[] a;\n");
        xml_.append("  a=$new $int[]{value};\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $void catching(){\n");
        xml_.append("  Integer value = $null;\n");
        xml_.append("  $int[] a;\n");
        xml_.append("  a=$new $int[]{value};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq("code.util.exceptions.NullObjectException", calculateError("pkg.Ex",id_,cont_).getClassName(cont_));
    }
}
