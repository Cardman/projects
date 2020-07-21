package code.expressionlanguage.analyze;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.AnalysisMessages;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.util.StringList;

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

    public static ManageTokens partClass(ContextEl _cont) {
        AnalysisMessages ana_ = _cont.getAnalysisMessages();
        return new ManageTokens(ana_.getBadPartClassName(),ana_.getKeyWordPartClassName(),
                ana_.getPrimPartClassName(),ana_.getDigitPartClassName());
    }

    public static ManageTokens partVarClass(ContextEl _cont) {
        AnalysisMessages ana_ = _cont.getAnalysisMessages();
        return new ManageTokens(ana_.getBadPartVarClassName(),ana_.getKeyWordPartVarClassName(),
                ana_.getPrimPartVarClassName(),ana_.getDigitPartVarClassName());
    }

    public static ManageTokens partField(ContextEl _cont) {
        AnalysisMessages ana_ = _cont.getAnalysisMessages();
        return new ManageTokens(ana_.getBadFieldName(),ana_.getKeyWordFieldName(),
                ana_.getPrimFieldName(),ana_.getDigitFieldName());
    }

    public static ManageTokens partMethod(ContextEl _cont) {
        AnalysisMessages ana_ = _cont.getAnalysisMessages();
        return new ManageTokens(ana_.getBadMethodName(),ana_.getKeyWordMethodName(),
                ana_.getPrimMethodName(),ana_.getDigitMethodName());
    }

    public static ManageTokens partParam(ContextEl _cont) {
        AnalysisMessages ana_ = _cont.getAnalysisMessages();
        return new ManageTokens(ana_.getBadParamName(),ana_.getKeyWordParamName(),
                ana_.getPrimParamName(),ana_.getDigitParamName());
    }

    public static ManageTokens partVar(ContextEl _cont) {
        AnalysisMessages ana_ = _cont.getAnalysisMessages();
        return new ManageTokens(ana_.getBadVariableName(),ana_.getKeyWordVariableName(),
                ana_.getPrimVariableName(),ana_.getDigitVariableName());
    }

    public TokenErrorMessage checkTokenVar(ContextEl _cont, String _id) {
        return checkToken(_cont,_id, _cont.getAnalysisMessages().getDuplicatedVariableName());
    }

    private TokenErrorMessage checkToken(ContextEl _cont, String _id, String _duplicateMessage) {
        TokenErrorMessage err_ = checkToken(_cont, _id);
        if (err_.isError()) {
            return err_;
        }
        if (!ContextUtil.idDisjointToken(_cont,_id)){
            return new TokenErrorMessage(FoundErrorInterpret.buildARError(_duplicateMessage,_id),true);
        }
        return new TokenErrorMessage("",false);
    }

    public TokenErrorMessage checkToken(ContextEl _cont, String _id) {
        if (!StringList.isDollarWord(_id)) {
            return new TokenErrorMessage(FoundErrorInterpret.buildARError(badCharactersMessage,_id),true);
        }
        if (PrimitiveTypeUtil.isPrimitive(_id, _cont)) {
            return new TokenErrorMessage(FoundErrorInterpret.buildARError(primMessage,_id),true);
        }
        if (_cont.getKeyWords().isKeyWordNotVar(_id)) {
            return new TokenErrorMessage(FoundErrorInterpret.buildARError(keyWordMessage,_id),true);
        }
        if (StringList.quickEq(_id, _cont.getStandards().getAliasVoid())) {
            return new TokenErrorMessage(FoundErrorInterpret.buildARError(keyWordMessage,_id),true);
        }
        if (StringExpUtil.isDigit(_id.charAt(0))) {
            return new TokenErrorMessage(FoundErrorInterpret.buildARError(digitMessage,_id),true);
        }
        return new TokenErrorMessage("",false);
    }
}
