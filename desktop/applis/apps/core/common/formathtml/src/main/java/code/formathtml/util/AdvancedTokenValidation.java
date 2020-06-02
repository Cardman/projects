package code.formathtml.util;

import code.expressionlanguage.types.AbstractTokenValidation;
import code.formathtml.Configuration;

public final class AdvancedTokenValidation implements AbstractTokenValidation {
    private final Configuration context;

    public AdvancedTokenValidation(Configuration context) {
        this.context = context;
    }

    @Override
    public boolean isStaticAccess() {
        return context.isStaticAccess();
    }

    @Override
    public boolean isValidSingleToken(String _id) {
        return context.isValidSingleToken(_id);
    }

}
