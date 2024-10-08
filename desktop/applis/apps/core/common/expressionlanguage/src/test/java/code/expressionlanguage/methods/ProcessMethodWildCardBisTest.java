package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.structs.*;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessMethodWildCardBisTest extends ProcessMethodCommon {
    @Test
    public void instanceArgument126Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst;\n");
        xml_.append(" $public pkg.ExTwo<java.lang.String> ance;\n");
        xml_.append(" {\n");
        xml_.append("  $try{\n");
        xml_.append("   ance=$new pkg.ExTwo<java.lang.String>(8i);\n");
        xml_.append("  }\n");
        xml_.append("  $catch(code.expressionlanguage.exceptions.DynamicCastClassException e){\n");
        xml_.append("   inst=$new pkg.ExTwo<java.lang.Number>(8i);\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public java.lang.Object inst;\n");
        xml_.append(" $public (java.lang.Object i){\n");
        xml_.append("  inst=$(T)i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<java.lang.Number>", field_.getClassName(cont_));
        Struct subField_;
        subField_ = getField(field_, new ClassField("pkg.ExTwo", "inst"));
        assertEq(INTEGER, subField_.getClassName(cont_));
        assertEq(8, ((NumberStruct)subField_).intStruct());
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertSame(NullStruct.NULL_VALUE,field_);
    }

    @Test
    public void instanceArgument127Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" {\n");
        xml_.append("  $try{\n");
        xml_.append("   ance=inst.$classchoice(pkg.ExTwo<!java.lang.String>)get(\"\");\n");
        xml_.append("  }\n");
        xml_.append("  $catch(code.expressionlanguage.exceptions.DynamicCastClassException e){\n");
        xml_.append("   ance=2i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
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
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument130Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>(5i);\n");
        xml_.append(" $public $int ance=inst.get(8I);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number> {\n");
        xml_.append(" $public T inst;\n");
        xml_.append(" $public (T i){\n");
        xml_.append("  inst=i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(T i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U:java.lang.Number> :pkg.ExTwo<U>{\n");
        xml_.append(" $public (U i){\n");
        xml_.append("  $super(i);\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(U i){\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        Struct subField_;
        subField_ = getField(field_, new ClassField("pkg.ExTwo", "inst"));
        assertEq(5, ((NumberStruct)subField_).intStruct());
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(3, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument131Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public java.lang.Number ance;\n");
        xml_.append(" {\n");
        xml_.append("  $try{\n");
        xml_.append("   inst.$classchoice(pkg.ExThree<!java.lang.String>)get=\"\";\n");
        xml_.append("   inst.get=1i;\n");
        xml_.append("   ance=inst.getter();\n");
        xml_.append("  }\n");
        xml_.append("  $catch(code.expressionlanguage.exceptions.DynamicCastClassException e){\n");
        xml_.append("   ance=2i;\n");
        xml_.append("  }\n");
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
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument137Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" {\n");
        xml_.append("  $try{\n");
        xml_.append("   ance=inst.get(1i);\n");
        xml_.append("  }\n");
        xml_.append("  $catch(code.expressionlanguage.exceptions.DynamicCastClassException e){\n");
        xml_.append("   ance=2i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $normal $int get(T... i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get(U... i){\n");
        xml_.append("  $return 3i+i.length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(4, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument137_Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" {\n");
        xml_.append("  $try{\n");
        xml_.append("   ance=inst[1i];\n");
        xml_.append("  }\n");
        xml_.append("  $catch(code.expressionlanguage.exceptions.DynamicCastClassException e){\n");
        xml_.append("   ance=2i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $normal $int $this(T... i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void $this(T... i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int $this(U... i){\n");
        xml_.append("  $return 3i+i.length;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void $this(U... i){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(4, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument138Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" {\n");
        xml_.append("  $try{\n");
        xml_.append("   ance=inst.get(1i,6i);\n");
        xml_.append("  }\n");
        xml_.append("  $catch(code.expressionlanguage.exceptions.DynamicCastClassException e){\n");
        xml_.append("   ance=2i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $normal $int get(T... i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get(U... i){\n");
        xml_.append("  $return 3i+i.length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(5, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument139Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" {\n");
        xml_.append("  $try{\n");
        xml_.append("   ance=inst.get();\n");
        xml_.append("  }\n");
        xml_.append("  $catch(code.expressionlanguage.exceptions.DynamicCastClassException e){\n");
        xml_.append("   ance=2i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $normal $int get(T... i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get(U... i){\n");
        xml_.append("  $return 3i+i.length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(3, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument140Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" {\n");
        xml_.append("  $try{\n");
        xml_.append("   ance=inst.$classchoice(pkg.ExTwo<!java.lang.Number>)get(1i);\n");
        xml_.append("  }\n");
        xml_.append("  $catch(code.expressionlanguage.exceptions.DynamicCastClassException e){\n");
        xml_.append("   ance=2i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $normal $int get(T... i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get(U... i){\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument141Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" {\n");
        xml_.append("  $try{\n");
        xml_.append("   ance=inst.$classchoice(pkg.ExTwo<!java.lang.Number>)get($new java.lang.Number[]{1i,2i});\n");
        xml_.append("  }\n");
        xml_.append("  $catch(code.expressionlanguage.exceptions.DynamicCastClassException e){\n");
        xml_.append("   ance=2i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $normal $int get(T... i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get(U... i){\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument142Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" {\n");
        xml_.append("  $try{\n");
        xml_.append("   ance=((pkg.ExTwo<!java.lang.Number>)inst).get(1i);\n");
        xml_.append("  }\n");
        xml_.append("  $catch(code.expressionlanguage.exceptions.DynamicArrayStoreException e){\n");
        xml_.append("   ance=2i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $normal $int get(T... i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get(U... i){\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(3, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument1422Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" {\n");
        xml_.append("  $try{\n");
        xml_.append("   ance=((pkg.ExTwo<!java.lang.String>)inst).get(\"\");\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Object e){\n");
        xml_.append("   ance=2i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $normal $int get(T... i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get(U... i){\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument143Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" {\n");
        xml_.append("  $try{\n");
        xml_.append("   ance=inst.$classchoice(pkg.ExTwo<!java.lang.Object>)get(8i,\"\");\n");
        xml_.append("  }\n");
        xml_.append("  $catch(Exception e){\n");
        xml_.append("   ance=2i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $normal $int get(T... i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get(U... i){\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument143_Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" {\n");
        xml_.append("  $try{\n");
        xml_.append("   ance=inst.$classchoice(pkg.ExTwo<!java.lang.Object>)get(8i,\"\");\n");
        xml_.append("  }\n");
        xml_.append("  $catch(Exception e){\n");
        xml_.append("   ance=2i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $normal $int get(T... i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get(U... i){\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument1431Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {\n");
        xml_.append(" $public $static Ex<String> inst=$new Ex<>();\n");
        xml_.append(" $static{\n");
        xml_.append("  ExThree.inst++;\n");
        xml_.append(" }\n");
        xml_.append(" $public $int res(T v){\n");
        xml_.append("  $return ($int)inst+($int)v;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int inst;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return (pkg.Ex.inst).$classchoice(pkg.Ex)res(8);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void instanceArgument144Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" {\n");
        xml_.append("  $try{\n");
        xml_.append("   ance=inst.$classchoice(pkg.ExTwo<!java.lang.Number>)get($new java.lang.Number[]{$null});\n");
        xml_.append("  }\n");
        xml_.append("  $catch(code.expressionlanguage.exceptions.DynamicArrayStoreException e){\n");
        xml_.append("   ance=2i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $normal $int get(T... i){\n");
        xml_.append("  $return 3i+i.length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get(U... i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(4, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument1442Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" {\n");
        xml_.append("  $try{\n");
        xml_.append("   ance=inst.$classchoice(pkg.ExTwo<!java.lang.Number>)get($null);\n");
        xml_.append("  }\n");
        xml_.append("  $catch(code.util.exceptions.NullObjectException e){\n");
        xml_.append("   ance=2i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $normal $int get(T... i){\n");
        xml_.append("  $return 3i+i.length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get(U... i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument145Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" {\n");
        xml_.append("  $try{\n");
        xml_.append("   ance=inst.$classchoice(pkg.ExTwo<?>)get($null);\n");
        xml_.append("  }\n");
        xml_.append("  $catch(code.expressionlanguage.exceptions.DynamicArrayStoreException e){\n");
        xml_.append("   ance=2i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $normal $int get($int... i){\n");
        xml_.append("  $return 1i+$bool(i==$null,0i,-2i);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get($int... i){\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument146Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst;\n");
        xml_.append(" $public pkg.ExTwo<java.lang.String> ance;\n");
        xml_.append(" {\n");
        xml_.append("  $try{\n");
        xml_.append("   ance=$new pkg.ExTwo<java.lang.String>(8i);\n");
        xml_.append("  }\n");
        xml_.append("  $catch(code.expressionlanguage.exceptions.DynamicCastClassException e){\n");
        xml_.append("   inst=$new pkg.ExTwo<java.lang.Number>(8i);\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public java.lang.Object inst;\n");
        xml_.append(" $public (java.lang.Object i){\n");
        xml_.append("  inst=(T)i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<java.lang.Number>", field_.getClassName(cont_));
        Struct subField_;
        subField_ = getField(field_, new ClassField("pkg.ExTwo", "inst"));
        assertEq(INTEGER, subField_.getClassName(cont_));
        assertEq(8, ((NumberStruct)subField_).intStruct());
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertSame(NullStruct.NULL_VALUE,field_);
    }
    @Test
    public void instanceArgument147Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst;\n");
        xml_.append(" $public pkg.ExTwo<java.lang.String> ance;\n");
        xml_.append(" {\n");
        xml_.append("  $try{\n");
        xml_.append("   ance=$new pkg.ExTwo<java.lang.String>(8i);\n");
        xml_.append("  }\n");
        xml_.append("  $catch(code.expressionlanguage.exceptions.DynamicCastClassException e){\n");
        xml_.append("   inst=(ExTwo<java.lang.Number>)$new pkg.ExTwo<java.lang.Number>(8i);\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public java.lang.Object inst;\n");
        xml_.append(" $public (java.lang.Object i){\n");
        xml_.append("  inst=$(T)i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<java.lang.Number>", field_.getClassName(cont_));
        Struct subField_;
        subField_ = getField(field_, new ClassField("pkg.ExTwo", "inst"));
        assertEq(INTEGER, subField_.getClassName(cont_));
        assertEq(8, ((NumberStruct)subField_).intStruct());
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertSame(NullStruct.NULL_VALUE,field_);
    }
    @Test
    public void instanceArgument148Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number[]> inst;\n");
        xml_.append(" $public pkg.ExTwo<java.lang.String> ance;\n");
        xml_.append(" {\n");
        xml_.append("  $try{\n");
        xml_.append("   ance=$new pkg.ExTwo<java.lang.String>(8i);\n");
        xml_.append("  }\n");
        xml_.append("  $catch(code.expressionlanguage.exceptions.DynamicCastClassException e){\n");
        xml_.append("   inst=(ExTwo<java.lang.Number[]>)$new pkg.ExTwo<java.lang.Number[]>($new java.lang.Number[]{8i});\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public java.lang.Object inst;\n");
        xml_.append(" $public (java.lang.Object i){\n");
        xml_.append("  inst=$(T)i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<[java.lang.Number>", field_.getClassName(cont_));
        Struct subField_;
        subField_ = getField(field_, new ClassField("pkg.ExTwo", "inst"));
        assertEq("[java.lang.Number", subField_.getClassName(cont_));
        Struct[] arr_ = ((ArrayStruct) subField_).getInstance();
        assertEq(1, arr_.length);
        assertEq(8, ((NumberStruct) arr_[0]).intStruct());
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertSame(NullStruct.NULL_VALUE,field_);
    }

    @Test
    public void instanceArgument149Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" {\n");
        xml_.append("  $try{\n");
        xml_.append("   ance=inst.$superaccess(pkg.ExTwo<java.lang.Number>)get(1i);\n");
        xml_.append("  }\n");
        xml_.append("  $catch(code.expressionlanguage.exceptions.DynamicCastClassException e){\n");
        xml_.append("   ance=2i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $normal $int get(T... i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get(U... i){\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument150Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" {\n");
        xml_.append("  $try{\n");
        xml_.append("   ance=inst.$superaccess(pkg.ExTwo<java.lang.Number>)get($new java.lang.Number[]{1i,2i});\n");
        xml_.append("  }\n");
        xml_.append("  $catch(code.expressionlanguage.exceptions.DynamicCastClassException e){\n");
        xml_.append("   ance=2i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $normal $int get(T... i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get(U... i){\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument1FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" {\n");
        xml_.append("  $try{\n");
        xml_.append("   ance=inst.$superaccess(pkg.ExTwo<java.lang.Number>)get(\"\");\n");
        xml_.append("  }\n");
        xml_.append("  $catch(code.expressionlanguage.exceptions.DynamicArrayStoreException e){\n");
        xml_.append("   ance=2i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $normal $int get(T... i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get(U... i){\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void instanceArgument2FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" {\n");
        xml_.append("  $try{\n");
        xml_.append("   ance=inst.$superaccess(pkg.ExTwo<java.lang.Number>)get(8i,\"\");\n");
        xml_.append("  }\n");
        xml_.append("  $catch(code.expressionlanguage.exceptions.DynamicArrayStoreException e){\n");
        xml_.append("   ance=2i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $normal $int get(T... i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get(U... i){\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void instanceArgument162Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" {\n");
        xml_.append("  $try{\n");
        xml_.append("   ance=inst.$superaccess(pkg.ExTwo<java.lang.Number>)get($null);\n");
        xml_.append("  }\n");
        xml_.append("  $catch(code.expressionlanguage.exceptions.DynamicArrayStoreException e){\n");
        xml_.append("   ance=2i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $normal $int get(T... i){\n");
        xml_.append("  $return 3i+i.length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get(U... i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");
        assertEq(cont_.getStandards().getCoreNames().getAliasNullPe(),instanceError("pkg.Ex", id_, cont_).getClassName(cont_));
    }
    @Test
    public void instanceArgument163Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" {\n");
        xml_.append("  $try{\n");
        xml_.append("   ance=inst.$superaccess(pkg.ExTwo<java.lang.Number>)get($null);\n");
        xml_.append("  }\n");
        xml_.append("  $catch(code.expressionlanguage.exceptions.DynamicArrayStoreException e){\n");
        xml_.append("   ance=2i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $normal $int get($int... i){\n");
        xml_.append("  $return 1i+$bool(i==$null,0i,-2i);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get($int... i){\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument3FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>();\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" {\n");
        xml_.append("  $try{\n");
        xml_.append("   ance=inst.$superaccess(pkg.ExThree<java.lang.Number>)get(1i);\n");
        xml_.append("  }\n");
        xml_.append("  $catch(code.expressionlanguage.exceptions.DynamicCastClassException e){\n");
        xml_.append("   ance=2i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> {\n");
        xml_.append(" $public $normal $int get(T... i){\n");
        xml_.append("  $return 1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U> :pkg.ExTwo<U>{\n");
        xml_.append(" $public $normal $int get(U... i){\n");
        xml_.append("  $return 3i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void instanceArgument16FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int res;\n");
        xml_.append(" {\n");
        xml_.append("  $return {};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void instanceArgument17FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $void res()\n");
        xml_.append(" {\n");
        xml_.append("  $return {};\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
    @Test
    public void instanceArgument18FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $void res()\n");
        xml_.append(" {\n");
        xml_.append("  $bool({},5,6,7);$bool({},5,6);$bool(0,5,6);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
}
