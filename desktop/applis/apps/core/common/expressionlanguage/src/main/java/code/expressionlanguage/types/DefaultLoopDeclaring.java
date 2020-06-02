package code.expressionlanguage.types;

import code.expressionlanguage.ContextEl;

public final class DefaultLoopDeclaring implements AbstractLoopDeclaring {
    private final ContextEl context;

    public DefaultLoopDeclaring(ContextEl _context) {
        context = _context;
    }

    @Override
    public boolean hasLoopDeclarator() {
        return context.hasLoopDeclarator();
    }

    @Override
    public void setupLoopDeclaratorClass(String _className) {
        context.setupLoopDeclaratorClass(_className);
    }

    @Override
    public String getIndexClassName() {
        return context.getIndexClassName();
    }
}
