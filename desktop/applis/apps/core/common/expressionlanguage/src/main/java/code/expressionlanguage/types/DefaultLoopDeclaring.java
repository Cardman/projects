package code.expressionlanguage.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.Block;
import code.expressionlanguage.analyze.blocks.ForMutableIterativeLoop;

public final class DefaultLoopDeclaring implements AbstractLoopDeclaring {
    private final AnalyzedPageEl page;

    public DefaultLoopDeclaring(AnalyzedPageEl _page) {
        page = _page;
    }

    @Override
    public boolean hasLoopDeclarator() {
        return hasLoop();
    }

    @Override
    public void setupLoopDeclaratorClass(String _className) {
        setupLoopClass(_className);
    }

    public boolean hasLoop() {
        Block bl_ =  page.getCurrentBlock();
        return bl_ instanceof ForMutableIterativeLoop;
    }

    public void setupLoopClass(String _className) {
        Block bl_ =  page.getCurrentBlock();
        ((ForMutableIterativeLoop)bl_).setImportedClassName(_className);
    }
    @Override
    public String getIndexClassName() {
        return ((ForMutableIterativeLoop)page.getCurrentBlock()).getImportedClassIndexName();
    }
}
