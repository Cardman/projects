package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.formathtml.Configuration;
import code.formathtml.analyze.AnalyzingDoc;
import code.util.StringList;

public final class AnaRendContinueBlock extends AnaRendLeaf {
    private String label;
    private int labelOffset;
    AnaRendContinueBlock(OffsetStringInfo _label, OffsetsBlock _offset) {
        super(_offset);
        label = _label.getInfo();
        labelOffset = _label.getOffset();
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont, AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        boolean childOfLoop_ = false;
        AnaRendParentBlock b_ = getParent();
        while (b_ != null) {
            if (b_ instanceof AnaRendLoop) {
                if (label.isEmpty()) {
                    childOfLoop_ = true;
                    break;
                }
                if (StringList.quickEq(label, ((AnaRendBreakableBlock)b_).getRealLabel())){
                    childOfLoop_ = true;
                    break;
                }
            }
            b_ = b_.getParent();
        }
        if (!childOfLoop_) {
            _page.setGlobalOffset(getOffset().getOffsetTrim());
            _page.setOffset(0);
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_anaDoc.getFileName());
            un_.setIndexFile(labelOffset);
            if (label.isEmpty()) {
                un_.buildError(_page.getAnalysisMessages().getUnexpectedAbrupt(),
                        _page.getKeyWords().getKeyWordContinue(),
                        StringList.join(
                                new StringList(
                                        _page.getKeyWords().getKeyWordFor(),
                                        _page.getKeyWords().getKeyWordForeach(),
                                        _page.getKeyWords().getKeyWordDo(),
                                        _page.getKeyWords().getKeyWordWhile()
                                ),
                                OR_ERR));
            } else {
                un_.buildError(_page.getAnalysisMessages().getUnexpectedAbruptLab(),
                        _page.getKeyWords().getKeyWordContinue(),
                        label,
                        StringList.join(
                                new StringList(
                                        _page.getKeyWords().getKeyWordFor(),
                                        _page.getKeyWords().getKeyWordForeach(),
                                        _page.getKeyWords().getKeyWordDo(),
                                        _page.getKeyWords().getKeyWordWhile()
                                ),
                                OR_ERR));
            }
            AnalyzingDoc.addError(un_, _anaDoc, _page);
        }
    }

    public String getLabel() {
        return label;
    }
}
