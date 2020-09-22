package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;

public class BadDottedOperation extends LeafOperation {
    public BadDottedOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        OperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+relativeOff_, _page);
        String argClName_;
        FoundErrorInterpret emptyPart_ = new FoundErrorInterpret();
        emptyPart_.setFileName(_page.getLocalizer().getCurrentFileName());
        emptyPart_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
        //if parent is null => all text
        //if parent is not null => use parent header
        emptyPart_.buildError(_page.getAnalysisMessages().getEmptyExpressionPart());
        _page.getLocalizer().addError(emptyPart_);
        getErrs().add(emptyPart_.getBuiltError());
        argClName_ = _page.getStandards().getAliasObject();
        setResultClass(new AnaClassArgumentMatching(argClName_));
    }

}
