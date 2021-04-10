package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecAbstractSwitchMethod;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.stacks.SwitchBlockStack;

public final class CommonSwitchMethodPageEl extends AbstractCommonMethodPageEl {
    private final Argument value;
    public CommonSwitchMethodPageEl(Argument _gl, String _glClass, Argument _value) {
        super(_gl, _glClass);
        value = _value;
    }

    @Override
    public void processTagsBase(ContextEl _context, StackCall _stack){
        //method walk through
        ExecBlock en_ = getBlock();
        if (en_ instanceof ExecAbstractSwitchMethod) {
            SwitchBlockStack st_ = new SwitchBlockStack();
            st_.setLabel("");
            setBlock(((ExecAbstractSwitchMethod)en_).processCase(_context,st_,value,_stack));
            return;
        }
        processTagsBase(_context,_stack,en_);
    }

}
