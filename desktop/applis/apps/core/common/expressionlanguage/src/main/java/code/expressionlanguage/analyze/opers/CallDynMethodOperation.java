package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.InfoErrorDto;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.util.FormattedFilter;
import code.expressionlanguage.analyze.opers.util.MethodInfo;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.util.ClassMethodIdAncestor;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.opers.AnaArrContent;
import code.expressionlanguage.linkage.ExportCst;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.stds.StandardType;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.BoolVal;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class CallDynMethodOperation extends InvokingOperation implements PreAnalyzableOperation,RetrieveMethod,SettableElResult,AbstractCallLeftOperation {
    private String sepErr = "";
    private boolean noNeed;
    private int indexCh=-1;
    private String fctName;
    private final AnaArrContent arrContent;
    private boolean errLeftValue;
    private final String methodFound;
    private final CustList<CustList<MethodInfo>> methodInfos = new CustList<CustList<MethodInfo>>();
    public CallDynMethodOperation(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        arrContent = new AnaArrContent();
        methodFound = _op.getFctName().trim();
    }

    @Override
    public void preAnalyze(AnalyzedPageEl _page) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _page);
        CustList<MethodInfo> methodInfos_ = new CustList<MethodInfo>();
        StandardType fctType_ = _page.getStandardsTypes().getVal(_page.getAliasFct());
        for (StandardMethod e: fctType_.getMethods()) {
            buildLambdaMethods(_page, methodInfos_, e);
        }
        methodInfos.add(methodInfos_);
        filterByNameReturnType(_page, methodFound, methodInfos);
    }

    private void buildLambdaMethods(AnalyzedPageEl _page, CustList<MethodInfo> _methodInfos, StandardMethod _e) {
        AnaClassArgumentMatching clCur_ = getPreviousResultClass();
        String fct_ = clCur_.getName();
        StandardType fctType_ = _page.getStandardsTypes().getVal(_page.getAliasFct());
        MethodId id_ = _e.getId();
        MethodInfo m_;
        String name_ = id_.getName();
        if (!StringUtil.quickEq(name_, _page.getAliasCall())) {
            m_ = OperationNode.getMethodInfo(_e, 0, fct_, _page,id_, new FormattedFilter());
            _methodInfos.add(m_);
            return;
        }
        StringList all_ = StringExpUtil.getAllTypes(fct_);
        String ret_ = all_.last();
        CustList<String> param_;
        if (all_.size() == 1) {
            param_ = new StringList();
            int len_ = getChildren().size();
            for (int i = 0; i < len_; i++) {
                param_.add(_page.getAliasObject());
            }
        } else {
            param_ = all_.leftMinusOne(all_.size() - 2);
        }
        m_ = new MethodInfo();
        m_.setOwner(fctType_);
        m_.setOriginalReturnType(_page.getAliasObject());
        m_.setStandardMethod(_e);
        m_.setParametersNames(_e.getParametersNames());
        m_.classMethodId(fct_,id_);
        String retBase_;
        boolean refRet_;
        if (StringUtil.quickEq(ret_, StringExpUtil.SUB_TYPE)) {
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
        CustList<BoolVal> refs_ = new CustList<BoolVal>();
        for (String c: param_) {
            if (StringUtil.quickEq(c, StringExpUtil.SUB_TYPE)) {
                cls_.add(_page.getAliasObject());
                refs_.add(BoolVal.FALSE);
            } else if (c.startsWith("~")) {
                cls_.add(c.substring(1));
                refs_.add(BoolVal.TRUE);
            } else {
                cls_.add(c);
                refs_.add(BoolVal.FALSE);
            }
        }
        m_.format(refRet_,
                name_,cls_,refs_);
        _methodInfos.add(m_);
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _page);
        String fctName_ = getMethodFound();
        fctName = fctName_;
        CustList<OperationNode> chidren_ = getChildrenNodes();
        ClassMethodIdAncestor idMethod_ = lookOnlyForId();
        if (idMethod_ != null) {
            chidren_ = adjustChildren(_page, chidren_, idMethod_);
        }
        if (StringUtil.quickEq(fctName_, _page.getAliasMetaInfo())) {
            withoutParamsCheck(_page, chidren_);
            setResultClass(new AnaClassArgumentMatching(_page.getAliasAnnotated()));
            return;
        }
        if (StringUtil.quickEq(fctName_, _page.getAliasInstance())) {
            withoutParamsCheck(_page, chidren_);
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        if (!StringUtil.quickEq(fctName_, _page.getAliasCall())) {
            errLeftValue = true;
            functionalApplyOnly(_page);
        }
        if (!chidren_.isEmpty()) {
            OperationNode last_ = lastArg(_page, chidren_);
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
            errorDiff(_page, param_, firstArgs_);
            return;
        }
        boolean allParamWildCard_ = allParamWildCard(param_);
        if (!allParamWildCard_) {
            notAllWildCard(_page, chidren_, param_, firstArgs_);
        }
        String void_ = _page.getAliasVoid();
        if (StringUtil.quickEq(ret_, void_) || StringUtil.quickEq(ret_, StringExpUtil.SUB_TYPE)) {
            ret_ = _page.getAliasObject();
        }
        if (!ret_.startsWith("~")) {
            errLeftValue = true;
            setResultClass(new AnaClassArgumentMatching(ret_));
        } else {
            setResultClass(new AnaClassArgumentMatching(ret_.substring(1)));
        }
    }

    private void notAllWildCard(AnalyzedPageEl _page, CustList<OperationNode> _children, CustList<String> _param, CustList<AnaClassArgumentMatching> _firstArgs) {
        int nb_ = _param.size();
        for (int i = 0; i < nb_; i++) {
            checkParam(_page, _children, _param, _firstArgs, i);
        }
    }

    private void checkParam(AnalyzedPageEl _page, CustList<OperationNode> _children, CustList<String> _param, CustList<AnaClassArgumentMatching> _firstArgs, int _index) {
        StringMap<StringList> map_ = _page.getCurrentConstraints().getCurrentConstraints();
        StrTypes operators_ = getOperators();
        InfoErrorDto parts_ = new InfoErrorDto("");
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ operators_.getKey(_index), _page);
        AnaClassArgumentMatching a_ = _firstArgs.get(_index);
        String pa_ = _param.get(_index);
        Mapping m_ = new Mapping();
        m_.setArg(a_);
        m_.setParam(pa_);
        m_.setMapping(map_);
        if (pa_.startsWith("~")) {
            if (!(_children.get(_index) instanceof WrappOperation)||!a_.matchClass(pa_.substring(1))) {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFile(_page.getCurrentFile());
                cast_.setIndexFile(_page);
                //character before
                cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        StringUtil.join(a_.getNames(),ExportCst.JOIN_TYPES),
                        pa_);
                _page.getLocalizer().addError(cast_);
                parts_= new InfoErrorDto(cast_, _page,1);
            }
        } else if (!StringUtil.quickEq("?", pa_)) {
            if (_children.get(_index) instanceof WrappOperation) {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFile(_page.getCurrentFile());
                cast_.setIndexFile(_page);
                //character before
                cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        StringUtil.join(a_.getNames(),ExportCst.JOIN_TYPES),
                        pa_);
                _page.getLocalizer().addError(cast_);
                parts_= new InfoErrorDto(cast_, _page,1);
            } else if (!AnaInherits.isCorrectOrNumbers(m_, _page)) {
                ClassMethodIdReturn res_ = tryGetDeclaredImplicitCast(pa_, a_, _page);
                if (res_ != null) {
                    a_.implicitInfos(res_);
                } else {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFile(_page.getCurrentFile());
                    cast_.setIndexFile(_page);
                    //character before
                    cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            StringUtil.join(a_.getNames(),ExportCst.JOIN_TYPES),
                            pa_);
                    _page.getLocalizer().addError(cast_);
                    parts_= new InfoErrorDto(cast_, _page,1);
                }
            }
        }
        if (AnaTypeUtil.isPrimitive(pa_, _page)) {
            a_.setUnwrapObject(pa_, _page.getPrimitiveTypes());
        }
        getPartOffsetsChildren().add(parts_);
    }

    private boolean allParamWildCard(CustList<String> _param) {
        boolean allParamWildCard_ = true;
        for (String p : _param) {
            if (!StringUtil.quickEq(p, StringExpUtil.SUB_TYPE)) {
                allParamWildCard_ = false;
                break;
            }
        }
        return allParamWildCard_;
    }

    private void errorDiff(AnalyzedPageEl _page, CustList<String> _param, CustList<AnaClassArgumentMatching> _firstArgs) {
        errLeftValue = true;
        if (_param.isEmpty()) {
            noNeed = true;
            diff(_page, _param.size(), _firstArgs.size());
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        indexCh = NumberUtil.min(_param.size() - 1, _firstArgs.size()-1);
        diff(_page, _param.size(), _firstArgs.size());
        setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
    }

    private void diff(AnalyzedPageEl _page, int _expected, int _found) {
        FoundErrorInterpret undefined_ = new FoundErrorInterpret();
        undefined_.setFile(_page.getCurrentFile());
        undefined_.setIndexFile(_page);
        //unexpected coma or right parenthese
        undefined_.buildError(_page.getAnalysisMessages().getFunctionalApplyNbDiff(),
                Long.toString(_expected),
                Long.toString(_found),
                _page.getAliasFct());
        _page.getLocalizer().addError(undefined_);
        sepErr = undefined_.getBuiltError();
    }

    private OperationNode lastArg(AnalyzedPageEl _page, CustList<OperationNode> _children) {
        OperationNode last_ = _children.last();
        if (!(last_ instanceof NamedArgumentOperation)) {
            for (OperationNode o: _children) {
                if (o instanceof NamedArgumentOperation) {
                    functionalApplyOnly(_page);
                }
            }
            return last_;
        }
        if (!(last_.getFirstChild() instanceof ArgumentListInstancing)) {
            functionalApplyOnly(_page);
        } else {
            StringList paramNames_ = paramNames(_page);
            if (StringUtil.indexOf(paramNames_,((NamedArgumentOperation) last_).getName()) < 0) {
                functionalApplyOnly(_page);
            }
            last_ = last_.getFirstChild();
            //last_ is now an ArgumentListInstancing
        }
        if (_children.size() > 1){
            functionalApplyOnly(_page);
        }
        return last_;
    }

    private StringList paramNames(AnalyzedPageEl _page) {
        StandardType fctType_ = _page.getStandardsTypes().getVal(_page.getAliasFct());
        StringList paramNames_ = new StringList();
        for (StandardMethod e: fctType_.getMethods()) {
            MethodId id_ = e.getId();
            String name_ = id_.getName();
            if (StringUtil.quickEq(name_, _page.getAliasCall())) {
                paramNames_ = e.getParametersNames();
            }
        }
        return paramNames_;
    }

    private void withoutParamsCheck(AnalyzedPageEl _page, CustList<OperationNode> _children) {
        errLeftValue = true;
        if (!_children.isEmpty()) {
            noNeed = true;
            diff(_page, 0, _children.size());
        }
    }

    private CustList<OperationNode> adjustChildren(AnalyzedPageEl _page, CustList<OperationNode> _children, ClassMethodIdAncestor _idMethod) {
        ClassMethodId id_ = _idMethod.getClassMethodId();
        String idClass_ = id_.getClassName();
        MethodId mid_ = id_.getConstraints();
        MethodAccessKind static_ = MethodId.getKind(isStaticAccess(), mid_.getKind());
        if (!StringUtil.quickEq(idClass_, _page.getAliasFct())) {
            functionalApplyOnly(_page);
        }
        if (static_ != MethodAccessKind.INSTANCE) {
            functionalApplyOnly(_page);
        }
        if (mid_.isRetRef()) {
            functionalApplyOnly(_page);
        }
        if ((StringUtil.quickEq(fctName, _page.getAliasMetaInfo()) || StringUtil.quickEq(fctName, _page.getAliasInstance())) && mid_.getParametersTypesLength() != 0) {
            functionalApplyOnly(_page);
        }
        if (StringUtil.quickEq(fctName, _page.getAliasCall())) {
            if (!mid_.isVararg()) {
                functionalApplyOnly(_page);
            }
            if (mid_.getParametersTypesLength() != 1||!StringUtil.quickEq(mid_.getParametersType(0), _page.getAliasObject())||mid_.getParametersRef(0) == BoolVal.TRUE){
                functionalApplyOnly(_page);
            }
        }
        if (_idMethod.getAncestor() != 0) {
            functionalApplyOnly(_page);
        }
        return filterArgs(_children);
    }

    private CustList<OperationNode> filterArgs(CustList<OperationNode> _children) {
        CustList<OperationNode> filter_ = new CustList<OperationNode>();
        for (OperationNode o: _children) {
            if (getDeltaCount(o) != 0) {
                continue;
            }
            filter_.add(o);
        }
        return filter_;
    }

    private void functionalApplyOnly(AnalyzedPageEl _page) {
        FoundErrorInterpret und_ = new FoundErrorInterpret();
        und_.setFile(_page.getCurrentFile());
        und_.setIndexFile(_page);
        //fctName_ len
        und_.buildError(_page.getAnalysisMessages().getFunctionalApplyOnly(),
                _page.getAliasCall(),
                _page.getAliasFct());
        _page.getLocalizer().addError(und_);
        addErr(und_.getBuiltError());
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
    public String getMethodFound() {
        return methodFound;
    }

    @Override
    public CustList<CustList<MethodInfo>> getMethodInfos() {
        return methodInfos;
    }
}
