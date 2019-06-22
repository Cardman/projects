package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.errors.custom.BadImplicitCast;
import code.expressionlanguage.errors.custom.BadNumberArgMethod;
import code.expressionlanguage.errors.custom.UndefinedFieldError;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;

public final class CallDynMethodOperation extends ReflectableInvokingOperation {

    public CallDynMethodOperation(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculateChildren() {
        IntTreeMap< String> vs_ = getOperations().getValues();
        vs_.removeKey(vs_.firstKey());
        getChildren().putAllMap(vs_);
    }

    @Override
    public void analyze(Analyzable _conf) {
        LgNames stds_ = _conf.getStandards();
        String fctName_ = getOperations().getFctName().trim();
        if (!StringList.quickEq(fctName_, _conf.getStandards().getAliasCall())) {
            UndefinedFieldError und_ = new UndefinedFieldError();
            und_.setClassName("");
            und_.setFileName(_conf.getCurrentFileName());
            und_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getClasses().addError(und_);
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
        if (hasVoidArguments(chidren_, firstArgs_, 0, _conf)) {
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
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
            StringList classesNames_ = new StringList();
            for (ClassArgumentMatching c: firstArgs_) {
                classesNames_.add(StringList.join(c.getNames(), ""));
            }
            BadNumberArgMethod undefined_ = new BadNumberArgMethod();
            undefined_.setNbVars(chidren_.size());
            undefined_.setNbTypes(param_.size());
            undefined_.setId(fct_);
            undefined_.setFileName(_conf.getCurrentFileName());
            undefined_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getClasses().addError(undefined_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        if (!allParamWildCard_) {
            int nb_ = param_.size();
            StringMap<StringList> map_ = _conf.getCurrentConstraints();
            for (int i = 0; i < nb_; i++) {
                ClassArgumentMatching a_ = firstArgs_.get(i);
                String pa_ = param_.get(i);
                ClassArgumentMatching p_ = new ClassArgumentMatching(pa_);
                Mapping m_ = new Mapping();
                m_.setArg(a_);
                m_.setParam(p_);
                m_.setMapping(map_);
                if (!Templates.isCorrectOrNumbers(m_, _conf)) {
                    BadImplicitCast cast_ = new BadImplicitCast();
                    cast_.setMapping(m_);
                    cast_.setFileName(_conf.getCurrentFileName());
                    cast_.setIndexFile(_conf.getCurrentLocationIndex());
                    _conf.getClasses().addError(cast_);
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
