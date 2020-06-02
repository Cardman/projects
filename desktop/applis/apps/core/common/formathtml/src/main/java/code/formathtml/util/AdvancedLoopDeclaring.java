package code.formathtml.util;

import code.expressionlanguage.types.AbstractLoopDeclaring;
import code.formathtml.Configuration;

public final class AdvancedLoopDeclaring implements AbstractLoopDeclaring {
    private final Configuration configuration;

    public AdvancedLoopDeclaring(Configuration _configuration) {
        configuration = _configuration;
    }

    @Override
    public boolean hasLoopDeclarator() {
        return configuration.hasLoopDeclarator();
    }

    @Override
    public void setupLoopDeclaratorClass(String _className) {
        configuration.setupLoopDeclaratorClass(_className);
    }

    @Override
    public String getIndexClassName() {
        return configuration.getIndexClassName();
    }
}
