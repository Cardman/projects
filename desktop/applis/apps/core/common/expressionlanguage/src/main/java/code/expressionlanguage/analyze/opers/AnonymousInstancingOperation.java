package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.files.ParsedAnnotations;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.opers.util.ConstrustorIdVarArg;
import code.expressionlanguage.analyze.opers.util.MemberId;
import code.expressionlanguage.analyze.opers.util.NameParametersFilter;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.analyze.util.TypeVar;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.fwd.blocks.AnaRootBlockContent;
import code.expressionlanguage.fwd.opers.AnaInstancingAnonContent;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.options.KeyWords;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class AnonymousInstancingOperation extends
        AbstractInstancingOperation implements PreAnalyzableOperation {

    private final AnaInstancingAnonContent instancingAnonContent;
    private String glClass="";
    private RootBlock glType;
    private String base="";
    private String type="";
    private int index;
    private final StringMap<String> anonTypeVars = new StringMap<String>();

    public AnonymousInstancingOperation(int _index, int _indexChild,
                                        MethodOperation _m, OperationsSequence _op, AnonymousTypeBlock _block) {
        super(_index, _indexChild, _m, _op);
        instancingAnonContent = new AnaInstancingAnonContent(_block);
    }


    @Override
    public void preAnalyze(AnalyzedPageEl _page) {
        _page.getAnonymous().add(this);
        _page.getAnonymousList().add(this);
        KeyWords keyWords_ = _page.getKeyWords();
        String newKeyWord_ = keyWords_.getKeyWordNew();
        String afterNew_ = getMethodName().trim().substring(newKeyWord_.length());
        int j_ = -1;
        if (afterNew_.trim().startsWith("{")) {
            setNewBefore(false);
            j_ =  afterNew_.indexOf('}',afterNew_.indexOf('{'));
        }
        tryAnalyze(_page);
        index = _page.getLocalizer().getCurrentLocationIndex();
        int off_ = StringUtil.getFirstPrintableCharIndex(getMethodName());
        setClassName(_page.getAliasObject());
        String realClassName_ = getMethodName().trim().substring(newKeyWord_.length());
        if (j_ > -1) {
            realClassName_ = realClassName_.substring(j_+1);
            off_ += j_+1;
        }
        int local_ = StringUtil.getFirstPrintableCharIndex(realClassName_);
        if (realClassName_.trim().startsWith("@")) {
            ParsedAnnotations parse_ = new ParsedAnnotations(realClassName_.trim(),local_);
            parse_.parse();
            local_ = parse_.getIndex();
            realClassName_ = parse_.getAfter();
            local_ += StringExpUtil.getOffset(realClassName_);
        }
        realClassName_ = realClassName_.trim();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _page);
        if (!StringExpUtil.isDollarWord(instancingAnonContent.getBlock().getName())) {
            FoundErrorInterpret static_ = new FoundErrorInterpret();
            static_.setFileName(_page.getLocalizer().getCurrentFileName());
            static_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //original type len
            static_.buildError(_page.getAnalysisMessages().getIllegalCtorUnknown(),
                    realClassName_);
            _page.getLocalizer().addError(static_);
            addErr(static_.getBuiltError());
        }
        if (!isIntermediateDottedOperation()) {
            setStaticAccess(_page.getStaticContext());
            if (!getTypeInfer().isEmpty()) {
                realClassName_ = getTypeInfer();
            } else  {
                realClassName_ = ResolvingTypes.resolveCorrectType(newKeyWord_.length()+local_,realClassName_, _page);
                getPartOffsets().addAllElts(_page.getCurrentParts());
            }
            type = realClassName_;
            checkInstancingType(realClassName_, isStaticAccess(), _page);
            preAnalyzeCtor(realClassName_, _page);
            return;
        }
        FoundErrorInterpret static_ = new FoundErrorInterpret();
        static_.setFileName(_page.getLocalizer().getCurrentFileName());
        static_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
        //key word len
        static_.buildError(_page.getAnalysisMessages().getIllegalCtorUnknown(),
                realClassName_);
        _page.getLocalizer().addError(static_);
        addErr(static_.getBuiltError());
        setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
    }

    @Override
    protected boolean koType(AnaGeneType _type, String _realClassName, AnalyzedPageEl _page) {
        if (!(_type instanceof RootBlock)) {
            return true;
        }
        if (ContextUtil.isFinalType(_type)) {
            return true;
        }
        return !_type.withoutInstance();
    }

    private void preAnalyzeCtor(String _realClassName, AnalyzedPageEl _page) {
        String base_ = StringExpUtil.getIdFromAllTypes(_realClassName);
        AnaGeneType g_ = _page.getAnaGeneType(base_);
        if (!(g_ instanceof RootBlock)) {
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFileName(_page.getLocalizer().getCurrentFileName());
            call_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //type len
            call_.buildError(_page.getAnalysisMessages().getIllegalCtorUnknown(),
                    _realClassName);
            _page.getLocalizer().addError(call_);
            addErr(call_.getBuiltError());
            return;
        }
        for (String p:StringExpUtil.getWildCards(_realClassName)){
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFileName(_page.getLocalizer().getCurrentFileName());
            call_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //part type len
            call_.buildError(_page.getAnalysisMessages().getIllegalCtorBound(),
                    p,
                    _realClassName);
            _page.getLocalizer().addError(call_);
            addErr(call_.getBuiltError());
        }
        CustList<String> args_ = StringExpUtil.getAllTypes(_realClassName).mid(1);
        CustList<TypeVar> cts_ = ((RootBlock) g_).getParamTypesMapValues();
        StringMap<String> map_ = new StringMap<String>();
        CustList<TypeVar> selected_ = new CustList<TypeVar>();
        CustList<String> superNames_ = new CustList<String>();
        int len_ = Math.min(args_.size(),cts_.size());
        for (int i = 0; i < len_; i++) {
            String a_ = args_.get(i);
            TypeVar ct_ = cts_.get(i);
            String name_ = ct_.getName();
            if (a_.contains("#")) {
                superNames_.add(StringUtil.concat("#",name_));
                selected_.add(ct_);
                anonTypeVars.addEntry(name_,a_);
            } else {
                superNames_.add(a_);
                map_.addEntry(name_,a_);
            }
        }
        AnaRootBlockContent rootBlockContent_ = instancingAnonContent.getBlock().getRootBlockContent();
        for (TypeVar t: selected_) {
            TypeVar t_ = new TypeVar();
            t_.setOffset(instancingAnonContent.getBlock().getIdRowCol());
            t_.setLength(t.getLength());
            StringList const_ = new StringList();
            for (String c: t.getConstraints()) {
                const_.add(StringExpUtil.getQuickFormattedType(c, map_));
            }
            t_.setConstraints(const_);
            t_.setName(t.getName());
            rootBlockContent_.getParamTypes().add(t_);
        }
        String superType_ = AnaInherits.getRealClassName(base_,superNames_);
        instancingAnonContent.getBlock().getDirectSuperTypes().add(superType_);
        instancingAnonContent.getBlock().getExplicitDirectSuperTypes().put(-1, false);
        instancingAnonContent.getBlock().getRowColDirectSuperTypes().put(-1, superType_);
        base = base_;
        RootBlock parentType_ = instancingAnonContent.getBlock().getParentType();
        OperatorBlock operator_ = instancingAnonContent.getBlock().getOperator();
        if (parentType_ != null) {
            instancingAnonContent.getBlock().getAllReservedInners().addAllElts(parentType_.getAllReservedInners());
        }
        if (operator_ != null) {
            instancingAnonContent.getBlock().getAllReservedInners().addAllElts(operator_.getAllReservedInners());
        }
        MemberCallingsBlock currentFct_ = _page.getCurrentFct();
        if (currentFct_ != null) {
            currentFct_.getAnonymous().add(instancingAnonContent.getBlock());
            instancingAnonContent.getBlock().getMappings().putAllMap(currentFct_.getMappings());
            instancingAnonContent.getBlock().getAllReservedInners().addAllElts(currentFct_.getMappings().getKeys());
        } else {
            if (parentType_ != null) {
                instancingAnonContent.getBlock().getMappings().putAllMap(parentType_.getMappings());
            }
            if (operator_ != null) {
                instancingAnonContent.getBlock().getMappings().putAllMap(operator_.getMappings());
            }
        }
        Block currentBlock_ = _page.getCurrentBlock();
        if (currentBlock_ instanceof InfoBlock) {
            ((InfoBlock)currentBlock_).getAnonymous().add(instancingAnonContent.getBlock());
        } else if (currentBlock_ instanceof MemberCallingsBlock) {
            ((MemberCallingsBlock)currentBlock_).getAnonymous().add(instancingAnonContent.getBlock());
        } else if (currentBlock_ instanceof RootBlock) {
            ((RootBlock)currentBlock_).getAnonymousRoot().add(instancingAnonContent.getBlock());
        }
        instancingAnonContent.getBlock().getStaticInitInterfaces().addAllElts(getStaticInitInterfaces());
        instancingAnonContent.getBlock().getStaticInitInterfacesOffset().addAllElts(getStaticInitInterfacesOffset());
        glClass = _page.getGlobalClass();
        glType = parentType_;
    }
    @Override
    public void analyze(AnalyzedPageEl _page) {
        setClassName(_page.getAliasObject());
        if (!isIntermediateDottedOperation()) {
            analyzeCtor(_page);
        }
    }
    private void analyzeCtor(AnalyzedPageEl _page) {
        String base_ = StringExpUtil.getIdFromAllTypes(type);
        AnaGeneType g_ = _page.getAnaGeneType(base_);
        if (!(g_ instanceof RootBlock)) {
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        setResultClass(new AnaClassArgumentMatching(type));
    }

    public void postAnalyze(AnalyzedPageEl _page) {
        CustList<OperationNode> filter_ = getChildrenNodes();
        int varargOnly_ = lookOnlyForVarArg();
        String varargParam_ = getVarargParam(filter_);
        AnaClassArgumentMatching aClass_ = new AnaClassArgumentMatching(StringExpUtil.getQuickFormattedType(instancingAnonContent.getBlock().getGenericString(), anonTypeVars));
        NameParametersFilter name_ = buildFilter(_page);
        if (!name_.isOk()) {
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        ConstrustorIdVarArg ctorRes_ = getDeclaredCustConstructor(this, varargOnly_, aClass_,instancingAnonContent.getBlock().getFullName(),instancingAnonContent.getBlock(), null, varargParam_, name_, _page);
        if (ctorRes_.getRealId() == null) {
            return;
        }
        instancingAnonContent.setMemberId(ctorRes_.getMemberId());
        setConstructor(ctorRes_.getPair());
        setConstId(ctorRes_.getRealId());
        setClassName(ctorRes_.getConstId().getName());
        if (ctorRes_.isVarArgToCall()) {
            setNaturalVararg(getConstId().getParametersTypes().size() - 1);
            setLastType(getConstId().getParametersTypes().last());
        }
        unwrapArgsFct(getConstId(), getNaturalVararg(), getLastType(), name_.getAll(), _page);
    }

    public AnonymousTypeBlock getBlock() {
        return instancingAnonContent.getBlock();
    }

    public RootBlock getGlType() {
        return glType;
    }

    public String getGlClass() {
        return glClass;
    }

    public String getBase() {
        return base;
    }

    public int getIndex() {
        return index;
    }

    public MemberId getMemberId() {
        return instancingAnonContent.getMemberId();
    }

}
