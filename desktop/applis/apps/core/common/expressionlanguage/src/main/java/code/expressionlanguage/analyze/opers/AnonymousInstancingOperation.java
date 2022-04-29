package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.util.ConstrustorIdVarArg;
import code.expressionlanguage.analyze.opers.util.NameParametersFilter;
import code.expressionlanguage.analyze.opers.util.ResolvedInstance;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.analyze.util.TypeVar;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.fwd.blocks.AnaRootBlockContent;
import code.expressionlanguage.fwd.opers.AnaInstancingAnonContent;
import code.expressionlanguage.options.KeyWords;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.BoolVal;
import code.util.core.StringUtil;

public final class AnonymousInstancingOperation extends
        AbstractInstancingOperation implements PreAnalyzableOperation {

    private final AnaInstancingAnonContent instancingAnonContent;
    private AnaFormattedRootBlock glType = AnaFormattedRootBlock.defValue();
    private String base="";
    private String type="";
    private int index;
    private final StringMap<String> anonTypeVars = new StringMap<String>();

    public AnonymousInstancingOperation(int _index, int _indexChild,
                                        MethodOperation _m, OperationsSequence _op, AnonymousTypeBlock _block) {
        super(_index, _indexChild, _m, _op);
        instancingAnonContent = new AnaInstancingAnonContent(_block);
        _block.setInstancingOperation(this);
    }


    @Override
    public void preAnalyze(AnalyzedPageEl _page) {
        KeyWords keyWords_ = _page.getKeyWords();
        String newKeyWord_ = keyWords_.getKeyWordNew();
        String afterNew_ = getMethodName().trim().substring(newKeyWord_.length());
        int j_ = -1;
        if (afterNew_.trim().startsWith("{")) {
            setNewBefore(false);
            j_ =  afterNew_.indexOf('}',afterNew_.indexOf('{'));
        }
        tryAnalyze(_page);
        index = _page.getTraceIndex();
        int off_ = StringUtil.getFirstPrintableCharIndex(getMethodName());
        String realClassName_ = getMethodName().trim().substring(newKeyWord_.length());
        if (j_ > -1) {
            realClassName_ = realClassName_.substring(j_+1);
            off_ += j_+1;
        }
        int local_ = StringUtil.getFirstPrintableCharIndex(realClassName_);
        realClassName_ = realClassName_.trim();
        realClassName_ = skip(realClassName_);
        local_ += getDeltaAnnot();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+newKeyWord_.length()+off_+local_, _page);
        String name_ = instancingAnonContent.getBlock().getName();
        if (!StringUtil.quickEq(name_, _page.getKeyWords().getKeyWordId())) {
            TokenErrorMessage tokenErrorMessage_ = ManageTokens.partClass(_page).checkToken(name_, _page);
            if (tokenErrorMessage_.isError()) {
                FoundErrorInterpret static_ = new FoundErrorInterpret();
                static_.setFile(_page.getCurrentFile());
                static_.setIndexFile(_page);
                //original type len
                static_.setBuiltError(tokenErrorMessage_.getMessage());
                _page.getLocalizer().addError(static_);
                addErr(static_.getBuiltError());
            }
        }
        if (!isIntermediateDottedOperation()) {
            setStaticAccess(_page.getStaticContext());
            if (!getTypeInfer().isEmpty()) {
                realClassName_ = getTypeInfer();
            } else  {
                AnaResultPartType result_ = ResolvingTypes.resolveCorrectType(0, realClassName_, _page);
                realClassName_ = result_.getResult(_page);
                setResolvedInstance(new ResolvedInstance(result_));
            }
            type = realClassName_;
            checkInstancingType(realClassName_, isStaticAccess(), _page);
            preAnalyzeCtor(realClassName_, _page);
            return;
        }
        FoundErrorInterpret static_ = new FoundErrorInterpret();
        static_.setFile(_page.getCurrentFile());
        static_.setIndexFile(_page);
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
            call_.setFile(_page.getCurrentFile());
            call_.setIndexFile(_page);
            //type len
            call_.buildError(_page.getAnalysisMessages().getIllegalCtorUnknown(),
                    _realClassName);
            _page.getLocalizer().addError(call_);
            addErr(call_.getBuiltError());
            return;
        }
        if (StandardInstancingOperation.chWc(this,_realClassName,_page)) {
            return;
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
        instancingAnonContent.getBlock().getExplicitDirectSuperTypes().put(-1, BoolVal.FALSE);
        instancingAnonContent.getBlock().getRowColDirectSuperTypes().put(-1, superType_);
        base = base_;
        AccessedBlock acc_ = instancingAnonContent.getBlock().getAccessedBlock();
        instancingAnonContent.getBlock().getAllReservedInners().addAllElts(acc_.getAllReservedInners());
        MemberCallingsBlock currentFct_ = _page.getCurrentFct();
        if (currentFct_ != null) {
            instancingAnonContent.getBlock().getMappings().putAllMap(currentFct_.getRefMappings());
            instancingAnonContent.getBlock().getAllReservedInners().addAllElts(currentFct_.getMappings().getKeys());
        } else {
            instancingAnonContent.getBlock().getMappings().putAllMap(acc_.getRefMappings());
        }
        AccessedFct imp_ = _page.getAccessedFct();
        if (imp_ != null) {
            imp_.getAnonymous().add(instancingAnonContent.getBlock());
        }
        AbsBk currentBlock_ = _page.getCurrentBlock();
        if (currentBlock_ instanceof InfoBlock) {
            ((InfoBlock)currentBlock_).getAnonymous().add(instancingAnonContent.getBlock());
        } else if (currentBlock_ instanceof MemberCallingsBlock) {
            ((MemberCallingsBlock)currentBlock_).getAnonymous().add(instancingAnonContent.getBlock());
        } else if (currentBlock_ instanceof RootBlock) {
            ((RootBlock)currentBlock_).getAnonymousRoot().add(instancingAnonContent.getBlock());
        }
        instancingAnonContent.getBlock().getStaticInitInterfaces().addAllElts(getStaticInitInterfaces());
        instancingAnonContent.getBlock().getStaticInitInterfacesOffset().addAllElts(getStaticInitInterfacesOffset());
        glType = _page.getGlobalType();
    }
    @Override
    public void analyze(AnalyzedPageEl _page) {
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
        String formatted_ = StringExpUtil.getQuickFormattedType(instancingAnonContent.getBlock().getGenericString(), anonTypeVars);
        AnaClassArgumentMatching aClass_ = new AnaClassArgumentMatching(formatted_);
        NameParametersFilter name_ = buildFilter(_page);
        if (!name_.getParameterFilterErr().isEmpty()) {
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        ConstrustorIdVarArg ctorRes_ = getDeclaredCustConstructor(varargOnly_, aClass_, instancingAnonContent.getBlock(), null, varargParam_, name_, _page);
        if (ctorRes_ == null) {
            buildCtorError(name_,_page,formatted_);
            return;
        }
        result(formatted_,instancingAnonContent.getBlock(),ctorRes_);
    }

    public AnonymousTypeBlock getBlock() {
        return instancingAnonContent.getBlock();
    }

    public AnaFormattedRootBlock getGlType() {
        return glType;
    }

    public String getBase() {
        return base;
    }

    public int getIndex() {
        return index;
    }


}
