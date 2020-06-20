package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.exec.blocks.WithEl;

public final class BlockPageEl extends AbstractPageEl {

    @Override
    public boolean checkCondition(ContextEl _context) {
        return true;
    }

    @Override
    public void tryProcessEl(ContextEl _context) {
        //static block or instance block walk through
        ReadWrite rw_ = getReadWrite();
        ExecBlock en_ = rw_.getBlock();
        if (en_ instanceof WithEl) {
            ((WithEl)en_).processEl(_context);
            return;
        }
        setNullReadWrite();
    }

}
