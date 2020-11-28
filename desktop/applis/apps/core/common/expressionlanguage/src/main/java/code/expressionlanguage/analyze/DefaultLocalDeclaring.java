package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.blocks.DeclareVariable;

public final class DefaultLocalDeclaring implements AbstractLocalDeclaring {
    private final AnalyzedPageEl context;

    public DefaultLocalDeclaring(AnalyzedPageEl _context) {
        context = _context;
    }

    @Override
    public boolean hasDeclarator() {
        return context.getCurrentBlock().getPreviousSibling() instanceof DeclareVariable;
    }

    @Override
    public void setupDeclaratorClass(String _className) {
        ((DeclareVariable)context.getCurrentBlock().getPreviousSibling()).setImportedClassName(_className);
    }

}
