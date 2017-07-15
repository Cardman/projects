package code.expressionlanguage.stacks;
import code.expressionlanguage.methods.BracedBlock;

public abstract class BlockStack {

    private BracedBlock block;

//    private Element readNode;

//    private Element writeNode;

    public final BracedBlock getBlock() {
        return block;
    }

    public final void setBlock(BracedBlock _block) {
        block = _block;
    }

//    public final Element getAssociatedElement() {
//        return block.getAssociateElement();
//    }
}
