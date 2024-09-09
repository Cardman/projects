package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
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
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(10, getNumber(ret_));
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
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(17, getNumber(ret_));
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
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(18, getNumber(ret_));
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
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq("first second", getString(ret_));
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
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(8, getNumber(ret_));
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
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, cont_);
        assertEq(8, getNumber(ret_));
    }
}
