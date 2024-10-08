package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.*;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessMethodImportsTest extends ProcessMethodCommon {
    private static final String CUST_ITER_PATH = "pkg/CustIter";
    private static final String CUST_LIST_PATH = "pkg/CustList";
    private static final String CUST_SEC_LIST_PATH = "pkg/SecCustList";

    @Test
    public void calculateArgument0Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$static pkg.Annot.method;\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $static(ExTwo).exmethtwo()+method+method();\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int method(){\n");
        xml_.append("  $return 6;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.Annot {\n");
        xml_.append(" $final $int method = 5;\n");
        xml_.append(" $int method();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmethtwo(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(20, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument1Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return $static(ExTwo).exmethtwo();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmethtwo(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(9, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument2Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  ExTwo out=$new ExTwo();\n");
        xml_.append("  $return $static($Class).getClass(out).getName();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq("pkg.ExTwo", getString(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument3Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  $return $static($Class).getClass($(ExTwo)$new ExTwo()).getName();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq("pkg.ExTwo", getString(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument4Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  $return $static($Class).getClass($new ExTwo() $instanceof ExTwo).getName()+\",\"+($new ExTwo() $instanceof ExTwo);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq("java.lang.Boolean,true",getString(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument5Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  $return $static($Class).getClass($new ExTwo[1i]).getName();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq("[pkg.ExTwo", getString(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument6Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  $return $static($Class).getClass($new ExTwo[]{$new ExTwo()}).getName();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq("[pkg.ExTwo", getString(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument7Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  java.lang.String out =\"\";\n");
        xml_.append("  $foreach(ExTwo e: $new ExTwo[]{$new ExTwo(1i),$new ExTwo(7i)}){\n");
        xml_.append("   out+=e.inst;\n");
        xml_.append("   out+=\".\";\n");
        xml_.append("  }\n");
        xml_.append("  $return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $final $int inst;\n");
        xml_.append(" $public($int _inst){\n");
        xml_.append("  inst=_inst;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq("1.7.", getString(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument8Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  java.lang.String out =\"\";\n");
        xml_.append("  $foreach(ExTwo e: $new ExTwo[]{$new ExTwo(10i),$new ExTwo(0i)}){\n");
        xml_.append("   $try{\n");
        xml_.append("    out+=1000i/e.inst;\n");
        xml_.append("   }\n");
        xml_.append("   $catch(code.expressionlanguage.exceptions.DivideZeroException d){\n");
        xml_.append("    out+=1i;\n");
        xml_.append("   }\n");
        xml_.append("   out+=\".\";\n");
        xml_.append("  }\n");
        xml_.append("  $return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $final $int inst;\n");
        xml_.append(" $public($int _inst){\n");
        xml_.append("  inst=_inst;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq("100.1.", getString(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument9Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  java.lang.String out =\"\";\n");
        xml_.append("  $foreach(ExTwo e: $new ExTwo[]{$new ExTwo(10i),$new ExTwo(0i)}){\n");
        xml_.append("   $try{\n");
        xml_.append("    out+=1000i/e.getter();\n");
        xml_.append("   }\n");
        xml_.append("   $catch(code.expressionlanguage.exceptions.DivideZeroException d){\n");
        xml_.append("    out+=1i;\n");
        xml_.append("   }\n");
        xml_.append("   out+=\".\";\n");
        xml_.append("  }\n");
        xml_.append("  $return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $final $int inst;\n");
        xml_.append(" $public($int _inst){\n");
        xml_.append("  inst=_inst;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return inst;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq("100.1.", getString(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument10Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  java.lang.String out =\"\";\n");
        xml_.append("  $foreach(ExTwo e: $new ExTwo[]{$new ExTwo(10i),$new ExTwo(0i)}){\n");
        xml_.append("   $try{\n");
        xml_.append("    out+=1000i/e.$classchoice(ExTwo)getter();\n");
        xml_.append("   }\n");
        xml_.append("   $catch(code.expressionlanguage.exceptions.DivideZeroException d){\n");
        xml_.append("    out+=1i;\n");
        xml_.append("   }\n");
        xml_.append("   out+=\".\";\n");
        xml_.append("  }\n");
        xml_.append("  $return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $final $int inst;\n");
        xml_.append(" $public($int _inst){\n");
        xml_.append("  inst=_inst;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return inst;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq("100.1.", getString(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument11Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  java.lang.String out =\"\";\n");
        xml_.append("  $foreach(ExTwo e: $new ExTwo[]{$new ExTwo(0i),$new ExThree(1i,11i)}){\n");
        xml_.append("   $try{\n");
        xml_.append("    out+=1000i/e.getter();\n");
        xml_.append("   }\n");
        xml_.append("   $catch(code.expressionlanguage.exceptions.DivideZeroException d){\n");
        xml_.append("    out+=1i;\n");
        xml_.append("   }\n");
        xml_.append("   out+=\".\";\n");
        xml_.append("  }\n");
        xml_.append("  $return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $final $int inst;\n");
        xml_.append(" $public($int _inst){\n");
        xml_.append("  inst=_inst;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return inst;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree:ExTwo {\n");
        xml_.append(" $public $final $int inst;\n");
        xml_.append(" $public($int _inst,$int _inst2){\n");
        xml_.append("  $super(_inst);\n");
        xml_.append("  inst=_inst2;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int getter(){\n");
        xml_.append("  $return inst-$super.getter();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq("1.100.", getString(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument12Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  java.lang.String out =\"\";\n");
        xml_.append("  ExTwo c=$new ExTwo(1i);\n");
        xml_.append("  $foreach(ExTwo e: $new ExTwo[]{$new ExTwo(0i),$new ExThree(1i,11i)}){\n");
        xml_.append("   out+=e.getter(c);\n");
        xml_.append("   out+=\".\";\n");
        xml_.append("   out+=e.$classchoice(ExTwo)inst;\n");
        xml_.append("   out+=\".\";\n");
        xml_.append("  }\n");
        xml_.append("  $return out;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $final $int inst;\n");
        xml_.append(" $public($int _inst){\n");
        xml_.append("  inst=_inst;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int getter(ExTwo p){\n");
        xml_.append("  $return inst-p.inst;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExThree:ExTwo {\n");
        xml_.append(" $public $final $int inst;\n");
        xml_.append(" $public($int _inst,$int _inst2){\n");
        xml_.append("  $super(_inst);\n");
        xml_.append("  inst=_inst2;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int getter(ExTwo p){\n");
        xml_.append("  $return inst-p.$classchoice(ExTwo)getter($this);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq("-1.0.11.1.", getString(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument13Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  $return $class(ExTwo).getName();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq("pkg.ExTwo", getString(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument14Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static java.lang.String exmeth(){\n");
        xml_.append("  $return $class(ExTwo).getName();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq("pkg.ExTwo", getString(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument15Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("pkgtwo.ExTwo;\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public CustList<ExTwo> inst=$new CustList<ExTwo>();\n");
        xml_.append(" $public $int res;\n");
        xml_.append(" {\n");
        xml_.append("  inst.add($new ExTwo());\n");
        xml_.append("  inst.add($new ExTwo());\n");
        xml_.append("  inst.add($new ExTwo());\n");
        xml_.append("  res=inst.size();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.ExTwo {\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "res"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(3, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void calculateArgument16Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("pkgthree.*;\n");
        xml_.append("pkgtwo.*;\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public CustList<ExTwo<ExThree>> inst=$new CustList<ExTwo<ExThree>>();\n");
        xml_.append(" $public $int res;\n");
        xml_.append(" {\n");
        xml_.append("  inst.add($new ExTwo<ExThree>().pass($new ExFour(),0i).pass($new ExFour(),1i));\n");
        xml_.append("  inst.add($new ExTwo<ExThree>().pass($new ExFour(),0i).pass($new ExFour(),1i));\n");
        xml_.append("  inst.add($new ExTwo<ExThree>().pass($new ExFour(),0i).pass($new ExFour(),1i));\n");
        xml_.append("  res=inst.size();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("pkgthree.ExThree;\n");
        xml_.append("$public $class pkgtwo.ExTwo<K:ExThree> {\n");
        xml_.append(" $public $final K[] array = $new K[2i];\n");
        xml_.append(" $public $final pkgtwo.ExTwo<K> pass(K _v, $int _index){\n");
        xml_.append("  array[_index]=_v;\n");
        xml_.append("  $int unused=_v.get();\n");
        xml_.append("  $return $this;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $abstract $class pkgthree.ExThree {\n");
        xml_.append(" $public $abstract $int get();\n");
        xml_.append("}\n");
        xml_.append("$public $final $class pkgthree.ExFour:ExThree {\n");
        xml_.append(" $public $final $int get(){\n");
        xml_.append("  $return 15i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "res"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(3, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void calculateArgument17Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("pkgthree.*;\n");
        xml_.append("pkgtwo.*;\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public CustList<ExTwo<ExThree>> inst=$new CustList<ExTwo<ExThree>>();\n");
        xml_.append(" $public $int res;\n");
        xml_.append(" {\n");
        xml_.append("  inst.addElts($new ExTwo<ExThree>().pass($new ExFour(),0i).pass($new ExFour(),1i),\n");
        xml_.append("  $new ExTwo<ExThree>().pass($new ExFour(),0i).pass($new ExFour(),1i));\n");
        xml_.append("  res=inst.size();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("pkgthree.ExThree;\n");
        xml_.append("$public $class pkgtwo.ExTwo<K:ExThree> {\n");
        xml_.append(" $public $final K[] array = $new K[2i];\n");
        xml_.append(" $public $final pkgtwo.ExTwo<K> pass(K _v, $int _index){\n");
        xml_.append("  array[_index]=_v;\n");
        xml_.append("  $int unused=_v.get();\n");
        xml_.append("  $return $this;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $abstract $class pkgthree.ExThree {\n");
        xml_.append(" $public $abstract $int get();\n");
        xml_.append("}\n");
        xml_.append("$public $final $class pkgthree.ExFour:ExThree {\n");
        xml_.append(" $public $final $int get(){\n");
        xml_.append("  $return 15i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "res"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void calculateArgument18Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("pkgthree.*;\n");
        xml_.append("pkgtwo.*;\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public CustList<ExTwo<ExThree>> inst=$new CustList<ExTwo<ExThree>>();\n");
        xml_.append(" $public $int res;\n");
        xml_.append(" {\n");
        xml_.append("  inst.addElts($vararg(ExTwo<ExThree>),$firstopt($new ExTwo<ExThree>().pass($new ExFour(),0i).pass($new ExFour(),1i)),\n");
        xml_.append("  $new ExTwo<ExThree>().pass($new ExFour(),0i).pass($new ExFour(),1i));\n");
        xml_.append("  res=inst.size();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("pkgthree.ExThree;\n");
        xml_.append("$public $class pkgtwo.ExTwo<K:ExThree> {\n");
        xml_.append(" $public $final K[] array = $new K[2i];\n");
        xml_.append(" $public $final pkgtwo.ExTwo<K> pass(K _v, $int _index){\n");
        xml_.append("  array[_index]=_v;\n");
        xml_.append("  $int unused=_v.get();\n");
        xml_.append("  $return $this;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $abstract $class pkgthree.ExThree {\n");
        xml_.append(" $public $abstract $int get();\n");
        xml_.append("}\n");
        xml_.append("$public $final $class pkgthree.ExFour:ExThree {\n");
        xml_.append(" $public $final $int get(){\n");
        xml_.append("  $return 15i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "res"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void calculateArgument19Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("pkgthree.*;\n");
        xml_.append("pkgtwo.*;\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public CustList<ExTwo<ExThree>> inst=$new CustList<ExTwo<ExThree>>();\n");
        xml_.append(" $public $int res;\n");
        xml_.append(" {\n");
        xml_.append("  inst.addEltsSec($vararg(ExTwo<ExThree>),$new ExTwo<ExThree>().pass($new ExFour(),0i).pass($new ExFour(),1i),\n");
        xml_.append("  $firstopt($new ExTwo<ExThree>().pass($new ExFour(),0i).pass($new ExFour(),1i)));\n");
        xml_.append("  res=inst.size();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("pkgthree.ExThree;\n");
        xml_.append("$public $class pkgtwo.ExTwo<K:ExThree> {\n");
        xml_.append(" $public $final K[] array = $new K[2i];\n");
        xml_.append(" $public $final pkgtwo.ExTwo<K> pass(K _v, $int _index){\n");
        xml_.append("  array[_index]=_v;\n");
        xml_.append("  $int unused=_v.get();\n");
        xml_.append("  $return $this;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $abstract $class pkgthree.ExThree {\n");
        xml_.append(" $public $abstract $int get();\n");
        xml_.append("}\n");
        xml_.append("$public $final $class pkgthree.ExFour:ExThree {\n");
        xml_.append(" $public $final $int get(){\n");
        xml_.append("  $return 15i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "res"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void calculateArgument20Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("pkgthree.*;\n");
        xml_.append("pkgtwo.*;\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public SecList<ExTwo<ExThree>> inst=$new SecList<ExTwo<ExThree>>();\n");
        xml_.append(" $public $int res;\n");
        xml_.append(" {\n");
        xml_.append("  inst.addPair($new ExTwo<ExThree>().pass($new ExFour(),0i).pass($new ExFour(),1i),\n");
        xml_.append("  $new ExTwo<ExThree>().pass($new ExFour(),0i).pass($new ExFour(),1i));\n");
        xml_.append("  res=inst.size();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("pkgthree.ExThree;\n");
        xml_.append("$public $class pkgtwo.ExTwo<K:ExThree> {\n");
        xml_.append(" $public $final K[] array = $new K[2i];\n");
        xml_.append(" $public $final pkgtwo.ExTwo<K> pass(K _v, $int _index){\n");
        xml_.append("  array[_index]=_v;\n");
        xml_.append("  $int unused=_v.get();\n");
        xml_.append("  $return $this;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $abstract $class pkgthree.ExThree {\n");
        xml_.append(" $public $abstract $int get();\n");
        xml_.append("}\n");
        xml_.append("$public $final $class pkgthree.ExFour:ExThree {\n");
        xml_.append(" $public $final $int get(){\n");
        xml_.append("  $return 15i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_SEC_LIST_PATH, getSecondCustomList());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "res"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void calculateArgument21Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE(4i),\n");
        xml_.append(" TWO;\n");
        xml_.append(" $public $int first;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  first=i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  first=5i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $final $int doubleValue(){\n");
        xml_.append("  $return first;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public $int inst=$values(Ex).length;\n");
        xml_.append("}\n");
        files_.put("pkg/ExCont", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.ExCont");

        Struct str_ = instanceNormal("pkg.ExCont", id_, cont_);
        assertEq("pkg.ExCont", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.ExCont", "inst"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
    }
    @Test
    public void calculateArgument22Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {\n");
        xml_.append(" ONE(4i),\n");
        xml_.append(" TWO;\n");
        xml_.append(" $public $int first;\n");
        xml_.append(" $public ($int i){\n");
        xml_.append("  first=i;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  first=5i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $final $int doubleValue(){\n");
        xml_.append("  $return first;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExCont {\n");
        xml_.append(" $public pkg.Ex inst=$valueOf(Ex,\"ONE\");\n");
        xml_.append("}\n");
        files_.put("pkg/ExCont", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.ExCont");

        Struct str_ = instanceNormal("pkg.ExCont", id_, cont_);
        assertEq("pkg.ExCont", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.ExCont", "inst"));
        assertEq("pkg.Ex", field_.getClassName(cont_));
        assertSame(getStaticField(cont_, new ClassField("pkg.Ex", "ONE")), field_);
    }
    @Test
    public void calculateArgument23Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class [pkgthree.*;pkgtwo.*;] pkg.Ex {\n");
        xml_.append(" $public SecList<ExTwo<ExThree>> inst=$new SecList<ExTwo<ExThree>>();\n");
        xml_.append(" $public $int res;\n");
        xml_.append(" {\n");
        xml_.append("  inst.addPair($new ExTwo<ExThree>().pass($new ExFour(),0i).pass($new ExFour(),1i),\n");
        xml_.append("  $new ExTwo<ExThree>().pass($new ExFour(),0i).pass($new ExFour(),1i));\n");
        xml_.append("  res=inst.size();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class [pkgthree.ExThree;] pkgtwo.ExTwo<K:ExThree> {\n");
        xml_.append(" $public $final K[] array = $new K[2i];\n");
        xml_.append(" $public $final pkgtwo.ExTwo<K> pass(K _v, $int _index){\n");
        xml_.append("  array[_index]=_v;\n");
        xml_.append("  $int unused=_v.get();\n");
        xml_.append("  $return $this;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $abstract $class pkgthree.ExThree {\n");
        xml_.append(" $public $abstract $int get();\n");
        xml_.append("}\n");
        xml_.append("$public $final $class pkgthree.ExFour:ExThree {\n");
        xml_.append(" $public $final $int get(){\n");
        xml_.append("  $return 15i;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        files_.put(CUST_SEC_LIST_PATH, getSecondCustomList());
        ContextEl cont_ = ctxOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");

        Struct str_ = instanceNormal("pkg.Ex", id_, cont_);
        assertTrue(isInitialized(cont_, "pkg.Ex"));
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "res"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, ((NumberStruct)field_).intStruct());
    }

    @Test
    public void calculateArgument24Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex<T>:Int<T> {\n");
        xml_.append(" ONE<java.lang.String>(4i,\"generic\"),\n");
        xml_.append(" TWO<java.lang.Integer>,\n");
        xml_.append(" THREE<ExTwo>(6i,$new ExTwo());\n");
        xml_.append(" $public $int first;\n");
        xml_.append(" $public T second;\n");
        xml_.append(" $public ($int i,T j){\n");
        xml_.append("  first=i;\n");
        xml_.append("  second=j;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  first=5i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal T getSecond() {\n");
        xml_.append("  $return second;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Int<U> {\n");
        xml_.append(" $public $abstract U getSecond();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);

        assertTrue(isInitialized(cont_, "pkg.Ex"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex", "ONE"));
        assertEq("pkg.Ex<java.lang.String>", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "first"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(4, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.Ex", "second"));
        assertEq(STRING, field_.getClassName(cont_));
        assertEq("generic", ((StringStruct)field_).getInstance());
        str_ = getStaticField(cont_, new ClassField("pkg.Ex", "TWO"));
        assertEq("pkg.Ex<java.lang.Integer>", str_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "first"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(5, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.Ex", "second"));
        assertSame(NullStruct.NULL_VALUE,field_);
        str_ = getStaticField(cont_, new ClassField("pkg.Ex", "THREE"));
        assertEq("pkg.Ex<pkg.ExTwo>", str_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "first"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(6, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.Ex", "second"));
        assertEq("pkg.ExTwo", field_.getClassName(cont_));
    }

    @Test
    public void calculateArgument25Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex<T>:Int<T> {\n");
        xml_.append(" ONE<java.lang.String>(4i,                       \"generic\"),\n");
        xml_.append(" TWO<java.lang.Integer>,\n");
        xml_.append(" THREE<ExTwo>(6i,$new ExTwo());\n");
        xml_.append(" $public $int first;\n");
        xml_.append(" $public T second;\n");
        xml_.append(" $public ($int i,T j){\n");
        xml_.append("  first=i;\n");
        xml_.append("  second=j;\n");
        xml_.append(" }\n");
        xml_.append(" $public (){\n");
        xml_.append("  first=5i;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal T getSecond() {\n");
        xml_.append("  $return second;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $interface pkg.Int<U> {\n");
        xml_.append(" $public $abstract U getSecond();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append("}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);

        assertTrue(isInitialized(cont_, "pkg.Ex"));
        Struct str_ = getStaticField(cont_, new ClassField("pkg.Ex", "ONE"));
        assertEq("pkg.Ex<java.lang.String>", str_.getClassName(cont_));
        Struct field_;
        field_ = getField(str_, new ClassField("pkg.Ex", "first"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(4, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.Ex", "second"));
        assertEq(STRING, field_.getClassName(cont_));
        assertEq("generic", ((StringStruct)field_).getInstance());
        str_ = getStaticField(cont_, new ClassField("pkg.Ex", "TWO"));
        assertEq("pkg.Ex<java.lang.Integer>", str_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "first"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(5, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.Ex", "second"));
        assertSame(NullStruct.NULL_VALUE,field_);
        str_ = getStaticField(cont_, new ClassField("pkg.Ex", "THREE"));
        assertEq("pkg.Ex<pkg.ExTwo>", str_.getClassName(cont_));
        field_ = getField(str_, new ClassField("pkg.Ex", "first"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(6, ((NumberStruct)field_).intStruct());
        field_ = getField(str_, new ClassField("pkg.Ex", "second"));
        assertEq("pkg.ExTwo", field_.getClassName(cont_));
    }
    @Test
    public void calculateArgument30Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$static pkg.ExTwo.exmethtwo;\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return exmethtwo();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmethtwo(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(9, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument30_Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$static pkg.ExTwo.exmethtwo;\n");
        xml_.append("$static pkg.ExTwo.exmethtwo;\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return exmethtwo();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmethtwo(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(9, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument31Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$static pkg.ExTwo.*;\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return exmethtwo();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmethtwo(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(9, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument32Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$static pkg.ExTwo.exmethtwo;\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return exmethtwo();\n");
        xml_.append(" }\n");
        xml_.append(" $public $static $int exmethtwo(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 2i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmethtwo(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(10, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }


    @Test
    public void calculateArgument33Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$static pkgtwo.ExThree.*;\n");
        xml_.append("$static pkg.ExTwo.exmethtwo;\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return exmethtwo();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmethtwo(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.ExThree {\n");
        xml_.append(" $public $static $int exmethtwo(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 3i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(9, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument34Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$static pkg.ExTwo.exmethtwo;\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return exmethtwo;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmethtwo=9i;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(9, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument35Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$static pkg.ExTwo.*;\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return exmethtwo;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmethtwo=9i;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(9, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }

    @Test
    public void calculateArgument36Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$static pkg.ExTwo.exmethtwo;\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmethtwo=10i;\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return exmethtwo;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return Ex.exmeth();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmethtwo=9i;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(10, getNumber(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculateArgument36_Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$static pkg.ExTwo.*;\n");
        xml_.append("$static pkg.ExTwo.exmethtwo;\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return exmethtwo;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return Ex.exmeth();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmethtwo=9i;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(9, getNumber(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculateArgument36__Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$static pkg.ExTwo.*;\n");
        xml_.append("$static pkg.ExTwo.exmethtwo;\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return exmethtwo();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Apply {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return Ex.exmeth();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmethtwo(){\n");
        xml_.append("  $return 9;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(9, getNumber(calculateNormal("pkg.Apply", id_, cont_)));
    }
    @Test
    public void calculateArgument37Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$static pkgtwo.ExThree.*;\n");
        xml_.append("$static pkg.ExTwo.exmethtwo;\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return exmethtwo;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmethtwo=9i;\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkgtwo.ExThree {\n");
        xml_.append(" $public $static $int exmethtwo=10i;\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(9, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument38Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$static java.lang.$math.plus;\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return plus(2,4);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(6, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    @Test
    public void calculateArgument39Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$static java.lang.StringBuilder.*;\n");
        xml_.append("$static pkg.ExTwo.exmethtwo;\n");
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return exmethtwo();\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {\n");
        xml_.append(" $public $static $int exmethtwo(){\n");
        xml_.append("  $long t;\n");
        xml_.append("  t=8;\n");
        xml_.append("  $return 1i+$($int)t;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = ctxOk(files_);
        MethodId id_ = getMethodId("exmeth");
        assertEq(9, getNumber(calculateNormal("pkg.Ex", id_, cont_)));
    }
    private static String getSecondCustomList() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.SecList<V> :CustList<V>{\n");
        xml_.append(" $public $normal $void addPair(V first,V second){\n");
        xml_.append("  addElts($vararg(V),$firstopt(first),second);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
    private static String getCustomList() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustList<U> :$iterable<U>{\n");
        xml_.append(" $private U[] list;\n");
        xml_.append(" $private $int length;\n");
        xml_.append(" $public (){\n");
        xml_.append("  list=$new U[0i];\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void addElts(U... elt){\n");
        xml_.append("  addInnerlyElts(elt);\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void addEltsSec(U first,U... elt){\n");
        xml_.append("  add(length,first);\n");
        xml_.append("  $foreach(U u:elt){\n");
        xml_.append("   add(length,u);\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void addInnerlyElts(U... elt){\n");
        xml_.append("  $foreach(U u:elt){\n");
        xml_.append("   add(length,u);\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void add(U elt){\n");
        xml_.append("  add(length,elt);\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void add($int index,U elt){\n");
        xml_.append("  U[] newlist=$new U[length+1i];\n");
        xml_.append("  $iter($int i=0i;index;1i){\n");
        xml_.append("   newlist[i]=list[i];\n");
        xml_.append("  }\n");
        xml_.append("  newlist[index]=elt;\n");
        xml_.append("  $iter($int i=index+1i;length+1i;1i){\n");
        xml_.append("   newlist[i]=list[i-1i];\n");
        xml_.append("  }\n");
        xml_.append("  length++;\n");
        xml_.append("  list=newlist;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int size(){\n");
        xml_.append("  $return length;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal U get($int index){\n");
        xml_.append("  $return list[index];\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void set($int index,U elt){\n");
        xml_.append("  list[index]=elt;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void remove($int index){\n");
        xml_.append("  $iter($int i=index;length-1i;1i){\n");
        xml_.append("   list[i]=list[i+1i];\n");
        xml_.append("  }\n");
        xml_.append("  list[length-1i]=$null;\n");
        xml_.append("  length--;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $iterator<U> iterator(){\n");
        xml_.append("  $return $new pkg.CustIter<U>($this);\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
    private static String getCustomIterator() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustIter<T> :$iterator<T>{\n");
        xml_.append(" $private pkg.CustList<T> list;\n");
        xml_.append(" $private $int length;\n");
        xml_.append(" $private $int index;\n");
        xml_.append(" $public (pkg.CustList<T> i){\n");
        xml_.append("  list=i;\n");
        xml_.append("  length=list.size();\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal T next(){\n");
        xml_.append("  T out=list.get(index);\n");
        xml_.append("  index++;\n");
        xml_.append("  $return out;\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $boolean hasNext(){\n");
        xml_.append("  $return index<length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        return xml_.toString();
    }
}
