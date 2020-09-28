package code.formathtml;

public abstract class RendLeaf extends RendBlock implements RendWithEl {
    RendLeaf(int _offsetTrim) {
        super(_offsetTrim);
    }

    @Override
    public RendBlock getFirstChild() {
        return null;
    }
}
