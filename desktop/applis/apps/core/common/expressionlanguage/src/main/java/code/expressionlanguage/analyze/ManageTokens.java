package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.util.core.StringUtil;

public final class ManageTokens {
    private final String badCharactersMessage;
    private final String keyWordMessage;
    private final String primMessage;
    private final String digitMessage;

    private ManageTokens(String badCharactersMessage, String keyWordMessage, String primMessage, String digitMessage) {
        this.badCharactersMessage = badCharactersMessage;
        this.keyWordMessage = keyWordMessage;
        this.primMessage = primMessage;
        this.digitMessage = digitMessage;
    }

    public static ManageTokens partClass(AnalyzedPageEl _analyzing) {
        AnalysisMessages ana_ = _analyzing.getAnalysisMessages();
        return new ManageTokens(ana_.getBadPartClassName(),ana_.getKeyWordPartClassName(),
                ana_.getPrimPartClassName(),ana_.getDigitPartClassName());
    }

    public static ManageTokens partVarClass(AnalyzedPageEl _analyzing) {
        AnalysisMessages ana_ = _analyzing.getAnalysisMessages();
        return new ManageTokens(ana_.getBadPartVarClassName(),ana_.getKeyWordPartVarClassName(),
                ana_.getPrimPartVarClassName(),ana_.getDigitPartVarClassName());
    }

    public static ManageTokens partField(AnalyzedPageEl _analyzing) {
        AnalysisMessages ana_ = _analyzing.getAnalysisMessages();
        return new ManageTokens(ana_.getBadFieldName(),ana_.getKeyWordFieldName(),
                ana_.getPrimFieldName(),ana_.getDigitFieldName());
    }

    public static ManageTokens partMethod(AnalyzedPageEl _analyzing) {
        AnalysisMessages ana_ = _analyzing.getAnalysisMessages();
        return new ManageTokens(ana_.getBadMethodName(),ana_.getKeyWordMethodName(),
                ana_.getPrimMethodName(),ana_.getDigitMethodName());
    }

    public static ManageTokens partParam(AnalyzedPageEl _analyzing) {
        AnalysisMessages ana_ = _analyzing.getAnalysisMessages();
        return new ManageTokens(ana_.getBadParamName(),ana_.getKeyWordParamName(),
                ana_.getPrimParamName(),ana_.getDigitParamName());
    }

    public static ManageTokens partVar(AnalyzedPageEl _analyzing) {
        AnalysisMessages ana_ = _analyzing.getAnalysisMessages();
        return new ManageTokens(ana_.getBadVariableName(),ana_.getKeyWordVariableName(),
                ana_.getPrimVariableName(),ana_.getDigitVariableName());
    }

    public TokenErrorMessage checkTokenVar(String _id, AnalyzedPageEl _analyzing) {
        return checkToken(_id, _analyzing.getAnalysisMessages().getDuplicatedVariableName(), _analyzing);
    }

    private TokenErrorMessage checkToken(String _id, String _duplicateMessage, AnalyzedPageEl _analyzing) {
        TokenErrorMessage err_ = checkToken(_id, _analyzing);
        if (err_.isError()) {
            return err_;
        }
        if (!ContextUtil.idDisjointToken(_id, _analyzing)){
            return new TokenErrorMessage(FoundErrorInterpret.buildARError(_duplicateMessage,_id),true);
        }
        return new TokenErrorMessage("",false);
    }

    public TokenErrorMessage checkToken(String _id, AnalyzedPageEl _analyzing) {
        if (!StringUtil.isDollarWord(_id)) {
            return new TokenErrorMessage(FoundErrorInterpret.buildARError(badCharactersMessage,_id),true);
        }
        if (AnaTypeUtil.isPrimitive(_id, _analyzing)) {
            return new TokenErrorMessage(FoundErrorInterpret.buildARError(primMessage,_id),true);
        }
        if (_analyzing.getKeyWords().isKeyWordNotVar(_id)) {
            return new TokenErrorMessage(FoundErrorInterpret.buildARError(keyWordMessage,_id),true);
        }
        if (StringUtil.quickEq(_id, _analyzing.getAliasVoid())) {
            return new TokenErrorMessage(FoundErrorInterpret.buildARError(keyWordMessage,_id),true);
        }
        if (StringExpUtil.isDigit(_id.charAt(0))) {
            return new TokenErrorMessage(FoundErrorInterpret.buildARError(digitMessage,_id),true);
        }
        return new TokenErrorMessage("",false);
    }
}
