package code.expressionlanguage.methods;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetsBlock;
import code.sml.Element;

public abstract class Leaf extends Block implements WithEl {

    Leaf(Element _el, ContextEl _importingPage, int _indexChild, BracedBlock _m) {
        super(_el, _indexChild, _m);
    }

    Leaf(ContextEl _importingPage, int _indexChild,
            BracedBlock _m, OffsetsBlock _offset) {
        super(_indexChild, _m, _offset);
    }

    @Override
    public void checkBlocksTree(ContextEl _cont) {
    }

    @Override
    public final Block getFirstChild() {
        return null;
    }

    @Override
    final boolean canBeIncrementedNextGroup() {
        return false;
    }

    @Override
    final boolean canBeIncrementedCurGroup() {
        return false;
    }
}
