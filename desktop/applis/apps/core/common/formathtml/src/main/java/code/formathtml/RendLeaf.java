package code.formathtml;

import code.expressionlanguage.files.OffsetsBlock;

public abstract class RendLeaf extends RendBlock implements RendWithEl {
    RendLeaf(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public RendBlock getFirstChild() {
        return null;
    }
}
