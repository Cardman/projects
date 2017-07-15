package code.expressionlanguage.methods.util;
import code.expressionlanguage.methods.BracedBlock;

public final class ParentStackBlock {

    private final BracedBlock element;

    public ParentStackBlock(BracedBlock _element) {
        element = _element;
    }

    public BracedBlock getElement() {
        return element;
    }
}
