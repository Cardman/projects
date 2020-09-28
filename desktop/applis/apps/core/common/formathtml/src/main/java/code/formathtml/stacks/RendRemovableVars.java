package code.formathtml.stacks;

import code.formathtml.exec.blocks.RendParentBlock;

public interface RendRemovableVars {

    RendParentBlock getBlock();
    RendParentBlock getCurrentVisitedBlock();
    void setCurrentVisitedBlock(RendParentBlock _bl);
    RendParentBlock getLastBlock();
}
