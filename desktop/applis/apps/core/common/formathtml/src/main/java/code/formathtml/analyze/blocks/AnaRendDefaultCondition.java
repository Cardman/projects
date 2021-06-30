package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenErrorMessage;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.common.ConstType;
import code.formathtml.analyze.AnalyzingDoc;

public final class AnaRendDefaultCondition extends AnaRendSwitchPartCondition {

    private final int variableOffset;
    AnaRendDefaultCondition(OffsetStringInfo _variableName, int _offset) {
        super(_offset);
        setVariableName(_variableName.getInfo());
        variableOffset = _variableName.getOffset();
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        AnaRendParentBlock b_ = getParent();
        if (!(b_ instanceof AnaRendSwitchBlock)) {
            _page.setGlobalOffset(getOffset());
            _page.zeroOffset();
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_anaDoc.getFileName());
            un_.setIndexFile(getOffset());
            un_.buildError(_page.getAnalysisMessages().getUnexpectedCaseDef(),
                    _page.getKeyWords().getKeyWordDefault(),
                    EMPTY_STRING,
                    _page.getKeyWords().getKeyWordSwitch());
            AnalyzingDoc.addError(un_, _anaDoc, _page);
        } else {
            AnaRendSwitchBlock s_ = (AnaRendSwitchBlock) b_;
            if (!s_.isInstance()) {
                AnaRendBlock first_ = b_.getFirstChild();
                while (first_ != this) {
                    if (first_ instanceof AnaRendDefaultCondition) {
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFileName(_anaDoc.getFileName());
                        un_.setIndexFile(getOffset());
                        //key word len
                        un_.buildError(_page.getAnalysisMessages().getUnexpectedDefDup(),
                                _page.getKeyWords().getKeyWordDefault(),
                                _page.getKeyWords().getKeyWordSwitch());
                        AnalyzingDoc.addError(un_, _anaDoc, _page);
                        break;
                    }
                    first_ = first_.getNextSibling();
                }
                return;
            }
            String instanceTest_ = s_.getInstanceTest();
            setImportedClassName(instanceTest_);
            if (getNextSibling() != null){
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(_anaDoc.getFileName());
                un_.setIndexFile(getOffset());
                //key word len
                un_.buildError(_page.getAnalysisMessages().getUnexpectedDefDup(),
                        _page.getKeyWords().getKeyWordDefault(),
                        _page.getKeyWords().getKeyWordSwitch());
                AnalyzingDoc.addError(un_, _anaDoc, _page);
            }
            TokenErrorMessage res_ = ManageTokens.partVar(_page).checkTokenVar(getVariableName(), _page);
            if (res_.isError()) {
                FoundErrorInterpret d_ = new FoundErrorInterpret();
                d_.setFileName(_anaDoc.getFileName());
                d_.setIndexFile(variableOffset);
                //variable name
                d_.setBuiltError(res_.getMessage());
                AnalyzingDoc.addError(d_, _anaDoc, _page);
                return;
            }
            AnaLocalVariable lv_ = new AnaLocalVariable();
            lv_.setClassName(instanceTest_);
            lv_.setConstType(ConstType.FIX_VAR);
            _page.getInfosVars().put(getVariableName(), lv_);
        }
    }

    @Override
    public void removeAllVars(AnalyzedPageEl _ip) {
        super.removeAllVars(_ip);
        if (!getVariableName().isEmpty()) {
            _ip.getInfosVars().removeKey(getVariableName());
        }
    }
}
