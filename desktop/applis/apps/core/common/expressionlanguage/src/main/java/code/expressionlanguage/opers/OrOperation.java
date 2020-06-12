package code.expressionlanguage.opers;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.instr.OperationsSequence;

public final class OrOperation extends QuickOperation {

    public OrOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void tryCalculateNode(ContextEl _conf) {
        tryGetResult(_conf, this, true, isOkNum());
    }


}
