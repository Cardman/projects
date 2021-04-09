package code.formathtml.analyze.blocks;


import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.formathtml.analyze.AnalyzingDoc;

public final class AnaRendDoBlock extends AnaRendParentBlock implements AnaRendLoop {

    private final String label;
    private final int labelOffset;
    AnaRendDoBlock(OffsetStringInfo _label, int _offset) {
        super(_offset);
        label = _label.getInfo();
        labelOffset = _label.getOffset();
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        AnaRendBlock pBlock_ = getNextSibling();
        if (pBlock_ == null) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_anaDoc.getFileName());
            un_.setIndexFile(getOffset());
            un_.buildError(_page.getAnalysisMessages().getUnexpectedDoTry(),
                    _page.getKeyWords().getKeyWordDo(),
                    _page.getKeyWords().getKeyWordWhile());
            AnalyzingDoc.addError(un_, _anaDoc, _page);
        } else if (!(pBlock_ instanceof AnaRendDoWhileCondition)) {
            if (!isPossibleEmpty(pBlock_)) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(_anaDoc.getFileName());
                un_.setIndexFile(pBlock_.getOffset());
                un_.buildError(_page.getAnalysisMessages().getUnexpectedDoTry(),
                        _page.getKeyWords().getKeyWordDo(),
                        _page.getKeyWords().getKeyWordWhile());
                AnalyzingDoc.addError(un_, _anaDoc, _page);
            } else if (pBlock_.getNextSibling() == null){
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(_anaDoc.getFileName());
                un_.setIndexFile(pBlock_.getOffset());
                un_.buildError(_page.getAnalysisMessages().getUnexpectedDoTry(),
                        _page.getKeyWords().getKeyWordDo(),
                        _page.getKeyWords().getKeyWordWhile());
                AnalyzingDoc.addError(un_, _anaDoc, _page);
            } else if (!(pBlock_.getNextSibling() instanceof AnaRendDoWhileCondition)){
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(_anaDoc.getFileName());
                un_.setIndexFile(pBlock_.getNextSibling().getOffset());
                un_.buildError(_page.getAnalysisMessages().getUnexpectedDoTry(),
                        _page.getKeyWords().getKeyWordDo(),
                        _page.getKeyWords().getKeyWordWhile());
                AnalyzingDoc.addError(un_, _anaDoc, _page);
            }
        }
    }

    @Override
    public String getRealLabel() {
        return label;
    }

    @Override
    public int getRealLabelOffset() {
        return labelOffset;
    }
}
