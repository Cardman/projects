package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.MethodHeaderInfo;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.accessing.Accessed;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.opers.util.MethodInfo;
import code.expressionlanguage.analyze.reach.opers.ReachOperationUtil;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.types.*;
import code.expressionlanguage.analyze.util.*;
import code.expressionlanguage.common.*;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.errors.custom.*;
import code.expressionlanguage.analyze.files.OffsetAccessInfo;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.fwd.blocks.AnaRootBlockContent;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.linkage.ExportCst;
import code.expressionlanguage.options.KeyWords;
import code.util.*;
import code.util.core.StringUtil;

public abstract class RootBlock extends BracedBlock implements AccessedBlock,AnnotableBlock,AnaGeneType,AnaInheritedType {
    private OperatorBlock operator;

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
    private final IntMap< Boolean> explicitDirectSuperTypes = new IntMap< Boolean>();

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

    private final StringList annotations = new StringList();

    private final Ints annotationsIndexes = new Ints();
    private final CustList<AnaFormattedRootBlock> allGenericSuperTypesInfo = new CustList<AnaFormattedRootBlock>();
    private final CustList<AnaFormattedRootBlock> allGenericClassesInfo = new CustList<AnaFormattedRootBlock>();
    private final CustList<OperationNode> roots = new CustList<OperationNode>();
    private final CustList<ResultExpression> resList = new CustList<ResultExpression>();
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
    private final CustList<AnonymousTypeBlock> anonymousRoot = new CustList<AnonymousTypeBlock>();
    private final CustList<NamedCalledFunctionBlock> anonymousRootFct = new CustList<NamedCalledFunctionBlock>();
    private final CustList<SwitchMethodBlock> switchMethodRoots = new CustList<SwitchMethodBlock>();
    private final CustList<NamedCalledFunctionBlock> overridableBlocks = new CustList<NamedCalledFunctionBlock>();
    private final CustList<NamedCalledFunctionBlock> annotationsMethodsBlocks = new CustList<NamedCalledFunctionBlock>();
    private final CustList<ConstructorBlock> constructorBlocks = new CustList<ConstructorBlock>();
    private final CustList<InfoBlock> fieldsBlocks = new CustList<InfoBlock>();
    private final CustList<FieldBlock> fieldsInstBlocks = new CustList<FieldBlock>();
    private final CustList<InstanceBlock> instanceBlocks = new CustList<InstanceBlock>();
    private final CustList<StaticBlock> staticBlocks = new CustList<StaticBlock>();
    private final CustList<RootBlock> childrenRootBlocks = new CustList<RootBlock>();
    RootBlock(int _idRowCol,
              String _packageName, OffsetAccessInfo _access, String _templateDef,
              IntMap<String> _directSuperTypes, int _offset, String _name) {
        super(_offset);
        allOverridingMethods = new CustList<OverridingMethodDto>();
        rootBlockContent = new AnaRootBlockContent();
        rootBlockContent.setPackageName(StringExpUtil.removeDottedSpaces(_packageName));
        access = _access.getInfo();
        accessOffset = _access.getOffset();
        templateDef = _templateDef;
        rootBlockContent.setIdRowCol(_idRowCol);
        rootBlockContent.setName(_name.trim());
        rowColDirectSuperTypes = _directSuperTypes;
        for (EntryCust<Integer, String> t: _directSuperTypes.entryList()) {
            directSuperTypes.add(t.getValue());
            explicitDirectSuperTypes.put(t.getKey(), true);
        }
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

    public CustList<AnaFormattedRootBlock> getAllGenericClassesInfo() {
        return allGenericClassesInfo;
    }

    public IntMap< Boolean> getExplicitDirectSuperTypes() {
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
        int len_ = annotationsIndexes.size();
        roots.clear();
        for (int i = 0; i < len_; i++) {
            int begin_ = annotationsIndexes.get(i);
            _page.setGlobalOffset(begin_);
            _page.zeroOffset();
            Calculation c_ = Calculation.staticCalculation(MethodAccessKind.STATIC);
            OperationNode r_ = ElUtil.getRootAnalyzedOperationsReadOnly(resList.get(i), annotations.get(i).trim(), c_, _page);
            ReachOperationUtil.tryCalculate(r_, _page);
            roots.add(r_);
        }
    }

    public StringList getAnnotations() {
        return annotations;
    }


    public Ints getAnnotationsIndexes() {
        return annotationsIndexes;
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
        StringMap<StringList> vars_ = new StringMap<StringList>();
        for (TypeVar t: getParamTypesMapValues()) {
            vars_.put(t.getName(), t.getConstraints());
        }
        for (OverridingMethodDto e: allOverridingMethods) {
            CustList<GeneStringOverridable> locGeneInt_ = new CustList<GeneStringOverridable>();
            CustList<GeneStringOverridable> locGeneCl_ = new CustList<GeneStringOverridable>();
            for (GeneStringOverridable c: e.getMethodIds()) {
                RootBlock r_ = c.getType();
                if (r_ instanceof InterfaceBlock) {
                    locGeneInt_.add(c);
                }
                if (r_ instanceof ClassBlock) {
                    locGeneCl_.add(c);
                }
            }
            for (GeneStringOverridable i: locGeneInt_) {
                NamedCalledFunctionBlock supInt_ = i.getBlock();
                String name_ = i.getGeneString();
                MethodId id_ = supInt_.getId();
                for (GeneStringOverridable c: locGeneCl_) {
                    String nameCl_ = c.getGeneString();
                    NamedCalledFunctionBlock supCl_ = c.getBlock();
                    MethodId idCl_ = supCl_.getId();
                    if (supInt_.getAccess().isStrictMoreAccessibleThan(supCl_.getAccess())) {
                        FoundErrorInterpret err_;
                        err_ = new FoundErrorInterpret();
                        err_.setFileName(getFile().getFileName());
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
                    String formattedRetDer_ = AnaInherits.quickFormat(c.getFormat(), retBase_);
                    String formattedRetBase_ = AnaInherits.quickFormat(i.getFormat(), retInt_);
                    if (supCl_.mustHaveSameRet()) {
                        if (!StringUtil.quickEq(formattedRetBase_, formattedRetDer_)) {
                            FoundErrorInterpret err_;
                            err_ = new FoundErrorInterpret();
                            err_.setFileName(getFile().getFileName());
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
                        continue;
                    }
                    if (!AnaInherits.isReturnCorrect(formattedRetBase_, formattedRetDer_, vars_, _page)) {
                        FoundErrorInterpret err_;
                        err_ = new FoundErrorInterpret();
                        err_.setFileName(getFile().getFileName());
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
            }
        }
    }
    public final RootBlock getParentType() {
        return rootBlockContent.getParentType();
    }

    public final void setParentType(RootBlock _parentType) {
        rootBlockContent.setParentType(_parentType);
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
        _page.getMappingLocal().putAllMap(mappings);
        for (RootBlock r: getSelfAndParentTypes()) {
            if (r == this) {
                if (r instanceof AnonymousTypeBlock) {
                    for (TypeVar t: rootBlockContent.getParamTypes()) {
                        rootBlockContent.getParamTypesMap().addEntry(t.getName(), t);
                    }
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
                for (EntryCust<String,TypeVar> e: r.rootBlockContent.getParamTypesMap().entryList()) {
                    boolean exist_ = false;
                    for (TypeVar t: r.getParamTypes()) {
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
        CustList<MethodId> idMethods_ = new CustList<MethodId>();
        CustList<NamedCalledFunctionBlock> indexersGet_ = new CustList<NamedCalledFunctionBlock>();
        CustList<NamedCalledFunctionBlock> indexersSet_ = new CustList<NamedCalledFunctionBlock>();
        CustList<ConstructorId> idConstructors_ = new CustList<ConstructorId>();
        CustList<AbsBk> bl_;
        bl_ = ClassesUtil.getDirectChildren(this);
        KeyWords keyWords_ = _page.getKeyWords();
        for (AbsBk b: bl_) {
            if (b instanceof InfoBlock) {
                continue;
            }
            if (b instanceof RootBlock) {
                continue;
            }
            if (b instanceof InternOverrideBlock) {
                continue;
            }
            if (!(b instanceof FunctionBlock)) {
                int where_ = b.getOffset();
                FoundErrorInterpret unexp_ = new FoundErrorInterpret();
                unexp_.setFileName(getFile().getFileName());
                unexp_.setIndexFile(where_);
                //block len
                unexp_.buildError(_page.getAnalysisMessages().getUnexpectedBlockExp());
                _page.addLocError(unexp_);
                b.addErrorBlock(unexp_.getBuiltError());
            }
        }
        if (this instanceof RecordBlock) {
            for (AbsBk b: bl_) {
                if (b instanceof ConstructorBlock) {
                    int where_ = b.getOffset();
                    FoundErrorInterpret unexp_ = new FoundErrorInterpret();
                    unexp_.setFileName(getFile().getFileName());
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
                        unexp_.setFileName(getFile().getFileName());
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
        CustList<MethodHeaderInfo> unary_ = new CustList<MethodHeaderInfo>();
        CustList<MethodHeaderInfo> binaryFirst_ = new CustList<MethodHeaderInfo>();
        CustList<MethodHeaderInfo> binarySecond_ = new CustList<MethodHeaderInfo>();
        CustList<MethodHeaderInfo> binaryAll_ = new CustList<MethodHeaderInfo>();
        CustList<MethodHeaderInfo> explicit_ = new CustList<MethodHeaderInfo>();
        CustList<MethodHeaderInfo> explicitId_ = new CustList<MethodHeaderInfo>();
        CustList<MethodHeaderInfo> explicitFrom_ = new CustList<MethodHeaderInfo>();
        CustList<MethodHeaderInfo> implicit_ = new CustList<MethodHeaderInfo>();
        CustList<MethodHeaderInfo> implicitId_ = new CustList<MethodHeaderInfo>();
        CustList<MethodHeaderInfo> implicitFrom_ = new CustList<MethodHeaderInfo>();
        CustList<MethodHeaderInfo> true_ = new CustList<MethodHeaderInfo>();
        CustList<MethodHeaderInfo> false_ = new CustList<MethodHeaderInfo>();
        for (AbsBk b: bl_) {
            if (!(b instanceof NamedFunctionBlock)) {
                continue;
            }
            NamedFunctionBlock method_ = (NamedFunctionBlock) b;
            String name_ = method_.getName();
            if (isOverBlock(method_)) {
                NamedCalledFunctionBlock m_ = (NamedCalledFunctionBlock) method_;
                m_.buildImportedTypes(_page);
                if (m_.getKind() == MethodKind.OPERATOR) {
                    if (!StringExpUtil.isOper(m_.getName())) {
                        int r_ = m_.getNameOffset();
                        FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
                        badMeth_.setFileName(getFile().getFileName());
                        badMeth_.setIndexFile(r_);
                        //method name len
                        badMeth_.buildError(_page.getAnalysisMessages().getBadOperatorName(),
                                name_);
                        _page.addLocError(badMeth_);
                        m_.addNameErrors(badMeth_);
                    }
                    if (m_.getId().getKind() == MethodAccessKind.STATIC_CALL) {
                        if (m_.getParametersTypes().size() == 2) {
                            if (StringExpUtil.isBin(m_.getName())) {
                                if (StringUtil.quickEq(StringExpUtil.getQuickComponentBase(m_.getImportedParametersTypes().first()),getGenericString())) {
                                    if (StringUtil.quickEq(StringExpUtil.getQuickComponentBase(m_.getImportedParametersTypes().last()),getGenericString())) {
                                        binaryAll_.add(new MethodHeaderInfo(this, m_, m_.getId(), getNumberAll(), m_.getNameOverrideNumber(),m_.getImportedReturnType(), m_.getAccess()));
                                    } else {
                                        binaryFirst_.add(new MethodHeaderInfo(this, m_, m_.getId(), getNumberAll(), m_.getNameOverrideNumber(),m_.getImportedReturnType(), m_.getAccess()));
                                    }
                                } else if (StringUtil.quickEq(StringExpUtil.getQuickComponentBase(m_.getImportedParametersTypes().last()),getGenericString())) {
                                    binarySecond_.add(new MethodHeaderInfo(this, m_, m_.getId(), getNumberAll(), m_.getNameOverrideNumber(),m_.getImportedReturnType(), m_.getAccess()));
                                }
                            }
                        }
                        if (m_.getParametersTypes().size() == 1) {
                            if (StringExpUtil.isUn(m_.getName())) {
                                if (StringUtil.quickEq(StringExpUtil.getQuickComponentBase(m_.getImportedParametersTypes().first()),getGenericString())) {
                                    unary_.add(new MethodHeaderInfo(this, m_, m_.getId(), getNumberAll(), m_.getNameOverrideNumber(),m_.getImportedReturnType(), m_.getAccess()));
                                }
                            }
                        }
                    }
                    nbOperators++;
                } else if (m_.getKind() == MethodKind.TRUE_OPERATOR || m_.getKind() == MethodKind.FALSE_OPERATOR) {
                    if (m_.getParametersTypes().size() != 1) {
                        int r_ = m_.getNameOffset();
                        FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
                        badMeth_.setFileName(getFile().getFileName());
                        badMeth_.setIndexFile(r_);
                        //method name len
                        badMeth_.buildError(_page.getAnalysisMessages().getBadParams(),
                                m_.getSignature(_page));
                        _page.addLocError(badMeth_);
                        m_.addNameErrors(badMeth_);
                    } else if (!StringUtil.quickEq(m_.getImportedReturnType(),_page.getAliasPrimBoolean())) {
                        int r_ = m_.getNameOffset();
                        FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
                        badMeth_.setFileName(getFile().getFileName());
                        badMeth_.setIndexFile(r_);
                        //method name len
                        badMeth_.buildError(_page.getAnalysisMessages().getBadReturnType(),
                                m_.getSignature(_page),
                                getGenericString());
                        _page.addLocError(badMeth_);
                        m_.addNameErrors(badMeth_);
                    } else if (!StringUtil.quickEq(m_.getImportedParametersTypes().first(),getGenericString())) {
                        int r_ = m_.getNameOffset();
                        FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
                        badMeth_.setFileName(getFile().getFileName());
                        badMeth_.setIndexFile(r_);
                        //method name len
                        badMeth_.buildError(_page.getAnalysisMessages().getBadReturnType(),
                                m_.getSignature(_page),
                                getGenericString());
                        _page.addLocError(badMeth_);
                        m_.addNameErrors(badMeth_);
                    } else if (!m_.isStaticMethod()) {
                        int r_ = m_.getNameOffset();
                        FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
                        badMeth_.setFileName(getFile().getFileName());
                        badMeth_.setIndexFile(r_);
                        //method name len
                        badMeth_.buildError(_page.getAnalysisMessages().getBadMethodModifier(),
                                m_.getSignature(_page));
                        _page.addLocError(badMeth_);
                        m_.addNameErrors(badMeth_);
                    } else if (m_.isVarargs() || m_.getId().isRef()) {
                        int r_ = m_.getNameOffset();
                        FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
                        badMeth_.setFileName(getFile().getFileName());
                        badMeth_.setIndexFile(r_);
                        //method name len
                        badMeth_.buildError(_page.getAnalysisMessages().getBadMethodVararg(),
                                m_.getSignature(_page));
                        _page.addLocError(badMeth_);
                        m_.addNameErrors(badMeth_);
                    } else {
                        if (m_.getKind() == MethodKind.TRUE_OPERATOR) {
                            true_.add(new MethodHeaderInfo(this, m_, m_.getId(), getNumberAll(), m_.getNameOverrideNumber(),m_.getImportedReturnType(), m_.getAccess()));
                        } else {
                            false_.add(new MethodHeaderInfo(this, m_, m_.getId(), getNumberAll(), m_.getNameOverrideNumber(),m_.getImportedReturnType(), m_.getAccess()));
                        }
                    }
                } else if (m_.getKind() == MethodKind.EXPLICIT_CAST || m_.getKind() == MethodKind.IMPLICIT_CAST) {
                    if (m_.getParametersTypes().size() != 1) {
                        int r_ = m_.getNameOffset();
                        FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
                        badMeth_.setFileName(getFile().getFileName());
                        badMeth_.setIndexFile(r_);
                        //method name len
                        badMeth_.buildError(_page.getAnalysisMessages().getBadParams(),
                                m_.getSignature(_page));
                        _page.addLocError(badMeth_);
                        m_.addNameErrors(badMeth_);
                    } else if (!StringUtil.quickEq(m_.getImportedReturnType(),getGenericString())&&!StringUtil.quickEq(m_.getImportedParametersTypes().first(),getGenericString())) {
                        int r_ = m_.getNameOffset();
                        FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
                        badMeth_.setFileName(getFile().getFileName());
                        badMeth_.setIndexFile(r_);
                        //method name len
                        badMeth_.buildError(_page.getAnalysisMessages().getBadReturnType(),
                                m_.getSignature(_page),
                                getGenericString());
                        _page.addLocError(badMeth_);
                        m_.addNameErrors(badMeth_);
                    } else if (!m_.isStaticMethod()) {
                        int r_ = m_.getNameOffset();
                        FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
                        badMeth_.setFileName(getFile().getFileName());
                        badMeth_.setIndexFile(r_);
                        //method name len
                        badMeth_.buildError(_page.getAnalysisMessages().getBadMethodModifier(),
                                m_.getSignature(_page));
                        _page.addLocError(badMeth_);
                        m_.addNameErrors(badMeth_);
                    } else if (m_.isVarargs() || m_.getId().isRef()) {
                        int r_ = m_.getNameOffset();
                        FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
                        badMeth_.setFileName(getFile().getFileName());
                        badMeth_.setIndexFile(r_);
                        //method name len
                        badMeth_.buildError(_page.getAnalysisMessages().getBadMethodVararg(),
                                m_.getSignature(_page));
                        _page.addLocError(badMeth_);
                        m_.addNameErrors(badMeth_);
                    } else {
                        if (m_.getKind() == MethodKind.EXPLICIT_CAST) {
                            if (StringUtil.quickEq(m_.getImportedParametersTypes().first(),m_.getImportedReturnType())) {
                                explicitId_.add(new MethodHeaderInfo(this, m_, m_.getId(), getNumberAll(), m_.getNameOverrideNumber(),m_.getImportedReturnType(), m_.getAccess()));
                            } else if (StringUtil.quickEq(m_.getImportedReturnType(),getGenericString())){
                                explicit_.add(new MethodHeaderInfo(this, m_, m_.getId(), getNumberAll(), m_.getNameOverrideNumber(),m_.getImportedReturnType(), m_.getAccess()));
                            } else {
                                explicitFrom_.add(new MethodHeaderInfo(this, m_, m_.getId(), getNumberAll(), m_.getNameOverrideNumber(),m_.getImportedReturnType(), m_.getAccess()));
                            }
                        } else {
                            if (StringUtil.quickEq(m_.getImportedParametersTypes().first(),m_.getImportedReturnType())) {
                                implicitId_.add(new MethodHeaderInfo(this, m_, m_.getId(), getNumberAll(), m_.getNameOverrideNumber(),m_.getImportedReturnType(), m_.getAccess()));
                            } else if (StringUtil.quickEq(m_.getImportedReturnType(),getGenericString())){
                                implicit_.add(new MethodHeaderInfo(this, m_, m_.getId(), getNumberAll(), m_.getNameOverrideNumber(),m_.getImportedReturnType(), m_.getAccess()));
                            } else {
                                implicitFrom_.add(new MethodHeaderInfo(this, m_, m_.getId(), getNumberAll(), m_.getNameOverrideNumber(),m_.getImportedReturnType(), m_.getAccess()));
                            }
                        }
                    }
                } else if (m_.getKind() == MethodKind.RAND_CODE) {
                    if (m_.getParametersTypes().size() != 0) {
                        int r_ = m_.getNameOffset();
                        FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
                        badMeth_.setFileName(getFile().getFileName());
                        badMeth_.setIndexFile(r_);
                        //method name len
                        badMeth_.buildError(_page.getAnalysisMessages().getBadParams(),
                                m_.getSignature(_page));
                        _page.addLocError(badMeth_);
                        m_.addNameErrors(badMeth_);
                    } else if (!StringUtil.quickEq(m_.getImportedReturnType(),_page.getAliasPrimLong()) || m_.getId().isRef()) {
                        int r_ = m_.getNameOffset();
                        FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
                        badMeth_.setFileName(getFile().getFileName());
                        badMeth_.setIndexFile(r_);
                        //method name len
                        badMeth_.buildError(_page.getAnalysisMessages().getBadReturnType(),
                                name_,
                                _page.getAliasString());
                        _page.addLocError(badMeth_);
                        m_.addNameErrors(badMeth_);
                    } else if (m_.hiddenInstance()) {
                        int r_ = m_.getNameOffset();
                        FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
                        badMeth_.setFileName(getFile().getFileName());
                        badMeth_.setIndexFile(r_);
                        //method name len
                        badMeth_.buildError(_page.getAnalysisMessages().getBadMethodModifier(),
                                m_.getSignature(_page));
                        _page.addLocError(badMeth_);
                        m_.addNameErrors(badMeth_);
                    } else if (m_.getAccess() != AccessEnum.PUBLIC) {
                        int r_ = m_.getNameOffset();
                        FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
                        badMeth_.setFileName(getFile().getFileName());
                        badMeth_.setIndexFile(r_);
                        //method name len
                        badMeth_.buildError(_page.getAnalysisMessages().getBadAccess(),
                                name_);
                        _page.addLocError(badMeth_);
                        m_.addNameErrors(badMeth_);
                    } else {
                        ToStringMethodHeader t_ = new ToStringMethodHeader(getNumberAll(),m_.getNameOverrideNumber(),m_.getName(), m_.getImportedReturnType(), m_.isFinalMethod(),m_.isAbstractMethod());
                        _page.getRandCodeMethods().addEntry(getFullName(), t_);
                    }
                } else if (m_.getKind() == MethodKind.TO_STRING) {
                    if (!StringUtil.quickEq(m_.getImportedReturnType(),_page.getAliasString())) {
                        int r_ = m_.getNameOffset();
                        FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
                        badMeth_.setFileName(getFile().getFileName());
                        badMeth_.setIndexFile(r_);
                        //method name len
                        badMeth_.buildError(_page.getAnalysisMessages().getBadReturnType(),
                                name_,
                                _page.getAliasString());
                        _page.addLocError(badMeth_);
                        m_.addNameErrors(badMeth_);
                    } else if (m_.getAccess() != AccessEnum.PUBLIC) {
                        int r_ = m_.getNameOffset();
                        FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
                        badMeth_.setFileName(getFile().getFileName());
                        badMeth_.setIndexFile(r_);
                        //method name len
                        badMeth_.buildError(_page.getAnalysisMessages().getBadAccess(),
                                name_);
                        _page.addLocError(badMeth_);
                        m_.addNameErrors(badMeth_);
                    } else {
                        ToStringMethodHeader t_ = new ToStringMethodHeader(getNumberAll(),m_.getNameOverrideNumber(),m_.getName(), m_.getImportedReturnType(), m_.isFinalMethod(),m_.isAbstractMethod());
                        _page.getToStringMethods().addEntry(getFullName(), t_);
                    }
                } else if (m_.getKind() == MethodKind.STD_METHOD) {
                    if (!StringUtil.quickEq(name_, keyWords_.getKeyWordToString())) {
                        TokenErrorMessage mess_ = ManageTokens.partMethod(_page).checkToken(name_, _page);
                        if (mess_.isError()) {
                            int r_ = m_.getNameOffset();
                            FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
                            badMeth_.setFileName(getFile().getFileName());
                            badMeth_.setIndexFile(r_);
                            //method name len
                            badMeth_.setBuiltError(mess_.getMessage());
                            _page.addLocError(badMeth_);
                            m_.addNameErrors(badMeth_);
                        }
                    }
                } else {
                    if (m_.isStaticMethod()) {
                        int where_ = b.getOffset();
                        FoundErrorInterpret unexp_ = new FoundErrorInterpret();
                        unexp_.setFileName(getFile().getFileName());
                        unexp_.setIndexFile(where_);
                        //key word this len
                        unexp_.buildError(_page.getAnalysisMessages().getBadIndexerModifier(),
                                m_.getSignature(_page));
                        _page.addLocError(unexp_);
                        m_.addNameErrors(unexp_);
                    }
                    if (m_.getParametersTypes().isEmpty()) {
                        int where_ = b.getOffset();
                        FoundErrorInterpret unexp_ = new FoundErrorInterpret();
                        unexp_.setFileName(getFile().getFileName());
                        unexp_.setIndexFile(where_);
                        //key word this len
                        unexp_.buildError(_page.getAnalysisMessages().getBadIndexerParams(),
                                m_.getSignature(_page));
                        _page.addLocError(unexp_);
                        m_.addNameErrors(unexp_);
                    }
                    if (m_.getKind() == MethodKind.GET_INDEX) {
                        indexersGet_.add(m_);
                    } else {
                        indexersSet_.add(m_);
                    }
                }
            }
            if (AbsBk.isAnnotBlock(method_)) {
                NamedCalledFunctionBlock m_ = (NamedCalledFunctionBlock) method_;
                m_.buildImportedTypes(_page);
                if (m_.isKo()) {
                    int r_ = m_.getNameOffset();
                    FoundErrorInterpret b_ = new FoundErrorInterpret();
                    b_.setFileName(getFile().getFileName());
                    b_.setIndexFile(r_);
                    //underline index char
                    b_.buildError(_page.getAnalysisMessages().getBadIndexInParser());
                    _page.addLocError(b_);
                    m_.addNameErrors(b_);
                }
                TokenErrorMessage mess_ = ManageTokens.partMethod(_page).checkToken(name_, _page);
                if (mess_.isError()) {
                    int r_ = m_.getNameOffset();
                    FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
                    badMeth_.setFileName(getFile().getFileName());
                    badMeth_.setIndexFile(r_);
                    //method name len
                    badMeth_.setBuiltError(mess_.getMessage());
                    _page.addLocError(badMeth_);
                    m_.addNameErrors(badMeth_);
                }
            }
            if (isOverBlock(method_)) {
                NamedCalledFunctionBlock m_ = (NamedCalledFunctionBlock) method_;
                if (m_.getKind() == MethodKind.TO_STRING || m_.getKind() == MethodKind.STD_METHOD || m_.getKind() == MethodKind.OPERATOR || m_.getKind() == MethodKind.EXPLICIT_CAST || m_.getKind() == MethodKind.IMPLICIT_CAST
                        || m_.getKind() == MethodKind.TRUE_OPERATOR || m_.getKind() == MethodKind.FALSE_OPERATOR) {
                    MethodId id_ = m_.getId();
                    if (ContextUtil.isEnumType(this)) {
                        String valueOf_ = _page.getAliasEnumPredValueOf();
                        String values_ = _page.getAliasEnumValues();
                        String string_ = _page.getAliasString();
                        if (id_.eq(new MethodId(MethodAccessKind.STATIC, valueOf_, new StringList(string_)))) {
                            int r_ = m_.getOffset();
                            FoundErrorInterpret duplicate_;
                            duplicate_ = new FoundErrorInterpret();
                            duplicate_.setIndexFile(r_);
                            duplicate_.setFileName(getFile().getFileName());
                            //method name len
                            duplicate_.buildError(_page.getAnalysisMessages().getReservedCustomMethod(),
                                    id_.getSignature(_page.getDisplayedStrings()));
                            _page.addLocError(duplicate_);
                            m_.addNameErrors(duplicate_);
                        }
                        if (id_.eq(new MethodId(MethodAccessKind.STATIC, values_, new StringList()))) {
                            int r_ = m_.getOffset();
                            FoundErrorInterpret duplicate_;
                            duplicate_ = new FoundErrorInterpret();
                            duplicate_.setIndexFile(r_);
                            duplicate_.setFileName(getFile().getFileName());
                            //method name len
                            duplicate_.buildError(_page.getAnalysisMessages().getReservedCustomMethod(),
                                    id_.getSignature(_page.getDisplayedStrings()));
                            _page.addLocError(duplicate_);
                            m_.addNameErrors(duplicate_);
                        }
                    }
                    for (MethodId m: idMethods_) {
                        if (m.eq(id_)) {
                            int r_ = m_.getOffset();
                            FoundErrorInterpret duplicate_;
                            duplicate_ = new FoundErrorInterpret();
                            duplicate_.setIndexFile(r_);
                            duplicate_.setFileName(getFile().getFileName());
                            //method name len
                            duplicate_.buildError(_page.getAnalysisMessages().getDuplicateCustomMethod(),
                                    id_.getSignature(_page.getDisplayedStrings()));
                            _page.addLocError(duplicate_);
                            m_.addNameErrors(duplicate_);
                        }
                    }
                    idMethods_.add(id_);
                } else {
                    MethodId id_ = m_.getId();
                    for (MethodId m: idMethods_) {
                        if (m.eq(id_)) {
                            int r_ = m_.getOffset();
                            FoundErrorInterpret duplicate_;
                            duplicate_ = new FoundErrorInterpret();
                            duplicate_.setIndexFile(r_);
                            duplicate_.setFileName(getFile().getFileName());
                            //method name len
                            duplicate_.buildError(_page.getAnalysisMessages().getDuplicateIndexer(),
                                    id_.getSignature(_page.getDisplayedStrings()));
                            _page.addLocError(duplicate_);
                            m_.addNameErrors(duplicate_);
                        }
                    }
                    idMethods_.add(id_);
                }
            }
            if (AbsBk.isAnnotBlock(method_)) {
                MethodId id_ = ((NamedCalledFunctionBlock)method_).getId();
                for (MethodId m: idMethods_) {
                    if (m.eq(id_)) {
                        int r_ = method_.getOffset();
                        FoundErrorInterpret duplicate_;
                        duplicate_ = new FoundErrorInterpret();
                        duplicate_.setIndexFile(r_);
                        duplicate_.setFileName(getFile().getFileName());
                        String sgn_ = id_.getSignature(_page.getDisplayedStrings());
                        //method name len
                        duplicate_.buildError(_page.getAnalysisMessages().getDuplicateCustomMethod(),
                                sgn_);
                        _page.addLocError(duplicate_);
                        method_.addNameErrors(duplicate_);
                    }
                }
                idMethods_.add(id_);
            }
            if (method_ instanceof ConstructorBlock) {
                method_.buildImportedTypes(_page);
                String ctorName_ = ((ConstructorBlock) method_).getCtorName();
                if (StringExpUtil.isTypeLeafPart(ctorName_)) {
                    if (!StringUtil.quickEq(ctorName_,getName())) {
                        int r_ = method_.getOffset();
                        FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
                        badMeth_.setFileName(getFile().getFileName());
                        badMeth_.setIndexFile(r_);
                        //method name len
                        AnalysisMessages ana_ = _page.getAnalysisMessages();
                        badMeth_.setBuiltError(FoundErrorInterpret.buildARError(ana_.getBadMethodName(),ctorName_));
                        _page.addLocError(badMeth_);
                        method_.addNameErrors(badMeth_);
                    }
                }
                ConstructorId idCt_ = ((ConstructorBlock)method_).getId();
                for (ConstructorId m: idConstructors_) {
                    if (m.eq(idCt_)) {
                        int r_ = method_.getOffset();
                        FoundErrorInterpret duplicate_;
                        duplicate_ = new FoundErrorInterpret();
                        duplicate_.setIndexFile(r_);
                        duplicate_.setFileName(getFile().getFileName());
                        //left par len
                        duplicate_.buildError(_page.getAnalysisMessages().getDuplicatedCtor(),
                                idCt_.getSignature(_page.getDisplayedStrings()));
                        _page.addLocError(duplicate_);
                        method_.addNameErrors(duplicate_);
                    }
                }
                idConstructors_.add(idCt_);
            }
            validateParameters(method_, _page, getFile());
        }
        buildFieldInfos(bl_, _page);
        _page.getUnary().addEntry(getFullName(),unary_);
        _page.getBinaryAll().addEntry(getFullName(),binaryAll_);
        _page.getBinaryFirst().addEntry(getFullName(),binaryFirst_);
        _page.getBinarySecond().addEntry(getFullName(),binarySecond_);
        _page.getExplicitCastMethods().addEntry(getFullName(),explicit_);
        _page.getExplicitIdCastMethods().addEntry(getFullName(),explicitId_);
        _page.getExplicitFromCastMethods().addEntry(getFullName(),explicitFrom_);
        _page.getImplicitCastMethods().addEntry(getFullName(),implicit_);
        _page.getImplicitIdCastMethods().addEntry(getFullName(),implicitId_);
        _page.getImplicitFromCastMethods().addEntry(getFullName(),implicitFrom_);
        _page.getTrues().addEntry(getFullName(),true_);
        _page.getFalses().addEntry(getFullName(),false_);
        validateIndexers(indexersGet_, indexersSet_, _page);
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
                b_.setFileName(_file.getFileName());
                b_.setIndexFile(_method.getOffset());
                //param name len
                b_.setBuiltError(res_.getMessage());
                _page.addLocError(b_);
                _method.addParamErrors(j_,b_);
            }
            if (isOverBlock(_method)) {
                NamedCalledFunctionBlock i_ = (NamedCalledFunctionBlock) _method;
                if (i_.getKind() == MethodKind.SET_INDEX) {
                    if (StringUtil.quickEq(v, keyWordValue_)) {
                        FoundErrorInterpret b_;
                        b_ = new FoundErrorInterpret();
                        b_.setFileName(_file.getFileName());
                        b_.setIndexFile(_method.getOffset());
                        //param name len
                        b_.buildError(_page.getAnalysisMessages().getReservedParamName(),
                                v);
                        _page.addLocError(b_);
                        _method.addParamErrors(j_,b_);
                    }
                }
            }
            if (StringUtil.contains(seen_, v)){
                FoundErrorInterpret b_;
                b_ = new FoundErrorInterpret();
                b_.setFileName(_file.getFileName());
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
        for (NamedCalledFunctionBlock i: _indexersGet) {
            MethodId iOne_ = i.getId();
            boolean ok_ = false;
            NamedCalledFunctionBlock set_ = null;
            for (NamedCalledFunctionBlock j: _indexersSet) {
                MethodId iTwo_ = j.getId();
                if (iOne_.eqPartial(iTwo_)) {
                    ok_ = true;
                    set_ = j;
                    i.setMatchParamNames(StringUtil.eqStrings(i.getParametersNames(),j.getParametersNames()));
                    j.setMatchParamNames(StringUtil.eqStrings(i.getParametersNames(),j.getParametersNames()));
                }
            }
            if (!ok_) {
                int where_ = i.getOffset();
                FoundErrorInterpret unexp_ = new FoundErrorInterpret();
                unexp_.setFileName(getFile().getFileName());
                unexp_.setIndexFile(where_);
                //method name len
                unexp_.buildError(_page.getAnalysisMessages().getBadIndexerPairSet(),
                        i.getSignature(_page));
                _page.addLocError(unexp_);
                i.addNameErrors(unexp_);
            } else {
                if (set_.getModifier() != i.getModifier()) {
                    int where_ = i.getOffset();
                    FoundErrorInterpret unexp_ = new FoundErrorInterpret();
                    unexp_.setFileName(getFile().getFileName());
                    unexp_.setIndexFile(where_);
                    //method name len
                    unexp_.buildError(_page.getAnalysisMessages().getBadIndexerModifiers(),
                            i.getSignature(_page));
                    _page.addLocError(unexp_);
                    i.addNameErrors(unexp_);
                }
                if (set_.getAccess() != i.getAccess()) {
                    int where_ = i.getOffset();
                    FoundErrorInterpret unexp_ = new FoundErrorInterpret();
                    unexp_.setFileName(getFile().getFileName());
                    unexp_.setIndexFile(where_);
                    //method name len
                    unexp_.buildError(_page.getAnalysisMessages().getBadIndexerAccesses(),
                            i.getSignature(_page));
                    _page.addLocError(unexp_);
                    i.addNameErrors(unexp_);
                }
            }
        }
        for (NamedCalledFunctionBlock i: _indexersSet) {
            MethodId iOne_ = i.getId();
            boolean ok_ = false;
            for (NamedCalledFunctionBlock j: _indexersGet) {
                MethodId iTwo_ = j.getId();
                if (iOne_.eqPartial(iTwo_)) {
                    i.setReturnTypeGet(j.getImportedReturnType());
                    ok_ = true;
                }
            }
            if (!ok_) {
                int where_ = i.getOffset();
                FoundErrorInterpret unexp_ = new FoundErrorInterpret();
                unexp_.setFileName(getFile().getFileName());
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
        _page.getMappingLocal().putAllMap(mappings);
        int len_ = rcs_.size();
        for (int i = 0; i < len_; i++) {
            int index_ = rcs_.getKey(i);
            String value_ = rcs_.getValue(i);
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
                        un_.setFileName(getFile().getFileName());
                        un_.setIndexFile(o_ + loc_);
                        //_in len
                        un_.buildError(_page.getAnalysisMessages().getEmptyType());
                        _page.addLocError(un_);
                        addNameErrors(un_);
                        ok_ =  false;
                    }
                    o_ += p.length() + 1;
                }
                String res_;
                if (ok_) {
                    res_ = AnaInherits.getRealClassName(allTypes_.first(), j_);
                } else {
                    res_ = _page.getAliasObject();
                }
                result_ = res_;
            } else if (index_ < 0){
                result_ = value_;
            } else {
                int off_ = StringUtil.getFirstPrintableCharIndex(value_);
                AnaResultPartType resType_ = ResolvingSuperTypes.processAnalyzeHeader(value_.trim(), this, off_ + index_, _page, true);
                ResolvingSuperTypes.loopWildCards(this, off_ + index_, _page, resType_);
                result_ = resType_.getResult();
                results.add(resType_);
            }
            String base_ = StringExpUtil.getIdFromAllTypes(result_);
            RootBlock r_ = _page.getAnaClassBody(base_);
            if (this instanceof AnnotationBlock||r_ instanceof InterfaceBlock) {
                importedDirectSuperInterfaces.add(result_);
            } else {
                importedDirectSuperClass = result_;
            }
            if (r_ != null) {
                importedDirectSuperTypes.add(new AnaFormattedRootBlock(r_, result_));
            }
        }
        if (importedDirectSuperClass.isEmpty()) {
            importedDirectSuperClass = _page.getAliasObject();

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
        StringMap<StringList> vars_ = new StringMap<StringList>();
        for (TypeVar t: getParamTypesMapValues()) {
            vars_.put(t.getName(), t.getConstraints());
        }
        String objectClassName_ = _page.getAliasObject();
        for (TypeVar t: getParamTypesMapValues()) {
            CustList<MethodIdAncestors> signatures_;
            signatures_ = new CustList<MethodIdAncestors>();
            StringList upper_ = Mapping.getAllUpperBounds(vars_, t.getName(),objectClassName_);
            CustList<CustList<MethodInfo>> methods_;
            methods_ = new CustList<CustList<MethodInfo>>();
            OperationNode.fetchParamClassAncMethods(upper_,methods_, _page);
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
            StringList retClasses_ = new StringList();
            StringList types_ = new StringList();
            for (MethodInfo m: e.getMethodInfos()) {
                retClasses_.add(m.getReturnType());
                types_.add(m.getClassName());
            }
            retClasses_.removeDuplicates();
            types_.removeDuplicates();
            FoundErrorInterpret err_ = new FoundErrorInterpret();
            err_.setFileName(getFile().getFileName());
            err_.setIndexFile(rootBlockContent.getIdRowCol());
            //original id len
            err_.buildError(_page.getAnalysisMessages().getReturnTypes(),
                    e.getClassMethodId().getClassMethodId().getSignature(_page.getDisplayedStrings()),
                    StringUtil.join(types_,ExportCst.JOIN_TYPES),
                    StringUtil.join(retClasses_,ExportCst.JOIN_TYPES));
            _page.addLocError(err_);
            addNameErrors(err_);
        }
        er_ = RootBlock.areCompatibleFinalReturn(_fullName, _vars, sub_, _page);
        for (MethodIdAncestors e: er_) {
            CustList<MethodInfo> fClasses_ = new CustList<MethodInfo>();
            for (MethodInfo s: e.getMethodInfos()) {
                if (s.isFinalMethod()) {
                    fClasses_.add(s);
                }
            }
            MethodInfo subInt_ = fClasses_.first();
            String subType_ = subInt_.getReturnType();
            for (MethodInfo s: e.getMethodInfos()) {
                String formattedSup_ = s.getReturnType();
                if (!AnaInherits.isReturnCorrect(formattedSup_, subType_,_vars, _page)) {
                    FoundErrorInterpret err_ = new FoundErrorInterpret();
                    err_.setFileName(getFile().getFileName());
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
            StringList retClasses_ = new StringList();
            StringList types_ = new StringList();
            for (MethodInfo m: e.getMethodInfos()) {
                retClasses_.add(m.getReturnType());
                types_.add(m.getClassName());
            }
            retClasses_.removeDuplicates();
            types_.removeDuplicates();
            FoundErrorInterpret err_ = new FoundErrorInterpret();
            err_.setFileName(getFile().getFileName());
            err_.setIndexFile(rootBlockContent.getIdRowCol());
            //original id len
            err_.buildError(_page.getAnalysisMessages().getTwoReturnTypes(),
                    e.getClassMethodId().getClassMethodId().getSignature(_page.getDisplayedStrings()),
                    StringUtil.join(types_,ExportCst.JOIN_TYPES),
                    StringUtil.join(retClasses_,ExportCst.JOIN_TYPES));
            _page.addLocError(err_);
            addNameErrors(err_);
        }
        er_ = RootBlock.areModifierCompatible(sub_);
        for (MethodIdAncestors e: er_) {
            FoundErrorInterpret err_ = new FoundErrorInterpret();
            err_.setFileName(getFile().getFileName());
            err_.setIndexFile(rootBlockContent.getIdRowCol());
            //original id len
            err_.buildError(_page.getAnalysisMessages().getTwoFinal(),
                    _virtualType,
                    e.getClassMethodId().getClassMethodId().getSignature(_page.getDisplayedStrings()));
            _page.addLocError(err_);
            addNameErrors(err_);
        }
    }

    public final void checkCompatibility(AnalyzedPageEl _page) {
        StringMap<StringList> vars_ = new StringMap<StringList>();
        for (TypeVar t: getParamTypesMapValues()) {
            vars_.put(t.getName(), t.getConstraints());
        }
        CustList<MethodIdAncestors> ov_;
        ov_ = new CustList<MethodIdAncestors>();
        CustList<CustList<MethodInfo>> methods_;
        methods_ = new CustList<CustList<MethodInfo>>();
        OperationNode.fetchParamClassAncMethods(new StringList(getGenericString()),methods_, _page);
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
        StringMap<StringList> vars_ = new StringMap<StringList>();
        for (TypeVar t: getParamTypesMapValues()) {
            vars_.put(t.getName(), t.getConstraints());
        }
        for (NamedCalledFunctionBlock b: overridableBlocks) {
            if (b.isAbstractMethod()) {
                if (b.getFirstChild() != null) {
                    FoundErrorInterpret err_;
                    err_ = new FoundErrorInterpret();
                    err_.setFileName(getFile().getFileName());
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
        }
        if (concreteClass_) {
            for (NamedCalledFunctionBlock b: overridableBlocks) {
                if (b.isAbstractMethod()) {
                    FoundErrorInterpret err_;
                    err_ = new FoundErrorInterpret();
                    err_.setFileName(getFile().getFileName());
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
            MethodIdAncestor cst_ = e.getClassMethodId();
            CustList<MethodInfo> classes_ = e.getMethodInfos();
            boolean skip_ = skip(_fullName, classes_);
            if (skip_) {
                continue;
            }
            CustList<MethodInfo> fClasses_ = new CustList<MethodInfo>();
            for (MethodInfo s: e.getMethodInfos()) {
                if (s.isFinalMethod()) {
                    fClasses_.add(s);
                }
            }
            if (!StringExpUtil.isDollarWord(cst_.getClassMethodId().getName())) {
                continue;
            }
            if (fClasses_.size() == 1) {
                MethodInfo subInt_ = fClasses_.first();
                String subType_ = subInt_.getReturnType();
                boolean err_ = false;
                for (MethodInfo s: e.getMethodInfos()) {
                    String formattedSup_ = s.getReturnType();
                    if (!AnaInherits.isReturnCorrect(formattedSup_, subType_,_vars, _page)) {
                        err_ = true;
                        addClass(output_, cst_, s);
                    }
                }
                if (err_) {
                    addClass(output_, cst_, subInt_);
                }
            }
        }
        return output_;
    }

    private static CustList<MethodIdAncestors> areCompatibleMerged(
            String _fullName,
            StringMap<StringList> _vars,
            CustList<MethodIdAncestors> _methodIds, AnalyzedPageEl _page) {
        CustList<MethodIdAncestors> output_;
        output_ = new CustList<MethodIdAncestors>();
        for (MethodIdAncestors e: _methodIds) {
            MethodIdAncestor cst_ = e.getClassMethodId();
            CustList<MethodInfo> classes_ = e.getMethodInfos();
            boolean skip_ = skip(_fullName, classes_);
            if (skip_) {
                continue;
            }
            CustList<MethodInfo> fClasses_ = new CustList<MethodInfo>();
            StringList retClasses_ = new StringList();
            for (MethodInfo s: e.getMethodInfos()) {
                if (s.isFinalMethod()) {
                    fClasses_.add(s);
                }
                retClasses_.add(s.getReturnType());
            }
            if (!StringExpUtil.isDollarWord(cst_.getClassMethodId().getName())) {
                continue;
            }
            if (fClasses_.size() == 1) {
                continue;
            }
            StringList subs_ = new StringList();
            int len_ = retClasses_.size();
            for (int i = 0; i < len_; i++) {
                String cur_ = retClasses_.get(i);
                boolean sub_ = true;
                for (int j = 0; j < len_; j++) {
                    String other_ = retClasses_.get(j);
                    if (StringUtil.quickEq(cur_, other_)) {
                        continue;
                    }
                    if (!AnaInherits.isReturnCorrect(other_, cur_, _vars, _page)) {
                        sub_ = false;
                        break;
                    }
                }
                if (sub_) {
                    subs_.add(cur_);
                }
            }
            if (!subs_.onlyOneElt()) {
                for (MethodInfo c: classes_) {
                    addClass(output_, e.getClassMethodId(), c);
                }
            }
        }
        return output_;
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
            CustList<MethodInfo> fClasses_ = new CustList<MethodInfo>();
            for (MethodInfo s: e.getMethodInfos()) {
                if (s.isFinalMethod()) {
                    fClasses_.add(s);
                }
            }
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
            un_.setFileName(getFile().getFileName());
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
                un_.setFileName(getFile().getFileName());
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
                ConstructorBlock found_ = null;
                cycle_.add(co_);
                for (EntryCust<ConstructorId, ConstructorBlock> c: ctorsId_.entryList()) {
                    if (co_.eq(c.getKey())) {
                        found_ = c.getValue();
                        break;
                    }
                }
                if (found_ == null) {
                    break;
                }
                if (found_ == f && cycle_.size() > 1) {
                    FoundErrorInterpret cyclic_ = new FoundErrorInterpret();
                    StringList c_ = new StringList();
                    for (ConstructorId c: cycle_) {
                        c_.add(c.getSignature(_page.getDisplayedStrings()));
                    }
                    cyclic_.setFileName(getFile().getFileName());
                    cyclic_.setIndexFile(getOffset());
                    //original contructor id len
                    cyclic_.buildError(_page.getAnalysisMessages().getCyclicCtorCall(),
                            StringUtil.join(c_,ExportCst.JOIN_TYPES),
                            getFullName());
                    _page.addLocError(cyclic_);
                    found_.addNameErrors(cyclic_);
                    break;
                }
                co_ = convert(found_.getConstIdSameClass());
            }
        }
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

    public CustList<OperationNode> getRoots() {
        return roots;
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
        String pkg_ = getPackageName();
        StringBuilder generic_ = new StringBuilder();
        RootBlock.addPkgIfNotEmpty(pkg_, generic_);
        CustList<RootBlock> pars_ = getSelfAndParentTypes();
        RootBlock previous_ = null;
        for (RootBlock r: pars_.first().getAllParentTypesReverse()) {
            appendParts(generic_, previous_, r);
            generic_.append(r.getSuffixedName());
            previous_ = r;
        }
        for (RootBlock r: pars_) {
            appendParts(generic_, previous_, r);
            generic_.append(r.getSuffixedName());
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
        String pkg_ = getPackageName();
        StringBuilder generic_ = new StringBuilder();
        RootBlock.addPkgIfNotEmpty(pkg_, generic_);
        CustList<RootBlock> pars_ = getSelfAndParentTypes();
        RootBlock previous_ = null;
        for (RootBlock r: pars_.first().getAllParentTypesReverse()) {
            appendParts(generic_, previous_, r);
            generic_.append(r.getSuffixedName());
            previous_ = r;
        }
        for (RootBlock r: pars_) {
            appendParts(generic_, previous_, r);
            generic_.append(r.getSuffixedName());
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
    public OperatorBlock getOperator() {
        return operator;
    }

    public void setOperator(OperatorBlock _operator) {
        operator = _operator;
    }

    public CustList<ResultExpression> getResList() {
        return resList;
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

    public CustList<NamedCalledFunctionBlock> getAnonymousRootFct() {
        return anonymousRootFct;
    }

    public CustList<AnonymousTypeBlock> getAnonymousRoot() {
        return anonymousRoot;
    }

    public CustList<SwitchMethodBlock> getSwitchMethodRoots() {
        return switchMethodRoots;
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
}
