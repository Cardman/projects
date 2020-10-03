package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.formathtml.analyze.AnalyzingDoc;
import code.util.StringList;

public final class AnaRendElseIfCondition extends AnaRendCondition implements AnaRendBreakableBlock {
    AnaRendElseIfCondition(OffsetStringInfo _condition, OffsetsBlock _offset) {
        super(_condition, _offset);
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        buildConditions(_anaDoc, _page);
        AnaRendBlock pBlock_ = getPreviousSibling();
        if (!(pBlock_ instanceof AnaRendIfCondition)) {
            if (!(pBlock_ instanceof AnaRendElseIfCondition)) {
                if (!(pBlock_ instanceof AnaRendPossibleEmpty)) {
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFileName(_anaDoc.getFileName());
                    un_.setIndexFile(getOffset().getOffsetTrim());
                    un_.buildError(_page.getAnalysisMessages().getUnexpectedCatchElseFinally(),
                            _page.getKeyWords().getKeyWordElseif(),
                            StringList.join(
                                    new StringList(
                                            _page.getKeyWords().getKeyWordIf(),
                                            _page.getKeyWords().getKeyWordElseif()
                                    ),
                                    OR_ERR));
                    AnalyzingDoc.addError(un_, _anaDoc, _page);
                } else if (!(pBlock_.getPreviousSibling() instanceof AnaRendIfCondition)){
                    if (!(pBlock_.getPreviousSibling() instanceof AnaRendElseIfCondition)){
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFileName(_anaDoc.getFileName());
                        un_.setIndexFile(getOffset().getOffsetTrim());
                        un_.buildError(_page.getAnalysisMessages().getUnexpectedCatchElseFinally(),
                                _page.getKeyWords().getKeyWordElseif(),
                                StringList.join(
                                        new StringList(
                                                _page.getKeyWords().getKeyWordIf(),
                                                _page.getKeyWords().getKeyWordElseif()
                                        ),
                                        OR_ERR));
                        AnalyzingDoc.addError(un_, _anaDoc, _page);
                    }
                }
            }
        }
    }

    public String getRealLabel() {
        AnaRendBlock p_ = getPreviousSibling();
        while (!(p_ instanceof AnaRendIfCondition)) {
            if (p_ == null) {
                return EMPTY_STRING;
            }
            p_ = p_.getPreviousSibling();
        }
        return ((AnaRendIfCondition)p_).getLabel();
    }

}
