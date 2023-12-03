package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AnnotationTypeInfo;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.annotation.ExportAnnotationUtil;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.exec.variables.*;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.blocks.AnaAnonFctContent;
import code.expressionlanguage.fwd.blocks.ExecAnonFctContent;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.methods.ProcessMethodCommon;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.sample.CustLgNames;
import code.expressionlanguage.structs.*;
import code.util.*;
import code.util.core.IndexConstants;
import org.junit.Test;

public final class ExecTemplatesTest extends ProcessMethodCommon {

    private static final String CUST_CLASS = "pkg.CustClass";
    private static final String ARR_ARR_CUST_CLASS = "[[pkg.CustClass";
    private static final String ARR_ARR_ARR_CUST_CLASS = "[[[pkg.CustClass";
    private static final String ARR_CUST_CLASS = "[pkg.CustClass";
    @Test
    public void getParent1Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl c_ = validated(files_);
        assertNotNull(getParent(new IntStruct(1),c_,0, getStackCall(c_)));
    }

    @Test
    public void getParent2Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append(" $public $class Inner{\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl c_ = validated(files_);
        ExecRootBlock classBody_ = c_.getClasses().getClassBody("pkg.Ex");
        Struct par_  = c_.getInit().processInit(c_,NullStruct.NULL_VALUE,new ExecFormattedRootBlock(classBody_,"pkg.Ex"), "",-1);
        ExecRootBlock root_ = c_.getClasses().getClassBody("pkg.Ex..Inner");
        Struct in_ = c_.getInit().processInit(c_,par_,new ExecFormattedRootBlock(root_,"pkg.Ex..Inner"), "",-1);
        getParent(in_, c_, 0, getStackCall(c_));
        assertNotNull(c_);
    }
    @Test
    public void getParent3Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append(" $public $class Inner{\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl c_ = validated(files_);
        ExecRootBlock classBody_ = c_.getClasses().getClassBody("pkg.Ex");
        Struct par_  = c_.getInit().processInit(c_,NullStruct.NULL_VALUE,new ExecFormattedRootBlock(classBody_,"pkg.Ex"), "",-1);
        ExecRootBlock classBody2_ = c_.getClasses().getClassBody("pkg.Ex..Inner");
        Struct in_ = c_.getInit().processInit(c_,par_,new ExecFormattedRootBlock(classBody2_,"pkg.Ex..Inner"), "",-1);
        Struct inTwo_ = c_.getInit().processInit(c_,in_,new ExecFormattedRootBlock(classBody2_,"pkg.Ex..Inner"), "",-1);
        getParent(inTwo_, c_, 0, getStackCall(c_));
        assertNotNull(c_);
    }

    @Test
    public void getParent4Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl c_ = validated(files_);
        ArrayStruct arr_ = ExecArrayTemplates.newCustomArray(c_.getStandards().getContent().getPrimTypes().getAliasPrimInteger(),Ints.newList(0),c_);
        assertNotNull(getParent(arr_, c_, getStackCall(c_)));
    }

    @Test
    public void getParent5Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl c_ = validated(files_);
        ArrayStruct arr_ = ExecArrayTemplates.newCustomArray(c_.getStandards().getContent().getPrimTypes().getAliasPrimInteger(),Ints.newList(0),c_);
        assertNotNull(getParent(arr_, c_, getStackCall(c_)));
    }

    @Test
    public void getParent6Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl c_ = validated(files_);
        ArrayStruct arr_ = ExecArrayTemplates.newCustomArray(c_.getStandards().getContent().getPrimTypes().getAliasPrimInteger(),Ints.newList(0),c_);
        assertNotNull(getParent(arr_, c_, getStackCall(c_)));
    }

    @Test
    public void getParent7Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl c_ = validated(files_);
        ArrayStruct arr_ = ExecArrayTemplates.newCustomArray(c_.getStandards().getContent().getPrimTypes().getAliasPrimInteger(),Ints.newList(0),c_);
        StackCall stackCall_ = getStackCall(c_);
        assertSame(arr_, getParent(arr_, c_, stackCall_));
        assertNull(stackCall_.getCallingState());
    }

    @Test
    public void newCustomArray1Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustClass{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = validated(files_);
        Ints dims_ = Ints.newList(1);
        ArrayStruct customArray_ = ExecArrayTemplates.newCustomArray(CUST_CLASS, dims_, cont_);
        assertEq(ARR_CUST_CLASS, customArray_.getClassName());
        Struct[] instance_ = customArray_.getInstance();
        assertEq(1, instance_.length);
        Struct elt_ = instance_[0];
        assertSame(NullStruct.NULL_VALUE,elt_);
    }

    @Test
    public void newCustomArray2Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustClass{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = validated(files_);
        Ints dims_ = Ints.newList(2);
        ArrayStruct customArray_ = ExecArrayTemplates.newCustomArray(CUST_CLASS, dims_, cont_);
        assertEq(ARR_CUST_CLASS, customArray_.getClassName());
        Struct[] instance_ = customArray_.getInstance();
        assertEq(2, instance_.length);
        Struct elt_ = instance_[0];
        assertSame(NullStruct.NULL_VALUE,elt_);
        elt_ = instance_[1];
        assertSame(NullStruct.NULL_VALUE,elt_);
    }

    @Test
    public void newCustomArray3Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustClass{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = validated(files_);
        Ints dims_ = Ints.newList(2,3);
        ArrayStruct customArray_ = ExecArrayTemplates.newCustomArray(CUST_CLASS, dims_, cont_);
        assertEq(ARR_ARR_CUST_CLASS, customArray_.getClassName());
        Struct[] instance_ = customArray_.getInstance();
        assertEq(2, instance_.length);
        ArrayStruct subArray_ = (ArrayStruct) instance_[0];
        assertEq(ARR_CUST_CLASS, subArray_.getClassName());
        Struct[] subInstance_ = subArray_.getInstance();
        assertEq(3, subInstance_.length);
        Struct elt_ = subInstance_[0];
        assertSame(NullStruct.NULL_VALUE,elt_);
        elt_ = subInstance_[1];
        assertSame(NullStruct.NULL_VALUE,elt_);
        elt_ = subInstance_[2];
        assertSame(NullStruct.NULL_VALUE,elt_);
        subArray_ = (ArrayStruct) instance_[1];
        assertEq(ARR_CUST_CLASS, subArray_.getClassName());
        subInstance_ = subArray_.getInstance();
        assertEq(3, subInstance_.length);
        elt_ = subInstance_[0];
        assertSame(NullStruct.NULL_VALUE,elt_);
        elt_ = subInstance_[1];
        assertSame(NullStruct.NULL_VALUE,elt_);
        elt_ = subInstance_[2];
        assertSame(NullStruct.NULL_VALUE,elt_);
    }

    @Test
    public void newCustomArray4Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustClass{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = validated(files_);
        Ints dims_ = Ints.newList(4,2,3);
        ArrayStruct customArray_ = ExecArrayTemplates.newCustomArray(CUST_CLASS, dims_, cont_);
        assertEq(ARR_ARR_ARR_CUST_CLASS, customArray_.getClassName());
        Struct[] instance_ = customArray_.getInstance();
        assertEq(4, instance_.length);
    }
    @Test
    public void reflectFormat1Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = validated(files_);
        String first_ = "pkg.Ex<?java.lang.Object>";
        String second_ = "pkg.Ex<[#T>";
        assertEq("pkg.Ex<?[java.lang.Object>", reflectFormat(context_, first_, second_));
    }
    @Test
    public void reflectFormat2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = validated(files_);
        String first_ = "pkg.Ex<!java.lang.Object>";
        String second_ = "pkg.Ex<[#T>";
        assertEq("pkg.Ex<![java.lang.Object>", reflectFormat(context_, first_, second_));
    }
    @Test
    public void reflectFormat3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = validated(files_);
        String first_ = "pkg.Ex<?>";
        String second_ = "pkg.Ex<[#T>";
        assertEq("pkg.Ex<?>", reflectFormat(context_, first_, second_));
    }
    @Test
    public void reflectFormat4Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = validated(files_);
        String first_ = "pkg.Ex<?>";
        String second_ = "pkg.Ex<[#E>";
        assertEq("pkg.Ex<[#E>", reflectFormat(context_, first_, second_));
    }
    @Test
    public void reflectFormat5Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = validated(files_);
        String first_ = "pkg.Ex<?>";
        String second_ = "[#E";
        assertEq("[#E", reflectFormat(context_, first_, second_));
    }
    @Test
    public void reflectFormat6Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = validated(files_);
        String first_ = "pkg.Ex<?java.lang.Object>";
        String second_ = "pkg.Ex<![#T>";
        assertEq("pkg.Ex<![java.lang.Object>", reflectFormat(context_, first_, second_));
    }
    @Test
    public void reflectFormat7Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = validated(files_);
        String first_ = "pkg.Ex<!java.lang.Object>";
        String second_ = "pkg.Ex<?[#T>";
        assertEq("pkg.Ex<?[java.lang.Object>", reflectFormat(context_, first_, second_));
    }
    @Test
    public void reflectFormat8Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = validated(files_);
        String first_ = "pkg.Ex<?java.lang.Object>";
        String second_ = "pkg.Ex<?[#T>";
        assertEq("pkg.Ex<?[java.lang.Object>", reflectFormat(context_, first_, second_));
    }
    @Test
    public void reflectFormat9Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = validated(files_);
        String first_ = "pkg.Ex<!java.lang.Object>";
        String second_ = "pkg.Ex<![#T>";
        assertEq("pkg.Ex<![java.lang.Object>", reflectFormat(context_, first_, second_));
    }

    @Test
    public void getGenericTypeByBases3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>:ExTwo<$iterable<?T>> {}\n");
        xml_.append("$public $class pkg.ExTwo<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = validated(files_);
        String first_ = "pkg.Ex<java.lang.Object>";
        String second_ = "pkg.ExTwo";
        String t_ = getFullObject(context_, first_, second_);
        assertEq("pkg.ExTwo<java.lang.$iterable<?java.lang.Object>>",t_);
    }

    @Test
    public void getGenericTypeByBases4Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>:ExTwo<$iterable<?T>> {}\n");
        xml_.append("$public $class pkg.ExTwo<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = validated(files_);
        String first_ = "pkg.Ex<java.lang.Object>";
        String second_ = "[pkg.ExTwo";
        String t_ = ExecInherits.getQuickFullTypeByBases(first_, second_, context_);
        assertEq("",t_);
    }

    @Test
    public void getGenericTypeByBases5Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>:ExTwo<$iterable<?T>> {}\n");
        xml_.append("$public $class pkg.ExTwo<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = validated(files_);
        String first_ = "[pkg.Ex<java.lang.Object>";
        String second_ = "pkg.ExTwo";
        String t_ = ExecInherits.getQuickFullTypeByBases(first_, second_, context_);
        assertEq("",t_);
    }

    @Test
    public void getGenericTypeByBases7Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>:ExTwo<$iterable<?T>> {}\n");
        xml_.append("$public $class pkg.ExTwo<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = validated(files_);
        String first_ = "pkg.Ex";
        String second_ = "pkg.ExTwo";
        String t_ = getFullObject(context_, first_, second_);
        assertEq("pkg.ExTwo<java.lang.$iterable<?#T>>",t_);
    }

    @Test
    public void getGenericTypeByBases16Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>:ExTwo<$iterable<?T>> {}\n");
        xml_.append("$public $class pkg.ExTwo<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = validated(files_);
        String first_ = "pkg.Ex<java.lang.String>";
        String second_ = "";
        String t_ = getSuperGeneric(context_, first_, second_);
        assertEq("",t_);
    }


    @Test
    public void getGenericTypeByBases17Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>:ExTwo<$iterable<?T>> {}\n");
        xml_.append("$public $class pkg.ExTwo<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = validated(files_);
        String first_ = "pkg.Ex";
        String second_ = "";
        String t_ = getSuperGeneric(context_, first_, second_);
        assertEq("",t_);
    }

    @Test
    public void getGenericTypeByBases18Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        xml_.append("$public $annotation pkg.ExAnnot {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = validated(files_);
        String first_ = "pkg.ExAnnot";
        String second_ = "pkg.Ex";
        String t_ = getSuperGeneric(context_, first_, second_);
        assertEq("",t_);
    }

    @Test
    public void getGenericTypeByBases19Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        xml_.append("$public $annotation pkg.ExAnnot {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = validated(files_);
        String first_ = "$void";
        String second_ = "pkg.Ex";
        String t_ = getFullTypeByBases(context_, first_, second_);
        assertEq("",t_);
    }

    @Test
    public void getGenericTypeByBases20Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        xml_.append("$public $annotation pkg.ExAnnot {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = validated(files_);
        String first_ = "pkg.Ex";
        String second_ = "$void";
        String t_ = getFullTypeByBases(context_, first_, second_);
        assertEq("",t_);
    }

    @Test
    public void getGenericTypeByBases21Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        xml_.append("$public $annotation pkg.ExAnnot {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = validated(files_);
        String first_ = "pkg.Ex";
        String second_ = "[java.lang.Object";
        String t_ = getFullTypeByBases(context_, first_, second_);
        assertEq("",t_);
    }

    @Test
    public void getGenericTypeByBases22Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        xml_.append("$public $annotation pkg.ExAnnot {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = validated(files_);
        String first_ = "";
        String second_ = "";
        String t_ = getFullTypeByBases(context_, first_, second_);
        assertEq("",t_);
    }

    @Test
    public void setCheckedElements1Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = validated(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(Argument.createVoid());
        StackCall stackCall_ = getStackCall(cont_);
        ExecArrayTemplates.checkedElements(ArrayStruct.instance("[$int", args_), cont_, stackCall_);
        assertNotNull(getTrueException(stackCall_));
    }
    @Test
    public void okArgsSetSw() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = validated(files_);
        StackCall stackCall_ = getStackCall(cont_);
        assertNotNull(ExecTemplates.okArgsSetSw(null, new ExecFormattedRootBlock((ExecRootBlock)null,""), null, cont_, stackCall_, new CustList<Argument>()).getError());
        assertNotNull(getTrueException(stackCall_));
    }
    @Test
    public void okArgsSetSwCall() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {$public $static $void m($int i){}}\n");
        xml_.append("$public $annotation pkg.ExAnnot {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = validated(files_);
        StackCall stackCall_ = getStackCall(cont_);
        ExecSwitchInstanceMethod ex_ = new ExecSwitchInstanceMethod("",false,"",null,"$int", "",new ExecAnonFctContent(new AnaAnonFctContent()));
        ExecRootBlock classBody_ = cont_.getClasses().getClassBody("pkg.Ex");
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(classBody_, new ExecFormattedRootBlock(classBody_,"pkg.Ex"), null);
        stackCall_.addInternPage(instancingClass_);
        ExecTemplates.okArgsSetSwCall( ex_, cont_, stackCall_, Argument.createVoid(), stackCall_.getLastPage().getGlobalClass(), stackCall_.getLastPage().getContentEx());
        assertNotNull(getTrueException(stackCall_));
    }
    @Test
    public void okArgs1Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = validated(files_);
        MethodId id_ = new MethodId(MethodAccessKind.STATIC,"method", new StringList("$int"),true);
        ArrayStruct arr_ = defaultArray1(NullStruct.NULL_VALUE, "[$int");
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(new Argument(arr_));
        assertNull(ExecTemplates.okArgsSet(id_, args_, cont_, getStackCall(cont_)));
    }
    @Test
    public void okArgs2Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = validated(files_);
        MethodId id_ = new MethodId(MethodAccessKind.STATIC,"method", new StringList("java.lang.Number"),true);
        ArrayStruct arr_ = defaultArray2("[java.lang.Number");
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(new Argument(arr_));
        assertNull(ExecTemplates.okArgsSet(id_, args_, cont_, getStackCall(cont_)));
    }
    @Test
    public void okArgs3Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = validated(files_);
        MethodId id_ = new MethodId(MethodAccessKind.STATIC,"method", new StringList("java.lang.Number"),true);
        ArrayStruct arr_ = new ArrayStruct(1,"[java.lang.Number");
        arr_.set(0,new StringStruct(""));
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(new Argument(arr_));
        arr_ = new ArrayStruct(1,"[java.lang.Number");
        arr_.set(0,new StringStruct(""));
        args_.add(new Argument(arr_));
        StackCall stackCall_ = getStackCall(cont_);
        assertNotNull(ExecTemplates.okArgsSet(id_, args_, cont_, stackCall_));
    }
    @Test
    public void okArgs4Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = validated(files_);
        MethodId id_ = new MethodId(MethodAccessKind.STATIC,"method", new StringList(""),false);
        ArrayStruct arr_ = defaultArray2("[java.lang.Number");
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(new Argument(arr_));
        StackCall stackCall_ = getStackCall(cont_);
        assertNotNull(ExecTemplates.okArgsSet(id_, args_, cont_, stackCall_));
    }
    @Test
    public void okArgs5Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<E> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = validated(files_);
        MethodId id_ = new MethodId(MethodAccessKind.INSTANCE,"method", new StringList(""),false);
        ExecRootBlock classBody_ = cont_.getClasses().getClassBody("pkg.Ex");
        Struct atr_ = cont_.getInit().processInit(cont_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(classBody_,"pkg.Ex<$int>"), "", -1);
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(new Argument(atr_));
        StackCall stackCall_ = getStackCall(cont_);
        assertNotNull(ExecTemplates.okArgsSet(id_, args_, cont_, stackCall_));
    }
    @Test
    public void okArgs6Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<E> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = validated(files_);
        MethodId id_ = new MethodId(MethodAccessKind.INSTANCE,"method", new StringList("pkg.Ex"),false);
        ExecRootBlock classBody_ = cont_.getClasses().getClassBody("pkg.Ex");
        Struct atr_ = cont_.getInit().processInit(cont_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(classBody_,"pkg.Ex<$int>"), "", -1);
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(new Argument(atr_));
        StackCall stackCall_ = getStackCall(cont_);
        assertNotNull(ExecTemplates.okArgsSet(id_, args_, cont_, stackCall_));
    }
    @Test
    public void okArgs7Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = validated(files_);
        MethodId id_ = new MethodId(MethodAccessKind.STATIC,"method", new StringList(""),false);
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(new Argument(NullStruct.NULL_VALUE));
        StackCall stackCall_ = getStackCall(cont_);
        assertNotNull(ExecTemplates.okArgsSet(id_, args_, cont_, stackCall_));
    }
    @Test
    public void okArgs8Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<E> { $public $int get($int...v){$return 0;}}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = validated(files_);
        MethodId id_ = new MethodId(MethodAccessKind.INSTANCE,"get", new StringList("$int"),true);
        ArrayStruct arr_ = defaultArray2("[$int");
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(new Argument(arr_));
        ArgumentListCall l_ = ArgumentListCall.wrapCall(args_);
        ExecRootBlock classBody_ = cont_.getClasses().getClassBody("pkg.Ex");
        StackCall stackCall_ = getStackCall(cont_);
        addPage(ExecutingUtil.createInstancingClass(classBody_,new ExecFormattedRootBlock(classBody_,"pkg.Ex"),null), stackCall_);
        ExecInheritsAdv.checkQuick("","pkg.Ex<$int>",cont_, stackCall_);
        assertNotNull(getTrueException(stackCall_));
        stackCall_.setNullCallingState();
        ExecTemplates.okArgsSet(ExecClassesUtil.getMethodBodiesById(classBody_,id_).first(), new ExecFormattedRootBlock(classBody_,"pkg.Ex<$int>"), null, l_, cont_, stackCall_);
    }
    @Test
    public void okArgs9Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<E> { $public $int get($int...v){$return 0;}}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = validated(files_);
        MethodId id_ = new MethodId(MethodAccessKind.INSTANCE,"get", new StringList("$int"),true);
        CustList<Argument> args_ = new CustList<Argument>();
        ArgumentListCall l_ = ArgumentListCall.wrapCall(args_);
        ExecRootBlock classBody_ = cont_.getClasses().getClassBody("pkg.Ex");
        StackCall stackCall_ = getStackCall(cont_);
        ExecTemplates.okArgsSet(ExecClassesUtil.getMethodBodiesById(classBody_,id_).first(), new ExecFormattedRootBlock(classBody_,"pkg.Ex<$int>"), null, l_, cont_, stackCall_);
        assertNotNull(getTrueException(stackCall_));
    }
    @Test
    public void okArgs10Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<E> { $public $int get($int...v){$return 0;}}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = validated(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(Argument.createVoid());
        ArgumentListCall l_ = ArgumentListCall.wrapCall(args_);
        ExecRootBlock classBody_ = cont_.getClasses().getClassBody("pkg.Ex");
        StackCall stackCall_ = getStackCall(cont_);
        ExecTemplates.okArgsSet(null, new ExecFormattedRootBlock(classBody_,"pkg.Ex<$int>"), null, l_, cont_, stackCall_);
        assertNotNull(getTrueException(stackCall_));
    }
    @Test
    public void okArgs11Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<E> { $public $int get($int...v){$return 0;}}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = validated(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ArgumentListCall l_ = ArgumentListCall.wrapCall(args_);
        StackCall stackCall_ = getStackCall(cont_);
        ExecRootBlock classBody_ = cont_.getClasses().getClassBody("pkg.Ex");
        ExecTemplates.okArgsSet(null, new ExecFormattedRootBlock(classBody_,"pkg.Ex"), null, l_, cont_, stackCall_);
        assertNull(stackCall_.getCallingState());
    }
    @Test
    public void getErrorWhenContain1Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = validated(files_);
        ArrayStruct arr_ = defaultArray();
        StackCall stackCall_ = getStackCall(cont_);
        ExecArrayTemplates.setElement(arr_, NullStruct.NULL_VALUE, NullStruct.NULL_VALUE, cont_, stackCall_);
        assertNotNull(stackCall_.getCallingState());
    }
    @Test
    public void getErrorWhenContain2Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = validated(files_);
        ArrayStruct arr_ = defaultArray();
        StackCall stackCall_ = getStackCall(cont_);
        ExecArrayTemplates.setElement(arr_, new StringStruct(""), NullStruct.NULL_VALUE, cont_, stackCall_);
        assertNotNull(stackCall_.getCallingState());
    }
    @Test
    public void getErrorWhenIndex1Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = validated(files_);
        ArrayStruct arr_ = defaultArray();
        assertSame(NullStruct.NULL_VALUE, ExecArrayTemplates.getElement(arr_, NullStruct.NULL_VALUE,cont_, getStackCall(cont_)));
    }
    @Test
    public void getErrorWhenIndex2Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = validated(files_);
        ArrayStruct arr_ = defaultArray();
        assertSame(NullStruct.NULL_VALUE, ExecArrayTemplates.getElement(arr_, new StringStruct(""),cont_, getStackCall(cont_)));
    }
    @Test
    public void setErrorWhenRangeTest() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = validated(files_);
        assertSame(NullStruct.NULL_VALUE, ExecArrayTemplates.setRange(new StringStruct(""), new RangeStruct(0), NullStruct.NULL_VALUE,cont_, getStackCall(cont_)));
    }
    @Test
    public void getErrorWhenRange1Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = validated(files_);
        assertSame(NullStruct.NULL_VALUE, ExecArrayTemplates.getRange(new StringStruct(""), NullStruct.NULL_VALUE,cont_, getStackCall(cont_)));
    }
    @Test
    public void getErrorWhenRange2Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = validated(files_);
        ArrayStruct arr_ = defaultArray();
        assertSame(NullStruct.NULL_VALUE, ExecArrayTemplates.getRange(arr_, NullStruct.NULL_VALUE,cont_, getStackCall(cont_)));
    }
    @Test
    public void getErrorWhenRange3Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = validated(files_);
        ArrayStruct arr_ = defaultArray();
        assertSame(NullStruct.NULL_VALUE, ExecArrayTemplates.getRange(arr_, new StringStruct(""),cont_, getStackCall(cont_)));
    }
    @Test
    public void getErrorWhenRange4Test() {
        assertNotNull(NumParsers.convertToRange(null));
    }
    @Test
    public void getIndexLoop() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = validated(files_);
        ExecRootBlock classBody_ = cont_.getClasses().getClassBody("pkg.Ex");
        StackCall stackCall_ = getStackCall(cont_);
        addPage(ExecutingUtil.createInstancingClass(classBody_,new ExecFormattedRootBlock(classBody_,"pkg.Ex"),null), stackCall_);
        ExecVariableTemplates.getIndexLoop(cont_,"", -1, stackCall_.getLastPage().getCache(), stackCall_.getLastPage().getVars(), stackCall_);
        assertNotNull(getTrueException(stackCall_));
    }
    @Test
    public void getIndexLoop2() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = validated(files_);
        ExecRootBlock classBody_ = cont_.getClasses().getClassBody("pkg.Ex");
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(classBody_, new ExecFormattedRootBlock(classBody_,"pkg.Ex"), null);
        instancingClass_.setCache(new HiddenCache(new StringMap<AbstractWrapper>(),new StringMap<LoopVariable>(),null));
        StackCall stackCall_ = getStackCall(cont_);
        addPage(instancingClass_, stackCall_);
        ExecVariableTemplates.getIndexLoop(cont_,"", 0, stackCall_.getLastPage().getCache(), stackCall_.getLastPage().getVars(), stackCall_);
        assertNotNull(getTrueException(stackCall_));
    }
    @Test
    public void getIndexLoop3() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = validated(files_);
        ExecRootBlock classBody_ = cont_.getClasses().getClassBody("pkg.Ex");
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(classBody_, new ExecFormattedRootBlock(classBody_,"pkg.Ex"), null);
        StringMap<LoopVariable> loops_ = new StringMap<LoopVariable>();
        LoopVariable loop_ = new LoopVariable();
        loop_.setIndex(2);
        loop_.setIndexClassName(cont_.getStandards().getContent().getPrimTypes().getAliasPrimInteger());
        loops_.addEntry("myvar", loop_);
        Cache cache_ = new HiddenCache(new StringMap<AbstractWrapper>(), loops_,null);
        instancingClass_.setCache(cache_);
        StackCall stackCall_ = getStackCall(cont_);
        addPage(instancingClass_, stackCall_);
        Argument myvar_ = ExecVariableTemplates.getIndexLoop(cont_, "myvar", 0, stackCall_.getLastPage().getCache(), stackCall_.getLastPage().getVars(), stackCall_);
        assertNull(stackCall_.getCallingState());
        assertEq(2,getNumber(myvar_));
    }
    @Test
    public void getValue() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = validated(files_);
        ExecRootBlock classBody_ = cont_.getClasses().getClassBody("pkg.Ex");
        StackCall stackCall_ = getStackCall(cont_);
        addPage(ExecutingUtil.createInstancingClass(classBody_,new ExecFormattedRootBlock(classBody_,"pkg.Ex"),null), stackCall_);
        ExecVariableTemplates.getWrapValue(cont_,"", -1, stackCall_.getLastPage().getCache(), stackCall_.getLastPage().getRefParams(), stackCall_);
        assertNull(stackCall_.getCallingState());
    }
    @Test
    public void getValue2() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = validated(files_);
        ExecRootBlock classBody_ = cont_.getClasses().getClassBody("pkg.Ex");
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(classBody_, new ExecFormattedRootBlock(classBody_,"pkg.Ex"), null);
        instancingClass_.setCache(new HiddenCache(new StringMap<AbstractWrapper>(),new StringMap<LoopVariable>(),null));
        StackCall stackCall_ = getStackCall(cont_);
        addPage(instancingClass_, stackCall_);
        ExecVariableTemplates.getWrapValue(cont_,"", 0, stackCall_.getLastPage().getCache(), stackCall_.getLastPage().getRefParams(), stackCall_);
        assertNull(stackCall_.getCallingState());
        ExecVariableTemplates.getValueVar("",new StringMap<LocalVariable>(),cont_, stackCall_);
        ExecTemplates.getWrap(null);
    }
    @Test
    public void getValue3() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = validated(files_);
        ExecRootBlock classBody_ = cont_.getClasses().getClassBody("pkg.Ex");
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(classBody_, new ExecFormattedRootBlock(classBody_,"pkg.Ex"), null);
        StringMap<AbstractWrapper> refPar_ = new StringMap<AbstractWrapper>();
        refPar_.addEntry("myvar",new VariableWrapper(LocalVariable.newLocalVariable(new IntStruct(2),cont_)));
        Cache cache_ = new HiddenCache(refPar_,new StringMap<LoopVariable>(),null);
        instancingClass_.setCache(cache_);
        StackCall stackCall_ = getStackCall(cont_);
        addPage(instancingClass_, stackCall_);
        Argument myvar_ = ExecVariableTemplates.getWrapValue(cont_, "myvar", 0, stackCall_.getLastPage().getCache(), stackCall_.getLastPage().getRefParams(), stackCall_);
        assertNull(stackCall_.getCallingState());
        assertEq(2,getNumber(myvar_));
    }

    @Test
    public void getValue4() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = validated(files_);
        ExecRootBlock classBody_ = cont_.getClasses().getClassBody("pkg.Ex");
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(classBody_, new ExecFormattedRootBlock(classBody_,"pkg.Ex"), null);
        StringMap<AbstractWrapper> refPar_ = new StringMap<AbstractWrapper>();
        refPar_.addEntry("myvar",new VariableWrapper(LocalVariable.newLocalVariable(new IntStruct(2),cont_)));
        Cache cache_ = new HiddenCache(refPar_,new StringMap<LoopVariable>(),null);
        instancingClass_.setCache(cache_);
        StackCall stackCall_ = getStackCall(cont_);
        addPage(instancingClass_, stackCall_);
        Argument myvar_ = ExecVariableTemplates.getWrapValue(cont_, "myvar", 0, stackCall_.getLastPage().getCache(), stackCall_.getLastPage().getRefParams(), stackCall_);
        assertNull(stackCall_.getCallingState());
        assertEq(2,getNumber(myvar_));
    }
    @Test
    public void setValue() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = validated(files_);
        ExecRootBlock classBody_ = cont_.getClasses().getClassBody("pkg.Ex");
        StackCall stackCall_ = getStackCall(cont_);
        addPage(ExecutingUtil.createInstancingClass(classBody_,new ExecFormattedRootBlock(classBody_,"pkg.Ex"),null), stackCall_);
        ExecVariableTemplates.setWrapValue(cont_,"", null,-1, stackCall_.getLastPage().getCache(), stackCall_.getLastPage().getRefParams(), stackCall_);
        assertNull(stackCall_.getCallingState());
    }
    @Test
    public void setValue2() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = validated(files_);
        ExecRootBlock classBody_ = cont_.getClasses().getClassBody("pkg.Ex");
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(classBody_, new ExecFormattedRootBlock(classBody_,"pkg.Ex"), null);
        instancingClass_.setCache(new HiddenCache(new StringMap<AbstractWrapper>(),new StringMap<LoopVariable>(),null));
        StackCall stackCall_ = getStackCall(cont_);
        addPage(instancingClass_, stackCall_);
        ExecVariableTemplates.setWrapValue(cont_,"", null,0, stackCall_.getLastPage().getCache(), stackCall_.getLastPage().getRefParams(), stackCall_);
        assertNull(stackCall_.getCallingState());
    }
    @Test
    public void setValue3() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = validated(files_);
        ExecRootBlock classBody_ = cont_.getClasses().getClassBody("pkg.Ex");
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(classBody_, new ExecFormattedRootBlock(classBody_,"pkg.Ex"), null);
        StringMap<AbstractWrapper> refPar_ = new StringMap<AbstractWrapper>();
        refPar_.addEntry("myvar",new VariableWrapper(LocalVariable.newLocalVariable(new IntStruct(2),cont_)));
        Cache cache_ = new HiddenCache(refPar_,new StringMap<LoopVariable>(),null);
        instancingClass_.setCache(cache_);
        StackCall stackCall_ = getStackCall(cont_);
        addPage(instancingClass_, stackCall_);
        Argument myvar_ = ExecVariableTemplates.setWrapValue(cont_, "myvar", new Argument(new IntStruct(4)),0, stackCall_.getLastPage().getCache(), stackCall_.getLastPage().getRefParams(), stackCall_);
        assertNull(stackCall_.getCallingState());
        assertEq(4,getNumber(myvar_));
        assertEq(4,getNumber(new Argument(cache_.getLocalWrapper("myvar",0).getValue(stackCall_, cont_))));
    }
    @Test
    public void incrValue() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = validated(files_);
        ExecRootBlock classBody_ = cont_.getClasses().getClassBody("pkg.Ex");
        StackCall stackCall_ = getStackCall(cont_);
        addPage(ExecutingUtil.createInstancingClass(classBody_,new ExecFormattedRootBlock(classBody_,"pkg.Ex"),null), stackCall_);
        ExecVariableTemplates.incrIndexLoop(cont_,"", 0, stackCall_.getLastPage().getCache(), stackCall_.getLastPage().getVars(), stackCall_);
        assertNotNull(getTrueException(stackCall_));
    }
    @Test
    public void incrValue2() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = validated(files_);
        ExecRootBlock classBody_ = cont_.getClasses().getClassBody("pkg.Ex");
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(classBody_, new ExecFormattedRootBlock(classBody_,"pkg.Ex"), null);
        instancingClass_.setCache(new HiddenCache(new StringMap<AbstractWrapper>(),new StringMap<LoopVariable>(),null));
        StackCall stackCall_ = getStackCall(cont_);
        addPage(instancingClass_, stackCall_);
        ExecVariableTemplates.incrIndexLoop(cont_,"", 0, stackCall_.getLastPage().getCache(), stackCall_.getLastPage().getVars(), stackCall_);
        assertNotNull(getTrueException(stackCall_));
    }
    @Test
    public void incrValue3() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = validated(files_);
        ExecRootBlock classBody_ = cont_.getClasses().getClassBody("pkg.Ex");
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(classBody_, new ExecFormattedRootBlock(classBody_,"pkg.Ex"), null);
        StringMap<LoopVariable> loops_ = new StringMap<LoopVariable>();
        LoopVariable loop_ = new LoopVariable();
        loop_.setIndex(2);
        loop_.setIndexClassName(cont_.getStandards().getContent().getPrimTypes().getAliasPrimInteger());
        loops_.addEntry("myvar", loop_);
        Cache cache_ = new HiddenCache(new StringMap<AbstractWrapper>(), loops_,null);
        instancingClass_.setCache(cache_);
        StackCall stackCall_ = getStackCall(cont_);
        addPage(instancingClass_, stackCall_);
        ExecVariableTemplates.incrIndexLoop(cont_,"myvar", 0, stackCall_.getLastPage().getCache(), stackCall_.getLastPage().getVars(), stackCall_);
        assertNull(stackCall_.getCallingState());
        assertEq(3,getNumber(new Argument(cache_.getLoopValue("myvar",0))));
    }
    @Test
    public void trySet1() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = validated(files_);
        ArrayStruct str_ = new ArrayStruct(1,StringExpUtil.getPrettyArrayType(cont_.getStandards().getCoreNames().getAliasObject()));
        ExecArrayTemplates.trySet(str_,-1,NullStruct.NULL_VALUE);
        assertNotNull(str_.get(0));
    }
    @Test
    public void trySet2() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = validated(files_);
        ArrayStruct str_ = new ArrayStruct(1,StringExpUtil.getPrettyArrayType(cont_.getStandards().getCoreNames().getAliasObject()));
        ExecArrayTemplates.trySet(str_,1,NullStruct.NULL_VALUE);
        assertNotNull(str_.get(0));
    }
    @Test
    public void trySet3() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = validated(files_);
        ArrayStruct str_ = new ArrayStruct(1,StringExpUtil.getPrettyArrayType(cont_.getStandards().getCoreNames().getAliasObject()));
        ExecArrayTemplates.trySet(str_,0,NullStruct.NULL_VALUE);
        assertNotNull(str_.get(0));
    }
    @Test
    public void wrapAndCall() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append(" $public $static $void m($int p){\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = validated(files_);
        ExecRootBlock classBody_ = cont_.getClasses().getClassBody("pkg.Ex");
        StackCall stackCall_ = getStackCall(cont_);
        addPage(ExecutingUtil.createInstancingClass(classBody_,new ExecFormattedRootBlock(classBody_,"pkg.Ex"),null), stackCall_);
        ExecNamedFunctionBlock first_ = ExecClassesUtil.getMethodBodiesById(classBody_, new MethodId(MethodAccessKind.STATIC, "m", new StringList("$int"))).first();
        wrapAndCall(cont_, classBody_, first_, stackCall_);
        assertNotNull(getTrueException(stackCall_));
    }
    @Test
    public void breakEmpty() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = validated(files_);
        ExecRootBlock classBody_ = cont_.getClasses().getClassBody("pkg.Ex");
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(classBody_, new ExecFormattedRootBlock(classBody_,"pkg.Ex"), null);
        StackCall stackCall_ = getStackCall(cont_);
        addPage(instancingClass_, stackCall_);
        assertNull(ExecHelperBlocks.hasBlockBreak(stackCall_,instancingClass_,""));
        assertSame(ReadWrite.EXIT,stackCall_.getReadWrite());
    }
    @Test
    public void continueEmpty() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = validated(files_);
        ExecRootBlock classBody_ = cont_.getClasses().getClassBody("pkg.Ex");
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(classBody_, new ExecFormattedRootBlock(classBody_,"pkg.Ex"), null);
        StackCall stackCall_ = getStackCall(cont_);
        addPage(instancingClass_, stackCall_);
        assertNull(ExecHelperBlocks.hasBlockContinue(stackCall_,instancingClass_,""));
        assertSame(ReadWrite.EXIT,stackCall_.getReadWrite());
    }
    @Test
    public void setVisited() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = validated(files_);
        ExecRootBlock classBody_ = cont_.getClasses().getClassBody("pkg.Ex");
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(classBody_, new ExecFormattedRootBlock(classBody_,"pkg.Ex"), null);
        StackCall stackCall_ = getStackCall(cont_);
        addPage(instancingClass_, stackCall_);
        ExecHelperBlocks.setVisitedDefault(cont_, stackCall_,null);
        assertSame(ReadWrite.EXIT,stackCall_.getReadWrite());
    }
    @Test
    public void setVisitedCase() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = validated(files_);
        ExecRootBlock classBody_ = cont_.getClasses().getClassBody("pkg.Ex");
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(classBody_, new ExecFormattedRootBlock(classBody_,"pkg.Ex"), null);
        StackCall stackCall_ = getStackCall(cont_);
        addPage(instancingClass_, stackCall_);
        ExecHelperBlocks.setVisitedCase(cont_, stackCall_,null);
        assertSame(ReadWrite.EXIT,stackCall_.getReadWrite());
    }
    @Test
    public void setVisitedCatch() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = validated(files_);
        ExecRootBlock classBody_ = cont_.getClasses().getClassBody("pkg.Ex");
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(classBody_, new ExecFormattedRootBlock(classBody_,"pkg.Ex"), null);
        StackCall stackCall_ = getStackCall(cont_);
        addPage(instancingClass_, stackCall_);
        ExecHelperBlocks.processCatch(cont_, stackCall_,null);
        assertSame(ReadWrite.EXIT,stackCall_.getReadWrite());
    }
    @Test
    public void processFinally() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = validated(files_);
        ExecRootBlock classBody_ = cont_.getClasses().getClassBody("pkg.Ex");
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(classBody_, new ExecFormattedRootBlock(classBody_,"pkg.Ex"), null);
        StackCall stackCall_ = getStackCall(cont_);
        addPage(instancingClass_, stackCall_);
        ExecHelperBlocks.processElse(null, stackCall_);
        assertSame(ReadWrite.EXIT,stackCall_.getReadWrite());
    }
    @Test
    public void processElseIf() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = validated(files_);
        ExecRootBlock classBody_ = cont_.getClasses().getClassBody("pkg.Ex");
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(classBody_, new ExecFormattedRootBlock(classBody_,"pkg.Ex"), null);
        StackCall stackCall_ = getStackCall(cont_);
        addPage(instancingClass_, stackCall_);
        ExecHelperBlocks.processElseIf(cont_,null, stackCall_, new ExecOperationNodeListOff(new CustList<ExecOperationNode>(),0,0));
        assertSame(ReadWrite.EXIT,stackCall_.getReadWrite());
    }
    @Test
    public void processElse() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = validated(files_);
        ExecRootBlock classBody_ = cont_.getClasses().getClassBody("pkg.Ex");
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(classBody_, new ExecFormattedRootBlock(classBody_,"pkg.Ex"), null);
        StackCall stackCall_ = getStackCall(cont_);
        addPage(instancingClass_, stackCall_);
        ExecHelperBlocks.processElse(null, stackCall_);
        ExecHelperBlocks.processElse(null, stackCall_);
        assertSame(ReadWrite.EXIT,stackCall_.getReadWrite());
    }
    @Test
    public void processDo() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = validated(files_);
        ExecRootBlock classBody_ = cont_.getClasses().getClassBody("pkg.Ex");
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(classBody_, new ExecFormattedRootBlock(classBody_,"pkg.Ex"), null);
        StackCall stackCall_ = getStackCall(cont_);
        addPage(instancingClass_, stackCall_);
        ExecHelperBlocks.processDoWhile(cont_,null, stackCall_, new ExecOperationNodeListOff(new CustList<ExecOperationNode>(),0,0));
        assertSame(ReadWrite.EXIT,stackCall_.getReadWrite());
    }
    @Test
    public void processBlockAndRemove() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = validated(files_);
        ExecRootBlock classBody_ = cont_.getClasses().getClassBody("pkg.Ex");
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(classBody_, new ExecFormattedRootBlock(classBody_,"pkg.Ex"), null);
        StackCall stackCall_ = getStackCall(cont_);
        addPage(instancingClass_, stackCall_);
        ExecHelperBlocks.processBlockAndRemove(null, stackCall_);
        assertSame(ReadWrite.EXIT,stackCall_.getReadWrite());
    }
    @Test
    public void exportAnnotation1() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append(" Annot myAnnot();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl c_ = validated(files_);
        ExecRootBlock root_ = c_.getClasses().getClassBody("pkg.Annot");
        AbstractPageEl instancingClass_ = ExecutingUtil.createAnnotation(c_, new ExecFormattedRootBlock(root_,"pkg.Annot"),root_,  new StringMap<AnnotationTypeInfo>(),new CustList<Argument>());
        StackCall stackCall_ = getStackCall(c_);
        addPage2(instancingClass_, stackCall_);
        ExecAnnotationMethodBlock.setValue(root_,"pkg.Annot","myAnnot","pkg.Annot",c_,instancingClass_.getGlobalArgument(), stackCall_);
        assertEq("@pkg.Annot(myAnnot=@pkg.Annot(pkg.Annot/1))",ExportAnnotationUtil.exportAnnotation("I","N","E", "", instancingClass_.getGlobalStruct()));
    }
    @Test
    public void exportAnnotation2() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append(" Annot myAnnot();\n");
        xml_.append(" Annot myAnnot2();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl c_ = validated(files_);
        ExecRootBlock root_ = c_.getClasses().getClassBody("pkg.Annot");
        AbstractPageEl instancingClass_ = ExecutingUtil.createAnnotation(c_, new ExecFormattedRootBlock(root_,"pkg.Annot"),root_,  new StringMap<AnnotationTypeInfo>(),new CustList<Argument>());
        StackCall stackCall_ = getStackCall(c_);
        addPage2(instancingClass_, stackCall_);
        ExecAnnotationMethodBlock.setValue(root_,"pkg.Annot","myAnnot","pkg.Annot",c_,instancingClass_.getGlobalArgument(), stackCall_);
        ExecAnnotationMethodBlock.setValue(root_,"pkg.Annot","myAnnot2","pkg.Annot",c_,instancingClass_.getGlobalArgument(), stackCall_);
        assertEq("@pkg.Annot(myAnnot=@pkg.Annot(pkg.Annot/1),myAnnot2=@pkg.Annot(pkg.Annot/1))",ExportAnnotationUtil.exportAnnotation("I","N","E", "", instancingClass_.getGlobalStruct()));
    }
    @Test
    public void exportAnnotation3() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append(" Annot myAnnot();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl c_ = validated(files_);
        ExecRootBlock root_ = c_.getClasses().getClassBody("pkg.Annot");
        AbstractPageEl instancingClass_ = ExecutingUtil.createAnnotation(c_, new ExecFormattedRootBlock(root_,"pkg.Annot"),root_,  new StringMap<AnnotationTypeInfo>(),new CustList<Argument>());
        StackCall stackCall_ = getStackCall(c_);
        addPage2(instancingClass_, stackCall_);
        AbstractPageEl instancingClass2_ = ExecutingUtil.createAnnotation(c_, new ExecFormattedRootBlock(root_,"pkg.Annot"),root_,  new StringMap<AnnotationTypeInfo>(),new CustList<Argument>());
        ExecAnnotationMethodBlock.setValue(root_,"pkg.Annot","myAnnot","pkg.Annot",c_,instancingClass2_.getGlobalArgument(), stackCall_);
        stackCall_.removeLastPage();
        addPage2(instancingClass2_, stackCall_);
        ExecAnnotationMethodBlock.setValue(root_,"pkg.Annot","myAnnot","pkg.Annot",c_,instancingClass_.getGlobalArgument(), stackCall_);
        assertEq("@pkg.Annot(myAnnot=@pkg.Annot(myAnnot=@pkg.Annot(pkg.Annot/1)))",ExportAnnotationUtil.exportAnnotation("I","N","E", "", instancingClass_.getGlobalStruct()));
    }
    @Test
    public void exportAnnotation4() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append(" Annot myAnnot();\n");
        xml_.append(" Annot myAnnot2();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl c_ = validated(files_);
        ExecRootBlock root_ = c_.getClasses().getClassBody("pkg.Annot");
        AbstractPageEl instancingClass_ = ExecutingUtil.createAnnotation(c_, new ExecFormattedRootBlock(root_,"pkg.Annot"),root_,  new StringMap<AnnotationTypeInfo>(),new CustList<Argument>());
        StackCall stackCall_ = getStackCall(c_);
        addPage2(instancingClass_, stackCall_);
        AbstractPageEl instancingClass2_ = ExecutingUtil.createAnnotation(c_, new ExecFormattedRootBlock(root_,"pkg.Annot"),root_,  new StringMap<AnnotationTypeInfo>(),new CustList<Argument>());
        ExecAnnotationMethodBlock.setValue(root_,"pkg.Annot","myAnnot","pkg.Annot",c_,instancingClass2_.getGlobalArgument(), stackCall_);
        ExecAnnotationMethodBlock.setValue(root_,"pkg.Annot","myAnnot2","pkg.Annot",c_,instancingClass_.getGlobalArgument(), stackCall_);
        stackCall_.removeLastPage();
        addPage2(instancingClass2_, stackCall_);
        ExecAnnotationMethodBlock.setValue(root_,"pkg.Annot","myAnnot","pkg.Annot",c_,instancingClass2_.getGlobalArgument(), stackCall_);
        ExecAnnotationMethodBlock.setValue(root_,"pkg.Annot","myAnnot2","pkg.Annot",c_,instancingClass_.getGlobalArgument(), stackCall_);
        assertEq("@pkg.Annot(myAnnot=@pkg.Annot(myAnnot=@pkg.Annot(pkg.Annot/2),myAnnot2=@pkg.Annot(pkg.Annot/1)),myAnnot2=@pkg.Annot(pkg.Annot/1))",ExportAnnotationUtil.exportAnnotation("I","N","E", "", instancingClass_.getGlobalStruct()));
        assertSame(NullStruct.NULL_VALUE,instancingClass_.getValue(1).getStruct());
    }
    @Test
    public void getFirstArgument() {
        assertEq("", ExecHelper.getFirstArgument(new CustList<Argument>()).getStruct().getClassName(null));
    }
    @Test
    public void getLastArgument() {
        assertEq("", ExecHelper.getLastArgument(new CustList<Argument>(Argument.createVoid())).getStruct().getClassName(null));
    }
    @Test
    public void setArgumentExp() {
        ExpressionLanguage ex_ = new ExpressionLanguage(new CustList<ExecOperationNode>(), null);
        ex_.setArgument(null,null,null, null);
        assertEq(0,ex_.getIndex());
    }
    @Test
    public void getParentOrNull() {
        assertNull(ExecHelper.getParentOrNull(null));
    }
    @Test
    public void getFirstNode() {
        assertNull(ExecHelper.getFirstNode(null));
    }
    @Test
    public void getArgumentPair() {
        assertNotNull(ExecHelper.getArgumentPair(new IdMap<ExecOperationNode, ArgumentsPair>(),null));
    }
    @Test
    public void getGenericTypeNameOrObject() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl c_ = validated(files_);
        assertNotNull(ExecHelper.getGenericTypeNameOrObject(c_,""));
    }
    @Test
    public void safeObject() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl c_ = validated(files_);
        assertNotSame(ErrorType.NOTHING, ExecInherits.safeObject("","",c_));
    }


    @Test
    public void hasToLookForParent() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = validated(files_);
        assertNotNull(cont_);
    }

    private static ArrayStruct defaultArray() {
        ArrayStruct array_ = new ArrayStruct(1, "[java.lang.Number");
        array_.set(0, NullStruct.NULL_VALUE);
        return array_;
    }

    private static ArrayStruct defaultArray1(NullStruct _value, String _className) {
        return defaultArr(_value, _className);
    }

    private static ArrayStruct defaultArray2(String _s) {
        return defaultArr(new StringStruct(""),_s);
    }

    private static ArrayStruct defaultArr(Struct _value, String _className) {
        ArrayStruct array_ = new ArrayStruct(1, _className);
        array_.set(0,_value);
        return array_;
    }

    private void addPage(AbstractPageEl _instancingClass, StackCall _stackCall) {
        ExecutingUtil.addPage(_instancingClass, _stackCall);
    }

    protected static Struct getTrueException(StackCall _stackCall) {
        CallingState str_ = _stackCall.getCallingState();
        return ((CustomFoundExc) str_).getStruct();
    }

    private static ContextEl validated(StringMap<String> _files) {
        Options opt_ = newOptions();
        addTypesInit(opt_);
        CustLgNames lgName_ = getLgNames();
        KeyWords kw_ = new KeyWords();
        setOpts(opt_,IndexConstants.INDEX_NOT_FOUND_ELT);
        return validateAndRetWithoutInit(opt_,lgName_,kw_,_files,new StringMap<String>()).getContext();
    }

    private static StackCall getStackCall(ContextEl _ctx) {
        return StackCall.newInstance(InitPhase.NOTHING, _ctx);
    }

    private static String reflectFormat(ContextEl _context, String _first, String _second) {
        return ExecInherits.reflectFormat(_first,_second,_context);
    }

    private void addPage2(AbstractPageEl _instancingClass2, StackCall _stackCall) {
        ExecutingUtil.addPage(_instancingClass2, _stackCall);
    }

    private void wrapAndCall(ContextEl _cont, ExecRootBlock _classBody, ExecNamedFunctionBlock _first, StackCall _stackCall) {
        ArgumentListCall argList_ = new ArgumentListCall(new Argument());
        ArgumentWrapper.helpArg(null);
        ExecHelper.getArgumentWrapper(new CustList<ArgumentWrapper>(),0);
        ExecTemplates.wrapAndCall(new ExecOverrideInfo(new ExecFormattedRootBlock(_classBody,"pkg.Ex"),new ExecTypeFunction(_classBody, _first)), Argument.createVoid(), _cont, _stackCall, argList_);
//        new ReflectGetFieldPageEl(null,null, false).receive(null,null,_cont, _stackCall);
//        new ReflectSetFieldPageEl(null,null,null, false).receive(null,null,_cont, _stackCall);
    }

    private static String getFullTypeByBases(ContextEl _context, String _first, String _second) {
        return ExecInherits.getFullTypeByBases(_first, _second, _context);
    }


    private String getFullObject(ContextEl _context, String _first, String _second) {
        return ExecInherits.getFullObject(_first, _second, _context);
    }

    private static String getSuperGeneric(ContextEl _context, String _first, String _second) {
        return ExecInherits.getFullObject(_first, _second, _context);
    }

    private Struct getParent(Struct _inTwo, ContextEl _c, int _nbAncestors, StackCall _stackCall) {
        return ExecFieldTemplates.getParent(_nbAncestors, _inTwo, _c, _stackCall);
    }

    private Struct getParent(ArrayStruct _arr, ContextEl _c, StackCall _stackCall) {
        return getParent(_arr, _c, 0, _stackCall);
    }

}
