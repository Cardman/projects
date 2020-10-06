package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.stacks.RendLoopBlockStack;
import code.formathtml.util.BeanLgNames;

public interface RendLoop extends RendWithEl {

    void processLastElementLoop(Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx, RendLoopBlockStack _loopBlock);
}
