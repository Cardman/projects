package code.expressionlanguage.stacks;
import code.expressionlanguage.methods.BracedBlock;

public abstract class BlockStack {

    private BracedBlock block;

    public final BracedBlock getBlock() {
        return block;
    }

    public final void setBlock(BracedBlock _block) {
        block = _block;
    }
}
