package code.formathtml.exec;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.opers.LeafOperation;
import code.expressionlanguage.opers.MethodOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.StringList;

public final class InternGlobalOperation extends LeafOperation {
    private int off;

    public InternGlobalOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
        off = _op.getOffset();
    }

    @Override
    public void analyze(Analyzable _conf) {
        OperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+relativeOff_, _conf);
        String arg_ = ((Configuration)_conf).getInternGlobalClass();
        if (_conf.getAnalyzing().isStaticContext()) {
            FoundErrorInterpret static_ = new FoundErrorInterpret();
            static_.setFileName(_conf.getCurrentFileName());
            static_.setIndexFile(_conf.getCurrentLocationIndex());
            static_.buildError(((Configuration)_conf).getContext().getAnalysisMessages().getStaticAccess(),
                    ((Configuration)_conf).getContext().getKeyWords().getKeyWordThis());
            _conf.addError(static_);
        }
        setResultClass(new ClassArgumentMatching(arg_));
    }

    public int getOff() {
        return off;
    }
}
