package code.formathtml.exec.stacks;

import code.formathtml.exec.blocks.RendParentBlock;

public interface RendRemovableVars {

    RendParentBlock getCurrentVisitedBlock();
    void setCurrentVisitedBlock(RendParentBlock _bl);
    String getLabel();
}
