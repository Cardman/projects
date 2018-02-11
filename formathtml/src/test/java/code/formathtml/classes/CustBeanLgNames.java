package code.formathtml.classes;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.opers.util.BooleanStruct;
import code.expressionlanguage.opers.util.CharStruct;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.IntStruct;
import code.expressionlanguage.opers.util.LongStruct;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.opers.util.NullStruct;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.StringStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardField;
import code.expressionlanguage.stds.StandardMethod;
import code.formathtml.DefaultInitialization;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public class CustBeanLgNames extends BeanLgNames {

    private static final String ALIAS_SB = "sb";
    private static final String ALIAS_LS = "ls";
    private static final String ALIAS_LSE = "lse";
    public CustBeanLgNames() {
        setSelectedBoolean(ALIAS_SB);
        setCustList(ALIAS_LS);
        setCustMap(ALIAS_LSE);
        DefaultInitialization.basicStandards(this);
    }
    @Override
    public void buildOther() {
        buildBeans();
        StringMap<StandardField> fields_;
        ObjectMap<MethodId, StandardMethod> methods_;
        CustList<StandardConstructor> constructors_;
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        StandardMethod method_;
        StringList params_;
        StandardClass cl_;
//        cl_ = new StandardClass("code.util.StringList", fields_, constructors_, methods_, getCustList(), MethodModifier.FINAL);
        cl_ = new StandardClass("code.util.StringList", fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        cl_.getDirectInterfaces().add(getAliasCountable());
        cl_.getDirectInterfaces().add(getAliasSimpleIterableType());
        cl_.getDirectInterfaces().add(getAliasDisplayable());
        cl_.setIterative(getAliasString());
        params_ = new StringList();
        method_ = new StandardMethod("getReverse",params_,"code.util.StringList", false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod("get",params_,getAliasObject(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        getStandards().put("code.util.StringList", cl_);
        buildBeanOne();
        buildBeanTwo();
        buildBeanThree();
        buildBeanFour();
        buildBeanFive();
        buildBeanSix();
        buildBeanSeven();
        buildBeanEight();
        buildComposite();
        buildBeanUtil();
        buildEnumNumber();
        buildEnumNumbers();
        buildEncapsFields();
        buildGeneObjs();
        buildGeneObjects();
        buildPickableList();
        buildPickableList();
        buildRateEq();
        buildSimpleDataBase();
        buildNatTreeMapStringInteger();
    }
    private void buildBeanOne() {
        StringMap<StandardField> fields_;
        ObjectMap<MethodId, StandardMethod> methods_;
        CustList<StandardConstructor> constructors_;
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        StandardMethod method_;
        StringList params_;
        StandardClass cl_;
        cl_ = new StandardClass("code.formathtml.classes.BeanOne", fields_, constructors_, methods_, getBean(), MethodModifier.FINAL);
        fields_.put("chosenNumber",new StandardField("chosenNumber","code.formathtml.classes.EnumNumber",false,false,cl_));
        fields_.put("chosenNumbers",new StandardField("chosenNumbers","code.formathtml.classes.EnumNumbers",false,false,cl_));
        fields_.put("combobox",new StandardField("combobox","code.formathtml.classes.EnumNumbers",false,false,cl_));
        fields_.put("commonClass",new StandardField("commonClass",getAliasString(),false,false,cl_));
        fields_.put("composite",new StandardField("composite","code.formathtml.classes.Composite",false,false,cl_));
        fields_.put("map",new StandardField("map",getCustMap(),false,false,cl_));
        fields_.put("message",new StandardField("message",getAliasString(),false,false,cl_));
        fields_.put("numbers",new StandardField("numbers",getCustMap(),false,false,cl_));
        fields_.put("selectedString",new StandardField("selectedString",getAliasString(),false,false,cl_));
        fields_.put("translations",new StandardField("translations",getCustMap(),false,false,cl_));
        fields_.put("tree",new StandardField("tree",getCustMap(),false,false,cl_));
        params_ = new StringList(getAliasLong());
        method_ = new StandardMethod("getList",params_,getCustList(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasLong());
        method_ = new StandardMethod("getDouble",params_,getAliasPrimInteger(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("goToNullPage",params_,getAliasString(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("goToPage",params_,getAliasString(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasLong());
        method_ = new StandardMethod("goToPage",params_,getAliasString(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasLong());
        method_ = new StandardMethod("invokeMethod",params_,getAliasString(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("hasMoreThanOne",params_,getAliasPrimBoolean(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("getComposite",params_,"code.formathtml.classes.Composite", false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("getDefaultChoice",params_,"code.formathtml.classes.EnumNumber", false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("getComposites",params_,getCustList(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasLong());
        method_ = new StandardMethod("getSpanClass",params_,getAliasString(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasLong(),getAliasLong(),getAliasLong());
        method_ = new StandardMethod("getSpanClasses",params_,getAliasString(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod("getStandard",params_,getAliasString(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasLong());
        method_ = new StandardMethod("getTrans",params_,getAliasString(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("validateStrings",params_,getAliasVoid(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        getStandards().put("code.formathtml.classes.BeanOne", cl_);
    }
    private void buildBeanTwo() {
        StringMap<StandardField> fields_;
        ObjectMap<MethodId, StandardMethod> methods_;
        CustList<StandardConstructor> constructors_;
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        StandardMethod method_;
        StringList params_;
        StandardClass cl_;
        cl_ = new StandardClass("code.formathtml.classes.BeanTwo", fields_, constructors_, methods_, getBean(), MethodModifier.FINAL);
        fields_.put("checked",new StandardField("checked",getAliasPrimBoolean(),false,false,cl_));
        fields_.put("choose",new StandardField("choose",getAliasString(),false,false,cl_));
        fields_.put("chosenNumber",new StandardField("chosenNumber","code.formathtml.classes.EnumNumber",false,false,cl_));
        fields_.put("field",new StandardField("field",getAliasString(),false,false,cl_));
        fields_.put("nullableCheckbox",new StandardField("nullableCheckbox",getAliasBoolean(),false,false,cl_));
        fields_.put("nullableInt",new StandardField("nullableInt",getAliasLong(),false,false,cl_));
        fields_.put("rate",new StandardField("rate","code.formathtml.classes.Rate",false,false,cl_));
        fields_.put("typedInt",new StandardField("typedInt",getAliasPrimInteger(),false,false,cl_));
        fields_.put("typedString",new StandardField("typedString",getAliasString(),false,false,cl_));
        params_ = new StringList();
        method_ = new StandardMethod("getChosenNumbers",params_,getCustList(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("go",params_,getAliasString(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasLong());
        method_ = new StandardMethod("go",params_,getAliasString(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("goTextArea",params_,getAliasString(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod("setTypedInt",params_,getAliasVoid(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod("setTypedString",params_,getAliasVoid(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("validate",params_,getAliasVoid(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        getStandards().put("code.formathtml.classes.BeanTwo", cl_);
    }
    private void buildBeanThree() {
        StringMap<StandardField> fields_;
        ObjectMap<MethodId, StandardMethod> methods_;
        CustList<StandardConstructor> constructors_;
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        StandardClass cl_;
        cl_ = new StandardClass("code.formathtml.classes.BeanThree", fields_, constructors_, methods_, getBean(), MethodModifier.FINAL);
        fields_.put("index",new StandardField("index",getAliasPrimInteger(),false,false,cl_));
        fields_.put("indexTwo",new StandardField("indexTwo",getAliasPrimInteger(),false,false,cl_));
        fields_.put("numbers",new StandardField("numbers",getCustList(),false,false,cl_));
        fields_.put("numbersTwo",new StandardField("numbersTwo",getCustList(),false,false,cl_));
        getStandards().put("code.formathtml.classes.BeanThree", cl_);
    }
    private void buildBeanFour() {
        StringMap<StandardField> fields_;
        ObjectMap<MethodId, StandardMethod> methods_;
        CustList<StandardConstructor> constructors_;
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        StandardMethod method_;
        StringList params_;
        StandardClass cl_;
        cl_ = new StandardClass("code.formathtml.classes.BeanFour", fields_, constructors_, methods_, getBean(), MethodModifier.FINAL);
        params_ = new StringList(getAliasInteger());
        method_ = new StandardMethod("setInvisibleField",params_,getAliasVoid(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod("setInvisibleIntField",params_,getAliasVoid(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod("setter",params_,getAliasVoid(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        getStandards().put("code.formathtml.classes.BeanFour", cl_);
    }
    private void buildBeanFive() {
        StringMap<StandardField> fields_;
        ObjectMap<MethodId, StandardMethod> methods_;
        CustList<StandardConstructor> constructors_;
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        StandardMethod method_;
        StringList params_;
        StandardClass cl_;
        cl_ = new StandardClass("code.formathtml.classes.BeanFive", fields_, constructors_, methods_, getBean(), MethodModifier.FINAL);
        fields_.put("chosenNumbers",new StandardField("chosenNumbers","code.formathtml.classes.EnumNumbers",false,false,cl_));
        fields_.put("combobox",new StandardField("combobox","code.formathtml.classes.EnumNumbers",false,false,cl_));
        fields_.put("selectedStrings",new StandardField("selectedStrings","code.util.StringList",false,false,cl_));
        fields_.put("translations",new StandardField("translations",getCustMap(),false,false,cl_));
        fields_.put("tree",new StandardField("tree",getCustMap(),false,false,cl_));
        params_ = new StringList();
        method_ = new StandardMethod("go",params_,getAliasString(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("getDefaultChoices",params_,getCustList(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        getStandards().put("code.formathtml.classes.BeanFive", cl_);
    }
    private void buildBeanSix() {
        StringMap<StandardField> fields_;
        ObjectMap<MethodId, StandardMethod> methods_;
        CustList<StandardConstructor> constructors_;
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        StandardClass cl_;
        cl_ = new StandardClass("code.formathtml.classes.BeanSix", fields_, constructors_, methods_, getBean(), MethodModifier.FINAL);
        fields_.put("myEnumOne",new StandardField("myEnumOne","code.formathtml.classes.EnumNumber",false,false,cl_));
        fields_.put("myEnumThree",new StandardField("myEnumThree","code.formathtml.classes.EnumNumber",false,false,cl_));
        fields_.put("myEnumTwo",new StandardField("myEnumTwo","code.formathtml.classes.EnumNumber",false,false,cl_));
        getStandards().put("code.formathtml.classes.BeanSix", cl_);
    }
    private void buildBeanSeven() {
        StringMap<StandardField> fields_;
        ObjectMap<MethodId, StandardMethod> methods_;
        CustList<StandardConstructor> constructors_;
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        StandardMethod method_;
        StringList params_;
        StandardClass cl_;
        cl_ = new StandardClass("code.formathtml.classes.BeanSeven", fields_, constructors_, methods_, getBean(), MethodModifier.FINAL);
        fields_.put("arrayInt",new StandardField("arrayInt",PrimitiveTypeUtil.getPrettyArrayType(getAliasPrimInteger()),false,false,cl_));
        fields_.put("composite",new StandardField("composite","code.formathtml.classes.Composite",false,false,cl_));
        params_ = new StringList();
        method_ = new StandardMethod("getTree",params_,"code.formathtml.classes.NatTreeMapStringInteger", false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("getStrings",params_,"code.util.StringList", false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasDouble());
        method_ = new StandardMethod("getDouble",params_,getAliasPrimDouble(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger(),getAliasPrimInteger());
        method_ = new StandardMethod("goTwoArgs",params_,getAliasString(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("validateIntsSave",params_,getAliasVoid(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("validateMap",params_,getAliasVoid(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("validateStrings",params_,getAliasVoid(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("validateStringsSave",params_,getAliasVoid(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        getStandards().put("code.formathtml.classes.BeanSeven", cl_);
    }
    private void buildBeanEight() {
        StringMap<StandardField> fields_;
        ObjectMap<MethodId, StandardMethod> methods_;
        CustList<StandardConstructor> constructors_;
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        StandardMethod method_;
        StringList params_;
        StandardClass cl_;
        cl_ = new StandardClass("code.formathtml.classes.BeanEight", fields_, constructors_, methods_, getBean(), MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod("getDataBase",params_,"code.formathtml.classes.SimpleDataBase", false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("isCheckBox",params_,getAliasPrimBoolean(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("getComboNumber",params_,"code.formathtml.classes.EnumNumber", false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("getComboNumberTwo",params_,"code.formathtml.classes.EnumNumber", false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("getComboNumbers",params_,"code.formathtml.classes.EnumNumbers", false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("getCombobox",params_,getCustList(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("getComposites",params_,getCustList(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("getComboboxMap",params_,getCustMap(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("getTypedString",params_,getAliasString(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("getTypedText",params_,getAliasString(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("getRadioLong",params_,getAliasPrimLong(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("setup",params_,getAliasString(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimBoolean());
        method_ = new StandardMethod("setCheckBox",params_,getAliasVoid(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList("code.formathtml.classes.EnumNumber");
        method_ = new StandardMethod("setComboNumber",params_,getAliasVoid(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList("code.formathtml.classes.EnumNumber");
        method_ = new StandardMethod("setComboNumberTwo",params_,getAliasVoid(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList("code.formathtml.classes.EnumNumbers");
        method_ = new StandardMethod("setComboNumbers",params_,getAliasVoid(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimLong());
        method_ = new StandardMethod("setRadioLong",params_,getAliasVoid(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod("setTypedString",params_,getAliasVoid(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod("setTypedText",params_,getAliasVoid(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        getStandards().put("code.formathtml.classes.BeanEight", cl_);
    }
    private void buildComposite() {
        StringMap<StandardField> fields_;
        ObjectMap<MethodId, StandardMethod> methods_;
        CustList<StandardConstructor> constructors_;
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        StandardMethod method_;
        StringList params_;
        StandardConstructor ctor_;
        StandardClass cl_;
        cl_ = new StandardClass("code.formathtml.classes.Composite", fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        fields_.put("displayed",new StandardField("displayed",getAliasPrimBoolean(),false,false,cl_));
        fields_.put("integer",new StandardField("integer",getAliasPrimInteger(),false,false,cl_));
        fields_.put("map",new StandardField("map",getCustMap(),false,false,cl_));
        fields_.put("myChar",new StandardField("myChar",getAliasPrimChar(),false,false,cl_));
        fields_.put("string",new StandardField("string",getAliasString(),false,false,cl_));
        fields_.put("strings",new StandardField("strings","code.util.StringList",false,false,cl_));
        params_ = new StringList();
        method_ = new StandardMethod("internMethod",params_,getAliasString(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("privateMethod",params_,getAliasString(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("getString",params_,getAliasString(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("getStrings",params_,"code.util.StringList", false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod("summum",params_,getAliasPrimInteger(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod("getStringElt",params_,getAliasString(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod("setString",params_,getAliasVoid(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getValueChangedEvent());
        method_ = new StandardMethod("updateValue",params_,getAliasVoid(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        ctor_ = new StandardConstructor(params_,false,cl_);
        constructors_.add(ctor_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,cl_);
        constructors_.add(ctor_);
        cl_.getDirectInterfaces().add(getAliasDisplayable());
        getStandards().put("code.formathtml.classes.Composite", cl_);
    }
    private void buildBeanUtil() {
        StringMap<StandardField> fields_;
        ObjectMap<MethodId, StandardMethod> methods_;
        CustList<StandardConstructor> constructors_;
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        StandardMethod method_;
        StringList params_;
        StandardClass cl_;
        cl_ = new StandardClass("code.formathtml.classes.BeanUtil", fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        fields_.put("NB_BEANS",new StandardField("NB_BEANS",getAliasPrimBoolean(),true,true,cl_));
        params_ = new StringList(getAliasPrimInteger(),getAliasPrimInteger());
        method_ = new StandardMethod("sum",params_,getAliasPrimInteger(), false, MethodModifier.STATIC,cl_);
        methods_.put(method_.getId(), method_);
        getStandards().put("code.formathtml.classes.BeanUtil", cl_);
    }
    private void buildEnumNumber() {
        StringMap<StandardField> fields_;
        ObjectMap<MethodId, StandardMethod> methods_;
        CustList<StandardConstructor> constructors_;
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        StandardClass cl_;
        cl_ = new StandardClass("code.formathtml.classes.EnumNumber", fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        cl_.getDirectInterfaces().add(getAliasDisplayable());
        getStandards().put("code.formathtml.classes.EnumNumber", cl_);
    }
    private void buildEnumNumbers() {
        StringMap<StandardField> fields_;
        ObjectMap<MethodId, StandardMethod> methods_;
        CustList<StandardConstructor> constructors_;
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        StandardClass cl_;
        cl_ = new StandardClass("code.formathtml.classes.EnumNumbers", fields_, constructors_, methods_, getCustList(), MethodModifier.FINAL);
        cl_.getDirectInterfaces().add(getAliasDisplayable());
        cl_.setIterative("code.formathtml.classes.EnumNumber");
        getStandards().put("code.formathtml.classes.EnumNumbers", cl_);
    }
    private void buildGeneObjs() {
        StringMap<StandardField> fields_;
        ObjectMap<MethodId, StandardMethod> methods_;
        CustList<StandardConstructor> constructors_;
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        StandardMethod method_;
        StringList params_;
        StandardConstructor ctor_;
        StandardClass cl_;
        cl_ = new StandardClass("code.formathtml.classes.GeneObjs", fields_, constructors_, methods_, getCustList(), MethodModifier.FINAL);
        params_ = new StringList(getAliasObject());
        method_ = new StandardMethod("add",params_,getAliasVoid(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod("get",params_,getAliasObject(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,cl_);
        constructors_.add(ctor_);
        params_ = new StringList(getCustList());
        ctor_ = new StandardConstructor(params_,false,cl_);
        constructors_.add(ctor_);
        params_ = new StringList(PrimitiveTypeUtil.getPrettyArrayType(getAliasObject()));
        ctor_ = new StandardConstructor(params_,false,cl_);
        constructors_.add(ctor_);
        getStandards().put("code.formathtml.classes.GeneObjs", cl_);
    }
    private void buildGeneObjects() {
        StringMap<StandardField> fields_;
        ObjectMap<MethodId, StandardMethod> methods_;
        CustList<StandardConstructor> constructors_;
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        StandardMethod method_;
        StringList params_;
        StandardConstructor ctor_;
        StandardClass cl_;
        cl_ = new StandardClass("code.formathtml.classes.GeneObjects", fields_, constructors_, methods_, getCustList(), MethodModifier.FINAL);
        params_ = new StringList(getAliasObject());
        method_ = new StandardMethod("add",params_,getAliasVoid(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("last",params_,getAliasObject(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("clear",params_,getAliasObject(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,cl_);
        constructors_.add(ctor_);
        getStandards().put("code.formathtml.classes.GeneObjects", cl_);
    }
    private void buildPickableList() {
        StringMap<StandardField> fields_;
        ObjectMap<MethodId, StandardMethod> methods_;
        CustList<StandardConstructor> constructors_;
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        StandardMethod method_;
        StringList params_;
        StandardConstructor ctor_;
        StandardClass cl_;
        cl_ = new StandardClass("code.formathtml.classes.PickableList", fields_, constructors_, methods_, getCustList(), MethodModifier.FINAL);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod("removeAndExistAfter",params_,getAliasPrimBoolean(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("getList",params_,"code.formathtml.classes.GeneObjects", false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_,false,cl_);
        constructors_.add(ctor_);
        getStandards().put("code.formathtml.classes.PickableList", cl_);
    }
    private void buildEncapsFields() {
        StringMap<StandardField> fields_;
        ObjectMap<MethodId, StandardMethod> methods_;
        CustList<StandardConstructor> constructors_;
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        StandardMethod method_;
        StringList params_;
        StandardClass cl_;
        cl_ = new StandardClass("code.formathtml.classes.EncapsFields", fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod("isCheckBox",params_,getAliasPrimBoolean(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("getComboNumber",params_,"code.formathtml.classes.EnumNumber", false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("getComboNumberTwo",params_,"code.formathtml.classes.EnumNumber", false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("getComboNumbers",params_,"code.formathtml.classes.EnumNumbers", false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("getCombobox",params_,getCustList(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("getComposites",params_,getCustList(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("getComboboxMap",params_,getCustMap(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("getTypedString",params_,getAliasString(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("getTypedText",params_,getAliasString(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("getRadioLong",params_,getAliasPrimLong(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimBoolean());
        method_ = new StandardMethod("setCheckBox",params_,getAliasVoid(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList("code.formathtml.classes.EnumNumber");
        method_ = new StandardMethod("setComboNumber",params_,getAliasVoid(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList("code.formathtml.classes.EnumNumber");
        method_ = new StandardMethod("setComboNumberTwo",params_,getAliasVoid(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList("code.formathtml.classes.EnumNumbers");
        method_ = new StandardMethod("setComboNumbers",params_,getAliasVoid(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimLong());
        method_ = new StandardMethod("setRadioLong",params_,getAliasVoid(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod("setTypedString",params_,getAliasVoid(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod("setTypedText",params_,getAliasVoid(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        getStandards().put("code.formathtml.classes.EncapsFields", cl_);
    }
    private void buildRateEq() {
        StringMap<StandardField> fields_;
        ObjectMap<MethodId, StandardMethod> methods_;
        CustList<StandardConstructor> constructors_;
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        StandardMethod method_;
        StringList params_;
        StandardConstructor ctor_;
        StandardClass cl_;
        cl_ = new StandardClass("code.formathtml.classes.RateEq", fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList("code.formathtml.classes.RateEq");
        method_ = new StandardMethod("eq",params_,getAliasPrimBoolean(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        ctor_ = new StandardConstructor(params_,false,cl_);
        constructors_.add(ctor_);
        params_ = new StringList("code.formathtml.classes.RateEq");
        ctor_ = new StandardConstructor(params_,false,cl_);
        constructors_.add(ctor_);
        cl_.getDirectInterfaces().add(getAliasDisplayable());
        getStandards().put("code.formathtml.classes.RateEq", cl_);
    }
    private void buildNatTreeMapStringInteger() {
        StringMap<StandardField> fields_;
        ObjectMap<MethodId, StandardMethod> methods_;
        CustList<StandardConstructor> constructors_;
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        StandardClass cl_;
        cl_ = new StandardClass("code.formathtml.classes.NatTreeMapStringInteger", fields_, constructors_, methods_, getCustMap(), MethodModifier.FINAL);
        getStandards().put("code.formathtml.classes.NatTreeMapStringInteger", cl_);
    }
    private void buildSimpleDataBase() {
        StringMap<StandardField> fields_;
        ObjectMap<MethodId, StandardMethod> methods_;
        CustList<StandardConstructor> constructors_;
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        StandardMethod method_;
        StringList params_;
        StandardClass cl_;
        cl_ = new StandardClass("code.formathtml.classes.SimpleDataBase", fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod("getValue",params_,getAliasPrimInteger(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        getStandards().put("code.formathtml.classes.SimpleDataBase", cl_);
    }
    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        Object instance_ =  _instance.getInstance();
        String className_ = _classField.getClassName();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(className_,"code.formathtml.classes.BeanOne")) {
            BeanOne i_ = (BeanOne)instance_;
            if (StringList.quickEq(fieldName_,"chosenNumber")) {
                res_.setResult(new StdStruct(i_.getChosenNumber(),"code.formathtml.classes.EnumNumber"));
                return res_;
            }
            if (StringList.quickEq(fieldName_,"chosenNumbers")) {
                res_.setResult(new StdStruct(i_.getChosenNumbers(),"code.formathtml.classes.EnumNumbers"));
                return res_;
            }
            if (StringList.quickEq(fieldName_,"combobox")) {
                res_.setResult(new StdStruct(i_.getCombobox(),"code.formathtml.classes.EnumNumbers"));
                return res_;
            }
            if (StringList.quickEq(fieldName_,"commonClass")) {
                res_.setResult(new StringStruct(i_.getCommonClass()));
                return res_;
            }
            if (StringList.quickEq(fieldName_,"composite")) {
                res_.setResult(new StdStruct(i_.getComposite(),"code.formathtml.classes.Composite"));
                return res_;
            }
            if (StringList.quickEq(fieldName_,"map")) {
                res_.setResult(new StdStruct(i_.getMap(),"lse"));
                return res_;
            }
            if (StringList.quickEq(fieldName_,"message")) {
                res_.setResult(new StringStruct(i_.getMessage()));
                return res_;
            }
            if (StringList.quickEq(fieldName_,"numbers")) {
                res_.setResult(new StdStruct(i_.getNumbers(),"lse"));
                return res_;
            }
            if (StringList.quickEq(fieldName_,"selectedString")) {
                res_.setResult(new StringStruct(i_.getSelectedString()));
                return res_;
            }
            if (StringList.quickEq(fieldName_,"translations")) {
                res_.setResult(new StdStruct(i_.getTranslations(),"lse"));
                return res_;
            }
            if (StringList.quickEq(fieldName_,"tree")) {
                res_.setResult(new StdStruct(i_.getTree(),"lse"));
                return res_;
            }
        }
        if (StringList.quickEq(className_,"code.formathtml.classes.BeanTwo")) {
            BeanTwo i_ = (BeanTwo)instance_;
            if (StringList.quickEq(fieldName_,"checked")) {
                res_.setResult(new BooleanStruct(i_.isChecked()));
                return res_;
            }
            if (StringList.quickEq(fieldName_,"choose")) {
                res_.setResult(new StringStruct(i_.getChoose()));
                return res_;
            }
            if (StringList.quickEq(fieldName_,"chosenNumber")) {
                res_.setResult(new StdStruct(i_.getChosenNumber(),"code.formathtml.classes.EnumNumber"));
                return res_;
            }
            if (StringList.quickEq(fieldName_,"field")) {
                res_.setResult(new StringStruct(i_.getField()));
                return res_;
            }
            if (StringList.quickEq(fieldName_,"nullableCheckbox")) {
                if (i_.getNullableCheckbox() == null) {
                    res_.setResult(NullStruct.NULL_VALUE);
                } else {
                    res_.setResult(new BooleanStruct(i_.getNullableCheckbox()));
                }
                return res_;
            }
            if (StringList.quickEq(fieldName_,"nullableInt")) {
                if (i_.getNullableInt() == null) {
                    res_.setResult(NullStruct.NULL_VALUE);
                } else {
                    res_.setResult(new LongStruct(i_.getNullableInt()));
                }
                return res_;
            }
            if (StringList.quickEq(fieldName_,"rate")) {
                res_.setResult(new StdStruct(i_.getRate(),"code.formathtml.classes.Rate"));
                return res_;
            }
            if (StringList.quickEq(fieldName_,"typedInt")) {
                res_.setResult(new IntStruct(i_.getTypedInt()));
                return res_;
            }
            if (StringList.quickEq(fieldName_,"typedString")) {
                res_.setResult(new StringStruct(i_.getTypedString()));
                return res_;
            }
        }
        if (StringList.quickEq(className_,"code.formathtml.classes.BeanThree")) {
            BeanThree i_ = (BeanThree)instance_;
            if (StringList.quickEq(fieldName_,"index")) {
                res_.setResult(new IntStruct(i_.getIndex()));
                return res_;
            }
            if (StringList.quickEq(fieldName_,"indexTwo")) {
                res_.setResult(new IntStruct(i_.getIndexTwo()));
                return res_;
            }
            if (StringList.quickEq(fieldName_,"numbers")) {
                res_.setResult(new StdStruct(i_.getNumbers(),"ls"));
                return res_;
            }
            if (StringList.quickEq(fieldName_,"numbersTwo")) {
                res_.setResult(new StdStruct(i_.getNumbersTwo(),"ls"));
                return res_;
            }
        }
        if (StringList.quickEq(className_,"code.formathtml.classes.BeanFive")) {
            BeanFive i_ = (BeanFive)instance_;
            if (StringList.quickEq(fieldName_,"chosenNumbers")) {
                res_.setResult(new StdStruct(i_.getChosenNumbers(),"code.formathtml.classes.EnumNumbers"));
                return res_;
            }
            if (StringList.quickEq(fieldName_,"combobox")) {
                res_.setResult(new StdStruct(i_.getCombobox(),"code.formathtml.classes.EnumNumbers"));
                return res_;
            }
            if (StringList.quickEq(fieldName_,"selectedStrings")) {
                res_.setResult(new StdStruct(i_.getSelectedStrings(),"code.util.StringList"));
                return res_;
            }
            if (StringList.quickEq(fieldName_,"translations")) {
                res_.setResult(new StdStruct(i_.getTranslations(),"lse"));
                return res_;
            }
            if (StringList.quickEq(fieldName_,"tree")) {
                res_.setResult(new StdStruct(i_.getTree(),"lse"));
                return res_;
            }
        }
        if (StringList.quickEq(className_,"code.formathtml.classes.BeanSix")) {
            BeanSix i_ = (BeanSix)instance_;
            if (StringList.quickEq(fieldName_,"myEnumOne")) {
                res_.setResult(new StdStruct(i_.getMyEnumOne(),"code.formathtml.classes.EnumNumber"));
                return res_;
            }
            if (StringList.quickEq(fieldName_,"myEnumThree")) {
                res_.setResult(new StdStruct(i_.getMyEnumThree(),"code.formathtml.classes.EnumNumber"));
                return res_;
            }
            if (StringList.quickEq(fieldName_,"myEnumTwo")) {
                res_.setResult(new StdStruct(i_.getMyEnumTwo(),"code.formathtml.classes.EnumNumber"));
                return res_;
            }
        }
        if (StringList.quickEq(className_,"code.formathtml.classes.BeanSeven")) {
            BeanSeven i_ = (BeanSeven)instance_;
            if (StringList.quickEq(fieldName_,"arrayInt")) {
                res_.setResult(new StdStruct(i_.getArrayInt(),"[$int"));
                return res_;
            }
            if (StringList.quickEq(fieldName_,"composite")) {
                res_.setResult(new StdStruct(i_.getComposite(),"code.formathtml.classes.Composite"));
                return res_;
            }
        }
        if (StringList.quickEq(className_,"code.formathtml.classes.Composite")) {
            Composite i_ = (Composite)instance_;
            if (StringList.quickEq(fieldName_,"displayed")) {
                res_.setResult(new BooleanStruct(i_.isDisplayed()));
                return res_;
            }
            if (StringList.quickEq(fieldName_,"integer")) {
                res_.setResult(new IntStruct(i_.getInteger()));
                return res_;
            }
            if (StringList.quickEq(fieldName_,"map")) {
                res_.setResult(new StdStruct(i_.getMap(),"lse"));
                return res_;
            }
            if (StringList.quickEq(fieldName_,"myChar")) {
                res_.setResult(new CharStruct(i_.getMyChar()));
                return res_;
            }
            if (StringList.quickEq(fieldName_,"string")) {
                res_.setResult(new StringStruct(i_.getString()));
                return res_;
            }
            if (StringList.quickEq(fieldName_,"strings")) {
                res_.setResult(new StdStruct(i_.getStrings(),"code.util.StringList"));
                return res_;
            }
        }
        if (StringList.quickEq(className_,"code.formathtml.classes.BeanUtil")) {
            if (StringList.quickEq(fieldName_,"NB_BEANS")) {
                res_.setResult(new IntStruct(BeanUtil.NB_BEANS));
                return res_;
            }
        }
        return res_;
    }

    @Override
    public ResultErrorStd setOtherResult(ContextEl _cont, ClassField _classField, Struct _instance, Struct _value) {
        ResultErrorStd res_ = new ResultErrorStd();
        Object instance_ =  _instance.getInstance();
        Object value_ =  _value.getInstance();
        String className_ = _classField.getClassName();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(className_,"code.formathtml.classes.BeanOne")) {
            BeanOne i_ = (BeanOne)instance_;
            if (StringList.quickEq(fieldName_,"chosenNumber")) {
                i_.setChosenNumber((EnumNumber)value_);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(fieldName_,"chosenNumbers")) {
                i_.setChosenNumbers((EnumNumbers)value_);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(fieldName_,"combobox")) {
                i_.setCombobox((EnumNumbers)value_);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(fieldName_,"commonClass")) {
                i_.setCommonClass((String)value_);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(fieldName_,"composite")) {
                i_.setComposite((Composite)value_);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(fieldName_,"message")) {
                i_.setMessage((String)value_);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(fieldName_,"selectedString")) {
                i_.setSelectedString((String)value_);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
        }
        if (StringList.quickEq(className_,"code.formathtml.classes.BeanTwo")) {
            BeanTwo i_ = (BeanTwo)instance_;
            if (StringList.quickEq(fieldName_,"checked")) {
                i_.setChecked((Boolean)value_);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(fieldName_,"choose")) {
                i_.setChoose((String)value_);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(fieldName_,"chosenNumber")) {
                i_.setChosenNumber((EnumNumber)value_);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(fieldName_,"field")) {
                i_.setField((String)value_);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(fieldName_,"nullableCheckbox")) {
                i_.setNullableCheckbox((Boolean)value_);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(fieldName_,"nullableInt")) {
                i_.setNullableInt((Long)value_);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(fieldName_,"rate")) {
                i_.setRate((Rate)value_);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(fieldName_,"typedInt")) {
                i_.setTypedInt((Integer)value_);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(fieldName_,"typedString")) {
                i_.setTypedString((String)value_);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
        }
        if (StringList.quickEq(className_,"code.formathtml.classes.BeanThree")) {
            BeanThree i_ = (BeanThree)instance_;
            if (StringList.quickEq(fieldName_,"index")) {
                i_.setIndex((Integer)value_);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(fieldName_,"indexTwo")) {
                i_.setIndexTwo((Integer)value_);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
        }
        if (StringList.quickEq(className_,"code.formathtml.classes.BeanFive")) {
            BeanFive i_ = (BeanFive)instance_;
            if (StringList.quickEq(fieldName_,"chosenNumbers")) {
                i_.setChosenNumbers((EnumNumbers)value_);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(fieldName_,"combobox")) {
                i_.setCombobox((EnumNumbers)value_);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(fieldName_,"selectedStrings")) {
                i_.setSelectedStrings((StringList)value_);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
        }
        if (StringList.quickEq(className_,"code.formathtml.classes.BeanSix")) {
            BeanSix i_ = (BeanSix)instance_;
            if (StringList.quickEq(fieldName_,"myEnumOne")) {
                i_.setMyEnumOne((EnumNumber)value_);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(fieldName_,"myEnumThree")) {
                i_.setMyEnumThree((EnumNumber)value_);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(fieldName_,"myEnumTwo")) {
                i_.setMyEnumTwo((EnumNumber)value_);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
        }
        if (StringList.quickEq(className_,"code.formathtml.classes.BeanSeven")) {
            BeanSeven i_ = (BeanSeven)instance_;
            if (StringList.quickEq(fieldName_,"arrayInt")) {
                i_.setArrayInt((int[])value_);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(fieldName_,"composite")) {
                i_.setComposite((Composite)value_);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
        }
        if (StringList.quickEq(className_,"code.formathtml.classes.Composite")) {
            Composite i_ = (Composite)instance_;
            if (StringList.quickEq(fieldName_,"displayed")) {
                i_.setDisplayed((Boolean)value_);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(fieldName_,"integer")) {
                i_.setInteger((Integer)value_);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(fieldName_,"myChar")) {
                i_.setMyChar((Character)value_);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(fieldName_,"string")) {
                i_.setString((String)value_);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(fieldName_,"strings")) {
                i_.setStrings((StringList)value_);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
        }
        return res_;
    }

    @Override
    public StringList getDefaultValues(ContextEl _cont, String _className,
            String _value) {
        if (StringList.quickEq(_className, "code.formathtml.classes.EnumNumber")) {
            return StringList.splitChars(_value, ',');
        }
        if (StringList.quickEq(_className, "code.formathtml.classes.EnumNumbers")) {
            return new StringList(_value);
        }
        return new StringList();
    }
    @Override
    public void setOtherElement(Object _array, int _index, Struct _element) {
        ((Object[])_array)[_index] = _element.getInstance();
    }
    @Override
    public String getOtherBeanStructClassName(Object _struct, ContextEl _context) {
        if (_struct instanceof int[]) {
            return PrimitiveTypeUtil.getPrettyArrayType(getAliasPrimInteger());
        }
        if (_struct instanceof Integer[]) {
            return PrimitiveTypeUtil.getPrettyArrayType(getAliasInteger());
        }
        if (_struct instanceof int[][]) {
            return PrimitiveTypeUtil.getPrettyArrayType(getAliasPrimInteger(),2);
        }
        if (_struct instanceof Integer[][]) {
            return PrimitiveTypeUtil.getPrettyArrayType(getAliasInteger(),2);
        }
        if (_struct instanceof Object[][]) {
            return PrimitiveTypeUtil.getPrettyArrayType(getAliasObject(), 2);
        }
        if (_struct instanceof String[]) {
            return PrimitiveTypeUtil.getPrettyArrayType(getAliasString());
        }
        if (_struct instanceof Object[]) {
            return PrimitiveTypeUtil.getPrettyArrayType(getAliasObject());
        }
        if (_struct instanceof EnumNumber) {
            return "code.formathtml.classes.EnumNumber";
        }
        if (_struct instanceof EnumNumbers) {
            return "code.formathtml.classes.EnumNumbers";
        }
        if (_struct instanceof Composite) {
            return "code.formathtml.classes.Composite";
        }
        if (_struct instanceof EncapsFields) {
            return "code.formathtml.classes.EncapsFields";
        }
        if (_struct instanceof BeanOne) {
            return "code.formathtml.classes.BeanOne";
        }
        if (_struct instanceof BeanTwo) {
            return "code.formathtml.classes.BeanTwo";
        }
        if (_struct instanceof BeanThree) {
            return "code.formathtml.classes.BeanThree";
        }
        if (_struct instanceof BeanFour) {
            return "code.formathtml.classes.BeanFour";
        }
        if (_struct instanceof BeanFive) {
            return "code.formathtml.classes.BeanFive";
        }
        if (_struct instanceof BeanSix) {
            return "code.formathtml.classes.BeanSix";
        }
        if (_struct instanceof BeanSeven) {
            return "code.formathtml.classes.BeanSeven";
        }
        if (_struct instanceof BeanEight) {
            return "code.formathtml.classes.BeanEight";
        }
        if (_struct instanceof Rate) {
            return "code.formathtml.classes.Rate";
        }
        if (_struct instanceof RateEq) {
            return "code.formathtml.classes.RateEq";
        }
        if (_struct instanceof StringList) {
            return "code.util.StringList";
        }
        if (_struct instanceof Ints) {
            return "code.formathtml.classes.Ints";
        }
        if (_struct instanceof GeneObjects) {
            return "code.formathtml.classes.GeneObjects";
        }
        if (_struct instanceof GeneObjs) {
            return "code.formathtml.classes.GeneObjs";
        }
        if (_struct instanceof PickableList) {
            return "code.formathtml.classes.PickableList";
        }
        return getAliasObject();
    }
    @Override
    public ResultErrorStd getOtherStructToBeValidated(StringList _values,
            String _className, ContextEl _context) {
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(_className, "code.formathtml.classes.Rate")) {
            res_.setResult(new StdStruct(new Rate(_values.first()), _className));
            return res_;
        }
        if (StringList.quickEq(_className, "code.formathtml.classes.EnumNumber")) {
            EnumNumber en_ = EnumNumber.getByName(_values.first());
            if (en_ == null) {
                res_.setError(getAliasError());
            } else {
                res_.setResult(new StdStruct(en_, _className));
            }
            return res_;
        }
        if (StringList.quickEq(_className, "code.formathtml.classes.EnumNumbers")) {
            EnumNumbers list_ = new EnumNumbers();
            for (String s: _values) {
                list_.add(EnumNumber.getByName(s));
            }
            res_.setResult(new StdStruct(list_, _className));
            return res_;
        }
        return res_;
    }
    @Override
    public ResultErrorStd setOtherElementAtIndex(Struct _struct, int _index, boolean _key,
            Struct _element, ContextEl _context) {
        ResultErrorStd res_ = new ResultErrorStd();
        res_.setResult(NullStruct.NULL_VALUE);
        if (_struct.getInstance() instanceof StringList) {
            ((StringList)_struct.getInstance()).set(_index, (String) _element.getInstance());
            return res_;
        }
        if (_struct.getInstance() instanceof NatTreeMapStringInteger) {
            if (_key) {
                ((NatTreeMapStringInteger)_struct.getInstance()).setKey(_index, (String) _element.getInstance());
                ((NatTreeMapStringInteger)_struct.getInstance()).applyChanges();
                return res_;
            }
            ((NatTreeMapStringInteger)_struct.getInstance()).setValue(_index, (Integer) _element.getInstance());
            return res_;
        }
        return res_;
    }
}
