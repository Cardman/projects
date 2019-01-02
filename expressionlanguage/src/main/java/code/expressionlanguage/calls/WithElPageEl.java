package code.expressionlanguage.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.util.ParentStackBlock;

public interface WithElPageEl {

    void tryProcessEl(ContextEl _context);

    void postBlock(ContextEl _conf);

    ParentStackBlock getNextBlock(Block _block, ContextEl _conf);
}
