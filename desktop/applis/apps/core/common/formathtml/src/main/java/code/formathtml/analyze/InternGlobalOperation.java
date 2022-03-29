package code.formathtml.analyze;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.LeafOperation;
import code.expressionlanguage.analyze.opers.MethodOperation;

public final class InternGlobalOperation extends LeafOperation {
    private int off;
    private AnalyzingDoc analyzingDoc;

    public InternGlobalOperation(int _indexInEl, int _indexChild,
                                 MethodOperation _m, OperationsSequence _op, AnalyzingDoc _analyzingDoc) {
        super(_indexInEl, _indexChild, _m, _op);
        off = _op.getOffset();
        analyzingDoc = _analyzingDoc;
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        OperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+relativeOff_, _page);
        String arg_ = analyzingDoc.getInternGlobalClass();
        if (_page.isStaticContext()) {
            FoundErrorInterpret static_ = new FoundErrorInterpret();
            static_.setFile(_page.getCurrentFile());
            static_.setIndexFile(_page);
            static_.buildError(_page.getAnalysisMessages().getStaticAccess(),
                    _page.getKeyWords().getKeyWordThis());
            AnalyzingDoc.addError(static_, _page);
        }
        setResultClass(new AnaClassArgumentMatching(arg_));
    }

    public int getOff() {
        return off;
    }
}
