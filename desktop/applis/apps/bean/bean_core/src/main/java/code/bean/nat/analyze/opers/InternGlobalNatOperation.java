package code.bean.nat.analyze.opers;

import code.bean.nat.analyze.blocks.NatAnalyzedCode;
import code.bean.nat.analyze.instr.NatOperationsSequence;
import code.formathtml.analyze.AnalyzingDoc;

public final class InternGlobalNatOperation extends LeafNatOperation {
    private final int off;
    private final AnalyzingDoc analyzingDoc;

    public InternGlobalNatOperation(int _indexInEl, int _indexChild,
                                    MethodNatOperation _m, NatOperationsSequence _op, AnalyzingDoc _analyzingDoc) {
        super(_indexInEl, _indexChild, _m, _op);
        off = _op.getOffset();
        analyzingDoc = _analyzingDoc;
    }

    @Override
    public void analyze(NatAnalyzedCode _page) {
        NatOperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+relativeOff_, _page);
        String arg_ = analyzingDoc.getInternGlobalClass();
        setResultClass(arg_);
    }

    public int getOff() {
        return off;
    }
}
