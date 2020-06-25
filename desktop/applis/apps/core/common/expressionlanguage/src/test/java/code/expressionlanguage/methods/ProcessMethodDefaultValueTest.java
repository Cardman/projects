package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;

public final class ProcessMethodDefaultValueTest extends ProcessMethodCommon {
    @Test
    public void calculate1Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<$int> inst=$new pkg.ExTwo<$int>();\n");
        xml_.append(" $public $int ance=inst.value;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T>:ExThree<T> {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree<S> {\n");
        xml_.append(" $public S value = $defaultValue(S);\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextElDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<$int>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(0, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void calculate2Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<$int> inst=$new pkg.ExTwo<$int>();\n");
        xml_.append(" $public $iterable<$int> ance=inst.value;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T>:ExThree<T> {\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree<S> {\n");
        xml_.append(" $public $iterable<S> value = $defaultValue($iterable<S>);\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextElDefault();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<$int>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq("", field_.getClassName(cont_));
    }
}
