package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;


public final class ProcessMethodInstanceGenericTest extends ProcessMethodCommon {
    @Test
    public void instanceArgument95Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExTwo<java.lang.Number>();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<java.lang.Number>", field_.getClassName(cont_));
        Struct subField_;
        subField_ = getField(field_, new ClassField("pkg.ExTwo", "inst"));
        assertEq(INTEGER, subField_.getClassName(cont_));
        assertEq(2, ((NumberStruct)subField_).intStruct());
    }

    @Test
    public void instanceArgument96Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExTwo<java.lang.Number>(8i);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T[] inst;\n");
        xml_.append(" $public (T i){\n");
        xml_.append("  inst=$new T[]{i};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<java.lang.Number>", field_.getClassName(cont_));
        Struct subField_;
        subField_ = getField(field_, new ClassField("pkg.ExTwo", "inst"));
        assertEq(ARR_NUMBER, subField_.getClassName(cont_));
        Struct[] nbs_ = ((ArrayStruct) subField_).getInstance();
        assertEq(1, nbs_.length);
        assertEq(8, ((NumberStruct) nbs_[0]).intStruct());
    }
    @Test
    public void instanceArgument97Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExTwo<java.lang.Number>(8i);\n");
        xml_.append(" $public pkg.ExTwo<java.lang.String> ance=$new pkg.ExTwo<java.lang.String>(8i);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $public (java.lang.Object i){\n");
        xml_.append("  $if(i $instanceof T){\n");
        xml_.append("   inst=1i;\n");
        xml_.append("  }\n");
        xml_.append("  $else{\n");
        xml_.append("   inst=2i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<java.lang.Number>", field_.getClassName(cont_));
        Struct subField_;
        subField_ = getField(field_, new ClassField("pkg.ExTwo", "inst"));
        assertEq(INTEGER, subField_.getClassName(cont_));
        assertEq(1, ((NumberStruct)subField_).intStruct());
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq("pkg.ExTwo<java.lang.String>", field_.getClassName(cont_));
        subField_ = getField(field_, new ClassField("pkg.ExTwo", "inst"));
        assertEq(INTEGER, subField_.getClassName(cont_));
        assertEq(2, ((NumberStruct)subField_).intStruct());
    }
    @Test
    public void instanceArgument97_Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExTwo<java.lang.Number>(8i);\n");
        xml_.append(" $public pkg.ExTwo<java.lang.String> ance=$new pkg.ExTwo<java.lang.String>(8i);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $public (java.lang.Object i){\n");
        xml_.append("  $if(i $instanceof T){\n");
        xml_.append("   inst=1i;\n");
        xml_.append("  }\n");
        xml_.append("  $else{\n");
        xml_.append("   inst=2i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<java.lang.Number>", field_.getClassName(cont_));
        Struct subField_;
        subField_ = getField(field_, new ClassField("pkg.ExTwo", "inst"));
        assertEq(INTEGER, subField_.getClassName(cont_));
        assertEq(1, ((NumberStruct)subField_).intStruct());
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq("pkg.ExTwo<java.lang.String>", field_.getClassName(cont_));
        subField_ = getField(field_, new ClassField("pkg.ExTwo", "inst"));
        assertEq(INTEGER, subField_.getClassName(cont_));
        assertEq(2, ((NumberStruct)subField_).intStruct());
    }
    @Test
    public void instanceArgument98Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExTwo<java.lang.Number>();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public pkg.ExThree<T> inst=$new pkg.ExThree<T>();\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U> {\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<java.lang.Number>", field_.getClassName(cont_));
        Struct subField_;
        subField_ = getField(field_, new ClassField("pkg.ExTwo", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", subField_.getClassName(cont_));
        Struct subSubField_;
        subSubField_ = getField(subField_, new ClassField("pkg.ExThree", "inst"));
        assertEq(INTEGER, subSubField_.getClassName(cont_));
        assertEq(2, ((NumberStruct)subSubField_).intStruct());
    }
}
