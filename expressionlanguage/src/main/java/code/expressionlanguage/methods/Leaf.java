package code.expressionlanguage.methods;
import code.expressionlanguage.ContextEl;
import code.sml.Element;

public abstract class Leaf extends Block implements WithEl {

    Leaf(Element _el, ContextEl _importingPage, int _indexChild, BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
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
