package code.expressionlanguage.opers;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.methods.Block;
import code.util.CustList;
import code.util.StringMap;

public final class AndOperation extends QuickOperation {

    public AndOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void tryCalculateNode(ContextEl _conf) {
        tryGetResult(_conf, this, false, isOkNum());
    }


}
