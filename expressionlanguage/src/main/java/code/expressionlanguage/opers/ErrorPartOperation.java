package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.errors.custom.EmptyPartError;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.opers.util.ClassArgumentMatching;

public final class ErrorPartOperation extends ConstLeafOperation {

    public ErrorPartOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
    }

    @Override
    public void analyze(Analyzable _conf) {
        OperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+relativeOff_, _conf);
        String argClName_;
        EmptyPartError emptyPart_ = new EmptyPartError();
        emptyPart_.setFileName(_conf.getCurrentFileName());
        emptyPart_.setIndexFile(_conf.getCurrentLocationIndex());
        _conf.getClasses().addError(emptyPart_);
        argClName_ = _conf.getStandards().getAliasObject();
        setResultClass(new ClassArgumentMatching(argClName_));    
    }

    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
        analyzeNotBoolAssignmentAfter(_conf);
    }

}
