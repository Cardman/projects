package code.bean.nat.exec;

import code.bean.nat.exec.blocks.NatParentBlock;
import code.expressionlanguage.exec.stacks.LoopBlockStackContent;

public class NatLoopBlockStack implements NatAbstractStask{

    private final LoopBlockStackContent content = new LoopBlockStackContent();
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

    public LoopBlockStackContent getContent() {
        return content;
    }
}
