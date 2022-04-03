package code.bean.nat.exec;

import code.formathtml.exec.blocks.RendParentBlock;

public interface NatAbstractStask {

    RendParentBlock getCurrentVisitedBlock();
    void setCurrentVisitedBlock(RendParentBlock _bl);
}
