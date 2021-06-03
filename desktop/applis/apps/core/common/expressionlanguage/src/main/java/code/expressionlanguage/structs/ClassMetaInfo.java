package code.expressionlanguage.structs;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.AbstractReplacingType;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.opers.ExecCastOperation;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ExecTypeVar;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.exec.ClassCategory;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.stds.*;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class ClassMetaInfo extends AbsAnnotatedStruct implements AnnotatedStruct,AnaDisplayableStruct {

    private static final String EMPTY_STRING = "";

    private final String name;

    private String superClass = EMPTY_STRING;

    private final StringList superInterfaces = new StringList();
    private final StringList memberTypes = new StringList();
    private final StringList upperBounds = new StringList();
    private final StringList lowerBounds = new StringList();
    private String typeOwner = EMPTY_STRING;

    private final CustList<FieldMetaInfo> fieldsInfos = new CustList<FieldMetaInfo>();
    private final CustList<MethodMetaInfo> explicitsInfos = new CustList<MethodMetaInfo>();
    private final CustList<MethodMetaInfo> implicitsInfos = new CustList<MethodMetaInfo>();
    private final CustList<MethodMetaInfo> truesInfos = new CustList<MethodMetaInfo>();
    private final CustList<MethodMetaInfo> falsesInfos = new CustList<MethodMetaInfo>();
    private final CustList<MethodMetaInfo> methodsInfos = new CustList<MethodMetaInfo>();
    private final CustList<MethodMetaInfo> blocsInfos = new CustList<MethodMetaInfo>();

    private final CustList<ConstructorMetaInfo> constructorsInfos = new CustList<ConstructorMetaInfo>();

    private ClassCategory category;

    private boolean abstractType;

    private boolean finalType;
    private boolean staticType;

    private final String variableOwner;
    private AccessEnum access;
    private String fileName = EMPTY_STRING;
    private ExecRootBlock rootBlock;
    private ExecFormattedRootBlock formatted;
    public ClassMetaInfo(String _name) {
        name = StringUtil.nullToEmpty(_name);
        variableOwner = "";
        formatted = new ExecFormattedRootBlock((ExecRootBlock)null,_name);
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
        category = _cat;
        finalType = true;
        formatted = new ExecFormattedRootBlock((ExecRootBlock)null,_name);
    }
    public ClassMetaInfo() {
        name = "";
        variableOwner = "";
        staticType = true;
        typeOwner = EMPTY_STRING;
        abstractType = true;
        superClass = EMPTY_STRING;
        access = AccessEnum.PUBLIC;
        category = ClassCategory.VOID;
        finalType = true;
        formatted = ExecFormattedRootBlock.defValue();
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
        category = _cat;
        finalType = true;
        staticType = true;
        formatted = new ExecFormattedRootBlock((ExecRootBlock)null,_name);
    }
    public ClassMetaInfo(ExecFormattedRootBlock _formatted, ContextEl _context){
        variableOwner = "";
        ExecRootBlock type_ = _formatted.getRootBlock();
        name = _formatted.getFormatted();
        fetchInners(_formatted);
        fetchFields(_formatted, _context);
        fetchMethods(_formatted, _context);
        feed(_formatted, _context);
        fetchInfosBlock(_formatted, _context);
        fetchCtors(_formatted, _context);
        fileName = type_.getFile().getFileName();
        formatted = _formatted;
        ExecRootBlock par_ = type_.getParentType();
        String format_;
        if (par_ != null) {
            String gene_ = par_.getGenericString();
            if (ExecInherits.correctNbParameters(name, _context)) {
                format_ = ExecInherits.quickFormat(_formatted, gene_);
            } else {
                format_ = par_.getFullName();
            }
        } else {
            format_ = "";
        }
        formatted(_formatted);
        access = type_.getAccess();
        staticType = type_.withoutInstance();
        typeOwner = StringUtil.nullToEmpty(format_);
        if (type_ instanceof ExecInterfaceBlock) {
            superInterfaces.addAllElts(type_.getImportedDirectGenericSuperInterfaces());
            category = ClassCategory.INTERFACE;
            return;
        }
        if (type_ instanceof ExecAnnotationBlock) {
            category = ClassCategory.ANNOTATION;
            return;
        }
        ClassCategory cat_ = ClassCategory.CLASS;
        if (type_ instanceof ExecEnumBlock) {
            cat_ = ClassCategory.ENUM;
        }
        boolean abs_ = true;
        boolean final_ = true;
        if (type_ instanceof ExecClassBlock) {
            abs_ = ((ExecClassBlock)type_).isAbstractType();
            final_ = ((ExecClassBlock)type_).isFinalType();
        } else if (type_ instanceof ExecRecordBlock) {
            abs_ = false;
            if (((ExecRecordBlock)type_).isMutable()) {
                cat_ = ClassCategory.SPE_MU_CLASS;
            } else {
                cat_ = ClassCategory.SPE_CLASS;
            }
        }
        abstractType = abs_;
        finalType = final_;
        category = cat_;
        superClass = StringUtil.nullToEmpty(type_.getImportedDirectGenericSuperClass());
        superInterfaces.addAllElts(type_.getImportedDirectGenericSuperInterfaces());
    }
    public ClassMetaInfo(ContextEl _context, StandardType _type, String _name){
        name = _name;
        variableOwner = "";
        ExecFormattedRootBlock formatted_ = new ExecFormattedRootBlock((ExecRootBlock) null, _name);
        initStdFields(_type, formatted_);
        initStdMethods(_type, formatted_);
        initStdCtors(_context, _type, formatted_);
        staticType = _type.withoutInstance();
        access = AccessEnum.PUBLIC;
        typeOwner = "";
        if (_type instanceof StandardInterface) {
            category = ClassCategory.INTERFACE;
            superInterfaces.addAllElts(_type.getDirectInterfaces());
            return;
        }
        category = ClassCategory.CLASS;
        abstractType = ((StandardClass) _type).isAbstractStdType();
        finalType = ((StandardClass) _type).isFinalStdType();
        superClass = StringUtil.nullToEmpty(((StandardClass) _type).getSuperClass());
        superInterfaces.addAllElts(_type.getDirectInterfaces());
    }

    private void initStdFields(StandardType _type, ExecFormattedRootBlock _formatted) {
        for (StandardField f: _type.getFields()) {
            String ret_ = f.getImportedClassName();
            String decl_ = _type.getFullName();
            FieldMetaInfo met_ = new FieldMetaInfo(f.getFieldName(), ret_, decl_, _formatted);
            fieldsInfos.add(met_);
        }
    }

    private void initStdMethods(StandardType _type, ExecFormattedRootBlock _formatted) {
        for (StandardMethod m: _type.getMethods()) {
            String decl_ = _type.getFullName();
            methodsInfos.add(new MethodMetaInfo(m, decl_, _formatted));
        }
    }

    private void initStdCtors(ContextEl _context, StandardType _typeStd, ExecFormattedRootBlock _formatted) {
        String name_ = _formatted.getFormatted();
        for (StandardConstructor d: _typeStd.getConstructors()) {
            constructorsInfos.add(new ConstructorMetaInfo(_context, _typeStd,d, name_, _formatted));
        }
        if (constructorsInfos.isEmpty()) {
            constructorsInfos.add(new ConstructorMetaInfo(_context, _typeStd,null, name_, _formatted));
        }
    }

    private void fetchMethods(ExecFormattedRootBlock _formatted, ContextEl _context) {
        ExecRootBlock type_ = _formatted.getRootBlock();
        for (ExecMemberCallingsBlock b: type_.getAllFct()) {
            if (b instanceof ExecOverridableBlock) {
                ExecOverridableBlock method_ = (ExecOverridableBlock) b;
                MethodMetaInfo met_ = new MethodMetaInfo(_formatted, _context, method_, method_.getKind() == ExecMethodKind.EXPLICIT_CAST || method_.getKind() == ExecMethodKind.IMPLICIT_CAST
                        || method_.getKind() == ExecMethodKind.TRUE_OPERATOR  || method_.getKind() == ExecMethodKind.FALSE_OPERATOR);
                methodsInfos.add(met_);
            }
            if (b instanceof ExecInitBlock) {
                ExecInitBlock method_ = (ExecInitBlock) b;
                MethodMetaInfo met_ = new MethodMetaInfo(_formatted, _context,method_);
                methodsInfos.add(met_);
            }
            if (b instanceof ExecAnnotationMethodBlock) {
                ExecAnnotationMethodBlock method_ = (ExecAnnotationMethodBlock) b;
                MethodMetaInfo met_ = new MethodMetaInfo(_formatted, method_);
                methodsInfos.add(met_);
            }
        }
        if (type_ instanceof ExecEnumBlock) {
            String valueOf_ = _context.getStandards().getContent().getPredefTypes().getAliasEnumPredValueOf();
            String values_ = _context.getStandards().getContent().getPredefTypes().getAliasEnumValues();
            String string_ = _context.getStandards().getContent().getCharSeq().getAliasString();
            MethodId id_ = new MethodId(MethodAccessKind.STATIC, valueOf_, new StringList(string_));
            String ret_ = type_.getWildCardString();
            MethodMetaInfo met_ = new MethodMetaInfo(_formatted, id_, ret_);
            methodsInfos.add(met_);
            id_ = new MethodId(MethodAccessKind.STATIC, values_, new StringList());
            ret_ = StringExpUtil.getPrettyArrayType(ret_);
            met_ = new MethodMetaInfo(_formatted, id_, ret_);
            methodsInfos.add(met_);
        }
    }

    private void feed(ExecFormattedRootBlock _formatted, ContextEl _context) {
        ExecRootBlock type_ = _formatted.getRootBlock();
        for (ExecMemberCallingsBlock b: type_.getAllFct()) {
            if (b instanceof ExecOverridableBlock) {
                ExecOverridableBlock method_ = (ExecOverridableBlock) b;
                MethodMetaInfo met_;
                if (method_.getKind() == ExecMethodKind.EXPLICIT_CAST) {
                    met_ = new MethodMetaInfo(_formatted, _context, method_, true);
                    explicitsInfos.add(met_);
                }
                if (method_.getKind() == ExecMethodKind.IMPLICIT_CAST) {
                    met_ = new MethodMetaInfo(_formatted, _context, method_, true);
                    implicitsInfos.add(met_);
                }
                if (method_.getKind() == ExecMethodKind.TRUE_OPERATOR) {
                    met_ = new MethodMetaInfo(_formatted, _context, method_, true);
                    truesInfos.add(met_);
                }
                if (method_.getKind() == ExecMethodKind.FALSE_OPERATOR) {
                    met_ = new MethodMetaInfo(_formatted, _context, method_, true);
                    falsesInfos.add(met_);
                }
            }
        }
    }

    private void fetchInfosBlock(ExecFormattedRootBlock _formatted, ContextEl _context) {
        ExecRootBlock type_ = _formatted.getRootBlock();
        for (ExecMemberCallingsBlock b: type_.getAllFct()) {
            if (b instanceof ExecInitBlock) {
                ExecInitBlock method_ = (ExecInitBlock) b;
                MethodMetaInfo met_ = new MethodMetaInfo(_formatted, _context,method_);
                blocsInfos.add(met_);
            }
        }
    }

    private void fetchCtors(ExecFormattedRootBlock _formatted, ContextEl _context) {
        ExecRootBlock type_ = _formatted.getRootBlock();
        for (ExecMemberCallingsBlock b: type_.getAllFct()) {
            if (b instanceof ExecConstructorBlock) {
                ExecConstructorBlock method_ = (ExecConstructorBlock) b;
                constructorsInfos.add(new ConstructorMetaInfo(_formatted, method_, _context));
            }
        }
        if (constructorsInfos.isEmpty()) {
            constructorsInfos.add(new ConstructorMetaInfo(_formatted, null, _context));
        }
    }

    private void fetchFields(ExecFormattedRootBlock _formatted, ContextEl _context) {
        ExecRootBlock type_ = _formatted.getRootBlock();
        String name_ = _formatted.getFormatted();
        for (ExecInfoBlock b: type_.getAllFields()) {

            for (String f: b.getFieldName()) {
                FieldMetaInfo met_ = new FieldMetaInfo(_context, b, name_, f, _formatted);
                fieldsInfos.add(met_);
            }
        }
    }

    private void fetchInners(ExecFormattedRootBlock _formatted) {
        ExecRootBlock type_ = _formatted.getRootBlock();
        for (ExecRootBlock b: type_.getChildrenTypes()) {
            memberTypes.add(b.getFullName());
        }
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
        _dest.formatted = _src.formatted;
        _dest.setOwner(_src.getOwner());
        _dest.blocsInfos.addAllElts(_src.blocsInfos);
    }

    public CustList<CustList<ExecOperationNode>> getAnnotationsOps(){
        if (rootBlock != null) {
            return rootBlock.getAnnotationsOps();
        }
        return new CustList<CustList<ExecOperationNode>>();
    }

    public void formatted(ExecFormattedRootBlock _rootBlock) {
        this.rootBlock = _rootBlock.getRootBlock();
        formatted = _rootBlock;
        setOwner(_rootBlock.getRootBlock());
    }

    @Override
    public String getFileName() {
        return fileName;
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
    public Struct tryWrap(ContextEl _cont, Struct _input) {
        Argument arg_ = new Argument(_input);
        ExecCastOperation.wrapFct(name,true,_cont,arg_);
        return arg_.getStruct();
    }
    public StringList getBounds(ContextEl _cont) {
        StringList list_ = new StringList();
        String id_ = StringExpUtil.getIdFromAllTypes(variableOwner);
        ExecRootBlock g_ = _cont.getClasses().getClassBody(id_);
        String varName_ = "";
        if (!name.startsWith(AbstractReplacingType.PREFIX_VAR_TYPE_STR)) {
            g_ = null;
        } else {
            varName_ = name.substring(AbstractReplacingType.PREFIX_VAR_TYPE_STR.length());
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
            String pref_ = StringUtil.concat(AbstractReplacingType.PREFIX_VAR_TYPE_STR, b.getName());
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
        return name.contains(AbstractReplacingType.PREFIX_VAR_TYPE_STR);
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
        if (rootBlock instanceof ExecInfoBlock) {
            return ((ExecInfoBlock)rootBlock).getAnonymousLambda();
        }
        if (rootBlock != null) {
            return rootBlock.getAnonymousRootLambda();
        }
        return new CustList<ExecAnonymousFunctionBlock>();
    }

    @Override
    public CustList<ExecAbstractSwitchMethod> getSwitchMethods() {
        if (rootBlock instanceof ExecInfoBlock) {
            return ((ExecInfoBlock)rootBlock).getSwitchMethods();
        }
        if (rootBlock != null) {
            return rootBlock.getSwitchMethodsRoot();
        }
        return new CustList<ExecAbstractSwitchMethod>();
    }

    @Override
    public ExecFormattedRootBlock getFormatted() {
        return formatted;
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

    @Override
    public long randCode() {
        long r_ = NumParsers.mergeRandCode(1,NumParsers.randCode(variableOwner));
        r_ = NumParsers.mergeRandCode(r_,NumParsers.randCode(name));
        return r_;
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
