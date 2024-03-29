package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.formathtml.analyze.AnalyzingDoc;
import code.sml.NavigationCore;
import code.util.StringList;
import code.util.core.StringUtil;

public final class AnaRendElseIfCondition extends AnaRendCondition implements AnaRendBreakableBlock {
    AnaRendElseIfCondition(OffsetStringInfo _condition, int _offset) {
        super(_condition, _offset);
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        buildConditions(_anaDoc, _page);
        AnaRendBlock pBlock_ = getPreviousSibling();
        if (!(pBlock_ instanceof AnaRendIfCondition) && !(pBlock_ instanceof AnaRendElseIfCondition) && (!isPossibleEmpty(pBlock_) || !(pBlock_.getPreviousSibling() instanceof AnaRendIfCondition) && !(pBlock_.getPreviousSibling() instanceof AnaRendElseIfCondition))) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(_page.getCurrentFile());
            un_.setIndexFile(getOffset());
            un_.buildError(_page.getAnalysisMessages().getUnexpectedCatchElseFinally(),
                    _page.getKeyWords().getKeyWordElseif(),
                    StringUtil.join(
                            new StringList(
                                    _page.getKeyWords().getKeyWordIf(),
                                    _page.getKeyWords().getKeyWordElseif()
                            ),
                            OR_ERR));
            AnalyzingDoc.addError(un_, _page);
        }
    }

    public String getRealLabel() {
        AnaRendBlock p_ = getPreviousSibling();
        while (!(p_ instanceof AnaRendIfCondition)) {
            if (p_ == null) {
                return NavigationCore.EMPTY_STRING;
            }
            p_ = p_.getPreviousSibling();
        }
        return ((AnaRendIfCondition)p_).getLabel();
    }

}
