package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.files.OffsetsBlock;

public abstract class AnaRendLeaf extends AnaRendBlock {
    AnaRendLeaf(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public final AnaRendBlock getFirstChild() {
        return null;
    }
}
