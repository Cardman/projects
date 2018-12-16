package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.errors.custom.VarargError;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;

public final class VarargOperation extends ConstLeafOperation {

    private String className;
    private int offset;

    public VarargOperation(int _indexInEl, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
        offset = _op.getValues().firstKey();
        className = _op.getValues().firstValue();
    }

    @Override
    public void analyze(Analyzable _conf) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl() + offset, _conf);
        LgNames stds_ = _conf.getStandards();
        MethodOperation m_ = getParent();
        if (!m_.isCallMethodCtor()) {
            VarargError varg_ = new VarargError();
            varg_.setFileName(_conf.getCurrentFileName());
            varg_.setIndexFile(_conf.getCurrentLocationIndex());
            varg_.setMethodName(VAR_ARG);
            _conf.getClasses().addError(varg_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            setSimpleArgument(new Argument());
            return;
        }
        if (!isFirstChild()) {
            VarargError varg_ = new VarargError();
            varg_.setFileName(_conf.getCurrentFileName());
            varg_.setIndexFile(_conf.getCurrentLocationIndex());
            varg_.setMethodName(VAR_ARG);
            _conf.getClasses().addError(varg_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            setSimpleArgument(new Argument());
            return;
        }
        String str_ = className.substring(className.indexOf(PAR_LEFT)+1, className.lastIndexOf(PAR_RIGHT));
        str_ = _conf.resolveCorrectType(str_);
        setResultClass(new ClassArgumentMatching(str_));
        className = str_;
        setSimpleArgument(new Argument());
    }

    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
        analyzeNotBoolAssignmentAfter(_conf);
    }

}
