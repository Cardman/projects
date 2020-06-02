package code.expressionlanguage.stds;

import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class AliasCore {
    private static final String EMPTY_STRING = "";
    private String aliasObject;

    private String aliasVoid;

    private String aliasClone;

    private String aliasEnums;
    private String aliasError;
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

    public void build(LgNames _lgNames) {
        StringMap<StandardType> standards_ = _lgNames.getStandards();
        StringMap<StandardField> fields_;
        StringList params_;
        StandardMethod method_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        StandardType std_;
        StandardClass stdcl_;
        stdcl_ = new StandardClass(aliasObject, fields_, constructors_, methods_, EMPTY_STRING, MethodModifier.NORMAL);
        std_ = stdcl_;
        standards_.put(aliasObject, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasError, fields_, constructors_, methods_, aliasObject, MethodModifier.ABSTRACT);
        String stackElt_ = _lgNames.getAliasStackTraceElement();
        stackElt_ = PrimitiveTypeUtil.getPrettyArrayType(stackElt_);
        params_ = new StringList();
        method_ = new StandardMethod(_lgNames.getAliasCurrentStack(), params_, stackElt_, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(_lgNames.getAliasToStringMethod(), params_, _lgNames.getAliasString(), false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasError);
        method_ = new StandardMethod(_lgNames.getAliasCurrentStack(), params_, stackElt_, false, MethodModifier.STATIC,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasError);
        method_ = new StandardMethod(_lgNames.getAliasToStringMethod(), params_, _lgNames.getAliasString(), false, MethodModifier.STATIC,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetMessage, params_, _lgNames.getAliasString(), false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetCause, params_, _lgNames.getAliasObject(), false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        std_ = stdcl_;
        standards_.put(aliasError, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasNullPe, fields_, constructors_, methods_, aliasError, MethodModifier.ABSTRACT);
        std_ = stdcl_;
        standards_.put(aliasNullPe, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasDivisionZero, fields_, constructors_, methods_, aliasError, MethodModifier.ABSTRACT);
        std_ = stdcl_;
        standards_.put(aliasDivisionZero, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasCastType, fields_, constructors_, methods_, aliasError, MethodModifier.ABSTRACT);
        std_ = stdcl_;
        standards_.put(aliasCastType, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasStore, fields_, constructors_, methods_, aliasError, MethodModifier.ABSTRACT);
        std_ = stdcl_;
        standards_.put(aliasStore, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasBadSize, fields_, constructors_, methods_, aliasError, MethodModifier.ABSTRACT);
        std_ = stdcl_;
        standards_.put(aliasBadSize, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasNbFormat, fields_, constructors_, methods_, aliasError, MethodModifier.ABSTRACT);
        std_ = stdcl_;
        standards_.put(aliasNbFormat, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasBadIndex, fields_, constructors_, methods_, aliasError, MethodModifier.ABSTRACT);
        std_ = stdcl_;
        standards_.put(aliasBadIndex, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasIllegalArg, fields_, constructors_, methods_, aliasError, MethodModifier.ABSTRACT);
        std_ = stdcl_;
        standards_.put(aliasIllegalArg, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasSof, fields_, constructors_, methods_, aliasError, MethodModifier.ABSTRACT);
        std_ = stdcl_;
        standards_.put(aliasSof, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasErrorInitClass, fields_, constructors_, methods_, aliasError, MethodModifier.ABSTRACT);
        std_ = stdcl_;
        standards_.put(aliasErrorInitClass, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasBadEncode, fields_, constructors_, methods_, aliasError, MethodModifier.ABSTRACT);
        std_ = stdcl_;
        standards_.put(aliasBadEncode, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasEnums, fields_, constructors_, methods_, aliasObject, MethodModifier.ABSTRACT);
        params_ = new StringList(_lgNames.getAliasEnumType());
        method_ = new StandardMethod(aliasName, params_, _lgNames.getAliasString(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_lgNames.getAliasEnumType());
        method_ = new StandardMethod(aliasOrdinal, params_, _lgNames.getAliasPrimInteger(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        standards_.put(aliasEnums, stdcl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasObjectsUtil, fields_, constructors_, methods_, aliasObject, MethodModifier.ABSTRACT);
        params_ = new StringList(aliasObject,aliasObject);
        method_ = new StandardMethod(aliasSameRef, params_, _lgNames.getAliasPrimBoolean(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasObject);
        method_ = new StandardMethod(aliasGetParent, params_, aliasObject, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasObject,aliasObject);
        method_ = new StandardMethod(aliasSetParent, params_, aliasVoid, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        standards_.put(aliasObjectsUtil, stdcl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasStringUtil, fields_, constructors_, methods_, aliasObject, MethodModifier.ABSTRACT);
        params_ = new StringList(aliasObject);
        method_ = new StandardMethod(_lgNames.getAliasValueOfMethod(), params_, _lgNames.getAliasString(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        standards_.put(aliasStringUtil, stdcl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasResources, fields_, constructors_, methods_, aliasObject, MethodModifier.ABSTRACT);
        params_ = new StringList();
        method_ = new StandardMethod(aliasReadResourcesNames, params_, PrimitiveTypeUtil.getPrettyArrayType(_lgNames.getAliasString()), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_lgNames.getAliasString());
        method_ = new StandardMethod(aliasReadResources, params_, _lgNames.getAliasString(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasReadResourcesNamesLength, params_, _lgNames.getAliasPrimInteger(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_lgNames.getAliasPrimInteger());
        method_ = new StandardMethod(aliasReadResourcesIndex, params_, _lgNames.getAliasString(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        standards_.put(aliasResources, stdcl_);
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

    public void setAliasReadResourcesNamesLength(String aliasReadResourcesNamesLength) {
        this.aliasReadResourcesNamesLength = aliasReadResourcesNamesLength;
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

    public void setAliasReadResourcesIndex(String aliasReadResourcesIndex) {
        this.aliasReadResourcesIndex = aliasReadResourcesIndex;
    }

    public String getAliasResources() {
		return aliasResources;
	}

	public void setAliasResources(String _aliasResources) {
		aliasResources = _aliasResources;
	}

}
