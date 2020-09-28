package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.TokenErrorMessage;

public interface AbstractTokenValidation {

    boolean isStaticAccess();
    TokenErrorMessage isValidSingleToken(String _id);

}
