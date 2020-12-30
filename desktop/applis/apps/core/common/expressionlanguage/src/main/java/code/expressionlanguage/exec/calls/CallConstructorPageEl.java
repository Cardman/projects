package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.WithEl;

public final class CallConstructorPageEl extends AbstractCallingInstancingPageEl {

    @Override
    public void tryProcessEl(ContextEl _context, StackCall _stack) {
        //constructor walk through (class, enum, interface)
        ExecBlock en_ = getBlock();
        if (en_ instanceof WithEl) {
            ((WithEl)en_).processEl(_context, _stack);
            return;
        }
        setNullReadWrite();
    }

}
