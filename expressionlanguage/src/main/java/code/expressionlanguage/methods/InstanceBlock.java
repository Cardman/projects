package code.expressionlanguage.methods;
import code.expressionlanguage.files.OffsetsBlock;

public final class InstanceBlock extends InitBlock {

    public InstanceBlock(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public boolean isStaticContext() {
        return false;
    }
}
