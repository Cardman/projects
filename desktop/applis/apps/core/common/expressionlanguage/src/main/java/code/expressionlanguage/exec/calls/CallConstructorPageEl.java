package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.WithEl;

public final class CallConstructorPageEl extends AbstractCallingInstancingPageEl {
    @Override
    public void processTagsBase(ContextEl _context, StackCall _stack) {
        if (!checkCondition(_context, _stack)) {
            return;
        }
        ExecBlock en_ = getBlock();
        if (en_ instanceof WithEl) {
            setGlobalOffset(en_.getOffsetTrim());
            setOffset(0);
            ((WithEl)en_).processEl(_context, _stack);
            return;
        }
        setNullReadWrite();
    }

}
