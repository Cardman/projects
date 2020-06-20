package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.inherits.ClassArgumentMatching;

public final class ValueOperation extends LeafOperation {
    private int off;
    private String className;
    public ValueOperation(int _indexInEl, int _indexChild, MethodOperation _m, OperationsSequence _op, String _className) {
        super(_indexInEl, _indexChild, _m, _op);
        off = _op.getOffset();
        className = _className;
    }

    @Override
    public void analyze(ContextEl _conf) {
        setResultClass(new ClassArgumentMatching(className));
    }

    public int getOff() {
        return off;
    }
}
