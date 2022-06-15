package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;


public final class ProcessMethodInstanceCoreTest extends ProcessMethodCommon {

    @Test
    public void instanceArgument1Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
    }


    @Test
    public void instanceArgument2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
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
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public (){}\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormalCtor("pkg.Ex", null, id_, args_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
    }

    @Test
    public void instanceArgument4Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  inst=i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        Argument arg_;
        arg_ = new Argument();
        arg_.setStruct(new IntStruct(8));
        args_.add(arg_);
        ConstructorId id_ = getConstructorId("pkg.Ex", cont_.getStandards().getContent().getPrimTypes().getAliasPrimInteger());

        Argument ret_;
        ret_ = instanceNormalCtorParam("pkg.Ex", null, id_, args_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(8, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument5Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  inst=i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  $this(0i);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        Argument arg_;
        arg_ = new Argument();
        arg_.setStruct(new IntStruct(8));
        args_.add(arg_);
        ConstructorId id_ = getConstructorId("pkg.Ex", cont_.getStandards().getContent().getPrimTypes().getAliasPrimInteger());

        Argument ret_;
        ret_ = instanceNormalCtorParam("pkg.Ex", null, id_, args_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(8, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument6Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  inst=i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  $this(4i);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();

        Argument ret_;
        ret_ = instanceNormalCtor("pkg.Ex", null, 1, args_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(4, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument7Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append(" $public $int ance=3i;\n");
        xml_.append(" $public ($int i,$int j){\n");
        xml_.append("  inst=i;\n");
        xml_.append("  ance=j;\n");
        xml_.append(" }\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  $this(5i,i);\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  $this(4i);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();

        Argument ret_;
        ret_ = instanceNormalCtor("pkg.Ex", null, 2, args_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(5, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(4, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument8Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append(" $public $int ance=3i;\n");
        xml_.append(" $public $int field=1i;\n");
        xml_.append(" $public ($int i,$int j){\n");
        xml_.append("  inst=i;\n");
        xml_.append("  ance=j;\n");
        xml_.append(" }\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  $this(5i,i);\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  $this(4i);\n");
        xml_.append("  field=6i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();

        Argument ret_;
        ret_ = instanceNormalCtor("pkg.Ex", null, 2, args_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(5, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(4, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.Ex", "field"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(6, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument9Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int st;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Ex res = $new {} Ex();\n");
        xml_.append(" $public $static Ex exec(){\n");
        xml_.append("  $return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.st++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_,"pkg.Apply");
        CustList<Argument> args_ = new CustList<Argument>();
        Argument ret_;
        ret_ = calculateNormal("pkg.Apply", getMethodId("exec"),args_,cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument10Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $final $int inst;\n");
        xml_.append(" {\n");
        xml_.append("  $int loc=0;\n");
        xml_.append("  $int i=0;\n");
        xml_.append("  $while($true){\n");
        xml_.append("   $if(i>=5){\n");
        xml_.append("    inst = loc;\n");
        xml_.append("    $break;\n");
        xml_.append("   }\n");
        xml_.append("   loc += i;\n");
        xml_.append("   i++;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
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
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(10, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument11Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int st;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static Ex res = $new{ } Ex(5);\n");
        xml_.append(" $public $static Ex exec(){\n");
        xml_.append("  $return res;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append(" $static{\n");
        xml_.append("  ExTwo.st++;\n");
        xml_.append(" }\n");
        xml_.append(" $public Ex($int p){\n");
        xml_.append("  inst = p;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_,"pkg.Apply");
        CustList<Argument> args_ = new CustList<Argument>();
        Argument ret_;
        ret_ = calculateNormal("pkg.Apply", getMethodId("exec"),args_,cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(5, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument12Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $final $int inst;\n");
        xml_.append(" $static {\n");
        xml_.append("  $int loc=0;\n");
        xml_.append("  $int i=0;\n");
        xml_.append("  $while($true){\n");
        xml_.append("   $if(i>=5){\n");
        xml_.append("    Ex.inst = loc;\n");
        xml_.append("    $break;\n");
        xml_.append("   }\n");
        xml_.append("   loc += i;\n");
        xml_.append("   i++;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        instanceNormal("pkg.Ex", null, id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        Struct field_;
        field_ = getStaticField(cont_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(10, ((NumberStruct)field_).intStruct());
    }


    @Test
    public void instanceArgument2FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {{$new CharSequence();$new Exception();}}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        assertTrue(hasErr(files_));
    }
}
