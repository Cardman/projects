package code.expressionlanguage.methods;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.files.OffsetsBlock;

public final class StaticBlock extends InitBlock {

    public StaticBlock(ContextEl _importingPage, BracedBlock _m, OffsetsBlock _offset) {
        super(_importingPage, _m, _offset);
    }

    @Override
    public boolean isStaticContext() {
        return true;
    }
}
