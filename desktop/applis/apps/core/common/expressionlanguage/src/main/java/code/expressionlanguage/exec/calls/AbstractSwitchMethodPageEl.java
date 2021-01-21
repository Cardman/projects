package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecAbstractSwitchMethod;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.stacks.SwitchBlockStack;

public abstract class AbstractSwitchMethodPageEl extends AbstractCommonMethodPageEl {
    private final Argument value;
    protected AbstractSwitchMethodPageEl(Argument _gl, String _glClass,Argument _value) {
        super(_gl, _glClass);
        value = _value;
    }

    @Override
    public boolean checkCondition(ContextEl _context, StackCall _stack) {
        AbstractPageEl ip_ = _stack.getLastPage();
        ExecBlock en_ = ip_.getBlock();
        if (en_ instanceof ExecAbstractSwitchMethod) {
            SwitchBlockStack st_ = new SwitchBlockStack();
            st_.setLabel("");
            ip_.setBlock(((ExecAbstractSwitchMethod)en_).processCase(_context,st_,value,_stack));
        }
        return true;
    }

}
