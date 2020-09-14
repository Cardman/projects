package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.inherits.ClassArgumentMatching;

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
        AnalyzedPageEl page_ = _conf.getAnalyzing();
        emptyPart_.setFileName(page_.getLocalizer().getCurrentFileName());
        emptyPart_.setIndexFile(page_.getLocalizer().getCurrentLocationIndex());
        //if parent is null => all text
        //if parent is not null => use parent header
        emptyPart_.buildError(_conf.getAnalyzing().getAnalysisMessages().getEmptyExpressionPart());
        page_.getLocalizer().addError(emptyPart_);
        getErrs().add(emptyPart_.getBuiltError());
        argClName_ = page_.getStandards().getAliasObject();
        setResultClass(new ClassArgumentMatching(argClName_));    
    }

}
