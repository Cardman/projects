package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.ClassesUtil;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.ErrorType;
import code.expressionlanguage.exec.ExecutingUtil;
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
        ContextEl c_ = unfullValidateOverridingMethods(files_);
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
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        Struct par_  = c_.getInit().processInit(c_,NullStruct.NULL_VALUE,"pkg.Ex","",-1);
        Struct in_ = c_.getInit().processInit(c_,par_,"pkg.Ex..Inner","",-1);
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
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        Struct par_  = c_.getInit().processInit(c_,NullStruct.NULL_VALUE,"pkg.Ex","",-1);
        Struct in_ = c_.getInit().processInit(c_,par_,"pkg.Ex..Inner","",-1);
        Struct inTwo_ = c_.getInit().processInit(c_,in_,"pkg.Ex..Inner","",-1);
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
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ArrayStruct arr_ = ExecTemplates.newCustomArray(c_.getStandards().getAliasPrimInteger(),new Ints(0),c_);
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
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ArrayStruct arr_ = ExecTemplates.newCustomArray(c_.getStandards().getAliasPrimInteger(),new Ints(0),c_);
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
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ArrayStruct arr_ = ExecTemplates.newCustomArray(c_.getStandards().getAliasPrimInteger(),new Ints(0),c_);
        assertSame(NullStruct.NULL_VALUE, ExecTemplates.getParent(0,"[["+c_.getStandards().getAliasObject(),arr_,c_));
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
        ContextEl c_ = unfullValidateOverridingMethods(files_);
        ArrayStruct arr_ = ExecTemplates.newCustomArray(c_.getStandards().getAliasPrimInteger(),new Ints(0),c_);
        assertSame(arr_, ExecTemplates.getParent(0,"["+c_.getStandards().getAliasObject(),arr_,c_));
        assertNull(getException(c_));
    }

    @Test
    public void newCustomArray1Test() {
        ContextEl cont_ = simpleContextEl();
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
        ContextEl cont_ = simpleContextEl();
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
        ContextEl cont_ = simpleContextEl();
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
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?java.lang.Object>";
        String second_ = "pkg.Ex<[#T>";
        assertEq("pkg.Ex<?[java.lang.Object>",ExecTemplates.reflectFormat(first_,second_,context_));
    }
    @Test
    public void reflectFormat2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<!java.lang.Object>";
        String second_ = "pkg.Ex<[#T>";
        assertEq("pkg.Ex<![java.lang.Object>",ExecTemplates.reflectFormat(first_,second_,context_));
    }
    @Test
    public void reflectFormat3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?>";
        String second_ = "pkg.Ex<[#T>";
        assertEq("pkg.Ex<?>",ExecTemplates.reflectFormat(first_,second_,context_));
    }
    @Test
    public void reflectFormat4Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?>";
        String second_ = "pkg.Ex<[#E>";
        assertEq("pkg.Ex<[#E>",ExecTemplates.reflectFormat(first_,second_,context_));
    }
    @Test
    public void reflectFormat5Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?>";
        String second_ = "[#E";
        assertEq("[#E",ExecTemplates.reflectFormat(first_,second_,context_));
    }
    @Test
    public void reflectFormat6Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?java.lang.Object>";
        String second_ = "pkg.Ex<![#T>";
        assertEq("pkg.Ex<![java.lang.Object>",ExecTemplates.reflectFormat(first_,second_,context_));
    }
    @Test
    public void reflectFormat7Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<!java.lang.Object>";
        String second_ = "pkg.Ex<?[#T>";
        assertEq("pkg.Ex<?[java.lang.Object>",ExecTemplates.reflectFormat(first_,second_,context_));
    }
    @Test
    public void reflectFormat8Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?java.lang.Object>";
        String second_ = "pkg.Ex<?[#T>";
        assertEq("pkg.Ex<?[java.lang.Object>",ExecTemplates.reflectFormat(first_,second_,context_));
    }
    @Test
    public void reflectFormat9Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<!java.lang.Object>";
        String second_ = "pkg.Ex<![#T>";
        assertEq("pkg.Ex<![java.lang.Object>",ExecTemplates.reflectFormat(first_,second_,context_));
    }

    @Test
    public void getGenericTypeByBases3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>:ExTwo<$iterable<?T>> {}\n");
        xml_.append("$public $class pkg.ExTwo<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.Object>";
        String second_ = "pkg.ExTwo";
        String t_ = ExecTemplates.getFullObject(first_, second_, context_);
        assertEq("pkg.ExTwo<java.lang.$iterable<?java.lang.Object>>",t_);
    }

    @Test
    public void getGenericTypeByBases4Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>:ExTwo<$iterable<?T>> {}\n");
        xml_.append("$public $class pkg.ExTwo<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
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
        ContextEl context_ = unfullValidateOverridingMethods(files_);
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
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex";
        String second_ = "pkg.ExTwo";
        String t_ = ExecTemplates.getFullObject(first_, second_, context_);
        assertEq("",t_);
    }

    @Test
    public void getGenericTypeByBases16Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>:ExTwo<$iterable<?T>> {}\n");
        xml_.append("$public $class pkg.ExTwo<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.String>";
        String second_ = "";
        String t_ = ExecTemplates.getSuperGeneric(first_, second_, context_);
        assertEq("",t_);
    }


    @Test
    public void getGenericTypeByBases17Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>:ExTwo<$iterable<?T>> {}\n");
        xml_.append("$public $class pkg.ExTwo<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex";
        String second_ = "";
        String t_ = ExecTemplates.getSuperGeneric(first_, second_, context_);
        assertEq("",t_);
    }



    @Test
    public void setCheckedElements1Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Struct[] instance_ = new Struct[1];
        instance_[0] = new IntStruct(0);
        ArrayStruct arr_ = new ArrayStruct(instance_,"[$int");
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(Argument.createVoid());
        ExecTemplates.setCheckedElements(args_,arr_,cont_);
        assertNotNull(getException(cont_));
    }
    @Test
    public void okArgs1Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        MethodId id_ = new MethodId(MethodAccessKind.STATIC,"method", new StringList("$int"),true);
        Struct[] instance_ = new Struct[1];
        instance_[0] = NullStruct.NULL_VALUE;
        ArrayStruct arr_ = new ArrayStruct(instance_,"[$int");
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(new Argument(arr_));
        assertTrue(!ExecTemplates.okArgs(id_,false,"",args_, cont_,null));
        assertNotNull(getException(cont_));
    }
    @Test
    public void okArgs2Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        MethodId id_ = new MethodId(MethodAccessKind.STATIC,"method", new StringList("java.lang.Number"),true);
        Struct[] instance_ = new Struct[1];
        instance_[0] = new StringStruct("");
        ArrayStruct arr_ = new ArrayStruct(instance_,"[java.lang.Number");
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(new Argument(arr_));
        assertTrue(!ExecTemplates.okArgs(id_,false,"",args_, cont_,null));
        assertNotNull(getException(cont_));
    }
    @Test
    public void okArgs3Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        MethodId id_ = new MethodId(MethodAccessKind.STATIC,"method", new StringList("java.lang.Number"),true);
        Struct[] instance_ = new Struct[1];
        instance_[0] = new StringStruct("");
        ArrayStruct arr_ = new ArrayStruct(instance_,"[java.lang.Number");
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(new Argument(arr_));
        arr_ = new ArrayStruct(instance_,"[java.lang.Number");
        args_.add(new Argument(arr_));
        assertTrue(!ExecTemplates.okArgs(id_,false,"",args_, cont_,null));
        assertNotNull(getException(cont_));
    }
    @Test
    public void okArgs4Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        MethodId id_ = new MethodId(MethodAccessKind.STATIC,"method", new StringList(""),false);
        Struct[] instance_ = new Struct[1];
        instance_[0] = new StringStruct("");
        ArrayStruct arr_ = new ArrayStruct(instance_,"[java.lang.Number");
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(new Argument(arr_));
        assertTrue(!ExecTemplates.okArgs(id_,false,"",args_, cont_,null));
        assertNotNull(getException(cont_));
    }
    @Test
    public void okArgs5Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        MethodId id_ = new MethodId(MethodAccessKind.INSTANCE,"method", new StringList(""),false);
        Struct atr_ = cont_.getInit().processInit(cont_, NullStruct.NULL_VALUE, "pkg.Ex<$int>", "", -1);
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(new Argument(atr_));
        assertTrue(!ExecTemplates.okArgs(id_,false,"pkg.Ex",args_, cont_,null));
        assertNotNull(getException(cont_));
    }
    @Test
    public void okArgs6Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<#E> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        MethodId id_ = new MethodId(MethodAccessKind.INSTANCE,"method", new StringList("pkg.Ex"),false);
        Struct atr_ = cont_.getInit().processInit(cont_, NullStruct.NULL_VALUE, "pkg.Ex<$int>", "", -1);
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(new Argument(atr_));
        assertTrue(!ExecTemplates.okArgs(id_,false,"pkg.Ex<$int>",args_, cont_,null));
        assertNotNull(getException(cont_));
    }
    @Test
    public void okArgs7Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        MethodId id_ = new MethodId(MethodAccessKind.STATIC,"method", new StringList(""),false);
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(new Argument(NullStruct.NULL_VALUE));
        assertTrue(!ExecTemplates.okArgs(id_,false,"",args_, cont_,null));
        assertNotNull(getException(cont_));
    }
    @Test
    public void getErrorWhenContain1Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Struct[] instance_ = new Struct[1];
        instance_[0] = NullStruct.NULL_VALUE;
        ArrayStruct arr_ = new ArrayStruct(instance_,"[java.lang.Number");
        assertSame(ErrorType.NPE, ExecTemplates.getErrorWhenContain(arr_, NullStruct.NULL_VALUE, NullStruct.NULL_VALUE, cont_));
    }
    @Test
    public void getErrorWhenContain2Test() {
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        Struct[] instance_ = new Struct[1];
        instance_[0] = NullStruct.NULL_VALUE;
        ArrayStruct arr_ = new ArrayStruct(instance_,"[java.lang.Number");
        assertSame(ErrorType.CAST, ExecTemplates.getErrorWhenContain(arr_, new StringStruct(""), NullStruct.NULL_VALUE, cont_));
    }
    @Test
    public void getErrorWhenIndex1Test() {
        Struct[] instance_ = new Struct[1];
        instance_[0] = NullStruct.NULL_VALUE;
        ArrayStruct arr_ = new ArrayStruct(instance_,"[java.lang.Number");
        assertSame(ErrorType.NPE, ExecTemplates.getErrorWhenIndex(arr_, NullStruct.NULL_VALUE));
    }
    @Test
    public void getErrorWhenIndex2Test() {
        Struct[] instance_ = new Struct[1];
        instance_[0] = NullStruct.NULL_VALUE;
        ArrayStruct arr_ = new ArrayStruct(instance_,"[java.lang.Number");
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
        ContextEl cont_ = getSimpleContextEl();
        Classes.validateAll(files_,cont_);
        ExecutingUtil.addPage(cont_,ExecutingUtil.createInstancingClass(cont_,"pkg.Ex",null));
        ExecTemplates.getIndexLoop(cont_,"", cont_.getLastPage());
        assertNotNull(getException(cont_));
    }
    @Test
    public void getValue() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = getSimpleContextEl();
        Classes.validateAll(files_,cont_);
        ExecutingUtil.addPage(cont_,ExecutingUtil.createInstancingClass(cont_,"pkg.Ex",null));
        ExecTemplates.getValue(cont_,"", cont_.getLastPage());
        assertNotNull(getException(cont_));
    }
    @Test
    public void setValue() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = getSimpleContextEl();
        Classes.validateAll(files_,cont_);
        ExecutingUtil.addPage(cont_,ExecutingUtil.createInstancingClass(cont_,"pkg.Ex",null));
        ExecTemplates.setValue(cont_,"", cont_.getLastPage(),null);
        assertNotNull(getException(cont_));
    }
    @Test
    public void incrValue() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = getSimpleContextEl();
        Classes.validateAll(files_,cont_);
        ExecutingUtil.addPage(cont_,ExecutingUtil.createInstancingClass(cont_,"pkg.Ex",null));
        ExecTemplates.incrIndexLoop(cont_,"", cont_.getLastPage());
        assertNotNull(getException(cont_));
    }
    private ContextEl unfullValidateOverridingMethods(StringMap<String> _files) {
        ContextEl cont_ = getSimpleContextEl();
        parseCustomFiles(_files, cont_);
        assertTrue(cont_.isEmptyErrors());
        ClassesUtil.validateInheritingClasses(cont_);
        assertTrue(cont_.isEmptyErrors());
        return cont_;
    }

    private ContextEl simpleContextEl() {
        return getSimpleContextEl();
    }
}
