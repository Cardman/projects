package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.InitClassState;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public final class ProcessMethodCallGenericTest extends ProcessMethodCommon {

    @Test
    public void instanceArgument98Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<$int> inst=$new pkg.ExTwo<$int>();\n");
        xml_.append(" $public $int ance=inst.get();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $normal T get(){\n");
        xml_.append("  $return $null;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        Argument arg_ = instanceError("pkg.Ex", null, id_, args_, cont_);
        assertEq(cont_.getStandards().getCoreNames().getAliasNullPe(),arg_.getStruct().getClassName(cont_));
    }
    @Test
    public void instanceArgument99Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExTwo<java.lang.Number>();\n");
        xml_.append(" $public $int ance=inst.get(8I);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(T i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument100Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public $int ance=inst.get(8I);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(T i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U:java.lang.Number> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get(U i){\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(3, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument101Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo inst=$new pkg.ExFour();\n");
        xml_.append(" $public $int ance=inst.get();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.ExThree :pkg.ExTwo{\n");
        xml_.append(" $public $abstract $int get(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExFour :pkg.ExThree{\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExFour", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(3, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument102Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public $int ance=inst.$classchoice(pkg.ExTwo<?>)get();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U:java.lang.Number> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument103Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public $int ance=inst.$classchoice(pkg.ExTwo<java.lang.Number>)get(8I);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(T i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U:java.lang.Number> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get(U i){\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument104Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public $int ance=inst.$classchoice(pkg.ExTwo<!java.lang.Number>)get(8I);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(T i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U:java.lang.Number> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get(U i){\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument105Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public $int ance=inst.$classchoice(pkg.ExTwo<!java.lang.Number>)get(1I);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $normal $int get(T i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get(U i){\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument106Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExThree<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public $int ance=inst.$super.get(1I);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $normal $int get(T i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get(U i){\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument107Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExThree<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public java.lang.Number ance;\n");
        xml_.append(" {\n");
        xml_.append("  inst.get=3i;\n");
        xml_.append("  inst.$super.get=1i;\n");
        xml_.append("  ance=inst.$super.get;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T get;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U> :pkg.ExTwo<U>{\n");
        xml_.append(" $public U get;\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument108Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExTwo<java.lang.Number>();\n");
        xml_.append(" $public $int ance=inst.get(8I);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> :pkg.Int<T>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Int<U:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(U i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Int", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument109Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.Int<java.lang.Number> inst=$new pkg.ExTwo<java.lang.Number>();\n");
        xml_.append(" $public $int ance=inst.get(8I);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> :pkg.Int<T>{\n");
        xml_.append(" $public $normal $int get(T i){\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Int<U:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(U i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Int", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(3, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument110Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExThree<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public java.lang.Number ance;\n");
        xml_.append(" {\n");
        xml_.append("  inst.get=3i;\n");
        xml_.append("  inst.$super.get=1i;\n");
        xml_.append("  ance=inst.$super.getter();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T get;\n");
        xml_.append(" $public $normal T getter(){\n");
        xml_.append("  $return get;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U> :pkg.ExTwo<U>{\n");
        xml_.append(" $public U get;\n");
        xml_.append(" $public $normal U getter(){\n");
        xml_.append("  $return get;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument111Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public java.lang.Number ance;\n");
        xml_.append(" {\n");
        xml_.append("  inst.$classchoice(pkg.ExThree<!java.lang.Number>)get=3i;\n");
        xml_.append("  inst.get=1i;\n");
        xml_.append("  ance=inst.getter();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T get;\n");
        xml_.append(" $public $normal T getter(){\n");
        xml_.append("  $return get;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U> :pkg.ExTwo<U>{\n");
        xml_.append(" $public U get;\n");
        xml_.append(" $public $normal U getter(){\n");
        xml_.append("  $return get;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(3, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument112Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public java.lang.Number ance;\n");
        xml_.append(" {\n");
        xml_.append("  inst.$classchoice(pkg.ExThree<!java.lang.Number>)get=3i;\n");
        xml_.append("  inst.get=1i;\n");
        xml_.append("  ance=inst.getter();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo<T> {\n");
        xml_.append(" $public T get;\n");
        xml_.append(" $public $normal T getter(){\n");
        xml_.append("  $return get;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U> :pkg.ExTwo<U>{\n");
        xml_.append(" $public U get;\n");
        xml_.append(" $public(){\n");
        xml_.append("  $interfaces(pkg.ExTwo)();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal U getter(){\n");
        xml_.append("  $return get;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(3, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument113Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public java.lang.Number ance;\n");
        xml_.append(" {\n");
        xml_.append("  inst.$classchoice(pkg.ExThree<!java.lang.Number>)get=3i;\n");
        xml_.append("  inst.get=1i;\n");
        xml_.append("  ance=inst.getter();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo<T> {\n");
        xml_.append(" $public T get;\n");
        xml_.append(" $public $abstract T getter(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U> :pkg.ExTwo<U>{\n");
        xml_.append(" $public U get;\n");
        xml_.append(" $public(){\n");
        xml_.append("  $interfaces(pkg.ExTwo)();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal U getter(){\n");
        xml_.append("  $return get;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(3, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument1132Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public java.lang.Number ance;\n");
        xml_.append(" {\n");
        xml_.append("  inst.$classchoice(pkg.ExThree<!java.lang.Number>)get=3i;\n");
        xml_.append("  inst.get=1i;\n");
        xml_.append("  ance=($int)inst.$classchoice(pkg.ExThree<?java.lang.Number>)get+1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo<T> {\n");
        xml_.append(" $public T get;\n");
        xml_.append(" $public $abstract T getter(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U> :pkg.ExTwo<U>{\n");
        xml_.append(" $public U get;\n");
        xml_.append(" $public(){\n");
        xml_.append("  $interfaces(pkg.ExTwo)();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal U getter(){\n");
        xml_.append("  $return get;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(4, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument114Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExTwo<java.lang.Number>();\n");
        xml_.append(" $public $int ance=inst.get(8I);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(T i){\n");
        xml_.append("  $return 1i+i.intValue();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(9, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument115Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<pkg.CustNb> inst=$new pkg.ExTwo<pkg.CustNb>();\n");
        xml_.append(" $public $int ance=inst.get($new pkg.CustNb(8i));\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:pkg.CustNb> {\n");
        xml_.append(" $public $normal $int get(T i){\n");
        xml_.append("  $return 1i+i.intValue();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustNb {\n");
        xml_.append(" $public $int value;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  value=i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int intValue(){\n");
        xml_.append("  $return value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/CustNb", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<pkg.CustNb>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(9, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument116Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<pkg.CustNb> inst=$new pkg.ExTwo<pkg.CustNb>();\n");
        xml_.append(" $public $int ance=inst.get($new pkg.CustInt(8i));\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:pkg.CustNb> {\n");
        xml_.append(" $public $normal $int get(T i){\n");
        xml_.append("  $return 1i+i.intValue();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.CustNb {\n");
        xml_.append(" $public $abstract $int intValue(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/CustNb", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustInt :pkg.CustNb{\n");
        xml_.append(" $public $int value;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  value=i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int intValue(){\n");
        xml_.append("  $return value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/CustInt", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<pkg.CustNb>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(9, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument117Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<pkg.CustInt> inst=$new pkg.ExTwo<pkg.CustInt>();\n");
        xml_.append(" $public $int ance=inst.get($new pkg.CustInt(8i),$new pkg.CustInt(5i));\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:pkg.CustNb<T>> {\n");
        xml_.append(" $public $normal $int get(T i,T j){\n");
        xml_.append("  $return 1i+i.diff(j);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.CustNb<U:pkg.CustNb<U>> {\n");
        xml_.append(" $public $abstract $int intValue(){\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int diff(U o){\n");
        xml_.append("  $return intValue()-o.intValue();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/CustNb", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustInt :pkg.CustNb<pkg.CustInt>{\n");
        xml_.append(" $public $int value;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  value=i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int intValue(){\n");
        xml_.append("  $return value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/CustInt", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<pkg.CustInt>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(4, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument118Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<pkg.CustInt> inst=$new pkg.ExTwo<pkg.CustInt>();\n");
        xml_.append(" $public $int ance=inst.get($new pkg.CustInt(8i),$new pkg.CustInt(5i));\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:pkg.CustNb<T>> {\n");
        xml_.append(" $public $normal $int get(T i,T j){\n");
        xml_.append("  $return 1i+i.diff(j);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $abstract $class pkg.CustNb<U:pkg.CustNb<U>> {\n");
        xml_.append(" $public $abstract $int intValue(){\n");
        xml_.append(" }\n");
        xml_.append(" $public $abstract $int diff(U o){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/CustNb", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustInt :pkg.CustNb<pkg.CustInt>{\n");
        xml_.append(" $public $int value;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  value=i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int intValue(){\n");
        xml_.append("  $return value;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int diff(pkg.CustInt o){\n");
        xml_.append("  $return intValue()-o.intValue();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/CustInt", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<pkg.CustInt>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(4, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument119Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $normal $int superaccess(){\n");
        xml_.append("  $return $super.getter();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return 2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Int{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Int {\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return 5i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Int", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public pkg.Ex inst=$new pkg.Ex();\n");
        xml_.append(" $public $int ance=inst.superaccess();\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.ExThree");

        Argument ret_;
        ret_ = instanceNormal("pkg.ExThree", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.ExThree", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(5, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument120Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExTwo<java.lang.Number>($new pkg.ExThree<java.lang.Number>());\n");
        xml_.append(" $public pkg.ExTwo<java.lang.String> ance=$new pkg.ExTwo<java.lang.String>($new pkg.ExFour<java.lang.Number>());\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $public (java.lang.Object i){\n");
        xml_.append("  $if(i $instanceof pkg.ExThree){\n");
        xml_.append("   inst=1i;\n");
        xml_.append("  }\n");
        xml_.append("  $else{\n");
        xml_.append("   inst=2i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<E> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExFour<F> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        assertTrue(isInitialized(cont_));
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
    public void instanceArgument121Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExThree<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public $int ance=inst.$super.get(1I);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $normal $int get(T i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get(U i){\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument122Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExThree<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public java.lang.Number ance;\n");
        xml_.append(" {\n");
        xml_.append("  inst.get=3i;\n");
        xml_.append("  inst.$super.get=1i;\n");
        xml_.append("  ance=inst.$super.get;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T get;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U> :pkg.ExTwo<U>{\n");
        xml_.append(" $public U get;\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument123Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public $int ance=inst.$classchoice(pkg.ExTwo<?>)get();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U:java.lang.Number> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument124Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExThree<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public $int ance=(inst.$super.get(1I));\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $normal $int get(T i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get(U i){\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument125Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public $int ance=(inst.$classchoice(pkg.ExTwo<?>)get());\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U:java.lang.Number> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument126Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public $int ance=inst.$superaccess(pkg.ExTwo<java.lang.Number>)get();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U:java.lang.Number> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument127Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public $int ance=inst.$superaccess(pkg.ExTwo<java.lang.Number>)get(8I);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(T i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U:java.lang.Number> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get(U i){\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument128Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExThree<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public java.lang.Number ance;\n");
        xml_.append(" {\n");
        xml_.append("  inst.$superaccess(pkg.ExTwo<java.lang.Number>)get=3i;\n");
        xml_.append("  inst.get=inst.$superaccess(pkg.ExTwo<java.lang.Number>)get;\n");
        xml_.append("  ance=inst.getter();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T get;\n");
        xml_.append(" $public $normal T getter(){\n");
        xml_.append("  $return get;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U> :pkg.ExTwo<U>{\n");
        xml_.append(" $public U get;\n");
        xml_.append(" $public $normal U getter(){\n");
        xml_.append("  $return get;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(3, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument129Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExThree<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public java.lang.Number ance;\n");
        xml_.append(" {\n");
        xml_.append("  inst.$superaccess(pkg.ExThree<java.lang.Number>)get=3i;\n");
        xml_.append("  inst.get=inst.$superaccess(pkg.ExThree<java.lang.Number>)get;\n");
        xml_.append("  ance=inst.getter();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo<T> {\n");
        xml_.append(" $public T get;\n");
        xml_.append(" $public $normal T getter(){\n");
        xml_.append("  $return get;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U> :pkg.ExTwo<U>{\n");
        xml_.append(" $public U get;\n");
        xml_.append(" $public(){\n");
        xml_.append("  $interfaces(pkg.ExTwo)();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal U getter(){\n");
        xml_.append("  $return get;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(3, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument130Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExThree<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public java.lang.Number ance;\n");
        xml_.append(" {\n");
        xml_.append("  inst.$superaccess(pkg.ExThree<java.lang.Number>)get=3i;\n");
        xml_.append("  inst.get=inst.$superaccess(pkg.ExThree<java.lang.Number>)get;\n");
        xml_.append("  ance=inst.getter();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo<T> {\n");
        xml_.append(" $public T get;\n");
        xml_.append(" $public $abstract T getter(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U> :pkg.ExTwo<U>{\n");
        xml_.append(" $public U get;\n");
        xml_.append(" $public(){\n");
        xml_.append("  $interfaces(pkg.ExTwo)();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal U getter(){\n");
        xml_.append("  $return get;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(3, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument131Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExTwo<java.lang.Number>($new pkg.ExThree<java.lang.Number>());\n");
        xml_.append(" $public pkg.ExTwo<java.lang.String> ance=$new pkg.ExTwo<java.lang.String>($new pkg.ExFour<java.lang.Number>());\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $public (java.lang.Object i){\n");
        xml_.append("  $if(i $instanceof pkg.ExThree){\n");
        xml_.append("   inst=1i;\n");
        xml_.append("  }\n");
        xml_.append("  $else{\n");
        xml_.append("   inst=2i;\n");
        xml_.append("  }\n");
        xml_.append("  $if((inst $instanceof ExThree)){\n");
        xml_.append("   inst=inst;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<E> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExFour<F> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        assertTrue(isInitialized(cont_));
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
    public void instanceArgument132Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExTwo<java.lang.Number>($new pkg.ExThree<java.lang.Number>());\n");
        xml_.append(" $public pkg.ExTwo<java.lang.String> ance=$new pkg.ExTwo<java.lang.String>($new pkg.ExFour<java.lang.Number>());\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Int {\n");
        xml_.append(" $public $int value;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $public Int sec = $new Int();\n");
        xml_.append(" $public (java.lang.Object i){\n");
        xml_.append("  $if(i $instanceof pkg.ExThree){\n");
        xml_.append("   inst=1i;\n");
        xml_.append("  }\n");
        xml_.append("  $else{\n");
        xml_.append("   inst=2i;\n");
        xml_.append("  }\n");
        xml_.append("  $if((sec.value $instanceof ExThree)){\n");
        xml_.append("   inst=inst;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<E> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExFour<F> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        assertTrue(isInitialized(cont_));
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
    public void instanceArgument133Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExTwo<java.lang.Number>($new pkg.ExThree<java.lang.Number>());\n");
        xml_.append(" $public pkg.ExTwo<java.lang.String> ance=$new pkg.ExTwo<java.lang.String>($new pkg.ExFour<java.lang.Number>());\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Int {\n");
        xml_.append(" $public $int value;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $int inst;\n");
        xml_.append(" $public Int sec = $new Int();\n");
        xml_.append(" $public (java.lang.Object i){\n");
        xml_.append("  $if(i $instanceof pkg.ExThree){\n");
        xml_.append("   inst=1i;\n");
        xml_.append("  }\n");
        xml_.append("  $else{\n");
        xml_.append("   inst=2i;\n");
        xml_.append("  }\n");
        xml_.append("  $if((sec.value $instanceof pkg.ExThree)){\n");
        xml_.append("   inst=inst;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<E> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExFour<F> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        assertTrue(isInitialized(cont_));
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

    private static boolean isInitialized(ContextEl cont_) {
        return cont_.getLocks().getState("pkg.Ex") != InitClassState.NOT_YET;
    }

    @Test
    public void instanceArgument134Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public $int ance=inst.$thisaccess(pkg.ExTwo<java.lang.Number>)get();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U:java.lang.Number> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(3, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument135Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExThree<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public $int ance=inst.$thisaccess(pkg.ExTwo<java.lang.Number>)get();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U:java.lang.Number> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(3, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument136Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExTwo<java.lang.Number>();\n");
        xml_.append(" $public $int ance=inst.get(8I);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(T i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument137Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExThree<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public $int ance=inst.$thisaccess(pkg.ExTwo<java.lang.Number>)get();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U:java.lang.Number> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(3, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument138Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<$int> inst=$new pkg.ExTwo<$int>();\n");
        xml_.append(" $public $int ance=inst.get();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  T err = $null;\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        Argument arg_ = instanceError("pkg.Ex", null, id_, args_, cont_);
        assertEq(cont_.getStandards().getCoreNames().getAliasNullPe(),arg_.getStruct().getClassName(cont_));
    }
    @Test
    public void instanceArgument139Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<$int> inst=$new pkg.ExTwo<$int>();\n");
        xml_.append(" $public $int ance=inst.get();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $int res = 2i;\n");
        xml_.append("  $for(T err = $null;res==2i;){\n");
        xml_.append("  }\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        Argument arg_ = instanceError("pkg.Ex", null, id_, args_, cont_);
        assertEq(cont_.getStandards().getCoreNames().getAliasNullPe(),arg_.getStruct().getClassName(cont_));
    }
    @Test
    public void instanceArgument140Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<$int> inst=$new pkg.ExTwo<$int>();\n");
        xml_.append(" $public $int ance=inst.get();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  T err = (T)1i;\n");
        xml_.append("  $return ($int)err;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
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
        assertEq(1, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument141Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<$int> inst=$new pkg.ExTwo<$int>();\n");
        xml_.append(" $public $int ance=inst.get();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $int res = 0i;\n");
        xml_.append("  $int cum = 0i;\n");
        xml_.append("  $for(;;){\n");
        xml_.append("   $if (res>=2i){\n");
        xml_.append("    $break;\n");
        xml_.append("   }\n");
        xml_.append("   cum += ++res;\n");
        xml_.append("  }\n");
        xml_.append("  $return cum;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
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
        assertEq(3, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument142Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<$int> inst=$new pkg.ExTwo<$int>();\n");
        xml_.append(" $public $int ance=inst.value;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T value;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
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
    public void instanceArgument143Test() {
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
        xml_.append(" $public S value;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
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
    public void instanceArgument144Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public $int ance=inst.$thisaccess (pkg.ExTwo<java.lang.Number>)get();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U:java.lang.Number> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(3, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument145Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public $int ance=inst.$thisaccess( pkg.ExTwo<java.lang.Number>)get();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U:java.lang.Number> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(3, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument146Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public $int ance=inst.$thisaccess(pkg.ExTwo<java.lang.Number> )get();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U:java.lang.Number> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(3, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument147Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public $int ance=inst.$thisaccess(pkg.ExTwo<java.lang.Number>) get();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U:java.lang.Number> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(3, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument148Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public $int ance=inst.$thisaccess(pkg.ExTwo<java.lang.Number>)get ();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U:java.lang.Number> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(3, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument149Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExTwo<>();\n");
        xml_.append(" $public $int ance=inst.get(8I);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(T i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument150Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<>();\n");
        xml_.append(" $public $int ance=inst.$thisaccess(pkg.ExTwo<java.lang.Number> )get();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U:java.lang.Number> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(3, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument151Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=($new pkg.ExTwo<>());\n");
        xml_.append(" $public $int ance=inst.get(8I);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(T i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument152Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=method();\n");
        xml_.append(" $public $int ance=inst.get(8I);\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> method(){\n");
        xml_.append("  $return $new pkg.ExTwo<>();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(T i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument153Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=method($true);\n");
        xml_.append(" $public $int ance=inst.$thisaccess(pkg.ExTwo<java.lang.Number> )get();\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> instTwo=method($false);\n");
        xml_.append(" $public $int anceTwo=instTwo.$thisaccess(pkg.ExTwo<java.lang.Number> )get();\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> method($boolean p){\n");
        xml_.append("  $return $bool(p,$new pkg.ExFour<>(),$new pkg.ExThree<>());\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U:java.lang.Number> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExFour<U:java.lang.Number> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return 4i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExFour<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(4, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.Ex", "instTwo"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "anceTwo"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(3, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument154Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number>[] inst={$new pkg.ExThree<>()};\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> instElt=inst[0];\n");
        xml_.append(" $public $int ance=inst[0].$classchoice(pkg.ExTwo<?>)get();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U:java.lang.Number> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "instElt"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("[pkg.ExTwo<java.lang.Number>", field_.getClassName(cont_));
    }
    @Test
    public void instanceArgument1541Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number>[] inst=$new pkg.ExTwo<java.lang.Number>[]{$new pkg.ExThree<>()};\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> instElt=inst[0];\n");
        xml_.append(" $public $int ance=inst[0].$classchoice(pkg.ExTwo<?>)get();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U:java.lang.Number> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "instElt"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("[pkg.ExTwo<java.lang.Number>", field_.getClassName(cont_));
    }
    @Test
    public void instanceArgument155Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new ExTwo<>();\n");
        xml_.append(" $public $int ance=inst.get(8I);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(T i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument156Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number>[] arr=$new pkg.ExTwo<>[1];\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExTwo<java.lang.Number>();\n");
        xml_.append(" {\n");
        xml_.append("  arr[0]=inst;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int ance=arr[0].get(8I);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(T i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument157Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number>[][] arr={$new pkg.ExTwo<>[1]};\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExTwo<java.lang.Number>();\n");
        xml_.append(" {\n");
        xml_.append("  arr[0][0]=inst;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int ance=arr[0][0].get(8I);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(T i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument1571Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number>[][] arr=$new pkg.ExTwo<java.lang.Number>[][]{$new pkg.ExTwo<>[1]};\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExTwo<java.lang.Number>();\n");
        xml_.append(" {\n");
        xml_.append("  arr[0][0]=inst;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int ance=arr[0][0].get(8I);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(T i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument158Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number>[][] arr=$new pkg.ExTwo<>[1][];\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExTwo<java.lang.Number>();\n");
        xml_.append(" {\n");
        xml_.append("  arr[0]=$new pkg.ExTwo<>[1];\n");
        xml_.append("  arr[0][0]=inst;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int ance=arr[0][0].get(8I);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(T i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument159Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number>[] arrOne=method($true);\n");
        xml_.append(" {\n");
        xml_.append("  arrOne[0]=$new pkg.ExFour<>();\n");
        xml_.append(" }\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=arrOne[0];\n");
        xml_.append(" $public $int ance=inst.$thisaccess(pkg.ExTwo<java.lang.Number> )get();\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number>[] arrTwo=method($false);\n");
        xml_.append(" {\n");
        xml_.append("  arrTwo[0]=$new pkg.ExThree<>();\n");
        xml_.append(" }\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> instTwo=arrTwo[0];\n");
        xml_.append(" $public $int anceTwo=instTwo.$thisaccess(pkg.ExTwo<java.lang.Number> )get();\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number>[] method($boolean p){\n");
        xml_.append("  $return $bool(p,$new pkg.ExFour<>[1],$new pkg.ExThree<>[1]);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U:java.lang.Number> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExFour<U:java.lang.Number> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return 4i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExFour<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(4, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.Ex", "instTwo"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "anceTwo"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(3, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument160Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number>[][] arr={($new pkg.ExTwo<>[1])};\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExTwo<java.lang.Number>();\n");
        xml_.append(" {\n");
        xml_.append("  arr[0][0]=inst;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int ance=arr[0][0].get(8I);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(T i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument2FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public $int ance=inst.$superaccess(pkg.ExTwo)get(8I);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(T i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U:java.lang.Number> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get(U i){\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void instanceArgument4FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public $int ance=inst.$superaccess(pkg.ExTwo)get(1I);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $normal $int get(T i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get(U i){\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void instanceArgumentFailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExTwo<java.lang.Number>();\n");
        xml_.append(" $public $int ance=inst.get(8I);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(T i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int gettwo(T i){\n");
        xml_.append("  $return $static(pkg.ExTwo).get(i);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void instanceArgument5FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public java.lang.Number ance;\n");
        xml_.append(" {\n");
        xml_.append("  inst.$superaccess(pkg.ExThree<java.lang.Number>)get=3i;\n");
        xml_.append("  ance=inst.getter();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public T get;\n");
        xml_.append(" $public $normal T getter(){\n");
        xml_.append("  $return get;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U> :pkg.ExTwo<U>{\n");
        xml_.append(" $public U get;\n");
        xml_.append(" $public $normal U getter(){\n");
        xml_.append("  $return get;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void instanceArgument6FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public $int ance=inst.$thisaccess(pkg.ExThree<java.lang.Number>)get();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U:java.lang.Number> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void instanceArgument7FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new String<>();\n");
        xml_.append(" $public $int ance=inst.get(8I);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(T i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void instanceArgument8FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExTwo<java.lang.Number>();\n");
        xml_.append(" $public $int ance=inst.get(8I);\n");
        xml_.append(" {\n");
        xml_.append("  $var v = $new pkg.ExTwo<>();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(T i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void instanceArgument9FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExTwo<java.lang.Number>();\n");
        xml_.append(" $public $int ance=inst.get(8I);\n");
        xml_.append(" {\n");
        xml_.append("  ([v]) = $new pkg.ExTwo<>();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(T i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void instanceArgument10FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst;\n");
        xml_.append(" $public $int ance=inst.get(8I);\n");
        xml_.append(" {method();}\n");
        xml_.append(" $public $void method(){\n");
        xml_.append("  $return $new pkg.ExTwo<>();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(T i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void instanceArgument11FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst;\n");
        xml_.append(" $public $int ance=inst.get(8I);\n");
        xml_.append(" {method();}\n");
        xml_.append(" $public Ex(){\n");
        xml_.append("  $return $new pkg.ExTwo<>();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(T i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void instanceArgument12FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst;\n");
        xml_.append(" $public $int ance=inst.get(8I);\n");
        xml_.append(" {method();}\n");
        xml_.append(" $public $void method(){\n");
        xml_.append("  $bool($new pkg.ExTwo<>(),0,1);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(T i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void instanceArgument13FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst;\n");
        xml_.append(" $public $int ance=inst.get(8I);\n");
        xml_.append(" {method();}\n");
        xml_.append(" $public $void method(){\n");
        xml_.append("  $bool($new pkg.ExThree<>(),0,1);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(T i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void instanceArgument14FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst;\n");
        xml_.append(" $public $int ance=inst.get(8I);\n");
        xml_.append(" {method();}\n");
        xml_.append(" $public $void method(){\n");
        xml_.append("  $bool($new pkg.ExTwo<java.lang.Number>().$new ExThree<>(),0,1);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(T i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void instanceArgument15FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst;\n");
        xml_.append(" $public $int ance=inst.get(8I);\n");
        xml_.append(" {method();}\n");
        xml_.append(" $public $void method(){\n");
        xml_.append("  $new pkg.ExTwo<>();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(T i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void instanceArgument16FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst;\n");
        xml_.append(" $public $int ance=inst.get(8I);\n");
        xml_.append(" {$new $new pkg.ExTwo<>();}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(T i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void instanceArgument17FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst;\n");
        xml_.append(" $public $int ance=inst.get(8I);\n");
        xml_.append(" {$new $void<>();}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(T i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void instanceArgument18FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst={$new pkg.ExThree<>()};\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> instElt=inst[0];\n");
        xml_.append(" $public $int ance=inst[0].$classchoice(pkg.ExTwo<?>)get();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U:java.lang.Number> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void instanceArgument19FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst;\n");
        xml_.append(" $public $int ance=inst.get(8I);\n");
        xml_.append(" {$return $new pkg.ExTwo<>();}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(T i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void instanceArgument20FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number>[] arr={($new pkg.ExTwo<>[1])};\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(T i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void instanceArgument21FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number>[][][] arr={($new pkg.ExTwo<>[1])};\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(T i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void instanceArgument22FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" {$var arr={($new pkg.ExTwo<>[1])};}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(T i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void instanceArgument24FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" {$new pkg.ExTwo<>[1];}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(T i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void instanceArgument25FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" {$return $new pkg.ExTwo<>[1];}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(T i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void instanceArgument26FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $void m(){$return $new pkg.ExTwo<>[1];}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(T i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void instanceArgument27FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $void m(){$bool($new pkg.ExTwo<>[1],0,1);}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(T i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void instanceArgument28FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public ExTwo<Number> m(){\n");
        xml_.append("  $return $new ExThree<>[1];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(T i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void instanceArgument29FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $void m(){([v])=$new pkg.ExTwo<>[1];}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(T i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        assertTrue(hasErr(files_));
    }

    @Test
    public void instanceArgument30FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExTwo<java.lang.Number>();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public $normal T get(T i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        assertTrue(hasErr(files_));
    }
}
