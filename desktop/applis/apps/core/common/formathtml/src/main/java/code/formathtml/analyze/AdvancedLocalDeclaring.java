package code.formathtml.analyze;

import code.expressionlanguage.analyze.AbstractLocalDeclaring;
import code.formathtml.analyze.blocks.AnaRendDeclareVariable;

public final class AdvancedLocalDeclaring implements AbstractLocalDeclaring {
    private final AnalyzingDoc configuration;

    public AdvancedLocalDeclaring(AnalyzingDoc _configuration) {
        configuration = _configuration;
    }

    @Override
    public boolean hasDeclarator() {
        return configuration.getCurrentBlock().getPreviousSibling() instanceof AnaRendDeclareVariable;
    }

    @Override
    public void setupDeclaratorClass(String _className) {
        ((AnaRendDeclareVariable)configuration.getCurrentBlock().getPreviousSibling()).setImportedClassName(_className);
    }
}
