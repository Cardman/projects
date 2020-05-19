package code.expressionlanguage.types;

import code.expressionlanguage.ContextEl;

public final class DefaultLocalDeclaring implements AbstractLocalDeclaring {
    private final ContextEl context;

    public DefaultLocalDeclaring(ContextEl _context) {
        context = _context;
    }

    @Override
    public boolean hasDeclarator() {
        return context.hasDeclarator();
    }

    @Override
    public void setupDeclaratorClass(String _className) {
        context.setupDeclaratorClass(_className);
    }
}
