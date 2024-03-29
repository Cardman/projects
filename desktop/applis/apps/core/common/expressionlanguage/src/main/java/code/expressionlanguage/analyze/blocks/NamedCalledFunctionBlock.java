package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.*;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.instr.PartOffsetsClassMethodId;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.analyze.opers.IdFctOperation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.opers.util.AnaTypeFct;
import code.expressionlanguage.analyze.opers.util.ResolvedId;
import code.expressionlanguage.analyze.opers.util.ResolvedIdBuilder;
import code.expressionlanguage.analyze.reach.opers.ReachOperationUtil;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.types.*;
import code.expressionlanguage.analyze.util.AnaCache;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.analyze.util.FormattedMethodId;
import code.expressionlanguage.analyze.util.TypeVar;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.DisplayedStrings;
import code.expressionlanguage.common.ExtractedParts;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.fwd.blocks.AnaAnonFctContent;
import code.expressionlanguage.linkage.ExportCst;
import code.expressionlanguage.options.KeyWords;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class NamedCalledFunctionBlock extends NamedFunctionBlock {
    private final int modifierOffset;

    private final boolean staticMethod;
    private final boolean staticCallMethod;

    private final boolean finalMethod;
    private final boolean abstractMethod;

    private final boolean normalMethod;
    private final NameCalledEnum typeCall;
    private MethodKind kind;
    private final CustList<PartOffsetsClassMethodId> allInternTypesParts = new CustList<PartOffsetsClassMethodId>();
    private String definition  = "";
    private int definitionOffset;
    private final StringMap<GeneStringOverridable> overrides = new StringMap<GeneStringOverridable>();
    private int nameOverrideNumber;
    private String returnTypeGet = "";
    private AccessedBlock accessedBlock;
    private int indexEnd;
    private final StringList allReservedInners = new StringList();
    private int numberLambda;
    private final AnaAnonFctContent anaAnonFctContent = new AnaAnonFctContent();
    private final String defaultValue;
    private final ResultExpression res = new ResultExpression();
    private final int defaultValueOffset;
    private final int rightPar;
    private boolean ko;
    private int nameNumber;
    private int typeSetterOff;
    private String typeSetter="";
    private AnaResultPartType partOffsetsReturnSetter = new AnaResultPartType();
//    private CustList<OperationNode> rootsListSupp = new CustList<OperationNode>();
//
//    private final CustList<ResultExpression> resListSupp = new CustList<ResultExpression>();
    private ResultParsedAnnots annotationsSupp = new ResultParsedAnnots();
    private final boolean dynamic;

    public NamedCalledFunctionBlock(ParsedFctHeader _header, OffsetAccessInfo _access, OffsetStringInfo _retType, OffsetStringInfo _defaultValue, OffsetStringInfo _fctName, int _offset, int _rightPar) {
        super(_header, _access, _retType, _fctName, _offset);
        normalMethod = false;
        abstractMethod = false;
        staticMethod = false;
        finalMethod = false;
        staticCallMethod = false;
        modifierOffset = 0;
        typeCall = NameCalledEnum.ANNOTATION;
        defaultValue = _defaultValue.getInfo();
        defaultValueOffset = _defaultValue.getOffset();
        rightPar = _rightPar;
        dynamic = false;
    }
    public NamedCalledFunctionBlock(ParsedFctHeader _header, OffsetAccessInfo _access,
                                    OffsetStringInfo _retType, OffsetStringInfo _fctName,
                                    OffsetStringInfo _modifier, int _offset, KeyWords _keyWords) {
        super(_header, _access, _retType, _fctName, _offset);
        typeSetterOff = _header.getTypeSetterOff();
        typeSetter = _header.getTypeSetter();
        annotationsSupp = _header.getAnnotationsSupp();
        modifierOffset = _modifier.getOffset();
        String modifier_ = _modifier.getInfo();
        String keyWordStatic_ = _keyWords.getKeyWordStatic();
        String keyWordStaticCall_ = _keyWords.getKeyWordStaticCall();
        String keyWordFinal_ = _keyWords.getKeyWordFinal();
        String keyWordAbstract_ = _keyWords.getKeyWordAbstract();
        String keyWordNormal_ = _keyWords.getKeyWordNormal();
        staticMethod = StringUtil.quickEq(modifier_, keyWordStatic_);
        staticCallMethod = StringUtil.quickEq(modifier_, keyWordStaticCall_);
        finalMethod = StringUtil.quickEq(modifier_, keyWordFinal_);
        abstractMethod = StringUtil.quickEq(modifier_, keyWordAbstract_);
        normalMethod = StringUtil.quickEq(modifier_, keyWordNormal_);
        typeCall = NameCalledEnum.OVERRIDABLE;
        defaultValue = "";
        defaultValueOffset = 0;
        rightPar = 0;
        dynamic = false;
    }
    public NamedCalledFunctionBlock(int _fctName, int _offset, FileResolverContext _res) {
        this(_fctName,_offset,_res.getStat(),_res.getKeys(), _res.isDynamic());
    }
    public NamedCalledFunctionBlock(int _fctName, int _offset, MethodAccessKind _stat, KeyWords _keyWords, boolean _dynamic) {
        super(_fctName, _offset, _keyWords);
        normalMethod = false;
        abstractMethod = false;
        finalMethod = false;
        modifierOffset = 0;
        staticMethod = _stat == MethodAccessKind.STATIC;
        staticCallMethod = _stat == MethodAccessKind.STATIC_CALL;
        typeCall = NameCalledEnum.ANONYMOUS;
        defaultValue = "";
        defaultValueOffset = 0;
        rightPar = 0;
        dynamic = _dynamic;
    }

    public boolean isDynamic() {
        return dynamic;
    }

    public void setDefinition(String _definition) {
        this.definition = _definition;
    }

    public void setDefinitionOffset(int _definitionOffset) {
        this.definitionOffset = _definitionOffset;
    }

    public int getModifierOffset() {
        return modifierOffset;
    }

    public MethodModifier getModifier() {
        if (abstractMethod) {
            return MethodModifier.ABSTRACT;
        }
        if (finalMethod) {
            return MethodModifier.FINAL;
        }
        if (staticCallMethod) {
            return MethodModifier.STATIC_CALL;
        }
        if (staticMethod) {
            return MethodModifier.STATIC;
        }
        return MethodModifier.NORMAL;
    }

    public boolean hiddenInstance() {
        return staticCallMethod || staticMethod;
    }

    public MethodId getId() {
        String name_ = getName();
        StringList types_ = getImportedParametersTypes();
        int len_ = types_.size();
        CustList<BoolVal> rTypes_ = new CustList<BoolVal>();
        StringList pTypes_ = new StringList();
        if (kind == MethodKind.EXPLICIT_CAST || kind == MethodKind.IMPLICIT_CAST
                ||kind == MethodKind.TRUE_OPERATOR || kind == MethodKind.FALSE_OPERATOR) {
            pTypes_.add(getImportedReturnType());
            rTypes_.add(BoolVal.FALSE);
        }
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            pTypes_.add(n_);
            rTypes_.add(getParametersRef().get(i));
        }
        return new MethodId(isRetRef(), MethodId.getKind(getModifier()), name_, pTypes_,rTypes_, isVarargs());
    }

    @Override
    public String getSignature(DisplayedStrings _page) {
        return getId().getSignature(_page);
    }

    public boolean isStaticMethod() {
        return staticMethod;
    }

    public boolean isFinalMethod() {
        return finalMethod;
    }

    public boolean isAbstractMethod() {
        return abstractMethod;
    }

    public boolean isNormalMethod() {
        return normalMethod;
    }

    @Override
    public MethodAccessKind getStaticContext() {
        if (typeCall == NameCalledEnum.ANNOTATION) {
            return MethodAccessKind.STATIC;
        }
        if (staticMethod) {
            return MethodAccessKind.STATIC;
        }
        if (staticCallMethod) {
            return MethodAccessKind.STATIC_CALL;
        }
        return MethodAccessKind.INSTANCE;
    }
    public void buildAnnotationsSupp(AnalyzedPageEl _page) {
        annotationsSupp.buildAnnotations(_page);
    }

    public void buildTypes(RootBlock _root, AnalyzedPageEl _page) {
        int indexDefOv_ = definition.indexOf('(');
        _page.setSumOffset(definitionOffset+indexDefOv_+1);
        ExtractedParts extractedParts_ = StringExpUtil.tryToExtract(definition, '(', ')');
        StringList overrideList_ = StringUtil.splitChar(extractedParts_.getSecond(), ';');
        int sum_ = 0;
        for (String o: overrideList_) {
            sum_ = loopOverrides(_root, _page, sum_, o);
        }
    }

    private int loopOverrides(RootBlock _root, AnalyzedPageEl _page, int _sum, String _ov) {
        int sum_ = _sum;
        _page.setOffset(sum_);
        int indexDef_ = _ov.indexOf(StringExpUtil.EXTENDS_DEF);
        StringList parts_ = StringUtil.splitInTwo(_ov, indexDef_);
        if (parts_.size() <= 1) {
            sum_ += _ov.length()+1;
            return sum_;
        }
        String key_ = parts_.first();
        int off_ = StringUtil.getFirstPrintableCharIndex(key_);
        ResolvedIdType resolvedIdType_ = ResolvingTypes.resolveAccessibleIdTypeBlock(off_, key_.trim(), _page);
        AnaGeneType bl_ = resolvedIdType_.getGeneType();
        String clKey_ = resolvedIdType_.getFullName();
        CustList<AnaResultPartTypeDtoInt> allPartTypes_ = new CustList<AnaResultPartTypeDtoInt>();
        CustList<AnaResultPartTypeDtoInt> allPartSuperTypes_ = new CustList<AnaResultPartTypeDtoInt>();
        allPartTypes_.add(resolvedIdType_.getDels());
        if (!(bl_ instanceof RootBlock)) {
            sum_ += _ov.length()+1;
            allInternTypesParts.add(new PartOffsetsClassMethodId(allPartTypes_,allPartSuperTypes_, null, 0, 0));
            return sum_;
        }
        RootBlock root_ = (RootBlock)bl_;
        AnaFormattedRootBlock formInfo_ = AnaInherits.getOverridingFullTypeByBases(root_, _root);
        if (formInfo_ == null) {
            sum_ += _ov.length()+1;
            allInternTypesParts.add(new PartOffsetsClassMethodId(allPartTypes_,allPartSuperTypes_, null, 0, 0));
            return sum_;
        }
        String sgn_ = parts_.last().substring(1);
        ExtractedParts extr_ = StringExpUtil.tryToExtract(sgn_,'(',')');
        String nameLoc_ = extr_.getFirst();
        if (StringExpUtil.isIndexerOrInexist(nameLoc_)) {
            sum_ += _ov.length() + 1;
            allInternTypesParts.add(new PartOffsetsClassMethodId(allPartTypes_,allPartSuperTypes_, null, 0, 0));
            return sum_;
        }
        _page.setOffset(sum_ +indexDef_+1);
        StringList args_ = StringExpUtil.getAllSepCommaTypes(extr_.getSecond());
        String firstFull_ = args_.first();
        off_ = StringUtil.getFirstPrintableCharIndex(firstFull_);
        String fromType_ = firstFull_.trim();
        int firstPar_ = extr_.getFirst().length();
        ResolvedIdType resolvedIdTypeDest_ = ResolvingTypes.resolveAccessibleIdTypeBlock(off_ + firstPar_ + 1, fromType_, _page);
        String clDest_ = resolvedIdTypeDest_.getFullName();
        CustList<AnaResultPartTypeDtoInt> superPartOffsets_ = new CustList<AnaResultPartTypeDtoInt>();
        superPartOffsets_.add(resolvedIdTypeDest_.getDels());
        AnaFormattedRootBlock formInfoDest_ = AnaInherits.getOverridingFullTypeByBases(root_, resolvedIdTypeDest_.getGeneType());
        if (formInfoDest_ == null) {
            allPartSuperTypes_.addAllElts(superPartOffsets_);
            sum_ += _ov.length()+1;
            allInternTypesParts.add(new PartOffsetsClassMethodId(allPartTypes_,allPartSuperTypes_, null, 0, 0));
            return sum_;
        }
        boolean retRef_ = false;
        int delta_ = StringExpUtil.getOffset(nameLoc_);
        String nameLocId_ = nameLoc_.trim();
        if (nameLocId_.startsWith("~")) {
            retRef_ = true;
            delta_++;
            nameLocId_ = nameLoc_.trim().substring(1);
            delta_+= StringExpUtil.getOffset(nameLocId_);
            nameLocId_ = nameLocId_.trim();
        }
        ResolvedId resolved_;
        if (IdFctOperation.off(false, 1, args_, sgn_) > -1) {
            resolved_ = IdFctOperation.errCase(1, clDest_, args_, sgn_, _page);
        } else {
            resolved_ = new ResolvedIdBuilder(1, clDest_, args_, sgn_, _page).build(1, retRef_, nameLocId_, MethodAccessKind.INSTANCE, args_, _page);
        }
        superPartOffsets_.addAllElts(resolved_.getResults());
        MethodId methodIdDest_ = resolved_.getId();
        if (methodIdDest_ == null) {
            allPartSuperTypes_.addAllElts(superPartOffsets_);
            sum_ += _ov.length()+1;
            allInternTypesParts.add(new PartOffsetsClassMethodId(allPartTypes_,allPartSuperTypes_, null,  0, 0,resolved_.getInfo()));
            return sum_;
        }
        if (FormattedMethodId.notEqPartial(new FormattedMethodId(getId().quickFormat(AnaInherits.getVarTypes(formInfo_))), new FormattedMethodId(methodIdDest_.quickFormat(AnaInherits.getVarTypes(formInfoDest_))))) {
            allPartSuperTypes_.addAllElts(superPartOffsets_);
            sum_ += _ov.length()+1;
            allInternTypesParts.add(new PartOffsetsClassMethodId(allPartTypes_,allPartSuperTypes_, null,  0, 0));
            return sum_;
        }
        String retValue_ = AnaInherits.quickFormat(formInfo_,getImportedReturnType());
        StringMap<StringList> vars_ = new StringMap<StringList>();
        for (TypeVar t: root_.getParamTypesMapValues()) {
            vars_.put(t.getName(), t.getConstraints());
        }
        AnaTypeFct fct_ = null;
        RootBlock formattedDestType_ = formInfoDest_.getRootBlock();
        CustList<NamedCalledFunctionBlock> methods_ = formattedDestType_.getOverridableBlocks();
        for (NamedCalledFunctionBlock m: methods_) {
            if (!m.isAbstractMethod() && m.getId().eq(methodIdDest_) && include(_page, formInfoDest_, retValue_, vars_, m)) {
                fct_ = new AnaTypeFct();
                fct_.setType(formattedDestType_);
                fct_.setFunction(m);
                overrides.put(clKey_, new GeneStringOverridable(formInfoDest_, m));
                break;
            }
        }
        allPartSuperTypes_.addAllElts(superPartOffsets_);
        allInternTypesParts.add(new PartOffsetsClassMethodId(allPartTypes_,allPartSuperTypes_, fct_, _page,off_+delta_, nameLocId_.length()));
        sum_ += _ov.length()+1;
        return sum_;
    }

    private boolean include(AnalyzedPageEl _page, AnaFormattedRootBlock _formInfoDest, String _retValue, StringMap<StringList> _vars, NamedCalledFunctionBlock _m) {
        String returnDest_ = AnaInherits.quickFormat(_formInfoDest, _m.getImportedReturnType());
        if (_m.mustHaveSameRet()) {
            return StringUtil.quickEq(_retValue, returnDest_);
        }
        return AnaInherits.isReturnCorrect(_retValue, returnDest_, _vars, _page);
    }

    @Override
    public void buildImportedReturnTypes(AnalyzedPageEl _page) {
        if (kind == MethodKind.SET_INDEX && !getTypeSetter().isEmpty()) {
            buildInternRetSett(_page);
        }
        super.buildImportedReturnTypes(_page);
        retRef(_page, getKind());
        if (typeCall != NameCalledEnum.ANNOTATION) {
            return;
        }
        String string_ = _page.getAliasString();
        String class_ = _page.getAliasClassType();
        String itype_ = getImportedReturnType();
        String type_ = itype_;
        String ctype_ = StringExpUtil.getQuickComponentType(type_);
        if (ctype_ != null) {
            type_ = ctype_;
        }
        if (AnaTypeUtil.isPrimitiveOrWrapper(type_, _page)) {
            return;
        }
        RootBlock r_ = _page.getAnaClassBody(type_);
        if (r_ instanceof AnnotationBlock) {
            return;
        }
        if (r_ instanceof EnumBlock) {
            return;
        }
        if (StringUtil.quickEq(type_, string_)) {
            return;
        }
        if (StringUtil.quickEq(type_, class_)) {
            return;
        }
        FoundErrorInterpret cast_ = new FoundErrorInterpret();
        cast_.setFile(_page.getCurrentFile());
        cast_.setIndexFile(_page);
        //return type len
        cast_.buildError(_page.getAnalysisMessages().getUnexpectedRetType(),
                itype_,getSignature(_page.getDisplayedStrings()));
        _page.addLocError(cast_);
        addNameErrors(cast_);
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public int getDefaultValueOffset() {
        return defaultValueOffset;
    }

    public void buildExpressionLanguage(AnalyzedPageEl _page) {
        if (defaultValue.trim().isEmpty()) {
            return;
        }
        _page.setSumOffset(res.getSumOffset());
        _page.zeroOffset();
        res.setRoot(ElUtil.getRootAnalyzedOperationsReadOnly(res, Calculation.staticCalculation(MethodAccessKind.STATIC), _page));
        String import_ = getImportedReturnType();
        StringMap<StringList> vars_ = new StringMap<StringList>();
        Mapping mapping_ = new Mapping();
        mapping_.setMapping(vars_);
        AnaClassArgumentMatching arg_ = res.getRoot().getResultClass();
        mapping_.setArg(arg_);
        mapping_.setParam(import_);
        if (!AnaInherits.isCorrectOrNumbers(mapping_, _page)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFile(getFile());
            cast_.setIndexFile(defaultValueOffset);
            //parentheses
            cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                    StringUtil.join(arg_.getNames(), ExportCst.JOIN_TYPES),
                    import_);
            _page.addLocError(cast_);
            addNameErrors(cast_);
        }
        if (AnaTypeUtil.isPrimitive(getImportedReturnType(), _page)) {
            res.getRoot().getResultClass().setUnwrapObject(getImportedReturnType(), _page.getPrimitiveTypes());
        }
        ReachOperationUtil.tryCalculate(res.getRoot(), _page);
    }
    public void buildInternRetSett(AnalyzedPageEl _page) {
        _page.setSumOffset(getTypeSetterOff());
        _page.zeroOffset();
        partOffsetsReturnSetter = ResolvingTypes.resolveCorrectType(getTypeSetter(), _page);
        returnTypeGet = partOffsetsReturnSetter.getResult(_page);
    }
    public String getTypeSetter() {
        return typeSetter;
    }

    public int getTypeSetterOff() {
        return typeSetterOff;
    }

    public int getNameNumber() {
        return nameNumber;
    }

    public void setNameNumber(int _nameNumber) {
        this.nameNumber = _nameNumber;
    }

    public ResultExpression getRes() {
        return res;
    }

    public OperationNode getRoot() {
        return res.getRoot();
    }

    public void setKo() {
        ko = true;
    }

    public boolean isKo() {
        return ko;
    }

    public int getRightPar() {
        return rightPar;
    }

    public StringMap<GeneStringOverridable> getOverrides() {
        return overrides;
    }

    public CustList<PartOffsetsClassMethodId> getAllInternTypesParts() {
        return allInternTypesParts;
    }

    public boolean mustHaveSameRet() {
        return getKind() != MethodKind.STD_METHOD || isRetRef();
    }
    public MethodKind getKind() {
        return kind;
    }

    public void setKind(MethodKind _kind) {
        kind = _kind;
    }

    public int getNameOverrideNumber() {
        return nameOverrideNumber;
    }

    public void setNameOverrideNumber(int _nameOverrideNumber) {
        this.nameOverrideNumber = _nameOverrideNumber;
    }

    public String getReturnTypeGet() {
        return returnTypeGet;
    }

    public void returnTypeGet(String _returnType) {
        if (!returnTypeGet.isEmpty()) {
            return;
        }
        this.returnTypeGet = _returnType;
    }

    public AccessedBlock getAccessedBlock() {
        return accessedBlock;
    }

    public void setAccessedBlock(AccessedBlock _accessedBlock) {
        this.accessedBlock = _accessedBlock;
    }

    public StringList getAllReservedInners() {
        return allReservedInners;
    }

    public int getIndexEnd() {
        return indexEnd;
    }

    public void setIndexEnd(int _indexEnd) {
        this.indexEnd = _indexEnd;
    }

    public int getNumberLambda() {
        return numberLambda;
    }

    public void setNumberLambda(int _numberLambda) {
        this.numberLambda = _numberLambda;
    }

    public AnaCache getCache() {
        return anaAnonFctContent.getCache();
    }

    public AnaAnonFctContent getAnaAnonFctContent() {
        return anaAnonFctContent;
    }

    public NameCalledEnum getTypeCall() {
        return typeCall;
    }

    public AnaResultPartType getPartOffsetsReturnSetter() {
        return partOffsetsReturnSetter;
    }

    public ResultParsedAnnots getAnnotationsSupp() {
        return annotationsSupp;
    }

}
