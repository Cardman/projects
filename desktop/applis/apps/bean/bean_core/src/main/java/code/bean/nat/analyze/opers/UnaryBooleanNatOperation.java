package code.bean.nat.analyze.opers;

import code.bean.nat.analyze.blocks.NatAnalyzedCode;
import code.bean.nat.analyze.instr.NatOperationsSequence;

public final class UnaryBooleanNatOperation extends AbstractUnaryNatOperation {

    public UnaryBooleanNatOperation(int _index,
                                    int _indexChild, MethodNatOperation _m, NatOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void analyzeUnary(NatAnalyzedCode _page) {
        String booleanPrimType_ = _page.getAliasPrimBoolean();
        int opOffset_ = getOperations().getOperators().firstKey();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+opOffset_, _page);
        setResultClass(booleanPrimType_);
    }

}
