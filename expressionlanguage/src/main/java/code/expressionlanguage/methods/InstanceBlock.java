package code.expressionlanguage.methods;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetsBlock;

public final class InstanceBlock extends InitBlock {

    public InstanceBlock(ContextEl _importingPage, int _indexChild,
            BracedBlock _m, OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _offset);
    }

    @Override
    boolean canBeIncrementedNextGroup() {
        return false;
    }

    @Override
    boolean canBeIncrementedCurGroup() {
        return false;
    }

    @Override
    boolean canBeLastOfBlockGroup() {
        return false;
    }

    @Override
    public boolean isStaticContext() {
        return false;
    }
}
