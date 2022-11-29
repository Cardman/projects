package code.bean.nat.analyze.opers;

import code.bean.nat.analyze.blocks.NatAnalyzedCode;
import code.bean.nat.analyze.instr.NatOperationsSequence;

public final class InternGlobalNatOperation extends LeafNatOperation {
    private final String internGlobalClass;

    public InternGlobalNatOperation(int _indexInEl, int _indexChild,
                                    MethodNatOperation _m, NatOperationsSequence _op, String _intern) {
        super(_indexInEl, _indexChild, _m, _op);
        internGlobalClass = _intern;
    }

    @Override
    public void analyze(NatAnalyzedCode _page) {
        setResultClass(internGlobalClass);
    }

}
