package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecAbstractSwitchMethod;
import code.expressionlanguage.exec.blocks.ExecAnnotableBlock;
import code.expressionlanguage.exec.blocks.ExecAnonymousFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.util.ExecTypeVar;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.exec.ClassCategory;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class ClassMetaInfo extends WithoutParentStruct implements AnnotatedStruct,AnaDisplayableStruct {

    private static final String EMPTY_STRING = "";

    private final String name;

    private String superClass = EMPTY_STRING;

    private final StringList superInterfaces = new StringList();
    private final StringList memberTypes = new StringList();
    private final StringList upperBounds = new StringList();
    private final StringList lowerBounds = new StringList();
    private String typeOwner = EMPTY_STRING;

    private final CustList<FieldMetaInfo> fieldsInfos;
    private final CustList<MethodMetaInfo> explicitsInfos;
    private final CustList<MethodMetaInfo> implicitsInfos;
    private final CustList<MethodMetaInfo> truesInfos;
    private final CustList<MethodMetaInfo> falsesInfos;
    private final CustList<MethodMetaInfo> methodsInfos;
    private final CustList<MethodMetaInfo> blocsInfos = new CustList<MethodMetaInfo>();

    private final CustList<ConstructorMetaInfo> constructorsInfos;

    private ClassCategory category;

    private boolean abstractType;

    private boolean finalType;
    private boolean staticType;

    private final String variableOwner;
    private AccessEnum access;
    private String fileName = EMPTY_STRING;
    private ExecRootBlock rootBlock;
    public ClassMetaInfo(String _name) {
        name = StringUtil.nullToEmpty(_name);
        variableOwner = "";
        fieldsInfos = new CustList<FieldMetaInfo>();
        explicitsInfos = new CustList<MethodMetaInfo>();
        implicitsInfos = new CustList< MethodMetaInfo>();
        truesInfos = new CustList<MethodMetaInfo>();
        falsesInfos = new CustList<MethodMetaInfo>();
        methodsInfos = new CustList<MethodMetaInfo>();
        constructorsInfos = new CustList<ConstructorMetaInfo>();
    }
    public ClassMetaInfo(String _name, ContextEl _context, ClassCategory _cat, String _variableOwner) {
        name = StringUtil.nullToEmpty(_name);
        variableOwner = StringUtil.nullToEmpty(_variableOwner);
        staticType = true;
        typeOwner = EMPTY_STRING;
        if (_cat == ClassCategory.ARRAY) {
            String id_ = StringExpUtil.getIdFromAllTypes(name);
            String comp_ = StringExpUtil.getQuickComponentBaseType(id_).getComponent();
            if (ExecClassArgumentMatching.isPrimitive(comp_, _context)) {
                abstractType = true;
                superClass = EMPTY_STRING;
                access = AccessEnum.PUBLIC;
            } else {
                ExecRootBlock g_ = _context.getClasses().getClassBody(comp_);
                if (g_ == null) {
                    access = AccessEnum.PUBLIC;
                } else {
                    access = g_.getAccess();
                }
                abstractType = false;
                superClass = _context.getStandards().getContent().getCoreNames().getAliasObject();
            }
        } else {
            abstractType = true;
            superClass = EMPTY_STRING;
            access = AccessEnum.PUBLIC;
        }
        fieldsInfos = new CustList<FieldMetaInfo>();
        explicitsInfos = new CustList<MethodMetaInfo>();
        implicitsInfos = new CustList<MethodMetaInfo>();
        truesInfos = new CustList<MethodMetaInfo>();
        falsesInfos = new CustList<MethodMetaInfo>();
        methodsInfos = new CustList<MethodMetaInfo>();
        constructorsInfos = new CustList<ConstructorMetaInfo>();
        category = _cat;
        finalType = true;
    }
    public ClassMetaInfo() {
        name = "";
        variableOwner = "";
        staticType = true;
        typeOwner = EMPTY_STRING;
        abstractType = true;
        superClass = EMPTY_STRING;
        access = AccessEnum.PUBLIC;
        fieldsInfos = new CustList<FieldMetaInfo>();
        explicitsInfos = new CustList<MethodMetaInfo>();
        implicitsInfos = new CustList<MethodMetaInfo>();
        truesInfos = new CustList<MethodMetaInfo>();
        falsesInfos = new CustList<MethodMetaInfo>();
        methodsInfos = new CustList<MethodMetaInfo>();
        constructorsInfos = new CustList<ConstructorMetaInfo>();
        category = ClassCategory.VOID;
        finalType = true;
    }
    public ClassMetaInfo(String _name, ClassCategory _cat, StringList _upperBounds, StringList _lowerBounds, String _variableOwner, AccessEnum _access) {
        name = StringUtil.nullToEmpty(_name);
        upperBounds.addAllElts(_upperBounds);
        lowerBounds.addAllElts(_lowerBounds);
        access = _access;
        abstractType = true;
        typeOwner = EMPTY_STRING;
        superClass = EMPTY_STRING;
        variableOwner = StringUtil.nullToEmpty(_variableOwner);
        fieldsInfos = new CustList<FieldMetaInfo>();
        explicitsInfos = new CustList<MethodMetaInfo>();
        implicitsInfos = new CustList<MethodMetaInfo>();
        truesInfos = new CustList<MethodMetaInfo>();
        falsesInfos = new CustList<MethodMetaInfo>();
        methodsInfos = new CustList<MethodMetaInfo>();
        constructorsInfos = new CustList<ConstructorMetaInfo>();
        category = _cat;
        finalType = true;
        staticType = true;
    }
    public ClassMetaInfo(String _name,
            String _superClass,
            StringList _superInterfaces,
            String _typeOwner,
            StringList _memberTypes,
                         CustList<FieldMetaInfo> _fields,
                         CustList<MethodMetaInfo> _exlicits,
                         CustList<MethodMetaInfo> _imlicits,
                         CustList<MethodMetaInfo> _trues,
                         CustList<MethodMetaInfo> _falses,
                         CustList<MethodMetaInfo> _methods,
                         CustList<ConstructorMetaInfo> _constructors,
            ClassCategory _category,
            boolean _abstractType,
            boolean _staticType,
            boolean _finalType, AccessEnum _access) {
        variableOwner = "";
        memberTypes.addAllElts(_memberTypes);
        typeOwner = StringUtil.nullToEmpty(_typeOwner);
        name = StringUtil.nullToEmpty(_name);
        superClass = StringUtil.nullToEmpty(_superClass);
        superInterfaces.addAllElts(_superInterfaces);
        fieldsInfos = _fields;
        explicitsInfos = _exlicits;
        implicitsInfos = _imlicits;
        truesInfos = _trues;
        falsesInfos = _falses;
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
            StringList _memberTypes,CustList<FieldMetaInfo> _fields,
                         CustList<MethodMetaInfo> _exlicits,
                         CustList<MethodMetaInfo> _imlicits,
                         CustList<MethodMetaInfo> _trues,
                         CustList<MethodMetaInfo> _falses,
                         CustList<MethodMetaInfo> _methods,
                         CustList<ConstructorMetaInfo> _constructors,
            ClassCategory _category, boolean _staticType, AccessEnum _access) {
        variableOwner = "";
        typeOwner = StringUtil.nullToEmpty(_typeOwner);
        memberTypes.addAllElts(_memberTypes);
        name = StringUtil.nullToEmpty(_name);
        superInterfaces.addAllElts(_superInterfaces);
        superClass = EMPTY_STRING;
        fieldsInfos = _fields;
        explicitsInfos = _exlicits;
        implicitsInfos = _imlicits;
        truesInfos = _trues;
        falsesInfos = _falses;
        methodsInfos = _methods;
        constructorsInfos = _constructors;
        category = _category;
        abstractType = true;
        staticType = _staticType;
        finalType = false;
        access = _access;
    }
    public static void forward(ClassMetaInfo _src, ClassMetaInfo _dest) {
        _dest.category = _src.category;
        _dest.access = _src.access;
        _dest.abstractType = _src.abstractType;
        _dest.finalType = _src.finalType;
        _dest.staticType = _src.staticType;
        _dest.superClass = _src.superClass;
        _dest.superInterfaces.addAllElts(_src.superInterfaces);
        _dest.memberTypes.addAllElts(_src.memberTypes);
        _dest.typeOwner = _src.typeOwner;
        _dest.fieldsInfos.addAllElts(_src.fieldsInfos);
        _dest.methodsInfos.addAllElts(_src.methodsInfos);
        _dest.constructorsInfos.addAllElts(_src.constructorsInfos);
        _dest.falsesInfos.addAllElts(_src.falsesInfos);
        _dest.truesInfos.addAllElts(_src.truesInfos);
        _dest.implicitsInfos.addAllElts(_src.implicitsInfos);
        _dest.explicitsInfos.addAllElts(_src.explicitsInfos);
        _dest.fileName = _src.fileName;
        _dest.rootBlock = _src.rootBlock;
        _dest.blocsInfos.addAllElts(_src.blocsInfos);
    }

    public ExecAnnotableBlock getAnnotableBlock() {
        return rootBlock;
    }

    public ExecRootBlock getRootBlock() {
        return rootBlock;
    }

    public void setRootBlock(ExecRootBlock _rootBlock) {
        this.rootBlock = _rootBlock;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String _fileName) {
        fileName = StringUtil.nullToEmpty(_fileName);
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
    public StringList getBounds(ContextEl _cont) {
        StringList list_ = new StringList();
        String id_ = StringExpUtil.getIdFromAllTypes(variableOwner);
        ExecRootBlock g_ = _cont.getClasses().getClassBody(id_);
        String varName_ = "";
        if (!name.startsWith(Templates.PREFIX_VAR_TYPE)) {
            g_ = null;
        } else {
            varName_ = name.substring(Templates.PREFIX_VAR_TYPE.length());
        }
        if (g_ == null) {
            return list_;
        }
        CustList<ExecTypeVar> vars_ = g_.getParamTypesMapValues();
        for (ExecTypeVar b: vars_) {
            if (!StringUtil.quickEq(b.getName(), varName_)) {
                continue;
            }
            list_.addAllElts(b.getConstraints());
        }
        return list_;
    }

    public CustList<ClassMetaInfo> getTypeParameters(ContextEl _cont) {
        CustList<ClassMetaInfo> list_;
        list_ = new CustList<ClassMetaInfo>();
        String id_ = StringExpUtil.getIdFromAllTypes(name);
        ExecRootBlock g_ = _cont.getClasses().getClassBody(id_);
        if (g_ == null) {
            return list_;
        }
        CustList<ExecTypeVar> vars_;
        vars_ = g_.getParamTypesMapValues();
        StringList upperBounds_ = new StringList();
        StringList lowerBounds_ = new StringList();
        for (ExecTypeVar b: vars_) {
            String pref_ = StringUtil.concat(Templates.PREFIX_VAR_TYPE, b.getName());
            list_.add(new ClassMetaInfo(pref_, ClassCategory.VARIABLE, upperBounds_, lowerBounds_, name, g_.getAccess()));
        }
        return list_;
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
    public boolean isRefType() {
        return category == ClassCategory.REF_TYPE;
    }
    public boolean isTypeClass() {
        return category == ClassCategory.CLASS;
    }
    public boolean isTypeSpeClass() {
        return category == ClassCategory.SPE_CLASS;
    }
    public boolean isTypeSpeMuClass() {
        return category == ClassCategory.SPE_MU_CLASS;
    }
    public boolean isTypeInterface() {
        return category == ClassCategory.INTERFACE;
    }
    public boolean isTypePrimitive() {
        return category == ClassCategory.PRIMITIVE;
    }

    public boolean isVariable() {
        return name.contains(Templates.PREFIX_VAR_TYPE);
    }

    public boolean isTypeVariable() {
        return category == ClassCategory.VARIABLE;
    }
    public boolean isTypeAnnotation() {
        return category == ClassCategory.ANNOTATION;
    }
    public boolean isTypeVoid() {
        return category == ClassCategory.VOID;
    }

    public ClassCategory getCategory() {
        return category;
    }

    @Override
    public CustList<ExecAnonymousFunctionBlock> getAnonymousLambda() {
        if (rootBlock != null) {
            return rootBlock.getAnonymousRootLambda();
        }
        return new CustList<ExecAnonymousFunctionBlock>();
    }

    @Override
    public CustList<ExecAbstractSwitchMethod> getSwitchMethods() {
        if (rootBlock != null) {
            return rootBlock.getSwitchMethods();
        }
        return new CustList<ExecAbstractSwitchMethod>();
    }

    @Override
    public String getDeclaringClass() {
        return getName();
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

    public CustList<FieldMetaInfo> getFieldsInfos() {
        return fieldsInfos;
    }

    public CustList<MethodMetaInfo> getExplicitsInfos() {
        return explicitsInfos;
    }

    public CustList<MethodMetaInfo> getImplicitsInfos() {
        return implicitsInfos;
    }

    public CustList<MethodMetaInfo> getTruesInfos() {
        return truesInfos;
    }

    public CustList<MethodMetaInfo> getFalsesInfos() {
        return falsesInfos;
    }

    public CustList<MethodMetaInfo> getMethodsInfos() {
        return methodsInfos;
    }

    public CustList<ConstructorMetaInfo> getConstructorsInfos() {
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
    public String getClassName(ContextEl _contextEl) {
        return _contextEl.getStandards().getContent().getReflect().getAliasClassType();
    }

    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof ClassMetaInfo)) {
            return false;
        }
        ClassMetaInfo info_ = (ClassMetaInfo) _other;
        if (!StringUtil.quickEq(variableOwner, info_.variableOwner)) {
            return false;
        }
        return StringUtil.quickEq(name, info_.name);
    }


    public StringStruct exportValue() {
        return getDisplayedString();
    }

    public CustList<MethodMetaInfo> getBlocsInfos() {
        return blocsInfos;
    }

    @Override
    public StringStruct getDisplayedString(ContextEl _an) {
        return getDisplayedString();
    }

    @Override
    public StringStruct getDisplayedString(AnalyzedPageEl _an) {
        return getDisplayedString();
    }

    private StringStruct getDisplayedString() {
        if (variableOwner.isEmpty()) {
            return new StringStruct(getName());
        }
        return new StringStruct(StringUtil.concat(variableOwner,";",getName()));
    }
}
