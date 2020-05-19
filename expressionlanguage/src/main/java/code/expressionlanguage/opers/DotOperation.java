package code.expressionlanguage.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.opers.exec.Operable;
import code.expressionlanguage.opers.exec.ParentOperable;
import code.util.CustList;

public final class DotOperation extends AbstractDotOperation {

    public DotOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    public void quickCalculate(ContextEl _conf) {
        DotOperation.setArg(this,_conf);
    }

    public static void setArg(ParentOperable _op, ContextEl _conf) {
        CustList<Operable> chidren_ = _op.getChildrenOperable();
        _op.setSimpleArgumentAna(chidren_.last().getArgument(), _conf);
    }
}
