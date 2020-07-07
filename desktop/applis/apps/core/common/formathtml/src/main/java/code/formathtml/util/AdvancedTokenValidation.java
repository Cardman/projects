package code.formathtml.util;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
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
    public TokenErrorMessage isValidSingleToken(String _id) {
        ContextEl ctx_ = this.context.getContext();
        return ManageTokens.partVar(ctx_).checkTokenVar(ctx_,_id,false);
    }

}
