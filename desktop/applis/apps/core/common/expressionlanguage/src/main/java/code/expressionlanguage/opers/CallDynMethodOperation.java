package code.expressionlanguage.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class CallDynMethodOperation extends InvokingOperation {

    public CallDynMethodOperation(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void analyze(ContextEl _conf) {
        LgNames stds_ = _conf.getStandards();
        String fctName_ = getOperations().getFctName().trim();
        if (!StringList.quickEq(fctName_, _conf.getStandards().getAliasCall())) {
            FoundErrorInterpret und_ = new FoundErrorInterpret();
            und_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            und_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //fctName_ len
            und_.buildError(_conf.getAnalysisMessages().getFunctionalApplyOnly(),
                    _conf.getStandards().getAliasCall(),
                    _conf.getStandards().getAliasFct());
            _conf.getAnalyzing().getLocalizer().addError(und_);
        }
        ClassArgumentMatching clCur_ = getPreviousResultClass();
        String fct_ = clCur_.getName();
        StringList all_ = Templates.getAllTypes(fct_);
        String ret_ = all_.last();
        CustList<String> param_ = all_.mid(1, all_.size() - 2);
        CustList<OperationNode> chidren_ = getChildrenNodes();
        CustList<ClassArgumentMatching> firstArgs_ = new CustList<ClassArgumentMatching>();
        for (OperationNode o: chidren_) {
            firstArgs_.add(o.getResultClass());
        }
        if (all_.size() == 1) {
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
        if (firstArgs_.size() != param_.size()) {
            FoundErrorInterpret undefined_ = new FoundErrorInterpret();
            undefined_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            undefined_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //unexpected coma or right parenthese
            undefined_.buildError(_conf.getAnalysisMessages().getFunctionalApplyNbDiff(),
                    Integer.toString(param_.size()),
                    Integer.toString(firstArgs_.size()),
                    _conf.getStandards().getAliasFct());
            _conf.getAnalyzing().getLocalizer().addError(undefined_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        if (!allParamWildCard_) {
            int nb_ = param_.size();
            StringMap<StringList> map_ = _conf.getAnalyzing().getCurrentConstraints().getCurrentConstraints();
            for (int i = 0; i < nb_; i++) {
                ClassArgumentMatching a_ = firstArgs_.get(i);
                String pa_ = param_.get(i);
                ClassArgumentMatching p_ = new ClassArgumentMatching(pa_);
                Mapping m_ = new Mapping();
                m_.setArg(a_);
                m_.setParam(p_);
                m_.setMapping(map_);
                if (!Templates.isCorrectOrNumbers(m_, _conf)) {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                    cast_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                    //character before
                    cast_.buildError(_conf.getAnalysisMessages().getBadImplicitCast(),
                            StringList.join(a_.getNames(),"&"),
                            StringList.join(p_.getNames(),"&"));
                    _conf.getAnalyzing().getLocalizer().addError(cast_);
                }
                if (PrimitiveTypeUtil.isPrimitive(pa_, _conf)) {
                    a_.setUnwrapObject(pa_);
                    chidren_.get(i).cancelArgument();
                }
            }
        }
        String void_ = stds_.getAliasVoid();
        if (StringList.quickEq(ret_, void_) || StringList.quickEq(ret_, Templates.SUB_TYPE)) {
            ret_ = stds_.getAliasObject();
        }
        setResultClass(new ClassArgumentMatching(ret_));
    }

}
