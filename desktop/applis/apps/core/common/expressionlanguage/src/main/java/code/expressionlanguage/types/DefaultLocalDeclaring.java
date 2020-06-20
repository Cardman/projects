package code.expressionlanguage.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.Block;
import code.expressionlanguage.analyze.blocks.DeclareVariable;

public final class DefaultLocalDeclaring implements AbstractLocalDeclaring {
    private final ContextEl context;

    public DefaultLocalDeclaring(ContextEl _context) {
        context = _context;
    }

    @Override
    public boolean hasDeclarator() {
        return hasDecl();
    }

    @Override
    public void setupDeclaratorClass(String _className) {
        setupDeclClass(_className);
    }

    public boolean hasDecl() {
        Block bl_ = context.getAnalyzing().getCurrentBlock();
        return bl_.getPreviousSibling() instanceof DeclareVariable;
    }

    public void setupDeclClass(String _className) {
        Block bl_ = context.getAnalyzing().getCurrentBlock();
        Block previousSibling_ = bl_.getPreviousSibling();
        ((DeclareVariable)previousSibling_).setImportedClassName(_className);
    }
}
