package code.formathtml.stacks;
import code.formathtml.exec.blocks.RendParentBlock;

public final class RendParentElement {

    private final RendParentBlock element;

    public RendParentElement(RendParentBlock _element) {
        element = _element;
    }

    public RendParentBlock getElement() {
        return element;
    }
}
