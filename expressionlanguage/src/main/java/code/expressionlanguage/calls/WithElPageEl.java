package code.expressionlanguage.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.util.ParentStackBlock;

public interface WithElPageEl {

    void postBlock(ContextEl _conf);

    ParentStackBlock getNextBlock(Block _block);
}
