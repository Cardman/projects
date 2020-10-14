package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.opers.util.ConstrustorIdVarArg;
import code.expressionlanguage.analyze.opers.util.NameParametersFilter;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.fwd.opers.AnaInstancingAnonContent;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.options.KeyWords;
import code.util.CustList;
import code.util.core.StringUtil;

public final class AnonymousInstancingOperation extends
        AbstractInstancingOperation implements PreAnalyzableOperation {

    private AnaInstancingAnonContent instancingAnonContent;
    private String glClass;
    private String base;
    private int index;

    public AnonymousInstancingOperation(int _index, int _indexChild,
                                        MethodOperation _m, OperationsSequence _op, AnonymousTypeBlock _block) {
        super(_index, _indexChild, _m, _op);
        instancingAnonContent = new AnaInstancingAnonContent(_block);
    }


    @Override
    public void preAnalyze(AnalyzedPageEl _page) {
        _page.getAnonymous().last().add(this);
        _page.getAnonymousList().add(this);
        KeyWords keyWords_ = _page.getKeyWords();
        String newKeyWord_ = keyWords_.getKeyWordNew();
        String afterNew_ = getMethodName().trim().substring(newKeyWord_.length());
        int j_ = afterNew_.indexOf("}");
        if (j_ > -1) {
            setNewBefore(false);
        }
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        tryAnalyze(_page);
        index = _page.getLocalizer().getCurrentLocationIndex();
        int off_ = StringUtil.getFirstPrintableCharIndex(getMethodName());
        setClassName(_page.getAliasObject());
        KeyWords keyWords_ = _page.getKeyWords();
        String newKeyWord_ = keyWords_.getKeyWordNew();
        String realClassName_ = getMethodName().trim().substring(newKeyWord_.length());
        int j_ = realClassName_.indexOf("}");
        if (j_ > -1) {
            realClassName_ = realClassName_.substring(j_+1);
            off_ += j_+1;
        }
        if (getTypeInfer().contains("#")) {
            FoundErrorInterpret static_ = new FoundErrorInterpret();
            static_.setFileName(_page.getLocalizer().getCurrentFileName());
            static_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //original type len
            static_.buildError(_page.getAnalysisMessages().getIllegalCtorUnknown(),
                    realClassName_);
            _page.getLocalizer().addError(static_);
            getErrs().add(static_.getBuiltError());
        }
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _page);
        if (!isIntermediateDottedOperation()) {
            setStaticAccess(_page.getStaticContext());
            if (!getTypeInfer().isEmpty()) {
                realClassName_ = getTypeInfer();
            } else  {
                int local_ = StringUtil.getFirstPrintableCharIndex(realClassName_);
                realClassName_ = ResolvingImportTypes.resolveCorrectType(newKeyWord_.length()+local_,realClassName_, _page);
                getPartOffsets().addAllElts(_page.getCurrentParts());
            }
            checkInstancingType(realClassName_, isStaticAccess(), getErrs(), _page);
            analyzeCtor(realClassName_, _page);
            return;
        }
        FoundErrorInterpret static_ = new FoundErrorInterpret();
        static_.setFileName(_page.getLocalizer().getCurrentFileName());
        static_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
        //key word len
        static_.buildError(_page.getAnalysisMessages().getIllegalCtorUnknown(),
                realClassName_);
        _page.getLocalizer().addError(static_);
        getErrs().add(static_.getBuiltError());
        setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
    }
    private void analyzeCtor(String _realClassName, AnalyzedPageEl _page) {
        String base_ = StringExpUtil.getIdFromAllTypes(_realClassName);
        AnaGeneType g_ = _page.getAnaGeneType(base_);
        if (!(g_ instanceof ImmutableNameRootBlock)) {
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFileName(_page.getLocalizer().getCurrentFileName());
            call_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //type len
            call_.buildError(_page.getAnalysisMessages().getIllegalCtorUnknown(),
                    _realClassName);
            _page.getLocalizer().addError(call_);
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            getErrs().add(call_.getBuiltError());
            return;
        }
        OperationNode possibleInit_ = getFirstChild();
        if (possibleInit_ instanceof StaticInitOperation) {
            StaticInitOperation st_ = (StaticInitOperation) possibleInit_;
            boolean staticType_ = g_.isStaticType();
            st_.setInit(base_,staticType_, _page);
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
            getErrs().add(call_.getBuiltError());
        }
        instancingAnonContent.getBlock().getDirectSuperTypes().add(_realClassName);
        instancingAnonContent.getBlock().getExplicitDirectSuperTypes().put(-1, false);
        instancingAnonContent.getBlock().getRowColDirectSuperTypes().put(-1, _realClassName);
        instancingAnonContent.getBlock().setName(((ImmutableNameRootBlock)g_).getName());
        instancingAnonContent.getBlock().setParentType(_page.getGlobalType());
        base = base_;
        instancingAnonContent.getBlock().getAllReservedInners().addAllElts(_page.getGlobalDirType().getAllReservedInners());
        MemberCallingsBlock currentFct_ = _page.getCurrentFct();
        if (currentFct_ != null) {
            currentFct_.getAnonymous().add(instancingAnonContent.getBlock());
            instancingAnonContent.getBlock().getMappings().putAllMap(currentFct_.getMappings());
            instancingAnonContent.getBlock().getAllReservedInners().addAllElts(currentFct_.getMappings().getKeys());
        } else {
            instancingAnonContent.getBlock().getMappings().putAllMap(_page.getGlobalDirType().getMappings());
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
        setResultClass(new AnaClassArgumentMatching(_realClassName));
        glClass = _page.getGlobalClass();
    }

    public void postAnalyze(AnalyzedPageEl _page) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<OperationNode> filter_ = ElUtil.filterInvoking(chidren_);
        int varargOnly_ = lookOnlyForVarArg();
        String varargParam_ = getVarargParam(filter_);
        AnaClassArgumentMatching aClass_ = new AnaClassArgumentMatching(instancingAnonContent.getBlock().getGenericString());
        NameParametersFilter name_ = buildFilter(_page);
        if (!name_.isOk()) {
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        ConstrustorIdVarArg ctorRes_ = getDeclaredCustConstructor(this, varargOnly_, aClass_,instancingAnonContent.getBlock().getFullName(),instancingAnonContent.getBlock(), null, varargParam_, name_, _page);
        if (ctorRes_.getRealId() == null) {
            return;
        }
        instancingAnonContent.setRootNumber(ctorRes_.getRootNumber());
        instancingAnonContent.setMemberNumber(ctorRes_.getMemberNumber());
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

    public String getGlClass() {
        return glClass;
    }

    public String getBase() {
        return base;
    }

    public int getIndex() {
        return index;
    }

    public int getRootNumber() {
        return instancingAnonContent.getRootNumber();
    }

    public int getMemberNumber() {
        return instancingAnonContent.getMemberNumber();
    }
}
