package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.common.ConstType;
import code.formathtml.analyze.AnalyzingDoc;
import code.util.StringList;
import code.util.core.StringUtil;

public final class AnaRendCatchEval extends AnaRendAbstractCatchEval {

    private final String className;

    private String importedClassName;

    private final int classNameOffset;

    private final String variableName;

    private final int variableNameOffset;
    AnaRendCatchEval(OffsetStringInfo _className, OffsetStringInfo _variable, int _offset) {
        super(_offset);
        className = _className.getInfo();
        classNameOffset = _className.getOffset();
        variableName = _variable.getInfo();
        variableNameOffset = _variable.getOffset();
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        _page.setGlobalOffset(variableNameOffset);
        _page.zeroOffset();
        TokenErrorMessage res_ = ManageTokens.partVar(_page).checkTokenVar(variableName, _page);
        if (res_.isError()) {
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFile(_page.getCurrentFile());
            b_.setIndexFile(variableNameOffset);
            b_.setBuiltError(res_.getMessage());
            AnalyzingDoc.addError(b_, _page);
            return;
        }
        _page.setGlobalOffset(classNameOffset);
        importedClassName = ResolvingTypes.resolveCorrectType(className, _page).getResult(_page);
        AnaLocalVariable lv_ = new AnaLocalVariable();
        lv_.setClassName(importedClassName);
        lv_.setConstType(ConstType.FIX_VAR);
        _page.getInfosVars().put(variableName, lv_);
        AnaRendBlock pBlock_ = getPreviousSibling();
        if (!(pBlock_ instanceof AnaRendAbstractCatchEval)) {
            if (!(pBlock_ instanceof AnaRendTryEval)) {
                if (!isPossibleEmpty(pBlock_)) {
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFile(_page.getCurrentFile());
                    un_.setIndexFile(getOffset());
                    un_.buildError(_page.getAnalysisMessages().getUnexpectedCatchElseFinally(),
                            _page.getKeyWords().getKeyWordCatch(),
                            StringUtil.join(
                                    new StringList(
                                            _page.getKeyWords().getKeyWordCatch(),
                                            _page.getKeyWords().getKeyWordTry()
                                    ),
                                    OR_ERR));
                    AnalyzingDoc.addError(un_, _page);
                } else if (!(pBlock_.getPreviousSibling() instanceof AnaRendAbstractCatchEval)) {
                    if (!(pBlock_.getPreviousSibling() instanceof AnaRendTryEval)) {
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFile(_page.getCurrentFile());
                        un_.setIndexFile(getOffset());
                        un_.buildError(_page.getAnalysisMessages().getUnexpectedCatchElseFinally(),
                                _page.getKeyWords().getKeyWordCatch(),
                                StringUtil.join(
                                        new StringList(
                                                _page.getKeyWords().getKeyWordCatch(),
                                                _page.getKeyWords().getKeyWordTry()
                                        ),
                                        OR_ERR));
                        AnalyzingDoc.addError(un_, _page);
                    }
                }
            }
        }
    }

    @Override
    public void removeAllVars(AnalyzedPageEl _ip) {
        super.removeAllVars(_ip);
        String var_ = getVariableName();
        _ip.getInfosVars().removeKey(var_);
    }

    public String getVariableName() {
        return variableName;
    }

    public String getImportedClassName() {
        return importedClassName;
    }
}
