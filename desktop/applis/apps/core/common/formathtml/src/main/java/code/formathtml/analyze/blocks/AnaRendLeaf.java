package code.formathtml.analyze.blocks;

public abstract class AnaRendLeaf extends AnaRendBlock {
    AnaRendLeaf(int _offset) {
        super(_offset);
    }

    @Override
    public final AnaRendBlock getFirstChild() {
        return null;
    }
}
