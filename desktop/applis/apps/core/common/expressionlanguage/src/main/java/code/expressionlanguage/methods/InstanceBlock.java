package code.expressionlanguage.methods;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.opers.util.MethodAccessKind;

public final class InstanceBlock extends InitBlock {

    public InstanceBlock(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public MethodAccessKind getStaticContext() {
        return MethodAccessKind.INSTANCE;
    }
}
