package code.expressionlanguage.stds;

import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.functionid.MethodModifier;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class AliasCore {
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

    private String aliasObjectsUtil;
    private String aliasSameRef;
    private String aliasGetParent;
    private String aliasSetParent;
    private String aliasReadResourcesNames;
    private String aliasReadResources;
    private String aliasReadResourcesNamesLength;
    private String aliasReadResourcesIndex;
    private String aliasResources;
    private String aliasStringUtil;
    private String aliasStringUtilValueOf;
    private String aliasArrayLength;
    private AliasParamCore params = new AliasParamCore();

    public void build(LgNames _lgNames) {
        StringMap<StandardType> standards_ = _lgNames.getStandards();
        CustList<StandardField> fields_;
        StringList params_;
        StandardMethod method_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        StandardType std_;
        StandardClass stdcl_;
        stdcl_ = new StandardClass(aliasObject, fields_, constructors_, methods_, EMPTY_STRING, MethodModifier.NORMAL);
        std_ = stdcl_;
        standards_.addEntry(aliasObject, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        stdcl_ = new StandardClass(aliasError, fields_, constructors_, methods_, aliasObject, MethodModifier.ABSTRACT);
        String stackElt_ = _lgNames.getContent().getStackElt().getAliasStackTraceElement();
        stackElt_ = StringExpUtil.getPrettyArrayType(stackElt_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasErrorCurrentStack, params_, stackElt_, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasErrorToString, params_, _lgNames.getContent().getCharSeq().getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(aliasError);
        method_ = new StandardMethod(aliasErrorCurrentStack, params_, stackElt_, false, MethodModifier.STATIC,new StringList(params.getAliasError0CurrentStack0()));
        methods_.add( method_);
        params_ = new StringList(aliasError);
        method_ = new StandardMethod(aliasErrorToString, params_, _lgNames.getContent().getCharSeq().getAliasString(), false, MethodModifier.STATIC,new StringList(params.getAliasError0ToStringMethod0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetMessage, params_, _lgNames.getContent().getCharSeq().getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetCause, params_, _lgNames.getContent().getCoreNames().getAliasObject(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        std_ = stdcl_;
        standards_.addEntry(aliasError, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        stdcl_ = new StandardClass(aliasNullPe, fields_, constructors_, methods_, aliasError, MethodModifier.ABSTRACT);
        std_ = stdcl_;
        standards_.addEntry(aliasNullPe, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        stdcl_ = new StandardClass(aliasDivisionZero, fields_, constructors_, methods_, aliasError, MethodModifier.ABSTRACT);
        std_ = stdcl_;
        standards_.addEntry(aliasDivisionZero, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        stdcl_ = new StandardClass(aliasCastType, fields_, constructors_, methods_, aliasError, MethodModifier.ABSTRACT);
        std_ = stdcl_;
        standards_.addEntry(aliasCastType, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        stdcl_ = new StandardClass(aliasStore, fields_, constructors_, methods_, aliasError, MethodModifier.ABSTRACT);
        std_ = stdcl_;
        standards_.addEntry(aliasStore, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        stdcl_ = new StandardClass(aliasBadSize, fields_, constructors_, methods_, aliasError, MethodModifier.ABSTRACT);
        std_ = stdcl_;
        standards_.addEntry(aliasBadSize, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        stdcl_ = new StandardClass(aliasNbFormat, fields_, constructors_, methods_, aliasError, MethodModifier.ABSTRACT);
        std_ = stdcl_;
        standards_.addEntry(aliasNbFormat, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        stdcl_ = new StandardClass(aliasBadIndex, fields_, constructors_, methods_, aliasError, MethodModifier.ABSTRACT);
        std_ = stdcl_;
        standards_.addEntry(aliasBadIndex, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        stdcl_ = new StandardClass(aliasBadArgNumber, fields_, constructors_, methods_, aliasError, MethodModifier.ABSTRACT);
        std_ = stdcl_;
        standards_.addEntry(aliasBadArgNumber, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        stdcl_ = new StandardClass(aliasIllegalType, fields_, constructors_, methods_, aliasError, MethodModifier.ABSTRACT);
        std_ = stdcl_;
        standards_.addEntry(aliasIllegalType, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        stdcl_ = new StandardClass(aliasAbstractTypeErr, fields_, constructors_, methods_, aliasError, MethodModifier.ABSTRACT);
        std_ = stdcl_;
        standards_.addEntry(aliasAbstractTypeErr, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        stdcl_ = new StandardClass(aliasNonInvokable, fields_, constructors_, methods_, aliasError, MethodModifier.ABSTRACT);
        std_ = stdcl_;
        standards_.addEntry(aliasNonInvokable, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        stdcl_ = new StandardClass(aliasIllegalArg, fields_, constructors_, methods_, aliasError, MethodModifier.ABSTRACT);
        std_ = stdcl_;
        standards_.addEntry(aliasIllegalArg, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        stdcl_ = new StandardClass(aliasSof, fields_, constructors_, methods_, aliasError, MethodModifier.ABSTRACT);
        std_ = stdcl_;
        standards_.addEntry(aliasSof, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        stdcl_ = new StandardClass(aliasErrorInitClass, fields_, constructors_, methods_, aliasError, MethodModifier.ABSTRACT);
        std_ = stdcl_;
        standards_.addEntry(aliasErrorInitClass, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        stdcl_ = new StandardClass(aliasBadEncode, fields_, constructors_, methods_, aliasError, MethodModifier.ABSTRACT);
        std_ = stdcl_;
        standards_.addEntry(aliasBadEncode, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        stdcl_ = new StandardClass(aliasEnums, fields_, constructors_, methods_, aliasObject, MethodModifier.ABSTRACT);
        params_ = new StringList(_lgNames.getContent().getPredefTypes().getAliasEnumType());
        method_ = new StandardMethod(aliasName, params_, _lgNames.getContent().getCharSeq().getAliasString(), false, MethodModifier.STATIC,new StringList(params.getAliasEnums0Name0()));
        methods_.add( method_);
        params_ = new StringList(_lgNames.getContent().getPredefTypes().getAliasEnumType());
        method_ = new StandardMethod(aliasOrdinal, params_, _lgNames.getContent().getPrimTypes().getAliasPrimInteger(), false, MethodModifier.STATIC,new StringList(params.getAliasEnums0Ordinal0()));
        methods_.add( method_);
        standards_.addEntry(aliasEnums, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        stdcl_ = new StandardClass(aliasObjectsUtil, fields_, constructors_, methods_, aliasObject, MethodModifier.ABSTRACT);
        params_ = new StringList(aliasObject,aliasObject);
        method_ = new StandardMethod(aliasSameRef, params_, _lgNames.getContent().getPrimTypes().getAliasPrimBoolean(), false, MethodModifier.STATIC,new StringList(params.getAliasObjectsUtil0SameRef0(),params.getAliasObjectsUtil0SameRef1()));
        methods_.add( method_);
        params_ = new StringList(aliasObject);
        method_ = new StandardMethod(aliasGetParent, params_, aliasObject, false, MethodModifier.STATIC,new StringList(params.getAliasObjectsUtil0GetParent0()));
        methods_.add( method_);
        params_ = new StringList(aliasObject,aliasObject);
        method_ = new StandardMethod(aliasSetParent, params_, aliasVoid, false, MethodModifier.STATIC,new StringList(params.getAliasObjectsUtil0SetParent0(),params.getAliasObjectsUtil0SetParent1()));
        methods_.add( method_);
        standards_.addEntry(aliasObjectsUtil, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        stdcl_ = new StandardClass(aliasStringUtil, fields_, constructors_, methods_, aliasObject, MethodModifier.ABSTRACT);
        params_ = new StringList(aliasObject);
        method_ = new StandardMethod(aliasStringUtilValueOf, params_, _lgNames.getContent().getCharSeq().getAliasString(), false, MethodModifier.STATIC,new StringList(params.getAliasStringUtil0ValueOfMethod0()));
        methods_.add( method_);
        standards_.addEntry(aliasStringUtil, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        stdcl_ = new StandardClass(aliasResources, fields_, constructors_, methods_, aliasObject, MethodModifier.ABSTRACT);
        params_ = new StringList();
        method_ = new StandardMethod(aliasReadResourcesNames, params_, StringExpUtil.getPrettyArrayType(_lgNames.getContent().getCharSeq().getAliasString()), false, MethodModifier.STATIC);
        methods_.add( method_);
        params_ = new StringList(_lgNames.getContent().getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasReadResources, params_, _lgNames.getContent().getCharSeq().getAliasString(), false, MethodModifier.STATIC,new StringList(params.getAliasResources0ReadResources0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasReadResourcesNamesLength, params_, _lgNames.getContent().getPrimTypes().getAliasPrimInteger(), false, MethodModifier.STATIC);
        methods_.add( method_);
        params_ = new StringList(_lgNames.getContent().getPrimTypes().getAliasPrimInteger());
        method_ = new StandardMethod(aliasReadResourcesIndex, params_, _lgNames.getContent().getCharSeq().getAliasString(), false, MethodModifier.STATIC,new StringList(params.getAliasResources0ReadResourcesIndex0()));
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
