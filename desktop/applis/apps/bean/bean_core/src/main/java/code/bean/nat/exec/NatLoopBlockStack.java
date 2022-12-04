package code.bean.nat.exec;

import code.bean.nat.exec.blocks.NatParentBlock;

public class NatLoopBlockStack implements NatAbstractStask{

    private final NatLoopBlockStackContent content = new NatLoopBlockStackContent();
    private NatParentBlock natBlock;

    public void setBlock(NatParentBlock _block) {
        natBlock = _block;
    }

    @Override
    public NatParentBlock getCurrentVisitedBlock() {
        return natBlock;
    }

    @Override
    public void setCurrentVisitedBlock(NatParentBlock _bl) {
        natBlock = _bl;
    }

    public NatLoopBlockStackContent getContent() {
        return content;
    }
}
