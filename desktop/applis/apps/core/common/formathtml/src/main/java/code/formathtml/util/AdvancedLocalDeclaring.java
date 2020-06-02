package code.formathtml.util;

import code.expressionlanguage.types.AbstractLocalDeclaring;
import code.expressionlanguage.types.AbstractLoopDeclaring;
import code.formathtml.Configuration;

public final class AdvancedLocalDeclaring implements AbstractLocalDeclaring {
    private final Configuration configuration;

    public AdvancedLocalDeclaring(Configuration _configuration) {
        configuration = _configuration;
    }

    @Override
    public boolean hasDeclarator() {
        return configuration.hasDeclarator();
    }

    @Override
    public void setupDeclaratorClass(String _className) {
        configuration.setupDeclaratorClass(_className);
    }
}
