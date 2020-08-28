package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ClassMethodIdReturn;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.linkage.LinkageUtil;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.IntTreeMap;
import code.util.StringList;
import code.util.StringMap;

public final class CallDynMethodOperation extends InvokingOperation {
    private String sepErr = "";
    private boolean noNeed;
    private int indexCh=-1;
    private String fctName;
    public CallDynMethodOperation(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void analyze(ContextEl _conf) {
        LgNames stds_ = _conf.getStandards();
        String fctName_ = getOperations().getFctName().trim();
        fctName = fctName_;
        CustList<OperationNode> chidren_ = getChildrenNodes();
        if (StringList.quickEq(fctName_, _conf.getStandards().getAliasMetaInfo())) {
            if (!chidren_.isEmpty()) {
                noNeed = true;
                FoundErrorInterpret undefined_ = new FoundErrorInterpret();
                undefined_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                undefined_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                //unexpected coma or right parenthese
                undefined_.buildError(_conf.getAnalysisMessages().getFunctionalApplyNbDiff(),
                        Integer.toString(0),
                        Integer.toString(chidren_.size()),
                        _conf.getStandards().getAliasFct());
                _conf.getAnalyzing().getLocalizer().addError(undefined_);
                sepErr = undefined_.getBuiltError();
            }
            setResultClass(new ClassArgumentMatching(stds_.getAliasAnnotated()));
            return;
        }
        if (StringList.quickEq(fctName_, _conf.getStandards().getAliasInstance())) {
            if (!chidren_.isEmpty()) {
                noNeed = true;
                FoundErrorInterpret undefined_ = new FoundErrorInterpret();
                undefined_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                undefined_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                //unexpected coma or right parenthese
                undefined_.buildError(_conf.getAnalysisMessages().getFunctionalApplyNbDiff(),
                        Integer.toString(0),
                        Integer.toString(chidren_.size()),
                        _conf.getStandards().getAliasFct());
                _conf.getAnalyzing().getLocalizer().addError(undefined_);
                sepErr = undefined_.getBuiltError();
            }
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        if (!StringList.quickEq(fctName_, _conf.getStandards().getAliasCall())) {
            FoundErrorInterpret und_ = new FoundErrorInterpret();
            und_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            und_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //fctName_ len
            und_.buildError(_conf.getAnalysisMessages().getFunctionalApplyOnly(),
                    _conf.getStandards().getAliasCall(),
                    _conf.getStandards().getAliasFct());
            _conf.getAnalyzing().getLocalizer().addError(und_);
            getErrs().add(und_.getBuiltError());
        }
        ClassArgumentMatching clCur_ = getPreviousResultClass();
        String fct_ = clCur_.getName();
        StringList all_ = StringExpUtil.getAllTypes(fct_);
        String ret_ = all_.last();
        CustList<String> param_ = all_.mid(1, all_.size() - 2);
        CustList<ClassArgumentMatching> firstArgs_ = new CustList<ClassArgumentMatching>();
        for (OperationNode o: chidren_) {
            firstArgs_.add(o.getResultClass());
        }
        checkNull(getPreviousArgument(),_conf);
        if (all_.size() == 1) {
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        if (firstArgs_.size() != param_.size()) {
            if (param_.isEmpty()) {
                noNeed = true;
                FoundErrorInterpret undefined_ = new FoundErrorInterpret();
                undefined_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                undefined_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                //unexpected coma or right parenthese
                undefined_.buildError(_conf.getAnalysisMessages().getFunctionalApplyNbDiff(),
                        Integer.toString(param_.size()),
                        Integer.toString(firstArgs_.size()),
                        _conf.getStandards().getAliasFct());
                _conf.getAnalyzing().getLocalizer().addError(undefined_);
                sepErr = undefined_.getBuiltError();
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                return;
            }
            indexCh = Math.min(param_.size() - 1,firstArgs_.size()-1);
            FoundErrorInterpret undefined_ = new FoundErrorInterpret();
            undefined_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            undefined_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //unexpected coma or right parenthese
            undefined_.buildError(_conf.getAnalysisMessages().getFunctionalApplyNbDiff(),
                    Integer.toString(param_.size()),
                    Integer.toString(firstArgs_.size()),
                    _conf.getStandards().getAliasFct());
            _conf.getAnalyzing().getLocalizer().addError(undefined_);
            sepErr = undefined_.getBuiltError();
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        boolean allParamWildCard_ = true;
        for (String p :param_) {
            if (!StringList.quickEq(p, Templates.SUB_TYPE)) {
                allParamWildCard_ = false;
                break;
            }
        }
        if (!allParamWildCard_) {
            int nb_ = param_.size();
            StringMap<StringList> map_ = _conf.getAnalyzing().getCurrentConstraints().getCurrentConstraints();
            for (int i = 0; i < nb_; i++) {
                IntTreeMap<String> operators_ = getOperations().getOperators();
                CustList<PartOffset> parts_ = new CustList<PartOffset>();
                setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ operators_.getKey(i), _conf);
                ClassArgumentMatching a_ = firstArgs_.get(i);
                String pa_ = param_.get(i);
                ClassArgumentMatching p_ = new ClassArgumentMatching(pa_);
                Mapping m_ = new Mapping();
                m_.setArg(a_);
                m_.setParam(p_);
                m_.setMapping(map_);
                if (!AnaTemplates.isCorrectOrNumbers(m_, _conf)) {
                    ClassMethodIdReturn res_ = tryGetDeclaredImplicitCast(_conf, pa_, a_);
                    if (res_.isFoundMethod()) {
                        ClassMethodId cl_ = new ClassMethodId(res_.getId().getClassName(),res_.getRealId());
                        a_.getImplicits().add(cl_);
                        a_.setRootNumber(res_.getRootNumber());
                        a_.setMemberNumber(res_.getMemberNumber());
                    } else {
                        FoundErrorInterpret cast_ = new FoundErrorInterpret();
                        cast_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                        int i_ = _conf.getAnalyzing().getLocalizer().getCurrentLocationIndex();
                        cast_.setIndexFile(i_);
                        //character before
                        cast_.buildError(_conf.getAnalysisMessages().getBadImplicitCast(),
                                StringList.join(a_.getNames(),"&"),
                                StringList.join(p_.getNames(),"&"));
                        _conf.getAnalyzing().getLocalizer().addError(cast_);
                        parts_.add(new PartOffset("<a title=\""+LinkageUtil.transform(cast_.getBuiltError()) +"\" class=\"e\">",i_));
                        parts_.add(new PartOffset("</a>",i_+1));
                    }
                }
                if (PrimitiveTypeUtil.isPrimitive(pa_, _conf)) {
                    a_.setUnwrapObject(pa_);
                    chidren_.get(i).cancelArgument();
                }
                getPartOffsetsChildren().add(parts_);
            }
        }
        String void_ = stds_.getAliasVoid();
        if (StringList.quickEq(ret_, void_) || StringList.quickEq(ret_, Templates.SUB_TYPE)) {
            ret_ = stds_.getAliasObject();
        }
        setResultClass(new ClassArgumentMatching(ret_));
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
}
