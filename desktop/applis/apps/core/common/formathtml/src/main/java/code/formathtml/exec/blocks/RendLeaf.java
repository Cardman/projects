package code.formathtml.exec.blocks;

public abstract class RendLeaf extends RendBlock implements RendWithEl {

    @Override
    public RendBlock getFirstChild() {
        return null;
    }
}
