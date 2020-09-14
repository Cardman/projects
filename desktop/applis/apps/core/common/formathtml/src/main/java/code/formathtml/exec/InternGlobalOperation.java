package code.formathtml.exec;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.LeafOperation;
import code.expressionlanguage.analyze.opers.MethodOperation;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.formathtml.Configuration;

public final class InternGlobalOperation extends LeafOperation {
    private int off;
    private Configuration configuration;

    public InternGlobalOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op, Configuration _conf) {
        super(_indexInEl, _indexChild, _m, _op);
        off = _op.getOffset();
        configuration = _conf;
    }

    @Override
    public void analyze(ContextEl _conf) {
        OperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+relativeOff_, configuration.getContext());
        String arg_ = configuration.getInternGlobalClass();
        if (configuration.getAnalyzing().isStaticContext()) {
            FoundErrorInterpret static_ = new FoundErrorInterpret();
            static_.setFileName(configuration.getAnalyzingDoc().getFileName());
            static_.setIndexFile(Configuration.getCurrentLocationIndex(configuration.getContext().getAnalyzing(), configuration.getAnalyzingDoc()));
            static_.buildError(configuration.getContext().getAnalyzing().getAnalysisMessages().getStaticAccess(),
                    configuration.getContext().getAnalyzing().getKeyWords().getKeyWordThis());
            Configuration.addError(static_, configuration.getAnalyzingDoc(), configuration.getContext().getAnalyzing());
        }
        setResultClass(new ClassArgumentMatching(arg_));
    }

    public int getOff() {
        return off;
    }
}
