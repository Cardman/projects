package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.InitClassState;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.StringMap;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public final class ProcessMethodInnerElementTest extends ProcessMethodCommon {
    @Test
    public void calculate1Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {;\n");
        xml_.append("$public $enum ExInner {\n");
        xml_.append(" ONE{\n");
        xml_.append(" $public $class InnerInner{\n");
        xml_.append("  $public $int field = 6;\n");
        xml_.append(" }\n");
        xml_.append(" };\n");
        xml_.append("}\n");
        xml_.append("$public $static pkg.Ex.ExInner..ONE INSTANCE = ExInner.ONE;\n");
        xml_.append("$public $static pkg.Ex.ExInner..ONE.InnerInner INSTANCE2 = INSTANCE.$new InnerInner();\n");
        xml_.append("$public $static $int field = INSTANCE2.field;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner-ONE"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex..ExInner", "ONE"));
        assertEq("pkg.Ex..ExInner-ONE", str_.getClassName(cont_));
        Struct field_;
        field_ = getStaticField(cont_, new ClassField("pkg.Ex", "field"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(6, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void calculate2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {;\n");
        xml_.append("$public $enum ExInner {\n");
        xml_.append(" ONE{\n");
        xml_.append(" $public $class InnerInner<T>{\n");
        xml_.append("  $public T field = (T)6;\n");
        xml_.append(" }\n");
        xml_.append(" };\n");
        xml_.append("}\n");
        xml_.append("$public $static pkg.Ex.ExInner..ONE INSTANCE = ExInner.ONE;\n");
        xml_.append("$public $static pkg.Ex.ExInner..ONE.InnerInner<$int> INSTANCE2 = INSTANCE.$new InnerInner<>();\n");
        xml_.append("$public $static $int field = INSTANCE2.field;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner-ONE"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex..ExInner", "ONE"));
        assertEq("pkg.Ex..ExInner-ONE", str_.getClassName(cont_));
        Struct field_;
        field_ = getStaticField(cont_, new ClassField("pkg.Ex", "field"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(6, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void calculate3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {;\n");
        xml_.append("$public $enum ExInner {\n");
        xml_.append(" ONE{\n");
        xml_.append(" $public $class InnerInner<T>{\n");
        xml_.append("  $public T field = (T)6;\n");
        xml_.append(" }\n");
        xml_.append(" };\n");
        xml_.append("}\n");
        xml_.append("$public $static pkg.Ex.ExInner..ONE.InnerInner<$int> INSTANCE2 = ExInner.ONE.$new InnerInner<>();\n");
        xml_.append("$public $static $int field = INSTANCE2.field;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner-ONE"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex..ExInner", "ONE"));
        assertEq("pkg.Ex..ExInner-ONE", str_.getClassName(cont_));
        Struct field_;
        field_ = getStaticField(cont_, new ClassField("pkg.Ex", "field"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(6, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void calculate4Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {;\n");
        xml_.append("$public $enum ExInner {\n");
        xml_.append(" ONE{\n");
        xml_.append(" $public $class InnerInner<T>{\n");
        xml_.append("  $public $static $final $int CST = 6;\n");
        xml_.append(" }\n");
        xml_.append(" };\n");
        xml_.append("}\n");
        xml_.append("$public $static $int field = pkg.Ex.ExInner..ONE.InnerInner.CST;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner-ONE"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex..ExInner", "ONE"));
        assertEq("pkg.Ex..ExInner-ONE", str_.getClassName(cont_));
        Struct field_;
        field_ = getStaticField(cont_, new ClassField("pkg.Ex", "field"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(6, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void calculate5Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {;\n");
        xml_.append("$public $enum ExInner {\n");
        xml_.append(" ONE{\n");
        xml_.append(" $public $class InnerInner<T>{\n");
        xml_.append("  $public T field = (T)6;\n");
        xml_.append(" }\n");
        xml_.append(" };\n");
        xml_.append("}\n");
        xml_.append("$public $static pkg.Ex.ExInner..ONE.InnerInner<$int> INSTANCE2 =\n");
        xml_.append("(pkg.Ex.ExInner..ONE.InnerInner<$int>)\n");
        xml_.append("$class(pkg.Ex.ExInner..ONE.InnerInner<$int>).defaultInstance();\n");
        xml_.append("$public $static $int field = INSTANCE2.field;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner-ONE"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex..ExInner", "ONE"));
        assertEq("pkg.Ex..ExInner-ONE", str_.getClassName(cont_));
        Struct field_;
        field_ = getStaticField(cont_, new ClassField("pkg.Ex", "field"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(0, ((NumberStruct)field_).intStruct());
        field_ = getStaticField(cont_, new ClassField("pkg.Ex", "INSTANCE2"));
        assertEq("pkg.Ex..ExInner-ONE..InnerInner<$int>", field_.getClassName(cont_));
        assertEq("pkg.Ex..ExInner-ONE", field_.getParent().getClassName(cont_));
        assertSame(str_, field_.getParent());
    }
    @Test
    public void calculate6Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {;\n");
        xml_.append("$public $enum ExInner {\n");
        xml_.append(" ONE{\n");
        xml_.append(" $public $class InnerInner<T>{\n");
        xml_.append("  $public T field = (T)6;\n");
        xml_.append(" }\n");
        xml_.append(" };\n");
        xml_.append("}\n");
        xml_.append("$public $static pkg.Ex.ExInner..ONE.InnerInner<$int> INSTANCE2 =\n");
        xml_.append("(pkg.Ex.ExInner..ONE.InnerInner<$int>)\n");
        xml_.append("$class(pkg.Ex.ExInner..ONE.InnerInner<$int>).defaultInstance(ExInner.ONE);\n");
        xml_.append("$public $static $int field = INSTANCE2.field;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner-ONE"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex..ExInner", "ONE"));
        assertEq("pkg.Ex..ExInner-ONE", str_.getClassName(cont_));
        Struct field_;
        field_ = getStaticField(cont_, new ClassField("pkg.Ex", "field"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(0, ((NumberStruct)field_).intStruct());
        field_ = getStaticField(cont_, new ClassField("pkg.Ex", "INSTANCE2"));
        assertEq("pkg.Ex..ExInner-ONE..InnerInner<$int>", field_.getClassName(cont_));
        assertEq("pkg.Ex..ExInner-ONE", field_.getParent().getClassName(cont_));
        assertSame(str_, field_.getParent());
    }
    @Test
    public void calculate7Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {;\n");
        xml_.append("$public $enum ExInner {\n");
        xml_.append(" ONE{\n");
        xml_.append(" $public $class InnerInner<T>{\n");
        xml_.append("  $public T field;\n");
        xml_.append("  $public InnerInner(T p){\n");
        xml_.append("   field = p;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" };\n");
        xml_.append("}\n");
        xml_.append("$public $static pkg.Ex.ExInner..ONE.InnerInner<$int> INSTANCE2 = ExInner.ONE.$new InnerInner<>(6);\n");
        xml_.append("$public $static $int field = INSTANCE2.field;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner-ONE"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex..ExInner", "ONE"));
        assertEq("pkg.Ex..ExInner-ONE", str_.getClassName(cont_));
        Struct field_;
        field_ = getStaticField(cont_, new ClassField("pkg.Ex", "field"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(6, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void calculate8Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {;\n");
        xml_.append("$public $enum ExInner {\n");
        xml_.append(" ONE{\n");
        xml_.append(" $public $class InnerInner<T>{\n");
        xml_.append("  $public T field;\n");
        xml_.append("  $public InnerInner(T p){\n");
        xml_.append("   field = p;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" };\n");
        xml_.append("}\n");
        xml_.append("$public $static pkg.Ex.ExInner..ONE.InnerInner<$int> INSTANCE2 = ExInner.ONE.$lambda(InnerInner<$int>,$new,$int).call(6);\n");
        xml_.append("$public $static $int field = INSTANCE2.field;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner-ONE"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex..ExInner", "ONE"));
        assertEq("pkg.Ex..ExInner-ONE", str_.getClassName(cont_));
        Struct field_;
        field_ = getStaticField(cont_, new ClassField("pkg.Ex", "field"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(6, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void calculate9Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {;\n");
        xml_.append("$public $enum ExInner {\n");
        xml_.append(" ONE{\n");
        xml_.append(" $public $class InnerInner<T>{\n");
        xml_.append("  $public T field;\n");
        xml_.append("  $public InnerInner(T p){\n");
        xml_.append("   field = p;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" };\n");
        xml_.append("}\n");
        xml_.append("$public $static pkg.Ex.ExInner..ONE.InnerInner<$int> INSTANCE2 = $lambda(ExInner..ONE.InnerInner<$int>,$new,$int).call(ExInner.ONE,6);\n");
        xml_.append("$public $static $int field = INSTANCE2.field;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner-ONE"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex..ExInner", "ONE"));
        assertEq("pkg.Ex..ExInner-ONE", str_.getClassName(cont_));
        Struct field_;
        field_ = getStaticField(cont_, new ClassField("pkg.Ex", "field"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(6, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void calculate10Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {;\n");
        xml_.append("$public $enum ExInner<S> {\n");
        xml_.append(" ONE<String>{\n");
        xml_.append(" $public $class InnerInner<T>{\n");
        xml_.append("  $public T field = (T)6;\n");
        xml_.append(" }\n");
        xml_.append(" };\n");
        xml_.append("}\n");
        xml_.append("$public $static pkg.Ex.ExInner..ONE.InnerInner<$int> INSTANCE2 =\n");
        xml_.append("(pkg.Ex.ExInner..ONE.InnerInner<$int>)\n");
        xml_.append("$class(pkg.Ex.ExInner..ONE.InnerInner<$int>).defaultInstance();\n");
        xml_.append("$public $static $int field = INSTANCE2.field;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner-ONE"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex..ExInner", "ONE"));
        assertEq("pkg.Ex..ExInner-ONE", str_.getClassName(cont_));
        Struct field_;
        field_ = getStaticField(cont_, new ClassField("pkg.Ex", "field"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(0, ((NumberStruct)field_).intStruct());
        field_ = getStaticField(cont_, new ClassField("pkg.Ex", "INSTANCE2"));
        assertEq("pkg.Ex..ExInner-ONE..InnerInner<$int>", field_.getClassName(cont_));
        assertEq("pkg.Ex..ExInner-ONE", field_.getParent().getClassName(cont_));
        assertSame(str_, field_.getParent());
    }
    @Test
    public void calculate11Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {;\n");
        xml_.append("$public $enum ExInner<S> {\n");
        xml_.append(" ONE<String>{\n");
        xml_.append(" $public $class InnerInner<T>{\n");
        xml_.append("  $public T field = (T)6;\n");
        xml_.append(" }\n");
        xml_.append(" };\n");
        xml_.append("}\n");
        xml_.append("$public $static pkg.Ex.ExInner..ONE.InnerInner<$int> INSTANCE2 =\n");
        xml_.append("(pkg.Ex.ExInner..ONE.InnerInner<$int>)\n");
        xml_.append("$class(pkg.Ex.ExInner..ONE.InnerInner<$int>).defaultInstance(ExInner.ONE);\n");
        xml_.append("$public $static $int field = INSTANCE2.field;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner-ONE"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex..ExInner", "ONE"));
        assertEq("pkg.Ex..ExInner-ONE", str_.getClassName(cont_));
        Struct field_;
        field_ = getStaticField(cont_, new ClassField("pkg.Ex", "field"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(0, ((NumberStruct)field_).intStruct());
        field_ = getStaticField(cont_, new ClassField("pkg.Ex", "INSTANCE2"));
        assertEq("pkg.Ex..ExInner-ONE..InnerInner<$int>", field_.getClassName(cont_));
        assertEq("pkg.Ex..ExInner-ONE", field_.getParent().getClassName(cont_));
        assertSame(str_, field_.getParent());
    }
}
