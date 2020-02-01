package code.formathtml.stacks;

import code.formathtml.RendParentBlock;

public interface RendRemovableVars {

    RendParentBlock getBlock();
    RendParentBlock getCurrentVisitedBlock();
    RendParentBlock getLastBlock();
}
