package code.expressionlanguage.types;

import code.expressionlanguage.ContextEl;

public final class DefaultTokenValidation implements AbstractTokenValidation {
    private final ContextEl context;

    public DefaultTokenValidation(ContextEl context) {
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
