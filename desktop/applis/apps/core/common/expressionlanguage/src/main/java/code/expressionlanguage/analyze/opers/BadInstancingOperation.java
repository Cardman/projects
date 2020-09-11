package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.inherits.ClassArgumentMatching;

public final class BadInstancingOperation extends LeafOperation {

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
        AnalyzedPageEl page_ = _conf.getAnalyzing();
        emptyPart_.setFileName(page_.getLocalizer().getCurrentFileName());
        emptyPart_.setIndexFile(page_.getLocalizer().getCurrentLocationIndex());
        //if parent is null => all text
        //if parent is not null => use parent header
        emptyPart_.buildError(_conf.getAnalysisMessages().getEmptyExpressionPart());
        getErrs().add(emptyPart_.getBuiltError());
        page_.getLocalizer().addError(emptyPart_);
        argClName_ = page_.getStandards().getAliasObject();
        setResultClass(new ClassArgumentMatching(argClName_));
    }

}
