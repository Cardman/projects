package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.AnalyzedTestContext;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ErrorType;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.methods.ProcessMethodCommon;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.structs.*;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;
import code.util.StringMap;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public final class ExecTemplatesTest extends ProcessMethodCommon {

    private static final String CUST_CLASS = "pkg.CustClass";
    private static final String ARR_ARR_CUST_CLASS = "[[pkg.CustClass";
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
        assertSame(NullStruct.NULL_VALUE, ExecTemplates.getParent(0,"pkg.Ex",new IntStruct(1),c_));
        assertNotNull(getException(c_));
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
        Struct par_  = c_.getInit().processInit(c_,NullStruct.NULL_VALUE,"pkg.Ex",c_.getClasses().getClassBody("pkg.Ex"),"",-1);
        Struct in_ = c_.getInit().processInit(c_,par_,"pkg.Ex..Inner",c_.getClasses().getClassBody("pkg.Ex..Inner"),"",-1);
        ExecTemplates.getParent(0,"java.lang.Integer",in_,c_);
        assertNotNull(getException(c_));
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
        Struct par_  = c_.getInit().processInit(c_,NullStruct.NULL_VALUE,"pkg.Ex",c_.getClasses().getClassBody("pkg.Ex"),"",-1);
        Struct in_ = c_.getInit().processInit(c_,par_,"pkg.Ex..Inner",c_.getClasses().getClassBody("pkg.Ex..Inner"),"",-1);
        Struct inTwo_ = c_.getInit().processInit(c_,in_,"pkg.Ex..Inner",c_.getClasses().getClassBody("pkg.Ex..Inner"),"",-1);
        ExecTemplates.getParent(0,"java.lang.Integer",inTwo_,c_);
        assertNotNull(getException(c_));
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
        ArrayStruct arr_ = ExecTemplates.newCustomArray(c_.getStandards().getContent().getPrimTypes().getAliasPrimInteger(),new Ints(0),c_);
        assertSame(NullStruct.NULL_VALUE, ExecTemplates.getParent(0,"[pkg.Ex",arr_,c_));
        assertNotNull(getException(c_));
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
        ArrayStruct arr_ = ExecTemplates.newCustomArray(c_.getStandards().getContent().getPrimTypes().getAliasPrimInteger(),new Ints(0),c_);
        assertSame(NullStruct.NULL_VALUE, ExecTemplates.getParent(0,"pkg.Ex",arr_,c_));
        assertNotNull(getException(c_));
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
        ArrayStruct arr_ = ExecTemplates.newCustomArray(c_.getStandards().getContent().getPrimTypes().getAliasPrimInteger(),new Ints(0),c_);
        assertSame(NullStruct.NULL_VALUE, ExecTemplates.getParent(0,"[["+ c_.getStandards().getContent().getCoreNames().getAliasObject(),arr_,c_));
        assertNotNull(getException(c_));
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
        ArrayStruct arr_ = ExecTemplates.newCustomArray(c_.getStandards().getContent().getPrimTypes().getAliasPrimInteger(),new Ints(0),c_);
        assertSame(arr_, ExecTemplates.getParent(0,"["+ c_.getStandards().getContent().getCoreNames().getAliasObject(),arr_,c_));
        assertNull(getException(c_));
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
        Ints dims_ = new Ints(1);
        ArrayStruct customArray_ = ExecTemplates.newCustomArray(CUST_CLASS, dims_, cont_);
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
        Ints dims_ = new Ints(2);
        ArrayStruct customArray_ = ExecTemplates.newCustomArray(CUST_CLASS, dims_, cont_);
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
        Ints dims_ = new Ints(2,3);
        ArrayStruct customArray_ = ExecTemplates.newCustomArray(CUST_CLASS, dims_, cont_);
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

    private static String reflectFormat(ContextEl context_, String first_, String second_) {
        return ExecTemplates.reflectFormat(first_,second_,context_);
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
        String t_ = ExecTemplates.getQuickFullTypeByBases(first_, second_, context_);
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
        String t_ = ExecTemplates.getQuickFullTypeByBases(first_, second_, context_);
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
        assertEq("",t_);
    }

    private String getFullObject(ContextEl context_, String first_, String second_) {
        return ExecTemplates.getFullObject(first_, second_, context_);
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

    private static String getSuperGeneric(ContextEl context_, String first_, String second_) {
        return ExecTemplates.getSuperGeneric(first_, second_, context_);
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

    private static String getFullTypeByBases(ContextEl context_, String first_, String second_) {
        return ExecTemplates.getFullTypeByBases(first_, second_, context_);
    }

    @Test
    public void setCheckedElements1Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = validated(files_);
        ArrayStruct arr_ = new ArrayStruct(1,"[$int");
        arr_.set(0,new IntStruct(0));
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(Argument.createVoid());
        ExecTemplates.setCheckedElements(args_,arr_,cont_);
        assertNotNull(getException(cont_));
    }
    @Test
    public void okArgs1Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = validated(files_);
        MethodId id_ = new MethodId(MethodAccessKind.STATIC,"method", new StringList("$int"),true);
        ArrayStruct arr_ = defaultArray1(NullStruct.NULL_VALUE, "[$int");
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(new Argument(arr_));
        assertTrue(!ExecTemplates.okArgs(id_, "",args_, cont_,null));
        assertNotNull(getException(cont_));
    }
    @Test
    public void okArgs2Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = validated(files_);
        MethodId id_ = new MethodId(MethodAccessKind.STATIC,"method", new StringList("java.lang.Number"),true);
        ArrayStruct arr_ = defaultArray2("[java.lang.Number");
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(new Argument(arr_));
        assertTrue(!ExecTemplates.okArgs(id_, "",args_, cont_,null));
        assertNotNull(getException(cont_));
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
        assertTrue(!ExecTemplates.okArgs(id_, "",args_, cont_,null));
        assertNotNull(getException(cont_));
    }
    @Test
    public void okArgs4Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = validated(files_);
        MethodId id_ = new MethodId(MethodAccessKind.STATIC,"method", new StringList(""),false);
        ArrayStruct arr_ = defaultArray2("[java.lang.Number");
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(new Argument(arr_));
        assertTrue(!ExecTemplates.okArgs(id_, "",args_, cont_,null));
        assertNotNull(getException(cont_));
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
        Struct atr_ = cont_.getInit().processInit(cont_, NullStruct.NULL_VALUE, "pkg.Ex<$int>",cont_.getClasses().getClassBody("pkg.Ex"), "", -1);
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(new Argument(atr_));
        assertTrue(!ExecTemplates.okArgs(id_, "pkg.Ex",args_, cont_,null));
        assertNotNull(getException(cont_));
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
        Struct atr_ = cont_.getInit().processInit(cont_, NullStruct.NULL_VALUE, "pkg.Ex<$int>",cont_.getClasses().getClassBody("pkg.Ex"), "", -1);
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(new Argument(atr_));
        assertTrue(!ExecTemplates.okArgs(id_, "pkg.Ex<$int>",args_, cont_,null));
        assertNotNull(getException(cont_));
    }
    @Test
    public void okArgs7Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = validated(files_);
        MethodId id_ = new MethodId(MethodAccessKind.STATIC,"method", new StringList(""),false);
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(new Argument(NullStruct.NULL_VALUE));
        assertTrue(!ExecTemplates.okArgs(id_, "",args_, cont_,null));
        assertNotNull(getException(cont_));
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
        ExecRootBlock classBody_ = cont_.getClasses().getClassBody("pkg.Ex");
        ExecTemplates.okArgs(classBody_,ExecBlock.getMethodBodiesById(classBody_,id_).first(),false,"pkg.Ex<$int>",args_, cont_,null);
        assertNotNull(getException(cont_));
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
        ExecRootBlock classBody_ = cont_.getClasses().getClassBody("pkg.Ex");
        ExecTemplates.okArgs(classBody_,ExecBlock.getMethodBodiesById(classBody_,id_).first(),false,"pkg.Ex<$int>",args_, cont_,null);
        assertNotNull(getException(cont_));
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
        ExecTemplates.okArgs(cont_.getClasses().getClassBody("pkg.Ex"),null,false,"pkg.Ex<$int>",args_, cont_,null);
        assertNotNull(getException(cont_));
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
        ExecTemplates.okArgs(cont_.getClasses().getClassBody("pkg.Ex"),null,false,"pkg.Ex",args_, cont_,null);
        assertNotNull(getException(cont_));
    }
    @Test
    public void getErrorWhenContain1Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = validated(files_);
        ArrayStruct arr_ = defaultArray();
        assertSame(ErrorType.NPE, ExecTemplates.getErrorWhenContain(arr_, NullStruct.NULL_VALUE, NullStruct.NULL_VALUE, cont_));
    }
    @Test
    public void getErrorWhenContain2Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = validated(files_);
        ArrayStruct arr_ = defaultArray();
        assertSame(ErrorType.CAST, ExecTemplates.getErrorWhenContain(arr_, new StringStruct(""), NullStruct.NULL_VALUE, cont_));
    }
    @Test
    public void getErrorWhenIndex1Test() {
        ArrayStruct arr_ = defaultArray();
        assertSame(ErrorType.NPE, ExecTemplates.getErrorWhenIndex(arr_, NullStruct.NULL_VALUE));
    }
    @Test
    public void getErrorWhenIndex2Test() {
        ArrayStruct arr_ = defaultArray();
        assertSame(ErrorType.CAST, ExecTemplates.getErrorWhenIndex(arr_, new StringStruct("")));
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
        ExecutingUtil.addPage(cont_,ExecutingUtil.createInstancingClass(cont_,cont_.getClasses().getClassBody("pkg.Ex"),"pkg.Ex",null));
        ExecTemplates.getIndexLoop(cont_,"", cont_.getLastPage(),-1);
        assertNotNull(getException(cont_));
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
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(cont_, cont_.getClasses().getClassBody("pkg.Ex"), "pkg.Ex", null);
        instancingClass_.setCache(new Cache());
        ExecutingUtil.addPage(cont_, instancingClass_);
        ExecTemplates.getIndexLoop(cont_,"", cont_.getLastPage(),0);
        assertNotNull(getException(cont_));
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
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(cont_, cont_.getClasses().getClassBody("pkg.Ex"), "pkg.Ex", null);
        Cache cache_ = new Cache();
        LoopVariable loopVariable = new LoopVariable();
        loopVariable.setIndex(2);
        loopVariable.setIndexClassName(cont_.getStandards().getContent().getPrimTypes().getAliasPrimInteger());
        cache_.addLoop("myvar", loopVariable);
        cache_.setClassLoopValue(-1,"");
        instancingClass_.setCache(cache_);
        ExecutingUtil.addPage(cont_, instancingClass_);
        Argument myvar_ = ExecTemplates.getIndexLoop(cont_, "myvar", cont_.getLastPage(),0);
        assertNull(getException(cont_));
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
        ExecutingUtil.addPage(cont_,ExecutingUtil.createInstancingClass(cont_,cont_.getClasses().getClassBody("pkg.Ex"),"pkg.Ex",null));
        ExecTemplates.getValue(cont_,"", cont_.getLastPage(),-1);
        assertNotNull(getException(cont_));
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
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(cont_, cont_.getClasses().getClassBody("pkg.Ex"), "pkg.Ex", null);
        instancingClass_.setCache(new Cache());
        ExecutingUtil.addPage(cont_, instancingClass_);
        ExecTemplates.getValue(cont_,"", cont_.getLastPage(),0);
        assertNotNull(getException(cont_));
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
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(cont_, cont_.getClasses().getClassBody("pkg.Ex"), "pkg.Ex", null);
        Cache cache_ = new Cache();
        cache_.addLocal("myvar", LocalVariable.newLocalVariable(new IntStruct(2),cont_));
        cache_.setClassLocalValue(-1,"");
        instancingClass_.setCache(cache_);
        ExecutingUtil.addPage(cont_, instancingClass_);
        Argument myvar_ = ExecTemplates.getValue(cont_, "myvar", cont_.getLastPage(),0);
        assertNull(getException(cont_));
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
        ExecutingUtil.addPage(cont_,ExecutingUtil.createInstancingClass(cont_,cont_.getClasses().getClassBody("pkg.Ex"),"pkg.Ex",null));
        ExecTemplates.setValue(cont_,"", cont_.getLastPage(),null,-1);
        assertNotNull(getException(cont_));
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
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(cont_, cont_.getClasses().getClassBody("pkg.Ex"), "pkg.Ex", null);
        instancingClass_.setCache(new Cache());
        ExecutingUtil.addPage(cont_, instancingClass_);
        ExecTemplates.setValue(cont_,"", cont_.getLastPage(),null,0);
        assertNotNull(getException(cont_));
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
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(cont_, cont_.getClasses().getClassBody("pkg.Ex"), "pkg.Ex", null);
        Cache cache_ = new Cache();
        cache_.addLocal("myvar", LocalVariable.newLocalVariable(new IntStruct(2),cont_));
        instancingClass_.setCache(cache_);
        ExecutingUtil.addPage(cont_, instancingClass_);
        Argument myvar_ = ExecTemplates.setValue(cont_, "myvar", cont_.getLastPage(), new Argument(new IntStruct(4)),0);
        assertNull(getException(cont_));
        assertEq(4,getNumber(myvar_));
        assertEq(4,getNumber(new Argument(cache_.getLocalValue("myvar",0))));
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
        ExecutingUtil.addPage(cont_,ExecutingUtil.createInstancingClass(cont_,cont_.getClasses().getClassBody("pkg.Ex"),"pkg.Ex",null));
        ExecTemplates.incrIndexLoop(cont_,"", cont_.getLastPage(), 0);
        assertNotNull(getException(cont_));
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
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(cont_, cont_.getClasses().getClassBody("pkg.Ex"), "pkg.Ex", null);
        instancingClass_.setCache(new Cache());
        ExecutingUtil.addPage(cont_, instancingClass_);
        ExecTemplates.incrIndexLoop(cont_,"", cont_.getLastPage(), 0);
        assertNotNull(getException(cont_));
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
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(cont_, cont_.getClasses().getClassBody("pkg.Ex"), "pkg.Ex", null);
        Cache cache_ = new Cache();
        LoopVariable loopVariable = new LoopVariable();
        loopVariable.setIndex(2);
        loopVariable.setIndexClassName(cont_.getStandards().getContent().getPrimTypes().getAliasPrimInteger());
        cache_.addLoop("myvar", loopVariable);
        instancingClass_.setCache(cache_);
        ExecutingUtil.addPage(cont_, instancingClass_);
        ExecTemplates.incrIndexLoop(cont_,"myvar", cont_.getLastPage(), 0);
        assertNull(getException(cont_));
        assertEq(3,getNumber(new Argument(cache_.getLoopValue("myvar",0))));
    }
    @Test
    public void trySet1() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = validated(files_);
        ArrayStruct str_ = new ArrayStruct(1,StringExpUtil.getPrettyArrayType(cont_.getStandards().getCoreNames().getAliasObject()));
        ExecTemplates.trySet(str_,-1,NullStruct.NULL_VALUE);
        assertNull(str_.get(0));
    }
    @Test
    public void trySet2() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = validated(files_);
        ArrayStruct str_ = new ArrayStruct(1,StringExpUtil.getPrettyArrayType(cont_.getStandards().getCoreNames().getAliasObject()));
        ExecTemplates.trySet(str_,1,NullStruct.NULL_VALUE);
        assertNull(str_.get(0));
    }
    @Test
    public void trySet3() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = validated(files_);
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
        ContextEl cont_ = validated(files_);
        ExecRootBlock classBody_ = cont_.getClasses().getClassBody("pkg.Ex");
        ExecutingUtil.addPage(cont_,ExecutingUtil.createInstancingClass(cont_, classBody_,"pkg.Ex",null));
        ExecNamedFunctionBlock first_ = ExecBlock.getMethodBodiesById(classBody_, new MethodId(MethodAccessKind.STATIC, "m", new StringList("$int"))).first();
        ExecTemplates.wrapAndCall(first_,classBody_,"pkg.Ex",Argument.createVoid(),new CustList<Argument>(new Argument()), cont_);
        assertNotNull(getException(cont_));
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
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(cont_, cont_.getClasses().getClassBody("pkg.Ex"), "pkg.Ex", null);
        ExecutingUtil.addPage(cont_, instancingClass_);
        assertTrue(!ExecTemplates.hasBlockBreak(instancingClass_,""));
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
        ContextEl cont_ = validated(files_);
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(cont_, cont_.getClasses().getClassBody("pkg.Ex"), "pkg.Ex", null);
        ExecutingUtil.addPage(cont_, instancingClass_);
        assertTrue(!ExecTemplates.hasBlockContinue(cont_,instancingClass_,""));
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
        ContextEl cont_ = validated(files_);
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(cont_, cont_.getClasses().getClassBody("pkg.Ex"), "pkg.Ex", null);
        ExecutingUtil.addPage(cont_, instancingClass_);
        ExecTemplates.setVisited(instancingClass_,null);
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
        ContextEl cont_ = validated(files_);
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(cont_, cont_.getClasses().getClassBody("pkg.Ex"), "pkg.Ex", null);
        ExecutingUtil.addPage(cont_, instancingClass_);
        ExecTemplates.processFinally(cont_,null);
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
        ContextEl cont_ = validated(files_);
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(cont_, cont_.getClasses().getClassBody("pkg.Ex"), "pkg.Ex", null);
        ExecutingUtil.addPage(cont_, instancingClass_);
        ExecTemplates.processElseIf(cont_,null);
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
        ContextEl cont_ = validated(files_);
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(cont_, cont_.getClasses().getClassBody("pkg.Ex"), "pkg.Ex", null);
        ExecutingUtil.addPage(cont_, instancingClass_);
        ExecTemplates.processElse(cont_,null);
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
        ContextEl cont_ = validated(files_);
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(cont_, cont_.getClasses().getClassBody("pkg.Ex"), "pkg.Ex", null);
        ExecutingUtil.addPage(cont_, instancingClass_);
        ExecTemplates.processDo(cont_,null);
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
        ContextEl cont_ = validated(files_);
        AbstractPageEl instancingClass_ = ExecutingUtil.createInstancingClass(cont_, cont_.getClasses().getClassBody("pkg.Ex"), "pkg.Ex", null);
        ExecutingUtil.addPage(cont_, instancingClass_);
        ExecTemplates.processBlockAndRemove(cont_,null);
        assertNull(instancingClass_.getReadWrite());
    }

    private static ArrayStruct defaultArray() {
        ArrayStruct array_ = new ArrayStruct(1, "[java.lang.Number");
        array_.set(0, NullStruct.NULL_VALUE);
        return array_;
    }

    private static ArrayStruct defaultArray1(NullStruct _value, String _className) {
        return defaultArr(_value, _className);
    }

    private static ArrayStruct defaultArray2(String s) {
        return defaultArr(new StringStruct(""),s);
    }

    private static ArrayStruct defaultArr(Struct _value, String _className) {
        ArrayStruct array_ = new ArrayStruct(1, _className);
        array_.set(0,_value);
        return array_;
    }


    @Test
    public void hasToLookForParent() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = validated(files_);
        assertTrue(!ExecTemplates.hasToLookForParent(cont_,"",null));
    }

    private static ContextEl validated(StringMap<String> files_) {
        AnalyzedTestContext cont_ = ctxAna();
        validateWithoutInit(files_,cont_);
        assertTrue(isEmptyErrors(cont_));
        forwardAndClear(cont_);
        return cont_.getContext();
    }

}
