package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.maths.litteral.StrTypes;
import code.util.*;
import code.util.core.StringUtil;

public final class TernaryOperation extends AbstractTernaryOperation {

    public TernaryOperation(int _index, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculateChildren() {
        StrTypes vs_ = getOperations().getValues();
        vs_.remove(0);
        getChildren().addAllEntries(vs_);
        String fct_ = getOperations().getFctName();
        setOffsetLocal(StringUtil.getFirstPrintableCharIndex(fct_));
    }

}
