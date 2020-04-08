package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.structs.FieldableStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;

public final class ProcessMethodIdCallTest extends ProcessMethodCommon {

    @Test
    public void calculate1Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number,java.lang.Number> inst=$new pkg.ExTwo<java.lang.Number,java.lang.Number>():\n");
        xml_.append(" $public $int ance=inst;;;get($id(pkg.ExTwo,#T),8I):\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T:java.lang.Number,#U:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(#T i){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(#U i){\n");
        xml_.append("  $return 2i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<java.lang.Number,java.lang.Number>", field_.getClassName(cont_));
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void calculate1_Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number,java.lang.Number> inst=$new pkg.ExTwo<java.lang.Number,java.lang.Number>():\n");
        xml_.append(" $public $int ance=inst;;;get($id(pkg.ExTwo,0,#T),8I):\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T:java.lang.Number,#U:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(#T i){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(#U i){\n");
        xml_.append("  $return 2i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<java.lang.Number,java.lang.Number>", field_.getClassName(cont_));
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void calculate2Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number,java.lang.Number> inst=$new pkg.ExThree<java.lang.Number,java.lang.Number>():\n");
        xml_.append(" $public $int ance=inst;;;get($id(pkg.ExTwo,#T),8I):\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T:java.lang.Number,#U:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(#T i){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(#U i){\n");
        xml_.append("  $return 2i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<#V:java.lang.Number,#W:java.lang.Number>:ExTwo<#V,#W> {\n");
        xml_.append(" $public $normal $int get(#V i){\n");
        xml_.append("  $return 3i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(#W i){\n");
        xml_.append("  $return 4i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number,java.lang.Number>", field_.getClassName(cont_));
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(3, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void calculate3Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number,java.lang.Number> inst=$new pkg.ExTwo<java.lang.Number,java.lang.Number>($id(pkg.ExTwo,#T),8I):\n");
        xml_.append(" $public $int ance=inst;;;get():\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T:java.lang.Number,#U:java.lang.Number> {\n");
        xml_.append(" $private $int value:\n");
        xml_.append(" $public(#T i){\n");
        xml_.append("  value=i;.;intValue()+1i:\n");
        xml_.append(" }\n");
        xml_.append(" $public(#U i){\n");
        xml_.append("  value=i;.;intValue()+2i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return value:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<java.lang.Number,java.lang.Number>", field_.getClassName(cont_));
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(9, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void calculate4Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number,java.lang.Number> inst=$new pkg.ExThree<java.lang.Number,java.lang.Number>($id(pkg.ExThree,#V),8I):\n");
        xml_.append(" $public $int ance=inst;;;get():\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T:java.lang.Number,#U:java.lang.Number> {\n");
        xml_.append(" $private $int value:\n");
        xml_.append(" $public(#T i){\n");
        xml_.append("  value=i;.;intValue()+1i:\n");
        xml_.append(" }\n");
        xml_.append(" $public(#U i){\n");
        xml_.append("  value=i;.;intValue()+2i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return value:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<#V:java.lang.Number,#W:java.lang.Number>:ExTwo<#V,#W> {\n");
        xml_.append(" $public (#V i){\n");
        xml_.append("  $super($id(pkg.ExTwo,#T),i;.;):\n");
        xml_.append(" }\n");
        xml_.append(" $public (#W i){\n");
        xml_.append("  $super($id(pkg.ExTwo,#U),i;.;):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number,java.lang.Number>", field_.getClassName(cont_));
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(9, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void calculate5Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number,java.lang.Number> inst=$new pkg.ExThree(8I):\n");
        xml_.append(" $public $int ance=inst;;;get():\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T:java.lang.Number,#U:java.lang.Number> {\n");
        xml_.append(" $private $int value:\n");
        xml_.append(" $public(#T i){\n");
        xml_.append("  value=i;.;intValue()+1i:\n");
        xml_.append(" }\n");
        xml_.append(" $public(#U i){\n");
        xml_.append("  value=i;.;intValue()+2i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(){\n");
        xml_.append("  $return value:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree:ExTwo<java.lang.Number,java.lang.Number> {\n");
        xml_.append(" $public (java.lang.Number i){\n");
        xml_.append("  $super($id(pkg.ExTwo,#T),i;.;):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree", field_.getClassName(cont_));
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(9, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void calculate6Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number,java.lang.Number> inst=$new pkg.ExTwo<java.lang.Number,java.lang.Number>():\n");
        xml_.append(" $public $int ance=inst;;;get($id(pkg.ExTwo,#T...),8I):\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T:java.lang.Number,#U:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(#T... i){\n");
        xml_.append("  $int sum = 0i:\n");
        xml_.append("  $foreach(#T p: i;.;){\n");
        xml_.append("   sum;.+=p;intValue():\n");
        xml_.append("  }\n");
        xml_.append("  $return sum;.+1i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(#U... i){\n");
        xml_.append("  $return 2i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<java.lang.Number,java.lang.Number>", field_.getClassName(cont_));
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(9, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void calculate7Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number,java.lang.Number> inst=$new pkg.ExTwo<java.lang.Number,java.lang.Number>():\n");
        xml_.append(" $public $int ance=inst;;;get($id(pkg.ExTwo,#T...),8I,5I):\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T:java.lang.Number,#U:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(#T... i){\n");
        xml_.append("  $int sum = 0i:\n");
        xml_.append("  $foreach(#T p: i;.;){\n");
        xml_.append("   sum;.+=p;intValue():\n");
        xml_.append("  }\n");
        xml_.append("  $return sum;.+1i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(#U... i){\n");
        xml_.append("  $return 2i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<java.lang.Number,java.lang.Number>", field_.getClassName(cont_));
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(14, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void calculate8Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number,java.lang.Number> inst=$new pkg.ExTwo<java.lang.Number,java.lang.Number>():\n");
        xml_.append(" $public $int ance=inst;;;get($id(pkg.ExTwo,#T...)):\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T:java.lang.Number,#U:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(#T... i){\n");
        xml_.append("  $int sum = 0i:\n");
        xml_.append("  $foreach(#T p: i;.;){\n");
        xml_.append("   sum;.+=p;intValue():\n");
        xml_.append("  }\n");
        xml_.append("  $return sum;.+1i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(#U... i){\n");
        xml_.append("  $return 2i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<java.lang.Number,java.lang.Number>", field_.getClassName(cont_));
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void calculate9Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public pkg.ExTwo.Inner inst=new pkg.ExTwo().new Inner();\n");
        xml_.append(" public int ance=inst.get($id(pkg.ExTwo.Inner,int),8I);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("public class pkg.ExTwo {\n");
        xml_.append(" public class Inner {\n");
        xml_.append("  public normal int get(int i){\n");
        xml_.append("   return 1i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEnElDefaultInternType();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo..Inner", field_.getClassName(cont_));
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq("$core.Integer", field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void calculate10Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number,java.lang.Number> inst=$new pkg.ExTwo<java.lang.Number,java.lang.Number>():\n");
        xml_.append(" $public $int ance=inst;;;get($id(pkg.ExTwo,#T...),$firstopt(8I)):\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T:java.lang.Number,#U:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(#T... i){\n");
        xml_.append("  $int sum = 0i:\n");
        xml_.append("  $foreach(#T p: i;.;){\n");
        xml_.append("   sum;.+=p;intValue():\n");
        xml_.append("  }\n");
        xml_.append("  $return sum;.+1i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(#U... i){\n");
        xml_.append("  $return 2i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextElReadOnly();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<java.lang.Number,java.lang.Number>", field_.getClassName(cont_));
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(9, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void calculate11Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number,java.lang.Number> inst=$new pkg.ExTwo<java.lang.Number,java.lang.Number>():\n");
        xml_.append(" $public $int ance=inst;;;get($id(pkg.ExTwo,#T...),$firstopt($null)):\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T:java.lang.Number,#U:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(#T... i){\n");
        xml_.append("  $int sum = 0i:\n");
        xml_.append("  $foreach(#T p: i;.;){\n");
        xml_.append("   $if (p; != $null){\n");
        xml_.append("    sum;.+=p;intValue():\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return sum;.+1i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(#U... i){\n");
        xml_.append("  $return 2i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextElReadOnly();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<java.lang.Number,java.lang.Number>", field_.getClassName(cont_));
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void calculate12Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number,java.lang.Number> inst=$new pkg.ExTwo<java.lang.Number,java.lang.Number>():\n");
        xml_.append(" $public $int ance=inst;;;get($id(pkg.ExTwo,#T...),$firstopt($new Integer[]{8I})):\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T:java.lang.Number,#U:java.lang.Number> {\n");
        xml_.append(" $public $normal $int get(#T... i){\n");
        xml_.append("  $int sum = 0i:\n");
        xml_.append("  $foreach(#T p: i;.;){\n");
        xml_.append("   sum;.+=p;intValue():\n");
        xml_.append("  }\n");
        xml_.append("  $return sum;.+1i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(#U... i){\n");
        xml_.append("  $return 2i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextElReadOnly();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<java.lang.Number,java.lang.Number>", field_.getClassName(cont_));
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(9, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void calculate13Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number,java.lang.Number> inst=$new pkg.ExTwo<java.lang.Number,java.lang.Number>($id(pkg.ExTwo,#T...),$firstopt(8I)):\n");
        xml_.append(" $public $int ance=inst;;;ance:\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T:java.lang.Number,#U:java.lang.Number> {\n");
        xml_.append(" $public $int ance:\n");
        xml_.append(" $public ExTwo(#T... i){\n");
        xml_.append("  $int sum = 0i:\n");
        xml_.append("  $foreach(#T p: i;.;){\n");
        xml_.append("   sum;.+=p;intValue():\n");
        xml_.append("  }\n");
        xml_.append("  ance=sum;.+1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextElReadOnly();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<java.lang.Number,java.lang.Number>", field_.getClassName(cont_));
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(9, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void calculate14Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number,java.lang.Number> inst=$new pkg.ExTwo<java.lang.Number,java.lang.Number>($id(pkg.ExTwo,#T...),$firstopt($null)):\n");
        xml_.append(" $public $int ance=inst;;;ance:\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T:java.lang.Number,#U:java.lang.Number> {\n");
        xml_.append(" $public $int ance:\n");
        xml_.append(" $public ExTwo(#T... i){\n");
        xml_.append("  $int sum = 0i:\n");
        xml_.append("  $foreach(#T p: i;.;){\n");
        xml_.append("   $if (p; != $null){\n");
        xml_.append("    sum;.+=p;intValue():\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  ance=sum;.+1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextElReadOnly();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<java.lang.Number,java.lang.Number>", field_.getClassName(cont_));
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void calculate15Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number,java.lang.Number> inst=$new pkg.ExTwo<java.lang.Number,java.lang.Number>($id(pkg.ExTwo,#T...),$firstopt($new Integer[]{8I})):\n");
        xml_.append(" $public $int ance=inst;;;ance:\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T:java.lang.Number,#U:java.lang.Number> {\n");
        xml_.append(" $public $int ance:\n");
        xml_.append(" $public ExTwo(#T... i){\n");
        xml_.append("  $int sum = 0i:\n");
        xml_.append("  $foreach(#T p: i;.;){\n");
        xml_.append("   sum;.+=p;intValue():\n");
        xml_.append("  }\n");
        xml_.append("  ance=sum;.+1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextElReadOnly();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<java.lang.Number,java.lang.Number>", field_.getClassName(cont_));
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(9, ((NumberStruct)field_).intStruct());
    }
}
