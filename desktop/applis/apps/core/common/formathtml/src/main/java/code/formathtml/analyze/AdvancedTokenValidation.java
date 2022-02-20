package code.formathtml.analyze;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.AbstractTokenValidation;

public final class AdvancedTokenValidation implements AbstractTokenValidation {
    private final AnalyzedPageEl context;

    public AdvancedTokenValidation(AnalyzedPageEl _context) {
        this.context = _context;
    }

    @Override
    public boolean isStaticAccess() {
        return context.isStaticContext();
    }

}
