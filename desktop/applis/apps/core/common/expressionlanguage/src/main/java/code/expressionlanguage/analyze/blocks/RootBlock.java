package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.MethodHeaderInfo;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.accessing.Accessed;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetAccessInfo;
import code.expressionlanguage.analyze.files.ResultParsedAnnots;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.opers.util.MethodInfo;
import code.expressionlanguage.analyze.types.*;
import code.expressionlanguage.analyze.util.*;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.AnaInheritedType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.blocks.AnaRootBlockContent;
import code.expressionlanguage.linkage.ExportCst;
import code.expressionlanguage.options.KeyWords;
import code.util.*;
import code.util.core.BoolVal;
import code.util.core.StringUtil;

public abstract class RootBlock extends BracedBlock implements AccessedBlock,AnnotableBlock,AnaGeneType,AnaInheritedType {

    private AccessedBlock accessedBlock;
    private final StringList nameErrors = new StringList();

    private final AnaRootBlockContent rootBlockContent;

    private final AccessEnum access;

    private final int accessOffset;

    private final String templateDef;

    private final StringList imports = new StringList();

    private final Ints importsOffset = new Ints();

    private final CustList<OverridingMethodDto> allOverridingMethods;

    private final CustList<AnaResultPartType> results = new CustList<AnaResultPartType>();

    private final StringList directSuperTypes = new StringList();
    private final IntMap<String> importedDirectBaseSuperTypes = new IntMap<String>();

    private final IntMap< String> rowColDirectSuperTypes;
    private final IntMap< BoolVal> explicitDirectSuperTypes = new IntMap< BoolVal>();

    private final StringList staticInitInterfaces = new StringList();
    private final StringList instInitInterfaces = new StringList();
    private int templateDefOffset;
    private int nameLength;

    private final CustList<Ints> paramTypesConstraintsOffset = new CustList<Ints>();


    private final Ints staticInitInterfacesOffset = new Ints();
    private final Ints instInitInterfacesOffset = new Ints();
    private final CustList<AnaResultPartType> partsStaticInitInterfacesOffset = new CustList<AnaResultPartType>();
    private final CustList<AnaResultPartType> partsInstInitInterfacesOffset = new CustList<AnaResultPartType>();


    private String importedDirectSuperClass = "";
    private final StringList importedDirectSuperInterfaces = new StringList();
    private final CustList<RootBlock> staticInitImportedInterfaces = new CustList<RootBlock>();
    private final CustList<AnaFormattedRootBlock> instanceInitImportedInterfaces = new CustList<AnaFormattedRootBlock>();
    private final CustList<AnaFormattedRootBlock> importedDirectSuperTypes = new CustList<AnaFormattedRootBlock>();

    private ResultParsedAnnots annotations = new ResultParsedAnnots();
    private final CustList<AnaFormattedRootBlock> allGenericSuperTypesInfo = new CustList<AnaFormattedRootBlock>();
    private final CustList<AnaFormattedRootBlock> allGenericClassesInfo = new CustList<AnaFormattedRootBlock>();
    private int nbOperators;
    private int numberAll = -1;
    private final StringList allSuperTypes = new StringList();
    private final IdList<AnaGeneType> allSuperTypesInfo = new IdList<AnaGeneType>();
    private final StringList allReservedInners = new StringList();
    private final StringMap<Integer> counts = new StringMap<Integer>();
    private final StringMap<Integer> countsAnon = new StringMap<Integer>();
    private int countsAnonFct;
    private final StringMap<MappingLocalType> mappings = new StringMap<MappingLocalType>();
    private ConstructorBlock emptyCtor;
    private final AnonymousElements elementsType = new AnonymousElements();
    private final CustList<NamedCalledFunctionBlock> overridableBlocks = new CustList<NamedCalledFunctionBlock>();
    private final CustList<NamedCalledFunctionBlock> annotationsMethodsBlocks = new CustList<NamedCalledFunctionBlock>();
    private final CustList<ConstructorBlock> constructorBlocks = new CustList<ConstructorBlock>();
    private final CustList<InfoBlock> fieldsBlocks = new CustList<InfoBlock>();
    private final CustList<FieldBlock> fieldsInstBlocks = new CustList<FieldBlock>();
    private final CustList<InstanceBlock> instanceBlocks = new CustList<InstanceBlock>();
    private final CustList<StaticBlock> staticBlocks = new CustList<StaticBlock>();
    private final CustList<RootBlock> childrenRootBlocks = new CustList<RootBlock>();
    private final CustList<NamedCalledFunctionBlock> validMethods = new CustList<NamedCalledFunctionBlock>();
    private final CustList<ConstructorBlock> validCtors = new CustList<ConstructorBlock>();
    private boolean reference;
    RootBlock(OffsetAccessInfo _access, String _templateDef,
              IntMap<String> _directSuperTypes, int _offset, AnaRootBlockContent _content) {
        super(_offset);
        allOverridingMethods = new CustList<OverridingMethodDto>();
        rootBlockContent = _content;
        access = _access.getInfo();
        accessOffset = _access.getOffset();
        templateDef = _templateDef;
        rowColDirectSuperTypes = _directSuperTypes;
        for (EntryCust<Integer, String> t: _directSuperTypes.entryList()) {
            directSuperTypes.add(t.getValue());
            explicitDirectSuperTypes.put(t.getKey(), BoolVal.TRUE);
        }
    }
    public static AnaRootBlockContent contentRoot(int _idRowCol,String _packageName, String _name) {
        AnaRootBlockContent content_ = new AnaRootBlockContent();
        content_.setPackageName(StringExpUtil.removeDottedSpaces(_packageName));
        content_.setIdRowCol(_idRowCol);
        content_.setName(_name.trim());
        return content_;
    }

    public void setupOffsets(String _name, String _packageName) {
        nameLength = _name.length();
        if (!templateDef.isEmpty()) {
            templateDefOffset = rootBlockContent.getIdRowCol() + nameLength;
            if (!_packageName.isEmpty()) {
                templateDefOffset += _packageName.length() + 1;
            }
        }
        if (!_packageName.isEmpty()) {
            nameLength = (_packageName+"."+_name).trim().length();
        } else {
            nameLength = _name.trim().length();
        }
    }

    public void setNameLength(int _nameLength) {
        this.nameLength = _nameLength;
    }

    public int getAccessOffset() {
        return accessOffset;
    }

    public CustList<AnaFormattedRootBlock> getAllGenericSuperTypesInfo() {
        return allGenericSuperTypesInfo;
    }

    public static StringList allGenericClasses(CustList<AnaFormattedRootBlock> _gene) {
        StringList allGenericClasses_ = new StringList();
        for (AnaFormattedRootBlock a: _gene) {
            allGenericClasses_.add(a.getFormatted());
        }
        return allGenericClasses_;
    }
    public CustList<AnaFormattedRootBlock> getAllGenericClassesInfo() {
        return allGenericClassesInfo;
    }

    public IntMap<BoolVal> getExplicitDirectSuperTypes() {
        return explicitDirectSuperTypes;
    }

    public StringList getStaticInitInterfaces() {
        return staticInitInterfaces;
    }

    public Ints getStaticInitInterfacesOffset() {
        return staticInitInterfacesOffset;
    }

    public StringList getInstInitInterfaces() {
        return instInitInterfaces;
    }

    public Ints getInstInitInterfacesOffset() {
        return instInitInterfacesOffset;
    }

    public void buildAnnotations(AnalyzedPageEl _page) {
        annotations.buildAnnotations(_page);
    }

    public ResultParsedAnnots getAnnotations() {
        return annotations;
    }

    public void setAnnotations(ResultParsedAnnots _a) {
        this.annotations = _a;
    }


    public StringList getImports() {
        return imports;
    }


    @Override
    public StringList getFileImports() {
        return getFile().getImports();
    }

    public Ints getImportsOffset() {
        return importsOffset;
    }

    public IntMap< String> getRowColDirectSuperTypes() {
        return rowColDirectSuperTypes;
    }

    public int getIdRowCol() {
        return rootBlockContent.getIdRowCol();
    }

    public CustList<AnaResultPartType> getResults() {
        return results;
    }

    public StringList getDirectSuperTypes() {
        return directSuperTypes;
    }

    public IntMap<String> getImportedDirectBaseSuperTypes() {
        return importedDirectBaseSuperTypes;
    }

    protected void checkAccess(AnalyzedPageEl _page) {
        useSuperTypesOverrides(_page);
        StringMap<StringList> vars_ = extractVarTypes();
        for (OverridingMethodDto e: allOverridingMethods) {
            loopOverrideMethod(_page, vars_, e);
        }
    }

    private void loopOverrideMethod(AnalyzedPageEl _page, StringMap<StringList> _vars, OverridingMethodDto _e) {
        CustList<GeneStringOverridable> locGeneInt_ = new CustList<GeneStringOverridable>();
        CustList<GeneStringOverridable> locGeneCl_ = new CustList<GeneStringOverridable>();
        feedOverByTypes(_e, locGeneInt_, locGeneCl_);
        for (GeneStringOverridable i: locGeneInt_) {
            for (GeneStringOverridable c: locGeneCl_) {
                crossCheckMethods(_page, _vars, i, c);
            }
        }
    }

    private void crossCheckMethods(AnalyzedPageEl _page, StringMap<StringList> _vars, GeneStringOverridable _i, GeneStringOverridable _c) {
        String nameCl_ = _c.getGeneString();
        NamedCalledFunctionBlock supCl_ = _c.getBlock();
        NamedCalledFunctionBlock supInt_ = _i.getBlock();
        String name_ = _i.getGeneString();
        if (supInt_.getAccess().isStrictMoreAccessibleThan(supCl_.getAccess())) {
            MethodId id_ = supInt_.getId();
            MethodId idCl_ = supCl_.getId();
            FoundErrorInterpret err_;
            err_ = new FoundErrorInterpret();
            err_.setFile(getFile());
            err_.setIndexFile(supCl_.getAccessOffset());
            //key word access or method name
            err_.buildError(_page.getAnalysisMessages().getMethodsAccesses(),
                    name_,
                    id_.getSignature(_page.getDisplayedStrings()),
                    nameCl_,
                    idCl_.getSignature(_page.getDisplayedStrings()));
            _page.addLocError(err_);
            supCl_.addNameErrors(err_);
        }
        String retInt_ = supInt_.getImportedReturnType();
        String retBase_ = supCl_.getImportedReturnType();
        String formattedRetDer_ = AnaInherits.quickFormat(_c.getFormat(), retBase_);
        String formattedRetBase_ = AnaInherits.quickFormat(_i.getFormat(), retInt_);
        if (supCl_.mustHaveSameRet()) {
            if (!StringUtil.quickEq(formattedRetBase_, formattedRetDer_)) {
                MethodId id_ = supInt_.getId();
                MethodId idCl_ = supCl_.getId();
                FoundErrorInterpret err_;
                err_ = new FoundErrorInterpret();
                err_.setFile(getFile());
                err_.setIndexFile(supCl_.getReturnTypeOffset());
                //sub return type len
                err_.buildError(_page.getAnalysisMessages().getBadReturnTypeIndexer(),
                        formattedRetBase_,
                        id_.getSignature(_page.getDisplayedStrings()),
                        name_,
                        formattedRetDer_,
                        idCl_.getSignature(_page.getDisplayedStrings()),
                        nameCl_);
                _page.addLocError(err_);
                supCl_.addNameErrors(err_);
            }
            return;
        }
        if (!AnaInherits.isReturnCorrect(formattedRetBase_, formattedRetDer_, _vars, _page)) {
            MethodId id_ = supInt_.getId();
            MethodId idCl_ = supCl_.getId();
            FoundErrorInterpret err_;
            err_ = new FoundErrorInterpret();
            err_.setFile(getFile());
            err_.setIndexFile(supCl_.getReturnTypeOffset());
            //sub return type len
            err_.buildError(_page.getAnalysisMessages().getBadReturnTypeInherit(),
                    formattedRetDer_,
                    idCl_.getSignature(_page.getDisplayedStrings()),
                    nameCl_,
                    formattedRetBase_,
                    id_.getSignature(_page.getDisplayedStrings()),
                    name_);
            _page.addLocError(err_);
            supCl_.addNameErrors(err_);
        }
    }

    private void feedOverByTypes(OverridingMethodDto _e, CustList<GeneStringOverridable> _locGeneInt, CustList<GeneStringOverridable> _locGeneCl) {
        for (GeneStringOverridable c: _e.getMethodIds()) {
            RootBlock r_ = c.getType();
            if (r_ instanceof InterfaceBlock) {
                _locGeneInt.add(c);
            }
            if (r_ instanceof ClassBlock) {
                _locGeneCl.add(c);
            }
        }
    }

    private StringMap<StringList> extractVarTypes() {
        StringMap<StringList> vars_ = new StringMap<StringList>();
        for (TypeVar t: getParamTypesMapValues()) {
            vars_.put(t.getName(), t.getConstraints());
        }
        return vars_;
    }

    public final RootBlock getParentType() {
        return rootBlockContent.getParentType();
    }

    public final void setParentType(RootBlock _parentType) {
        rootBlockContent.setParentType(_parentType);
        setAccessedBlock(_parentType);
    }

    public final CustList<RootBlock> getAllParentTypes() {
        CustList<RootBlock> pars_ = new CustList<RootBlock>();
        RootBlock c_ = getParentType();
        while (c_ != null) {
            pars_.add(c_);
            c_ = c_.getParentType();
        }
        return pars_;
    }
    public final CustList<RootBlock> getSelfAndParentTypes() {
        CustList<RootBlock> pars_ = new CustList<RootBlock>();
        RootBlock c_ = this;
        boolean add_ = true;
        while (c_ != null) {
            if (add_) {
                pars_.add(c_);
            }
            if (c_.withoutInstance()) {
                add_ = false;
            }
            c_ = c_.getParentType();
        }
        return pars_.getReverse();
    }
    public final void buildMapParamType(AnalyzedPageEl _page) {
        rootBlockContent.setParamTypesMap(new StringMap<TypeVar>());
        _page.getMappingLocal().clear();
        _page.getMappingLocal().putAllMap(getRefMappings());
        for (RootBlock r: getSelfAndParentTypes()) {
            if (r == this) {
                if (r instanceof AnonymousTypeBlock) {
                    constraintsAnonymous();
                    continue;
                }
                int j_ = 0;
                for (TypeVar t: rootBlockContent.getParamTypes()) {
                    StringList const_ = new StringList();
                    CustList<AnaResultPartType> results_ = new CustList<AnaResultPartType>();
                    Ints ints_ = paramTypesConstraintsOffset.get(j_);
//                    varDef(t, constraintsParts);
                    int i_ = 0;
                    for (String c: t.getConstraints()) {
                        int d_ = ints_.get(i_);
                        AnaResultPartType res_ = ResolvingSuperTypes.processAnalyzeHeader(c.trim(), this, 1 + d_, _page, false);
                        results_.add(res_);
                        const_.add(res_.getResult());
                        i_++;
                    }
//                    constraintsParts.addAllElts(_page.getCurrentParts());
                    j_++;
                    TypeVar t_ = new TypeVar();
                    t_.setOffset(t.getOffset());
                    t_.setLength(t.getLength());
                    t.getResults().addAllElts(results_);
                    t_.getResults().addAllElts(results_);
                    t_.setConstraints(const_);
                    t_.setName(t.getName());
                    rootBlockContent.getParamTypesMap().addEntry(t.getName(), t_);
                }
            } else {
                constraintsParent(r);
            }
        }
    }

    private void constraintsAnonymous() {
        for (TypeVar t: rootBlockContent.getParamTypes()) {
            rootBlockContent.getParamTypesMap().addEntry(t.getName(), t);
        }
    }

    private void constraintsParent(RootBlock _r) {
        for (EntryCust<String,TypeVar> e: _r.rootBlockContent.getParamTypesMap().entryList()) {
            boolean exist_ = false;
            for (TypeVar t: _r.getParamTypes()) {
                if (StringUtil.quickEq(t.getName(),e.getKey())) {
                    exist_ = true;
                }
            }
            if (!exist_) {
                continue;
            }
            rootBlockContent.getParamTypesMap().addEntry(e.getKey(),e.getValue());
        }
    }

    public CustList<TypeVar> getParamTypesMapValues() {
        return rootBlockContent.getParamTypesMap().values();
    }

    public CustList<TypeVar> getParamTypes() {
        return rootBlockContent.getParamTypes();
    }

    public int getTemplateDefOffset() {
        return templateDefOffset;
    }

    public CustList<Ints> getParamTypesConstraintsOffset() {
        return paramTypesConstraintsOffset;
    }

    private static void appendParts(StringBuilder _generic, RootBlock _previous, RootBlock _r) {
        if (_previous != null) {
            if (_r instanceof InnerElementBlock) {
                _generic.append("-");
            } else {
                _generic.append("..");
            }
        }
    }

    public static void addPkgIfNotEmpty(String _pkg, StringBuilder _generic) {
        if (!_pkg.isEmpty()) {
            _generic.append(_pkg);
            _generic.append(DOT);
        }
    }

    public String getFullDefinition() {
        return StringUtil.concat(getFullName(),getTemplateDef());
    }

    public String getTemplateDef() {
        return templateDef;
    }

    public String getName() {
        return getRootBlockContent().getName();
    }
    public String getSuffixedName() {
        return StringUtil.concat(getName(), rootBlockContent.getSuffix());
    }
    public String getPackageName() {
        return rootBlockContent.getPackageName();
    }

    public AnaRootBlockContent getRootBlockContent() {
        return rootBlockContent;
    }

    public AccessEnum getAccess() {
        return access;
    }
    /**
     @return a map with formatted id from super types as key
     and a list of (formatted super types and id) as value
     */
    public CustList<OverridingMethodDto> getAllOverridingMethods() {
        return allOverridingMethods;
    }

    public String getFullName() {
        CustList<RootBlock> all_ = new CustList<RootBlock>(this);
        all_.addAllElts(getAllParentTypes());
        RootBlock p_ = null;
        StringBuilder strBuilder_ = new StringBuilder();
        addPkgIfNotEmpty(rootBlockContent.getPackageName(),strBuilder_);
        for (RootBlock r: all_.getReverse()) {
            appendParts(strBuilder_,p_,r);
            strBuilder_.append(r.getSuffixedName());
            p_ = r;
        }
        return strBuilder_.toString();
    }

    public final void validateIds(AnalyzedPageEl _page) {
        RootBlockIdValidator validator_ = new RootBlockIdValidator();
        CustList<AbsBk> bl_;
        bl_ = ClassesUtil.getDirectChildren(this);
        checkMembers(_page, bl_);
        checkForRecord(_page, bl_);
        for (AbsBk b: bl_) {
            validateId(_page,validator_,b);
        }
        buildFieldInfos(bl_, _page);
        _page.getUnary().addEntry(getFullName(),validator_.getUnary());
        _page.getBinaryAll().addEntry(getFullName(),validator_.getBinaryAll());
        _page.getBinaryFirst().addEntry(getFullName(),validator_.getBinaryFirst());
        _page.getBinarySecond().addEntry(getFullName(),validator_.getBinarySecond());
        _page.getExplicitCastMethods().addEntry(getFullName(),validator_.getExplicit());
        _page.getExplicitIdCastMethods().addEntry(getFullName(),validator_.getExplicitId());
        _page.getExplicitFromCastMethods().addEntry(getFullName(),validator_.getExplicitFrom());
        _page.getImplicitCastMethods().addEntry(getFullName(),validator_.getImplicit());
        _page.getImplicitIdCastMethods().addEntry(getFullName(),validator_.getImplicitId());
        _page.getImplicitFromCastMethods().addEntry(getFullName(),validator_.getImplicitFrom());
        _page.getTrues().addEntry(getFullName(),validator_.getTrues());
        _page.getFalses().addEntry(getFullName(),validator_.getFalses());
        validateIndexers(validator_.getIndexersGet(), validator_.getIndexersSet(), _page);
    }
    private void validateId(AnalyzedPageEl _page, RootBlockIdValidator _validator, AbsBk _b) {
        CustList<MethodId> idMethods_ = _validator.getIdMethods();
        CustList<ConstructorId> idConstructors_ = _validator.getIdConstructors();
        if (!(_b instanceof NamedFunctionBlock)) {
            return;
        }
        NamedFunctionBlock method_ = (NamedFunctionBlock) _b;
        if (isOverBlock(method_)) {
            NamedCalledFunctionBlock m_ = (NamedCalledFunctionBlock) method_;
            m_.buildImportedTypes(_page);
            processRegularMethods(_page,_validator, m_);
        }
        if (AbsBk.isAnnotBlock(method_)) {
            NamedCalledFunctionBlock m_ = (NamedCalledFunctionBlock) method_;
            m_.buildImportedTypes(_page);
            processAnnotMethods(_page, idMethods_, m_);
        }
        if (method_ instanceof ConstructorBlock) {
            method_.buildImportedTypes(_page);
            processCtors(_page, idConstructors_, (ConstructorBlock) method_);
        }
        validateParameters(method_, _page, getFile());
    }

    private void processRegularMethods(AnalyzedPageEl _page, RootBlockIdValidator _validator, NamedCalledFunctionBlock _m) {
        CustList<MethodId> idMethods_ = _validator.getIdMethods();
        CustList<NamedCalledFunctionBlock> indexersGet_ = _validator.getIndexersGet();
        CustList<NamedCalledFunctionBlock> indexersSet_ = _validator.getIndexersSet();
        CustList<MethodHeaderInfo> unary_ = _validator.getUnary();
        CustList<MethodHeaderInfo> binaryFirst_ = _validator.getBinaryFirst();
        CustList<MethodHeaderInfo> binarySecond_ = _validator.getBinarySecond();
        CustList<MethodHeaderInfo> binaryAll_ = _validator.getBinaryAll();
        CustList<MethodHeaderInfo> explicit_ = _validator.getExplicit();
        CustList<MethodHeaderInfo> explicitId_ = _validator.getExplicitId();
        CustList<MethodHeaderInfo> explicitFrom_ = _validator.getExplicitFrom();
        CustList<MethodHeaderInfo> implicit_ = _validator.getImplicit();
        CustList<MethodHeaderInfo> implicitId_ = _validator.getImplicitId();
        CustList<MethodHeaderInfo> implicitFrom_ = _validator.getImplicitFrom();
        CustList<MethodHeaderInfo> true_ = _validator.getTrues();
        CustList<MethodHeaderInfo> false_ = _validator.getFalses();
        boolean valid_;
        if (_m.getKind() == MethodKind.OPERATOR) {
            valid_ = checkOperators(_page, _m);
            feedOperators(unary_, binaryFirst_, binarySecond_, binaryAll_, _m);
            nbOperators++;
        } else if (_m.getKind() == MethodKind.TRUE_OPERATOR || _m.getKind() == MethodKind.FALSE_OPERATOR) {
            valid_ = checkTests(_page, _m, true_, false_);
        } else if (_m.getKind() == MethodKind.EXPLICIT_CAST || _m.getKind() == MethodKind.IMPLICIT_CAST) {
            if (koBase(_page, _m)) {
                valid_ = false;
            } else if (!StringUtil.quickEq(_m.getImportedReturnType(), getGenericString()) && !StringUtil.quickEq(_m.getImportedParametersTypes().first(), getGenericString())) {
                int r_ = _m.getNameOffset();
                FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
                badMeth_.setFile(getFile());
                badMeth_.setIndexFile(r_);
                //method name len
                badMeth_.buildError(_page.getAnalysisMessages().getBadReturnType(),
                        _m.getSignature(_page),
                        getGenericString());
                _page.addLocError(badMeth_);
                _m.addNameErrors(badMeth_);
                valid_ = false;
            } else {
                valid_ = true;
                feedConverter(explicit_, explicitId_, explicitFrom_, implicit_, implicitId_, implicitFrom_, _m);
            }

        } else if (_m.getKind() == MethodKind.RAND_CODE) {
            valid_ = checkRandCode(_page, _m);
        } else if (_m.getKind() == MethodKind.TO_STRING) {
            valid_ = checkToStr(_page, _m);
        } else if (_m.getKind() == MethodKind.STD_METHOD) {
            valid_ = checkStd(_page, _m);
        } else {
            valid_ = checkIndexers(_page, indexersGet_, indexersSet_, _m);
        }
        checkDuplicates(_page, idMethods_, _m, valid_);
    }

    private boolean checkOperators(AnalyzedPageEl _page, NamedCalledFunctionBlock _m) {
        String name_ = _m.getName();
        boolean valid_ = true;
        if (!StringExpUtil.isOper(_m.getName())) {
            int r_ = _m.getNameOffset();
            FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
            badMeth_.setFile(getFile());
            badMeth_.setIndexFile(r_);
            //method name len
            badMeth_.buildError(_page.getAnalysisMessages().getBadOperatorName(),
                    name_);
            _page.addLocError(badMeth_);
            _m.addNameErrors(badMeth_);
            valid_ = false;
        }
        return valid_;
    }

    private boolean checkStd(AnalyzedPageEl _page, NamedCalledFunctionBlock _m) {
        String name_ = _m.getName();
        boolean valid_ = true;
        KeyWords keyWords_ = _page.getKeyWords();
        if (!StringUtil.quickEq(name_, keyWords_.getKeyWordToString()) && koFctName(_page, _m)) {
            valid_ = false;
        }
        return valid_;
    }

    private boolean checkTests(AnalyzedPageEl _page, NamedCalledFunctionBlock _m, CustList<MethodHeaderInfo> _t, CustList<MethodHeaderInfo> _f) {
        if (koBase(_page, _m)) {
            return false;
        }
        if (!StringUtil.quickEq(_m.getImportedReturnType(), _page.getAliasPrimBoolean()) || !StringUtil.quickEq(_m.getImportedParametersTypes().first(), getGenericString())) {
            int r_ = _m.getNameOffset();
            FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
            badMeth_.setFile(getFile());
            badMeth_.setIndexFile(r_);
            //method name len
            badMeth_.buildError(_page.getAnalysisMessages().getBadReturnType(),
                    _m.getSignature(_page),
                    getGenericString());
            _page.addLocError(badMeth_);
            _m.addNameErrors(badMeth_);
            return false;
        }
        feedTests(_t, _f, _m);
        return true;
    }

    private boolean checkIndexers(AnalyzedPageEl _page, CustList<NamedCalledFunctionBlock> _indexersGet, CustList<NamedCalledFunctionBlock> _indexersSet, NamedCalledFunctionBlock _m) {
        boolean valid_ = true;
        if (_m.hiddenInstance()) {
            int where_ = _m.getOffset();
            FoundErrorInterpret unexp_ = new FoundErrorInterpret();
            unexp_.setFile(getFile());
            unexp_.setIndexFile(where_);
            //key word this len
            unexp_.buildError(_page.getAnalysisMessages().getBadIndexerModifier(),
                    _m.getSignature(_page));
            _page.addLocError(unexp_);
            _m.addNameErrors(unexp_);
            valid_ = false;
        }
        if (_m.getParametersTypes().isEmpty()) {
            int where_ = _m.getOffset();
            FoundErrorInterpret unexp_ = new FoundErrorInterpret();
            unexp_.setFile(getFile());
            unexp_.setIndexFile(where_);
            //key word this len
            unexp_.buildError(_page.getAnalysisMessages().getBadIndexerParams(),
                    _m.getSignature(_page));
            _page.addLocError(unexp_);
            _m.addNameErrors(unexp_);
            valid_ = false;
        }
        if (_m.getKind() == MethodKind.GET_INDEX) {
            _indexersGet.add(_m);
        } else {
            _indexersSet.add(_m);
        }
        return valid_;
    }

    private void checkDuplicates(AnalyzedPageEl _page, CustList<MethodId> _allMethods, NamedCalledFunctionBlock _m, boolean _valid) {
        boolean valid_ = _valid;
        MethodId id_ = _m.getId();
        if (_m.getKind() == MethodKind.TO_STRING || _m.getKind() == MethodKind.STD_METHOD || _m.getKind() == MethodKind.OPERATOR || _m.getKind() == MethodKind.EXPLICIT_CAST || _m.getKind() == MethodKind.IMPLICIT_CAST
                || _m.getKind() == MethodKind.TRUE_OPERATOR || _m.getKind() == MethodKind.FALSE_OPERATOR) {
            if (ContextUtil.isEnumType(this)) {
                valid_ = checkSpecialEnumMethoos(_page, _m, valid_, id_);
            }
            valid_ = duplicate(_page, _allMethods, _m, valid_, id_, _page.getAnalysisMessages().getDuplicateCustomMethod());
        } else {
            valid_ = duplicate(_page, _allMethods, _m, valid_, id_, _page.getAnalysisMessages().getDuplicateIndexer());
        }
        _allMethods.add(id_);
        if (valid_) {
            validMethods.add(_m);
        }
    }

    private boolean duplicate(AnalyzedPageEl _page, CustList<MethodId> _allMethods, NamedCalledFunctionBlock _m, boolean _valid, MethodId _id, String _message) {
        boolean valid_ = _valid;
        for (MethodId m: _allMethods) {
            if (m.eq(_id)) {
                int r_ = _m.getOffset();
                FoundErrorInterpret duplicate_;
                duplicate_ = new FoundErrorInterpret();
                duplicate_.setIndexFile(r_);
                duplicate_.setFile(getFile());
                //method name len
                duplicate_.buildError(_message,
                        _id.getSignature(_page.getDisplayedStrings()));
                _page.addLocError(duplicate_);
                _m.addNameErrors(duplicate_);
                valid_ = false;
            }
        }
        return valid_;
    }

    private boolean checkSpecialEnumMethoos(AnalyzedPageEl _page, NamedCalledFunctionBlock _m, boolean _valid, MethodId _id) {
        boolean valid_ = _valid;
        String valueOf_ = _page.getAliasEnumPredValueOf();
        String values_ = _page.getAliasEnumValues();
        String string_ = _page.getAliasString();
        if (_id.eq(new MethodId(MethodAccessKind.STATIC, valueOf_, new StringList(string_)))) {
            int r_ = _m.getOffset();
            FoundErrorInterpret duplicate_;
            duplicate_ = new FoundErrorInterpret();
            duplicate_.setIndexFile(r_);
            duplicate_.setFile(getFile());
            //method name len
            duplicate_.buildError(_page.getAnalysisMessages().getReservedCustomMethod(),
                    _id.getSignature(_page.getDisplayedStrings()));
            _page.addLocError(duplicate_);
            _m.addNameErrors(duplicate_);
            valid_ = false;
        }
        if (_id.eq(new MethodId(MethodAccessKind.STATIC, values_, new StringList()))) {
            int r_ = _m.getOffset();
            FoundErrorInterpret duplicate_;
            duplicate_ = new FoundErrorInterpret();
            duplicate_.setIndexFile(r_);
            duplicate_.setFile(getFile());
            //method name len
            duplicate_.buildError(_page.getAnalysisMessages().getReservedCustomMethod(),
                    _id.getSignature(_page.getDisplayedStrings()));
            _page.addLocError(duplicate_);
            _m.addNameErrors(duplicate_);
            valid_ = false;
        }
        return valid_;
    }

    private boolean checkToStr(AnalyzedPageEl _page, NamedCalledFunctionBlock _m) {
        String name_ = _m.getName();
        boolean valid_ = true;
        if (!StringUtil.quickEq(_m.getImportedReturnType(), _page.getAliasString())) {
            int r_ = _m.getNameOffset();
            FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
            badMeth_.setFile(getFile());
            badMeth_.setIndexFile(r_);
            //method name len
            badMeth_.buildError(_page.getAnalysisMessages().getBadReturnType(),
                    name_,
                    _page.getAliasString());
            _page.addLocError(badMeth_);
            _m.addNameErrors(badMeth_);
            valid_ = false;
        } else if (koAccess(_page, _m)) {
            valid_ = false;
        } else {
            ToStringMethodHeader t_ = new ToStringMethodHeader(getNumberAll(), _m.getNameOverrideNumber(), _m.getName(), _m.getImportedReturnType(), _m.isFinalMethod(), _m.isAbstractMethod());
            _page.getToStringMethods().addEntry(getFullName(), t_);
        }
        return valid_;
    }

    private boolean checkRandCode(AnalyzedPageEl _page, NamedCalledFunctionBlock _m) {
        String name_ = _m.getName();
        boolean valid_ = true;
        if (_m.getParametersTypes().size() != 0) {
            int r_ = _m.getNameOffset();
            FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
            badMeth_.setFile(getFile());
            badMeth_.setIndexFile(r_);
            //method name len
            badMeth_.buildError(_page.getAnalysisMessages().getBadParams(),
                    _m.getSignature(_page));
            _page.addLocError(badMeth_);
            _m.addNameErrors(badMeth_);
            valid_ = false;
        } else if (!StringUtil.quickEq(_m.getImportedReturnType(), _page.getAliasPrimLong()) || _m.getId().isRef()) {
            int r_ = _m.getNameOffset();
            FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
            badMeth_.setFile(getFile());
            badMeth_.setIndexFile(r_);
            //method name len
            badMeth_.buildError(_page.getAnalysisMessages().getBadReturnType(),
                    name_,
                    _page.getAliasString());
            _page.addLocError(badMeth_);
            _m.addNameErrors(badMeth_);
            valid_ = false;
        } else if (_m.hiddenInstance()) {
            int r_ = _m.getNameOffset();
            FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
            badMeth_.setFile(getFile());
            badMeth_.setIndexFile(r_);
            //method name len
            badMeth_.buildError(_page.getAnalysisMessages().getBadMethodModifier(),
                    _m.getSignature(_page));
            _page.addLocError(badMeth_);
            _m.addNameErrors(badMeth_);
            valid_ = false;
        } else if (koAccess(_page, _m)) {
            valid_ = false;
        } else {
            ToStringMethodHeader t_ = new ToStringMethodHeader(getNumberAll(), _m.getNameOverrideNumber(), _m.getName(), _m.getImportedReturnType(), _m.isFinalMethod(), _m.isAbstractMethod());
            _page.getRandCodeMethods().addEntry(getFullName(), t_);
        }
        return valid_;
    }

    private void processAnnotMethods(AnalyzedPageEl _page, CustList<MethodId> _allAnnotMethods, NamedCalledFunctionBlock _m) {
        boolean valid_ = true;
        if (_m.isKo()) {
            int r_ = _m.getNameOffset();
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFile(getFile());
            b_.setIndexFile(r_);
            //underline index char
            b_.buildError(_page.getAnalysisMessages().getBadIndexInParser());
            _page.addLocError(b_);
            _m.addNameErrors(b_);
            valid_ = false;
        }
        if (koFctName(_page, _m)) {
            valid_ = false;
        }
        MethodId id_ = _m.getId();
        for (MethodId m: _allAnnotMethods) {
            if (m.eq(id_)) {
                int r_ = _m.getOffset();
                FoundErrorInterpret duplicate_;
                duplicate_ = new FoundErrorInterpret();
                duplicate_.setIndexFile(r_);
                duplicate_.setFile(getFile());
                String sgn_ = id_.getSignature(_page.getDisplayedStrings());
                //method name len
                duplicate_.buildError(_page.getAnalysisMessages().getDuplicateCustomMethod(),
                        sgn_);
                _page.addLocError(duplicate_);
                _m.addNameErrors(duplicate_);
                valid_ = false;
            }
        }
        if (valid_) {
            validMethods.add(_m);
        }
        _allAnnotMethods.add(id_);
    }

    private void processCtors(AnalyzedPageEl _page, CustList<ConstructorId> _allCtors, ConstructorBlock _ctor) {
        boolean valid_ = true;
        String ctorName_ = _ctor.getCtorName();
        if (StringExpUtil.isTypeLeafPart(ctorName_) && !StringUtil.quickEq(ctorName_, getName())) {
            int r_ = _ctor.getOffset();
            FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
            badMeth_.setFile(getFile());
            badMeth_.setIndexFile(r_);
            //method name len
            AnalysisMessages ana_ = _page.getAnalysisMessages();
            badMeth_.setBuiltError(FoundErrorInterpret.buildARError(ana_.getBadMethodName(), ctorName_));
            _page.addLocError(badMeth_);
            _ctor.addNameErrors(badMeth_);
        }
        ConstructorId idCt_ = _ctor.getId();
        for (ConstructorId m: _allCtors) {
            if (m.eq(idCt_)) {
                int r_ = _ctor.getOffset();
                FoundErrorInterpret duplicate_;
                duplicate_ = new FoundErrorInterpret();
                duplicate_.setIndexFile(r_);
                duplicate_.setFile(getFile());
                //left par len
                duplicate_.buildError(_page.getAnalysisMessages().getDuplicatedCtor(),
                        idCt_.getSignature(_page.getDisplayedStrings()));
                _page.addLocError(duplicate_);
                _ctor.addNameErrors(duplicate_);
                valid_ = false;
            }
        }
        _allCtors.add(idCt_);
        if (valid_) {
            validCtors.add(_ctor);
        }
    }

    private void feedConverter(CustList<MethodHeaderInfo> _explicit, CustList<MethodHeaderInfo> _explicitId, CustList<MethodHeaderInfo> _explicitFrom, CustList<MethodHeaderInfo> _implicit, CustList<MethodHeaderInfo> _implicitId, CustList<MethodHeaderInfo> _implicitFrom, NamedCalledFunctionBlock _m) {
        if (_m.getKind() == MethodKind.EXPLICIT_CAST) {
            if (StringUtil.quickEq(_m.getImportedParametersTypes().first(), _m.getImportedReturnType())) {
                _explicitId.add(new MethodHeaderInfo(this, _m, _m.getId(), getNumberAll(), _m.getNameOverrideNumber(), _m.getImportedReturnType(), _m.getAccess()));
            } else if (StringUtil.quickEq(_m.getImportedReturnType(),getGenericString())){
                _explicit.add(new MethodHeaderInfo(this, _m, _m.getId(), getNumberAll(), _m.getNameOverrideNumber(), _m.getImportedReturnType(), _m.getAccess()));
            } else {
                _explicitFrom.add(new MethodHeaderInfo(this, _m, _m.getId(), getNumberAll(), _m.getNameOverrideNumber(), _m.getImportedReturnType(), _m.getAccess()));
            }
        } else {
            if (StringUtil.quickEq(_m.getImportedParametersTypes().first(), _m.getImportedReturnType())) {
                _implicitId.add(new MethodHeaderInfo(this, _m, _m.getId(), getNumberAll(), _m.getNameOverrideNumber(), _m.getImportedReturnType(), _m.getAccess()));
            } else if (StringUtil.quickEq(_m.getImportedReturnType(),getGenericString())){
                _implicit.add(new MethodHeaderInfo(this, _m, _m.getId(), getNumberAll(), _m.getNameOverrideNumber(), _m.getImportedReturnType(), _m.getAccess()));
            } else {
                _implicitFrom.add(new MethodHeaderInfo(this, _m, _m.getId(), getNumberAll(), _m.getNameOverrideNumber(), _m.getImportedReturnType(), _m.getAccess()));
            }
        }
    }

    private void feedTests(CustList<MethodHeaderInfo> _t, CustList<MethodHeaderInfo> _f, NamedCalledFunctionBlock _m) {
        if (_m.getKind() == MethodKind.TRUE_OPERATOR) {
            _t.add(new MethodHeaderInfo(this, _m, _m.getId(), getNumberAll(), _m.getNameOverrideNumber(), _m.getImportedReturnType(), _m.getAccess()));
        } else {
            _f.add(new MethodHeaderInfo(this, _m, _m.getId(), getNumberAll(), _m.getNameOverrideNumber(), _m.getImportedReturnType(), _m.getAccess()));
        }
    }

    private void feedOperators(CustList<MethodHeaderInfo> _unary, CustList<MethodHeaderInfo> _binaryFirst, CustList<MethodHeaderInfo> _binarySecond, CustList<MethodHeaderInfo> _binaryAll, NamedCalledFunctionBlock _m) {
        if (_m.getId().getKind() != MethodAccessKind.STATIC_CALL) {
            return;
        }
        if (_m.getParametersTypes().size() == 2 && StringExpUtil.isBin(_m.getName())) {
            if (StringUtil.quickEq(StringExpUtil.getQuickComponentBase(_m.getImportedParametersTypes().first()), getGenericString())) {
                if (StringUtil.quickEq(StringExpUtil.getQuickComponentBase(_m.getImportedParametersTypes().last()), getGenericString())) {
                    _binaryAll.add(new MethodHeaderInfo(this, _m, _m.getId(), getNumberAll(), _m.getNameOverrideNumber(), _m.getImportedReturnType(), _m.getAccess()));
                } else {
                    _binaryFirst.add(new MethodHeaderInfo(this, _m, _m.getId(), getNumberAll(), _m.getNameOverrideNumber(), _m.getImportedReturnType(), _m.getAccess()));
                }
            } else if (StringUtil.quickEq(StringExpUtil.getQuickComponentBase(_m.getImportedParametersTypes().last()), getGenericString())) {
                _binarySecond.add(new MethodHeaderInfo(this, _m, _m.getId(), getNumberAll(), _m.getNameOverrideNumber(), _m.getImportedReturnType(), _m.getAccess()));
            }
        }
        if (_m.getParametersTypes().size() == 1 && StringExpUtil.isUn(_m.getName()) && StringUtil.quickEq(StringExpUtil.getQuickComponentBase(_m.getImportedParametersTypes().first()), getGenericString())) {
            _unary.add(new MethodHeaderInfo(this, _m, _m.getId(), getNumberAll(), _m.getNameOverrideNumber(), _m.getImportedReturnType(), _m.getAccess()));
        }
    }

    private boolean koFctName(AnalyzedPageEl _page, NamedCalledFunctionBlock _m) {
        String name_ = _m.getName();
        TokenErrorMessage mess_ = ManageTokens.partMethod(_page).checkToken(name_, _page);
        if (mess_.isError()) {
            int r_ = _m.getNameOffset();
            FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
            badMeth_.setFile(getFile());
            badMeth_.setIndexFile(r_);
            //method name len
            badMeth_.setBuiltError(mess_.getMessage());
            _page.addLocError(badMeth_);
            _m.addNameErrors(badMeth_);
            return true;
        }
        return false;
    }
    private boolean koAccess(AnalyzedPageEl _page, NamedCalledFunctionBlock _m) {
        String name_ = _m.getName();
        if (_m.getAccess() != AccessEnum.PUBLIC) {
            int r_ = _m.getNameOffset();
            FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
            badMeth_.setFile(getFile());
            badMeth_.setIndexFile(r_);
            //method name len
            badMeth_.buildError(_page.getAnalysisMessages().getBadAccess(),
                    name_);
            _page.addLocError(badMeth_);
            _m.addNameErrors(badMeth_);
            return true;
        }
        return false;
    }

    private boolean koBase(AnalyzedPageEl _page, NamedCalledFunctionBlock _m) {
        if (_m.getParametersTypes().size() != 1) {
            int r_ = _m.getNameOffset();
            FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
            badMeth_.setFile(getFile());
            badMeth_.setIndexFile(r_);
            //method name len
            badMeth_.buildError(_page.getAnalysisMessages().getBadParams(),
                    _m.getSignature(_page));
            _page.addLocError(badMeth_);
            _m.addNameErrors(badMeth_);
            return true;
        }
        if (!_m.isStaticMethod()) {
            int r_ = _m.getNameOffset();
            FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
            badMeth_.setFile(getFile());
            badMeth_.setIndexFile(r_);
            //method name len
            badMeth_.buildError(_page.getAnalysisMessages().getBadMethodModifier(),
                    _m.getSignature(_page));
            _page.addLocError(badMeth_);
            _m.addNameErrors(badMeth_);
            return true;
        }
        if (_m.isVarargs() || _m.getId().isRef()) {
            int r_ = _m.getNameOffset();
            FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
            badMeth_.setFile(getFile());
            badMeth_.setIndexFile(r_);
            //method name len
            badMeth_.buildError(_page.getAnalysisMessages().getBadMethodVararg(),
                    _m.getSignature(_page));
            _page.addLocError(badMeth_);
            _m.addNameErrors(badMeth_);
            return true;
        }
        return false;
    }

    private void checkForRecord(AnalyzedPageEl _page, CustList<AbsBk> _bl) {
        if (this instanceof RecordBlock) {
            for (AbsBk b: _bl) {
                if (b instanceof ConstructorBlock) {
                    int where_ = b.getOffset();
                    FoundErrorInterpret unexp_ = new FoundErrorInterpret();
                    unexp_.setFile(getFile());
                    unexp_.setIndexFile(where_);
                    //key word len
                    unexp_.buildError(_page.getAnalysisMessages().getUnexpectedMemberInst(),
                            getFullName()
                    );
                    _page.addLocError(unexp_);
                    b.addErrorBlock(unexp_.getBuiltError());
                }
            }
            for (AnaFormattedRootBlock a:getAllGenericSuperTypesInfo()) {
                for (AbsBk b: ClassesUtil.getDirectChildren(a.getRootBlock())) {
                    if (b instanceof ConstructorBlock) {
                        FoundErrorInterpret unexp_ = new FoundErrorInterpret();
                        unexp_.setFile(getFile());
                        unexp_.setIndexFile(getIdRowCol());
                        //key word len
                        unexp_.buildError(_page.getAnalysisMessages().getUnexpectedMemberInst(),
                                getFullName()
                        );
                        _page.addLocError(unexp_);
                        addNameErrors(unexp_);
                    }
                }
            }
        }
    }

    private void checkMembers(AnalyzedPageEl _page, CustList<AbsBk> _bl) {
        for (AbsBk b: _bl) {
            if (b instanceof InfoBlock || b instanceof RootBlock || b instanceof InternOverrideBlock) {
                continue;
            }
            if (!(b instanceof FunctionBlock)) {
                int where_ = b.getOffset();
                FoundErrorInterpret unexp_ = new FoundErrorInterpret();
                unexp_.setFile(getFile());
                unexp_.setIndexFile(where_);
                //block len
                unexp_.buildError(_page.getAnalysisMessages().getUnexpectedBlockExp());
                _page.addLocError(unexp_);
                b.addErrorBlock(unexp_.getBuiltError());
            }
        }
    }

    public static void validateParameters(NamedFunctionBlock _method, AnalyzedPageEl _page, FileBlock _file) {
        String keyWordValue_ = _page.getKeyWords().getKeyWordValue();
        StringList l_ = _method.getParametersNames();
        StringList seen_ = new StringList();
        int j_ = 0;
        for (String v: l_) {
            _method.addParamErrors();
            _method.addParamWarns();
            TokenErrorMessage res_ = ManageTokens.partParam(_page).checkToken(v, _page);
            if (res_.isError()) {
                FoundErrorInterpret b_;
                b_ = new FoundErrorInterpret();
                b_.setFile(_file);
                b_.setIndexFile(_method.getOffset());
                //param name len
                b_.setBuiltError(res_.getMessage());
                _page.addLocError(b_);
                _method.addParamErrors(j_,b_);
            }
            if (isOverBlock(_method)) {
                NamedCalledFunctionBlock i_ = (NamedCalledFunctionBlock) _method;
                if (i_.getKind() == MethodKind.SET_INDEX && StringUtil.quickEq(v, keyWordValue_)) {
                    FoundErrorInterpret b_;
                    b_ = new FoundErrorInterpret();
                    b_.setFile(_file);
                    b_.setIndexFile(_method.getOffset());
                    //param name len
                    b_.buildError(_page.getAnalysisMessages().getReservedParamName(),
                            v);
                    _page.addLocError(b_);
                    _method.addParamErrors(j_, b_);
                }
            }
            if (StringUtil.contains(seen_, v)){
                FoundErrorInterpret b_;
                b_ = new FoundErrorInterpret();
                b_.setFile(_file);
                b_.setIndexFile(_method.getOffset());
                //param name len
                b_.buildError(_page.getAnalysisMessages().getDuplicatedParamName(),
                        v);
                _page.addLocError(b_);
                _method.addParamErrors(j_,b_);
            } else {
                seen_.add(v);
            }
            j_++;
        }
    }

    private static void buildFieldInfos(CustList<AbsBk> _bl, AnalyzedPageEl _page) {
        StringList idsField_ = new StringList();
        for (AbsBk b: _bl) {
            if (!(b instanceof InfoBlock)) {
                continue;
            }
            InfoBlock method_ = (InfoBlock) b;
            method_.buildImportedType(_page);
            method_.retrieveNames(idsField_, _page);
        }
    }

    private void validateIndexers(CustList<NamedCalledFunctionBlock> _indexersGet, CustList<NamedCalledFunctionBlock> _indexersSet, AnalyzedPageEl _page) {
        for (NamedCalledFunctionBlock i: _indexersSet) {
            MethodId iOne_ = i.getId();
            for (NamedCalledFunctionBlock j: _indexersGet) {
                MethodId iTwo_ = j.getId();
                if (iOne_.eqPartial(iTwo_)) {
                    i.returnTypeGet(j.getImportedReturnType());
                }
            }
            if (i.getReturnTypeGet().isEmpty()) {
                int where_ = i.getOffset();
                FoundErrorInterpret unexp_ = new FoundErrorInterpret();
                unexp_.setFile(getFile());
                unexp_.setIndexFile(where_);
                //method name len
                unexp_.buildError(_page.getAnalysisMessages().getBadIndexerPairGet(),
                        i.getSignature(_page));
                _page.addLocError(unexp_);
                i.addNameErrors(unexp_);
            }
        }
    }

    public abstract void setupBasicOverrides(AnalyzedPageEl _page);

    final void useSuperTypesOverrides(AnalyzedPageEl _page) {
        AnaTypeUtil.buildOverrides(this, _page);
    }

    public final void buildDirectGenericSuperTypes(AnalyzedPageEl _page){
        IntMap< String> rcs_;
        rcs_ = getRowColDirectSuperTypes();
        results.clear();
        _page.getMappingLocal().clear();
        _page.getMappingLocal().putAllMap(getRefMappings());
        int len_ = rcs_.size();
        for (int i = 0; i < len_; i++) {
            int index_ = rcs_.getKey(i);
            String value_ = rcs_.getValue(i);
            boolean wc_ = false;
            String result_;
            if (this instanceof InnerElementBlock) {
                int o_ = 1;
                boolean ok_ = true;
                StringList j_ = new StringList();
                StringList allTypes_ = StringExpUtil.getAllTypes(value_);
                for (String p: allTypes_.mid(1)) {
                    int loc_ = StringUtil.getFirstPrintableCharIndex(p);
                    String trim_ = p.trim();
                    if (!trim_.isEmpty()) {
                        AnaResultPartType resType_ = ResolvingSuperTypes.processAnalyzeHeader(trim_,this, o_ + loc_, _page,true);
                        j_.add(resType_.getResult());
                    } else {
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFile(getFile());
                        un_.setIndexFile(o_ + loc_);
                        //_in len
                        un_.buildError(_page.getAnalysisMessages().getEmptyType());
                        _page.addLocError(un_);
                        addNameErrors(un_);
                        ok_ =  false;
                    }
                    o_ += p.length() + 1;
                }
                result_ = tryGetType(_page, ok_, j_, allTypes_);
            } else if (index_ < 0){
                result_ = value_;
            } else {
                int off_ = StringUtil.getFirstPrintableCharIndex(value_);
                AnaResultPartType resType_ = ResolvingSuperTypes.processAnalyzeHeader(value_.trim(), this, off_ + index_, _page, true);
                wc_ = ResolvingSuperTypes.loopWildCards(this, off_ + index_, _page, resType_);
                result_ = resType_.getResult();
                results.add(resType_);
            }
            tryAddType(_page, wc_, result_);
        }
        if (importedDirectSuperClass.isEmpty()) {
            importedDirectSuperClass = _page.getAliasObject();

        }
    }

    private String tryGetType(AnalyzedPageEl _page, boolean _ok, StringList _j, StringList _allTypes) {
        String res_;
        if (_ok) {
            res_ = AnaInherits.getRealClassName(_allTypes.first(), _j);
        } else {
            res_ = _page.getAliasObject();
        }
        return res_;
    }

    private void tryAddType(AnalyzedPageEl _page, boolean _wc, String _result) {
        String base_ = StringExpUtil.getIdFromAllTypes(_result);
        RootBlock r_ = _page.getAnaClassBody(base_);
        if (this instanceof AnnotationBlock||r_ instanceof InterfaceBlock) {
            importedDirectSuperInterfaces.add(_result);
        } else {
            importedDirectSuperClass = _result;
        }
        if (r_ != null&&!_wc) {
            importedDirectSuperTypes.add(new AnaFormattedRootBlock(r_, _result));
        }
    }

    public final CustList<AnaFormattedRootBlock> fetchAllGenericSuperTypes() {
        CustList<AnaFormattedRootBlock> current_ = new CustList<AnaFormattedRootBlock>(new AnaFormattedRootBlock(this));
        StringList allSeen_ = new StringList();
        CustList<AnaFormattedRootBlock> all_ = new CustList<AnaFormattedRootBlock>();
        while (true) {
            CustList<AnaFormattedRootBlock> next_ = new CustList<AnaFormattedRootBlock>();
            for (AnaFormattedRootBlock c: current_) {
                RootBlock rootBlock_ = c.getRootBlock();
                CustList<AnaFormattedRootBlock> superTypes_ = rootBlock_.getImportedDirectSuperTypesInfo();
                for (AnaFormattedRootBlock t: superTypes_) {
                    AnaFormattedRootBlock a_ = AnaFormattedRootBlock.quickFormat(c,t);
                    String format_ = a_.getFormatted();
                    if (!added(format_,allSeen_,a_,next_)) {
                        continue;
                    }
                    all_.add(a_);
                    allSeen_.add(format_);
                }
            }
            if (next_.isEmpty()) {
                break;
            }
            current_ = next_;
        }
        return all_;
    }

    public final CustList<AnaFormattedRootBlock> fetchAllGenericClasses() {
        CustList<AnaFormattedRootBlock> current_ = new CustList<AnaFormattedRootBlock>(new AnaFormattedRootBlock(this));
        StringList allSeen_ = new StringList();
        CustList<AnaFormattedRootBlock> all_ = new CustList<AnaFormattedRootBlock>();
        while (true) {
            CustList<AnaFormattedRootBlock> next_ = new CustList<AnaFormattedRootBlock>();
            for (AnaFormattedRootBlock c: current_) {
                RootBlock curType_ = c.getRootBlock();
                if (!(curType_ instanceof UniqueRootedBlock)) {
                    continue;
                }
                all_.add(c);
                allSeen_.add(c.getFormatted());
                CustList<AnaFormattedRootBlock> superTypes_ = curType_.getImportedDirectSuperTypesInfo();
                for (AnaFormattedRootBlock t: superTypes_) {
                    AnaFormattedRootBlock a_ = AnaFormattedRootBlock.quickFormat(c,t);
                    String format_ = a_.getFormatted();
                    added(format_,allSeen_, a_, next_);
                }
            }
            if (next_.isEmpty()) {
                break;
            }
            current_ = next_;
        }
        return all_;
    }
    private static boolean added(String _type, StringList _list,AnaFormattedRootBlock _n, CustList<AnaFormattedRootBlock> _next) {
        if (StringUtil.contains(_list, _type)) {
            return false;
        }
        _next.add(_n);
        return true;
    }
    public final void checkCompatibilityBounds(AnalyzedPageEl _page) {
        StringMap<StringList> vars_ = extractVarTypes();
        String objectClassName_ = _page.getAliasObject();
        for (TypeVar t: getParamTypesMapValues()) {
            CustList<MethodIdAncestors> signatures_;
            signatures_ = new CustList<MethodIdAncestors>();
            StringList upper_ = Mapping.getAllUpperBounds(vars_, t.getName(),objectClassName_);
            CustList<CustList<MethodInfo>> methods_ = OperationNode.fetchParamClassAncMethods(upper_, _page);
            for (CustList<MethodInfo> l: methods_) {
                for (MethodInfo e: l) {
                    if (e.getConstraints().getKind() != MethodAccessKind.INSTANCE) {
                        continue;
                    }
                    addClass(signatures_, new MethodIdAncestor(e.getFormatted(),e.getAncestor()), e);
                }
            }
            String fullName_ = "";
            lookForErrors(vars_, signatures_, fullName_,t.getName(), _page);
        }
    }

    private void lookForErrors(StringMap<StringList> _vars, CustList<MethodIdAncestors> _signatures, String _fullName, String _virtualType, AnalyzedPageEl _page) {
        CustList<MethodIdAncestors> sub_;
        sub_ = RootBlock.getAllOverridingMethods(_signatures, _page);
        CustList<MethodIdAncestors> er_;
        er_ = RootBlock.areCompatibleIndexer(_fullName, sub_);
        for (MethodIdAncestors e: er_) {
            errorsRetTypes(_page, e, _page.getAnalysisMessages().getReturnTypes());
        }
        er_ = RootBlock.areCompatibleFinalReturn(_fullName, _vars, sub_, _page);
        for (MethodIdAncestors e: er_) {
            CustList<MethodInfo> fClasses_ = finalMethods(e);
            MethodInfo subInt_ = fClasses_.first();
            String subType_ = subInt_.getReturnType();
            for (MethodInfo s: e.getMethodInfos()) {
                String formattedSup_ = s.getReturnType();
                if (!AnaInherits.isReturnCorrect(formattedSup_, subType_,_vars, _page)) {
                    FoundErrorInterpret err_ = new FoundErrorInterpret();
                    err_.setFile(getFile());
                    err_.setIndexFile(rootBlockContent.getIdRowCol());
                    //original id len
                    err_.buildError(_page.getAnalysisMessages().getFinalNotSubReturnType(),
                            subType_,
                            subInt_.getConstraints().getSignature(_page.getDisplayedStrings()),
                            subInt_.getClassName(),
                            formattedSup_,
                            s.getConstraints().getSignature(_page.getDisplayedStrings()),
                            s.getClassName());
                    _page.addLocError(err_);
                    addNameErrors(err_);
                }
            }
        }
        er_ = RootBlock.areCompatibleMerged(_fullName, _vars, sub_, _page);
        for (MethodIdAncestors e: er_) {
            errorsRetTypes(_page, e, _page.getAnalysisMessages().getTwoReturnTypes());
        }
        er_ = RootBlock.areModifierCompatible(sub_);
        for (MethodIdAncestors e: er_) {
            FoundErrorInterpret err_ = new FoundErrorInterpret();
            err_.setFile(getFile());
            err_.setIndexFile(rootBlockContent.getIdRowCol());
            //original id len
            err_.buildError(_page.getAnalysisMessages().getTwoFinal(),
                    _virtualType,
                    e.getClassMethodId().getClassMethodId().getSignature(_page.getDisplayedStrings()));
            _page.addLocError(err_);
            addNameErrors(err_);
        }
    }

    private void errorsRetTypes(AnalyzedPageEl _page, MethodIdAncestors _e, String _message) {
        StringList retClasses_ = new StringList();
        StringList types_ = new StringList();
        feedTypes(_e, retClasses_, types_);
        retClasses_.removeDuplicates();
        types_.removeDuplicates();
        FoundErrorInterpret err_ = new FoundErrorInterpret();
        err_.setFile(getFile());
        err_.setIndexFile(rootBlockContent.getIdRowCol());
        //original id len
        err_.buildError(_message,
                _e.getClassMethodId().getClassMethodId().getSignature(_page.getDisplayedStrings()),
                StringUtil.join(types_,ExportCst.JOIN_TYPES),
                StringUtil.join(retClasses_,ExportCst.JOIN_TYPES));
        _page.addLocError(err_);
        addNameErrors(err_);
    }

    private void feedTypes(MethodIdAncestors _e, StringList _retClasses, StringList _types) {
        for (MethodInfo m: _e.getMethodInfos()) {
            _retClasses.add(m.getReturnType());
            _types.add(m.getClassName());
        }
    }

    public final void checkCompatibility(AnalyzedPageEl _page) {
        StringMap<StringList> vars_ = extractVarTypes();
        CustList<MethodIdAncestors> ov_;
        ov_ = new CustList<MethodIdAncestors>();
        CustList<CustList<MethodInfo>> methods_ = OperationNode.fetchParamClassAncMethods(new StringList(getGenericString()), _page);
        for (CustList<MethodInfo> l: methods_) {
            for (MethodInfo e: l) {
                if (e.getConstraints().getKind() != MethodAccessKind.INSTANCE) {
                    continue;
                }
                addClass(ov_, new MethodIdAncestor(e.getFormatted(),e.getAncestor()), e);
            }
        }
        String fullName_ = getFullName();
        lookForErrors(vars_, ov_, fullName_,fullName_, _page);
    }
    public final void checkImplements(AnalyzedPageEl _page) {
        boolean concreteClass_ = mustImplement();
        for (NamedCalledFunctionBlock b: overridableBlocks) {
            if (b.isAbstractMethod() && b.getFirstChild() != null) {
                FoundErrorInterpret err_;
                err_ = new FoundErrorInterpret();
                err_.setFile(getFile());
                err_.setIndexFile(b.getNameOffset());
                //last char (brace) in header
                err_.buildError(
                        _page.getAnalysisMessages().getAbstractMethodBody(),
                        getFullName(),
                        b.getSignature(_page));
                _page.addLocError(err_);
                b.addNameErrors(err_);
            }
        }
        if (concreteClass_) {
            for (NamedCalledFunctionBlock b: overridableBlocks) {
                if (b.isAbstractMethod()) {
                    FoundErrorInterpret err_;
                    err_ = new FoundErrorInterpret();
                    err_.setFile(getFile());
                    err_.setIndexFile(b.getNameOffset());
                    //abstract key word
                    err_.buildError(
                            _page.getAnalysisMessages().getAbstractMethodConc(),
                            getFullName(),
                            b.getSignature(_page));
                    _page.addLocError(err_);
                    b.addNameErrors(err_);
                }
            }
        }
    }

    public static CustList<MethodIdAncestors> getAllOverridingMethods(
            CustList<MethodIdAncestors> _methodIds, AnalyzedPageEl _page) {
        CustList<MethodIdAncestors> map_;
        map_ = new CustList<MethodIdAncestors>();
        for (MethodIdAncestors e: _methodIds) {
            StringMap<MethodInfo> defs_ = new StringMap<MethodInfo>();
            CustList<MethodInfo> value_ = e.getMethodInfos();
            StringList list_ = new StringList(new CollCapacity(value_.size()));
            for (MethodInfo v: value_) {
                defs_.put(v.getClassName(), v);
                list_.add(v.getClassName());
            }
            list_ = AnaTypeUtil.getSubclasses(list_, _page);
            list_.removeDuplicates();
            CustList<MethodInfo> out_ = new CustList<MethodInfo>(new CollCapacity(list_.size()));
            for (String v: list_) {
                out_.add(defs_.getVal(v));
            }
            MethodIdAncestors l_ = new MethodIdAncestors(e.getClassMethodId());
            l_.getMethodInfos().addAllElts(out_);
            map_.add(l_);
        }
        return map_;
    }

    private static CustList<MethodIdAncestors> areCompatibleIndexer(
            String _fullName,
            CustList<MethodIdAncestors> _methodIds) {
        CustList<MethodIdAncestors> output_;
        output_ = new CustList<MethodIdAncestors>();
        for (MethodIdAncestors e: _methodIds) {
            MethodIdAncestor cst_ = e.getClassMethodId();
            CustList<MethodInfo> classes_ = e.getMethodInfos();
            boolean skip_ = skip(_fullName, classes_);
            if (skip_) {
                continue;
            }
            if (!StringExpUtil.isDollarWord(cst_.getClassMethodId().getName())) {
                StringList retClasses_ = new StringList();
                for (MethodInfo s: e.getMethodInfos()) {
                    retClasses_.add(s.getReturnType());
                }
                //indexer
                if (!retClasses_.onlyOneElt()) {
                    for (MethodInfo c: classes_) {
                        addClass(output_, e.getClassMethodId(), c);
                    }
                }
            }
        }
        return output_;
    }

    private static CustList<MethodIdAncestors> areCompatibleFinalReturn(
            String _fullName,
            StringMap<StringList> _vars,
            CustList<MethodIdAncestors> _methodIds, AnalyzedPageEl _page) {
        CustList<MethodIdAncestors> output_;
        output_ = new CustList<MethodIdAncestors>();
        for (MethodIdAncestors e: _methodIds) {
            CustList<MethodInfo> classes_ = e.getMethodInfos();
            boolean skip_ = skip(_fullName, classes_);
            if (skip_) {
                continue;
            }
            lookForFinal(_vars, _page, output_, e);
        }
        return output_;
    }

    private static void lookForFinal(StringMap<StringList> _vars, AnalyzedPageEl _page, CustList<MethodIdAncestors> _output, MethodIdAncestors _e) {
        MethodIdAncestor cst_ = _e.getClassMethodId();
        CustList<MethodInfo> fClasses_ = finalMethods(_e);
        if (StringExpUtil.isDollarWord(cst_.getClassMethodId().getName()) && fClasses_.size() == 1) {
            MethodInfo subInt_ = fClasses_.first();
            String subType_ = subInt_.getReturnType();
            boolean err_ = false;
            for (MethodInfo s : _e.getMethodInfos()) {
                String formattedSup_ = s.getReturnType();
                if (!AnaInherits.isReturnCorrect(formattedSup_, subType_, _vars, _page)) {
                    err_ = true;
                    addClass(_output, cst_, s);
                }
            }
            if (err_) {
                addClass(_output, cst_, subInt_);
            }
        }
    }

    private static CustList<MethodInfo> finalMethods(MethodIdAncestors _e) {
        CustList<MethodInfo> fClasses_ = new CustList<MethodInfo>();
        for (MethodInfo s: _e.getMethodInfos()) {
            if (s.isFinalMethod()) {
                fClasses_.add(s);
            }
        }
        return fClasses_;
    }

    private static CustList<MethodIdAncestors> areCompatibleMerged(
            String _fullName,
            StringMap<StringList> _vars,
            CustList<MethodIdAncestors> _methodIds, AnalyzedPageEl _page) {
        CustList<MethodIdAncestors> output_;
        output_ = new CustList<MethodIdAncestors>();
        for (MethodIdAncestors e: _methodIds) {
            CustList<MethodInfo> classes_ = e.getMethodInfos();
            boolean skip_ = skip(_fullName, classes_);
            if (skip_) {
                continue;
            }
            lookForMerged(_vars, _page, output_, e);
        }
        return output_;
    }

    private static void lookForMerged(StringMap<StringList> _vars, AnalyzedPageEl _page, CustList<MethodIdAncestors> _output, MethodIdAncestors _e) {
        MethodIdAncestor cst_ = _e.getClassMethodId();
        CustList<MethodInfo> classes_ = _e.getMethodInfos();
        CustList<MethodInfo> fClasses_ = new CustList<MethodInfo>();
        StringList retClasses_ = new StringList();
        for (MethodInfo s: _e.getMethodInfos()) {
            if (s.isFinalMethod()) {
                fClasses_.add(s);
            }
            retClasses_.add(s.getReturnType());
        }
        if (StringExpUtil.isDollarWord(cst_.getClassMethodId().getName()) && fClasses_.size() != 1) {
            StringList subs_ = subs(_vars, _page, retClasses_);
            if (!subs_.onlyOneElt()) {
                for (MethodInfo c : classes_) {
                    addClass(_output, _e.getClassMethodId(), c);
                }
            }
        }
    }

    private static StringList subs(StringMap<StringList> _vars, AnalyzedPageEl _page, StringList _retClasses) {
        StringList subs_ = new StringList();
        int len_ = _retClasses.size();
        for (int i = 0; i < len_; i++) {
            String cur_ = _retClasses.get(i);
            boolean sub_ = true;
            for (int j = 0; j < len_; j++) {
                String other_ = _retClasses.get(j);
                if (!StringUtil.quickEq(cur_, other_) && !AnaInherits.isReturnCorrect(other_, cur_, _vars, _page)) {
                    sub_ = false;
                    break;
                }
            }
            if (sub_) {
                subs_.add(cur_);
            }
        }
        return subs_;
    }

    private static boolean skip(String _fullName, CustList<MethodInfo> _classes) {
        boolean skip_ = false;
        for (MethodInfo m: _classes) {
            String id_ = StringExpUtil.getIdFromAllTypes(m.getClassName());
            if (StringUtil.quickEq(id_,_fullName)) {
                skip_ = true;
                break;
            }
        }
        return skip_;
    }
    @Override
    public CustList<StringList> getBoundAll() {
        CustList<StringList> boundsAll_ = new CustList<StringList>();
        for (TypeVar t: getParamTypesMapValues()) {
            boolean contained_ = false;
            for (TypeVar u: rootBlockContent.getParamTypes()) {
                if (!StringUtil.quickEq(t.getName(),u.getName())) {
                    continue;
                }
                contained_ = true;
            }
            if (!contained_) {
                continue;
            }
            StringList localBound_ = new StringList();
            for (String b: t.getConstraints()) {
                localBound_.add(b);
            }
            boundsAll_.add(localBound_);
        }
        return boundsAll_;
    }

    public CustList<StringList> getBoundAllAll() {
        CustList<StringList> boundsAll_ = new CustList<StringList>();
        for (TypeVar t: getParamTypesMapValues()) {
            StringList localBound_ = new StringList();
            for (String b: t.getConstraints()) {
                localBound_.add(b);
            }
            boundsAll_.add(localBound_);
        }
        return boundsAll_;
    }
    private static CustList<MethodIdAncestors> areModifierCompatible(
            CustList<MethodIdAncestors> _methodIds) {
        CustList<MethodIdAncestors> output_;
        output_ = new CustList<MethodIdAncestors>();
        for (MethodIdAncestors e: _methodIds) {
            MethodIdAncestor cst_ = e.getClassMethodId();
            CustList<MethodInfo> fClasses_ = finalMethods(e);
            if (fClasses_.size() > 1) {
                for (MethodInfo c: fClasses_) {
                    addClass(output_, cst_, c);
                }
            }
        }
        return output_;
    }

    private static void addClass(CustList<MethodIdAncestors> _map, MethodIdAncestor _key, MethodInfo _class) {
        boolean found_ = false;
        for (MethodIdAncestors m : _map) {
            if (m.getClassMethodId().eq(_key)) {
                m.getMethodInfos().add(_class);
                found_ = true;
                break;
            }
        }
        if (!found_) {
            MethodIdAncestors m_ = new MethodIdAncestors(_key);
            m_.getMethodInfos().add(_class);
            _map.add(m_);
        }
    }

    public void validateConstructors(AnalyzedPageEl _page) {
        boolean opt_ = optionalCallConstr(_page);
        CustList<ConstructorBlock> ctors_ = new CustList<ConstructorBlock>();
        IdMap<ConstructorId,ConstructorBlock> ctorsId_ = new IdMap<ConstructorId,ConstructorBlock>();
        for (ConstructorBlock b: constructorBlocks) {
            ctors_.add(b);
            ctorsId_.addEntry(b.getId(), b);
        }
        if (ctors_.isEmpty() && !opt_) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(getFile());
            un_.setIndexFile(getOffset());
            //original id len
            un_.buildError(_page.getAnalysisMessages().getUndefinedSuperCtor(),
                    getFullName());
            _page.addLocError(un_);
            addNameErrors(un_);
        }
        for (ConstructorBlock c: ctors_) {
            c.setupInstancingStep(_page);
        }
        for (ConstructorBlock c: ctors_) {
            if (c.implicitConstr() && !opt_) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFile(getFile());
                un_.setIndexFile(c.getOffset());
                //original id len
                un_.buildError(_page.getAnalysisMessages().getUndefinedSuperCtorCall(),
                        c.getSignature(_page));
                _page.addLocError(un_);
                c.addNameErrors(un_);
            }
        }
        for (EntryCust<ConstructorId, ConstructorBlock> e: ctorsId_.entryList()) {
            ConstructorBlock f = e.getValue();
            ConstructorId co_ = convert(e.getKey());
            CustList<ConstructorId> cycle_ = new CustList<ConstructorId>();
            while (true) {
                ConstructorBlock found_ = exitCycle(_page,f,ctorsId_,co_,cycle_);
                if (found_ == null) {
                    break;
                }
                co_ = convert(found_.getConstIdSameClass());
            }
        }
    }
    private ConstructorBlock exitCycle(AnalyzedPageEl _page, ConstructorBlock _f,IdMap<ConstructorId, ConstructorBlock> _ctorsId, ConstructorId _co, CustList<ConstructorId> _cycle) {
        ConstructorBlock found_ = null;
        _cycle.add(_co);
        for (EntryCust<ConstructorId, ConstructorBlock> c : _ctorsId.entryList()) {
            if (_co.eq(c.getKey())) {
                found_ = c.getValue();
                break;
            }
        }
        if (found_ == null) {
            return null;
        }
        if (found_ == _f && _cycle.size() > 1) {
            FoundErrorInterpret cyclic_ = new FoundErrorInterpret();
            StringList c_ = new StringList();
            for (ConstructorId c: _cycle) {
                c_.add(c.getSignature(_page.getDisplayedStrings()));
            }
            cyclic_.setFile(getFile());
            cyclic_.setIndexFile(getOffset());
            //original contructor id len
            cyclic_.buildError(_page.getAnalysisMessages().getCyclicCtorCall(),
                    StringUtil.join(c_,ExportCst.JOIN_TYPES),
                    getFullName());
            _page.addLocError(cyclic_);
            found_.addNameErrors(cyclic_);
            return null;
        }
        return found_;
    }

    private static ConstructorId convert(ConstructorId _ctor) {
        if (_ctor == null) {
            return new ConstructorId("",new StringList(),true);
        }
        return _ctor;
    }

    private boolean optionalCallConstr(AnalyzedPageEl _page) {
        if (!(this instanceof UniqueRootedBlock)) {
            return true;
        }
        String superClass_ = getImportedDirectGenericSuperClass();
        String superClassId_ = StringExpUtil.getIdFromAllTypes(superClass_);
        RootBlock clMeta_ = _page.getAnaClassBody(superClassId_);
        if (clMeta_ == null) {
            return true;
        }
        CustList<ConstructorBlock> ctors_ = new CustList<ConstructorBlock>();
        for (AbsBk b: ClassesUtil.getDirectChildren(clMeta_)) {
            if (b instanceof ConstructorBlock) {
                ctors_.add((ConstructorBlock) b);
            }
        }
        if (ctors_.isEmpty()) {
            return true;
        }
        for (ConstructorBlock c: ctors_) {
            Accessed a_ = new Accessed(c.getAccess(), clMeta_.getPackageName(), clMeta_);
            if (!ContextUtil.canAccess(getFullName(), a_, _page)) {
                continue;
            }
            if (c.getId().getParametersTypesLength() == 0) {
                return true;
            }
        }
        return false;
    }

    public abstract boolean mustImplement();

    public int getNbOperators() {
        return nbOperators;
    }

    public int getNameLength() {
        return nameLength;
    }

    public CustList<AnaResultPartType> getPartsStaticInitInterfacesOffset() {
        return partsStaticInitInterfacesOffset;
    }

    public CustList<AnaResultPartType> getPartsInstInitInterfacesOffset() {
        return partsInstInitInterfacesOffset;
    }

    public String getImportedDirectGenericSuperClass(){
        return importedDirectSuperClass;
    }
    public CustList<RootBlock> getStaticInitImportedInterfaces() {
        return staticInitImportedInterfaces;
    }

    public CustList<AnaFormattedRootBlock> getInstanceInitImportedInterfaces() {
        return instanceInitImportedInterfaces;
    }

    public StringList getImportedDirectSuperInterfaces() {
        return importedDirectSuperInterfaces;
    }

    public void addNameErrors(FoundErrorInterpret _error) {
        addNameErrors(_error.getBuiltError());
    }

    public void addNameErrors(String _error) {
        if (nameLength == 0){
            addErrorBlock(_error);
        } else {
            nameErrors.add(_error);
        }
    }
    public StringList getNameErrors() {
        return nameErrors;
    }

    public final RootBlock getOuterParent() {
        RootBlock t = this;
        RootBlock o = this;
        while (t != null) {
            o = t;
            t = t.getParentType();
        }
        return o;
    }
    public String getWildCardElement() {
        StringList allElements_ = new StringList();
        for (AbsBk e: ClassesUtil.getDirectChildren(this)) {
            if (e instanceof InnerTypeOrElement) {
                String type_ = ((InnerTypeOrElement)e).getRealImportedClassName();
                allElements_.add(type_);
            }
        }
        String className_;
        if (allElements_.onlyOneElt()) {
            className_ = allElements_.first();
        } else {
            className_ = getWildCardString();
        }
        return className_;
    }

    public final String getWildCardString() {
        StringBuilder generic_ = initGeneric();
        CustList<RootBlock> pars_ = getSelfAndParentTypes();
        RootBlock previous_ = beginType(generic_, pars_);
        for (RootBlock r: pars_) {
            appendName(generic_, previous_, r);
            if (!r.rootBlockContent.getParamTypes().isEmpty()) {
                StringList vars_ = new StringList();
                int count_ = r.rootBlockContent.getParamTypes().size();
                for (int i = 0; i < count_; i++) {
                    vars_.add(StringExpUtil.SUB_TYPE);
                }
                generic_.append(StringExpUtil.TEMPLATE_BEGIN);
                generic_.append(StringUtil.join(vars_, StringExpUtil.TEMPLATE_SEP));
                generic_.append(StringExpUtil.TEMPLATE_END);
            }
            previous_ = r;
        }
        return generic_.toString();
    }

    public final CustList<RootBlock> getAllParentTypesReverse() {
        return getAllParentTypes().getReverse();
    }

    public boolean isSubTypeOf(String _fullName, AnalyzedPageEl _an) {
        return isSubTypeOf(_fullName);
    }

    public boolean isSubTypeOf(AnaGeneType _fullName) {
        if (this == _fullName) {
            return true;
        }
        return allSuperTypesInfo.containsObj(_fullName);
    }

    private boolean isSubTypeOf(String _parName) {
        if (StringUtil.quickEq(_parName,getFullName())) {
            return true;
        }
        return StringUtil.contains(getAllSuperTypes(), _parName);
    }

    public StringList getAllSuperTypes(){
        return allSuperTypes;
    }

    public IdList<AnaGeneType> getAllSuperTypesInfo() {
        return allSuperTypesInfo;
    }

    public final String getGenericString() {
        StringBuilder generic_ = initGeneric();
        CustList<RootBlock> pars_ = getSelfAndParentTypes();
        RootBlock previous_ = beginType(generic_, pars_);
        for (RootBlock r: pars_) {
            appendName(generic_, previous_, r);
            if (!r.rootBlockContent.getParamTypes().isEmpty()) {
                StringList vars_ = new StringList();
                for (TypeVar t: r.rootBlockContent.getParamTypes()) {
                    vars_.add(StringUtil.concat(AnaInherits.PREFIX_VAR_TYPE,t.getName()));
                }
                generic_.append(StringExpUtil.TEMPLATE_BEGIN);
                generic_.append(StringUtil.join(vars_, StringExpUtil.TEMPLATE_SEP));
                generic_.append(StringExpUtil.TEMPLATE_END);
            }
            previous_ = r;
        }
        return generic_.toString();
    }

    private StringBuilder initGeneric() {
        String pkg_ = getPackageName();
        StringBuilder generic_ = new StringBuilder();
        RootBlock.addPkgIfNotEmpty(pkg_, generic_);
        return generic_;
    }

    private static void appendName(StringBuilder _generic, RootBlock _previous, RootBlock _r) {
        appendParts(_generic, _previous, _r);
        _generic.append(_r.getSuffixedName());
    }

    private RootBlock beginType(StringBuilder _generic, CustList<RootBlock> _pars) {
        RootBlock previous_ = null;
        for (RootBlock r: _pars.first().getAllParentTypesReverse()) {
            appendParts(_generic, previous_, r);
            _generic.append(r.getSuffixedName());
            previous_ = r;
        }
        return previous_;
    }

    public StringList getParamTypesValues() {
        StringList l_ = new StringList();
        for (RootBlock r: getSelfAndParentTypes()) {
            for (TypeVar t: r.rootBlockContent.getParamTypes()) {
                l_.add(t.getName());
            }
        }
        return l_;
    }
    public Ints getTypeVarCounts() {
        Ints generic_ = new Ints();
        CustList<RootBlock> pars_ = getSelfAndParentTypes();
        CustList<RootBlock> stPars_ = pars_.first().getAllParentTypesReverse();
        int len_ = stPars_.size();
        for (int i = 0; i < len_; i++) {
            generic_.add(0);
        }
        for (RootBlock r: pars_) {
            generic_.add(r.rootBlockContent.getParamTypes().size());
        }
        return generic_;
    }

    public AccessedBlock getAccessedBlock() {
        return accessedBlock;
    }

    public void setAccessedBlock(AccessedBlock _accessedBlock) {
        this.accessedBlock = _accessedBlock;
    }

    public int getNumberAll() {
        return numberAll;
    }

    public void setNumberAll(int _number) {
        numberAll = _number;
    }

    public CustList<AnaFormattedRootBlock> getImportedDirectSuperTypesInfo() {
        return importedDirectSuperTypes;
    }

    public StringList getAllReservedInners() {
        return allReservedInners;
    }

    public StringMap<Integer> getCounts() {
        return counts;
    }

    public StringMap<Integer> getCountsAnon() {
        return countsAnon;
    }

    public void setSuffix(String _suffix) {
        this.rootBlockContent.setSuffix(_suffix);
    }

    public StringMap<MappingLocalType> getRefMappings() {
        return AnaTypeUtil.getRefMappings(mappings);
    }

    public StringMap<MappingLocalType> getMappings() {
        return mappings;
    }

    public ConstructorBlock getEmptyCtor() {
        return emptyCtor;
    }

    public void setEmptyCtor(ConstructorBlock _emptyCtor) {
        emptyCtor = _emptyCtor;
    }

    public int getCountsAnonFct() {
        return countsAnonFct;
    }

    public void setCountsAnonFct(int _countsAnonFct) {
        this.countsAnonFct = _countsAnonFct;
    }

    public AnonymousElements getElementsType() {
        return elementsType;
    }

    public CustList<NamedCalledFunctionBlock> getOverridableBlocks() {
        return overridableBlocks;
    }

    public CustList<NamedCalledFunctionBlock> getAnnotationsMethodsBlocks() {
        return annotationsMethodsBlocks;
    }

    public CustList<ConstructorBlock> getConstructorBlocks() {
        return constructorBlocks;
    }

    public CustList<InfoBlock> getFieldsBlocks() {
        return fieldsBlocks;
    }

    public CustList<FieldBlock> getFieldsInstBlocks() {
        return fieldsInstBlocks;
    }

    public int getCountInit() {
        return getInstanceBlocks().size()+getStaticBlocks().size();
    }

    public CustList<InstanceBlock> getInstanceBlocks(){
        return instanceBlocks;
    }

    public CustList<StaticBlock> getStaticBlocks(){
        return staticBlocks;
    }
    public CustList<RootBlock> getChildrenRootBlocks() {
        return childrenRootBlocks;
    }

    public boolean isReference() {
        return reference;
    }

    public void setReference(boolean _reference) {
        this.reference = _reference;
    }

    public CustList<NamedCalledFunctionBlock> getValidMethods() {
        return validMethods;
    }

    public CustList<ConstructorBlock> getValidCtors() {
        return validCtors;
    }
}
