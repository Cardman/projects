package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.opers.util.FormattedFilter;
import code.expressionlanguage.analyze.opers.util.MethodInfo;
import code.expressionlanguage.analyze.opers.util.ParametersGroup;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.util.ClassMethodIdAncestor;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.opers.AnaArrContent;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.linkage.LinkageUtil;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.stds.StandardType;
import code.util.*;
import code.util.core.StringUtil;

public final class CallDynMethodOperation extends InvokingOperation implements PreAnalyzableOperation,RetrieveMethod,SettableElResult,AbstractCallLeftOperation {
    private String sepErr = "";
    private boolean noNeed;
    private int indexCh=-1;
    private String fctName;
    private final AnaArrContent arrContent;
    private boolean errLeftValue;
    private String methodFound = EMPTY_STRING;
    private final CustList<CustList<MethodInfo>> methodInfos = new CustList<CustList<MethodInfo>>();
    public CallDynMethodOperation(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        arrContent = new AnaArrContent();
    }

    @Override
    public void preAnalyze(AnalyzedPageEl _page) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _page);
        String fctName_ = getOperations().getFctName().trim();
        AnaClassArgumentMatching clCur_ = getPreviousResultClass();
        String fct_ = clCur_.getName();
        CustList<MethodInfo> methodInfos_ = new CustList<MethodInfo>();
        StandardType fctType_ = _page.getStandardsTypes().getVal(_page.getAliasFct());
        for (StandardMethod e: fctType_.getMethods()) {
            MethodId id_ = e.getId();
            MethodInfo m_;
            String name_ = id_.getName();
            if (StringUtil.quickEq(name_,_page.getAliasCall())) {
                StringList all_ = StringExpUtil.getAllTypes(fct_);
                String ret_ = all_.last();
                CustList<String> param_;
                if (all_.size() == 1) {
                    param_ = new StringList();
                    int len_ = getOperations().getValues().size();
                    for (int i = 0; i < len_; i++) {
                        param_.add(_page.getAliasObject());
                    }
                } else {
                    param_ = all_.leftMinusOne(all_.size() - 2);
                }
                m_ = new MethodInfo();
                ParametersGroup p_ = new ParametersGroup();
                m_.setOriginalReturnType(_page.getAliasObject());
                m_.setStandardMethod(e);
                m_.setParametersNames(e.getParametersNames());
                m_.setClassName(fct_);
                m_.setConstraints(id_);
                m_.setParameters(p_);
                String retBase_;
                boolean refRet_;
                if (StringUtil.quickEq(ret_, Templates.SUB_TYPE)) {
                    retBase_ = _page.getAliasObject();
                    refRet_ = false;
                } else if (ret_.startsWith("~")) {
                    retBase_ = ret_.substring(1);
                    refRet_ = true;
                } else {
                    retBase_ = ret_;
                    refRet_ = false;
                }
                m_.setReturnType(retBase_);
                m_.setAncestor(0);
                StringList cls_ = new StringList();
                BooleanList refs_ = new BooleanList();
                for (String c: param_) {
                    if (StringUtil.quickEq(c, Templates.SUB_TYPE)) {
                        cls_.add(_page.getAliasObject());
                        refs_.add(false);
                    } else if (c.startsWith("~")) {
                        cls_.add(c.substring(1));
                        refs_.add(true);
                    } else {
                        cls_.add(c);
                        refs_.add(false);
                    }
                }
                m_.format(new MethodId(refRet_, MethodAccessKind.INSTANCE,
                        name_,cls_,refs_,false));
            } else {
                m_ = OperationNode.getMethodInfo(e,false,0,fct_,_page,id_,e.getImportedReturnType(),e.getImportedReturnType(), new FormattedFilter());
            }
            methodInfos_.add(m_);
        }
        methodInfos.add(methodInfos_);
        methodFound = fctName_;
        filterByNameReturnType(_page, fctName_, methodInfos);
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _page);
        String fctName_ = getMethodFound();
        fctName = fctName_;
        CustList<OperationNode> chidren_ = getChildrenNodes();
        ClassMethodIdAncestor idMethod_ = lookOnlyForId();
        if (idMethod_ != null) {
            ClassMethodId id_ = idMethod_.getClassMethodId();
            String idClass_ = id_.getClassName();
            MethodId mid_ = id_.getConstraints();
            MethodAccessKind static_ = MethodId.getKind(isStaticAccess(), mid_.getKind());
            if (!StringUtil.quickEq(idClass_,_page.getAliasFct())) {
                FoundErrorInterpret und_ = new FoundErrorInterpret();
                und_.setFileName(_page.getLocalizer().getCurrentFileName());
                und_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //fctName_ len
                und_.buildError(_page.getAnalysisMessages().getFunctionalApplyOnly(),
                        _page.getAliasCall(),
                        _page.getAliasFct());
                _page.getLocalizer().addError(und_);
                addErr(und_.getBuiltError());
            }
            if (static_ != MethodAccessKind.INSTANCE) {
                FoundErrorInterpret und_ = new FoundErrorInterpret();
                und_.setFileName(_page.getLocalizer().getCurrentFileName());
                und_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //fctName_ len
                und_.buildError(_page.getAnalysisMessages().getFunctionalApplyOnly(),
                        _page.getAliasCall(),
                        _page.getAliasFct());
                _page.getLocalizer().addError(und_);
                addErr(und_.getBuiltError());
            }
            if (mid_.isRetRef()) {
                FoundErrorInterpret und_ = new FoundErrorInterpret();
                und_.setFileName(_page.getLocalizer().getCurrentFileName());
                und_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //fctName_ len
                und_.buildError(_page.getAnalysisMessages().getFunctionalApplyOnly(),
                        _page.getAliasCall(),
                        _page.getAliasFct());
                _page.getLocalizer().addError(und_);
                addErr(und_.getBuiltError());
            }
            if (StringUtil.quickEq(fctName_, _page.getAliasMetaInfo()) || StringUtil.quickEq(fctName_, _page.getAliasInstance())) {
                if (mid_.getParametersTypesLength() != 0) {
                    FoundErrorInterpret und_ = new FoundErrorInterpret();
                    und_.setFileName(_page.getLocalizer().getCurrentFileName());
                    und_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                    //fctName_ len
                    und_.buildError(_page.getAnalysisMessages().getFunctionalApplyOnly(),
                            _page.getAliasCall(),
                            _page.getAliasFct());
                    _page.getLocalizer().addError(und_);
                    addErr(und_.getBuiltError());
                }
            }
            if (StringUtil.quickEq(fctName_, _page.getAliasCall())) {
                if (!mid_.isVararg()) {
                    FoundErrorInterpret und_ = new FoundErrorInterpret();
                    und_.setFileName(_page.getLocalizer().getCurrentFileName());
                    und_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                    //fctName_ len
                    und_.buildError(_page.getAnalysisMessages().getFunctionalApplyOnly(),
                            _page.getAliasCall(),
                            _page.getAliasFct());
                    _page.getLocalizer().addError(und_);
                    addErr(und_.getBuiltError());
                }
                if (mid_.getParametersTypesLength() != 1) {
                    FoundErrorInterpret und_ = new FoundErrorInterpret();
                    und_.setFileName(_page.getLocalizer().getCurrentFileName());
                    und_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                    //fctName_ len
                    und_.buildError(_page.getAnalysisMessages().getFunctionalApplyOnly(),
                            _page.getAliasCall(),
                            _page.getAliasFct());
                    _page.getLocalizer().addError(und_);
                    addErr(und_.getBuiltError());
                } else if (!StringUtil.quickEq(mid_.getParametersType(0), _page.getAliasObject())){
                    FoundErrorInterpret und_ = new FoundErrorInterpret();
                    und_.setFileName(_page.getLocalizer().getCurrentFileName());
                    und_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                    //fctName_ len
                    und_.buildError(_page.getAnalysisMessages().getFunctionalApplyOnly(),
                            _page.getAliasCall(),
                            _page.getAliasFct());
                    _page.getLocalizer().addError(und_);
                    addErr(und_.getBuiltError());
                } else if (mid_.getParametersRef(0)){
                    FoundErrorInterpret und_ = new FoundErrorInterpret();
                    und_.setFileName(_page.getLocalizer().getCurrentFileName());
                    und_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                    //fctName_ len
                    und_.buildError(_page.getAnalysisMessages().getFunctionalApplyOnly(),
                            _page.getAliasCall(),
                            _page.getAliasFct());
                    _page.getLocalizer().addError(und_);
                    addErr(und_.getBuiltError());
                }
            }
            if (idMethod_.getAncestor() != 0) {
                FoundErrorInterpret und_ = new FoundErrorInterpret();
                und_.setFileName(_page.getLocalizer().getCurrentFileName());
                und_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //fctName_ len
                und_.buildError(_page.getAnalysisMessages().getFunctionalApplyOnly(),
                        _page.getAliasCall(),
                        _page.getAliasFct());
                _page.getLocalizer().addError(und_);
                addErr(und_.getBuiltError());
            }
            CustList<OperationNode> filter_ = new CustList<OperationNode>();
            for (OperationNode o: chidren_) {
                if (o instanceof IdFctOperation) {
                    continue;
                }
                filter_.add(o);
            }
            chidren_ = filter_;
        }
        if (StringUtil.quickEq(fctName_, _page.getAliasMetaInfo())) {
            errLeftValue = true;
            if (!chidren_.isEmpty()) {
                noNeed = true;
                FoundErrorInterpret undefined_ = new FoundErrorInterpret();
                undefined_.setFileName(_page.getLocalizer().getCurrentFileName());
                undefined_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //unexpected coma or right parenthese
                undefined_.buildError(_page.getAnalysisMessages().getFunctionalApplyNbDiff(),
                        Long.toString(0),
                        Long.toString(chidren_.size()),
                        _page.getAliasFct());
                _page.getLocalizer().addError(undefined_);
                sepErr = undefined_.getBuiltError();
            }
            setResultClass(new AnaClassArgumentMatching(_page.getAliasAnnotated()));
            return;
        }
        if (StringUtil.quickEq(fctName_, _page.getAliasInstance())) {
            errLeftValue = true;
            if (!chidren_.isEmpty()) {
                noNeed = true;
                FoundErrorInterpret undefined_ = new FoundErrorInterpret();
                undefined_.setFileName(_page.getLocalizer().getCurrentFileName());
                undefined_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //unexpected coma or right parenthese
                undefined_.buildError(_page.getAnalysisMessages().getFunctionalApplyNbDiff(),
                        Long.toString(0),
                        Long.toString(chidren_.size()),
                        _page.getAliasFct());
                _page.getLocalizer().addError(undefined_);
                sepErr = undefined_.getBuiltError();
            }
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        if (!StringUtil.quickEq(fctName_, _page.getAliasCall())) {
            errLeftValue = true;
            FoundErrorInterpret und_ = new FoundErrorInterpret();
            und_.setFileName(_page.getLocalizer().getCurrentFileName());
            und_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //fctName_ len
            und_.buildError(_page.getAnalysisMessages().getFunctionalApplyOnly(),
                    _page.getAliasCall(),
                    _page.getAliasFct());
            _page.getLocalizer().addError(und_);
            addErr(und_.getBuiltError());
        }
        if (!chidren_.isEmpty()) {
            OperationNode last_ = chidren_.last();
            if (last_ instanceof NamedArgumentOperation) {
                if (!(last_.getFirstChild() instanceof ArgumentListInstancing)) {
                    FoundErrorInterpret und_ = new FoundErrorInterpret();
                    und_.setFileName(_page.getLocalizer().getCurrentFileName());
                    und_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                    //fctName_ len
                    und_.buildError(_page.getAnalysisMessages().getFunctionalApplyOnly(),
                            _page.getAliasCall(),
                            _page.getAliasFct());
                    _page.getLocalizer().addError(und_);
                    addErr(und_.getBuiltError());
                } else {
                    StandardType fctType_ = _page.getStandardsTypes().getVal(_page.getAliasFct());
                    StringList paramNames_ = new StringList();
                    for (StandardMethod e: fctType_.getMethods()) {
                        MethodId id_ = e.getId();
                        String name_ = id_.getName();
                        if (StringUtil.quickEq(name_,_page.getAliasCall())) {
                            paramNames_ = e.getParametersNames();
                        }
                    }
                    if (StringUtil.indexOf(paramNames_,((NamedArgumentOperation) last_).getName()) < 0) {
                        FoundErrorInterpret und_ = new FoundErrorInterpret();
                        und_.setFileName(_page.getLocalizer().getCurrentFileName());
                        und_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                        //fctName_ len
                        und_.buildError(_page.getAnalysisMessages().getFunctionalApplyOnly(),
                                _page.getAliasCall(),
                                _page.getAliasFct());
                        _page.getLocalizer().addError(und_);
                        addErr(und_.getBuiltError());
                    }
                    last_ = last_.getFirstChild();
                }
                if (chidren_.size() > 1){
                    FoundErrorInterpret und_ = new FoundErrorInterpret();
                    und_.setFileName(_page.getLocalizer().getCurrentFileName());
                    und_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                    //fctName_ len
                    und_.buildError(_page.getAnalysisMessages().getFunctionalApplyOnly(),
                            _page.getAliasCall(),
                            _page.getAliasFct());
                    _page.getLocalizer().addError(und_);
                    addErr(und_.getBuiltError());
                }
            } else {
                for (OperationNode o: chidren_) {
                    if (o instanceof NamedArgumentOperation) {
                        FoundErrorInterpret und_ = new FoundErrorInterpret();
                        und_.setFileName(_page.getLocalizer().getCurrentFileName());
                        und_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                        //fctName_ len
                        und_.buildError(_page.getAnalysisMessages().getFunctionalApplyOnly(),
                                _page.getAliasCall(),
                                _page.getAliasFct());
                        _page.getLocalizer().addError(und_);
                        addErr(und_.getBuiltError());
                    }
                }
            }
            if (last_ instanceof ArgumentListInstancing) {
                chidren_ = ((ArgumentListInstancing) last_).getChildrenNodes();
            }
        }
        AnaClassArgumentMatching clCur_ = getPreviousResultClass();
        String fct_ = clCur_.getName();
        StringList all_ = StringExpUtil.getAllTypes(fct_);
        String ret_ = all_.last();
        CustList<String> param_ = all_.leftMinusOne(all_.size() - 2);
        CustList<AnaClassArgumentMatching> firstArgs_ = new CustList<AnaClassArgumentMatching>();
        for (OperationNode o: chidren_) {
            firstArgs_.add(o.getResultClass());
        }
        if (all_.size() == 1) {
            errLeftValue = true;
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        if (firstArgs_.size() != param_.size()) {
            errLeftValue = true;
            if (param_.isEmpty()) {
                noNeed = true;
                FoundErrorInterpret undefined_ = new FoundErrorInterpret();
                undefined_.setFileName(_page.getLocalizer().getCurrentFileName());
                undefined_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //unexpected coma or right parenthese
                undefined_.buildError(_page.getAnalysisMessages().getFunctionalApplyNbDiff(),
                        Long.toString(param_.size()),
                        Long.toString(firstArgs_.size()),
                        _page.getAliasFct());
                _page.getLocalizer().addError(undefined_);
                sepErr = undefined_.getBuiltError();
                setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                return;
            }
            indexCh = Math.min(param_.size() - 1,firstArgs_.size()-1);
            FoundErrorInterpret undefined_ = new FoundErrorInterpret();
            undefined_.setFileName(_page.getLocalizer().getCurrentFileName());
            undefined_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //unexpected coma or right parenthese
            undefined_.buildError(_page.getAnalysisMessages().getFunctionalApplyNbDiff(),
                    Long.toString(param_.size()),
                    Long.toString(firstArgs_.size()),
                    _page.getAliasFct());
            _page.getLocalizer().addError(undefined_);
            sepErr = undefined_.getBuiltError();
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        boolean allParamWildCard_ = true;
        for (String p :param_) {
            if (!StringUtil.quickEq(p, Templates.SUB_TYPE)) {
                allParamWildCard_ = false;
                break;
            }
        }
        if (!allParamWildCard_) {
            int nb_ = param_.size();
            StringMap<StringList> map_ = _page.getCurrentConstraints().getCurrentConstraints();
            for (int i = 0; i < nb_; i++) {
                IntTreeMap<String> operators_ = getOperations().getOperators();
                CustList<PartOffset> parts_ = new CustList<PartOffset>();
                setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ operators_.getKey(i), _page);
                AnaClassArgumentMatching a_ = firstArgs_.get(i);
                String pa_ = param_.get(i);
                Mapping m_ = new Mapping();
                m_.setArg(a_);
                m_.setParam(pa_);
                m_.setMapping(map_);
                if (pa_.startsWith("~")) {
                    if (!(chidren_.get(i) instanceof WrappOperation)||!a_.matchClass(pa_.substring(1))) {
                        FoundErrorInterpret cast_ = new FoundErrorInterpret();
                        cast_.setFileName(_page.getLocalizer().getCurrentFileName());
                        int i_ = _page.getLocalizer().getCurrentLocationIndex();
                        cast_.setIndexFile(i_);
                        //character before
                        cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                                StringUtil.join(a_.getNames(),"&"),
                                pa_);
                        _page.getLocalizer().addError(cast_);
                        parts_.add(new PartOffset("<a title=\""+LinkageUtil.transform(cast_.getBuiltError()) +"\" class=\"e\">",i_));
                        parts_.add(new PartOffset("</a>",i_+1));
                    }
                } else if (!StringUtil.quickEq("?", pa_)) {
                    if (chidren_.get(i) instanceof WrappOperation) {
                        FoundErrorInterpret cast_ = new FoundErrorInterpret();
                        cast_.setFileName(_page.getLocalizer().getCurrentFileName());
                        int i_ = _page.getLocalizer().getCurrentLocationIndex();
                        cast_.setIndexFile(i_);
                        //character before
                        cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                                StringUtil.join(a_.getNames(),"&"),
                                pa_);
                        _page.getLocalizer().addError(cast_);
                        parts_.add(new PartOffset("<a title=\""+LinkageUtil.transform(cast_.getBuiltError()) +"\" class=\"e\">",i_));
                        parts_.add(new PartOffset("</a>",i_+1));
                    } else if (!AnaTemplates.isCorrectOrNumbers(m_, _page)) {
                        ClassMethodIdReturn res_ = tryGetDeclaredImplicitCast(pa_, a_, _page);
                        if (res_.isFoundMethod()) {
                            ClassMethodId cl_ = new ClassMethodId(res_.getId().getClassName(),res_.getRealId());
                            a_.getImplicits().add(cl_);
                            a_.setMemberId(res_.getMemberId());
                            a_.setFunction(res_.getPair());
                        } else {
                            FoundErrorInterpret cast_ = new FoundErrorInterpret();
                            cast_.setFileName(_page.getLocalizer().getCurrentFileName());
                            int i_ = _page.getLocalizer().getCurrentLocationIndex();
                            cast_.setIndexFile(i_);
                            //character before
                            cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                                    StringUtil.join(a_.getNames(),"&"),
                                    pa_);
                            _page.getLocalizer().addError(cast_);
                            parts_.add(new PartOffset("<a title=\""+LinkageUtil.transform(cast_.getBuiltError()) +"\" class=\"e\">",i_));
                            parts_.add(new PartOffset("</a>",i_+1));
                        }
                    }
                }
                if (AnaTypeUtil.isPrimitive(pa_, _page)) {
                    a_.setUnwrapObject(pa_, _page.getPrimitiveTypes());
                }
                getPartOffsetsChildren().add(parts_);
            }
        }
        String void_ = _page.getAliasVoid();
        if (StringUtil.quickEq(ret_, void_) || StringUtil.quickEq(ret_, Templates.SUB_TYPE)) {
            ret_ = _page.getAliasObject();
        }
        if (!ret_.startsWith("~")) {
            errLeftValue = true;
            setResultClass(new AnaClassArgumentMatching(ret_));
        } else {
            setResultClass(new AnaClassArgumentMatching(ret_.substring(1)));
        }
    }

    public int getIndexCh() {
        return indexCh;
    }

    public String getSepErr() {
        return sepErr;
    }

    public boolean isNoNeed() {
        return noNeed;
    }

    public String getFctName() {
        return fctName;
    }

    @Override
    public boolean isErrLeftValue() {
        return errLeftValue;
    }

    @Override
    public AnaArrContent getArrContent() {
        return arrContent;
    }

    @Override
    public void setVariable(boolean _variable) {
        arrContent.setVariable(_variable);
    }

    @Override
    public void setCatenizeStrings() {
        arrContent.setCatString(true);
    }

    @Override
    public String getMethodFound() {
        return methodFound;
    }

    @Override
    public CustList<CustList<MethodInfo>> getMethodInfos() {
        return methodInfos;
    }
}
