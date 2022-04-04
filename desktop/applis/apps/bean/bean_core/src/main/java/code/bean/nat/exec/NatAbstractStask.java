package code.bean.nat.exec;

import code.bean.nat.exec.blocks.NatParentBlock;

public interface NatAbstractStask {

    NatParentBlock getCurrentVisitedBlock();
    void setCurrentVisitedBlock(NatParentBlock _bl);
}
