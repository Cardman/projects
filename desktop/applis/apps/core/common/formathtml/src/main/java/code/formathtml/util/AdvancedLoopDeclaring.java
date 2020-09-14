package code.formathtml.util;

import code.expressionlanguage.types.AbstractLoopDeclaring;
import code.formathtml.RendBlock;
import code.formathtml.RendForMutableIterativeLoop;

public final class AdvancedLoopDeclaring implements AbstractLoopDeclaring {
    private final AnalyzingDoc configuration;

    public AdvancedLoopDeclaring(AnalyzingDoc _configuration) {
        configuration = _configuration;
    }

    @Override
    public boolean hasLoopDeclarator() {
        RendBlock currentBlock_ = configuration.getCurrentBlock();
        return currentBlock_ instanceof RendForMutableIterativeLoop;
    }

    @Override
    public void setupLoopDeclaratorClass(String _className) {
        RendBlock currentBlock_ = configuration.getCurrentBlock();
        ((RendForMutableIterativeLoop)currentBlock_).setImportedClassName(_className);
    }

    @Override
    public String getIndexClassName() {
        RendBlock currentBlock_ = configuration.getCurrentBlock();
        return ((RendForMutableIterativeLoop)currentBlock_).getImportedClassIndexName();
    }
}
