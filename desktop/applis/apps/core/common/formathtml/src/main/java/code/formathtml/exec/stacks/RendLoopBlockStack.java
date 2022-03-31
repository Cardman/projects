package code.formathtml.exec.stacks;
import code.expressionlanguage.exec.stacks.LoopBlockStackContent;
import code.formathtml.exec.blocks.RendParentBlock;


public final class RendLoopBlockStack extends RendAbstractStask {

    private final LoopBlockStackContent content = new LoopBlockStackContent();
    private RendParentBlock block;

    public void setBlock(RendParentBlock _block) {
        block = _block;
    }

    @Override
    public RendParentBlock getCurrentVisitedBlock() {
        return block;
    }

    @Override
    public void setCurrentVisitedBlock(RendParentBlock _bl) {
        block = _bl;
    }

    public LoopBlockStackContent getContent() {
        return content;
    }

}
