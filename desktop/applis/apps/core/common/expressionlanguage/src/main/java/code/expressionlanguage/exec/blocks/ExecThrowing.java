package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class ExecThrowing extends ExecLeaf implements WithEl {


    private final ExecOperationNodeListOff exp;
    public ExecThrowing(int _expressionOffset, CustList<ExecOperationNode> _opThrow) {
        exp = new ExecOperationNodeListOff(_opThrow,_expressionOffset);
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        AbstractPageEl ip_ = _stack.getLastPage();
        ip_.globalOffset(exp.getOffset());
        Argument arg_ = ExecHelperBlocks.tryToCalculate(_cont,0,_stack,exp.getList(),0, this);
        if (_cont.callsOrException(_stack)) {
            return;
        }
        ip_.clearCurrentEls();
        Struct o_ = arg_.getStruct();
        _stack.setCallingState(new CustomFoundExc(o_));
    }

}
