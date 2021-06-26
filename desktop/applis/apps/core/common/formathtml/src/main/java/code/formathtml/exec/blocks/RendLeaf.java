package code.formathtml.exec.blocks;

public abstract class RendLeaf extends RendBlock implements RendWithEl {
    RendLeaf() {
        super();
    }

    @Override
    public RendBlock getFirstChild() {
        return null;
    }
}
