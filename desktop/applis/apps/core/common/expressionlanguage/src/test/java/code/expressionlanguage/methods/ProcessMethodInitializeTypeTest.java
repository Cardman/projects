package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.InitClassState;
import code.expressionlanguage.structs.*;
import code.util.StringMap;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;

public final class ProcessMethodInitializeTypeTest extends ProcessMethodCommon {
    @Test
    public void calculate0Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree e = $null;\n");
        xml_.append("  e.$superaccess(ExThree)inst().append(\"word\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExInit {\n");
        xml_.append(" $static{\n");
        xml_.append("  $classchoice(ExTwo)inst().append(\"word\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final StringBuilder inst=$new StringBuilder();\n");
        xml_.append(" $public $static StringBuilder inst(){\n");
        xml_.append("  $return inst;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $final StringBuilder inst=$new StringBuilder();\n");
        xml_.append(" $public $static StringBuilder inst(){\n");
        xml_.append("  $return inst;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(!isInitialized(cont_, "pkg.Ex"));
        assertTrue(!isInitialized(cont_, "pkg.ExInit"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
        assertTrue(isInitialized(cont_, "pkg.ExThree"));
    }

    private static boolean isInitialized(ContextEl cont_, String _cl) {
        return cont_.getLocks().getState(_cl) != InitClassState.NOT_YET;
    }

    private static boolean isSuccessfulInitialized(ContextEl cont_, String _cl) {
        return cont_.getLocks().getState(_cl) == InitClassState.SUCCESS;
    }

    @Test
    public void calculate1Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst.append(\"word\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final StringBuilder inst=$new StringBuilder();\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(!isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
    }
    @Test
    public void calculate2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst.append(0);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final StringBuilder inst=$new StringBuilder();\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(!isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
    }
    @Test
    public void calculate3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst.append($new $char[]{'0'});\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final StringBuilder inst=$new StringBuilder();\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(!isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
    }
    @Test
    public void calculate4Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst.append(\"word\",0,1);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final StringBuilder inst=$new StringBuilder();\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(!isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
    }
    @Test
    public void calculate5Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst.append($new $char[]{'0'},0,1);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final StringBuilder inst=$new StringBuilder();\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(!isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
    }
    @Test
    public void calculate6Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst.delete(0,1);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final StringBuilder inst=$new StringBuilder(\"word\");\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(!isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
    }
    @Test
    public void calculate7Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst.deleteCharAt(0);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final StringBuilder inst=$new StringBuilder(\"word\");\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(!isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
    }
    @Test
    public void calculate8Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst.clear();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final StringBuilder inst=$new StringBuilder(\"word\");\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(!isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
    }
    @Test
    public void calculate9Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst.ensureCapacity(1);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final StringBuilder inst=$new StringBuilder(\"word\");\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(!isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
    }
    @Test
    public void calculate10Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst.reverse();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final StringBuilder inst=$new StringBuilder(\"word\");\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(!isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
    }
    @Test
    public void calculate11Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst.setLength(1);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final StringBuilder inst=$new StringBuilder(\"word\");\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(!isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
    }
    @Test
    public void calculate12Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst.trimToSize();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final StringBuilder inst=$new StringBuilder(\"word\");\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(!isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
    }
    @Test
    public void calculate13Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst.insert(0,\"word\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final StringBuilder inst=$new StringBuilder();\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(!isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
    }
    @Test
    public void calculate14Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst.insert(0,0);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final StringBuilder inst=$new StringBuilder();\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(!isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
    }
    @Test
    public void calculate15Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst.insert(0,$new $char[]{'0'});\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final StringBuilder inst=$new StringBuilder();\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(!isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
    }
    @Test
    public void calculate16Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst.insert(0,\"word\",0,1);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final StringBuilder inst=$new StringBuilder();\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(!isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
    }
    @Test
    public void calculate17Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst.insert(0,$new $char[]{'0'},0,1);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final StringBuilder inst=$new StringBuilder();\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(!isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
    }
    @Test
    public void calculate18Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst.replace(0,1,\"hello\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final StringBuilder inst=$new StringBuilder(\"word\");\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(!isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
    }
    @Test
    public void calculate19Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst.setCharAt(0,'h');\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final StringBuilder inst=$new StringBuilder(\"word\");\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(!isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
    }
    @Test
    public void calculate20Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst=1;\n");
        xml_.append(" $static{\n");
        xml_.append("  inst &= 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq(0, getNumber(cont_,"pkg.Ex","inst"));
    }
    @Test
    public void calculate21Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst=1;\n");
        xml_.append(" $static{\n");
        xml_.append("  inst |= 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq(3, getNumber(cont_,"pkg.Ex","inst"));
    }
    @Test
    public void calculate22Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $long inst=1;\n");
        xml_.append(" $static{\n");
        xml_.append("  inst &= 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq(0, getNumber(cont_,"pkg.Ex","inst"));
    }
    @Test
    public void calculate23Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $long inst=1;\n");
        xml_.append(" $static{\n");
        xml_.append("  inst |= 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq(3, getNumber(cont_,"pkg.Ex","inst"));
    }
    @Test
    public void calculate24Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst=1;\n");
        xml_.append(" $static{\n");
        xml_.append("  inst ^= 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq(3, getNumber(cont_,"pkg.Ex","inst"));
    }
    @Test
    public void calculate25Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $long inst=1;\n");
        xml_.append(" $static{\n");
        xml_.append("  inst ^= 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq(3, getNumber(cont_,"pkg.Ex","inst"));
    }
    @Test
    public void calculate26Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst &= 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst = 1;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(!isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
    }
    @Test
    public void calculate27Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst=1;\n");
        xml_.append(" $static{\n");
        xml_.append("  inst %= 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq(1, getNumber(cont_,"pkg.Ex","inst"));
    }
    @Test
    public void calculate28Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $long inst=1;\n");
        xml_.append(" $static{\n");
        xml_.append("  inst %= 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq(1, getNumber(cont_,"pkg.Ex","inst"));
    }
    @Test
    public void calculate29Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $long inst=1;\n");
        xml_.append(" $static{\n");
        xml_.append("  inst += 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq(3, getNumber(cont_,"pkg.Ex","inst"));
    }
    @Test
    public void calculate30Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $long inst=1;\n");
        xml_.append(" $static{\n");
        xml_.append("  inst -= 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq(-1, getNumber(cont_,"pkg.Ex","inst"));
    }
    @Test
    public void calculate31Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $long inst=3;\n");
        xml_.append(" $static{\n");
        xml_.append("  inst /= 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq(1, getNumber(cont_,"pkg.Ex","inst"));
    }
    @Test
    public void calculate32Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $long inst=3;\n");
        xml_.append(" $static{\n");
        xml_.append("  inst <<= 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq(12, getNumber(cont_,"pkg.Ex","inst"));
    }
    @Test
    public void calculate33Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static StringBuilder inst=$new StringBuilder(\"hello \");\n");
        xml_.append(" $static{\n");
        xml_.append("  inst.append(\"world\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("hello world", getString(cont_,"pkg.Ex","inst"));
    }
    @Test
    public void calculate34Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $long inst=12;\n");
        xml_.append(" $static{\n");
        xml_.append("  inst >>= 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq(3, getNumber(cont_,"pkg.Ex","inst"));
    }
    @Test
    public void calculate35Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $long inst=3;\n");
        xml_.append(" $static{\n");
        xml_.append("  inst <<<= 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq(12, getNumber(cont_,"pkg.Ex","inst"));
    }
    @Test
    public void calculate36Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $long inst=12;\n");
        xml_.append(" $static{\n");
        xml_.append("  inst >>>= 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq(3, getNumber(cont_,"pkg.Ex","inst"));
    }
    @Test
    public void calculate37Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $long inst=3;\n");
        xml_.append(" $static{\n");
        xml_.append("  inst <<<<= 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq(12, getNumber(cont_,"pkg.Ex","inst"));
    }
    @Test
    public void calculate38Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $long inst=12;\n");
        xml_.append(" $static{\n");
        xml_.append("  inst >>>>= 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq(3, getNumber(cont_,"pkg.Ex","inst"));
    }
    @Test
    public void calculate39Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String inst=\"1\";\n");
        xml_.append(" $static{\n");
        xml_.append("  inst += 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("12", getString(cont_,"pkg.Ex","inst"));
    }

    @Test
    public void calculate40Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst ++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst = 1;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(!isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
    }

    @Test
    public void calculate41Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst += 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst = 1;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(!isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
    }

    @Test
    public void calculate42Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst = 5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst = 1;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(!isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
    }

    @Test
    public void calculate43Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.single.inst ++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final ExTwo single = $new ExTwo();\n");
        xml_.append(" $public $int inst = 1;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(!isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
    }

    @Test
    public void calculate44Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.single.inst += 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final ExTwo single = $new ExTwo();\n");
        xml_.append(" $public $int inst = 1;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(!isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
    }

    @Test
    public void calculate45Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.single.inst = 5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final ExTwo single = $new ExTwo();\n");
        xml_.append(" $public $int inst = 1;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(!isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
    }

    @Test
    public void calculate46Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst[0] ++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int[] inst = {1};\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(!isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
    }

    @Test
    public void calculate47Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst[0] += 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int[] inst = {1};\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(!isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
    }

    @Test
    public void calculate48Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst[0] = 5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int[] inst = {1};\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(!isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
    }

    @Test
    public void calculate49Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo single = ExTwo.single;\n");
        xml_.append("  single.inst ++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final ExTwo single = $new ExTwo();\n");
        xml_.append(" $public $int inst = 1;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(!isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
    }

    @Test
    public void calculate50Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo single = ExTwo.single;\n");
        xml_.append("  single.inst += 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final ExTwo single = $new ExTwo();\n");
        xml_.append(" $public $int inst = 1;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(!isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
    }

    @Test
    public void calculate51Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo single = ExTwo.single;\n");
        xml_.append("  single.inst = 5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final ExTwo single = $new ExTwo();\n");
        xml_.append(" $public $int inst = 1;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(!isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
    }

    @Test
    public void calculate52Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  $int[] inst = ExTwo.inst;\n");
        xml_.append("  inst[0] ++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int[] inst = {1};\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(!isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
    }

    @Test
    public void calculate53Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  $int[] inst = ExTwo.inst;\n");
        xml_.append("  inst[0] += 1;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int[] inst = {1};\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(!isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
    }

    @Test
    public void calculate54Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  $int[] inst = ExTwo.inst;\n");
        xml_.append("  inst[0] = 5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int[] inst = {1};\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(!isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
    }
    @Test
    public void calculate58Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  $Class.set(ExTwo.inst,0,1);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final $int[] inst = {0};\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(!isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
    }
    @Test
    public void calculate59Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $final $int[] inst = {0};\n");
        xml_.append(" $public $static $final $int ance;\n");
        xml_.append(" $static {\n");
        xml_.append("  $Class.set(inst,0,1);\n");
        xml_.append("  ance = inst[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq(1,getNumber(cont_,"pkg.Ex","inst",0));
        assertEq(1,getNumber(cont_,"pkg.Ex","ance"));
    }
    @Test
    public void calculate60Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $int[] other;\n");
        xml_.append(" $static{\n");
        xml_.append("  other = ExTwo.field.clone();\n");
        xml_.append("  other[0]+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static $int[] field = {0,1};\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
    }
    @Test
    public void calculate61Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $int[] field = {0,1};\n");
        xml_.append(" $static $int[] other;\n");
        xml_.append(" $static{\n");
        xml_.append("  other = field.clone();\n");
        xml_.append("  other[0]+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
    }
    @Test
    public void calculate62Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $int[][] other;\n");
        xml_.append(" $static{\n");
        xml_.append("  other = ExTwo.field.clone();\n");
        xml_.append("  other[0][0]+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static $int[][] field = {{0,1}};\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(!isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
    }
    @Test
    public void calculate63Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static code.util.Replacement other;\n");
        xml_.append(" $static{\n");
        xml_.append("  other = ExTwo.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static code.util.Replacement field = $new code.util.Replacement(\"\",\"\");\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
    }
    @Test
    public void calculate64Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static Ann other;\n");
        xml_.append(" $static{\n");
        xml_.append("  other = ExTwo.field;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.Ann {\n");
        xml_.append("}\n");
        xml_.append("@Ann\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static Ann field = (Ann)$class(ExTwo).getAnnotations()[0];\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
    }
    @Test
    public void calculate65Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $final Character ch = $null;\n");
        xml_.append(" $static $final String other = $new String(ch);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(!isInitialized(cont_, "pkg.Ex"));
    }
    @Test
    public void calculate66Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Int1 {$int m();}\n");
        xml_.append("$public $interface pkg.Int2 {}\n");
        xml_.append("$public $interface pkg.Int3:Int1:Int2 {}\n");
        xml_.append("$public $interface pkg.Int4:Int1:Int2 {}\n");
        xml_.append("$public $class pkg.ExThree:Int3:Int4 {$public $int m(){$return 5;}}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $int v;\n");
        xml_.append(" $static $int t=1;\n");
        xml_.append(" $static{\n");
        xml_.append("  (v)=(t)+($bool(1>0,(Int3)$new ExThree(),(Int4)$new ExThree()).m());\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final StringBuilder inst=$new StringBuilder();\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
        assertEq(1, ((NumberStruct) getStaticField(cont_, new ClassField("pkg.Ex", "t"))).intStruct());
        assertEq(6, ((NumberStruct) getStaticField(cont_, new ClassField("pkg.Ex", "v"))).intStruct());
    }
    @Test
    public void calculate67Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $final String a1 = \"\"+$null;\n");
        xml_.append(" $static $final String a2 = $null+\"\";\n");
        xml_.append(" $static $final String a3 = \"\"+$new StringBuilder();\n");
        xml_.append(" $static $final String a4 = $new StringBuilder()+\"\";\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
    }
    @Test
    public void calculate68Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $boolean inst=$true;\n");
        xml_.append(" $static{\n");
        xml_.append("  inst &= $false;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertTrue(!getBoolean(cont_,"pkg.Ex","inst"));
    }
    @Test
    public void calculate69Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $boolean inst=$false;\n");
        xml_.append(" $static{\n");
        xml_.append("  inst |= $true;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertTrue(getBoolean(cont_,"pkg.Ex","inst"));
    }
    @Test
    public void calculate70Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $boolean inst=$false;\n");
        xml_.append(" $static{\n");
        xml_.append("  inst ^= $true;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertTrue(getBoolean(cont_,"pkg.Ex","inst"));
    }
    @Test
    public void calculate71Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $boolean inst=$true;\n");
        xml_.append(" $static{\n");
        xml_.append("  inst ^= $true;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertTrue(!getBoolean(cont_,"pkg.Ex","inst"));
    }
    @Test
    public void calculate72Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $int value;\n");
        xml_.append(" $static {value=pkg.ExTwo.otherField+pkg.ExTwo.get;}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo:pkg.ExPar {\n");
        xml_.append("  $public $static $int otherField=3;\n");
        xml_.append("  $public $static $int get=2;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExPar {\n");
        xml_.append("  $static $int get;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq(5 ,getNumber(cont_,"pkg.Ex","value"));
    }
    @Test
    public void calculate73Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  $ObjectsUtil.setParent(ExTwo.inst,$new ExTwo());\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final ExTwo.Inner inst = $new ExTwo().$new Inner();\n");
        xml_.append(" $public $static $void exec(){}\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(!isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
    }
    @Test
    public void calculate74Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $final ExTwo.Inner inst = $new ExTwo().$new Inner();\n");
        xml_.append(" $static{\n");
        xml_.append("  $ObjectsUtil.setParent(inst,$new ExTwo());\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final ExTwo.Inner inst = $new ExTwo().$new Inner();\n");
        xml_.append(" $public $static $void exec(){}\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
    }
    @Test
    public void calculate75Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $final ExTwo.Inner inst = $new ExTwo().$new Inner();\n");
        xml_.append(" $static{\n");
        xml_.append("  $ObjectsUtil.setParent(\"\",$new ExTwo());\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final ExTwo.Inner inst = $new ExTwo().$new Inner();\n");
        xml_.append(" $public $static $void exec(){}\n");
        xml_.append(" $public $class Inner {\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
    }
    @Test
    public void calculate76Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final StringBuilder inst=$new StringBuilder();\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst.append(\"word\");\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxMustInit(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
    }
    @Test
    public void calculate77Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.Ann {\n");
        xml_.append("MyEnum f() MyEnum.ONE;\n");
        xml_.append("}\n");
        xml_.append("$public $enum pkg.MyEnum {\n");
        xml_.append("ONE;\n");
        xml_.append("$public $int i;\n");
        xml_.append("}\n");
        xml_.append("@Ann\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static {\n");
        xml_.append("  Ann field = (Ann)$class(ExTwo).getAnnotations()[0];\n");
        xml_.append("  field.f().i = 5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.MyEnum"));
        assertTrue(isInitialized(cont_, "pkg.Ann"));
        assertTrue(!isInitialized(cont_, "pkg.ExTwo"));
    }
    @Test
    public void calculate78Test() {
        StringBuilder xml_;
        StringMap<String> files_ = new StringMap<String>();
        xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.Ann {\n");
        xml_.append("MyEnum f();\n");
        xml_.append("}\n");
        xml_.append("$public $enum pkg.MyEnum {\n");
        xml_.append("ONE;\n");
        xml_.append("$public $int i;\n");
        xml_.append("}\n");
        xml_.append("@Ann(MyEnum.ONE)\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $static {\n");
        xml_.append("  Ann field = (Ann)$class(ExTwo).getAnnotations()[0];\n");
        xml_.append("  field.f().i = 5;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.MyEnum"));
        assertTrue(isInitialized(cont_, "pkg.Ann"));
        assertTrue(!isInitialized(cont_, "pkg.ExTwo"));
    }
    @Test
    public void calculate79Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static $int[] field;\n");
        xml_.append(" $static $int[] other;\n");
        xml_.append(" $static{\n");
        xml_.append("  other = field.clone();\n");
        xml_.append("  other[0]+=2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(!isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
    }
    @Test
    public void calculateFailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst.append(\"word\");n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final StringBuilder inst=$new StringBuilder();\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxMustInitFail(files_);
        assertTrue(!isSuccessfulInitialized(cont_, "pkg.Ex"));
    }
    private Boolean getBoolean(ContextEl _cont,String _className, String _fieldName) {
        Struct str_ = getStaticField(_cont, new ClassField(_className, _fieldName));
        return BooleanStruct.isTrue(str_);
    }
    private int getNumber(ContextEl _cont,String _className, String _fieldName, int _index) {
        Struct str_ = getStaticField(_cont, new ClassField(_className, _fieldName));
        return ((NumberStruct)((ArrayStruct)str_).getInstance()[_index]).intStruct();
    }
    private int getNumber(ContextEl _cont,String _className, String _fieldName) {
        Struct str_ = getStaticField(_cont, new ClassField(_className, _fieldName));
        return ((NumberStruct)str_).intStruct();
    }
    private String getString(ContextEl _cont,String _className, String _fieldName) {
        Struct str_ = getStaticField(_cont, new ClassField(_className, _fieldName));
        return ((CharSequenceStruct)str_).toStringInstance();
    }

}
