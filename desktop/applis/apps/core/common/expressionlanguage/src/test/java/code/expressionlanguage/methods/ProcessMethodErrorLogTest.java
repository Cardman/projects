package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.functionid.MethodId;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessMethodErrorLogTest extends ProcessMethodCommon{

    @Test
    public void err1() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object test(){\n");
        xml_.append("  $return String.valueOf(-1,3,'h',' ','w');\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        String arg_ = calculateErrorMess("pkg.Apply", id_, args_, cont_);
        assertEq("code.expressionlanguage.exceptions.BadIndexException\n" +
                "-1<0\n" +
                "pkg/Ex:3,18:76\n" +
                "pkg.Apply.$static test()",arg_);
    }
    @Test
    public void err2() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object test(){\n");
        xml_.append("  $throw $new Apply();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        String arg_ = calculateErrorMess("pkg.Apply", id_, args_, cont_);
        assertEq("pkg.Apply",arg_);
    }
    @Test
    public void err3() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object test(){\n");
        xml_.append("  $throw $new Apply();\n");
        xml_.append(" }\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"sample\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        String arg_ = calculateErrorMess("pkg.Apply", id_, args_, cont_);
        assertEq("sample",arg_);
    }
    @Test
    public void err4() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object test(){\n");
        xml_.append("  $throw $new Apply();\n");
        xml_.append(" }\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"sample\"+1/0;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        String arg_ = calculateErrorMess("pkg.Apply", id_, args_, cont_);
        assertEq("",arg_);
    }
    @Test
    public void noErr() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Object test(){\n");
        xml_.append("  $return $null;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOkRead(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        String arg_ = calculateErrorMess("pkg.Apply", id_, args_, cont_);
        assertNull(arg_);
    }
}
