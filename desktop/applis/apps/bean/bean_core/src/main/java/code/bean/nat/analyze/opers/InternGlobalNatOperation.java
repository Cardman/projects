package code.bean.nat.analyze.opers;

import code.bean.nat.analyze.NatAnalyzingDoc;
import code.bean.nat.analyze.blocks.NatAnalyzedCode;
import code.bean.nat.analyze.instr.NatOperationsSequence;

public final class InternGlobalNatOperation extends LeafNatOperation {
    private final NatAnalyzingDoc analyzingDoc;

    public InternGlobalNatOperation(int _indexInEl, int _indexChild,
                                    MethodNatOperation _m, NatOperationsSequence _op, NatAnalyzingDoc _analyzingDoc) {
        super(_indexInEl, _indexChild, _m, _op);
        analyzingDoc = _analyzingDoc;
    }

    @Override
    public void analyze(NatAnalyzedCode _page) {
        String arg_ = analyzingDoc.getInternGlobalClass();
        setResultClass(arg_);
    }

}
