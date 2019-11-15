package code.expressionlanguage.methods;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.opers.util.MethodAccessKind;

public final class StaticBlock extends InitBlock {

    public StaticBlock(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public MethodAccessKind getStaticContext() {
        return MethodAccessKind.STATIC;
    }
}
