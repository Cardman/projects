package code.formathtml.analyze;

import code.expressionlanguage.types.AbstractLocalDeclaring;
import code.formathtml.analyze.blocks.AnaRendBlock;
import code.formathtml.analyze.blocks.AnaRendDeclareVariable;

public final class AdvancedLocalDeclaring implements AbstractLocalDeclaring {
    private final AnalyzingDoc configuration;

    public AdvancedLocalDeclaring(AnalyzingDoc _configuration) {
        configuration = _configuration;
    }

    @Override
    public boolean hasDeclarator() {
        AnaRendBlock currentBl_ = configuration.getCurrentBlock();
        return currentBl_.getPreviousSibling() instanceof AnaRendDeclareVariable;
    }

    @Override
    public void setupDeclaratorClass(String _className) {
        AnaRendBlock currentBl_ = configuration.getCurrentBlock();
        AnaRendBlock previousSibling_ = currentBl_.getPreviousSibling();
        ((AnaRendDeclareVariable)previousSibling_).setImportedClassName(_className);
    }
}
