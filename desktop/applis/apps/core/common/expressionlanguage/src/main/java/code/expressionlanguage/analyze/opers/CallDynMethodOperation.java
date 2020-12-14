package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.fwd.opers.AnaArrContent;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.linkage.LinkageUtil;
import code.util.CustList;
import code.util.IntTreeMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class CallDynMethodOperation extends InvokingOperation implements SettableElResult,AbstractCallLeftOperation {
    private String sepErr = "";
    private boolean noNeed;
    private int indexCh=-1;
    private String fctName;
    private AnaArrContent arrContent;
    private boolean errLeftValue;
    public CallDynMethodOperation(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        arrContent = new AnaArrContent();
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        String fctName_ = getOperations().getFctName().trim();
        fctName = fctName_;
        CustList<OperationNode> chidren_ = getChildrenNodes();
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
}
