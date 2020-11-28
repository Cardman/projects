package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.blocks.ForMutableIterativeLoop;

public final class DefaultLoopDeclaring implements AbstractLoopDeclaring {
    private final AnalyzedPageEl page;

    public DefaultLoopDeclaring(AnalyzedPageEl _page) {
        page = _page;
    }

    @Override
    public boolean hasLoopDeclarator() {
        return page.getCurrentBlock() instanceof ForMutableIterativeLoop;
    }

    @Override
    public void setupLoopDeclaratorClass(String _className) {
        ((ForMutableIterativeLoop)page.getCurrentBlock()).setImportedClassName(_className);
    }

    @Override
    public String getIndexClassName() {
        return ((ForMutableIterativeLoop)page.getCurrentBlock()).getImportedClassIndexName();
    }
}
