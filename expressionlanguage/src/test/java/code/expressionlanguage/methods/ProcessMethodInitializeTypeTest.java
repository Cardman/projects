package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.structs.CharSequenceStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;

public final class ProcessMethodInitializeTypeTest extends ProcessMethodCommon {
    @Test
    public void calculate1Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst.append(\"word\"):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final StringBuilder inst=$new StringBuilder():\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertTrue(!cont_.getClasses().isInitialized("pkg.Ex"));
        assertTrue(cont_.getClasses().isInitialized("pkg.ExTwo"));
    }
    @Test
    public void calculate2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst.append(0):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final StringBuilder inst=$new StringBuilder():\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertTrue(!cont_.getClasses().isInitialized("pkg.Ex"));
        assertTrue(cont_.getClasses().isInitialized("pkg.ExTwo"));
    }
    @Test
    public void calculate3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst.append($new $char[]{'0'}):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final StringBuilder inst=$new StringBuilder():\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertTrue(!cont_.getClasses().isInitialized("pkg.Ex"));
        assertTrue(cont_.getClasses().isInitialized("pkg.ExTwo"));
    }
    @Test
    public void calculate4Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst.append(\"word\",0,1):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final StringBuilder inst=$new StringBuilder():\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertTrue(!cont_.getClasses().isInitialized("pkg.Ex"));
        assertTrue(cont_.getClasses().isInitialized("pkg.ExTwo"));
    }
    @Test
    public void calculate5Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst.append($new $char[]{'0'},0,1):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final StringBuilder inst=$new StringBuilder():\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertTrue(!cont_.getClasses().isInitialized("pkg.Ex"));
        assertTrue(cont_.getClasses().isInitialized("pkg.ExTwo"));
    }
    @Test
    public void calculate6Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst.delete(0,1):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final StringBuilder inst=$new StringBuilder(\"word\"):\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertTrue(!cont_.getClasses().isInitialized("pkg.Ex"));
        assertTrue(cont_.getClasses().isInitialized("pkg.ExTwo"));
    }
    @Test
    public void calculate7Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst.deleteCharAt(0):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final StringBuilder inst=$new StringBuilder(\"word\"):\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertTrue(!cont_.getClasses().isInitialized("pkg.Ex"));
        assertTrue(cont_.getClasses().isInitialized("pkg.ExTwo"));
    }
    @Test
    public void calculate8Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst.clear():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final StringBuilder inst=$new StringBuilder(\"word\"):\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertTrue(!cont_.getClasses().isInitialized("pkg.Ex"));
        assertTrue(cont_.getClasses().isInitialized("pkg.ExTwo"));
    }
    @Test
    public void calculate9Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst.ensureCapacity(1):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final StringBuilder inst=$new StringBuilder(\"word\"):\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertTrue(!cont_.getClasses().isInitialized("pkg.Ex"));
        assertTrue(cont_.getClasses().isInitialized("pkg.ExTwo"));
    }
    @Test
    public void calculate10Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst.reverse():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final StringBuilder inst=$new StringBuilder(\"word\"):\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertTrue(!cont_.getClasses().isInitialized("pkg.Ex"));
        assertTrue(cont_.getClasses().isInitialized("pkg.ExTwo"));
    }
    @Test
    public void calculate11Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst.setLength(1):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final StringBuilder inst=$new StringBuilder(\"word\"):\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertTrue(!cont_.getClasses().isInitialized("pkg.Ex"));
        assertTrue(cont_.getClasses().isInitialized("pkg.ExTwo"));
    }
    @Test
    public void calculate12Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst.trimToSize():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final StringBuilder inst=$new StringBuilder(\"word\"):\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertTrue(!cont_.getClasses().isInitialized("pkg.Ex"));
        assertTrue(cont_.getClasses().isInitialized("pkg.ExTwo"));
    }
    @Test
    public void calculate13Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst.insert(0,\"word\"):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final StringBuilder inst=$new StringBuilder():\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertTrue(!cont_.getClasses().isInitialized("pkg.Ex"));
        assertTrue(cont_.getClasses().isInitialized("pkg.ExTwo"));
    }
    @Test
    public void calculate14Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst.insert(0,0):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final StringBuilder inst=$new StringBuilder():\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertTrue(!cont_.getClasses().isInitialized("pkg.Ex"));
        assertTrue(cont_.getClasses().isInitialized("pkg.ExTwo"));
    }
    @Test
    public void calculate15Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst.insert(0,$new $char[]{'0'}):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final StringBuilder inst=$new StringBuilder():\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertTrue(!cont_.getClasses().isInitialized("pkg.Ex"));
        assertTrue(cont_.getClasses().isInitialized("pkg.ExTwo"));
    }
    @Test
    public void calculate16Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst.insert(0,\"word\",0,1):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final StringBuilder inst=$new StringBuilder():\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertTrue(!cont_.getClasses().isInitialized("pkg.Ex"));
        assertTrue(cont_.getClasses().isInitialized("pkg.ExTwo"));
    }
    @Test
    public void calculate17Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst.insert(0,$new $char[]{'0'},0,1):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final StringBuilder inst=$new StringBuilder():\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertTrue(!cont_.getClasses().isInitialized("pkg.Ex"));
        assertTrue(cont_.getClasses().isInitialized("pkg.ExTwo"));
    }
    @Test
    public void calculate18Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst.replace(0,1,\"hello\"):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final StringBuilder inst=$new StringBuilder(\"word\"):\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertTrue(!cont_.getClasses().isInitialized("pkg.Ex"));
        assertTrue(cont_.getClasses().isInitialized("pkg.ExTwo"));
    }
    @Test
    public void calculate19Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst.setCharAt(0,'h'):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $final StringBuilder inst=$new StringBuilder(\"word\"):\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertTrue(!cont_.getClasses().isInitialized("pkg.Ex"));
        assertTrue(cont_.getClasses().isInitialized("pkg.ExTwo"));
    }
    @Test
    public void calculate20Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst=1:\n");
        xml_.append(" $static{\n");
        xml_.append("  inst &= 2:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        assertEq(0, getNumber(cont_,"pkg.Ex","inst"));
    }
    @Test
    public void calculate21Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst=1:\n");
        xml_.append(" $static{\n");
        xml_.append("  inst |= 2:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        assertEq(3, getNumber(cont_,"pkg.Ex","inst"));
    }
    @Test
    public void calculate22Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $long inst=1:\n");
        xml_.append(" $static{\n");
        xml_.append("  inst &= 2:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        assertEq(0, getNumber(cont_,"pkg.Ex","inst"));
    }
    @Test
    public void calculate23Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $long inst=1:\n");
        xml_.append(" $static{\n");
        xml_.append("  inst |= 2:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        assertEq(3, getNumber(cont_,"pkg.Ex","inst"));
    }
    @Test
    public void calculate24Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst=1:\n");
        xml_.append(" $static{\n");
        xml_.append("  inst ^= 2:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        assertEq(3, getNumber(cont_,"pkg.Ex","inst"));
    }
    @Test
    public void calculate25Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $long inst=1:\n");
        xml_.append(" $static{\n");
        xml_.append("  inst ^= 2:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        assertEq(3, getNumber(cont_,"pkg.Ex","inst"));
    }
    @Test
    public void calculate26Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.inst &= 2:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int inst = 1:\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertTrue(!cont_.getClasses().isInitialized("pkg.Ex"));
        assertTrue(cont_.getClasses().isInitialized("pkg.ExTwo"));
    }
    @Test
    public void calculate27Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int inst=1:\n");
        xml_.append(" $static{\n");
        xml_.append("  inst %= 2:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        assertEq(1, getNumber(cont_,"pkg.Ex","inst"));
    }
    @Test
    public void calculate28Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $long inst=1:\n");
        xml_.append(" $static{\n");
        xml_.append("  inst %= 2:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        assertEq(1, getNumber(cont_,"pkg.Ex","inst"));
    }
    @Test
    public void calculate29Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $long inst=1:\n");
        xml_.append(" $static{\n");
        xml_.append("  inst += 2:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        assertEq(3, getNumber(cont_,"pkg.Ex","inst"));
    }
    @Test
    public void calculate30Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $long inst=1:\n");
        xml_.append(" $static{\n");
        xml_.append("  inst -= 2:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        assertEq(-1, getNumber(cont_,"pkg.Ex","inst"));
    }
    @Test
    public void calculate31Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $long inst=3:\n");
        xml_.append(" $static{\n");
        xml_.append("  inst /= 2:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        assertEq(1, getNumber(cont_,"pkg.Ex","inst"));
    }
    @Test
    public void calculate32Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $long inst=3:\n");
        xml_.append(" $static{\n");
        xml_.append("  inst <<= 2:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        assertEq(12, getNumber(cont_,"pkg.Ex","inst"));
    }
    @Test
    public void calculate34Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $long inst=12:\n");
        xml_.append(" $static{\n");
        xml_.append("  inst >>= 2:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        assertEq(3, getNumber(cont_,"pkg.Ex","inst"));
    }
    @Test
    public void calculate35Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $long inst=3:\n");
        xml_.append(" $static{\n");
        xml_.append("  inst <<<= 2:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        assertEq(12, getNumber(cont_,"pkg.Ex","inst"));
    }
    @Test
    public void calculate36Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $long inst=12:\n");
        xml_.append(" $static{\n");
        xml_.append("  inst >>>= 2:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        assertEq(3, getNumber(cont_,"pkg.Ex","inst"));
    }
    @Test
    public void calculate37Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $long inst=3:\n");
        xml_.append(" $static{\n");
        xml_.append("  inst <<<<= 2:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        assertEq(12, getNumber(cont_,"pkg.Ex","inst"));
    }
    @Test
    public void calculate38Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $long inst=12:\n");
        xml_.append(" $static{\n");
        xml_.append("  inst >>>>= 2:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        assertEq(3, getNumber(cont_,"pkg.Ex","inst"));
    }
    @Test
    public void calculate39Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static String inst=\"1\":\n");
        xml_.append(" $static{\n");
        xml_.append("  inst += 2:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        assertEq("12", getString(cont_,"pkg.Ex","inst"));
    }
    private Number getNumber(ContextEl _cont,String _className, String _fieldName) {
        Struct str_ = _cont.getClasses().getStaticField(new ClassField(_className,_fieldName));
        return ((NumberStruct)str_).getInstance();
    }
    private String getString(ContextEl _cont,String _className, String _fieldName) {
        Struct str_ = _cont.getClasses().getStaticField(new ClassField(_className,_fieldName));
        return ((CharSequenceStruct)str_).getInstance().toString();
    }
}
