package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.util.core.StringUtil;

public final class RefTernaryOperation extends AbstractRefTernaryOperation {

    public RefTernaryOperation(int _index, int _indexChild, MethodOperation _m,
                               OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        String fct_ = _op.getFctName();
        setOffsetLocal(StringUtil.getFirstPrintableCharIndex(fct_));
    }

}
