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
    private String aliasGetMessage;
    private String aliasCustomError;
    private String aliasBadSize;
    private String aliasDivisionZero;
    private String aliasCast;
    private String aliasStore;
    private String aliasNullPe;
    private String aliasNbFormat;
    private String aliasBadEncode;
    private String aliasBadIndex;
    private String aliasSof;

    private String aliasName;
    private String aliasOrdinal;
    private String aliasErrorInitClass;

    private String aliasObjectsUtil;
    private String aliasSameRef;
    private String aliasGetParent;

    public void build(LgNames _lgNames) {
        StringMap<StandardType> standards = _lgNames.getStandards();
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
        standards.put(aliasObject, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasError, fields_, constructors_, methods_, aliasObject, MethodModifier.NORMAL);
        String stackElt_ = _lgNames.getAliasStackTraceElement();
        stackElt_ = PrimitiveTypeUtil.getPrettyArrayType(stackElt_);
        params_ = new StringList();
        method_ = new StandardMethod(_lgNames.getAliasCurrentStack(), params_, stackElt_, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(_lgNames.getAliasToString(), params_, _lgNames.getAliasString(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetMessage, params_, _lgNames.getAliasString(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        std_ = stdcl_;
        standards.put(aliasError, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasNullPe, fields_, constructors_, methods_, aliasError, MethodModifier.NORMAL);
        std_ = stdcl_;
        standards.put(aliasNullPe, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasDivisionZero, fields_, constructors_, methods_, aliasError, MethodModifier.NORMAL);
        std_ = stdcl_;
        standards.put(aliasDivisionZero, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasCast, fields_, constructors_, methods_, aliasError, MethodModifier.NORMAL);
        std_ = stdcl_;
        standards.put(aliasCast, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasStore, fields_, constructors_, methods_, aliasError, MethodModifier.NORMAL);
        std_ = stdcl_;
        standards.put(aliasStore, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasBadSize, fields_, constructors_, methods_, aliasError, MethodModifier.NORMAL);
        std_ = stdcl_;
        standards.put(aliasBadSize, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasNbFormat, fields_, constructors_, methods_, aliasError, MethodModifier.NORMAL);
        std_ = stdcl_;
        standards.put(aliasNbFormat, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasBadIndex, fields_, constructors_, methods_, aliasError, MethodModifier.NORMAL);
        std_ = stdcl_;
        standards.put(aliasBadIndex, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasSof, fields_, constructors_, methods_, aliasError, MethodModifier.NORMAL);
        std_ = stdcl_;
        standards.put(aliasSof, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasErrorInitClass, fields_, constructors_, methods_, aliasError, MethodModifier.NORMAL);
        std_ = stdcl_;
        standards.put(aliasErrorInitClass, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasBadEncode, fields_, constructors_, methods_, aliasError, MethodModifier.NORMAL);
        std_ = stdcl_;
        standards.put(aliasBadEncode, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasCustomError, fields_, constructors_, methods_, aliasObject, MethodModifier.NORMAL);
        std_ = stdcl_;
        standards.put(aliasCustomError, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasEnums, fields_, constructors_, methods_, aliasObject, MethodModifier.FINAL);
        params_ = new StringList(_lgNames.getAliasEnum());
        method_ = new StandardMethod(aliasName, params_, _lgNames.getAliasString(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_lgNames.getAliasEnum());
        method_ = new StandardMethod(aliasOrdinal, params_, _lgNames.getAliasPrimInteger(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        standards.put(aliasEnums, stdcl_);
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
        standards.put(aliasObjectsUtil, stdcl_);
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

    public String getAliasCustomError() {
        return aliasCustomError;
    }

    public void setAliasCustomError(String _aliasCustomError) {
        aliasCustomError = _aliasCustomError;
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

    public String getAliasCast() {
        return aliasCast;
    }

    public void setAliasCast(String _aliasCast) {
        aliasCast = _aliasCast;
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

}
