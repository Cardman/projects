package code.expressionlanguage.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.opers.util.ClassArgumentMatching;

public final class ErrorPartOperation extends LeafOperation {

    public ErrorPartOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
    }

    @Override
    public void analyze(ContextEl _conf) {
        OperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+relativeOff_, _conf);
        String argClName_;
        FoundErrorInterpret emptyPart_ = new FoundErrorInterpret();
        emptyPart_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
        emptyPart_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
        //if parent is null => all text
        //if parent is not null => use parent header
        emptyPart_.buildError(_conf.getAnalysisMessages().getEmptyExpressionPart());
        _conf.getAnalyzing().getLocalizer().addError(emptyPart_);
        argClName_ = _conf.getStandards().getAliasObject();
        setResultClass(new ClassArgumentMatching(argClName_));    
    }

}
