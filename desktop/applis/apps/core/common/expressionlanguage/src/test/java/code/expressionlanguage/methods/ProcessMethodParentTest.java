package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.InitClassState;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.StringMap;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertSame;

public final class ProcessMethodParentTest extends ProcessMethodCommon {
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
        xml_.append("$public $static pkg.Ex.ExInner..ONE PARENT = INSTANCE2.$parent;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = ctx();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(isEmptyErrors(cont_));
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner-ONE"));
        Struct str_ = cont_.getClasses().getStaticField(new ClassField("pkg.Ex..ExInner", "ONE"));
        assertEq("pkg.Ex..ExInner-ONE", str_.getClassName(cont_));
        str_ = cont_.getClasses().getStaticField(new ClassField("pkg.Ex", "PARENT"));
        Struct exp_ = cont_.getClasses().getStaticField(new ClassField("pkg.Ex", "INSTANCE2"));
        assertSame(exp_.getParent(), str_);
    }
    @Test
    public void calculate2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {;\n");
        xml_.append("$public $enum ExInner {\n");
        xml_.append(" ONE{\n");
        xml_.append(" $public $class InnerInner{\n");
        xml_.append("  $public $int field = 6;\n");
        xml_.append("  $public pkg.Ex.ExInner..ONE parent = $parent;\n");
        xml_.append(" }\n");
        xml_.append(" };\n");
        xml_.append("}\n");
        xml_.append("$public $static pkg.Ex.ExInner..ONE INSTANCE = ExInner.ONE;\n");
        xml_.append("$public $static pkg.Ex.ExInner..ONE.InnerInner INSTANCE2 = INSTANCE.$new InnerInner();\n");
        xml_.append("$public $static pkg.Ex.ExInner..ONE PARENT = INSTANCE2.parent;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = ctx();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(isEmptyErrors(cont_));
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner-ONE"));
        Struct str_ = cont_.getClasses().getStaticField(new ClassField("pkg.Ex..ExInner", "ONE"));
        assertEq("pkg.Ex..ExInner-ONE", str_.getClassName(cont_));
        str_ = cont_.getClasses().getStaticField(new ClassField("pkg.Ex", "PARENT"));
        Struct exp_ = cont_.getClasses().getStaticField(new ClassField("pkg.Ex", "INSTANCE2"));
        assertSame(exp_.getParent(), str_);
    }
    @Test
    public void calculate3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {;\n");
        xml_.append("$public $enum ExInner {\n");
        xml_.append(" ONE{\n");
        xml_.append(" $public $class InnerInner<T>{\n");
        xml_.append("  $public T field;\n");
        xml_.append("  $public Object parent = field.$parent;\n");
        xml_.append(" }\n");
        xml_.append(" };\n");
        xml_.append("}\n");
        xml_.append("$public $static pkg.Ex.ExInner..ONE INSTANCE = ExInner.ONE;\n");
        xml_.append("$public $static pkg.Ex.ExInner..ONE.InnerInner<$int> INSTANCE2 = INSTANCE.$new InnerInner<>();\n");
        xml_.append("$public $static Object PARENT = INSTANCE2.parent;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = ctx();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(isEmptyErrors(cont_));
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner-ONE"));
        Struct str_ = cont_.getClasses().getStaticField(new ClassField("pkg.Ex..ExInner", "ONE"));
        assertEq("pkg.Ex..ExInner-ONE", str_.getClassName(cont_));
        str_ = cont_.getClasses().getStaticField(new ClassField("pkg.Ex", "PARENT"));
        assertSame(NullStruct.NULL_VALUE, str_);
    }
    @Test
    public void calculate4Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Int1:Int3:Int4 {}\n");
        xml_.append("$public $interface pkg.Int2:Int3:Int4 {}\n");
        xml_.append("$public $interface pkg.Int3 {}\n");
        xml_.append("$public $interface pkg.Int4 {}\n");
        xml_.append("$public $enum pkg.Ex {;\n");
        xml_.append("$public $enum ExInner {\n");
        xml_.append(" ONE{\n");
        xml_.append(" $public $class InnerInner<T>{\n");
        xml_.append("  $public T field;\n");
        xml_.append("  $public Object parent = (field==0?(Int1)$null:(Int2)$null).$parent;\n");
        xml_.append(" }\n");
        xml_.append(" };\n");
        xml_.append("}\n");
        xml_.append("$public $static pkg.Ex.ExInner..ONE INSTANCE = ExInner.ONE;\n");
        xml_.append("$public $static pkg.Ex.ExInner..ONE.InnerInner<$int> INSTANCE2 = INSTANCE.$new InnerInner<>();\n");
        xml_.append("$public $static Object PARENT = INSTANCE2.parent;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = ctx();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(isEmptyErrors(cont_));
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner-ONE"));
        Struct str_ = cont_.getClasses().getStaticField(new ClassField("pkg.Ex..ExInner", "ONE"));
        assertEq("pkg.Ex..ExInner-ONE", str_.getClassName(cont_));
        str_ = cont_.getClasses().getStaticField(new ClassField("pkg.Ex", "PARENT"));
        assertSame(NullStruct.NULL_VALUE, str_);
    }
    @Test
    public void calculate5Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Int1:Int3:Int4 {}\n");
        xml_.append("$public $interface pkg.Int2:Int3:Int4 {}\n");
        xml_.append("$public $interface pkg.Int3 {}\n");
        xml_.append("$public $interface pkg.Int4 {}\n");
        xml_.append("$public $enum pkg.Ex {;\n");
        xml_.append("$public $enum ExInner {\n");
        xml_.append(" ONE{\n");
        xml_.append(" $public $class InnerInner<T>{\n");
        xml_.append("  $public T field;\n");
        xml_.append("  $public Object parent = $new Object[0].$parent;\n");
        xml_.append(" }\n");
        xml_.append(" };\n");
        xml_.append("}\n");
        xml_.append("$public $static pkg.Ex.ExInner..ONE INSTANCE = ExInner.ONE;\n");
        xml_.append("$public $static pkg.Ex.ExInner..ONE.InnerInner<$int> INSTANCE2 = INSTANCE.$new InnerInner<>();\n");
        xml_.append("$public $static Object PARENT = INSTANCE2.parent;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = ctx();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(isEmptyErrors(cont_));
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner-ONE"));
        Struct str_ = cont_.getClasses().getStaticField(new ClassField("pkg.Ex..ExInner", "ONE"));
        assertEq("pkg.Ex..ExInner-ONE", str_.getClassName(cont_));
        str_ = cont_.getClasses().getStaticField(new ClassField("pkg.Ex", "PARENT"));
        assertSame(NullStruct.NULL_VALUE, str_);
    }
    @Test
    public void calculate6Test() {
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
        xml_.append("$public $static pkg.Ex.ExInner..ONE PARENT = $lambda(pkg.Ex.ExInner..ONE.InnerInner,,$parent).call(INSTANCE2);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = ctx();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(isEmptyErrors(cont_));
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner-ONE"));
        Struct str_ = cont_.getClasses().getStaticField(new ClassField("pkg.Ex..ExInner", "ONE"));
        assertEq("pkg.Ex..ExInner-ONE", str_.getClassName(cont_));
        str_ = cont_.getClasses().getStaticField(new ClassField("pkg.Ex", "PARENT"));
        Struct exp_ = cont_.getClasses().getStaticField(new ClassField("pkg.Ex", "INSTANCE2"));
        assertSame(exp_.getParent(), str_);
    }
    @Test
    public void calculate7Test() {
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
        xml_.append("$public $static pkg.Ex.ExInner..ONE PARENT = INSTANCE2.$lambda(pkg.Ex.ExInner..ONE.InnerInner,,$parent).call();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = ctx();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(isEmptyErrors(cont_));
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner-ONE"));
        Struct str_ = cont_.getClasses().getStaticField(new ClassField("pkg.Ex..ExInner", "ONE"));
        assertEq("pkg.Ex..ExInner-ONE", str_.getClassName(cont_));
        str_ = cont_.getClasses().getStaticField(new ClassField("pkg.Ex", "PARENT"));
        Struct exp_ = cont_.getClasses().getStaticField(new ClassField("pkg.Ex", "INSTANCE2"));
        assertSame(exp_.getParent(), str_);
    }
    @Test
    public void calculate8Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Int1:Int3:Int4 {}\n");
        xml_.append("$public $interface pkg.Int2:Int3:Int4 {}\n");
        xml_.append("$public $interface pkg.Int3 {}\n");
        xml_.append("$public $interface pkg.Int4 {}\n");
        xml_.append("$public $enum pkg.Ex {;\n");
        xml_.append("$public $enum ExInner {\n");
        xml_.append(" ONE{\n");
        xml_.append(" $public $class InnerInner<T>{\n");
        xml_.append("  $public T field;\n");
        xml_.append("  $public Object parent = $lambda(Object[],,$parent).call($new Object[0]);\n");
        xml_.append(" }\n");
        xml_.append(" };\n");
        xml_.append("}\n");
        xml_.append("$public $static pkg.Ex.ExInner..ONE INSTANCE = ExInner.ONE;\n");
        xml_.append("$public $static pkg.Ex.ExInner..ONE.InnerInner<$int> INSTANCE2 = INSTANCE.$new InnerInner<>();\n");
        xml_.append("$public $static Object PARENT = $lambda(pkg.Ex.ExInner..ONE.InnerInner<$int>,,parent).call(INSTANCE2);\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = ctx();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(isEmptyErrors(cont_));
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner-ONE"));
        Struct str_ = cont_.getClasses().getStaticField(new ClassField("pkg.Ex..ExInner", "ONE"));
        assertEq("pkg.Ex..ExInner-ONE", str_.getClassName(cont_));
        str_ = cont_.getClasses().getStaticField(new ClassField("pkg.Ex", "PARENT"));
        assertSame(NullStruct.NULL_VALUE, str_);
    }
    @Test
    public void calculate9Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Int1:Int3:Int4 {}\n");
        xml_.append("$public $interface pkg.Int2:Int3:Int4 {}\n");
        xml_.append("$public $interface pkg.Int3 {}\n");
        xml_.append("$public $interface pkg.Int4 {}\n");
        xml_.append("$public $enum pkg.Ex {;\n");
        xml_.append("$public $enum ExInner {\n");
        xml_.append(" ONE{\n");
        xml_.append(" $public $class InnerInner<T>{\n");
        xml_.append("  $public T field;\n");
        xml_.append("  $public Object parent = $new Object[0].$lambda(Object[],,$parent).call();\n");
        xml_.append(" }\n");
        xml_.append(" };\n");
        xml_.append("}\n");
        xml_.append("$public $static pkg.Ex.ExInner..ONE INSTANCE = ExInner.ONE;\n");
        xml_.append("$public $static pkg.Ex.ExInner..ONE.InnerInner<$int> INSTANCE2 = INSTANCE.$new InnerInner<>();\n");
        xml_.append("$public $static Object PARENT = INSTANCE2.$lambda(pkg.Ex.ExInner..ONE.InnerInner<$int>,,parent).call();\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = ctx();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(isEmptyErrors(cont_));
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner-ONE"));
        Struct str_ = cont_.getClasses().getStaticField(new ClassField("pkg.Ex..ExInner", "ONE"));
        assertEq("pkg.Ex..ExInner-ONE", str_.getClassName(cont_));
        str_ = cont_.getClasses().getStaticField(new ClassField("pkg.Ex", "PARENT"));
        assertSame(NullStruct.NULL_VALUE, str_);
    }
    @Test
    public void calculate10Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {;\n");
        xml_.append("$public $enum ExInner {\n");
        xml_.append(" ONE{\n");
        xml_.append(" $public $class InnerInner<T>{\n");
        xml_.append("  $public T field;\n");
        xml_.append("  $public Object parent = $lambda(T,,$parent).call(field);\n");
        xml_.append(" }\n");
        xml_.append(" };\n");
        xml_.append("}\n");
        xml_.append("$public $static pkg.Ex.ExInner..ONE INSTANCE = ExInner.ONE;\n");
        xml_.append("$public $static pkg.Ex.ExInner..ONE.InnerInner<$int> INSTANCE2 = INSTANCE.$new InnerInner<>();\n");
        xml_.append("$public $static Object PARENT = INSTANCE2.parent;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = ctx();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(isEmptyErrors(cont_));
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner-ONE"));
        Struct str_ = cont_.getClasses().getStaticField(new ClassField("pkg.Ex..ExInner", "ONE"));
        assertEq("pkg.Ex..ExInner-ONE", str_.getClassName(cont_));
        str_ = cont_.getClasses().getStaticField(new ClassField("pkg.Ex", "PARENT"));
        assertSame(NullStruct.NULL_VALUE, str_);
    }
    @Test
    public void calculate11Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {;\n");
        xml_.append("$public $enum ExInner {\n");
        xml_.append(" ONE{\n");
        xml_.append(" $public $class InnerInner<T>{\n");
        xml_.append("  $public T field;\n");
        xml_.append("  $public Object parent = field.$lambda(T,,$parent).call();\n");
        xml_.append(" }\n");
        xml_.append(" };\n");
        xml_.append("}\n");
        xml_.append("$public $static pkg.Ex.ExInner..ONE INSTANCE = ExInner.ONE;\n");
        xml_.append("$public $static pkg.Ex.ExInner..ONE.InnerInner<$int> INSTANCE2 = INSTANCE.$new InnerInner<>();\n");
        xml_.append("$public $static Object PARENT = INSTANCE2.parent;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = ctx();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(isEmptyErrors(cont_));
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner"));
        assertTrue(isInitialized(cont_, "pkg.Ex..ExInner-ONE"));
        Struct str_ = cont_.getClasses().getStaticField(new ClassField("pkg.Ex..ExInner", "ONE"));
        assertEq("pkg.Ex..ExInner-ONE", str_.getClassName(cont_));
        str_ = cont_.getClasses().getStaticField(new ClassField("pkg.Ex", "PARENT"));
        assertSame(NullStruct.NULL_VALUE, str_);
    }

    private static boolean isInitialized(ContextEl cont_, String _cl) {
        return cont_.getLocks().getState(_cl) != InitClassState.NOT_YET;
    }
}
