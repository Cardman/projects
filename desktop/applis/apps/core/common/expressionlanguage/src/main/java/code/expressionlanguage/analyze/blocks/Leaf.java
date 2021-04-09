package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnalyzedPageEl;

public abstract class Leaf extends AbsBk {

    Leaf(int _offset) {
        super(_offset);
    }

    @Override
    public final AbsBk getFirstChild() {
        return null;
    }

    @Override
    public void checkTree(AnalyzingEl _anEl, AnalyzedPageEl _page) {
    }
}
