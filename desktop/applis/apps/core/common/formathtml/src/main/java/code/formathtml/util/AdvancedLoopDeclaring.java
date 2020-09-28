package code.formathtml.util;

import code.expressionlanguage.types.AbstractLoopDeclaring;
import code.formathtml.analyze.blocks.AnaRendForMutableIterativeLoop;

public final class AdvancedLoopDeclaring implements AbstractLoopDeclaring {
    private final AnalyzingDoc configuration;

    public AdvancedLoopDeclaring(AnalyzingDoc _configuration) {
        configuration = _configuration;
    }

    @Override
    public boolean hasLoopDeclarator() {
        return configuration.getCurrentBlock() instanceof AnaRendForMutableIterativeLoop;
    }

    @Override
    public void setupLoopDeclaratorClass(String _className) {
        ((AnaRendForMutableIterativeLoop) configuration.getCurrentBlock()).setImportedClassName(_className);
    }

    @Override
    public String getIndexClassName() {
        return ((AnaRendForMutableIterativeLoop) configuration.getCurrentBlock()).getImportedClassIndexName();
    }
}
