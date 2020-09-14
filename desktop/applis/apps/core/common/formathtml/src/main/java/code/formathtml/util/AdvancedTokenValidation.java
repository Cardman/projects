package code.formathtml.util;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.types.AbstractTokenValidation;

public final class AdvancedTokenValidation implements AbstractTokenValidation {
    private final AnalyzedPageEl context;

    public AdvancedTokenValidation(AnalyzedPageEl context) {
        this.context = context;
    }

    @Override
    public boolean isStaticAccess() {
        return context.isStaticContext();
    }

    @Override
    public TokenErrorMessage isValidSingleToken(String _id) {
        return ManageTokens.partVar(context).checkTokenVar(_id, context);
    }

}
