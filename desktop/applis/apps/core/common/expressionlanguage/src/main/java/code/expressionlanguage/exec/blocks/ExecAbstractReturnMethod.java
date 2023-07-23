package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.stacks.AbstractStask;

public abstract class ExecAbstractReturnMethod extends ExecLeaf {

    public void tryReturn(StackCall _call, AbstractPageEl _stack) {
        while (true) {
            AbstractStask bl_ = _stack.tryGetLastStack();
            if (bl_ == null) {
                break;
            }
            if (ExecHelperBlocks.setRemovedCallingFinallyToProcess(_stack,bl_,this,null)) {
                return;
            }
        }
        _call.nullReadWrite();
    }

}
