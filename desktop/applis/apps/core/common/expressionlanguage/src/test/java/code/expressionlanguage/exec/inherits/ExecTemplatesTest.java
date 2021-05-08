package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.AnalyzedTestContext;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AnnotationTypeInfo;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.annotation.ExportAnnotationUtil;
import code.expressionlanguage.exec.blocks.ExecAnnotationMethodBlock;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.blocks.ExecSwitchInstanceMethod;
import code.expressionlanguage.exec.util.HiddenCache;
import code.expressionlanguage.exec.variables.*;
import code.expressionlanguage.fwd.blocks.*;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.methods.ProcessMethodCommon;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.exec.calls.*;
import code.util.*;
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
        AnalyzedTestContext c_ = validated(files_);
        assertSame(NullStruct.NULL_VALUE, ExecTemplates.getParent(0,"pkg.Ex",new IntStruct(1),c_.getContext(), c_.getStackCall()));
        assertNotNull(getTrueException(c_));
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
        AnalyzedTestContext c_ = validated(files_);
        Struct par_  = c_.getInit().processInit(c_.getContext(),NullStruct.NULL_VALUE,"pkg.Ex",c_.getClasses().getClassBody("pkg.Ex"),"",-1);
        Struct in_ = c_.getInit().processInit(c_.getContext(),par_,"pkg.Ex..Inner",c_.getClasses().getClassBody("pkg.Ex..Inner"),"",-1);
        ExecTemplates.getParent(0,"java.lang.Integer",in_,c_.getContext(), c_.getStackCall());
        assertNotNull(c_.getContext());
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
        AnalyzedTestContext c_ = validated(files_);
        Struct par_  = c_.getInit().processInit(c_.getContext(),NullStruct.NULL_VALUE,"pkg.Ex",c_.getClasses().getClassBody("pkg.Ex"),"",-1);
        Struct in_ = c_.getInit().processInit(c_.getContext(),par_,"pkg.Ex..Inner",c_.getClasses().getClassBody("pkg.Ex..Inner"),"",-1);
        Struct inTwo_ = c_.getInit().processInit(c_.getContext(),in_,"pkg.Ex..Inner",c_.getClasses().getClassBody("pkg.Ex..Inner"),"",-1);
        ExecTemplates.getParent(0,"java.lang.Integer",inTwo_,c_.getContext(), c_.getStackCall());
        assertNotNull(c_.getContext());
    }

    @Test
    public void getParent4Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext c_ = validated(files_);
        ArrayStruct arr_ = ExecTemplates.newCustomArray(c_.getStandards().getContent().getPrimTypes().getAliasPrimInteger(),new Ints(0),c_.getContext());
        assertSame(NullStruct.NULL_VALUE, ExecTemplates.getParent(0,"[pkg.Ex",arr_,c_.getContext(), c_.getStackCall()));
        assertNotNull(getTrueException(c_));
    }

    @Test
    public void getParent5Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext c_ = validated(files_);
        ArrayStruct arr_ = ExecTemplates.newCustomArray(c_.getStandards().getContent().getPrimTypes().getAliasPrimInteger(),new Ints(0),c_.getContext());
        assertSame(NullStruct.NULL_VALUE, ExecTemplates.getParent(0,"pkg.Ex",arr_,c_.getContext(), c_.getStackCall()));
        assertNotNull(getTrueException(c_));
    }

    @Test
    public void getParent6Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext c_ = validated(files_);
        ArrayStruct arr_ = ExecTemplates.newCustomArray(c_.getStandards().getContent().getPrimTypes().getAliasPrimInteger(),new Ints(0),c_.getContext());
        assertSame(NullStruct.NULL_VALUE, ExecTemplates.getParent(0,"[["+ c_.getStandards().getContent().getCoreNames().getAliasObject(),arr_,c_.getContext(), c_.getStackCall()));
        assertNotNull(getTrueException(c_));
    }

    @Test
    public void getParent7Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext c_ = validated(files_);
        ArrayStruct arr_ = ExecTemplates.newCustomArray(c_.getStandards().getContent().getPrimTypes().getAliasPrimInteger(),new Ints(0),c_.getContext());
        assertSame(arr_, ExecTemplates.getParent(0,"["+ c_.getStandards().getContent().getCoreNames().getAliasObject(),arr_,c_.getContext(), c_.getStackCall()));
        assertNull(c_.getStackCall().getCallingState());
    }

    @Test
    public void newCustomArray1Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.CustClass{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = validated(files_);
        Ints dims_ = new Ints(1);
        ArrayStruct customArray_ = ExecTemplates.newCustomArray(CUST_CLASS, dims_, cont_.getContext());
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
        AnalyzedTestContext cont_ = validated(files_);
        Ints dims_ = new Ints(2);
        ArrayStruct customArray_ = ExecTemplates.newCustomArray(CUST_CLASS, dims_, cont_.getContext());
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
        AnalyzedTestContext cont_ = validated(files_);
        Ints dims_ = new Ints(2,3);
        ArrayStruct customArray_ = ExecTemplates.newCustomArray(CUST_CLASS, dims_, cont_.getContext());
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
        AnalyzedTestContext cont_ = validated(files_);
        Ints dims_ = new Ints(4,2,3);
        ArrayStruct customArray_ = ExecTemplates.newCustomArray(CUST_CLASS, dims_, cont_.getContext());
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
        AnalyzedTestContext context_ = validated(files_);
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
        AnalyzedTestContext context_ = validated(files_);
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
        AnalyzedTestContext context_ = validated(files_);
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
        AnalyzedTestContext context_ = validated(files_);
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
        AnalyzedTestContext context_ = validated(files_);
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
        AnalyzedTestContext context_ = validated(files_);
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
        AnalyzedTestContext context_ = validated(files_);
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
        AnalyzedTestContext context_ = validated(files_);
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
        AnalyzedTestContext context_ = validated(files_);
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
        AnalyzedTestContext context_ = validated(files_);
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
        AnalyzedTestContext context_ = validated(files_);
        String first_ = "pkg.Ex<java.lang.Object>";
        String second_ = "[pkg.ExTwo";
        String t_ = ExecInherits.getQuickFullTypeByBases(first_, second_, context_.getContext());
        assertEq("",t_);
    }

    @Test
    public void getGenericTypeByBases5Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>:ExTwo<$iterable<?T>> {}\n");
        xml_.append("$public $class pkg.ExTwo<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext context_ = validated(files_);
        String first_ = "[pkg.Ex<java.lang.Object>";
        String second_ = "pkg.ExTwo";
        String t_ = ExecInherits.getQuickFullTypeByBases(first_, second_, context_.getContext());
        assertEq("",t_);
    }

    @Test
    public void getGenericTypeByBases7Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>:ExTwo<$iterable<?T>> {}\n");
        xml_.append("$public $class pkg.ExTwo<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext context_ = validated(files_);
        String first_ = "pkg.Ex";
        String second_ = "pkg.ExTwo";
        String t_ = getFullObject(context_.getContext(), first_, second_);
        assertEq("pkg.ExTwo<java.lang.$iterable<?#T>>",t_);
    }

    @Test
    public void getGenericTypeByBases16Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>:ExTwo<$iterable<?T>> {}\n");
        xml_.append("$public $class pkg.ExTwo<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext context_ = validated(files_);
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
        AnalyzedTestContext context_ = validated(files_);
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
        AnalyzedTestContext context_ = validated(files_);
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
        AnalyzedTestContext context_ = validated(files_);
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
        AnalyzedTestContext context_ = validated(files_);
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
        AnalyzedTestContext context_ = validated(files_);
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
        AnalyzedTestContext context_ = validated(files_);
        String first_ = "";
        String second_ = "";
        String t_ = getFullTypeByBases(context_, first_, second_);
        assertEq("",t_);
    }

    @Test
    public void setCheckedElements1Test() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext cont_ = validated(files_);
        ArrayStruct arr_ = new ArrayStruct(1,"[$int");
        arr_.set(0,new IntStruct(0));
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(Argument.createVoid());
        ExecTemplates.setCheckedElements(args_,arr_,cont_.getContext(), cont_.getStackCall());
        assertNotNull(getTrueException(cont_));
    }
    @Test
    public void okArgsSetSw() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext cont_ = validated(files_);
        StackCall stackCall_ = StackCall.newInstance(InitPhase.NOTHING, cont_.getContext());
        assertNotNull(ExecTemplates.okArgsSetSw(null,null, "", null, cont_.getContext(), stackCall_, new CustList<Argument>()).getError());
        assertNotNull(getTrueException(stackCall_));
    }
    @Test
    public void okArgsSetSwCall() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        xml_.append("$public $annotation pkg.ExAnnot {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = validated(files_);
        StackCall stackCall_ = StackCall.newInstance(InitPhase.NOTHING, cont_.getContext());
        ExecSwitchInstanceMethod ex_ = new ExecSwitchInstanceMethod(false,"",null,"$int",0,"",new ExecAnonFctContent(new AnaAnonFctContent()));
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(cont_.getClasses().getClassBody("pkg.Ex"), "pkg.Ex", null, cont_.getStackCall());
        stackCall_.addInternPage(instancingClass_);
        ExecTemplates.okArgsSetSwCall( ex_, cont_.getContext(), stackCall_, Argument.createVoid());
        assertNotNull(getTrueException(stackCall_));
    }
    @Test
    public void okArgs1Test() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext cont_ = validated(files_);
        MethodId id_ = new MethodId(MethodAccessKind.STATIC,"method", new StringList("$int"),true);
        ArrayStruct arr_ = defaultArray1(NullStruct.NULL_VALUE, "[$int");
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(new Argument(arr_));
        assertNotNull(ExecTemplates.okArgsSet(id_, args_, cont_.getContext(), cont_.getStackCall()));
        assertNotNull(getTrueException(cont_));
    }
    @Test
    public void okArgs2Test() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext cont_ = validated(files_);
        MethodId id_ = new MethodId(MethodAccessKind.STATIC,"method", new StringList("java.lang.Number"),true);
        ArrayStruct arr_ = defaultArray2("[java.lang.Number");
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(new Argument(arr_));
        assertNotNull(ExecTemplates.okArgsSet(id_, args_, cont_.getContext(), cont_.getStackCall()));
        assertNotNull(getTrueException(cont_));
    }
    @Test
    public void okArgs3Test() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext cont_ = validated(files_);
        MethodId id_ = new MethodId(MethodAccessKind.STATIC,"method", new StringList("java.lang.Number"),true);
        ArrayStruct arr_ = new ArrayStruct(1,"[java.lang.Number");
        arr_.set(0,new StringStruct(""));
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(new Argument(arr_));
        arr_ = new ArrayStruct(1,"[java.lang.Number");
        arr_.set(0,new StringStruct(""));
        args_.add(new Argument(arr_));
        assertNotNull(ExecTemplates.okArgsSet(id_, args_, cont_.getContext(), cont_.getStackCall()));
        assertNotNull(getTrueException(cont_));
    }
    @Test
    public void okArgs4Test() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext cont_ = validated(files_);
        MethodId id_ = new MethodId(MethodAccessKind.STATIC,"method", new StringList(""),false);
        ArrayStruct arr_ = defaultArray2("[java.lang.Number");
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(new Argument(arr_));
        assertNotNull(ExecTemplates.okArgsSet(id_, args_, cont_.getContext(), cont_.getStackCall()));
        assertNotNull(getTrueException(cont_));
    }
    @Test
    public void okArgs5Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<E> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = validated(files_);
        MethodId id_ = new MethodId(MethodAccessKind.INSTANCE,"method", new StringList(""),false);
        Struct atr_ = cont_.getInit().processInit(cont_.getContext(), NullStruct.NULL_VALUE, "pkg.Ex<$int>",cont_.getClasses().getClassBody("pkg.Ex"), "", -1);
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(new Argument(atr_));
        assertNotNull(ExecTemplates.okArgsSet(id_, args_, cont_.getContext(), cont_.getStackCall()));
        assertNotNull(getTrueException(cont_));
    }
    @Test
    public void okArgs6Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<E> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = validated(files_);
        MethodId id_ = new MethodId(MethodAccessKind.INSTANCE,"method", new StringList("pkg.Ex"),false);
        Struct atr_ = cont_.getInit().processInit(cont_.getContext(), NullStruct.NULL_VALUE, "pkg.Ex<$int>",cont_.getClasses().getClassBody("pkg.Ex"), "", -1);
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(new Argument(atr_));
        assertNotNull(ExecTemplates.okArgsSet(id_, args_, cont_.getContext(), cont_.getStackCall()));
        assertNotNull(getTrueException(cont_));
    }
    @Test
    public void okArgs7Test() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext cont_ = validated(files_);
        MethodId id_ = new MethodId(MethodAccessKind.STATIC,"method", new StringList(""),false);
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(new Argument(NullStruct.NULL_VALUE));
        assertNotNull(ExecTemplates.okArgsSet(id_, args_, cont_.getContext(), cont_.getStackCall()));
        assertNotNull(getTrueException(cont_));
    }
    @Test
    public void okArgs8Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<E> { $public $int get($int...v){$return 0;}}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = validated(files_);
        MethodId id_ = new MethodId(MethodAccessKind.INSTANCE,"get", new StringList("$int"),true);
        ArrayStruct arr_ = defaultArray2("[$int");
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(new Argument(arr_));
        ArgumentListCall l_ = new ArgumentListCall();
        l_.addAllArgs(args_);
        ExecRootBlock classBody_ = cont_.getClasses().getClassBody("pkg.Ex");
        ExecTemplates.okArgsSet(classBody_, ExecClassesUtil.getMethodBodiesById(classBody_,id_).first(), "pkg.Ex<$int>", null, l_, cont_.getContext(), null, true, cont_.getStackCall());
        assertNotNull(getTrueException(cont_));
    }
    @Test
    public void okArgs9Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<E> { $public $int get($int...v){$return 0;}}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = validated(files_);
        MethodId id_ = new MethodId(MethodAccessKind.INSTANCE,"get", new StringList("$int"),true);
        CustList<Argument> args_ = new CustList<Argument>();
        ArgumentListCall l_ = new ArgumentListCall();
        l_.addAllArgs(args_);
        ExecRootBlock classBody_ = cont_.getClasses().getClassBody("pkg.Ex");
        ExecTemplates.okArgsSet(classBody_, ExecClassesUtil.getMethodBodiesById(classBody_,id_).first(), "pkg.Ex<$int>", null, l_, cont_.getContext(), null, true, cont_.getStackCall());
        assertNotNull(getTrueException(cont_));
    }
    @Test
    public void okArgs10Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<E> { $public $int get($int...v){$return 0;}}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = validated(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(Argument.createVoid());
        ArgumentListCall l_ = new ArgumentListCall();
        l_.addAllArgs(args_);
        ExecTemplates.okArgsSet(cont_.getClasses().getClassBody("pkg.Ex"), null, "pkg.Ex<$int>", null, l_, cont_.getContext(), null, true, cont_.getStackCall());
        assertNotNull(getTrueException(cont_));
    }
    @Test
    public void okArgs11Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<E> { $public $int get($int...v){$return 0;}}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = validated(files_);
        CustList<Argument> args_ = new CustList<Argument>();
        ArgumentListCall l_ = new ArgumentListCall();
        l_.addAllArgs(args_);
        StackCall stackCall_ = cont_.getStackCall();
        ExecTemplates.okArgsSet(cont_.getClasses().getClassBody("pkg.Ex"), null, "pkg.Ex", null, l_, cont_.getContext(), null,true, stackCall_);
        assertNull(stackCall_.getCallingState());
    }
    @Test
    public void getErrorWhenContain1Test() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext cont_ = validated(files_);
        ArrayStruct arr_ = defaultArray();
        ExecTemplates.setElement(arr_, NullStruct.NULL_VALUE, NullStruct.NULL_VALUE, cont_.getContext(), cont_.getStackCall());
        assertNotNull(cont_.getStackCall().getCallingState());
    }
    @Test
    public void getErrorWhenContain2Test() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext cont_ = validated(files_);
        ArrayStruct arr_ = defaultArray();
        ExecTemplates.setElement(arr_, new StringStruct(""), NullStruct.NULL_VALUE, cont_.getContext(), cont_.getStackCall());
        assertNotNull(cont_.getStackCall().getCallingState());
    }
    @Test
    public void getErrorWhenIndex1Test() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext cont_ = validated(files_);
        ArrayStruct arr_ = defaultArray();
        assertSame(NullStruct.NULL_VALUE, ExecTemplates.getElement(arr_, NullStruct.NULL_VALUE,cont_.getContext(), cont_.getStackCall()));
    }
    @Test
    public void getErrorWhenIndex2Test() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext cont_ = validated(files_);
        ArrayStruct arr_ = defaultArray();
        assertSame(NullStruct.NULL_VALUE, ExecTemplates.getElement(arr_, new StringStruct(""),cont_.getContext(), cont_.getStackCall()));
    }
    @Test
    public void getErrorWhenRange1Test() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext cont_ = validated(files_);
        assertSame(NullStruct.NULL_VALUE, ExecTemplates.getRange(new StringStruct(""), NullStruct.NULL_VALUE,cont_.getContext(), cont_.getStackCall()));
    }
    @Test
    public void getErrorWhenRange2Test() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext cont_ = validated(files_);
        ArrayStruct arr_ = defaultArray();
        assertSame(NullStruct.NULL_VALUE, ExecTemplates.getRange(arr_, NullStruct.NULL_VALUE,cont_.getContext(), cont_.getStackCall()));
    }
    @Test
    public void getErrorWhenRange3Test() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext cont_ = validated(files_);
        ArrayStruct arr_ = defaultArray();
        assertSame(NullStruct.NULL_VALUE, ExecTemplates.getRange(arr_, new StringStruct(""),cont_.getContext(), cont_.getStackCall()));
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
        AnalyzedTestContext cont_ = validated(files_);
        addPage(cont_.getContext(), ExecutingUtil.createInstancingClass(cont_.getClasses().getClassBody("pkg.Ex"),"pkg.Ex",null, cont_.getStackCall()), cont_.getStackCall());
        ExecTemplates.getIndexLoop(cont_.getContext(),"", -1, cont_.getStackCall().getLastPage().getCache(), cont_.getStackCall().getLastPage().getVars(), cont_.getStackCall());
        assertNotNull(getTrueException(cont_));
    }
    @Test
    public void getIndexLoop2() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = validated(files_);
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(cont_.getClasses().getClassBody("pkg.Ex"), "pkg.Ex", null, cont_.getStackCall());
        instancingClass_.setCache(new HiddenCache(new StringMap<AbstractWrapper>(),new StringMap<LoopVariable>(),null));
        addPage(cont_.getContext(), instancingClass_, cont_.getStackCall());
        ExecTemplates.getIndexLoop(cont_.getContext(),"", 0, cont_.getStackCall().getLastPage().getCache(), cont_.getStackCall().getLastPage().getVars(), cont_.getStackCall());
        assertNotNull(getTrueException(cont_));
    }
    @Test
    public void getIndexLoop3() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = validated(files_);
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(cont_.getClasses().getClassBody("pkg.Ex"), "pkg.Ex", null, cont_.getStackCall());
        StringMap<LoopVariable> loops_ = new StringMap<LoopVariable>();
        LoopVariable loop_ = new LoopVariable();
        loop_.setIndex(2);
        loop_.setIndexClassName(cont_.getStandards().getContent().getPrimTypes().getAliasPrimInteger());
        loops_.addEntry("myvar", loop_);
        Cache cache_ = new HiddenCache(new StringMap<AbstractWrapper>(), loops_,null);
        instancingClass_.setCache(cache_);
        addPage(cont_.getContext(), instancingClass_, cont_.getStackCall());
        Argument myvar_ = ExecTemplates.getIndexLoop(cont_.getContext(), "myvar", 0, cont_.getStackCall().getLastPage().getCache(), cont_.getStackCall().getLastPage().getVars(), cont_.getStackCall());
        assertNull(cont_.getStackCall().getCallingState());
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
        AnalyzedTestContext cont_ = validated(files_);
        addPage(cont_.getContext(), ExecutingUtil.createInstancingClass(cont_.getClasses().getClassBody("pkg.Ex"),"pkg.Ex",null, cont_.getStackCall()), cont_.getStackCall());
        ExecTemplates.getWrapValue(cont_.getContext(),"", -1, cont_.getStackCall().getLastPage().getCache(), cont_.getStackCall().getLastPage().getRefParams(), cont_.getStackCall());
        assertNull(cont_.getStackCall().getCallingState());
    }
    @Test
    public void getValue2() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = validated(files_);
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(cont_.getClasses().getClassBody("pkg.Ex"), "pkg.Ex", null, cont_.getStackCall());
        instancingClass_.setCache(new HiddenCache(new StringMap<AbstractWrapper>(),new StringMap<LoopVariable>(),null));
        addPage(cont_.getContext(), instancingClass_, cont_.getStackCall());
        ExecTemplates.getWrapValue(cont_.getContext(),"", 0, cont_.getStackCall().getLastPage().getCache(), cont_.getStackCall().getLastPage().getRefParams(), cont_.getStackCall());
        assertNull(cont_.getStackCall().getCallingState());
        ExecTemplates.getValueVar("",new StringMap<LocalVariable>(),cont_.getContext(), cont_.getStackCall());
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
        AnalyzedTestContext cont_ = validated(files_);
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(cont_.getClasses().getClassBody("pkg.Ex"), "pkg.Ex", null, cont_.getStackCall());
        StringMap<AbstractWrapper> refPar_ = new StringMap<AbstractWrapper>();
        refPar_.addEntry("myvar",new VariableWrapper(LocalVariable.newLocalVariable(new IntStruct(2),cont_.getContext())));
        Cache cache_ = new HiddenCache(refPar_,new StringMap<LoopVariable>(),null);
        instancingClass_.setCache(cache_);
        addPage(cont_.getContext(), instancingClass_, cont_.getStackCall());
        Argument myvar_ = ExecTemplates.getWrapValue(cont_.getContext(), "myvar", 0, cont_.getStackCall().getLastPage().getCache(), cont_.getStackCall().getLastPage().getRefParams(), cont_.getStackCall());
        assertNull(cont_.getStackCall().getCallingState());
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
        AnalyzedTestContext cont_ = validated(files_);
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(cont_.getClasses().getClassBody("pkg.Ex"), "pkg.Ex", null, cont_.getStackCall());
        StringMap<AbstractWrapper> refPar_ = new StringMap<AbstractWrapper>();
        refPar_.addEntry("myvar",new VariableWrapper(LocalVariable.newLocalVariable(new IntStruct(2),cont_.getContext())));
        Cache cache_ = new HiddenCache(refPar_,new StringMap<LoopVariable>(),null);
        instancingClass_.setCache(cache_);
        addPage(cont_.getContext(), instancingClass_, cont_.getStackCall());
        Argument myvar_ = ExecTemplates.getWrapValue(cont_.getContext(), "myvar", 0, cont_.getStackCall().getLastPage().getCache(), cont_.getStackCall().getLastPage().getRefParams(), cont_.getStackCall());
        assertNull(cont_.getStackCall().getCallingState());
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
        AnalyzedTestContext cont_ = validated(files_);
        addPage(cont_.getContext(), ExecutingUtil.createInstancingClass(cont_.getClasses().getClassBody("pkg.Ex"),"pkg.Ex",null, cont_.getStackCall()), cont_.getStackCall());
        ExecTemplates.setWrapValue(cont_.getContext(),"", null,-1, cont_.getStackCall().getLastPage().getCache(), cont_.getStackCall().getLastPage().getRefParams(), cont_.getStackCall());
        assertNull(cont_.getStackCall().getCallingState());
    }
    @Test
    public void setValue2() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = validated(files_);
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(cont_.getClasses().getClassBody("pkg.Ex"), "pkg.Ex", null, cont_.getStackCall());
        instancingClass_.setCache(new HiddenCache(new StringMap<AbstractWrapper>(),new StringMap<LoopVariable>(),null));
        addPage(cont_.getContext(), instancingClass_, cont_.getStackCall());
        ExecTemplates.setWrapValue(cont_.getContext(),"", null,0, cont_.getStackCall().getLastPage().getCache(), cont_.getStackCall().getLastPage().getRefParams(), cont_.getStackCall());
        assertNull(cont_.getStackCall().getCallingState());
    }
    @Test
    public void setValue3() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = validated(files_);
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(cont_.getClasses().getClassBody("pkg.Ex"), "pkg.Ex", null, cont_.getStackCall());
        StringMap<AbstractWrapper> refPar_ = new StringMap<AbstractWrapper>();
        refPar_.addEntry("myvar",new VariableWrapper(LocalVariable.newLocalVariable(new IntStruct(2),cont_.getContext())));
        Cache cache_ = new HiddenCache(refPar_,new StringMap<LoopVariable>(),null);
        instancingClass_.setCache(cache_);
        addPage(cont_.getContext(), instancingClass_, cont_.getStackCall());
        Argument myvar_ = ExecTemplates.setWrapValue(cont_.getContext(), "myvar", new Argument(new IntStruct(4)),0, cont_.getStackCall().getLastPage().getCache(), cont_.getStackCall().getLastPage().getRefParams(), cont_.getStackCall());
        assertNull(cont_.getStackCall().getCallingState());
        assertEq(4,getNumber(myvar_));
        assertEq(4,getNumber(new Argument(cache_.getLocalWrapper("myvar",0).getValue(cont_.getStackCall(), cont_.getContext()))));
    }
    @Test
    public void incrValue() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = validated(files_);
        addPage(cont_.getContext(), ExecutingUtil.createInstancingClass(cont_.getClasses().getClassBody("pkg.Ex"),"pkg.Ex",null, cont_.getStackCall()), cont_.getStackCall());
        ExecTemplates.incrIndexLoop(cont_.getContext(),"", 0, cont_.getStackCall().getLastPage().getCache(), cont_.getStackCall().getLastPage().getVars(), cont_.getStackCall());
        assertNotNull(getTrueException(cont_));
    }
    @Test
    public void incrValue2() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = validated(files_);
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(cont_.getClasses().getClassBody("pkg.Ex"), "pkg.Ex", null, cont_.getStackCall());
        instancingClass_.setCache(new HiddenCache(new StringMap<AbstractWrapper>(),new StringMap<LoopVariable>(),null));
        addPage(cont_.getContext(), instancingClass_, cont_.getStackCall());
        ExecTemplates.incrIndexLoop(cont_.getContext(),"", 0, cont_.getStackCall().getLastPage().getCache(), cont_.getStackCall().getLastPage().getVars(), cont_.getStackCall());
        assertNotNull(getTrueException(cont_));
    }
    @Test
    public void incrValue3() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = validated(files_);
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(cont_.getClasses().getClassBody("pkg.Ex"), "pkg.Ex", null, cont_.getStackCall());
        StringMap<LoopVariable> loops_ = new StringMap<LoopVariable>();
        LoopVariable loop_ = new LoopVariable();
        loop_.setIndex(2);
        loop_.setIndexClassName(cont_.getStandards().getContent().getPrimTypes().getAliasPrimInteger());
        loops_.addEntry("myvar", loop_);
        Cache cache_ = new HiddenCache(new StringMap<AbstractWrapper>(), loops_,null);
        instancingClass_.setCache(cache_);
        addPage(cont_.getContext(), instancingClass_, cont_.getStackCall());
        ExecTemplates.incrIndexLoop(cont_.getContext(),"myvar", 0, cont_.getStackCall().getLastPage().getCache(), cont_.getStackCall().getLastPage().getVars(), cont_.getStackCall());
        assertNull(cont_.getStackCall().getCallingState());
        assertEq(3,getNumber(new Argument(cache_.getLoopValue("myvar",0))));
    }
    @Test
    public void trySet1() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext cont_ = validated(files_);
        ArrayStruct str_ = new ArrayStruct(1,StringExpUtil.getPrettyArrayType(cont_.getStandards().getCoreNames().getAliasObject()));
        ExecTemplates.trySet(str_,-1,NullStruct.NULL_VALUE);
        assertNotNull(str_.get(0));
    }
    @Test
    public void trySet2() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext cont_ = validated(files_);
        ArrayStruct str_ = new ArrayStruct(1,StringExpUtil.getPrettyArrayType(cont_.getStandards().getCoreNames().getAliasObject()));
        ExecTemplates.trySet(str_,1,NullStruct.NULL_VALUE);
        assertNotNull(str_.get(0));
    }
    @Test
    public void trySet3() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext cont_ = validated(files_);
        ArrayStruct str_ = new ArrayStruct(1,StringExpUtil.getPrettyArrayType(cont_.getStandards().getCoreNames().getAliasObject()));
        ExecTemplates.trySet(str_,0,NullStruct.NULL_VALUE);
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
        AnalyzedTestContext cont_ = validated(files_);
        ExecRootBlock classBody_ = cont_.getClasses().getClassBody("pkg.Ex");
        addPage(cont_.getContext(), ExecutingUtil.createInstancingClass(classBody_,"pkg.Ex",null, cont_.getStackCall()), cont_.getStackCall());
        ExecNamedFunctionBlock first_ = ExecClassesUtil.getMethodBodiesById(classBody_, new MethodId(MethodAccessKind.STATIC, "m", new StringList("$int"))).first();
        wrapAndCall(cont_, classBody_, first_);
        assertNotNull(getTrueException(cont_));
    }
    @Test
    public void breakEmpty() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = validated(files_);
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(cont_.getClasses().getClassBody("pkg.Ex"), "pkg.Ex", null, cont_.getStackCall());
        addPage(cont_.getContext(), instancingClass_, cont_.getStackCall());
        assertTrue(!ExecHelperBlocks.hasBlockBreak(instancingClass_,""));
        assertNull(instancingClass_.getReadWrite());
    }
    @Test
    public void continueEmpty() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = validated(files_);
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(cont_.getClasses().getClassBody("pkg.Ex"), "pkg.Ex", null, cont_.getStackCall());
        addPage(cont_.getContext(), instancingClass_, cont_.getStackCall());
        assertTrue(!ExecHelperBlocks.hasBlockContinue(cont_.getContext(),instancingClass_,"", cont_.getStackCall()));
        assertNull(instancingClass_.getReadWrite());
    }
    @Test
    public void setVisited() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = validated(files_);
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(cont_.getClasses().getClassBody("pkg.Ex"), "pkg.Ex", null, cont_.getStackCall());
        addPage(cont_.getContext(), instancingClass_, cont_.getStackCall());
        ExecHelperBlocks.setVisited(instancingClass_,null);
        assertNull(instancingClass_.getReadWrite());
    }
    @Test
    public void processFinally() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = validated(files_);
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(cont_.getClasses().getClassBody("pkg.Ex"), "pkg.Ex", null, cont_.getStackCall());
        addPage(cont_.getContext(), instancingClass_, cont_.getStackCall());
        ExecHelperBlocks.processFinally(cont_.getContext(),null, cont_.getStackCall());
        assertNull(instancingClass_.getReadWrite());
    }
    @Test
    public void processElseIf() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = validated(files_);
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(cont_.getClasses().getClassBody("pkg.Ex"), "pkg.Ex", null, cont_.getStackCall());
        addPage(cont_.getContext(), instancingClass_, cont_.getStackCall());
        ExecHelperBlocks.processElseIf(cont_.getContext(),null, cont_.getStackCall());
        assertNull(instancingClass_.getReadWrite());
    }
    @Test
    public void processElse() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = validated(files_);
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(cont_.getClasses().getClassBody("pkg.Ex"), "pkg.Ex", null, cont_.getStackCall());
        addPage(cont_.getContext(), instancingClass_, cont_.getStackCall());
        ExecHelperBlocks.processElse(cont_.getContext(),null, cont_.getStackCall());
        assertNull(instancingClass_.getReadWrite());
    }
    @Test
    public void processDo() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = validated(files_);
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(cont_.getClasses().getClassBody("pkg.Ex"), "pkg.Ex", null, cont_.getStackCall());
        addPage(cont_.getContext(), instancingClass_, cont_.getStackCall());
        ExecHelperBlocks.processDo(cont_.getContext(),null, cont_.getStackCall());
        assertNull(instancingClass_.getReadWrite());
    }
    @Test
    public void processBlockAndRemove() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = validated(files_);
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(cont_.getClasses().getClassBody("pkg.Ex"), "pkg.Ex", null, cont_.getStackCall());
        addPage(cont_.getContext(), instancingClass_, cont_.getStackCall());
        ExecHelperBlocks.processBlockAndRemove(cont_.getContext(),null, cont_.getStackCall());
        assertNull(instancingClass_.getReadWrite());
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
        AnalyzedTestContext c_ = validated(files_);
        ExecRootBlock root_ = c_.getClasses().getClassBody("pkg.Annot");
        AbstractPageEl instancingClass_ = ExecutingUtil.createAnnotation(c_.getContext(), "pkg.Annot",root_,  new StringMap<AnnotationTypeInfo>(),new CustList<Argument>(), c_.getStackCall());
        addPage2(c_, instancingClass_);
        ExecAnnotationMethodBlock.setValue(root_,"pkg.Annot","myAnnot","pkg.Annot",c_.getContext(),instancingClass_.getGlobalArgument(), c_.getStackCall());
        assertEq("@pkg.Annot(myAnnot=@pkg.Annot(pkg.Annot/1))",ExportAnnotationUtil.exportAnnotation("I","N","E",instancingClass_.getGlobalStruct()));
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
        AnalyzedTestContext c_ = validated(files_);
        ExecRootBlock root_ = c_.getClasses().getClassBody("pkg.Annot");
        AbstractPageEl instancingClass_ = ExecutingUtil.createAnnotation(c_.getContext(), "pkg.Annot",root_,  new StringMap<AnnotationTypeInfo>(),new CustList<Argument>(), c_.getStackCall());
        addPage2(c_, instancingClass_);
        ExecAnnotationMethodBlock.setValue(root_,"pkg.Annot","myAnnot","pkg.Annot",c_.getContext(),instancingClass_.getGlobalArgument(), c_.getStackCall());
        ExecAnnotationMethodBlock.setValue(root_,"pkg.Annot","myAnnot2","pkg.Annot",c_.getContext(),instancingClass_.getGlobalArgument(), c_.getStackCall());
        assertEq("@pkg.Annot(myAnnot=@pkg.Annot(pkg.Annot/1),myAnnot2=@pkg.Annot(pkg.Annot/1))",ExportAnnotationUtil.exportAnnotation("I","N","E",instancingClass_.getGlobalStruct()));
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
        AnalyzedTestContext c_ = validated(files_);
        ExecRootBlock root_ = c_.getClasses().getClassBody("pkg.Annot");
        AbstractPageEl instancingClass_ = ExecutingUtil.createAnnotation(c_.getContext(), "pkg.Annot",root_,  new StringMap<AnnotationTypeInfo>(),new CustList<Argument>(), c_.getStackCall());
        addPage2(c_, instancingClass_);
        AbstractPageEl instancingClass2_ = ExecutingUtil.createAnnotation(c_.getContext(), "pkg.Annot",root_,  new StringMap<AnnotationTypeInfo>(),new CustList<Argument>(), c_.getStackCall());
        ExecAnnotationMethodBlock.setValue(root_,"pkg.Annot","myAnnot","pkg.Annot",c_.getContext(),instancingClass2_.getGlobalArgument(), c_.getStackCall());
        c_.getStackCall().removeLastPage();
        addPage2(c_, instancingClass2_);
        ExecAnnotationMethodBlock.setValue(root_,"pkg.Annot","myAnnot","pkg.Annot",c_.getContext(),instancingClass_.getGlobalArgument(), c_.getStackCall());
        assertEq("@pkg.Annot(myAnnot=@pkg.Annot(myAnnot=@pkg.Annot(pkg.Annot/1)))",ExportAnnotationUtil.exportAnnotation("I","N","E",instancingClass_.getGlobalStruct()));
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
        AnalyzedTestContext c_ = validated(files_);
        ExecRootBlock root_ = c_.getClasses().getClassBody("pkg.Annot");
        AbstractPageEl instancingClass_ = ExecutingUtil.createAnnotation(c_.getContext(), "pkg.Annot",root_,  new StringMap<AnnotationTypeInfo>(),new CustList<Argument>(), c_.getStackCall());
        addPage2(c_, instancingClass_);
        AbstractPageEl instancingClass2_ = ExecutingUtil.createAnnotation(c_.getContext(), "pkg.Annot",root_,  new StringMap<AnnotationTypeInfo>(),new CustList<Argument>(), c_.getStackCall());
        ExecAnnotationMethodBlock.setValue(root_,"pkg.Annot","myAnnot","pkg.Annot",c_.getContext(),instancingClass2_.getGlobalArgument(), c_.getStackCall());
        ExecAnnotationMethodBlock.setValue(root_,"pkg.Annot","myAnnot2","pkg.Annot",c_.getContext(),instancingClass_.getGlobalArgument(), c_.getStackCall());
        c_.getStackCall().removeLastPage();
        addPage2(c_, instancingClass2_);
        ExecAnnotationMethodBlock.setValue(root_,"pkg.Annot","myAnnot","pkg.Annot",c_.getContext(),instancingClass2_.getGlobalArgument(), c_.getStackCall());
        ExecAnnotationMethodBlock.setValue(root_,"pkg.Annot","myAnnot2","pkg.Annot",c_.getContext(),instancingClass_.getGlobalArgument(), c_.getStackCall());
        assertEq("@pkg.Annot(myAnnot=@pkg.Annot(myAnnot=@pkg.Annot(pkg.Annot/2),myAnnot2=@pkg.Annot(pkg.Annot/1)),myAnnot2=@pkg.Annot(pkg.Annot/1))",ExportAnnotationUtil.exportAnnotation("I","N","E",instancingClass_.getGlobalStruct()));
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
        ExpressionLanguage ex_ = new ExpressionLanguage(new CustList<ExecOperationNode>());
        ex_.setArgument(null,null, null);
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
        AnalyzedTestContext c_ = validated(files_);
        assertNotNull(ExecHelper.getGenericTypeNameOrObject(c_.getContext(),""));
    }
    @Test
    public void safeObject() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext c_ = validated(files_);
        assertNotSame(ErrorType.NOTHING, ExecInherits.safeObject("","",c_.getContext()));
    }


    @Test
    public void hasToLookForParent() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext cont_ = validated(files_);
        assertNotNull(cont_.getContext());
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

    private void addPage(ContextEl _cont, AbstractPageEl _instancingClass, StackCall _stackCall) {
        ExecutingUtil.addPage(_cont, _instancingClass, _stackCall);
    }

    private static Struct getTrueException(AnalyzedTestContext _cont) {
        CallingState str_ = _cont.getStackCall().getCallingState();
        return ((CustomFoundExc) str_).getStruct();
    }

    private static AnalyzedTestContext validated(StringMap<String> _files) {
        AnalyzedTestContext cont_ = ctxAna();
        validateWithoutInit(_files,cont_);
        assertTrue(isEmptyErrors(cont_));
        forwardAndClear(cont_);
        cont_.setStackCall(StackCall.newInstance(InitPhase.NOTHING,cont_.getContext()));
        return cont_;
    }

    private static String reflectFormat(AnalyzedTestContext _context, String _first, String _second) {
        return ExecInherits.reflectFormat(_first,_second,_context.getContext());
    }

    private void addPage2(AnalyzedTestContext _c, AbstractPageEl _instancingClass2) {
        ExecutingUtil.addPage(_c.getContext(), _instancingClass2, _c.getStackCall());
    }

    private void wrapAndCall(AnalyzedTestContext _cont, ExecRootBlock _classBody, ExecNamedFunctionBlock _first) {
        ArgumentListCall argList_ = new ArgumentListCall();
        argList_.addArg(new Argument());
        ArgumentWrapper.helpArg(null);
        ExecHelper.getArgumentWrapper(new CustList<ArgumentWrapper>(),0);
        ExecTemplates.wrapAndCall(new ExecTypeFunction(_classBody, _first), "pkg.Ex",Argument.createVoid(), _cont.getContext(), _cont.getStackCall(), argList_, null);
        new ReflectGetFieldPageEl(null,null).receive(null,null,_cont.getContext(), _cont.getStackCall());
        new ReflectSetFieldPageEl(null,null,null).receive(null,null,_cont.getContext(), _cont.getStackCall());
    }

    private static String getFullTypeByBases(AnalyzedTestContext _context, String _first, String _second) {
        return ExecInherits.getFullTypeByBases(_first, _second, _context.getContext());
    }

    private static String getFullTypeByBases(ContextEl _context, String _first, String _second) {
        return ExecInherits.getFullTypeByBases(_first, _second, _context);
    }


    private String getFullObject(ContextEl _context, String _first, String _second) {
        return ExecInherits.getFullObject(_first, _second, _context);
    }

    private String getFullObject(AnalyzedTestContext _context, String _first, String _second) {
        return ExecInherits.getFullObject(_first, _second, _context.getContext());
    }

    private static String getSuperGeneric(AnalyzedTestContext _context, String _first, String _second) {
        return ExecInherits.getSuperGeneric(_first, _second, _context.getContext());
    }

    private static String getSuperGeneric(ContextEl _context, String _first, String _second) {
        return ExecInherits.getSuperGeneric(_first, _second, _context);
    }

}
