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
public final class ProcessMethodIterableGenericTest extends ProcessMethodCommon {
    private static final String CUST_ITER_PATH = "pkg/CustIter";
    private static final String CUST_LIST_PATH = "pkg/CustList";

    @Test
    public void instanceArgument121Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.CustList<java.lang.Number> inst=$new pkg.CustList<java.lang.Number>():\n");
        xml_.append(" $public $int res:\n");
        xml_.append(" {\n");
        xml_.append("  inst;;;add(3i):\n");
        xml_.append("  inst;;;add(1i):\n");
        xml_.append("  inst;;;add(2i):\n");
        xml_.append("  res;;;=inst;;;size():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().getErrorsDet().isEmpty());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        ProcessMethod.initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "res"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(3, (Number)field_.getInstance());
    }

    @Test
    public void instanceArgument122Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.CustList<java.lang.Number> inst=$new pkg.CustList<java.lang.Number>():\n");
        xml_.append(" $public $int one:\n");
        xml_.append(" $public $int two:\n");
        xml_.append(" $public $int three:\n");
        xml_.append(" {\n");
        xml_.append("  inst;;;add(3i):\n");
        xml_.append("  inst;;;add(1i):\n");
        xml_.append("  inst;;;add(2i):\n");
        xml_.append("  one;;;=inst;;;get(0i).intValue():\n");
        xml_.append("  two;;;=inst;;;get(1i).intValue():\n");
        xml_.append("  three;;;=inst;;;get(2i).intValue():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().getErrorsDet().isEmpty());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        ProcessMethod.initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "one"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(3, (Number)field_.getInstance());
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "two"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(1, (Number)field_.getInstance());
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "three"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, (Number)field_.getInstance());
    }
    @Test
    public void instanceArgument123Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.CustList<java.lang.Number> inst=$new pkg.CustList<java.lang.Number>():\n");
        xml_.append(" $public $int res:\n");
        xml_.append(" {\n");
        xml_.append("  inst;;;add(3i):\n");
        xml_.append("  inst;;;add(1i):\n");
        xml_.append("  inst;;;add(2i):\n");
        xml_.append("  $foreach(java.lang.Number e:inst;;;){\n");
        xml_.append("   res;;;+=e;intValue():\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().getErrorsDet().isEmpty());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        ProcessMethod.initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "res"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(6, (Number)field_.getInstance());
    }
    @Test
    public void instanceArgument124Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.CustList<java.lang.Number> inst=$new pkg.CustList<java.lang.Number>():\n");
        xml_.append(" $public $int res=123i:\n");
        xml_.append(" {\n");
        xml_.append("  $foreach(java.lang.Number e:inst;;;){\n");
        xml_.append("   res;;;+=e;intValue():\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().getErrorsDet().isEmpty());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        ProcessMethod.initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "res"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(123, (Number)field_.getInstance());
    }
    @Test
    public void instanceArgument125Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.CustList<java.lang.Number> inst=$new pkg.CustList<java.lang.Number>():\n");
        xml_.append(" $public $int res:\n");
        xml_.append(" {\n");
        xml_.append("  inst;;;add(3i):\n");
        xml_.append("  inst;;;add(1i):\n");
        xml_.append("  inst;;;add(2i):\n");
        xml_.append("  res;;;=inst;;;iterator().next().intValue():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().getErrorsDet().isEmpty());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        ProcessMethod.initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "res"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(3, (Number)field_.getInstance());
    }
    @Test
    public void instanceArgument126Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst:\n");
        xml_.append(" $public pkg.ExTwo<java.lang.String> ance:\n");
        xml_.append(" {\n");
        xml_.append("  $try{\n");
        xml_.append("   ance;;;=$new pkg.ExTwo<java.lang.String>(8i):\n");
        xml_.append("  }\n");
        xml_.append("  $catch(code.expressionlanguage.exceptions.DynamicCastClassException e){\n");
        xml_.append("   inst;;;=$new pkg.ExTwo<java.lang.Number>(8i):\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> {\n");
        xml_.append(" $public java.lang.Object inst:\n");
        xml_.append(" $public (java.lang.Object i){\n");
        xml_.append("  inst;;;=$class(\"#T\",i;.;):\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().getErrorsDet().isEmpty());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        ProcessMethod.initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<java.lang.Number>", field_.getClassName(cont_));
        Struct subField_;
        subField_ = field_.getFields().getVal(new ClassField("pkg.ExTwo", "inst"));
        assertEq(INTEGER, subField_.getClassName(cont_));
        assertEq(8, (Number) subField_.getInstance());
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertTrue(field_.isNull());
    }

    @Test
    public void instanceArgument127Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>():\n");
        xml_.append(" $public $int ance:\n");
        xml_.append(" {\n");
        xml_.append("  $try{\n");
        xml_.append("   ance;;;=inst;;;$classchoice(pkg.ExTwo)get(\"\"):\n");
        xml_.append("  }\n");
        xml_.append("  $catch(code.expressionlanguage.exceptions.DynamicCastClassException e){\n");
        xml_.append("   ance;;;=2i:\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> {\n");
        xml_.append(" $public $normal $int get(#T i){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<#U> :pkg.ExTwo<#U>{\n");
        xml_.append(" $public $normal $int get(#U i){\n");
        xml_.append("  $return 3i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().getErrorsDet().isEmpty());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        ProcessMethod.initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, (Number)field_.getInstance());
    }
    @Test
    public void instanceArgument128Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.CustList<java.lang.Number> inst=$new pkg.CustList<java.lang.Number>():\n");
        xml_.append(" $public $int res:\n");
        xml_.append(" {\n");
        xml_.append("  inst;;;add(3i):\n");
        xml_.append("  inst;;;add(1i):\n");
        xml_.append("  inst;;;add(2i):\n");
        xml_.append("  java.lang.Number mynb=inst;;;iterator().next():\n");
        xml_.append("  res;;;=mynb;.intValue():\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        files_.put(CUST_ITER_PATH, getCustomIterator());
        files_.put(CUST_LIST_PATH, getCustomList());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().getErrorsDet().isEmpty());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        ProcessMethod.initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "res"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(3, (Number)field_.getInstance());
    }
    @Test
    public void instanceArgument129Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExTwo<java.lang.Number>(8i):\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T> {\n");
        xml_.append(" $public $int nb:\n");
        xml_.append(" $public [#T inst:\n");
        xml_.append(" $public (#T i){\n");
        xml_.append("  $this(i;.;,2i):\n");
        xml_.append(" }\n");
        xml_.append(" $public (#T i,$int j){\n");
        xml_.append("  inst;;;=$new [#T[](i;.;):\n");
        xml_.append("  nb;;;=j;.;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().getErrorsDet().isEmpty());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        ProcessMethod.initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        assertTrue(cont_.getClasses().isInitialized("pkg.Ex"));
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExTwo<java.lang.Number>", field_.getClassName(cont_));
        Struct subField_;
        subField_ = field_.getFields().getVal(new ClassField("pkg.ExTwo", "inst"));
        assertEq(ARR_NUMBER, subField_.getClassName(cont_));
        Struct[] nbs_ = (Struct[]) subField_.getInstance();
        assertEq(1, nbs_.length);
        assertEq(8, (Number) nbs_[0].getInstance());
        subField_ = field_.getFields().getVal(new ClassField("pkg.ExTwo", "nb"));
        assertEq(2, (Number) subField_.getInstance());
    }
    @Test
    public void instanceArgument130Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>(5i):\n");
        xml_.append(" $public $int ance=inst;;;get(8I):\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<#T:java.lang.Number> {\n");
        xml_.append(" $public #T inst:\n");
        xml_.append(" $public (#T i){\n");
        xml_.append("  inst;;;=i;.;:\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(#T i){\n");
        xml_.append("  $return 1i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<#U:java.lang.Number> :pkg.ExTwo<#U>{\n");
        xml_.append(" $public (#U i){\n");
        xml_.append("  $super(i;.;):\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $int get(#U i){\n");
        xml_.append("  $return 3i:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().getErrorsDet().isEmpty());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        ProcessMethod.initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        Struct subField_;
        subField_ = field_.getFields().getVal(new ClassField("pkg.ExTwo", "inst"));
        assertEq(5, (Number)subField_.getInstance());
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(3, (Number)field_.getInstance());
    }
    @Test
    public void instanceArgument131Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public pkg.ExTwo<java.lang.Number> inst=$new pkg.ExThree<java.lang.Number>():\n");
        xml_.append(" $public java.lang.Number ance:\n");
        xml_.append(" {\n");
        xml_.append("  $try{\n");
        xml_.append("   inst;;;$classchoice(pkg.ExThree)get=\"\":\n");
        xml_.append("   inst;;;get;;;=1i:\n");
        xml_.append("   ance;;;=inst;;;getter():\n");
        xml_.append("  }\n");
        xml_.append("  $catch(code.expressionlanguage.exceptions.DynamicCastClassException e){\n");
        xml_.append("   ance;;;=2i:\n");
        xml_.append("  }\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo<#T> {\n");
        xml_.append(" $public #T get:\n");
        xml_.append(" $public $abstract #T getter(){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<#U> :pkg.ExTwo<#U>{\n");
        xml_.append("$interfaces(pkg.ExTwo)\n");
        xml_.append(" $public #U get:\n");
        xml_.append(" $public $normal #U getter(){\n");
        xml_.append("  $return get;;;:\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = contextEl();
        Classes.validateAll(files_, cont_);
        assertTrue(cont_.getClasses().getErrorsDet().isEmpty());
        CustList<Argument> args_ = new CustList<Argument>();
        ConstructorId id_ = getConstructorId("pkg.Ex");
        ProcessMethod.initializeClass("pkg.Ex", cont_);
        Argument ret_;
        ret_ = instanceArgument("pkg.Ex", null, id_, args_, cont_);
        Struct str_ = ret_.getStruct();
        assertEq("pkg.Ex", str_.getClassName(cont_));
        Struct field_;
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "inst"));
        assertEq("pkg.ExThree<java.lang.Number>", field_.getClassName(cont_));
        field_ = str_.getFields().getVal(new ClassField("pkg.Ex", "ance"));
        assertEq(INTEGER, field_.getClassName(cont_));
        assertEq(2, (Number)field_.getInstance());
    }
    private static String getCustomList() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustList<#U> :$iterable<#U>{\n");
        xml_.append(" $private [#U list:\n");
        xml_.append(" $private $int length:\n");
        xml_.append(" $public (){\n");
        xml_.append("  list;;;=$new [#U(0i):\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void add(#U elt){\n");
        xml_.append("  add(length;;;,elt;.;):\n");
        xml_.append(" }\n");
        xml_.append(" $public $normal $void add($int index,#U elt){\n");
        xml_.append("  [#U newlist=$new [#U(length;;;+1i):\n");
        xml_.append("  $for($int i=0i:index;.;:1i){\n");
        xml_.append("   newlist;.[i;]=list;;;[i;]:\n");
        xml_.append("  }\n");
        xml_.append("  newlist;.[index;.;]=elt;.;:\n");
        xml_.append("  $for($int i=index;.;+1i:length;;;+1i:1i){\n");
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
        xml_.append("  $for($int i=index;.;:length;;;-1i:1i){\n");
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
