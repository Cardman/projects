package code.formathtml.sample;

import code.bean.nat.*;
import code.bean.nat.exec.opers.NatStdFctOperation;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.formathtml.Configuration;
import code.bean.nat.BeanStruct;
import code.bean.nat.StringMapObjectSample;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.blocks.RendDocumentBlock;
import code.formathtml.structs.BeanInfo;
import code.util.*;
import code.util.core.StringUtil;

public final class CustBeanLgNames extends BeanNatLgNames implements AbstractNatImpLgNames {

    private static final String TYPE_INTS = "code.formathtml.classes.Ints";
    private static final String GET_VALUE = "getValue";
    private static final String EQ = "eq";
    private static final String REMOVE_AND_EXIST_AFTER = "removeAndExistAfter";
    private static final String CLEAR = "clear";
    private static final String LAST = "last";
    private static final String ADD = "add";
    private static final String SUM = "sum";
    private static final String NB_BEANS = "NB_BEANS";
    private static final String UPDATE_VALUE = "updateValue";
    private static final String SET_STRING = "setString";
    private static final String GET_STRING_ELT = "getStringElt";
    private static final String SUMMUM = "summum";
    private static final String GET_STRING = "getString";
    private static final String PRIVATE_METHOD = "privateMethod";
    private static final String INTERN_METHOD = "internMethod";
    private static final String STRINGS = "strings";
    private static final String STRINGS_SEC = "strings2";
    private static final String STRING = "string";
    private static final String MY_CHAR = "myChar";
    private static final String INTEGER = "integer";
    private static final String DISPLAYED = "displayed";
    private static final String SET_TYPED_TEXT = "setTypedText";
    private static final String SET_RADIO_LONG = "setRadioLong";
    private static final String SET_COMBO_NUMBERS = "setComboNumbers";
    private static final String SET_COMBO_NUMBER_TWO = "setComboNumberTwo";
    private static final String SET_COMBO_NUMBER = "setComboNumber";
    private static final String SET_CHECK_BOX = "setCheckBox";
    private static final String SETUP = "setup";
    private static final String GET_RADIO_LONG = "getRadioLong";
    private static final String GET_TYPED_TEXT = "getTypedText";
    private static final String GET_TYPED_STRING = "getTypedString";
    private static final String GET_COMBOBOX_MAP = "getComboboxMap";
    private static final String GET_COMBOBOX = "getCombobox";
    private static final String GET_COMBO_NUMBERS = "getComboNumbers";
    private static final String GET_COMBO_NUMBER_TWO = "getComboNumberTwo";
    private static final String GET_COMBO_NUMBER = "getComboNumber";
    private static final String IS_CHECK_BOX = "isCheckBox";
    private static final String GET_DATA_BASE = "getDataBase";
    private static final String VALIDATE_STRINGS_SAVE = "validateStringsSave";
    private static final String VALIDATE_MAP = "validateMap";
    private static final String VALIDATE_INTS_SAVE = "validateIntsSave";
    private static final String GO_TWO_ARGS = "goTwoArgs";
    private static final String GET_STRINGS = "getStrings";
    private static final String GET_TREE = "getTree";
    private static final String ARRAY_INT = "arrayInt";
    private static final String MY_ENUM_TWO = "myEnumTwo";
    private static final String MY_ENUM_THREE = "myEnumThree";
    private static final String MY_ENUM_ONE = "myEnumOne";
    private static final String GET_DEFAULT_CHOICES = "getDefaultChoices";
    private static final String SELECTED_STRINGS = "selectedStrings";
    private static final String SETTER = "setter";
    private static final String SET_INVISIBLE_INT_FIELD = "setInvisibleIntField";
    private static final String SET_INVISIBLE_FIELD = "setInvisibleField";
    private static final String NUMBERS_TWO = "numbersTwo";
    private static final String INDEX_TWO = "indexTwo";
    private static final String INDEX = "index";
    private static final String VALIDATE = "validate";
    private static final String SET_TYPED_STRING = "setTypedString";
    private static final String SET_TYPED_INT = "setTypedInt";
    private static final String GO_TEXT_AREA = "goTextArea";
    private static final String GO = "go";
    private static final String GET_CHOSEN_NUMBERS = "getChosenNumbers";
    private static final String TYPED_STRING = "typedString";
    private static final String TYPED_INT = "typedInt";
    private static final String TYPED_SHORT = "typedShort";
    private static final String RATE = "rate";
    private static final String NULLABLE_INT = "nullableInt";
    private static final String NULLABLE_CHECKBOX = "nullableCheckbox";
    private static final String FIELD = "field";
    private static final String CHOOSE = "choose";
    private static final String CHECKED = "checked";
    private static final String VALIDATE_STRINGS = "validateStrings";
    private static final String GET_TRANS = "getTrans";
    private static final String GET_STANDARD = "getStandard";
    private static final String GET_SPAN_CLASSES = "getSpanClasses";
    private static final String GET_SPAN_CLASS = "getSpanClass";
    private static final String GET_COMPOSITES = "getComposites";
    private static final String GET_DEFAULT_CHOICE = "getDefaultChoice";
    private static final String GET_COMPOSITE = "getComposite";
    private static final String HAS_MORE_THAN_ONE = "hasMoreThanOne";
    private static final String INVOKE_METHOD = "invokeMethod";
    private static final String GO_TO_PAGE = "goToPage";
    private static final String GO_TO_NULL_PAGE = "goToNullPage";
    private static final String GET_DOUBLE = "getDouble";
    private static final String GET_LIST = "getList";
    private static final String TREE = "tree";
    private static final String TRANSLATIONS = "translations";
    private static final String SELECTED_STRING = "selectedString";
    private static final String NUMBERS = "numbers";
    private static final String MESSAGE = "message";
    private static final String MAP = "map";
    private static final String COMPOSITE = "composite";
    private static final String COMMON_CLASS = "commonClass";
    private static final String COMBOBOX = "combobox";
    private static final String CHOSEN_NUMBERS = "chosenNumbers";
    private static final String CHOSEN_NUMBERS_NULL = "chosenNumbersNull";
    private static final String CHOSEN_NUMBER = "chosenNumber";
    private static final String GET = "get";
    private static final String GET_REVERSE = "getReverse";
    private static final String TYPE_SIMPLE_DATA_BASE = "code.formathtml.classes.SimpleDataBase";
    private static final String TYPE_NAT_TREE_MAP_STRING_INTEGER = "code.formathtml.classes.NatTreeMapStringInteger";
    private static final String TYPE_RATE_EQ = "code.formathtml.classes.RateEq";
    private static final String TYPE_ENCAPS_FIELDS = "code.formathtml.classes.EncapsFields";
    private static final String TYPE_PICKABLE_LIST = "code.formathtml.classes.PickableList";
    private static final String TYPE_GENE_OBJECTS = "code.formathtml.classes.GeneObjects";
    private static final String TYPE_GENE_OBJS = "code.formathtml.classes.GeneObjs";
    private static final String TYPE_ENUM_NUMBERS = "code.formathtml.classes.EnumNumbers";
    private static final String TYPE_ENUM_NUMBER = "code.formathtml.classes.EnumNumber";
    private static final String TYPE_BEAN_UTIL = "code.formathtml.classes.BeanUtil";
    private static final String TYPE_COMPOSITE = "code.formathtml.classes.Composite";
    private static final String TYPE_BEAN_EIGHT = "code.formathtml.classes.BeanEight";
    private static final String TYPE_BEAN_SEVEN = "code.formathtml.classes.BeanSeven";
    private static final String TYPE_BEAN_SIX = "code.formathtml.classes.BeanSix";
    private static final String TYPE_BEAN_FIVE = "code.formathtml.classes.BeanFive";
    private static final String TYPE_BEAN_FOUR = "code.formathtml.classes.BeanFour";
    private static final String TYPE_BEAN_THREE = "code.formathtml.classes.BeanThree";
    private static final String TYPE_BEAN_TWO = "code.formathtml.classes.BeanTwo";
    private static final String TYPE_BEAN_ONE = "code.formathtml.classes.BeanOne";
    private static final String TYPE_UNSELECTED_RADIO = "code.formathtml.classes.UnselectedRadio";
    private static final String TYPE_MY_VALIDATOR_ENUMS = "code.formathtml.classes.MyValidatorEnums";
    private static final String TYPE_MY_VALIDATOR_ENUM = "code.formathtml.classes.MyValidatorEnum";
    private static final String TYPE_MY_VALIDATOR = "code.formathtml.classes.MyValidator";
    private static final String TYPE_STRING_LIST = "code.util.StringList";
    private static final String TYPE_STRING_LIST_SEC = "code.util.StringList2";
    private static final String ALIAS_LS = "ls";
    private static final String ALIAS_LSE = "lse";

    public CustBeanLgNames() {
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        CustList<StandardConstructor> constructors_;
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        SpecialNatClass cl_;
        cl_ = new SpecialNatClass(TYPE_INTS, fields_, methods_, TYPE_LIST);
        cl_.getDirectInterfaces().add(TYPE_COUNTABLE);
        getStds().addEntry(TYPE_INTS, cl_);
        getIterables().put(TYPE_INTS, getContent().getNbAlias().getAliasInteger());
        DefaultInitialization.basicStandards(this);
    }
    @Override
    public void buildOther() {
        buildBeans();
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        CustList<StandardConstructor> constructors_;
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        SpecNatMethod method_;
        StringList params_;
        SpecialNatClass cl_;
        cl_ = new SpecialNatClass(TYPE_STRING_LIST, fields_, methods_, TYPE_LIST);
        cl_.getDirectInterfaces().add(TYPE_COUNTABLE);
        cl_.getDirectInterfaces().add(TYPE_DISPLAYABLE);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_REVERSE,params_,TYPE_STRING_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(getContent().getPrimTypes().getAliasPrimInteger());
        method_ = new SpecNatMethod(GET,params_, getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        getStds().addEntry(TYPE_STRING_LIST, cl_);
        getIterables().put(TYPE_STRING_LIST, getAliasString());
        cl_ = new SpecialNatClass(TYPE_STRING_LIST_SEC, fields_, methods_, TYPE_LIST);
        cl_.getDirectInterfaces().add(TYPE_COUNTABLE);
        cl_.getDirectInterfaces().add(TYPE_DISPLAYABLE);
        params_ = new StringList(getContent().getPrimTypes().getAliasPrimInteger());
        method_ = new SpecNatMethod(GET,params_, getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        getStds().addEntry(TYPE_STRING_LIST_SEC, cl_);
        getIterables().put(TYPE_STRING_LIST_SEC, getAliasObject());
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
        buildRate();
        buildRateEq();
        buildSimpleDataBase();
        buildNatTreeMapStringInteger();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        cl_ = new SpecialNatClass(TYPE_MY_VALIDATOR, fields_, methods_, TYPE_VALIDATOR);
        getStds().addEntry(TYPE_MY_VALIDATOR, cl_);
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        cl_ = new SpecialNatClass(TYPE_MY_VALIDATOR_ENUM, fields_, methods_, TYPE_VALIDATOR);
        getStds().addEntry(TYPE_MY_VALIDATOR_ENUM, cl_);
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        cl_ = new SpecialNatClass(TYPE_MY_VALIDATOR_ENUMS, fields_, methods_, TYPE_VALIDATOR);
        getStds().addEntry(TYPE_MY_VALIDATOR_ENUMS, cl_);
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        cl_ = new SpecialNatClass(TYPE_UNSELECTED_RADIO, fields_, methods_, TYPE_VALIDATOR);
        getStds().addEntry(TYPE_UNSELECTED_RADIO, cl_);
    }
    private void buildBeanOne() {
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        CustList<StandardConstructor> constructors_;
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        SpecNatMethod method_;
        StringList params_;
        SpecialNatClass cl_;
        cl_ = new SpecialNatClass(TYPE_BEAN_ONE, fields_, methods_, TYPE_BEAN);
        fields_.add(new StandardField(CHOSEN_NUMBER, getAliasString(),false,false));
        fields_.add(new StandardField(CHOSEN_NUMBERS,TYPE_ENUM_NUMBERS,false,false));
        fields_.add(new StandardField(COMBOBOX,TYPE_ENUM_NUMBERS,false,false));
        fields_.add(new StandardField(COMMON_CLASS, getAliasString(),false,false));
        fields_.add(new StandardField(COMPOSITE,TYPE_COMPOSITE,false,false));
        fields_.add(new StandardField(MAP, TYPE_MAP,false,false));
        fields_.add(new StandardField(MESSAGE, getAliasString(),false,false));
        fields_.add(new StandardField(NUMBERS, TYPE_MAP,false,false));
        fields_.add(new StandardField(SELECTED_STRING, getAliasString(),false,false));
        fields_.add(new StandardField(TRANSLATIONS, TYPE_MAP,false,false));
        fields_.add(new StandardField(TREE, TYPE_MAP,false,false));
        fields_.add(new StandardField(STRINGS,TYPE_STRING_LIST,false,false));
        fields_.add(new StandardField(STRINGS_SEC,TYPE_STRING_LIST_SEC,false,false));
        params_ = new StringList(getContent().getNbAlias().getAliasInteger());
        method_ = new SpecNatMethod(GET_LIST,params_, TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(getContent().getNbAlias().getAliasLong());
        method_ = new SpecNatMethod(GET_DOUBLE,params_, getContent().getPrimTypes().getAliasPrimInteger(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GO_TO_NULL_PAGE,params_, getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(getAliasObject());
        method_ = new SpecNatMethod("length",params_, getContent().getPrimTypes().getAliasPrimInteger(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GO_TO_PAGE,params_, getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(getContent().getNbAlias().getAliasLong());
        method_ = new SpecNatMethod(GO_TO_PAGE,params_, getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(getContent().getPrimTypes().getAliasPrimInteger());
        method_ = new SpecNatMethod(INVOKE_METHOD,params_, getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(HAS_MORE_THAN_ONE,params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_COMPOSITE,params_,TYPE_COMPOSITE, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_DEFAULT_CHOICE,params_,TYPE_ENUM_NUMBER, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_COMPOSITES,params_, TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(getContent().getPrimTypes().getAliasPrimLong());
        method_ = new SpecNatMethod(GET_SPAN_CLASS,params_, getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(getContent().getPrimTypes().getAliasPrimLong(), getContent().getPrimTypes().getAliasPrimLong(), getContent().getPrimTypes().getAliasPrimLong());
        method_ = new SpecNatMethod(GET_SPAN_CLASSES,params_, getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(getAliasString());
        method_ = new SpecNatMethod(GET_STANDARD,params_, getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(getContent().getNbAlias().getAliasLong());
        method_ = new SpecNatMethod(GET_TRANS,params_, getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(VALIDATE_STRINGS,params_, getContent().getCoreNames().getAliasVoid(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        getStds().addEntry(TYPE_BEAN_ONE, cl_);
    }
    private void buildBeanTwo() {
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        CustList<StandardConstructor> constructors_;
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        SpecNatMethod method_;
        StringList params_;
        SpecialNatClass cl_;
        cl_ = new SpecialNatClass(TYPE_BEAN_TWO, fields_, methods_, TYPE_BEAN);
        fields_.add(new StandardField(COMPOSITE,TYPE_COMPOSITE,false,false));
        fields_.add(new StandardField(CHECKED, getAliasPrimBoolean(),false,false));
        fields_.add(new StandardField(CHOOSE, getAliasString(),false,false));
        fields_.add(new StandardField(CHOSEN_NUMBER,TYPE_ENUM_NUMBER,false,false));
        fields_.add(new StandardField(FIELD, getAliasString(),false,false));
        fields_.add(new StandardField(NULLABLE_CHECKBOX, getContent().getNbAlias().getAliasBoolean(),false,false));
        fields_.add(new StandardField(NULLABLE_INT, TYPE_RATE,false,false, new NaNuIntGet(), new NaNuIntSet()));
        fields_.add(new StandardField(RATE,TYPE_RATE,false,false));
        fields_.add(new StandardField(TYPED_INT, getContent().getPrimTypes().getAliasPrimInteger(),false,false));
        fields_.add(new StandardField(TYPED_SHORT, getContent().getPrimTypes().getAliasPrimShort(),false,false));
        fields_.add(new StandardField(TYPED_STRING, getAliasString(),false,false));
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_CHOSEN_NUMBERS,params_, TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GO,params_, getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(getContent().getNbAlias().getAliasLong());
        method_ = new SpecNatMethod(GO,params_, getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GO_TEXT_AREA,params_, getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(getContent().getPrimTypes().getAliasPrimInteger());
        method_ = new SpecNatMethod(SET_TYPED_INT,params_, getContent().getCoreNames().getAliasVoid(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(getAliasString());
        method_ = new SpecNatMethod(SET_TYPED_STRING,params_, getContent().getCoreNames().getAliasVoid(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(VALIDATE,params_, getContent().getCoreNames().getAliasVoid(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        getStds().addEntry(TYPE_BEAN_TWO, cl_);
    }
    private void buildBeanThree() {
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        CustList<StandardConstructor> constructors_;
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        SpecialNatClass cl_;
        cl_ = new SpecialNatClass(TYPE_BEAN_THREE, fields_, methods_, TYPE_BEAN);
        fields_.add(new StandardField(INDEX, getContent().getPrimTypes().getAliasPrimInteger(),false,false));
        fields_.add(new StandardField(INDEX_TWO, getContent().getPrimTypes().getAliasPrimInteger(),false,false));
        fields_.add(new StandardField(NUMBERS, TYPE_LIST,false,false));
        fields_.add(new StandardField(NUMBERS_TWO, TYPE_LIST,false,false));
        getStds().addEntry(TYPE_BEAN_THREE, cl_);
    }
    private void buildBeanFour() {
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        CustList<StandardConstructor> constructors_;
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        SpecNatMethod method_;
        StringList params_;
        SpecialNatClass cl_;
        cl_ = new SpecialNatClass(TYPE_BEAN_FOUR, fields_, methods_, TYPE_BEAN);
        params_ = new StringList(getContent().getNbAlias().getAliasInteger());
        method_ = new SpecNatMethod(SET_INVISIBLE_FIELD,params_, getContent().getCoreNames().getAliasVoid(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(getContent().getPrimTypes().getAliasPrimInteger());
        method_ = new SpecNatMethod(SET_INVISIBLE_INT_FIELD,params_, getContent().getCoreNames().getAliasVoid(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(getAliasString());
        method_ = new SpecNatMethod(SETTER,params_, getContent().getCoreNames().getAliasVoid(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        getStds().addEntry(TYPE_BEAN_FOUR, cl_);
    }
    private void buildBeanFive() {
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        CustList<StandardConstructor> constructors_;
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        SpecNatMethod method_;
        StringList params_;
        SpecialNatClass cl_;
        cl_ = new SpecialNatClass(TYPE_BEAN_FIVE, fields_, methods_, TYPE_BEAN);
        fields_.add(new StandardField(CHOSEN_NUMBERS,TYPE_ENUM_NUMBERS,false,false));
        fields_.add(new StandardField(CHOSEN_NUMBERS_NULL, TYPE_LIST,false,false));
        fields_.add(new StandardField(COMBOBOX,TYPE_ENUM_NUMBERS,false,false));
        fields_.add(new StandardField(SELECTED_STRINGS,TYPE_STRING_LIST,false,false));
        fields_.add(new StandardField(TRANSLATIONS, TYPE_MAP,false,false));
        fields_.add(new StandardField(TREE, TYPE_MAP,false,false));
        params_ = new StringList();
        method_ = new SpecNatMethod(GO,params_, getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_DEFAULT_CHOICES,params_, TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        getStds().addEntry(TYPE_BEAN_FIVE, cl_);
    }
    private void buildBeanSix() {
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        CustList<StandardConstructor> constructors_;
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        SpecialNatClass cl_;
        cl_ = new SpecialNatClass(TYPE_BEAN_SIX, fields_, methods_, TYPE_BEAN);
        fields_.add(new StandardField(MY_ENUM_ONE,TYPE_ENUM_NUMBER,false,false));
        fields_.add(new StandardField(MY_ENUM_THREE,TYPE_ENUM_NUMBER,false,false));
        fields_.add(new StandardField(MY_ENUM_TWO,TYPE_ENUM_NUMBER,false,false));
        getStds().addEntry(TYPE_BEAN_SIX, cl_);
    }
    private void buildBeanSeven() {
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        CustList<StandardConstructor> constructors_;
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        SpecNatMethod method_;
        StringList params_;
        SpecialNatClass cl_;
        cl_ = new SpecialNatClass(TYPE_BEAN_SEVEN, fields_, methods_, TYPE_BEAN);
        fields_.add(new StandardField(ARRAY_INT,TYPE_INTS,false,true));
        fields_.add(new StandardField(COMPOSITE,TYPE_COMPOSITE,false,false));
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_TREE,params_,TYPE_NAT_TREE_MAP_STRING_INTEGER, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_STRINGS,params_,TYPE_STRING_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(getContent().getNbAlias().getAliasDouble());
        method_ = new SpecNatMethod(GET_DOUBLE,params_, getContent().getPrimTypes().getAliasPrimDouble(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(getContent().getPrimTypes().getAliasPrimInteger(), getContent().getPrimTypes().getAliasPrimInteger());
        method_ = new SpecNatMethod(GO_TWO_ARGS,params_, getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(VALIDATE_INTS_SAVE,params_, getContent().getCoreNames().getAliasVoid(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(VALIDATE_MAP,params_, getContent().getCoreNames().getAliasVoid(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(VALIDATE_STRINGS,params_, getContent().getCoreNames().getAliasVoid(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(VALIDATE_STRINGS_SAVE,params_, getContent().getCoreNames().getAliasVoid(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        getStds().addEntry(TYPE_BEAN_SEVEN, cl_);
    }
    private void buildBeanEight() {
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        CustList<StandardConstructor> constructors_;
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        SpecNatMethod method_;
        StringList params_;
        SpecialNatClass cl_;
        cl_ = new SpecialNatClass(TYPE_BEAN_EIGHT, fields_, methods_, TYPE_BEAN);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_DATA_BASE,params_,TYPE_SIMPLE_DATA_BASE, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(IS_CHECK_BOX,params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_COMBO_NUMBER,params_,TYPE_ENUM_NUMBER, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_COMBO_NUMBER_TWO,params_,TYPE_ENUM_NUMBER, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_COMBO_NUMBERS,params_,TYPE_ENUM_NUMBERS, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_COMBOBOX,params_, TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_COMPOSITES,params_, TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_COMBOBOX_MAP,params_, TYPE_MAP, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_TYPED_STRING,params_, getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_TYPED_TEXT,params_, getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_RADIO_LONG,params_, getContent().getPrimTypes().getAliasPrimLong(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(SETUP,params_, getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(getAliasPrimBoolean());
        method_ = new SpecNatMethod(SET_CHECK_BOX,params_, getContent().getCoreNames().getAliasVoid(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(TYPE_ENUM_NUMBER);
        method_ = new SpecNatMethod(SET_COMBO_NUMBER,params_, getContent().getCoreNames().getAliasVoid(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(TYPE_ENUM_NUMBER);
        method_ = new SpecNatMethod(SET_COMBO_NUMBER_TWO,params_, getContent().getCoreNames().getAliasVoid(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(TYPE_ENUM_NUMBERS);
        method_ = new SpecNatMethod(SET_COMBO_NUMBERS,params_, getContent().getCoreNames().getAliasVoid(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(getContent().getPrimTypes().getAliasPrimLong());
        method_ = new SpecNatMethod(SET_RADIO_LONG,params_, getContent().getCoreNames().getAliasVoid(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(getAliasString());
        method_ = new SpecNatMethod(SET_TYPED_STRING,params_, getContent().getCoreNames().getAliasVoid(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(getAliasString());
        method_ = new SpecNatMethod(SET_TYPED_TEXT,params_, getContent().getCoreNames().getAliasVoid(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        getStds().addEntry(TYPE_BEAN_EIGHT, cl_);
    }
    private void buildComposite() {
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        SpecNatMethod method_;
        StringList params_;
        StandardConstructor ctor_;
        SpecialNatClass cl_;
        cl_ = new SpecialNatClass(TYPE_COMPOSITE, fields_, methods_, getAliasObject());
        fields_.add(new StandardField(DISPLAYED, getAliasPrimBoolean(),false,false));
        fields_.add(new StandardField(INTEGER, getContent().getPrimTypes().getAliasPrimInteger(),false,false));
        fields_.add(new StandardField(MAP, TYPE_MAP,false,false));
        fields_.add(new StandardField(MY_CHAR, getContent().getPrimTypes().getAliasPrimChar(),false,false));
        fields_.add(new StandardField(STRING, getAliasString(),false,false));
        fields_.add(new StandardField(STRINGS,TYPE_STRING_LIST,false,false));
        fields_.add(new StandardField(STRINGS_SEC,TYPE_STRING_LIST_SEC,false,false));
        params_ = new StringList();
        method_ = new SpecNatMethod(INTERN_METHOD,params_, getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(PRIVATE_METHOD,params_, getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_STRING,params_, getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_STRINGS,params_,TYPE_STRING_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(getContent().getPrimTypes().getAliasPrimInteger());
        method_ = new SpecNatMethod(SUMMUM,params_, getContent().getPrimTypes().getAliasPrimInteger(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(getContent().getPrimTypes().getAliasPrimInteger());
        method_ = new SpecNatMethod(GET_STRING_ELT,params_, getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod("sum",params_, getContent().getPrimTypes().getAliasPrimInteger(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(getAliasString());
        method_ = new SpecNatMethod(SET_STRING,params_, getContent().getCoreNames().getAliasVoid(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList("code.formathtml.util.ValueChangeEvent");
        method_ = new SpecNatMethod(UPDATE_VALUE,params_, getContent().getCoreNames().getAliasVoid(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        cl_.getDirectInterfaces().add(TYPE_DISPLAYABLE);
        getStds().addEntry(TYPE_COMPOSITE, cl_);
    }
    private void buildBeanUtil() {
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        CustList<StandardConstructor> constructors_;
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        SpecNatMethod method_;
        StringList params_;
        SpecialNatClass cl_;
        cl_ = new SpecialNatClass(TYPE_BEAN_UTIL, fields_, methods_, getAliasObject());
        fields_.add(new StandardField(NB_BEANS, getAliasPrimBoolean(),true,true));
        params_ = new StringList(getContent().getPrimTypes().getAliasPrimInteger(), getContent().getPrimTypes().getAliasPrimInteger());
        method_ = new SpecNatMethod(SUM,params_, getContent().getPrimTypes().getAliasPrimInteger(), false, MethodModifier.STATIC);
        methods_.add( method_);
        getStds().addEntry(TYPE_BEAN_UTIL, cl_);
    }
    private void buildEnumNumber() {
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        CustList<StandardConstructor> constructors_;
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        SpecialNatClass cl_;
        cl_ = new SpecialNatClass(TYPE_ENUM_NUMBER, fields_, methods_, getAliasObject());
        cl_.getDirectInterfaces().add(TYPE_DISPLAYABLE);
        getStds().addEntry(TYPE_ENUM_NUMBER, cl_);
    }
    private void buildEnumNumbers() {
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        CustList<StandardConstructor> constructors_;
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        SpecialNatClass cl_;
        cl_ = new SpecialNatClass(TYPE_ENUM_NUMBERS, fields_, methods_, TYPE_LIST);
        cl_.getDirectInterfaces().add(TYPE_DISPLAYABLE);
        getStds().addEntry(TYPE_ENUM_NUMBERS, cl_);
        getIterables().put(TYPE_ENUM_NUMBERS,TYPE_ENUM_NUMBER);
    }
    private void buildGeneObjs() {
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        SpecNatMethod method_;
        StringList params_;
        SpecialNatClass cl_;
        cl_ = new SpecialNatClass(TYPE_GENE_OBJS, fields_, methods_, TYPE_LIST);
        params_ = new StringList(getAliasObject());
        method_ = new SpecNatMethod(ADD,params_, getContent().getCoreNames().getAliasVoid(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(getContent().getPrimTypes().getAliasPrimInteger());
        method_ = new SpecNatMethod(GET,params_, getAliasObject(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        getStds().addEntry(TYPE_GENE_OBJS, cl_);
    }
    private void buildGeneObjects() {
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        SpecNatMethod method_;
        StringList params_;
        StandardConstructor ctor_;
        SpecialNatClass cl_;
        cl_ = new SpecialNatClass(TYPE_GENE_OBJECTS, fields_, methods_, TYPE_LIST);
        params_ = new StringList(getAliasObject());
        method_ = new SpecNatMethod(ADD,params_, getContent().getCoreNames().getAliasVoid(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(LAST,params_, getAliasObject(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(CLEAR,params_, getContent().getCoreNames().getAliasVoid(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        getStds().addEntry(TYPE_GENE_OBJECTS, cl_);
    }
    private void buildPickableList() {
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        SpecNatMethod method_;
        StringList params_;
        StandardConstructor ctor_;
        SpecialNatClass cl_;
        cl_ = new SpecialNatClass(TYPE_PICKABLE_LIST, fields_, methods_, TYPE_LIST);
        params_ = new StringList(getContent().getPrimTypes().getAliasPrimInteger());
        method_ = new SpecNatMethod(REMOVE_AND_EXIST_AFTER,params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_LIST,params_,TYPE_GENE_OBJECTS, false, MethodModifier.NORMAL);
        methods_.add( method_);
        getStds().addEntry(TYPE_PICKABLE_LIST, cl_);
    }
    private void buildEncapsFields() {
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        CustList<StandardConstructor> constructors_;
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        SpecNatMethod method_;
        StringList params_;
        SpecialNatClass cl_;
        cl_ = new SpecialNatClass(TYPE_ENCAPS_FIELDS, fields_, methods_, getAliasObject());
        params_ = new StringList();
        method_ = new SpecNatMethod(IS_CHECK_BOX,params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_COMBO_NUMBER,params_,TYPE_ENUM_NUMBER, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_COMBO_NUMBER_TWO,params_,TYPE_ENUM_NUMBER, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_COMBO_NUMBERS,params_,TYPE_ENUM_NUMBERS, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_COMBOBOX,params_, TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_COMBOBOX_MAP,params_, TYPE_MAP, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_TYPED_STRING,params_, getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_TYPED_TEXT,params_, getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_RADIO_LONG,params_, getContent().getPrimTypes().getAliasPrimLong(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(getAliasPrimBoolean());
        method_ = new SpecNatMethod(SET_CHECK_BOX,params_, getContent().getCoreNames().getAliasVoid(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(TYPE_ENUM_NUMBER);
        method_ = new SpecNatMethod(SET_COMBO_NUMBER,params_, getContent().getCoreNames().getAliasVoid(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(TYPE_ENUM_NUMBER);
        method_ = new SpecNatMethod(SET_COMBO_NUMBER_TWO,params_, getContent().getCoreNames().getAliasVoid(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(TYPE_ENUM_NUMBERS);
        method_ = new SpecNatMethod(SET_COMBO_NUMBERS,params_, getContent().getCoreNames().getAliasVoid(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(getContent().getPrimTypes().getAliasPrimLong());
        method_ = new SpecNatMethod(SET_RADIO_LONG,params_, getContent().getCoreNames().getAliasVoid(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(getAliasString());
        method_ = new SpecNatMethod(SET_TYPED_STRING,params_, getContent().getCoreNames().getAliasVoid(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(getAliasString());
        method_ = new SpecNatMethod(SET_TYPED_TEXT,params_, getContent().getCoreNames().getAliasVoid(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        getStds().addEntry(TYPE_ENCAPS_FIELDS, cl_);
    }
    private void buildRate() {
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        CustList<StandardConstructor> constructors_;
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        SpecialNatClass cl_;
        cl_ = new SpecialNatClass(TYPE_RATE, fields_, methods_, getAliasObject());
        cl_.getDirectInterfaces().add(TYPE_DISPLAYABLE);
        getStds().addEntry(TYPE_RATE, cl_);
    }
    private void buildRateEq() {
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        SpecNatMethod method_;
        StringList params_;
        StandardConstructor ctor_;
        SpecialNatClass cl_;
        cl_ = new SpecialNatClass(TYPE_RATE_EQ, fields_, methods_, getAliasObject());
        params_ = new StringList(TYPE_RATE_EQ);
        method_ = new SpecNatMethod(EQ,params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        cl_.getDirectInterfaces().add(TYPE_DISPLAYABLE);
        getStds().addEntry(TYPE_RATE_EQ, cl_);
    }
    private void buildNatTreeMapStringInteger() {
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        CustList<StandardConstructor> constructors_;
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        SpecialNatClass cl_;
        cl_ = new SpecialNatClass(TYPE_NAT_TREE_MAP_STRING_INTEGER, fields_, methods_, TYPE_MAP);
        getStds().addEntry(TYPE_NAT_TREE_MAP_STRING_INTEGER, cl_);
    }
    private void buildSimpleDataBase() {
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        CustList<StandardConstructor> constructors_;
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        SpecNatMethod method_;
        StringList params_;
        SpecialNatClass cl_;
        cl_ = new SpecialNatClass(TYPE_SIMPLE_DATA_BASE, fields_, methods_, getAliasObject());
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_VALUE,params_, getContent().getPrimTypes().getAliasPrimInteger(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        getStds().addEntry(TYPE_SIMPLE_DATA_BASE, cl_);
    }
    @Override
    public void beforeDisplaying(Struct _arg) {
        ((BeanStruct)_arg).beforeDisplaying();
    }
    public String processAfterInvoke(Configuration _conf, String _dest, String _beanName, Struct _bean, String _currentUrl, String _language, ContextEl _ctx, RendStackCall _rendStack) {
        ImportingPage ip_ = new ImportingPage();
        _rendStack.addPage(ip_);
        StringMapObjectSample forms_ = new StringMapObjectSample();
        if (_bean instanceof BeanStruct) {
            forms_ = ((BeanStruct)_bean).getForms();
        }
        String currentBeanName_;
        RendDocumentBlock rendDocumentBlock_ = _conf.getRenders().getVal(_dest);
        currentBeanName_ = rendDocumentBlock_.getBeanName();
        Struct bean_ = getBeanOrNull(_conf,currentBeanName_);
        if (bean_ instanceof BeanStruct) {
            ((BeanStruct) bean_).setForms(forms_);
        }
        _rendStack.clearPages();
        return BeanNatCommonLgNames.getRes(rendDocumentBlock_,_conf, this, _ctx, _rendStack);
    }

    @Override
    public void setBeanForms(Configuration _conf, Struct _mainBean, String _beanName) {
        Struct bean_ = _conf.getBuiltBeans().getVal(_beanName);
        StringMapObjectSample forms_ = ((BeanStruct)bean_).getForms();
        StringMapObjectSample formsMap_ = ((BeanStruct)_mainBean).getForms();
        forms_.putAllMap(formsMap_);
    }

    @Override
    public ResultErrorStd getOtherResult(ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        String className_ = _classField.getClassName();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(className_,TYPE_COMPOSITE)) {
            CompositeStruct i_ = ((CompositeStruct)_instance);
            if (StringUtil.quickEq(fieldName_,INTEGER)) {
                res_.setResult(new IntStruct(i_.getInteger()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_,STRING)) {
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(fieldName_,STRINGS)|| StringUtil.quickEq(fieldName_,STRINGS_SEC)) {
                StringList ls_ = i_.getStrings();
                res_.setResult(getStringArray(ls_));
                return res_;
            }
        }
        if (StringUtil.quickEq(className_,TYPE_BEAN_ONE)) {
            if (StringUtil.quickEq(fieldName_,MAP)) {
                res_.setResult(getTree(((BeanStruct)_instance).getMap()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_,MESSAGE)) {
                res_.setResult(new StringStruct("Test {0}"));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_,SELECTED_STRING)) {
                res_.setResult(new StringStruct("ONE"));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_,TREE)) {
                if (((BeanStruct)_instance).getTree() == null) {
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                res_.setResult(getTree(((BeanStruct)_instance).getTree()));
                return res_;
            }
        }
        if (StringUtil.quickEq(className_,TYPE_BEAN_TWO)) {
            if (StringUtil.quickEq(fieldName_,CHECKED)) {
                res_.setResult(BooleanStruct.of(((BeanStruct)_instance).isChecked()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_,TYPED_SHORT)) {
                res_.setResult(new ShortStruct(((BeanStruct)_instance).getTypedShort()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_,TYPED_STRING)) {
                if ( ((BeanStruct)_instance).getTypedString() == null) {
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                res_.setResult(new StringStruct( ((BeanStruct)_instance).getTypedString()));
                return res_;
            }
        }
        if (StringUtil.quickEq(fieldName_,COMPOSITE)) {
            res_.setResult(((BeanStruct)_instance).getComposite());
            return res_;
        }
        return res_;
    }

    @Override
    public ResultErrorStd setOtherResult(ClassField _classField, Struct _instance, Struct _val) {
        ResultErrorStd res_ = new ResultErrorStd();
        String className_ = _classField.getClassName();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(className_,TYPE_BEAN_TWO)) {
            if (StringUtil.quickEq(fieldName_,CHECKED)) {
                ((BeanStruct)_instance).setChecked(BooleanStruct.isTrue(_val));
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(fieldName_,TYPED_SHORT)) {
                ((BeanStruct)_instance).setTypedShort(NumParsers.convertToNumber(_val).shortStruct());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringUtil.quickEq(fieldName_,TYPED_STRING)) {
                ((BeanStruct)_instance).setTypedString(NumParsers.getString(_val).getInstance());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
        }
        return res_;
    }

    @Override
    public ResultErrorStd getOtherResultBean(Struct _instance, ClassMethodId _method, Struct... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        String className_ = _method.getClassName();
        String methodName_ = _method.getConstraints().getName();
        if (StringUtil.quickEq(className_,TYPE_COMPOSITE)) {

            if (StringUtil.quickEq(methodName_,"sum")) {
                int s_ = 0;
                for (Struct s: _args) {
                    s_ += NumParsers.convertToNumber(s).intStruct();
                }
                res_.setResult(new IntStruct(s_));
                return res_;
            }
        }
         if (StringUtil.quickEq(className_,TYPE_BEAN_ONE)) {
            if (StringUtil.quickEq(methodName_,"length")) {
                res_.setResult(new IntStruct(NumParsers.getString(_args[0]).getInstance().length()));
                return res_;
            }
        }
        if (StringUtil.quickEq(className_,TYPE_BEAN_TWO)) {
            if (StringUtil.quickEq(methodName_,GO)) {
                if (_method.getConstraints().getParametersTypesLength() == 0) {
                    res_.setResult(new StringStruct(((BeanStruct)_instance).go()));
                    return res_;
                }
            }
        }
        return res_;
    }
    @Override
    public ResultErrorStd getOtherResultBean(ConstructorId _method, Struct... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        String className_ = _method.getName();
        if (StringUtil.quickEq(className_,TYPE_BEAN_ONE)) {
            res_.setResult(new BeanStruct(new BeanOne()));
            return res_;
        }
        if (StringUtil.quickEq(className_,TYPE_BEAN_TWO)) {
            res_.setResult(new BeanStruct(new BeanTwo()));
            return res_;
        }
        if (StringUtil.quickEq(className_,TYPE_BEAN_ONE)) {
            res_.setResult(new BeanStruct(new BeanOne()));
            return res_;
        }
        if (StringUtil.quickEq(className_,TYPE_BEAN_TWO)) {
            res_.setResult(new BeanStruct(new BeanTwo()));
            return res_;
        }
        return res_;
    }

    @Override
    public ResultErrorStd getOtherName(Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        Struct arg_ = Argument.getNull(_instance);
        res_.setResult(arg_);
        return res_;
    }
    protected Struct newSimpleBean(String _language, BeanInfo _bean) {
        ConstructorId id_ = new ConstructorId(_bean.getResolvedClassName(), new StringList(), false);
        Struct[] args_ = NatStdFctOperation.getObjects(Argument.toArgArray(new CustList<Argument>()));
        ResultErrorStd res_ = getOtherResultBean(id_, args_);
        Struct strBean_ = res_.getResult();
        BeanStruct str_ = (BeanStruct) strBean_;
        str_.setForms(new StringMapObjectSample());
        return strBean_;
    }

    public ArrayStruct getTree(AbsMap<String, Integer> _tree) {
        ArrayStruct arr_ = new ArrayStruct(_tree.size(),StringExpUtil.getPrettyArrayType(OBJECT));
        int i_ = 0;
        for (EntryCust<String,Integer> e: _tree.entryList()){
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(StringUtil.nullToEmpty(e.getKey())),new IntStruct(e.getValue()));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
}
