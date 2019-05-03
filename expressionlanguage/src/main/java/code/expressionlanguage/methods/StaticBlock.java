package code.expressionlanguage.methods;
import code.expressionlanguage.files.OffsetsBlock;

public final class StaticBlock extends InitBlock {

    public StaticBlock(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public boolean isStaticContext() {
        return true;
    }
}
