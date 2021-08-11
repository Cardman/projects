package code.bean.nat.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.bean.nat.analyze.instr.NatOperationsSequence;
import code.expressionlanguage.stds.PrimitiveTypes;

public final class UnaryBooleanNatOperation extends AbstractUnaryNatOperation {
    private int opOffset;
    private boolean okNum;

    public UnaryBooleanNatOperation(int _index,
                                    int _indexChild, MethodNatOperation _m, NatOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void analyzeUnary(AnalyzedPageEl _page) {
        okNum = true;
        String booleanPrimType_ = _page.getAliasPrimBoolean();
        opOffset = getOperations().getOperators().firstKey();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+opOffset, _page);
        setResultClass(new NatAnaClassArgumentMatching(booleanPrimType_));
    }

}
