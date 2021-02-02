package code.expressionlanguage.methods;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessReadonlyBisTest extends ProcessMethodCommon {
    @Test
    public void calculate1Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" {\n");
        xml_.append("  sec+=8i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  $super(17i);\n");
        xml_.append("  sec+=16i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  ance=i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
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
        field_ = getField(str_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(17, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void calculate2Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" {\n");
        xml_.append("  sec++;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  $super(17i);\n");
        xml_.append("  sec+=16i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  ance=i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
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
        assertEq(17, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(17, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void calculate3Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append(" $public $static $int ance;\n");
        xml_.append(" $static {\n");
        xml_.append("  ance=1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        assertEq(1,((IntStruct) getStaticField(cont_, new ClassField("pkg.ExFour", "ance"))).intStruct());
    }
    @Test
    public void calculate4Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" {\n");
        xml_.append("  sec++;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  $super(17i);\n");
        xml_.append("  anceSt=1i;\n");
        xml_.append("  sec+=16i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int anceSt;\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  ance=i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
        assertTrue(isInitialized(cont_, "pkg.ExThree"));
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(17, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(17, ((NumberStruct)field_).intStruct());
        assertEq(1,((IntStruct) getStaticField(cont_, new ClassField("pkg.ExThree", "anceSt"))).intStruct());
    }
    @Test
    public void calculate5Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" {\n");
        xml_.append("  sec++;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  $super(17i);\n");
        xml_.append("  anceSt+=1i;\n");
        xml_.append("  sec+=16i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int anceSt = 2;\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  ance=i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
        assertTrue(isInitialized(cont_, "pkg.ExThree"));
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(17, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(17, ((NumberStruct)field_).intStruct());
        assertEq(3,((IntStruct) getStaticField(cont_, new ClassField("pkg.ExThree", "anceSt"))).intStruct());
    }
    @Test
    public void calculate6Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex :pkg.ExTwo{\n");
        xml_.append(" $public $int inst=2i;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{\n");
        xml_.append(" $public $int sec;\n");
        xml_.append(" {\n");
        xml_.append("  sec++;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  $super(17i);\n");
        xml_.append("  anceSt++;\n");
        xml_.append("  sec+=16i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {\n");
        xml_.append(" $public $static $int anceSt = 2;\n");
        xml_.append(" $public $int ance;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  ance=i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertTrue(isInitialized(cont_, "pkg.ExTwo"));
        assertTrue(isInitialized(cont_, "pkg.ExThree"));
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Argument ret_;
        ret_ = instanceNormal("pkg.Ex", null, id_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExTwo", "sec"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(17, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.ExThree", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(17, ((NumberStruct)field_).intStruct());
        assertEq(3,((IntStruct) getStaticField(cont_, new ClassField("pkg.ExThree", "anceSt"))).intStruct());
    }
    @Test
    public void calculate12Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $if(t<0){\n");
        xml_.append("   $return 1i/0i;\n");
        xml_.append("  }\n");
        xml_.append("  $else{\n");
        xml_.append("   t=1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }

    @Test
    public void calculate13Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$operator+ $int (pkg.Ex a, pkg.Ex b){\n");
        xml_.append(" $return a.a+b.a;\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int a;\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  Ex one = $new Ex();\n");
        xml_.append("  one.a=5i;\n");
        xml_.append("  Ex two = $new Ex();\n");
        xml_.append("  two.a=3i;\n");
        xml_.append("  $if (one + two != 8i){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append("  $return 0i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(0, getNumber(ret_));
    }
    @Test
    public void calculate14Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append(" $public $static $final $int ance = 1;\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        assertEq(1,((IntStruct) getStaticField(cont_, new ClassField("pkg.ExFour", "ance"))).intStruct());
    }
    @Test
    public void calculate23Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t,i;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  i=4i;\n");
        xml_.append("  $do{\n");
        xml_.append("   t+=i;\n");
        xml_.append("   $break;\n");
        xml_.append("  }$while(i>0i);\n");
        xml_.append("  $return $($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }
    @Test
    public void calculate24Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t,i;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  i=4i;\n");
        xml_.append("  $do{\n");
        xml_.append("  }$while(i<0i);\n");
        xml_.append("  $return t+i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }
    @Test
    public void calculate25Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t,i;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  i=4i;\n");
        xml_.append("  $do{\n");
        xml_.append("   $continue;\n");
        xml_.append("  }$while(i<0i);\n");
        xml_.append("  $return t+i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_;
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(4, getNumber(ret_));
    }
    @Test
    public void calculate27Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $try{\n");
        xml_.append("   $return 1i/0i;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(java.lang.Exception e){\n");
        xml_.append("   $return 1i;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(1, getNumber(ret_));
    }

    @Test
    public void calculate28Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $try{\n");
        xml_.append("   $return 1i/0i;\n");
        xml_.append("  }\n");
        xml_.append("  $catch(code.expressionlanguage.exceptions.DivideZeroException e){\n");
        xml_.append("   t=1i;\n");
        xml_.append("  }\n");
        xml_.append("  $finally{\n");
        xml_.append("   t=2i;\n");
        xml_.append("  }\n");
        xml_.append("  $return t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculate29Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int catching(){\n");
        xml_.append("  $int t;\n");
        xml_.append("  t=0i;\n");
        xml_.append("  $try{\n");
        xml_.append("   $try{\n");
        xml_.append("    $throw $null;\n");
        xml_.append("   }\n");
        xml_.append("   $finally {\n");
        xml_.append("    t=1i;\n");
        xml_.append("   }\n");
        xml_.append("  }\n");
        xml_.append("  $catch{\n");
        xml_.append("   $return 1i+t;\n");
        xml_.append("  }\n");
        xml_.append("  $finally{\n");
        xml_.append("   t++;\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("catching");
        Argument ret_ = new Argument();
        ret_ = calculateNormal("pkg.Ex", id_, args_, cont_);
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void calculate30Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int test(){\n");
        xml_.append("  Ex e = $new Ex();\n");
        xml_.append("  e[0] = 5;\n");
        xml_.append("  $return e[0];\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $int[] inst=$new $int[2];\n");
        xml_.append(" $public $int $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  $return inst[p];\n");
        xml_.append(" }\n");
        xml_.append(" $public $void $this($int p)\n");
        xml_.append(" {\n");
        xml_.append("  inst[p] = $value;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId id_ = getMethodId("test");
        Argument ret_;
        ret_ = calculateNormal("pkg.Apply", id_, args_, cont_);
        assertEq(5, getNumber(ret_));
    }

    @Test
    public void calculate31Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $final $int inst = 5;\n");
        xml_.append(" $public $int ance = 10;\n");
        xml_.append(" Ex(){\n");
        xml_.append("    ance = 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
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
        assertEq(5, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void calculate32Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $final $int inst;\n");
        xml_.append(" $public $int ance = 10;\n");
        xml_.append(" Ex(){\n");
        xml_.append("    inst = 5;\n");
        xml_.append("    ance = 2;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
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
        assertEq(5, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void fail1Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree:pkg.ExFour {\n");
        xml_.append(" $static {\n");
        xml_.append("  ance=2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append(" $public $static $final $int ance;\n");
        xml_.append(" $static {\n");
        xml_.append("  ance=1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }
    @Test
    public void fail2Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append(" $public $static $final $int ance=1;\n");
        xml_.append(" $static {\n");
        xml_.append("  ance=1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }
    @Test
    public void fail3Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append(" $static {\n");
        xml_.append("  ance=1i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $final $int ance=1;\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }
    @Test
    public void fail4Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree:pkg.ExFour {\n");
        xml_.append(" $static {\n");
        xml_.append("  $var t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append(" $public $static $final $int ance;\n");
        xml_.append(" $static {\n");
        xml_.append("  ance=1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }
    @Test
    public void fail6Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree:pkg.ExFour {\n");
        xml_.append(" $static {\n");
        xml_.append("  ance+=2i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append(" $public $static $final $int ance;\n");
        xml_.append(" $static {\n");
        xml_.append("  ance=1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }
    @Test
    public void fail7Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree:pkg.ExFour {\n");
        xml_.append(" $static {\n");
        xml_.append("  ance++;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExFour {\n");
        xml_.append(" $public $static $final $int ance;\n");
        xml_.append(" $static {\n");
        xml_.append("  ance=1i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }
    @Test
    public void fail8Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.ExEnum{\n");
        xml_.append("ONE;\n");
        xml_.append(" $public $static ExEnum TWO = (ONE = $null);\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        assertTrue(hasErrReadOnly(files_));
    }
}
