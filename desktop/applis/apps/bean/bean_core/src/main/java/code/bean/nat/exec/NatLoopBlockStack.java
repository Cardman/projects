package code.bean.nat.exec;

import code.expressionlanguage.exec.stacks.LoopBlockStackContent;
import code.formathtml.exec.blocks.RendParentBlock;

public class NatLoopBlockStack implements NatAbstractStask{

    private final LoopBlockStackContent content = new LoopBlockStackContent();
    private RendParentBlock natBlock;

    public void setBlock(RendParentBlock _block) {
        natBlock = _block;
    }

    @Override
    public RendParentBlock getCurrentVisitedBlock() {
        return natBlock;
    }

    @Override
    public void setCurrentVisitedBlock(RendParentBlock _bl) {
        natBlock = _bl;
    }

    public LoopBlockStackContent getContent() {
        return content;
    }
}
