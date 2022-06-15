package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;


public final class ProcessMethodInstanceSimpleInheritTest extends ProcessMethodCommon {
    @Test
    public void instanceArgument9Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
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
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" $public (){\n");
        xml_.append("  sec=7i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
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
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(7, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument10Test() {
        StringMap<String> files_ = new StringMap<String>();
        
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
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" $public (){\n");
        xml_.append("  sec=7i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

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
    public void instanceArgument11Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append(" $public $int ance=3i;\n");
        xml_.append(" $public $int field=1i;\n");
        xml_.append(" $public ($int i,$int j){\n");
        xml_.append("  $super(7i);\n");
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
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  sec=i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

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
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(7, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument12Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" $public (){\n");
        xml_.append("  sec=8i;\n");
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
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(8, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument13Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public (){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" $public (){\n");
        xml_.append("  sec=8i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormalCtor("pkg.Ex", null, id_, args_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(8, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument14Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append(" $public (){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" $public (){\n");
        xml_.append("  sec=8i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormalCtor("pkg.Ex", null, id_, args_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(8, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument15Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" $public (){\n");
        xml_.append("  sec=8i;\n");
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
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(8, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument16Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" {\n");
        xml_.append("  sec+=8i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  sec+=16i;\n");
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
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(24, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void instanceArgument17Test() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
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
        xml_.append("  field=6;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" $public (){\n");
        xml_.append("  sec=7i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

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
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(7, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void instanceArgument1FailTest() {
        StringMap<String> files_ = new StringMap<String>();
        
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
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
        xml_.append("  $long value = 6;\n");
        xml_.append("  field=value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" $public (){\n");
        xml_.append("  sec=7i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        assertTrue(hasErr(files_));
    }
}
