package code.expressionlanguage.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.Block;
import code.expressionlanguage.analyze.blocks.ForMutableIterativeLoop;

public final class DefaultLoopDeclaring implements AbstractLoopDeclaring {
    private final ContextEl context;

    public DefaultLoopDeclaring(ContextEl _context) {
        context = _context;
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
        Block bl_ =  context.getAnalyzing().getCurrentBlock();
        return bl_ instanceof ForMutableIterativeLoop;
    }

    public void setupLoopClass(String _className) {
        Block bl_ =  context.getAnalyzing().getCurrentBlock();
        ((ForMutableIterativeLoop)bl_).setImportedClassName(_className);
    }
    @Override
    public String getIndexClassName() {
        return context.getIndexClassName();
    }
}
