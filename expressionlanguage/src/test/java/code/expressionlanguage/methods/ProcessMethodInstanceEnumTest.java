package code.expressionlanguage.methods;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.InitClassState;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.structs.CausingErrorStruct;
import code.expressionlanguage.structs.FieldableStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.StringMap;


public final class ProcessMethodInstanceEnumTest extends ProcessMethodCommon {

    @Test
    public void initializeClass1Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
    }

    @Test
    public void initializeClass2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
    }

    @Test
    public void initializeClass3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE:\n");
        xml_.append(" $public $int first=4i:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        Struct str_ = cont_.getClasses().getStaticField(new ClassField("pkg.Ex", "ONE"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "first"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(4, ((NumberStruct)field_).getInstance());
    }

    @Test
    public void initializeClass4Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE(4i):\n");
        xml_.append(" $public $int first:\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  first;;;=i;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        Struct str_ = cont_.getClasses().getStaticField(new ClassField("pkg.Ex", "ONE"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "first"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(4, ((NumberStruct)field_).getInstance());
    }

    @Test
    public void initializeClass5Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE(4i),\n");
        xml_.append(" TWO:\n");
        xml_.append(" $public $int first:\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  first;;;=i;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  first;;;=5i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        Struct str_ = cont_.getClasses().getStaticField(new ClassField("pkg.Ex", "ONE"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "first"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(4, ((NumberStruct)field_).getInstance());
        str_ = cont_.getClasses().getStaticField(new ClassField("pkg.Ex", "TWO"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "first"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(5, ((NumberStruct)field_).getInstance());
    }

    @Test
    public void initializeClass6Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE(4i),\n");
        xml_.append(" TWO:\n");
        xml_.append(" $public $int first:\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  first;;;=i;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  $this(5i):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        Struct str_ = cont_.getClasses().getStaticField(new ClassField("pkg.Ex", "ONE"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "first"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(4, ((NumberStruct)field_).getInstance());
        str_ = cont_.getClasses().getStaticField(new ClassField("pkg.Ex", "TWO"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "first"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(5, ((NumberStruct)field_).getInstance());
    }

    @Test
    public void initializeClass7Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE(4i),\n");
        xml_.append(" TWO:\n");
        xml_.append(" $public $int first:\n");
        xml_.append(" $public $static $int stFirst:\n");
        xml_.append(" $static {\n");
        xml_.append("  $try {\n");
        xml_.append("   stFirst;;;=1i/0i:\n");
        xml_.append("  } $catch (java.lang.Object e) {\n");
        xml_.append("   stFirst;;;++:\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  first;;;=i;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  $this(5i):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        Struct str_ = cont_.getClasses().getStaticField(new ClassField("pkg.Ex", "ONE"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "first"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(4, ((NumberStruct)field_).getInstance());
        str_ = cont_.getClasses().getStaticField(new ClassField("pkg.Ex", "TWO"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "first"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(5, ((NumberStruct)field_).getInstance());
        str_ = cont_.getClasses().getStaticField(new ClassField("pkg.Ex", "stFirst"));
        assertEq(INTEGER, str_.getClassName(cont_));
        assertEq(1, ((NumberStruct)str_).getInstance());
    }

    @Test
    public void initializeClass1FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE(4i),\n");
        xml_.append(" TWO:\n");
        xml_.append(" $public $int first:\n");
        xml_.append(" $public $static $int stFirst:\n");
        xml_.append(" $static {\n");
        xml_.append("   stFirst;;;=1i/0i:\n");
        xml_.append(" }\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  first;;;=i;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  $this(5i):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertTrue(!cont_.getClasses().isInitialized("pkg.Ex"));
        InitClassState state_ = cont_.getClasses().getLocks().getState("pkg.Ex");
        assertSame(InitClassState.NOT_YET, state_);
        ProcessMethod.initializeClass("pkg.Ex", cont_);
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        state_ = cont_.getClasses().getLocks().getState("pkg.Ex");
        assertSame(InitClassState.ERROR, state_);
        Struct exc_ = cont_.getException();
        assertTrue(exc_ instanceof CausingErrorStruct);
        CausingErrorStruct cause_ = (CausingErrorStruct) exc_;
        Struct c_ = cause_.getCause();
        assertEq("code.expressionlanguage.exceptions.DivideZeroException",c_.getClassName(cont_));
    }
}
