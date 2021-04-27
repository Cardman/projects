package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetAccessInfo;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.ParsedFctHeader;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.analyze.instr.PartOffsetsClassMethodId;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.analyze.opers.IdFctOperation;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.opers.util.AnaTypeFct;
import code.expressionlanguage.analyze.reach.opers.ReachOperationUtil;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.types.GeneStringOverridable;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.analyze.util.AnaCache;
import code.expressionlanguage.analyze.util.TypeVar;
import code.expressionlanguage.common.ExtractedParts;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.fwd.blocks.AnaAnonFctContent;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.linkage.ExportCst;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.DisplayedStrings;
import code.util.*;
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
    private RootBlock parentType;
    private OperatorBlock operator;
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

    public NamedCalledFunctionBlock(ParsedFctHeader _header, boolean _retRef, OffsetAccessInfo _access, OffsetStringInfo _retType, OffsetStringInfo _defaultValue, OffsetStringInfo _fctName, int _offset, int _rightPar) {
        super(_header,_retRef, _access, _retType, _fctName, _offset);
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
    }
    public NamedCalledFunctionBlock(ParsedFctHeader _header, boolean _retRef, OffsetAccessInfo _access,
                                    OffsetStringInfo _retType, OffsetStringInfo _fctName,
                                    OffsetStringInfo _modifier, int _offset, AnalyzedPageEl _page) {
        super(_header,_retRef, _access, _retType, _fctName, _offset);
        modifierOffset = _modifier.getOffset();
        String modifier_ = _modifier.getInfo();
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordStatic_ = keyWords_.getKeyWordStatic();
        String keyWordStaticCall_ = keyWords_.getKeyWordStaticCall();
        String keyWordFinal_ = keyWords_.getKeyWordFinal();
        String keyWordAbstract_ = keyWords_.getKeyWordAbstract();
        String keyWordNormal_ = keyWords_.getKeyWordNormal();
        staticMethod = StringUtil.quickEq(modifier_, keyWordStatic_);
        staticCallMethod = StringUtil.quickEq(modifier_, keyWordStaticCall_);
        finalMethod = StringUtil.quickEq(modifier_, keyWordFinal_);
        abstractMethod = StringUtil.quickEq(modifier_, keyWordAbstract_);
        normalMethod = StringUtil.quickEq(modifier_, keyWordNormal_);
        typeCall = NameCalledEnum.OVERRIDABLE;
        defaultValue = "";
        defaultValueOffset = 0;
        rightPar = 0;
    }
    public NamedCalledFunctionBlock(int _fctName, int _offset, AnalyzedPageEl _page) {
        super(_fctName, _offset, _page);
        normalMethod = false;
        abstractMethod = false;
        finalMethod = false;
        modifierOffset = 0;
        MethodAccessKind stCtx_ = _page.getStaticContext();
        staticMethod = stCtx_ == MethodAccessKind.STATIC;
        staticCallMethod = stCtx_ == MethodAccessKind.STATIC_CALL;
        typeCall = NameCalledEnum.ANONYMOUS;
        defaultValue = "";
        defaultValueOffset = 0;
        rightPar = 0;
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
        BooleanList rTypes_ = new BooleanList();
        StringList pTypes_ = new StringList();
        if (kind == MethodKind.EXPLICIT_CAST || kind == MethodKind.IMPLICIT_CAST
                ||kind == MethodKind.TRUE_OPERATOR || kind == MethodKind.FALSE_OPERATOR) {
            pTypes_.add(getImportedReturnType());
            rTypes_.add(false);
        }
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            String n_ = types_.get(i);
            pTypes_.add(n_);
            rTypes_.add(getParametersRef().get(i));
        }
        return new MethodId(isRetRef(), MethodId.getKind(getModifier()), name_, pTypes_,rTypes_, isVarargs());
    }
    @Override
    public String getSignature(AnalyzedPageEl _page) {
        return getId().getSignature(_page);
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

    public void buildTypes(RootBlock _root, AnalyzedPageEl _page) {
        int indexDefOv_ = definition.indexOf('(');
        _page.setGlobalOffset(definitionOffset+indexDefOv_+1);
        ExtractedParts extractedParts_ = StringExpUtil.tryToExtract(definition, '(', ')');
        StringList overrideList_ = StringUtil.splitChar(extractedParts_.getSecond(), ';');
        int sum_ = 0;
        for (String o: overrideList_) {
            _page.setOffset(sum_);
            int indexDef_ = o.indexOf(Templates.EXTENDS_DEF);
            StringList parts_ = StringUtil.splitInTwo(o, indexDef_);
            if (parts_.size() <= 1) {
                sum_ += o.length()+1;
                continue;
            }
            String key_ = parts_.first();
            int off_ = StringUtil.getFirstPrintableCharIndex(key_);
            String clKey_ = ResolvingTypes.resolveAccessibleIdType(off_,key_, _page);
            CustList<PartOffset> allPartTypes_ = new CustList<PartOffset>();
            CustList<PartOffset> allPartSuperTypes_ = new CustList<PartOffset>();
            allPartTypes_.addAllElts(_page.getCurrentParts());
            RootBlock root_ = _page.getAnaClassBody(clKey_);
            if (root_ == null) {
                sum_ += o.length()+1;
                allInternTypesParts.add(new PartOffsetsClassMethodId(allPartTypes_,allPartSuperTypes_,null, null, 0, 0));
                continue;
            }
            if (!root_.isSubTypeOf(_root)) {
                sum_ += o.length()+1;
                allInternTypesParts.add(new PartOffsetsClassMethodId(allPartTypes_,allPartSuperTypes_,null, null, 0, 0));
                continue;
            }
            String sgn_ = parts_.last().substring(1);
            ExtractedParts extr_ = StringExpUtil.tryToExtract(sgn_,'(',')');
            String nameLoc_ = extr_.getFirst().trim();
            if (StringExpUtil.isIndexerOrInexist(nameLoc_)) {
                sum_ += o.length() + 1;
                allInternTypesParts.add(new PartOffsetsClassMethodId(allPartTypes_,allPartSuperTypes_,null, null, 0, 0));
                continue;
            }
            _page.setOffset(sum_+indexDef_+1);
            StringList args_ = StringExpUtil.getAllSepCommaTypes(extr_.getSecond());
            String firstFull_ = args_.first();
            off_ = StringUtil.getFirstPrintableCharIndex(firstFull_);
            String fromType_ = firstFull_.trim();
            int firstPar_ = extr_.getFirst().length();
            String clDest_ = ResolvingTypes.resolveAccessibleIdType(off_+firstPar_+1,fromType_, _page);
            CustList<PartOffset> superPartOffsets_ = new CustList<PartOffset>();
            superPartOffsets_.addAllElts(_page.getCurrentParts());
            String formattedDest_ = AnaInherits.getOverridingFullTypeByBases(root_, clDest_, _page);
            RootBlock formattedDestType_ = _page.getAnaClassBody(StringExpUtil.getIdFromAllTypes(formattedDest_));
            if (formattedDestType_ == null) {
                allPartSuperTypes_.addAllElts(superPartOffsets_);
                sum_ += o.length()+1;
                allInternTypesParts.add(new PartOffsetsClassMethodId(allPartTypes_,allPartSuperTypes_,null, null, 0, 0));
                continue;
            }
            boolean retRef_ = false;
            String nameLocId_ = nameLoc_;
            if (nameLoc_.startsWith("~")) {
                retRef_ = true;
                nameLocId_ = nameLoc_.substring(1);
            }
            MethodId methodIdDest_ = IdFctOperation.resolveArguments(1, retRef_, clDest_,nameLocId_,MethodAccessKind.INSTANCE,args_,sgn_, superPartOffsets_, _page);
            if (methodIdDest_ == null) {
                allPartSuperTypes_.addAllElts(superPartOffsets_);
                sum_ += o.length()+1;
                allInternTypesParts.add(new PartOffsetsClassMethodId(allPartTypes_,allPartSuperTypes_,null,null,  0, 0));
                continue;
            }
            CustList<NamedCalledFunctionBlock> methods_ = formattedDestType_.getOverridableBlocks();
            String formattedDeclaring_ = AnaInherits.getOverridingFullTypeByBases(root_, _root.getFullName(), _page);
            if (!getId().quickOverrideFormat(_root,formattedDeclaring_).eqPartial(MethodId.to(methodIdDest_.quickFormat(formattedDestType_,formattedDest_)))) {
                allPartSuperTypes_.addAllElts(superPartOffsets_);
                sum_ += o.length()+1;
                allInternTypesParts.add(new PartOffsetsClassMethodId(allPartTypes_,allPartSuperTypes_,null,null,  0, 0));
                continue;
            }
            String return_ = AnaInherits.quickFormat(_root,formattedDeclaring_,getImportedReturnType());
            StringMap<StringList> vars_ = new StringMap<StringList>();
            for (TypeVar t: root_.getParamTypesMapValues()) {
                vars_.put(t.getName(), t.getConstraints());
            }
            ClassMethodId id_ = null;
            AnaTypeFct fct_ = null;
            int rc_ = _page.getTraceIndex() +off_;
            for (NamedCalledFunctionBlock m: methods_) {
                if (m.isAbstractMethod()) {
                    continue;
                }
                if (m.getId().eq(methodIdDest_)) {
                    String returnDest_ = AnaInherits.quickFormat(formattedDestType_,formattedDest_,m.getImportedReturnType());
                    if (methodIdDest_.isRetRef()) {
                        if (!StringUtil.quickEq(return_,returnDest_)) {
                            continue;
                        }
                    } else {
                        if (!AnaInherits.isReturnCorrect(return_,returnDest_,vars_,_page)) {
                            continue;
                        }
                    }
                    fct_ = new AnaTypeFct();
                    fct_.setType(formattedDestType_);
                    fct_.setFunction(m);
                    id_ = new ClassMethodId(clDest_,m.getId());
                    overrides.put(clKey_,new GeneStringOverridable(formattedDest_,formattedDestType_,m));
                    break;
                }
            }
            allPartSuperTypes_.addAllElts(superPartOffsets_);
            allInternTypesParts.add(new PartOffsetsClassMethodId(allPartTypes_,allPartSuperTypes_,id_,fct_, rc_, nameLoc_.length()));
            sum_ += o.length()+1;
        }
    }

    @Override
    public void buildImportedReturnTypes(AnalyzedPageEl _page) {
        super.buildImportedReturnTypes(_page);
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
        cast_.setFileName(_page.getLocalizer().getCurrentFileName());
        cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
        //return type len
        cast_.buildError(_page.getAnalysisMessages().getUnexpectedRetType(),
                itype_,getSignature(_page));
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
        _page.setGlobalOffset(defaultValueOffset);
        _page.zeroOffset();
        res.setRoot(ElUtil.getRootAnalyzedOperationsReadOnly(res, defaultValue, Calculation.staticCalculation(MethodAccessKind.STATIC), _page));
        String import_ = getImportedReturnType();
        StringMap<StringList> vars_ = new StringMap<StringList>();
        Mapping mapping_ = new Mapping();
        mapping_.setMapping(vars_);
        AnaClassArgumentMatching arg_ = res.getRoot().getResultClass();
        mapping_.setArg(arg_);
        mapping_.setParam(import_);
        if (!AnaInherits.isCorrectOrNumbers(mapping_, _page)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(getFile().getFileName());
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

    public void setReturnTypeGet(String _returnTypeGet) {
        this.returnTypeGet = _returnTypeGet;
    }

    public OperatorBlock getOperator() {
        return operator;
    }

    public void setOperator(OperatorBlock _operator) {
        operator = _operator;
    }

    public RootBlock getParentType() {
        return parentType;
    }

    public void setParentType(RootBlock _parentType) {
        this.parentType = _parentType;
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
}
