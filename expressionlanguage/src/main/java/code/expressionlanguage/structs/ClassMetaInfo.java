package code.expressionlanguage.structs;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.AccessEnum;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.util.ClassCategory;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.MethodId;
import code.util.CustList;
import code.util.ObjectNotNullMap;
import code.util.StringList;
import code.util.StringMap;

public final class ClassMetaInfo implements Struct, ExportableStringStruct {

    private static final String EMPTY_STRING = "";

    private final String name;

    private final String superClass;

    private final StringList superInterfaces = new StringList();
    private final StringList memberTypes = new StringList();
    private final StringList upperBounds = new StringList();
    private final StringList lowerBounds = new StringList();
    private final String typeOwner;

    private final StringMap<FieldMetaInfo> fieldsInfos;
    private final ObjectNotNullMap<MethodId, MethodMetaInfo> methodsInfos;

    private final ObjectNotNullMap<ConstructorId, ConstructorMetaInfo> constructorsInfos;

    private final ClassCategory category;

    private final boolean abstractType;

    private final boolean finalType;
    private final boolean staticType;

    private final String variableOwner;
    private final AccessEnum access;
    public ClassMetaInfo(String _name, ContextEl _context, ClassCategory _cat, String _variableOwner) {
        name = _name;
        variableOwner = _variableOwner;
        staticType = true;
        typeOwner = EMPTY_STRING;
        if (_cat == ClassCategory.ARRAY) {
            String id_ = Templates.getIdFromAllTypes(_name);
            String comp_ = PrimitiveTypeUtil.getQuickComponentBaseType(id_).getComponent();
            if (PrimitiveTypeUtil.isPrimitive(comp_, _context)) {
                abstractType = true;
                superClass = EMPTY_STRING;
                access = AccessEnum.PUBLIC;
            } else {
                GeneType g_ = _context.getClassBody(comp_);
                if (g_ == null) {
                    access = AccessEnum.PUBLIC;
                } else {
                    access = g_.getAccess();
                }
                abstractType = false;
                superClass = _context.getStandards().getAliasObject();
            }
        } else {
            abstractType = true;
            superClass = EMPTY_STRING;
            access = AccessEnum.PUBLIC;
        }
        fieldsInfos = new StringMap<FieldMetaInfo>();
        methodsInfos = new ObjectNotNullMap<MethodId, MethodMetaInfo>();
        constructorsInfos = new ObjectNotNullMap<ConstructorId, ConstructorMetaInfo>();
        category = _cat;
        finalType = true;
    }

    public ClassMetaInfo(String _name, ClassCategory _cat, StringList _upperBounds, StringList _lowerBounds, String _variableOwner, AccessEnum _access) {
        name = _name;
        upperBounds.addAllElts(_upperBounds);
        lowerBounds.addAllElts(_lowerBounds);
        access = _access;
        abstractType = true;
        typeOwner = EMPTY_STRING;
        superClass = EMPTY_STRING;
        variableOwner = _variableOwner;
        fieldsInfos = new StringMap<FieldMetaInfo>();
        methodsInfos = new ObjectNotNullMap<MethodId, MethodMetaInfo>();
        constructorsInfos = new ObjectNotNullMap<ConstructorId, ConstructorMetaInfo>();
        category = _cat;
        finalType = true;
        staticType = true;
    }
    public ClassMetaInfo(String _name,
            String _superClass,
            StringList _superInterfaces,
            String _typeOwner,
            StringList _memberTypes,
            StringMap<FieldMetaInfo> _fields,
            ObjectNotNullMap<MethodId, MethodMetaInfo> _methods,
            ObjectNotNullMap<ConstructorId, ConstructorMetaInfo> _constructors,
            ClassCategory _category,
            boolean _abstractType,
            boolean _staticType,
            boolean _finalType, AccessEnum _access) {
        variableOwner = "";
        memberTypes.addAllElts(_memberTypes);
        typeOwner = _typeOwner;
        name = _name;
        superClass = _superClass;
        superInterfaces.addAllElts(_superInterfaces);
        fieldsInfos = _fields;
        methodsInfos = _methods;
        constructorsInfos = _constructors;
        category = _category;
        abstractType = _abstractType;
        staticType = _staticType;
        finalType = _finalType;
        access = _access;
    }

    public ClassMetaInfo(String _name,
            StringList _superInterfaces,String _typeOwner,
            StringList _memberTypes,StringMap<FieldMetaInfo> _fields,
            ObjectNotNullMap<MethodId, MethodMetaInfo> _methods,
            ObjectNotNullMap<ConstructorId, ConstructorMetaInfo> _constructors,
            ClassCategory _category, boolean _staticType, AccessEnum _access) {
        variableOwner = "";
        typeOwner = _typeOwner;
        memberTypes.addAllElts(_memberTypes);
        name = _name;
        superInterfaces.addAllElts(_superInterfaces);
        superClass = EMPTY_STRING;
        fieldsInfos = _fields;
        methodsInfos = _methods;
        constructorsInfos = _constructors;
        category = _category;
        abstractType = true;
        staticType = _staticType;
        finalType = false;
        access = _access;
    }
    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }
    public boolean isEnum() {
        return category == ClassCategory.ENUM;
    }
    public String getVariableOwner() {
        return variableOwner;
    }
    public StringList getLowerBounds() {
        return lowerBounds;
    }
    public StringList getUpperBounds() {
        return upperBounds;
    }
    public CustList<ClassMetaInfo> getBounds(ContextEl _cont) {
        CustList<ClassMetaInfo> list_;
        list_ = new CustList<ClassMetaInfo>();
        String id_ = Templates.getIdFromAllTypes(variableOwner);
        CustList<TypeVar> vars_;
        GeneType g_ = _cont.getClassBody(id_);
        if (g_ == null) {
            return list_;
        }
        vars_ = g_.getParamTypesMapValues();
        String varName_ = name.substring(Templates.PREFIX_VAR_TYPE.length());
        for (TypeVar b: vars_) {
            if (!StringList.quickEq(b.getName(), varName_)) {
                continue;
            }
            for (String u: b.getConstraints()) {
                list_.add(_cont.getExtendedClassMetaInfo(u, variableOwner));
            }
        }
        return list_;
    }
    public CustList<ClassMetaInfo> getTypeParameters(ContextEl _cont) {
        CustList<ClassMetaInfo> list_;
        list_ = new CustList<ClassMetaInfo>();
        String id_ = Templates.getIdFromAllTypes(name);
        GeneType g_ = _cont.getClassBody(id_);
        CustList<TypeVar> vars_;
        vars_ = g_.getParamTypesMapValues();
        StringList upperBounds_ = new StringList();
        StringList lowerBounds_ = new StringList();
        for (TypeVar b: vars_) {
            String pref_ = StringList.concat(Templates.PREFIX_VAR_TYPE, b.getName());
            list_.add(new ClassMetaInfo(pref_, ClassCategory.VARIABLE, upperBounds_, lowerBounds_, name, g_.getAccess()));
        }
        return list_;
    }
    public AccessEnum getAccess() {
        return access;
    }
    public boolean isPublic() {
        return access == AccessEnum.PUBLIC;
    }
    
    public boolean isProtected() {
        return access == AccessEnum.PROTECTED;
    }
    
    public boolean isPackage() {
        return access == AccessEnum.PACKAGE;
    }

    public boolean isPrivate() {
        return access == AccessEnum.PRIVATE;
    }
    public boolean isTypeArray() {
        return category == ClassCategory.ARRAY;
    }
    public boolean isTypeEnum() {
        return category == ClassCategory.ENUM;
    }
    public boolean isTypeWildCard() {
        return category == ClassCategory.WILD_CARD;
    }
    public boolean isTypeClass() {
        return category == ClassCategory.CLASS;
    }
    public boolean isTypeInterface() {
        return category == ClassCategory.INTERFACE;
    }
    public boolean isTypePrimitive() {
        return category == ClassCategory.PRIMITIVE;
    }

    public boolean isTypeVariable() {
        return category == ClassCategory.VARIABLE;
    }
    public boolean isTypeAnnotation() {
        return category == ClassCategory.ANNOTATION;
    }
    public String getName() {
        return name;
    }

    public String getSuperClass() {
        return superClass;
    }

    public StringList getMemberTypes() {
        return memberTypes;
    }

    public String getTypeOwner() {
        return typeOwner;
    }

    public StringMap<FieldMetaInfo> getFieldsInfos() {
        return fieldsInfos;
    }
    public ObjectNotNullMap<MethodId, MethodMetaInfo> getMethodsInfos() {
        return methodsInfos;
    }

    public ObjectNotNullMap<ConstructorId, ConstructorMetaInfo> getConstructorsInfos() {
        return constructorsInfos;
    }

    public boolean isAbstractType() {
        return abstractType;
    }

    public boolean isStaticType() {
        return staticType;
    }

    public boolean isFinalType() {
        return finalType;
    }

    public StringList getSuperInterfaces() {
        return new StringList(superInterfaces);
    }


    @Override
    public String getClassName(ExecutableCode _contextEl) {
        return _contextEl.getStandards().getAliasClass();
    }

    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof ClassMetaInfo)) {
            return false;
        }
        ClassMetaInfo info_ = (ClassMetaInfo) _other;
        if (!StringList.quickEq(variableOwner, info_.variableOwner)) {
            return false;
        }
        return StringList.quickEq(name, info_.name);
    }

    @Override
    public StringStruct exportValue() {
        return new StringStruct(getName());
    }
}
