package code.formathtml.util;

import code.expressionlanguage.types.AbstractLocalDeclaring;
import code.formathtml.RendBlock;
import code.formathtml.RendDeclareVariable;

public final class AdvancedLocalDeclaring implements AbstractLocalDeclaring {
    private final AnalyzingDoc configuration;

    public AdvancedLocalDeclaring(AnalyzingDoc _configuration) {
        configuration = _configuration;
    }

    @Override
    public boolean hasDeclarator() {
        RendBlock currentBlock_ = configuration.getCurrentBlock();
        return currentBlock_.getPreviousSibling() instanceof RendDeclareVariable;
    }

    @Override
    public void setupDeclaratorClass(String _className) {
        RendBlock currentBlock_ = configuration.getCurrentBlock();
        RendBlock previousSibling_ = currentBlock_.getPreviousSibling();
        ((RendDeclareVariable)previousSibling_).setImportedClassName(_className);
    }
}
