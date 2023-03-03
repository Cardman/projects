package code.expressionlanguage.stds;

import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.common.CstFieldInfo;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.fcts.*;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.functionid.StdClassModifier;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class AliasCore {
    private static final String BAD_ENCODE = "BadEncode";
    private static final String DIVISION_ZERO = "DivisionZero";
    private static final String GET_MESSAGE = "GetMessage";
    private static final String BAD_SIZE = "BadSize";
    private static final String NULL_PE = "NullPe";
    private static final String OBJECT = "Object";
    private static final String CAST_TYPE = "CastType";
    private static final String STORE = "Store";
    private static final String ERROR = "Error";
    private static final String ERROR_CURRENT_STACK = "ErrorCurrentStack";
    private static final String ERROR_TO_STRING = "ErrorToString";
    private static final String VOID = "Void";
    private static final String GET_CAUSE = "GetCause";
    private static final String BAD_INDEX = "BadIndex";
    private static final String BAD_ARG_NUMBER = "BadArgNumber";
    private static final String ABSTRACT_TYPE_ERR = "AbstractTypeErr";
    private static final String ILLEGAL_TYPE = "IllegalType";
    private static final String NON_INVOKABLE = "NonInvokable";
    private static final String ENUMS = "Enums";
    private static final String NB_FORMAT = "NbFormat";
    private static final String SOF = "Sof";
    private static final String ILLEGAL_ARG = "IllegalArg";
    private static final String ARRAY_LENGTH = "ArrayLength";
    private static final String CLONE = "Clone";
    private static final String NAME = "Name";
    private static final String ORDINAL = "Ordinal";
    private static final String READ_RESOURCES_NAMES = "ReadResourcesNames";
    private static final String READ_RESOURCES_NAMES_LENGTH = "ReadResourcesNamesLength";
    private static final String READ_RESOURCES = "ReadResources";
    private static final String READ_RESOURCES_INDEX = "ReadResourcesIndex";
    private static final String RESOURCES = "Resources";
    private static final String ERROR_INIT_CLASS = "ErrorInitClass";
    private static final String SAME_REF = "SameRef";
    private static final String STRING_UTIL = "StringUtil";
    private static final String STRING_UTIL_VALUE_OF = "StringUtilValueOf";
    private static final String SET_PARENT = "SetParent";
    private static final String RANGE = "Range";
    private static final String RANGE_UPPER = "RangeUpper";
    private static final String RANGE_LOWER = "RangeLower";
    private static final String RANGE_STEP = "RangeStep";
    private static final String RANGE_UNLIMITED = "RangeUnlimited";
    private static final String RANGE_UNLIMITED_STEP = "RangeUnlimitedStep";
    private static final String OBJECTS_UTIL = "ObjectsUtil";
    private static final String GET_PARENT = "GetParent";
    private static final String GET_FCT = "GetFct";
    private static final String EMPTY_STRING = "";
    private String aliasObject;

    private String aliasVoid;

    private String aliasClone;

    private String aliasEnums;
    private String aliasError;
    private String aliasErrorCurrentStack;
    private String aliasErrorToString;
    private String aliasGetCause;
    private String aliasGetMessage;
    private String aliasBadSize;
    private String aliasDivisionZero;
    private String aliasCastType;
    private String aliasStore;
    private String aliasNullPe;
    private String aliasNbFormat;
    private String aliasBadEncode;
    private String aliasBadIndex;
    private String aliasBadArgNumber;
    private String aliasAbstractTypeErr;
    private String aliasIllegalType;
    private String aliasNonInvokable;
    private String aliasIllegalArg;
    private String aliasSof;

    private String aliasName;
    private String aliasOrdinal;
    private String aliasErrorInitClass;
    private String aliasRange;
    private String aliasRangeLower;
    private String aliasRangeUpper;
    private String aliasRangeStep;
    private String aliasRangeUnlimited;
    private String aliasRangeUnlimitedStep;
    private String aliasObjectsUtil;
    private String aliasSameRef;
    private String aliasGetParent;
    private String aliasSetParent;
    private String aliasGetFct;
    private String aliasReadResourcesNames;
    private String aliasReadResources;
    private String aliasReadResourcesNamesLength;
    private String aliasReadResourcesIndex;
    private String aliasResources;
    private String aliasStringUtil;
    private String aliasStringUtilValueOf;
    private String aliasArrayLength;
    private final AliasParamCore params = new AliasParamCore();

    public void build(StringMap<String> _util, StringMap<String> _cust) {
        setAliasBadEncode(LgNamesContent.get(_util,_cust, BAD_ENCODE));
        setAliasDivisionZero(LgNamesContent.get(_util,_cust, DIVISION_ZERO));
        setAliasGetMessage(LgNamesContent.get(_util,_cust, GET_MESSAGE));
        setAliasBadSize(LgNamesContent.get(_util,_cust, BAD_SIZE));
        setAliasNullPe(LgNamesContent.get(_util,_cust, NULL_PE));
        setAliasObject(LgNamesContent.get(_util,_cust, OBJECT));
        setAliasCastType(LgNamesContent.get(_util,_cust, CAST_TYPE));
        setAliasStore(LgNamesContent.get(_util,_cust, STORE));
        setAliasError(LgNamesContent.get(_util,_cust, ERROR));
        setAliasErrorCurrentStack(LgNamesContent.get(_util,_cust, ERROR_CURRENT_STACK));
        setAliasErrorToString(LgNamesContent.get(_util,_cust, ERROR_TO_STRING));
        setAliasVoid(LgNamesContent.get(_util,_cust, VOID));
        setAliasGetCause(LgNamesContent.get(_util,_cust, GET_CAUSE));
        setAliasBadIndex(LgNamesContent.get(_util,_cust, BAD_INDEX));
        setAliasBadArgNumber(LgNamesContent.get(_util,_cust, BAD_ARG_NUMBER));
        setAliasIllegalType(LgNamesContent.get(_util,_cust, ILLEGAL_TYPE));
        setAliasAbstractTypeErr(LgNamesContent.get(_util,_cust, ABSTRACT_TYPE_ERR));
        setAliasNonInvokable(LgNamesContent.get(_util,_cust, NON_INVOKABLE));
        setAliasEnums(LgNamesContent.get(_util,_cust, ENUMS));
        setAliasNbFormat(LgNamesContent.get(_util,_cust, NB_FORMAT));
        setAliasSof(LgNamesContent.get(_util,_cust, SOF));
        setAliasIllegalArg(LgNamesContent.get(_util,_cust, ILLEGAL_ARG));
        setAliasClone(LgNamesContent.get(_util,_cust, CLONE));
        setAliasName(LgNamesContent.get(_util,_cust, NAME));
        setAliasOrdinal(LgNamesContent.get(_util,_cust, ORDINAL));
        setAliasReadResourcesNames(LgNamesContent.get(_util,_cust, READ_RESOURCES_NAMES));
        setAliasReadResourcesNamesLength(LgNamesContent.get(_util,_cust, READ_RESOURCES_NAMES_LENGTH));
        setAliasReadResources(LgNamesContent.get(_util,_cust, READ_RESOURCES));
        setAliasReadResourcesIndex(LgNamesContent.get(_util,_cust, READ_RESOURCES_INDEX));
        setAliasResources(LgNamesContent.get(_util,_cust, RESOURCES));
        setAliasArrayLength(LgNamesContent.get(_util,_cust,ARRAY_LENGTH));
        setAliasErrorInitClass(LgNamesContent.get(_util,_cust, ERROR_INIT_CLASS));
        setAliasSameRef(LgNamesContent.get(_util,_cust, SAME_REF));
        setAliasStringUtil(LgNamesContent.get(_util,_cust, STRING_UTIL));
        setAliasStringUtilValueOf(LgNamesContent.get(_util,_cust, STRING_UTIL_VALUE_OF));
        setAliasSetParent(LgNamesContent.get(_util,_cust, SET_PARENT));
        setAliasRange(LgNamesContent.get(_util,_cust, RANGE));
        setAliasRangeUpper(LgNamesContent.get(_util,_cust, RANGE_UPPER));
        setAliasRangeStep(LgNamesContent.get(_util,_cust, RANGE_STEP));
        setAliasRangeUnlimitedStep(LgNamesContent.get(_util,_cust, RANGE_UNLIMITED_STEP));
        setAliasRangeLower(LgNamesContent.get(_util,_cust, RANGE_LOWER));
        setAliasRangeUnlimited(LgNamesContent.get(_util,_cust, RANGE_UNLIMITED));
        setAliasObjectsUtil(LgNamesContent.get(_util,_cust, OBJECTS_UTIL));
        setAliasGetParent(LgNamesContent.get(_util,_cust, GET_PARENT));
        setAliasGetFct(LgNamesContent.get(_util,_cust, GET_FCT));
    }
    public StringMap<String> allPrimitives() {
        StringMap<String> list_ = new StringMap<String>();
        list_.addEntry(VOID, getAliasVoid());
        return list_;
    }
    public StringMap<String> allRefTypes() {
        StringMap<String> list_ = new StringMap<String>();
        list_.addEntry(RANGE, getAliasRange());
        list_.addEntry(OBJECTS_UTIL, getAliasObjectsUtil());
        list_.addEntry(STRING_UTIL, getAliasStringUtil());
        list_.addEntry(RESOURCES, getAliasResources());
        list_.addEntry(ERROR_INIT_CLASS, getAliasErrorInitClass());
        list_.addEntry(ENUMS, getAliasEnums());
        list_.addEntry(BAD_ENCODE, getAliasBadEncode());
        list_.addEntry(BAD_INDEX, getAliasBadIndex());
        list_.addEntry(BAD_ARG_NUMBER, getAliasBadArgNumber());
        list_.addEntry(ABSTRACT_TYPE_ERR, getAliasAbstractTypeErr());
        list_.addEntry(ILLEGAL_TYPE, getAliasIllegalType());
        list_.addEntry(NON_INVOKABLE, getAliasNonInvokable());
        list_.addEntry(ILLEGAL_ARG, getAliasIllegalArg());
        list_.addEntry(DIVISION_ZERO, getAliasDivisionZero());
        list_.addEntry(STORE, getAliasStore());
        list_.addEntry(CAST_TYPE, getAliasCastType());
        list_.addEntry(BAD_SIZE, getAliasBadSize());
        list_.addEntry(SOF, getAliasSof());
        list_.addEntry(NULL_PE, getAliasNullPe());
        list_.addEntry(NB_FORMAT, getAliasNbFormat());
        list_.addEntry(ERROR, getAliasError());
        list_.addEntry(OBJECT,getAliasObject());
        return list_;
    }
    public StringMap<CustList<KeyValueMemberName>> allTableTypeFieldNames() {
        StringMap<CustList<KeyValueMemberName>> map_ = new StringMap<CustList<KeyValueMemberName>>();
        map_.addEntry(StringExpUtil.getPrettyArrayType(getAliasObject()), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ARRAY_LENGTH, getAliasArrayLength())));
        return map_;
    }
    public StringMap<CustList<KeyValueMemberName>> allTableTypeMethodNames() {
        StringMap<CustList<KeyValueMemberName>> map_ = new StringMap<CustList<KeyValueMemberName>>();
        map_.addEntry(StringExpUtil.getPrettyArrayType(getAliasObject()), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(CLONE, getAliasClone())));
        map_.addEntry(getAliasError(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ERROR_CURRENT_STACK, getAliasErrorCurrentStack()),
                new KeyValueMemberName(ERROR_TO_STRING, getAliasErrorToString()),
                new KeyValueMemberName(GET_MESSAGE, getAliasGetMessage()),
                new KeyValueMemberName(GET_CAUSE, getAliasGetCause())));
        map_.addEntry(getAliasRange(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(RANGE_LOWER, getAliasRangeLower()),
                new KeyValueMemberName(RANGE_UPPER, getAliasRangeUpper()),
                new KeyValueMemberName(RANGE_STEP, getAliasRangeStep()),
                new KeyValueMemberName(RANGE_UNLIMITED_STEP, getAliasRangeUnlimitedStep()),
                new KeyValueMemberName(RANGE_UNLIMITED, getAliasRangeUnlimited())));
        map_.addEntry(getAliasObjectsUtil(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(SAME_REF, getAliasSameRef()),
                new KeyValueMemberName(GET_PARENT, getAliasGetParent()),
                new KeyValueMemberName(SET_PARENT, getAliasSetParent()),
                new KeyValueMemberName(GET_FCT, getAliasGetFct())));
        map_.addEntry(getAliasStringUtil(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(STRING_UTIL_VALUE_OF, getAliasStringUtilValueOf())));
        map_.addEntry(getAliasResources(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(READ_RESOURCES_NAMES, getAliasReadResourcesNames()),
                new KeyValueMemberName(READ_RESOURCES_NAMES_LENGTH, getAliasReadResourcesNamesLength()),
                new KeyValueMemberName(READ_RESOURCES, getAliasReadResources()),
                new KeyValueMemberName(READ_RESOURCES_INDEX, getAliasReadResourcesIndex())));
        map_.addEntry(getAliasEnums(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(NAME, getAliasName()),
                new KeyValueMemberName(ORDINAL, getAliasOrdinal())));
        return map_;
    }
    public void build(LgNames _lgNames) {
        StringMap<StandardType> standards_ = _lgNames.getStandards();
        CustList<StandardMethod> methods_ = new CustList<StandardMethod>();
        CustList<StandardConstructor> constructors_ = new CustList<StandardConstructor>();
        CustList<CstFieldInfo> fields_ = new CustList<CstFieldInfo>();
        StandardClass stdcl_ = new StandardClass(aliasObject, fields_, constructors_, methods_, EMPTY_STRING, MethodModifier.NORMAL, new DfObj());
        StringList params_ = new StringList();
        StandardConstructor ctor_ = new StandardConstructor(params_, false, new FctObj());
        constructors_.add(ctor_);
        StandardType std_ = stdcl_;
        standards_.addEntry(aliasObject, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasError, fields_, constructors_, methods_, aliasObject, StdClassModifier.ABSTRACT);
        String stackElt_ = _lgNames.getContent().getStackElt().getAliasStackTraceElement();
        stackElt_ = StringExpUtil.getPrettyArrayType(stackElt_);
        params_ = new StringList();
        StandardMethod method_ = new StandardMethod(aliasErrorCurrentStack, params_, stackElt_, false, MethodModifier.NORMAL, new FctErrorCurrentStack0());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasErrorToString, params_, _lgNames.getContent().getCharSeq().getAliasString(), false, MethodModifier.NORMAL,new FctErrorToString0());
        methods_.add( method_);
        params_ = new StringList(aliasError);
        method_ = new StandardMethod(aliasErrorCurrentStack, params_, stackElt_, false, MethodModifier.STATIC,new StringList(params.getAliasError0CurrentStack0()),new FctErrorCurrentStack1());
        methods_.add( method_);
        params_ = new StringList(aliasError);
        method_ = new StandardMethod(aliasErrorToString, params_, _lgNames.getContent().getCharSeq().getAliasString(), false, MethodModifier.STATIC,new StringList(params.getAliasError0ToStringMethod0()),new FctErrorToString1());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetMessage, params_, _lgNames.getContent().getCharSeq().getAliasString(), false, MethodModifier.NORMAL,new FctErrorGetMessage());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetCause, params_, _lgNames.getContent().getCoreNames().getAliasObject(), false, MethodModifier.NORMAL,new FctErrorGetCause());
        methods_.add( method_);
        std_ = stdcl_;
        standards_.addEntry(aliasError, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasNullPe, fields_, constructors_, methods_, aliasError, StdClassModifier.ABSTRACT);
        std_ = stdcl_;
        standards_.addEntry(aliasNullPe, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasDivisionZero, fields_, constructors_, methods_, aliasError, StdClassModifier.ABSTRACT);
        std_ = stdcl_;
        standards_.addEntry(aliasDivisionZero, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasCastType, fields_, constructors_, methods_, aliasError, StdClassModifier.ABSTRACT);
        std_ = stdcl_;
        standards_.addEntry(aliasCastType, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasStore, fields_, constructors_, methods_, aliasError, StdClassModifier.ABSTRACT);
        std_ = stdcl_;
        standards_.addEntry(aliasStore, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasBadSize, fields_, constructors_, methods_, aliasError, StdClassModifier.ABSTRACT);
        std_ = stdcl_;
        standards_.addEntry(aliasBadSize, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasNbFormat, fields_, constructors_, methods_, aliasError, StdClassModifier.ABSTRACT);
        std_ = stdcl_;
        standards_.addEntry(aliasNbFormat, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasBadIndex, fields_, constructors_, methods_, aliasError, StdClassModifier.ABSTRACT);
        std_ = stdcl_;
        standards_.addEntry(aliasBadIndex, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasBadArgNumber, fields_, constructors_, methods_, aliasError, StdClassModifier.ABSTRACT);
        std_ = stdcl_;
        standards_.addEntry(aliasBadArgNumber, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasIllegalType, fields_, constructors_, methods_, aliasError, StdClassModifier.ABSTRACT);
        std_ = stdcl_;
        standards_.addEntry(aliasIllegalType, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasAbstractTypeErr, fields_, constructors_, methods_, aliasError, StdClassModifier.ABSTRACT);
        std_ = stdcl_;
        standards_.addEntry(aliasAbstractTypeErr, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasNonInvokable, fields_, constructors_, methods_, aliasError, StdClassModifier.ABSTRACT);
        std_ = stdcl_;
        standards_.addEntry(aliasNonInvokable, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasIllegalArg, fields_, constructors_, methods_, aliasError, StdClassModifier.ABSTRACT);
        std_ = stdcl_;
        standards_.addEntry(aliasIllegalArg, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasSof, fields_, constructors_, methods_, aliasError, StdClassModifier.ABSTRACT);
        std_ = stdcl_;
        standards_.addEntry(aliasSof, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasErrorInitClass, fields_, constructors_, methods_, aliasError, StdClassModifier.ABSTRACT);
        std_ = stdcl_;
        standards_.addEntry(aliasErrorInitClass, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasBadEncode, fields_, constructors_, methods_, aliasError, StdClassModifier.ABSTRACT);
        std_ = stdcl_;
        standards_.addEntry(aliasBadEncode, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasEnums, fields_, constructors_, methods_, aliasObject, StdClassModifier.HYPER_ABSTRACT);
        params_ = new StringList(_lgNames.getContent().getPredefTypes().getAliasEnumType());
        method_ = new StandardMethod(aliasName, params_, _lgNames.getContent().getCharSeq().getAliasString(), false, MethodModifier.STATIC,new StringList(params.getAliasEnums0Name0()),new FctEnumsName());
        methods_.add( method_);
        params_ = new StringList(_lgNames.getContent().getPredefTypes().getAliasEnumType());
        method_ = new StandardMethod(aliasOrdinal, params_, _lgNames.getContent().getPrimTypes().getAliasPrimInteger(), false, MethodModifier.STATIC,new StringList(params.getAliasEnums0Ordinal0()),new FctEnumsOrdinal());
        methods_.add( method_);
        standards_.addEntry(aliasEnums, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasRange, fields_, constructors_, methods_, aliasObject, MethodModifier.FINAL);
        params_ = new StringList(_lgNames.getPrimTypes().getAliasPrimInteger(),_lgNames.getPrimTypes().getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_, false, new StringList(params.getAliasRange0Range0(),params.getAliasRange0Range1()),new FctRange1());
        constructors_.add( ctor_);
        params_ = new StringList(_lgNames.getPrimTypes().getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_, false, new StringList(params.getAliasRange1Range0()),new FctRange0());
        constructors_.add( ctor_);
        params_ = new StringList(_lgNames.getPrimTypes().getAliasPrimInteger(),_lgNames.getPrimTypes().getAliasPrimInteger(),_lgNames.getPrimTypes().getAliasPrimInteger());
        ctor_ = new StandardConstructor(params_, false, new StringList(params.getAliasRange2Range0(),params.getAliasRange2Range1(),params.getAliasRange2Range2()),new FctRange2());
        constructors_.add( ctor_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasRangeLower, params_, _lgNames.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctRangeLower());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasRangeUpper, params_, _lgNames.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctRangeUpper());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasRangeStep, params_, _lgNames.getPrimTypes().getAliasPrimInteger(), false, MethodModifier.FINAL, new FctRangeStep());
        methods_.add( method_);
        params_ = new StringList(_lgNames.getPrimTypes().getAliasPrimInteger(),_lgNames.getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasRangeUnlimitedStep, params_, aliasRange, false, MethodModifier.STATIC,new StringList(params.getAliasRange0UnlimitedStep0(),params.getAliasRange0UnlimitedStep1()), new FctRangeUnlimitedStep());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasRangeUnlimited, params_, _lgNames.getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.FINAL, new FctRangeUnlimited());
        methods_.add( method_);
        standards_.addEntry(aliasRange, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasObjectsUtil, fields_, constructors_, methods_, aliasObject, StdClassModifier.HYPER_ABSTRACT);
        params_ = new StringList(aliasObject,aliasObject);
        method_ = new StandardMethod(aliasSameRef, params_, _lgNames.getContent().getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.STATIC,new StringList(params.getAliasObjectsUtil0SameRef0(),params.getAliasObjectsUtil0SameRef1()), new FctObjEquals());
        methods_.add( method_);
        params_ = new StringList(aliasObject);
        method_ = new StandardMethod(aliasGetParent, params_, aliasObject, false, MethodModifier.STATIC,new StringList(params.getAliasObjectsUtil0GetParent0()),new FctObjGetParent());
        methods_.add( method_);
        params_ = new StringList(aliasObject,aliasObject);
        method_ = new StandardMethod(aliasSetParent, params_, aliasVoid, false, MethodModifier.STATIC,new StringList(params.getAliasObjectsUtil0SetParent0(),params.getAliasObjectsUtil0SetParent1()),new FctObjSetParent());
        methods_.add( method_);
        params_ = new StringList(aliasObject);
        method_ = new StandardMethod(aliasGetFct, params_, _lgNames.getReflect().getAliasFct(), false, MethodModifier.STATIC,new StringList(params.getAliasObjectsUtil0GetFct0()),new FctObjGetFct());
        methods_.add( method_);
        standards_.addEntry(aliasObjectsUtil, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasStringUtil, fields_, constructors_, methods_, aliasObject, StdClassModifier.HYPER_ABSTRACT);
        params_ = new StringList(aliasObject);
        method_ = new StandardMethod(aliasStringUtilValueOf, params_, _lgNames.getContent().getCharSeq().getAliasString(), false, MethodModifier.STATIC,new StringList(params.getAliasStringUtil0ValueOfMethod0()),new FctStringUtilValueOf());
        methods_.add( method_);
        standards_.addEntry(aliasStringUtil, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasResources, fields_, constructors_, methods_, aliasObject, StdClassModifier.HYPER_ABSTRACT);
        params_ = new StringList();
        method_ = new StandardMethod(aliasReadResourcesNames, params_, StringExpUtil.getPrettyArrayType(_lgNames.getContent().getCharSeq().getAliasString()), false, MethodModifier.STATIC, new FctResourcesNames());
        methods_.add( method_);
        params_ = new StringList(_lgNames.getContent().getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasReadResources, params_, _lgNames.getContent().getCharSeq().getAliasString(), false, MethodModifier.STATIC,new StringList(params.getAliasResources0ReadResources0()),new FctResourcesRead());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasReadResourcesNamesLength, params_, _lgNames.getContent().getPrimTypes().getAliasPrimInteger(), false, MethodModifier.STATIC, new FctResourcesReadNamesLength());
        methods_.add( method_);
        params_ = new StringList(_lgNames.getContent().getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasReadResourcesIndex, params_, _lgNames.getContent().getCharSeq().getAliasString(), false, MethodModifier.STATIC,new StringList(params.getAliasResources0ReadResourcesIndex0()),new FctResourcesReadIndex());
        methods_.add( method_);
        standards_.addEntry(aliasResources, stdcl_);
    }

    public String getAliasObject() {
        return aliasObject;
    }

    public void setAliasObject(String _aliasObject) {
        aliasObject = _aliasObject;
    }

    public String getAliasEnums() {
        return aliasEnums;
    }

    public void setAliasEnums(String _aliasEnums) {
        aliasEnums = _aliasEnums;
    }

    public String getAliasError() {
        return aliasError;
    }

    public void setAliasError(String _aliasError) {
        aliasError = _aliasError;
    }

    public String getAliasErrorToString() {
        return aliasErrorToString;
    }

    public void setAliasErrorToString(String _aliasErrorToString) {
        this.aliasErrorToString = _aliasErrorToString;
    }

    public String getAliasErrorCurrentStack() {
        return aliasErrorCurrentStack;
    }

    public void setAliasErrorCurrentStack(String _aliasErrorCurrentStack) {
        this.aliasErrorCurrentStack = _aliasErrorCurrentStack;
    }

    public String getAliasGetMessage() {
        return aliasGetMessage;
    }

    public void setAliasGetMessage(String _aliasGetMessage) {
        aliasGetMessage = _aliasGetMessage;
    }

    public String getAliasGetCause() {
        return aliasGetCause;
    }

    public void setAliasGetCause(String _aliasGetCause) {
        aliasGetCause = _aliasGetCause;
    }

    public String getAliasBadSize() {
        return aliasBadSize;
    }

    public void setAliasBadSize(String _aliasBadSize) {
        aliasBadSize = _aliasBadSize;
    }

    public String getAliasDivisionZero() {
        return aliasDivisionZero;
    }

    public void setAliasDivisionZero(String _aliasDivisionZero) {
        aliasDivisionZero = _aliasDivisionZero;
    }

    public String getAliasCastType() {
        return aliasCastType;
    }

    public void setAliasCastType(String _aliasCast) {
        aliasCastType = _aliasCast;
    }

    public String getAliasStore() {
        return aliasStore;
    }

    public void setAliasStore(String _aliasStore) {
        aliasStore = _aliasStore;
    }

    public String getAliasNullPe() {
        return aliasNullPe;
    }

    public void setAliasNullPe(String _aliasNullPe) {
        aliasNullPe = _aliasNullPe;
    }

    public String getAliasNbFormat() {
        return aliasNbFormat;
    }

    public void setAliasNbFormat(String _aliasNbFormat) {
        aliasNbFormat = _aliasNbFormat;
    }

    public String getAliasBadEncode() {
        return aliasBadEncode;
    }

    public void setAliasBadEncode(String _aliasBadEncode) {
        aliasBadEncode = _aliasBadEncode;
    }

    public String getAliasBadIndex() {
        return aliasBadIndex;
    }

    public void setAliasBadIndex(String _aliasBadIndex) {
        aliasBadIndex = _aliasBadIndex;
    }

    public String getAliasBadArgNumber() {
        return aliasBadArgNumber;
    }

    public void setAliasBadArgNumber(String _aliasBadArgNumber) {
        this.aliasBadArgNumber = _aliasBadArgNumber;
    }

    public String getAliasAbstractTypeErr() {
        return aliasAbstractTypeErr;
    }

    public void setAliasAbstractTypeErr(String _aliasAbstractTypeErr) {
        this.aliasAbstractTypeErr = _aliasAbstractTypeErr;
    }

    public String getAliasIllegalType() {
        return aliasIllegalType;
    }

    public void setAliasIllegalType(String _aliasIllegalType) {
        this.aliasIllegalType = _aliasIllegalType;
    }

    public String getAliasNonInvokable() {
        return aliasNonInvokable;
    }

    public void setAliasNonInvokable(String _aliasNonInvokable) {
        this.aliasNonInvokable = _aliasNonInvokable;
    }

    public String getAliasIllegalArg() {
        return aliasIllegalArg;
    }

    public void setAliasIllegalArg(String _aliasIllegalArg) {
        aliasIllegalArg = _aliasIllegalArg;
    }

    public String getAliasSof() {
        return aliasSof;
    }

    public void setAliasSof(String _aliasSof) {
        aliasSof = _aliasSof;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String _aliasName) {
        aliasName = _aliasName;
    }

    public String getAliasOrdinal() {
        return aliasOrdinal;
    }

    public void setAliasOrdinal(String _aliasOrdinal) {
        aliasOrdinal = _aliasOrdinal;
    }

    public String getAliasErrorInitClass() {
        return aliasErrorInitClass;
    }

    public void setAliasErrorInitClass(String _aliasErrorInitClass) {
        aliasErrorInitClass = _aliasErrorInitClass;
    }

    public String getAliasRange() {
        return aliasRange;
    }

    public void setAliasRange(String _aliasRange) {
        this.aliasRange = _aliasRange;
    }

    public String getAliasRangeLower() {
        return aliasRangeLower;
    }

    public void setAliasRangeLower(String _aliasRangeLower) {
        this.aliasRangeLower = _aliasRangeLower;
    }

    public String getAliasRangeUpper() {
        return aliasRangeUpper;
    }

    public void setAliasRangeUpper(String _aliasRangeUpper) {
        this.aliasRangeUpper = _aliasRangeUpper;
    }

    public String getAliasRangeStep() {
        return aliasRangeStep;
    }

    public void setAliasRangeStep(String _v) {
        this.aliasRangeStep = _v;
    }

    public String getAliasRangeUnlimitedStep() {
        return aliasRangeUnlimitedStep;
    }

    public void setAliasRangeUnlimitedStep(String _aliasRangeUnlimitedStep) {
        this.aliasRangeUnlimitedStep = _aliasRangeUnlimitedStep;
    }

    public String getAliasRangeUnlimited() {
        return aliasRangeUnlimited;
    }

    public void setAliasRangeUnlimited(String _aliasRangeUnlimited) {
        this.aliasRangeUnlimited = _aliasRangeUnlimited;
    }

    public String getAliasObjectsUtil() {
        return aliasObjectsUtil;
    }

    public void setAliasObjectsUtil(String _aliasObjectsUtil) {
        aliasObjectsUtil = _aliasObjectsUtil;
    }

    public String getAliasStringUtil() {
        return aliasStringUtil;
    }

    public void setAliasStringUtil(String _aliasStringUtil) {
        aliasStringUtil = _aliasStringUtil;
    }

    public String getAliasStringUtilValueOf() {
        return aliasStringUtilValueOf;
    }

    public void setAliasStringUtilValueOf(String _aliasStringUtilValueOf) {
        this.aliasStringUtilValueOf = _aliasStringUtilValueOf;
    }

    public String getAliasSameRef() {
        return aliasSameRef;
    }

    public void setAliasSameRef(String _aliasSameRef) {
        aliasSameRef = _aliasSameRef;
    }

    public String getAliasGetParent() {
        return aliasGetParent;
    }

    public void setAliasGetParent(String _aliasGetParent) {
        aliasGetParent = _aliasGetParent;
    }

    public String getAliasSetParent() {
        return aliasSetParent;
    }

    public void setAliasSetParent(String _aliasSetParent) {
        aliasSetParent = _aliasSetParent;
    }

    public String getAliasGetFct() {
        return aliasGetFct;
    }

    public void setAliasGetFct(String _aliasGetFct) {
        this.aliasGetFct = _aliasGetFct;
    }

    public String getAliasVoid() {
        return aliasVoid;
    }

    public void setAliasVoid(String _aliasVoid) {
        aliasVoid = _aliasVoid;
    }

    public String getAliasClone() {
        return aliasClone;
    }

    public void setAliasClone(String _aliasClone) {
        aliasClone = _aliasClone;
    }

	public String getAliasReadResourcesNames() {
		return aliasReadResourcesNames;
	}

	public void setAliasReadResourcesNames(String _aliasReadResourcesNames) {
		aliasReadResourcesNames = _aliasReadResourcesNames;
	}

    public String getAliasReadResourcesNamesLength() {
        return aliasReadResourcesNamesLength;
    }

    public void setAliasReadResourcesNamesLength(String _aliasReadResourcesNamesLength) {
        this.aliasReadResourcesNamesLength = _aliasReadResourcesNamesLength;
    }

    public String getAliasReadResources() {
		return aliasReadResources;
	}

	public void setAliasReadResources(String _aliasReadResources) {
		aliasReadResources = _aliasReadResources;
	}

    public String getAliasReadResourcesIndex() {
        return aliasReadResourcesIndex;
    }

    public void setAliasReadResourcesIndex(String _aliasReadResourcesIndex) {
        this.aliasReadResourcesIndex = _aliasReadResourcesIndex;
    }

    public String getAliasResources() {
		return aliasResources;
	}

	public void setAliasResources(String _aliasResources) {
		aliasResources = _aliasResources;
	}

    public String getAliasArrayLength() {
        return aliasArrayLength;
    }

    public void setAliasArrayLength(String _aliasArrayLength) {
        this.aliasArrayLength = _aliasArrayLength;
    }

    public AliasParamCore getParams() {
        return params;
    }
}
