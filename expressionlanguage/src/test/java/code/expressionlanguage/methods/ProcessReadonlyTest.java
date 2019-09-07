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
        assertTrue(cont_.getClasses().isEmptyErrors());
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
        assertTrue(cont_.getClasses().isEmptyErrors());
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
        assertTrue(cont_.getClasses().isEmptyErrors());
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
        assertTrue(cont_.getClasses().isEmptyErrors());
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
        assertTrue(cont_.getClasses().isEmptyErrors());
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
        assertTrue(cont_.getClasses().isEmptyErrors());
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
        assertTrue(cont_.getClasses().isEmptyErrors());
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
        assertTrue(cont_.getClasses().isEmptyErrors());
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
        assertTrue(cont_.getClasses().isEmptyErrors());
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
        assertTrue(cont_.getClasses().isEmptyErrors());
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
        assertTrue(cont_.getClasses().isEmptyErrors());
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
        assertTrue(cont_.getClasses().isEmptyErrors());
        assertEq(1,((IntStruct)cont_.getClasses().getStaticField(new ClassField("pkg.ExFour","ance"))).intStruct());
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
        assertTrue(!cont_.getClasses().isEmptyErrors());
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
        assertTrue(!cont_.getClasses().isEmptyErrors());
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
        assertTrue(!cont_.getClasses().isEmptyErrors());
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
        assertTrue(!cont_.getClasses().isEmptyErrors());
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
        assertTrue(!cont_.getClasses().isEmptyErrors());
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
        assertTrue(!cont_.getClasses().isEmptyErrors());
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
        assertTrue(!cont_.getClasses().isEmptyErrors());
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
        assertTrue(!cont_.getClasses().isEmptyErrors());
    }
}
