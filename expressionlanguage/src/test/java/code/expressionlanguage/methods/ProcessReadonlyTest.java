package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.structs.FieldableStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;

public final class ProcessReadonlyTest extends ProcessMethodCommon {
    private static final String CUST_ITER_PATH = "pkg/CustIter";
    private static final String CUST_LIST_PATH = "pkg/CustList";
    private static final String CUST_ITER_TABLE_PATH = "pkg/CustIterTable";
    private static final String CUST_PAIR_PATH = "pkg/CustPair";
    private static final String CUST_TABLE_PATH = "pkg/CustTable";
    @Test
    public void calculate1Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElReadOnly();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i:\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{\n");
        xml_.append(" $public $int sec:\n");
        xml_.append(" {\n");
        xml_.append("  sec;;;+=8i:\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  $super(17i):\n");
        xml_.append("  sec;;;+=16i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $int ance:\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  ance;;;=i;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
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
        assertEq(2, ((NumberStruct)field_).intStruct());
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(24, ((NumberStruct)field_).intStruct());
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(17, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void calculate2Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElReadOnly();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i:\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{\n");
        xml_.append(" $public $int sec:\n");
        xml_.append(" {\n");
        xml_.append("  sec;;;++:\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  $super(17i):\n");
        xml_.append("  sec;;;+=16i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $int ance:\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  ance;;;=i;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
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
        assertEq(2, ((NumberStruct)field_).intStruct());
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(17, ((NumberStruct)field_).intStruct());
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(17, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void calculate3Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElReadOnly();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append(" $public $static $int ance:\n");
        xml_.append(" $static {\n");
        xml_.append("  ance;;;=1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        assertEq(1,((IntStruct)cont_.getClasses().getStaticField(new ClassField("pkg.ExFour","ance"))).intStruct());
    }
    @Test
    public void calculate4Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElReadOnly();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i:\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{\n");
        xml_.append(" $public $int sec:\n");
        xml_.append(" {\n");
        xml_.append("  sec;;;++:\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  $super(17i):\n");
        xml_.append("  anceSt;;;=1i:\n");
        xml_.append("  sec;;;+=16i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int anceSt:\n");
        xml_.append(" $public $int ance:\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  ance;;;=i;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        assertTrue(cont_.getClasses().isInitialized("pkg.ExTwo"));
        assertTrue(cont_.getClasses().isInitialized("pkg.ExThree"));
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(17, ((NumberStruct)field_).intStruct());
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(17, ((NumberStruct)field_).intStruct());
        assertEq(1,((IntStruct)cont_.getClasses().getStaticField(new ClassField("pkg.ExThree","anceSt"))).intStruct());
    }
    @Test
    public void calculate5Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElReadOnly();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i:\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{\n");
        xml_.append(" $public $int sec:\n");
        xml_.append(" {\n");
        xml_.append("  sec;;;++:\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  $super(17i):\n");
        xml_.append("  anceSt;;;+=1i:\n");
        xml_.append("  sec;;;+=16i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int anceSt = 2:\n");
        xml_.append(" $public $int ance:\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  ance;;;=i;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        assertTrue(cont_.getClasses().isInitialized("pkg.ExTwo"));
        assertTrue(cont_.getClasses().isInitialized("pkg.ExThree"));
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(17, ((NumberStruct)field_).intStruct());
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(17, ((NumberStruct)field_).intStruct());
        assertEq(3,((IntStruct)cont_.getClasses().getStaticField(new ClassField("pkg.ExThree","anceSt"))).intStruct());
    }
    @Test
    public void calculate6Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElReadOnly();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i:\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{\n");
        xml_.append(" $public $int sec:\n");
        xml_.append(" {\n");
        xml_.append("  sec;;;++:\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  $super(17i):\n");
        xml_.append("  anceSt;;;++:\n");
        xml_.append("  sec;;;+=16i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int anceSt = 2:\n");
        xml_.append(" $public $int ance:\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  ance;;;=i;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        assertTrue(cont_.getClasses().isInitialized("pkg.ExTwo"));
        assertTrue(cont_.getClasses().isInitialized("pkg.ExThree"));
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(17, ((NumberStruct)field_).intStruct());
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(17, ((NumberStruct)field_).intStruct());
        assertEq(3,((IntStruct)cont_.getClasses().getStaticField(new ClassField("pkg.ExThree","anceSt"))).intStruct());
    }
    @Test
    public void calculate7Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $for($int i=4i:i;>0i:i;--){\n");
        xml_.append("   t;.+=i;:\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElReadOnly();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(10, ret_.getNumber());
    }
    @Test
    public void calculate8Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t,u:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $for(u;.=4i::u;.--){\n");
        xml_.append("   $if(u;.<=0i){\n");
        xml_.append("    $break:\n");
        xml_.append("   }\n");
        xml_.append("   t;.+=u;.:\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElReadOnly();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(10, ret_.getNumber());
    }
    @Test
    public void calculate9Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t,u:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $for(u;.=4i::u;.--){\n");
        xml_.append("   $if(u;.<=0i){\n");
        xml_.append("    $break:\n");
        xml_.append("   }\n");
        xml_.append("   t;.+=u;.:\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElReadOnly();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(10, ret_.getNumber());
    }
    @Test
    public void calculate10Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $for($int i=4i:i;>0i:i;--){\n");
        xml_.append("   $for($int j=i;:j;>0i:j;--){\n");
        xml_.append("    t;.+=i;+j;:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElReadOnly();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(50, ret_.getNumber());
    }
    @Test
    public void calculate11Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE(4i),\n");
        xml_.append(" TWO{}:\n");
        xml_.append(" $public $int first:\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  first;;;=i;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  first;;;=5i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int doubleValue(){\n");
        xml_.append("  pkg.Ex var = $static(pkg.Ex).ONE;;;:\n");
        xml_.append("  $int r = 0i:\n");
        xml_.append("  $switch(var;.){\n");
        xml_.append("   $case(ONE){\n");
        xml_.append("    r;. = 1i:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return $values(pkg.Ex).length+r;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public $int inst=$static(pkg.Ex).doubleValue():\n");
        xml_.append("}\n");
        files_.put("pkg/ExCont", xml_.toString());
        ContextEl cont_ = contextElReadOnly();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.ExCont");

        Argument ret_;
        ret_ = instanceArgument("pkg.ExCont", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.ExCont", str_.getClassName(cont_));
        Struct field_;
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.ExCont", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(3, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void calculate12Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $if(t;.<0){\n");
        xml_.append("   $return 1i/0i:\n");
        xml_.append("  }\n");
        xml_.append("  $else{\n");
        xml_.append("   t;.=1i:\n");
        xml_.append("  }\n");
        xml_.append("  $return t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElReadOnly();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(1, ret_.getNumber());
    }

    @Test
    public void calculate13Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ $int (pkg.Ex a, pkg.Ex b){\n");
        xml_.append(" $return a;.;a+b;.;a:\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a:\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex():\n");
        xml_.append("  one;.a=5i:\n");
        xml_.append("  Ex two = $new Ex():\n");
        xml_.append("  two;.a=3i:\n");
        xml_.append("  $if (one;. + two;. != 8i){\n");
        xml_.append("   $return 1i:\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElReadOnly();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(0, ret_.getNumber());
    }
    @Test
    public void calculate14Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElReadOnly();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append(" $public $static $final $int ance = 1:\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        assertEq(1,((IntStruct)cont_.getClasses().getStaticField(new ClassField("pkg.ExFour","ance"))).intStruct());
    }
    @Test
    public void calculate15Test() {
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
        xml_.append(" $public $static $int doubleValue(){\n");
        xml_.append("  pkg.Ex var = $static(pkg.Ex).ONE;;;:\n");
        xml_.append("  $int r = 6i:\n");
        xml_.append("  $switch(var;.){\n");
        xml_.append("   $case(TWO){\n");
        xml_.append("    r;. = 1i:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return $values(pkg.Ex).length+r;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public $int inst=$static(pkg.Ex).doubleValue():\n");
        xml_.append("}\n");
        files_.put("pkg/ExCont", xml_.toString());
        ContextEl cont_ = contextElReadOnly();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.ExCont");

        Argument ret_;
        ret_ = instanceArgument("pkg.ExCont", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.ExCont", str_.getClassName(cont_));
        Struct field_;
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.ExCont", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(8, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void calculate16Test() {
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
        xml_.append(" $public $static $int doubleValue(){\n");
        xml_.append("  pkg.Ex var = $null:\n");
        xml_.append("  $int r = 0i:\n");
        xml_.append("  $switch(var;.){\n");
        xml_.append("   $case($null){\n");
        xml_.append("    r;. = 1i:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return $values(pkg.Ex).length+r;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public $int inst=$static(pkg.Ex).doubleValue():\n");
        xml_.append("}\n");
        files_.put("pkg/ExCont", xml_.toString());
        ContextEl cont_ = contextElReadOnly();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.ExCont");

        Argument ret_;
        ret_ = instanceArgument("pkg.ExCont", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.ExCont", str_.getClassName(cont_));
        Struct field_;
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.ExCont", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(3, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void calculate17Test() {
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
        xml_.append(" $public $static $int doubleValue(){\n");
        xml_.append("  pkg.Ex var = $static(pkg.Ex).ONE;;;:\n");
        xml_.append("  $int r = 6i:\n");
        xml_.append("  $switch(var;.){\n");
        xml_.append("   $case($null){\n");
        xml_.append("    r;. = 1i:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return $values(pkg.Ex).length+r;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public $int inst=$static(pkg.Ex).doubleValue():\n");
        xml_.append("}\n");
        files_.put("pkg/ExCont", xml_.toString());
        ContextEl cont_ = contextElReadOnly();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.ExCont");

        Argument ret_;
        ret_ = instanceArgument("pkg.ExCont", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.ExCont", str_.getClassName(cont_));
        Struct field_;
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.ExCont", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(8, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void calculate18Test() {
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
        xml_.append(" $public $static $int doubleValue(){\n");
        xml_.append("  $int var = 1:\n");
        xml_.append("  $int r = 0i:\n");
        xml_.append("  $switch(var;.){\n");
        xml_.append("   $case(1){\n");
        xml_.append("    r;. = 1i:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return $values(pkg.Ex).length+r;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public $int inst=$static(pkg.Ex).doubleValue():\n");
        xml_.append("}\n");
        files_.put("pkg/ExCont", xml_.toString());
        ContextEl cont_ = contextElReadOnly();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.ExCont");

        Argument ret_;
        ret_ = instanceArgument("pkg.ExCont", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.ExCont", str_.getClassName(cont_));
        Struct field_;
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.ExCont", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(3, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void calculate19Test() {
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
        xml_.append("  $return:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int doubleValue(){\n");
        xml_.append("  $int var = 2:\n");
        xml_.append("  $int r = 6:\n");
        xml_.append("  $switch(var;.){\n");
        xml_.append("   $case(1){\n");
        xml_.append("    r;. = 1i:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return $values(pkg.Ex).length+r;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public $int inst=$static(pkg.Ex).doubleValue():\n");
        xml_.append("}\n");
        files_.put("pkg/ExCont", xml_.toString());
        ContextEl cont_ = contextElReadOnly();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.ExCont");

        Argument ret_;
        ret_ = instanceArgument("pkg.ExCont", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.ExCont", str_.getClassName(cont_));
        Struct field_;
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.ExCont", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(8, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void calculate20Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $for($int i=4i:i;>0i:){\n");
        xml_.append("   t;.+=i;:\n");
        xml_.append("   $break:\n");
        xml_.append("  }\n");
        xml_.append("  $return $($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElReadOnly();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(4, ret_.getNumber());
    }
    @Test
    public void calculate21Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("public class pkg.Ex {\n");
        xml_.append(" public static int catching(){\n");
        xml_.append("  int t;\n");
        xml_.append("  t=0;\n");
        xml_.append("  for(int i:{4,3,2,1}){\n");
        xml_.append("   t+=i;\n");
        xml_.append("  }\n");
        xml_.append("  return $(int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElReadOnlyDefault();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(10, ret_.getNumber());
    }
    @Test
    public void calculate22Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static CustTable<Number,Number> inst=$new CustTable<Number,Number>():\n");
        xml_.append(" $public $static $int res:\n");
        xml_.append(" $static {\n");
        xml_.append("  inst.add(3,5):\n");
        xml_.append("  inst.add(8,1):\n");
        xml_.append("  inst.add(2,6):\n");
        xml_.append("  $for(Number f, Number s: inst){\n");
        xml_.append("   res += f;intValue()+s;intValue():\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return res:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_ITER_TABLE_PATH, getCustomIteratorTable());
        files_.put(CUST_TABLE_PATH, getCustomTable());
        files_.put(CUST_PAIR_PATH, getCustomPair());
        ContextEl cont_ = contextElReadOnly();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("exmeth");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(25, ret_.getNumber());
    }
    @Test
    public void calculate23Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t,i:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  i;.=4i:\n");
        xml_.append("  $do{\n");
        xml_.append("   t;.+=i;.:\n");
        xml_.append("   $break:\n");
        xml_.append("  }$while(i;.>0i):\n");
        xml_.append("  $return $($int)t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElReadOnly();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(4, ret_.getNumber());
    }
    @Test
    public void calculate24Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t,i:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  i;.=4i:\n");
        xml_.append("  $do{\n");
        xml_.append("  }$while(i;.<0i):\n");
        xml_.append("  $return t;.+i;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElReadOnly();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(4, ret_.getNumber());
    }
    @Test
    public void calculate25Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t,i:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  i;.=4i:\n");
        xml_.append("  $do{\n");
        xml_.append("   $continue:\n");
        xml_.append("  }$while(i;.<0i):\n");
        xml_.append("  $return t;.+i;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElReadOnly();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(4, ret_.getNumber());
    }
    @Test
    public void calculate26Test() {
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
        xml_.append("  $return:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int doubleValue(){\n");
        xml_.append("  $int var = 2:\n");
        xml_.append("  $int r = 0:\n");
        xml_.append("  $switch(var;.){\n");
        xml_.append("   $case(1){\n");
        xml_.append("    r;. = 1i:\n");
        xml_.append("   }\n");
        xml_.append("   $default{\n");
        xml_.append("    r;. = 6i:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $return $values(pkg.Ex).length+r;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public $int inst=$static(pkg.Ex).doubleValue():\n");
        xml_.append("}\n");
        files_.put("pkg/ExCont", xml_.toString());
        ContextEl cont_ = contextElReadOnly();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.ExCont");

        Argument ret_;
        ret_ = instanceArgument("pkg.ExCont", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.ExCont", str_.getClassName(cont_));
        Struct field_;
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.ExCont", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(8, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void calculate27Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $return 1i/0i:\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return 1i:\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElReadOnly();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(1, ret_.getNumber());
    }

    @Test
    public void calculate28Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $try{\n");
        xml_.append("   $return 1i/0i:\n");
        xml_.append("  }\n");
        xml_.append("  $catch(code.expressionlanguage.exceptions.DivideZeroException e){\n");
        xml_.append("   t;.=1i:\n");
        xml_.append("  }\n");
        xml_.append("  $finally{\n");
        xml_.append("   t;.=2i:\n");
        xml_.append("  }\n");
        xml_.append("  $return t;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElReadOnly();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(2, ret_.getNumber());
    }
    @Test
    public void calculate29Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $try{\n");
        xml_.append("   $try{\n");
        xml_.append("    $throw $null:\n");
        xml_.append("   }\n");
        xml_.append("   $finally {\n");
        xml_.append("    t;.=1i:\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $catch{\n");
        xml_.append("   $return 1i+t;.:\n");
        xml_.append("  }\n");
        xml_.append("  $finally{\n");
        xml_.append("   t;.++:\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElReadOnly();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateArgument("pkg.Ex", id_, args_, cont_);
        assertEq(2, ret_.getNumber());
    }
    @Test
    public void calculate30Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int test(){\n");
        xml_.append("  Ex e = $new Ex():\n");
        xml_.append("  e;.[0] = 5:\n");
        xml_.append("  $return e;.[0]:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst=$new $int[2]:\n");
        xml_.append(" $public $int $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return inst[p;.;]:\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p;.;] = $value;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = contextElReadOnly();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateArgument("pkg.Apply", id_, args_, cont_);
        assertEq(5, ret_.getNumber());
    }

    @Test
    public void calculate31Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $final $int inst = 5:\n");
        xml_.append(" $public $int ance = 10:\n");
        xml_.append(" Ex(){\n");
        xml_.append("    ance = 2:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElReadOnly();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
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
        assertEq(5, ((NumberStruct)field_).intStruct());
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void calculate32Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $final $int inst:\n");
        xml_.append(" $public $int ance = 10:\n");
        xml_.append(" Ex(){\n");
        xml_.append("    inst = 5:\n");
        xml_.append("    ance = 2:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElReadOnly();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.isEmptyErrors());
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
        assertEq(5, ((NumberStruct)field_).intStruct());
        field_ = ((FieldableStruct)str_).getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void fail1Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElReadOnly();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree:pkg.ExFour {\n");
        xml_.append(" $static {\n");
        xml_.append("  ance;;;=2i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append(" $public $static $final $int ance:\n");
        xml_.append(" $static {\n");
        xml_.append("  ance;;;=1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void fail2Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElReadOnly();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append(" $public $static $final $int ance=1:\n");
        xml_.append(" $static {\n");
        xml_.append("  ance;;;=1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void fail3Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElReadOnly();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append(" $static {\n");
        xml_.append("  ance;;;=1i:\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $final $int ance=1:\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void fail4Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElReadOnly();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree:pkg.ExFour {\n");
        xml_.append(" $static {\n");
        xml_.append("  $var t:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append(" $public $static $final $int ance:\n");
        xml_.append(" $static {\n");
        xml_.append("  ance;;;=1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void fail6Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElReadOnly();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree:pkg.ExFour {\n");
        xml_.append(" $static {\n");
        xml_.append("  ance;;;+=2i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append(" $public $static $final $int ance:\n");
        xml_.append(" $static {\n");
        xml_.append("  ance;;;=1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void fail7Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElReadOnly();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree:pkg.ExFour {\n");
        xml_.append(" $static {\n");
        xml_.append("  ance;;;++:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append(" $public $static $final $int ance:\n");
        xml_.append(" $static {\n");
        xml_.append("  ance;;;=1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void fail8Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElReadOnly();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.ExEnum{\n");
        xml_.append("ONE:\n");
        xml_.append(" $public $static ExEnum TWO = (ONE = $null):\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void fail9Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $final $int field:\n");
        xml_.append(" $static {\n");
        xml_.append("  $int t:\n");
        xml_.append("  t;.=0i:\n");
        xml_.append("  $for(:t;.>0i:){\n");
        xml_.append("   fieldTwo = 5:\n");
        xml_.append("   field = 5:\n");
        xml_.append("   t;.+=i;:\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElReadOnly();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void fail10Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int outer(){$public $static $class Inn{}$return 0:}\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextElReadOnly();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().displayErrors(),!cont_.isEmptyErrors());
    }
    @Test
    public void fail11Test() {
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
        xml_.append(" $public $static $int doubleValue(){\n");
        xml_.append("  pkg.Ex var = $static(pkg.Ex).ONE;;;:\n");
        xml_.append("  $int r = 6i:\n");
        xml_.append("  $case(TWO){\n");
        xml_.append("   r;. = 1i:\n");
        xml_.append("  }\n");
        xml_.append("  $return $values(pkg.Ex).length+r;.:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public $int inst=$static(pkg.Ex).doubleValue():\n");
        xml_.append("}\n");
        files_.put("pkg/ExCont", xml_.toString());
        ContextEl cont_ = contextElReadOnly();
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void fail12Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElReadOnly();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append(" $public $static $final $int ance = 1:\n");
        xml_.append(" $static {\n");
        xml_.append("  Integer.MAX_VALUE=1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void fail13Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElReadOnly();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append(" $public $static $final $int ance = 1:\n");
        xml_.append(" {\n");
        xml_.append("  Integer.MAX_VALUE=1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    @Test
    public void fail14Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElReadOnly();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append(" $public $static $final $int ance = 1:\n");
        xml_.append(" $static $void method(){\n");
        xml_.append("  Integer.MAX_VALUE=1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }

    @Test
    public void instanceArgument3FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $final $int inst = 5:\n");
        xml_.append(" Ex(){\n");
        xml_.append("    inst = 2:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElReadOnly();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }

    @Test
    public void instanceArgument4FailTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $final $int inst = 5:\n");
        xml_.append(" {\n");
        xml_.append("    inst = 2:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = contextElReadOnly();
        files_.put("pkg/Ex", xml_.toString());
        Classes.validateAll(files_, cont_);
        assertTrue(!cont_.isEmptyErrors());
    }
    private static String getCustomPair() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustPair<U,V> :$pair<U,V>{\n");
        xml_.append(" $private U first:\n");
        xml_.append(" $private V second:\n");
        xml_.append(" $public CustPair(){\n");
        xml_.append(" }\n");
        xml_.append(" $public CustPair(U f,V s){\n");
        xml_.append("  first = f;.;:\n");
        xml_.append("  second = s;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public U getFirst(){\n");
        xml_.append("  $return first:\n");
        xml_.append(" }\n");
        xml_.append(" $public V getSecond(){\n");
        xml_.append("  $return second:\n");
        xml_.append(" }\n");
        xml_.append(" $public $void setFirst(U f){\n");
        xml_.append("  first = f;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
    private static String getCustomTable() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustTable<U,V> :$iterableTable<U,V>{\n");
        xml_.append(" $private CustList<CustPair<U,V>> list:\n");
        xml_.append(" $public (){\n");
        xml_.append("  list=$new CustList<CustPair<U,V>>():\n");
        xml_.append(" }\n");
        xml_.append(" $public $void add(U f,V s){\n");
        xml_.append("  list.add($new CustPair<U,V>(f;.;,s;.;)):\n");
        xml_.append(" }\n");
        xml_.append(" $public $void add(CustPair<U,V> p){\n");
        xml_.append("  list.add(p;.;):\n");
        xml_.append(" }\n");
        xml_.append(" $public $int size(){\n");
        xml_.append("  $return list.size():\n");
        xml_.append(" }\n");
        xml_.append(" $public CustPair<U,V> get($int index){\n");
        xml_.append("  $return list.get(index;.;):\n");
        xml_.append(" }\n");
        xml_.append(" $public $iteratorTable<U,V> iteratorTable(){\n");
        xml_.append("  $return $new CustIterTable<U,V>($this):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
    private static String getCustomIteratorTable() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustIterTable<U,V> :$iteratorTable<U,V>{\n");
        xml_.append(" $private CustTable<U,V> list:\n");
        xml_.append(" $private $int length:\n");
        xml_.append(" $private $int index:\n");
        xml_.append(" $public CustIterTable(CustTable<U,V> i){\n");
        xml_.append("  list;;;=i;.;:\n");
        xml_.append("  length;;;=list;;;size():\n");
        xml_.append(" }\n");
        xml_.append(" $public CustPair<U,V> nextPair(){\n");
        xml_.append("  CustPair<U,V> out=list;;;get(index;;;):\n");
        xml_.append("  index;;;++:\n");
        xml_.append("  $return out;.:\n");
        xml_.append(" }\n");
        xml_.append(" $public $boolean hasNextPair(){\n");
        xml_.append("  $return index;;;<length;;;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
    private static String getCustomList() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustList<#U> :$iterable<#U>{\n");
        xml_.append(" $private #U[] list:\n");
        xml_.append(" $private $int length:\n");
        xml_.append(" $public (){\n");
        xml_.append("  list;;;=$new #U[0i]:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void add(#U elt){\n");
        xml_.append("  add(length;;;,elt;.;):\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void add($int index,#U elt){\n");
        xml_.append("  #U[] newlist=$new #U[length;;;+1i]:\n");
        xml_.append("  $iter($int i=0i:index;.;:1i){\n");
        xml_.append("   newlist;.[i;]=list;;;[i;]:\n");
        xml_.append("  }\n");
        xml_.append("  newlist;.[index;.;]=elt;.;:\n");
        xml_.append("  $iter($int i=index;.;+1i:length;;;+1i:1i){\n");
        xml_.append("   newlist;.[i;]=list;;;[i;-1i]:\n");
        xml_.append("  }\n");
        xml_.append("  length;;;++:\n");
        xml_.append("  list;;;=newlist;.:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int size(){\n");
        xml_.append("  $return length;;;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal #U get($int index){\n");
        xml_.append("  $return list;;;[index;.;]:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void set($int index,#U elt){\n");
        xml_.append("  list;;;[index;.;]=elt;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void remove($int index){\n");
        xml_.append("  $iter($int i=index;.;:length;;;-1i:1i){\n");
        xml_.append("   list;;;[i;]=list;;;[i;+1i]:\n");
        xml_.append("  }\n");
        xml_.append("  list;;;[length;;;-1i]=$null:\n");
        xml_.append("  length;;;--:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $iterator<#U> iterator(){\n");
        xml_.append("  $return $new pkg.CustIter<#U>($this):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
    private static String getCustomIterator() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustIter<#T> :$iterator<#T>{\n");
        xml_.append(" $private pkg.CustList<#T> list:\n");
        xml_.append(" $private $int length:\n");
        xml_.append(" $private $int index:\n");
        xml_.append(" $public (pkg.CustList<#T> i){\n");
        xml_.append("  list;;;=i;.;:\n");
        xml_.append("  length;;;=list;;;size():\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal #T next(){\n");
        xml_.append("  #T out=list;;;get(index;;;):\n");
        xml_.append("  index;;;++:\n");
        xml_.append("  $return out;.:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $boolean hasNext(){\n");
        xml_.append("  $return index;;;<length;;;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
}
