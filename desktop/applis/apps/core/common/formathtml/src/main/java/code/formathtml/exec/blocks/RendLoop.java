package code.formathtml.exec.blocks;

import code.formathtml.Configuration;

public interface RendLoop extends RendBreakableBlock, RendWithEl {

    void processLastElementLoop(Configuration _conf);
}
