package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.functionid.MethodId;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessMethodArrayTest extends ProcessMethodCommon {

    @Test
    public void calculate1Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int[] a;\n");
        xml_.append("  a=$new $int[1i];\n");
        xml_.append("  a[0i]=8i;\n");
        xml_.append("  a[0i]+=2i;\n");
        xml_.append("  $return a[0i];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(10, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculate2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int[] a;\n");
        xml_.append("  a=$new $int[1i];\n");
        xml_.append("  a[0i]=8i;\n");
        xml_.append("  $int bk = a[0i]++;\n");
        xml_.append("  $return a[0i]+bk;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(17, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculate3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int[] a;\n");
        xml_.append("  a=$new $int[1i];\n");
        xml_.append("  a[0i]=8i;\n");
        xml_.append("  $int bk = ++a[0i];\n");
        xml_.append("  $return a[0i]+bk;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(18, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculate4Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String catching(){\n");
        xml_.append("  String[] a;\n");
        xml_.append("  a=$new String[1i];\n");
        xml_.append("  a[0i]=\"first \";\n");
        xml_.append("  a[0i]+=\"second\";\n");
        xml_.append("  $return a[0i];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq("first second",getString(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculate5Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String catching(){\n");
        xml_.append("  Object a;\n");
        xml_.append("  a=($new String{});\n");
        xml_.append("  $return $null;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void calculate6Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int[] a;\n");
        xml_.append("  a=$new $int[1];\n");
        xml_.append("  a[0]=8i;\n");
        xml_.append("  $return a[0s];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(8, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculate7Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int[] a;\n");
        xml_.append("  a=$new $int[128];\n");
        xml_.append("  a['0']=8i;\n");
        xml_.append("  $return a['0'];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("catching");
        assertEq(8, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
}
