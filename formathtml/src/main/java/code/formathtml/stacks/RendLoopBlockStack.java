package code.formathtml.stacks;
import code.expressionlanguage.stacks.LoopStack;
import code.formathtml.RendParentBlock;


public final class RendLoopBlockStack extends LoopStack implements RendRemovableVars {

    private RendParentBlock block;

    @Override
    public RendParentBlock getBlock() {
        return block;
    }

    public void setBlock(RendParentBlock _block) {
        block = _block;
    }

    @Override
    public RendParentBlock getLastBlock() {
        return block;
    }

    @Override
    public RendParentBlock getCurrentVisitedBlock() {
        return block;
    }

    @Override
    public void setCurrentVisitedBlock(RendParentBlock _bl) {
        block = _bl;
    }
}
