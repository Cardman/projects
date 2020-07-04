package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.inherits.ClassArgumentMatching;

public final class BadInstancingOperation extends LeafOperation {

    private String err = "";
    public BadInstancingOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
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
        err = emptyPart_.getBuiltError();
        _conf.getAnalyzing().getLocalizer().addError(emptyPart_);
        argClName_ = _conf.getStandards().getAliasObject();
        setResultClass(new ClassArgumentMatching(argClName_));
    }

    public String getErr() {
        return err;
    }
}
