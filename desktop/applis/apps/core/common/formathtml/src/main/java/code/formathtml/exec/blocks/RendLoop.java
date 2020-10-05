package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.util.BeanLgNames;

public interface RendLoop extends RendBreakableBlock, RendWithEl {

    void processLastElementLoop(Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx);
}
