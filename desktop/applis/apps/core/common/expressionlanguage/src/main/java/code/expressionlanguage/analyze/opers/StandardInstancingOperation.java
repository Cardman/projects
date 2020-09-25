package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.opers.util.*;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.util.ClassMethodIdAncestor;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.fwd.opers.AnaInstancingStdContent;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class StandardInstancingOperation extends
        AbstractInstancingOperation implements PreAnalyzableOperation,RetrieveConstructor {

    private boolean hasFieldName;
    private AnaInstancingStdContent instancingStdContent;

    private CustList<ConstructorInfo> ctors = new CustList<ConstructorInfo>();
    private int rootNumber = -1;
    private int memberNumber = -1;

    public StandardInstancingOperation(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        instancingStdContent = new AnaInstancingStdContent();
    }

    public void setFieldName(String _fieldName) {
        instancingStdContent.setFieldName(_fieldName);
    }

    public void setHasFieldName(boolean _hasFieldName) {
        hasFieldName = _hasFieldName;
    }

    @Override
    public void preAnalyze(AnalyzedPageEl _page) {
        tryAnalyze(_page);
        if (getTypeInfer().isEmpty()) {
            return;
        }
        tryGetCtors(getTypeInfer(), ctors,_page);
    }



    @Override
    public void analyze(AnalyzedPageEl _page) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(getMethodName());
        setClassName(_page.getStandards().getAliasObject());
        KeyWords keyWords_ = _page.getKeyWords();
        String newKeyWord_ = keyWords_.getKeyWordNew();
        String realClassName_ = getMethodName().trim().substring(newKeyWord_.length());
        int j_ = realClassName_.indexOf("}");
        if (j_ > -1) {
            realClassName_ = realClassName_.substring(j_+1);
            off_ += j_+1;
        }
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _page);
        CustList<OperationNode> filter_ = ElUtil.filterInvoking(chidren_);
        String varargParam_ = getVarargParam(filter_);
        if (!isIntermediateDottedOperation()) {
            setStaticAccess(_page.getStaticContext());
            if (!getTypeInfer().isEmpty()) {
                realClassName_ = getTypeInfer();
            } else if (!hasFieldName) {
                int local_ = StringList.getFirstPrintableCharIndex(realClassName_);
                realClassName_ = ResolvingImportTypes.resolveCorrectType(newKeyWord_.length()+local_,realClassName_, _page);
                getPartOffsets().addAllElts(_page.getCurrentParts());
            } else {
                realClassName_ = realClassName_.trim();
            }
            checkInstancingType(realClassName_, isStaticAccess(), getErrs(), _page);
            analyzeCtor(realClassName_, varargParam_, _page);
            return;
        }
        if (!getTypeInfer().isEmpty()) {
            analyzeCtor(getTypeInfer(), varargParam_, _page);
            return;
        }
        int offset_ = StringList.getFirstPrintableCharIndex(realClassName_);
        realClassName_ = realClassName_.trim();
        AnaClassArgumentMatching arg_ = getPreviousResultClass();
        if (arg_.isArray()) {
            FoundErrorInterpret static_ = new FoundErrorInterpret();
            static_.setFileName(_page.getLocalizer().getCurrentFileName());
            static_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //key word len
            static_.buildError(_page.getAnalysisMessages().getIllegalCtorUnknown(),
                    realClassName_);
            _page.getLocalizer().addError(static_);
            getErrs().add(static_.getBuiltError());
            LgNames stds_ = _page.getStandards();
            setResultClass(new AnaClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        StringMap<String> ownersMap_ = new StringMap<String>();
        String idClass_ = StringExpUtil.getIdFromAllTypes(realClassName_);
        offset_ += idClass_.length() + 1;
        for (String o: arg_.getNames()) {
            boolean ok_ = true;
            for (String p: StringExpUtil.getAllTypes(o).mid(1)) {
                if (p.startsWith(Templates.SUB_TYPE)) {
                    FoundErrorInterpret call_ = new FoundErrorInterpret();
                    call_.setFileName(_page.getLocalizer().getCurrentFileName());
                    call_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                    //key word len
                    call_.buildError(_page.getAnalysisMessages().getIllegalCtorBound(),
                            p,
                            o);
                    _page.getLocalizer().addError(call_);
                    getErrs().add(call_.getBuiltError());
                    ok_ = false;
                }
                if (p.startsWith(Templates.SUP_TYPE)) {
                    FoundErrorInterpret call_ = new FoundErrorInterpret();
                    call_.setFileName(_page.getLocalizer().getCurrentFileName());
                    call_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                    //key word len
                    call_.buildError(_page.getAnalysisMessages().getIllegalCtorBound(),
                            p,
                            o);
                    _page.getLocalizer().addError(call_);
                    getErrs().add(call_.getBuiltError());
                    ok_ = false;
                }
            }
            if (!ok_) {
                LgNames stds_ = _page.getStandards();
                setResultClass(new AnaClassArgumentMatching(stds_.getAliasObject()));
                return;
            }
        }
        for (String o: arg_.getNames()) {
            StringList owners_ = AnaTypeUtil.getGenericOwners(o, idClass_, _page);
            if (owners_.onlyOneElt()) {
                ownersMap_.put(o, owners_.first());
            }
        }
        if (ownersMap_.size() != 1) {
            FoundErrorInterpret static_ = new FoundErrorInterpret();
            static_.setFileName(_page.getLocalizer().getCurrentFileName());
            static_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //idClass_ len
            static_.buildError(_page.getAnalysisMessages().getNotResolvedOwner(),
                    idClass_
            );
            _page.getLocalizer().addError(static_);
            getErrs().add(static_.getBuiltError());
            LgNames stds_ = _page.getStandards();
            setResultClass(new AnaClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        String sup_ = ownersMap_.values().first();
        StringList partsArgs_ = new StringList();
        for (String a: StringExpUtil.getAllTypes(realClassName_).mid(1)) {
            int loc_ = StringList.getFirstPrintableCharIndex(a);
            partsArgs_.add(ResolvingImportTypes.resolveCorrectType(offset_+loc_,a, _page));
            getPartOffsets().addAllElts(_page.getCurrentParts());
            offset_ += a.length() + 1;
        }
        StringMap<StringList> vars_ = _page.getCurrentConstraints().getCurrentConstraints();
        realClassName_ = AnaTemplates.check(getErrs(),StringList.concat(sup_, "..", idClass_), partsArgs_, vars_, _page);
        analyzeCtor(realClassName_, varargParam_, _page);
    }


    private void analyzeCtor(String _realClassName, String _paramVargArg, AnalyzedPageEl _page) {
        LgNames stds_ = _page.getStandards();
        int varargOnly_ = lookOnlyForVarArg();
        ClassMethodIdAncestor idMethod_ = lookOnlyForId();
        String base_ = StringExpUtil.getIdFromAllTypes(_realClassName);
        AnaGeneType g_ = _page.getAnaGeneType(base_);
        if (g_ == null) {
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFileName(_page.getLocalizer().getCurrentFileName());
            call_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //type len
            call_.buildError(_page.getAnalysisMessages().getIllegalCtorUnknown(),
                    _realClassName);
            _page.getLocalizer().addError(call_);
            setResultClass(new AnaClassArgumentMatching(stds_.getAliasObject()));
            getErrs().add(call_.getBuiltError());
            return;
        }
        OperationNode possibleInit_ = getFirstChild();
        if (possibleInit_ instanceof StaticInitOperation) {
            StaticInitOperation st_ = (StaticInitOperation) possibleInit_;
            boolean staticType_ = g_.isStaticType();
            st_.setInit(base_,staticType_, _page);
        }
        for (String p:StringExpUtil.getAllTypes(_realClassName).mid(1)){
            if (p.startsWith(Templates.SUB_TYPE)) {
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
            if (p.startsWith(Templates.SUP_TYPE)) {
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
        }
        if (ContextUtil.isAbstractType(g_) && !ContextUtil.isEnumType(g_)) {
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFileName(_page.getLocalizer().getCurrentFileName());
            call_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //type len
            call_.buildError(_page.getAnalysisMessages().getIllegalCtorAbstract(),
                    base_);
            _page.getLocalizer().addError(call_);
            getErrs().add(call_.getBuiltError());
            setResultClass(new AnaClassArgumentMatching(_realClassName));
            return;
        }
        instancingStdContent.setBlockIndex(ContextUtil.getCurrentChildTypeIndex(this, g_, instancingStdContent.getFieldName(), _realClassName, _page));
        if (instancingStdContent.getBlockIndex() < -1) {
            return;
        }
        ConstructorId feed_ = null;
        if (idMethod_ != null) {
            ClassMethodId id_ = idMethod_.getClassMethodId();
            String idClass_ = id_.getClassName();
            boolean vararg_ = id_.getConstraints().isVararg();
            StringList params_ = id_.getConstraints().getParametersTypes();
            feed_ = new ConstructorId(idClass_, params_, vararg_);
        }
        NameParametersFilter name_ = buildFilter(_page);
        if (!name_.isOk()) {
            setResultClass(new AnaClassArgumentMatching(_page.getStandards().getAliasObject()));
            return;
        }
        ConstrustorIdVarArg ctorRes_ = getDeclaredCustConstructor(this, varargOnly_, new AnaClassArgumentMatching(_realClassName),base_,g_, feed_, _paramVargArg, name_, _page);
        if (ctorRes_.getRealId() == null) {
            setResultClass(new AnaClassArgumentMatching(_realClassName));
            return;
        }
        setConstId(ctorRes_.getRealId());
        setClassName(ctorRes_.getConstId().getName());
        rootNumber = ctorRes_.getRootNumber();
        memberNumber = ctorRes_.getMemberNumber();
        if (ctorRes_.isVarArgToCall()) {
            setNaturalVararg(getConstId().getParametersTypes().size() - 1);
            setLastType(getConstId().getParametersTypes().last());
        }
        unwrapArgsFct(getConstId(), getNaturalVararg(), getLastType(), name_.getAll(), _page);
        setResultClass(new AnaClassArgumentMatching(_realClassName));
    }

    public boolean isHasFieldName() {
        return hasFieldName;
    }

    public AnaInstancingStdContent getInstancingStdContent() {
        return instancingStdContent;
    }

    public CustList<ConstructorInfo> getCtors() {
        return ctors;
    }

    public int getRootNumber() {
        return rootNumber;
    }

    public int getMemberNumber() {
        return memberNumber;
    }
}
