package code.expressionlanguage.methods;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.Struct;
import code.util.CustList;
import code.util.StringMap;

@SuppressWarnings("static-method")
public final class ProcessMethodInstanceSimpleInheritTest extends ProcessMethodCommon {
    @Test
    public void instanceArgument9Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i:\n");
        xml_.append(" $public $int ance=3i:\n");
        xml_.append(" $public $int field=1i:\n");
        xml_.append(" $public ($int i,$int j){\n");
        xml_.append("  inst;;;=i;.;:\n");
        xml_.append("  ance;;;=j;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  $this(5i,i;.;):\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  $this(4i):\n");
        xml_.append("  field;;;=6i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int sec:\n");
        xml_.append(" $public (){\n");
        xml_.append("  sec;;;=7i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(5, (Number)field_.getInstance());
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(4, (Number)field_.getInstance());
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "field"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(6, (Number)field_.getInstance());
        field_ = str_.getFields().getVal(new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(7, (Number)field_.getInstance());
    }

    @Test
    public void instanceArgument10Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst=2i:\n");
        xml_.append(" $public $int ance=3i:\n");
        xml_.append(" $public $int field=1i:\n");
        xml_.append(" $public ($int i,$int j){\n");
        xml_.append("  inst;;;=i;.;:\n");
        xml_.append("  ance;;;=j;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  $this(5i,i;.;):\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  $this(4i):\n");
        xml_.append("  field;;;=6i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{\n");
        xml_.append(" $public $int sec:\n");
        xml_.append(" $public (){\n");
        xml_.append("  sec;;;=7i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(5, (Number)field_.getInstance());
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(4, (Number)field_.getInstance());
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "field"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(6, (Number)field_.getInstance());
    }

    @Test
    public void instanceArgument11Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i:\n");
        xml_.append(" $public $int ance=3i:\n");
        xml_.append(" $public $int field=1i:\n");
        xml_.append(" $public ($int i,$int j){\n");
        xml_.append("  $super(7i):\n");
        xml_.append("  inst;;;=i;.;:\n");
        xml_.append("  ance;;;=j;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  $this(5i,i;.;):\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  $this(4i):\n");
        xml_.append("  field;;;=6i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int sec:\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  sec;;;=i;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(5, (Number)field_.getInstance());
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(4, (Number)field_.getInstance());
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "field"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(6, (Number)field_.getInstance());
        field_ = str_.getFields().getVal(new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(7, (Number)field_.getInstance());
    }

    @Test
    public void instanceArgument12Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int sec:\n");
        xml_.append(" $public (){\n");
        xml_.append("  sec;;;=8i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(8, (Number)field_.getInstance());
    }

    @Test
    public void instanceArgument13Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public (){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int sec:\n");
        xml_.append(" $public (){\n");
        xml_.append("  sec;;;=8i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(8, (Number)field_.getInstance());
    }

    @Test
    public void instanceArgument14Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i:\n");
        xml_.append(" $public (){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int sec:\n");
        xml_.append(" $public (){\n");
        xml_.append("  sec;;;=8i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, (Number)field_.getInstance());
        field_ = str_.getFields().getVal(new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(8, (Number)field_.getInstance());
    }

    @Test
    public void instanceArgument15Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i:\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int sec:\n");
        xml_.append(" $public (){\n");
        xml_.append("  sec;;;=8i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, (Number)field_.getInstance());
        field_ = str_.getFields().getVal(new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(8, (Number)field_.getInstance());
    }

    @Test
    public void instanceArgument16Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i:\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int sec:\n");
        xml_.append(" {\n");
        xml_.append("  sec;;;+=8i:\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  sec;;;+=16i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, (Number)field_.getInstance());
        field_ = str_.getFields().getVal(new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(24, (Number)field_.getInstance());
    }
    @Test
    public void instanceArgument17Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i:\n");
        xml_.append(" $public $int ance=3i:\n");
        xml_.append(" $public $int field=1i:\n");
        xml_.append(" $public ($int i,$int j){\n");
        xml_.append("  inst;;;=i;.;:\n");
        xml_.append("  ance;;;=j;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  $this(5i,i;.;):\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  $this(4i):\n");
        xml_.append("  field;;;=6:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int sec:\n");
        xml_.append(" $public (){\n");
        xml_.append("  sec;;;=7i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(5, (Number)field_.getInstance());
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(4, (Number)field_.getInstance());
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "field"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(6, (Number)field_.getInstance());
        field_ = str_.getFields().getVal(new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(7, (Number)field_.getInstance());
    }
    @Test
    public void instanceArgument1FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i:\n");
        xml_.append(" $public $int ance=3i:\n");
        xml_.append(" $public $int field=1i:\n");
        xml_.append(" $public ($int i,$int j){\n");
        xml_.append("  inst;;;=i;.;:\n");
        xml_.append("  ance;;;=j;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  $this(5i,i;.;):\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  $this(4i):\n");
        xml_.append("  $long value = 6:\n");
        xml_.append("  field;;;=value;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int sec:\n");
        xml_.append(" $public (){\n");
        xml_.append("  sec;;;=7i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
}
