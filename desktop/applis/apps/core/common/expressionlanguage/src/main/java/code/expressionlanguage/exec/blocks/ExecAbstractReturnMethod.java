package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.stacks.AbstractStask;

public abstract class ExecAbstractReturnMethod extends ExecLeaf implements MethodCallingFinally {

    protected void tryReturn(StackCall _stack) {
        AbstractPageEl ip_ = _stack.getLastPage();
        while (true) {
            AbstractStask bl_ = ip_.tryGetLastStack();
            if (bl_ == null) {
                break;
            }
            if (ExecHelperBlocks.setRemovedCallingFinallyToProcess(ip_,bl_,this,null)) {
                return;
            }
        }
        ip_.setNullReadWrite();
    }

}
