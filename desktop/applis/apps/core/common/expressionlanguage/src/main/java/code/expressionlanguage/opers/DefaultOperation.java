package code.expressionlanguage.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.util.IntTreeMap;

public final class DefaultOperation extends AbstractUnaryOperation implements FirstOptOperable {

    private int offset;
    public DefaultOperation(int _index, int _indexChild, MethodOperation _m,
                            OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculateChildren() {
        IntTreeMap< String> vs_ = getOperations().getValues();
        offset = vs_.firstKey();
        vs_.removeKey(vs_.firstKey());
        getChildren().putAllMap(vs_);
    }

    @Override
    public void analyzeUnary(ContextEl _conf) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+offset, _conf);
        OperationNode child_ = getFirstChild();
        ClassArgumentMatching res_ = child_.getResultClass();
        if (res_.isVariable()) {
            setResultClass(new ClassArgumentMatching(_conf.getStandards().getAliasObject()));
            return;
        }
        setResultClass(new ClassArgumentMatching(res_));
    }

    public int getOffset() {
        return offset;
    }
}
