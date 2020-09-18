package code.formathtml.exec;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.LeafOperation;
import code.expressionlanguage.analyze.opers.MethodOperation;
import code.formathtml.Configuration;
import code.formathtml.util.AnalyzingDoc;

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
    public void analyze(ContextEl _conf) {
        OperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+relativeOff_, _conf);
        String arg_ = analyzingDoc.getInternGlobalClass();
        AnalyzedPageEl page_ = _conf.getAnalyzing();
        if (page_.isStaticContext()) {
            FoundErrorInterpret static_ = new FoundErrorInterpret();
            static_.setFileName(analyzingDoc.getFileName());
            static_.setIndexFile(Configuration.getCurrentLocationIndex(page_, analyzingDoc));
            static_.buildError(page_.getAnalysisMessages().getStaticAccess(),
                    page_.getKeyWords().getKeyWordThis());
            Configuration.addError(static_, analyzingDoc, page_);
        }
        setResultClass(new AnaClassArgumentMatching(arg_));
    }

    public int getOff() {
        return off;
    }
}
