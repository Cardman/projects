package code.expressionlanguage.methods;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.structs.FieldableStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringMap;


public final class ProcessMethodInstanceCoreTest extends ProcessMethodCommon {

    @Test
    public void instanceArgument1Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
    }


    @Test
    public void instanceArgument2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst=2i:\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).getInstance());
    }

    @Test
    public void instanceArgument3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public (){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
    }

    @Test
    public void instanceArgument4Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst=2i:\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  inst;;;=i;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        Argument arg_;
        arg_ = new Argument();
        arg_.setObject(8);
        args_.add(arg_);
        ConstructorId id_ = getConstructorId("pkg.Ex",cont_.getStandards().getAliasPrimInteger());

        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(8, ((NumberStruct)field_).getInstance());
    }

    @Test
    public void instanceArgument5Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst=2i:\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  inst;;;=i;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  $this(0i):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        Argument arg_;
        arg_ = new Argument();
        arg_.setObject(8);
        args_.add(arg_);
        ConstructorId id_ = getConstructorId("pkg.Ex",cont_.getStandards().getAliasPrimInteger());

        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(8, ((NumberStruct)field_).getInstance());
    }

    @Test
    public void instanceArgument6Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst=2i:\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  inst;;;=i;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  $this(4i):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(4, ((NumberStruct)field_).getInstance());
    }

    @Test
    public void instanceArgument7Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst=2i:\n");
        xml_.append(" $public $int ance=3i:\n");
        xml_.append(" $public ($int i,$int j){\n");
        xml_.append("  inst;;;=i;.;:\n");
        xml_.append("  ance;;;=j;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  $this(5i,i;.;):\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  $this(4i):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(5, ((NumberStruct)field_).getInstance());
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(4, ((NumberStruct)field_).getInstance());
    }

    @Test
    public void instanceArgument8Test() {
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
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextEl();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(5, ((NumberStruct)field_).getInstance());
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(4, ((NumberStruct)field_).getInstance());
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "field"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(6, ((NumberStruct)field_).getInstance());
    }
}
