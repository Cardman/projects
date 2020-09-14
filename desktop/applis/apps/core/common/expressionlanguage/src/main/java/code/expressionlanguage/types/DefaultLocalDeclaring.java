package code.expressionlanguage.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.Block;
import code.expressionlanguage.analyze.blocks.DeclareVariable;

public final class DefaultLocalDeclaring implements AbstractLocalDeclaring {
    private final AnalyzedPageEl context;

    public DefaultLocalDeclaring(AnalyzedPageEl _context) {
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
        Block bl_ = context.getCurrentBlock();
        return bl_.getPreviousSibling() instanceof DeclareVariable;
    }

    public void setupDeclClass(String _className) {
        Block bl_ = context.getCurrentBlock();
        Block previousSibling_ = bl_.getPreviousSibling();
        ((DeclareVariable)previousSibling_).setImportedClassName(_className);
    }
}
