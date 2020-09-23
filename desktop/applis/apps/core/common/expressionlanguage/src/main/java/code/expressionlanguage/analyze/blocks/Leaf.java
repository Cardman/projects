package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.files.OffsetsBlock;

public abstract class Leaf extends Block {

    Leaf(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public final Block getFirstChild() {
        return null;
    }

    @Override
    public void checkTree(AnalyzingEl _anEl, AnalyzedPageEl _page) {
    }
}
