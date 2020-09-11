package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.opers.util.ConstrustorIdVarArg;
import code.expressionlanguage.analyze.opers.util.NameParametersFilter;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.StringList;

public final class AnonymousInstancingOperation extends
        AbstractInstancingOperation implements PreAnalyzableOperation {

    private AnonymousTypeBlock block;
    private String glClass;
    private String base;
    private int index;
    private int rootNumber = -1;
    private int memberNumber = -1;

    public AnonymousInstancingOperation(int _index, int _indexChild,
                                        MethodOperation _m, OperationsSequence _op, AnonymousTypeBlock _block) {
        super(_index, _indexChild, _m, _op);
        block = _block;
    }


    @Override
    public void preAnalyze(ContextEl _an) {
        KeyWords keyWords_ = _an.getKeyWords();
        String newKeyWord_ = keyWords_.getKeyWordNew();
        String afterNew_ = getMethodName().trim().substring(newKeyWord_.length());
        int j_ = afterNew_.indexOf("}");
        if (j_ > -1) {
            setNewBefore(false);
        }
    }

    @Override
    public void analyze(ContextEl _conf) {
        tryAnalyze(_conf);
        AnalyzedPageEl page_ = _conf.getAnalyzing();
        index = page_.getLocalizer().getCurrentLocationIndex();
        int off_ = StringList.getFirstPrintableCharIndex(getMethodName());
        setClassName(page_.getStandards().getAliasObject());
        KeyWords keyWords_ = _conf.getKeyWords();
        String newKeyWord_ = keyWords_.getKeyWordNew();
        String realClassName_ = getMethodName().trim().substring(newKeyWord_.length());
        int j_ = realClassName_.indexOf("}");
        if (j_ > -1) {
            realClassName_ = realClassName_.substring(j_+1);
            off_ += j_+1;
        }
        if (getTypeInfer().contains("#")) {
            FoundErrorInterpret static_ = new FoundErrorInterpret();
            static_.setFileName(page_.getLocalizer().getCurrentFileName());
            static_.setIndexFile(page_.getLocalizer().getCurrentLocationIndex());
            //original type len
            static_.buildError(_conf.getAnalysisMessages().getIllegalCtorUnknown(),
                    realClassName_);
            page_.getLocalizer().addError(static_);
            getErrs().add(static_.getBuiltError());
        }
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        if (!isIntermediateDottedOperation()) {
            setStaticAccess(page_.getStaticContext());
            if (!getTypeInfer().isEmpty()) {
                realClassName_ = getTypeInfer();
            } else  {
                int local_ = StringList.getFirstPrintableCharIndex(realClassName_);
                realClassName_ = ResolvingImportTypes.resolveCorrectType(_conf,newKeyWord_.length()+local_,realClassName_);
                getPartOffsets().addAllElts(page_.getCurrentParts());
            }
            checkInstancingType(_conf, realClassName_, isStaticAccess(), getErrs());
            analyzeCtor(_conf, realClassName_);
            return;
        }
        FoundErrorInterpret static_ = new FoundErrorInterpret();
        static_.setFileName(page_.getLocalizer().getCurrentFileName());
        static_.setIndexFile(page_.getLocalizer().getCurrentLocationIndex());
        //key word len
        static_.buildError(_conf.getAnalysisMessages().getIllegalCtorUnknown(),
                realClassName_);
        page_.getLocalizer().addError(static_);
        getErrs().add(static_.getBuiltError());
        LgNames stds_ = page_.getStandards();
        setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
    }
    private void analyzeCtor(ContextEl _conf, String _realClassName) {
        AnalyzedPageEl page_ = _conf.getAnalyzing();
        LgNames stds_ = page_.getStandards();
        String base_ = StringExpUtil.getIdFromAllTypes(_realClassName);
        AnaGeneType g_ = page_.getAnaGeneType(base_);
        if (!(g_ instanceof ImmutableNameRootBlock)) {
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFileName(page_.getLocalizer().getCurrentFileName());
            call_.setIndexFile(page_.getLocalizer().getCurrentLocationIndex());
            //type len
            call_.buildError(_conf.getAnalysisMessages().getIllegalCtorUnknown(),
                    _realClassName);
            page_.getLocalizer().addError(call_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            getErrs().add(call_.getBuiltError());
            return;
        }
        OperationNode possibleInit_ = getFirstChild();
        if (possibleInit_ instanceof StaticInitOperation) {
            StaticInitOperation st_ = (StaticInitOperation) possibleInit_;
            boolean staticType_ = g_.isStaticType();
            st_.setInit(_conf,base_,staticType_);
        }
        for (String p:StringExpUtil.getAllTypes(_realClassName).mid(1)){
            if (p.startsWith(Templates.SUB_TYPE)) {
                FoundErrorInterpret call_ = new FoundErrorInterpret();
                call_.setFileName(page_.getLocalizer().getCurrentFileName());
                call_.setIndexFile(page_.getLocalizer().getCurrentLocationIndex());
                //part type len
                call_.buildError(_conf.getAnalysisMessages().getIllegalCtorBound(),
                        p,
                        _realClassName);
                page_.getLocalizer().addError(call_);
                getErrs().add(call_.getBuiltError());
            }
            if (p.startsWith(Templates.SUP_TYPE)) {
                FoundErrorInterpret call_ = new FoundErrorInterpret();
                call_.setFileName(page_.getLocalizer().getCurrentFileName());
                call_.setIndexFile(page_.getLocalizer().getCurrentLocationIndex());
                //part type len
                call_.buildError(_conf.getAnalysisMessages().getIllegalCtorBound(),
                        p,
                        _realClassName);
                page_.getLocalizer().addError(call_);
                getErrs().add(call_.getBuiltError());
            }
        }
        block.getDirectSuperTypes().add(_realClassName);
        block.getExplicitDirectSuperTypes().put(-1, false);
        block.getRowColDirectSuperTypes().put(-1, _realClassName);
        block.setName(((ImmutableNameRootBlock)g_).getName());
        block.setParentType(page_.getGlobalType());
        base = base_;
        block.getAllReservedInners().addAllElts(page_.getGlobalDirType().getAllReservedInners());
        MemberCallingsBlock currentFct_ = page_.getCurrentFct();
        if (currentFct_ != null) {
            currentFct_.getAnonymous().add(block);
            block.getMappings().putAllMap(currentFct_.getMappings());
            block.getAllReservedInners().addAllElts(currentFct_.getMappings().getKeys());
        } else {
            block.getMappings().putAllMap(page_.getGlobalDirType().getMappings());
        }
        Block currentBlock_ = page_.getCurrentBlock();
        if (currentBlock_ instanceof InfoBlock) {
            ((InfoBlock)currentBlock_).getAnonymous().add(block);
        } else if (currentBlock_ instanceof MemberCallingsBlock) {
            ((MemberCallingsBlock)currentBlock_).getAnonymous().add(block);
        } else if (currentBlock_ instanceof RootBlock) {
            ((RootBlock)currentBlock_).getAnonymousRoot().add(block);
        }
        block.getStaticInitInterfaces().addAllElts(getStaticInitInterfaces());
        block.getStaticInitInterfacesOffset().addAllElts(getStaticInitInterfacesOffset());
        setResultClass(new ClassArgumentMatching(_realClassName));
        glClass = page_.getGlobalClass();
    }

    public void postAnalyze(ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<OperationNode> filter_ = ElUtil.filterInvoking(chidren_);
        int varargOnly_ = lookOnlyForVarArg();
        String varargParam_ = getVarargParam(filter_);
        ClassArgumentMatching aClass_ = new ClassArgumentMatching(block.getGenericString());
        NameParametersFilter name_ = buildFilter(_conf);
        if (!name_.isOk()) {
            setResultClass(new ClassArgumentMatching(_conf.getAnalyzing().getStandards().getAliasObject()));
            return;
        }
        ConstrustorIdVarArg ctorRes_ = getDeclaredCustConstructor(this,_conf, varargOnly_, aClass_,block.getFullName(),block, null, varargParam_, name_);
        if (ctorRes_.getRealId() == null) {
            return;
        }
        rootNumber = ctorRes_.getRootNumber();
        memberNumber = ctorRes_.getMemberNumber();
        setConstId(ctorRes_.getRealId());
        setClassName(ctorRes_.getConstId().getName());
        if (ctorRes_.isVarArgToCall()) {
            setNaturalVararg(getConstId().getParametersTypes().size() - 1);
            setLastType(getConstId().getParametersTypes().last());
        }
        unwrapArgsFct(filter_, getConstId(), getNaturalVararg(), getLastType(), name_.getAll(), _conf);
    }

    public AnonymousTypeBlock getBlock() {
        return block;
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
        return rootNumber;
    }

    public int getMemberNumber() {
        return memberNumber;
    }
}
