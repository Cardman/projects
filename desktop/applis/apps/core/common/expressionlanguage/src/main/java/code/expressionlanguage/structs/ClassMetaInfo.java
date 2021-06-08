package code.expressionlanguage.structs;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.ClassNameCmp;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecCastOperation;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ExecTypeVar;
import code.expressionlanguage.exec.ClassCategory;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.stds.*;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.core.StringUtil;

public final class ClassMetaInfo extends AbsAnnotatedStruct implements AnaDisplayableStruct {

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

    private final ExecFormattedRootBlock variableOwner;
    private ExecFormattedRootBlock formatted = ExecFormattedRootBlock.defValue();
    public ClassMetaInfo(String _name) {
        super(AccessEnum.PUBLIC,"");
        name = StringUtil.nullToEmpty(_name);
        variableOwner = ExecFormattedRootBlock.defValue();
        formatted = new ExecFormattedRootBlock((ExecRootBlock)null,_name);
    }
    public ClassMetaInfo(AccessEnum _acc, String _name, ExecFormattedRootBlock _variableOwner, String _aliasObject, ClassCategory _array) {
        super(_acc,"");
        name = StringUtil.nullToEmpty(_name);
        variableOwner = _variableOwner;
        staticType = true;
        typeOwner = EMPTY_STRING;
        abstractType = false;
        superClass = _aliasObject;
        category = _array;
        finalType = true;
        formatted = new ExecFormattedRootBlock((ExecRootBlock)null,_name);
    }
    public ClassMetaInfo(String _name, ClassCategory _cat, ExecFormattedRootBlock _variableOwner,String _superClass) {
        super(AccessEnum.PUBLIC,"");
        name = StringUtil.nullToEmpty(_name);
        variableOwner = _variableOwner;
        staticType = true;
        typeOwner = EMPTY_STRING;
        abstractType = true;
        superClass = _superClass;
        category = _cat;
        finalType = true;
        formatted = new ExecFormattedRootBlock((ExecRootBlock)null,_name);
    }
    public ClassMetaInfo() {
        super(AccessEnum.PUBLIC,"");
        name = "";
        variableOwner = ExecFormattedRootBlock.defValue();
        staticType = true;
        typeOwner = EMPTY_STRING;
        abstractType = true;
        superClass = EMPTY_STRING;
        category = ClassCategory.VOID;
        finalType = true;
    }
    public ClassMetaInfo(String _name, ClassCategory _cat, StringList _upperBounds, StringList _lowerBounds, ExecFormattedRootBlock _variableOwner, AccessEnum _access) {
        super(_access,"");
        name = StringUtil.nullToEmpty(_name);
        upperBounds.addAllElts(_upperBounds);
        lowerBounds.addAllElts(_lowerBounds);
        abstractType = true;
        typeOwner = EMPTY_STRING;
        superClass = EMPTY_STRING;
        variableOwner = _variableOwner;
        category = _cat;
        finalType = true;
        staticType = true;
        formatted = new ExecFormattedRootBlock((ExecRootBlock)null,_name);
    }
    public ClassMetaInfo(ExecFormattedRootBlock _formatted, ContextEl _context){
        super(_formatted.getRootBlock().getAccess(),_formatted.getRootBlock().getFile().getFileName());
        variableOwner = ExecFormattedRootBlock.defValue();
        ExecRootBlock type_ = _formatted.getRootBlock();
        name = _formatted.getFormatted();
        fetchInners(_formatted);
        fetchFields(_formatted);
        fetchMethods(_formatted, _context);
        feed(_formatted, _context);
        fetchInfosBlock(_formatted, _context);
        fetchCtors(_formatted, _context);
        ExecRootBlock par_ = type_.getParentType();
        String format_;
        if (par_ != null) {
            String gene_ = par_.getGenericString();
            if (ExecInherits.correctNbParameters(_formatted.getFormatted(), _context)) {
                format_ = ExecInherits.quickFormat(_formatted, gene_);
            } else {
                format_ = par_.getFullName();
            }
        } else {
            format_ = "";
        }
        formatted = _formatted;
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
        super(AccessEnum.PUBLIC,"");
        name = _name;
        variableOwner = ExecFormattedRootBlock.defValue();
        ExecFormattedRootBlock formatted_ = new ExecFormattedRootBlock((ExecRootBlock) null, _name);
        formatted = formatted_;
        initStdFields(_type, formatted_);
        initStdMethods(_type, formatted_);
        initStdCtors(_context, _type, formatted_);
        staticType = _type.withoutInstance();
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
            FieldMetaInfo met_ = new FieldMetaInfo(f.getFieldName(), ret_, _formatted);
            fieldsInfos.add(met_);
        }
    }

    private void initStdMethods(StandardType _type, ExecFormattedRootBlock _formatted) {
        for (StandardMethod m: _type.getMethods()) {
            methodsInfos.add(new MethodMetaInfo(m, _formatted));
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

    private void fetchFields(ExecFormattedRootBlock _formatted) {
        ExecRootBlock type_ = _formatted.getRootBlock();
        for (ExecInfoBlock b: type_.getAllFields()) {

            for (String f: b.getFieldName()) {
                FieldMetaInfo met_ = new FieldMetaInfo(b, f, _formatted);
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
        _dest.setAccess(_src.getAccess());
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
        _dest.setFileName(_src.getFileName());
        _dest.formatted = _src.formatted;
        _dest.blocsInfos.addAllElts(_src.blocsInfos);
    }

    private static ClassMetaInfo getCustomClassMetaInfo(String _name, ContextEl _context) {
        String base_ = StringExpUtil.getIdFromAllTypes(_name);
        for (ExecRootBlock c: _context.getClasses().getClassBodies()) {
            String k_ = c.getFullName();
            if (!StringUtil.quickEq(k_, base_)) {
                continue;
            }
            return new ClassMetaInfo(new ExecFormattedRootBlock(c,_name), _context);
        }
        return new ClassMetaInfo(_context.getStandards().getContent().getCoreNames().getAliasVoid(), ClassCategory.VOID,ExecFormattedRootBlock.defValue(),"");
    }

    private static ClassMetaInfo getExtendedClassMetaInfoInit(ContextEl _context, String _name, ClassMetaInfo _classOwner) {
        return getExtendedClassMetaInfo(_context,_name,_classOwner.getFormatted());
    }

    private static ClassMetaInfo getExtendedClassMetaInfo(ContextEl _context, String _name) {
        return getExtendedClassMetaInfo(_context,_name,ExecFormattedRootBlock.defValue());
    }

    private static ClassMetaInfo getExtendedClassMetaInfo(ContextEl _context, String _name, ClassMetaInfo _classOwner) {
        return getExtendedClassMetaInfo(_context,_name, _classOwner.variableOwner);
    }

    public static ClassMetaInfo getExtendedClassMetaInfo(ContextEl _context, String _name, AnnotatedStruct _member) {
        return getExtendedClassMetaInfo(_context,_name,_member.getFormatted());
    }

    private static ClassMetaInfo getExtendedClassMetaInfo(ContextEl _context, String _name, ExecFormattedRootBlock _variableOwner) {
        if (StringUtil.quickEq(_name, StringExpUtil.SUB_TYPE)) {
            StringList upperBounds_ = new StringList();
            StringList lowerBounds_ = new StringList();
            return new ClassMetaInfo(_name, ClassCategory.WILD_CARD,upperBounds_, lowerBounds_, _variableOwner, AccessEnum.PUBLIC);
        }
        if (_name.startsWith(StringExpUtil.SUB_TYPE)) {
            StringList upperBounds_ = new StringList(_name.substring(StringExpUtil.SUB_TYPE.length()));
            StringList lowerBounds_ = new StringList();
            return new ClassMetaInfo(_name, ClassCategory.WILD_CARD,upperBounds_, lowerBounds_, _variableOwner, AccessEnum.PUBLIC);
        }
        if (_name.startsWith(StringExpUtil.SUP_TYPE)) {
            StringList upperBounds_ = new StringList();
            StringList lowerBounds_ = new StringList(_name.substring(StringExpUtil.SUB_TYPE.length()));
            return new ClassMetaInfo(_name, ClassCategory.WILD_CARD,upperBounds_, lowerBounds_, _variableOwner, AccessEnum.PUBLIC);
        }
        if (_name.startsWith(AbstractReplacingType.PREFIX_VAR_TYPE_STR)) {
            StringList upperBounds_ = new StringList();
            StringList lowerBounds_ = new StringList();
            return new ClassMetaInfo(_name, ClassCategory.VARIABLE,upperBounds_, lowerBounds_, _variableOwner, AccessEnum.PUBLIC);
        }
        if (_name.startsWith("~")) {
            StringList upperBounds_ = new StringList(_name.substring("~".length()));
            StringList lowerBounds_ = new StringList(_name.substring("~".length()));
            return new ClassMetaInfo(_name, ClassCategory.REF_TYPE,upperBounds_, lowerBounds_, _variableOwner, AccessEnum.PUBLIC);
        }
        if (_name.startsWith(AbstractReplacingType.ARR_BEG_STRING)&&_name.contains(AbstractReplacingType.PREFIX_VAR_TYPE_STR)) {
            return processArray(_name, _context, _variableOwner);
        }
        return getClassMetaInfo(_context,_name);
    }

    private static ClassMetaInfo processArray(String _name, ContextEl _context, ExecFormattedRootBlock _variableOwner) {
        String id_ = StringExpUtil.getIdFromAllTypes(_name);
        String comp_ = StringExpUtil.getQuickComponentBaseType(id_).getComponent();
        ExecRootBlock g_ = _context.getClasses().getClassBody(comp_);
        if (g_ == null) {
            return new ClassMetaInfo(AccessEnum.PUBLIC,_name, _variableOwner, _context.getStandards().getContent().getCoreNames().getAliasObject(), ClassCategory.ARRAY);
        }
        return new ClassMetaInfo(g_.getAccess(),_name, _variableOwner, _context.getStandards().getContent().getCoreNames().getAliasObject(), ClassCategory.ARRAY);
    }

    private static ClassMetaInfo getClassMetaInfoId(ContextEl _context, String _variableOwnerId) {
        return getClassMetaInfo(_context, _variableOwnerId);
    }

    private static ClassMetaInfo getClassMetaInfo(ContextEl _context, ExecFormattedRootBlock _variableOwner) {
        return getClassMetaInfo(_context, _variableOwner.getFormatted());
    }

    public static ClassMetaInfo getClassMetaInfo(ContextEl _context, String _name) {
        if (ExecClassArgumentMatching.isPrimitive(_name, _context)) {
            return new ClassMetaInfo(AccessEnum.PUBLIC,_name,ExecFormattedRootBlock.defValue(),"", ClassCategory.PRIMITIVE);
        }
        if (_name.startsWith(AbstractReplacingType.ARR_BEG_STRING)) {
            return processArray(_name, _context, ExecFormattedRootBlock.defValue());
        }
        String base_ = StringExpUtil.getIdFromAllTypes(_name);
        for (EntryCust<String, StandardType> c: _context.getStandards().getStandards().entryList()) {
            String k_ = c.getKey();
            if (!StringUtil.quickEq(k_, base_)) {
                continue;
            }
            StandardType clblock_ = c.getValue();
            return new ClassMetaInfo(_context, clblock_, _name);
        }
        return getCustomClassMetaInfo(_name, _context);
    }

    public static Struct makeArray(ContextEl _cont, ClassMetaInfo _cl) {
        if (_cl.isTypeVoid()) {
            return NullStruct.NULL_VALUE;
        }
        String baseWildCard_ = baseWildCard(_cont, _cl.formatted.getFormatted());
        String arrayName_ = StringExpUtil.getPrettyArrayType(baseWildCard_);
        return buildClassInfo(_cont, _cl, arrayName_);
    }

    public static Struct getComponentType(ContextEl _cont, ClassMetaInfo _cl) {
        String baseWildCard_ = baseWildCard(_cont, _cl.formatted.getFormatted());
        if (!baseWildCard_.startsWith(AbstractReplacingType.ARR_BEG_STRING)) {
            return NullStruct.NULL_VALUE;
        }
        String compName_ = baseWildCard_.substring(AbstractReplacingType.ARR_BEG_STRING.length());
        return buildClassInfo(_cont, _cl, compName_);
    }

    private static ClassMetaInfo buildClassInfo(ContextEl _cont, ClassMetaInfo _cl, String _typeName) {
        String clName_ = _cl.formatted.getFormatted();
        String pre_ = "";
        if (clName_.startsWith(StringExpUtil.SUB_TYPE)) {
            pre_ = StringExpUtil.SUB_TYPE;
        } else if (clName_.startsWith(StringExpUtil.SUP_TYPE)) {
            pre_ = StringExpUtil.SUP_TYPE;
        } else if (clName_.startsWith("~")) {
            pre_ = "~";
        }
        String fName_ = StringUtil.concat(pre_, _typeName);
        return getExtendedClassMetaInfo(_cont, fName_, _cl);
    }

    private static String baseWildCard(ContextEl _lgNames, String _clName) {
        String baseWildCard_;
        if (StringUtil.quickEq(_clName, StringExpUtil.SUB_TYPE)) {
            baseWildCard_ = _lgNames.getStandards().getContent().getCoreNames().getAliasObject();
        } else {
            baseWildCard_ = extractName(_clName);
        }
        return baseWildCard_;
    }

    public static Struct makeWildCard(ContextEl _cont, ClassMetaInfo _instanceClass, Struct _arg) {
        if (_instanceClass.isTypeVoid()) {
            return NullStruct.NULL_VALUE;
        }
        String nameCl_ = _instanceClass.formatted.getFormatted();
        if (!(_arg instanceof BooleanStruct)) {
            return getExtendedClassMetaInfo(_cont, StringExpUtil.SUB_TYPE, _instanceClass);
        }
        if (StringUtil.quickEq(nameCl_,StringExpUtil.SUB_TYPE)) {
            return getExtendedClassMetaInfo(_cont,StringExpUtil.SUB_TYPE, _instanceClass);
        }
        String baseWildCard_ = extractName(nameCl_);
        if (BooleanStruct.isTrue(_arg)) {
            return getExtendedClassMetaInfo(_cont, StringUtil.concat(StringExpUtil.SUB_TYPE,baseWildCard_), _instanceClass);
        }
        return getExtendedClassMetaInfo(_cont, StringUtil.concat(StringExpUtil.SUP_TYPE,baseWildCard_), _instanceClass);
    }

    public static Struct makeRef(ContextEl _cont, ClassMetaInfo _instanceClass, Struct[] _args) {
        if (_instanceClass.isTypeVoid()) {
            return NullStruct.NULL_VALUE;
        }
        String ext_ = extractName(_instanceClass.formatted.getFormatted());
        String cat_ = ext_;
        if (BooleanStruct.isTrue(_args[0])) {
            cat_ = "~"+ext_;
        }
        return getExtendedClassMetaInfo(_cont, cat_, _instanceClass);
    }

    private static String extractName(String _nameCl) {
        String pre_ = _nameCl;
        if (_nameCl.startsWith(StringExpUtil.SUB_TYPE)) {
            pre_ = _nameCl.substring(StringExpUtil.SUB_TYPE.length());
        } else if (_nameCl.startsWith(StringExpUtil.SUP_TYPE)) {
            pre_ = _nameCl.substring(StringExpUtil.SUP_TYPE.length());
        } else if (_nameCl.startsWith("~")) {
            pre_ = _nameCl.substring("~".length());
        }
        return pre_;
    }

    public static Struct superType(ContextEl _cont, ClassMetaInfo _cl, ExecFormattedRootBlock _nameType) {
        if (_cl.superClass.isEmpty()) {
            return NullStruct.NULL_VALUE;
        }
        String genericSuperClassName_ = tryFormatType(_cont, _nameType, _cl.superClass);
        return getExtendedClassMetaInfo(_cont,genericSuperClassName_,_cl);
    }

    public static CustList<ClassMetaInfo> getUpperWildCardBoundsList(ContextEl _cont, ClassMetaInfo _cl) {
        return getWildCardBoundsList(_cont, _cl, _cl.upperBounds);
    }
    public static CustList<ClassMetaInfo> getLowerWildCardBoundsList(ContextEl _cont, ClassMetaInfo _cl) {
        return getWildCardBoundsList(_cont, _cl, _cl.lowerBounds);
    }
    private static CustList<ClassMetaInfo> getWildCardBoundsList(ContextEl _cont, ClassMetaInfo _cl, StringList _bounds) {
        ExecFormattedRootBlock clName_ = _cl.variableOwner;
        if (clName_.getFormatted().isEmpty()) {
            return new CustList<ClassMetaInfo>(new CollCapacity(0));
        }
        return getFormattedClassesMetaList(_cont, _cl, _bounds, clName_);
    }
    public static CustList<ClassMetaInfo> getExtendedClassMetaInfoInit(ContextEl _cont, ClassMetaInfo _cl) {
        StringList types_ = StringExpUtil.getAllTypes(_cl.formatted.getFormatted());
        int len_ = types_.size();
        CustList<ClassMetaInfo> arr_ = new CustList<ClassMetaInfo>(new CollCapacity(len_-1));
        for (int i = 1; i < len_; i++) {
            String nameVar_ = types_.get(i);
            arr_.add(getExtendedClassMetaInfoInit(_cont,nameVar_, _cl));
        }
        return arr_;
    }
    public static CustList<ClassMetaInfo> getFormattedInterfacesMetaList(ContextEl _cont, ClassMetaInfo _cl, ExecFormattedRootBlock _clName) {
        return getFormattedClassesMetaList(_cont, _cl, _cl.superInterfaces, _clName);
    }
    private static CustList<ClassMetaInfo> getFormattedClassesMetaList(ContextEl _cont, ClassMetaInfo _cl, StringList _geneInterfaces, ExecFormattedRootBlock _clName) {
        int len_ = _geneInterfaces.size();
        CustList<ClassMetaInfo> arr_ = new CustList<ClassMetaInfo>(new CollCapacity(len_));
        for (int i = 0; i < len_; i++) {
            String nameVar_ = _geneInterfaces.get(i);
            nameVar_ = tryFormatType(_cont, _clName,nameVar_);
            arr_.add(getExtendedClassMetaInfo(_cont,nameVar_, _cl));
        }
        return arr_;
    }
    public static CustList<ClassMetaInfo> getMembersMetaList(ContextEl _cont, ClassMetaInfo _cl) {
        return getClassesMetaList(_cont, _cl, _cl.memberTypes);
    }
    private static CustList<ClassMetaInfo> getClassesMetaList(ContextEl _cont, ClassMetaInfo _cl, StringList _geneInterfaces) {
        int len_ = _geneInterfaces.size();
        CustList<ClassMetaInfo> arr_ = new CustList<ClassMetaInfo>(new CollCapacity(len_));
        for (int i = 0; i < len_; i++) {
            String nameVar_ = _geneInterfaces.get(i);
            arr_.add(getExtendedClassMetaInfo(_cont,nameVar_, _cl));
        }
        return arr_;
    }

    public static CustList<ClassMetaInfo> fetchGenericBoundsClassesMetaList(ContextEl _cont, ClassMetaInfo _cl) {
        StringList list_ = _cl.getBounds();
        return getFormattedClassesMetaList(_cont, _cl, list_, ExecFormattedRootBlock.defValue());
    }

    public static CustList<ClassMetaInfo> fetchBoundsClassesMetaList(ContextEl _cont, ClassMetaInfo _cl) {
        StringList list_ = _cl.getBounds();
        return getFormattedClassesMetaList(_cont, _cl, list_, _cl.variableOwner);
    }
    public static CustList<ClassMetaInfo> getParamsFct(boolean _vararg, ContextEl _cont, AnnotatedStruct _declaring, StringList _geneInterfaces) {
        int len_ = _geneInterfaces.size();
        CustList<ClassMetaInfo> list_ = new CustList<ClassMetaInfo>(new CollCapacity(len_));
        for (int i = 0; i < len_; i++) {
            String int_ = _geneInterfaces.get(i);
            if (_vararg && i+1 == len_) {
                int_ = StringExpUtil.getPrettyArrayType(int_);
            }
            list_.add(getExtendedClassMetaInfo(_cont,int_, _declaring));
        }
        return list_;
    }
    public static CustList<ClassMetaInfo> getTypes(ContextEl _cont, StringList _typesNames) {
        CustList<ClassMetaInfo> list_ = new CustList<ClassMetaInfo>(new CollCapacity(_typesNames.size()));
        for (String t: _typesNames) {
            list_.add(getExtendedClassMetaInfo(_cont,t));
        }
        return list_;
    }

    public static CustList<ClassMetaInfo> addTypes(ContextEl _cont) {
        CustList<ClassMetaInfo> classes_  = new CustList<ClassMetaInfo>();
        Classes classesInfo_ = _cont.getClasses();
        for (ExecRootBlock c: classesInfo_.getClassBodies()) {
            classes_.add(new ClassMetaInfo(new ExecFormattedRootBlock(c), _cont));
        }
        for (EntryCust<String, StandardType> c: _cont.getStandards().getStandards().entryList()) {
            String k_ = c.getKey();
            StandardType clblock_ = c.getValue();
            classes_.add(new ClassMetaInfo(_cont, clblock_, k_));
        }
        classes_.sortElts(new ClassNameCmp());
        return classes_;
    }
    public CustList<CustList<ExecOperationNode>> getAnnotationsOps(){
        ExecRootBlock rootBlock_ = formatted.getRootBlock();
        if (rootBlock_ != null) {
            return rootBlock_.getAnnotationsOps();
        }
        return new CustList<CustList<ExecOperationNode>>();
    }

    public Struct variableOwner(ContextEl _context) {
        if (variableOwner.getFormatted().isEmpty()) {
            return NullStruct.NULL_VALUE;
        }
        return getClassMetaInfo(_context, variableOwner);
    }

    public Struct variableOwnerId(ContextEl _context) {
        String id_ = getVariableOwnerId();
        if (id_.isEmpty()) {
            return NullStruct.NULL_VALUE;
        }
        return getClassMetaInfoId(_context, id_);
    }
    private String getVariableOwnerId() {
        return StringExpUtil.getIdFromAllTypes(variableOwner.getFormatted());
    }
    public static Struct getClassMetaInfo(ContextEl _context, Struct _inst) {
        if (_inst == NullStruct.NULL_VALUE) {
            return NullStruct.NULL_VALUE;
        }
        return ClassMetaInfo.getClassMetaInfo(_context,_inst.getClassName(_context));
    }

    public static Struct getClassMetaInfo(ContextEl _context, ClassMetaInfo _base, Struct[] _paramTypes) {
        StringList classesNames_ = new StringList();
        if (!(_paramTypes[0] instanceof ArrayStruct)) {
            return NullStruct.NULL_VALUE;
        }
        for (Struct s: ((ArrayStruct)_paramTypes[0]).list()) {
            if (!(s instanceof ClassMetaInfo)) {
                return NullStruct.NULL_VALUE;
            }
            classesNames_.add(NumParsers.getClass(s).formatted.getFormatted());
        }
        String res_ = ExecTemplates.getMadeVarTypes(_base.formatted.getFormatted(), classesNames_, _context);
        if (res_ == null) {
            return NullStruct.NULL_VALUE;
        }
        return getClassMetaInfo(_context,res_);
    }

    public static Struct getClassMetaInfo(ContextEl _context, AnnotatedStruct _member) {
        String formDeclaringClass_ = _member.getFormatted().getFormatted();
        if (formDeclaringClass_.isEmpty()) {
            return NullStruct.NULL_VALUE;
        }
        return getClassMetaInfo(_context, formDeclaringClass_);
    }

    public Struct tryWrap(ContextEl _cont, Struct _input) {
        Argument arg_ = new Argument(_input);
        ExecCastOperation.wrapFct(formatted.getFormatted(),true,_cont,arg_);
        return arg_.getStruct();
    }
    private StringList getBounds() {
        StringList list_ = new StringList();
        ExecRootBlock g_ = variableOwner.getRootBlock();
        String varName_ = "";
        if (!formatted.getFormatted().startsWith(AbstractReplacingType.PREFIX_VAR_TYPE_STR)) {
            g_ = null;
        } else {
            varName_ = formatted.getFormatted().substring(AbstractReplacingType.PREFIX_VAR_TYPE_STR.length());
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

    public CustList<ClassMetaInfo> getTypeParameters() {
        CustList<ClassMetaInfo> list_;
        list_ = new CustList<ClassMetaInfo>();
        ExecRootBlock g_ = formatted.getRootBlock();
        if (g_ == null) {
            return list_;
        }
        CustList<ExecTypeVar> vars_;
        vars_ = g_.getParamTypesMapValues();
        StringList upperBounds_ = new StringList();
        StringList lowerBounds_ = new StringList();
        for (ExecTypeVar b: vars_) {
            String pref_ = StringUtil.concat(AbstractReplacingType.PREFIX_VAR_TYPE_STR, b.getName());
            list_.add(new ClassMetaInfo(pref_, ClassCategory.VARIABLE, upperBounds_, lowerBounds_, formatted, g_.getAccess()));
        }
        return list_;
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
        return formatted.getFormatted().contains(AbstractReplacingType.PREFIX_VAR_TYPE_STR);
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
        ExecRootBlock rootBlock_ = formatted.getRootBlock();
        if (rootBlock_ instanceof ExecInfoBlock) {
            return ((ExecInfoBlock)rootBlock_).getAnonymousLambda();
        }
        if (rootBlock_ != null) {
            return rootBlock_.getAnonymousRootLambda();
        }
        return new CustList<ExecAnonymousFunctionBlock>();
    }

    @Override
    public CustList<ExecAbstractSwitchMethod> getSwitchMethods() {
        ExecRootBlock rootBlock_ = formatted.getRootBlock();
        if (rootBlock_ instanceof ExecInfoBlock) {
            return ((ExecInfoBlock)rootBlock_).getSwitchMethods();
        }
        if (rootBlock_ != null) {
            return rootBlock_.getSwitchMethodsRoot();
        }
        return new CustList<ExecAbstractSwitchMethod>();
    }

    @Override
    public ExecFormattedRootBlock getFormatted() {
        return formatted;
    }

    public Struct typeOwner(ContextEl _cont) {
        if (typeOwner.isEmpty()) {
            return NullStruct.NULL_VALUE;
        }
        return getExtendedClassMetaInfo(_cont,typeOwner, this);
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
        if (!StringUtil.quickEq(variableOwner.getFormatted(), info_.variableOwner.getFormatted())) {
            return false;
        }
        return StringUtil.quickEq(name, info_.name);
    }

    @Override
    public long randCode() {
        long r_ = NumParsers.mergeRandCode(1,NumParsers.randCode(variableOwner.getFormatted()));
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
        if (variableOwner.getFormatted().isEmpty()) {
            return new StringStruct(name);
        }
        return new StringStruct(StringUtil.concat(variableOwner.getFormatted(),";", name));
    }
}
