package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.Struct;

public final class ExecThrowing extends ExecLeaf implements WithEl {


    private final ExecOperationNodeListOff exp;

    public ExecThrowing(ExecOperationNodeListOff _ex) {
        exp = _ex;
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        AbstractPageEl ip_ = _stack.getLastPage();
        ip_.globalOffset(exp.getOffset());
        Struct arg_ = ExecHelperBlocks.tryToCalculate(_cont,0,_stack,exp.getList(),0, this,exp.getEnd());
        if (_stack.stopAt(_cont)) {
            return;
        }
        _stack.setCallingState(new CustomFoundExc(ArgumentListCall.getNull(arg_)));
    }

}
