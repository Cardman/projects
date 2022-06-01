package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.formathtml.analyze.AnalyzingDoc;
import code.util.StringList;
import code.util.core.StringUtil;

public final class AnaRendContinueBlock extends AnaRendLeaf implements AnaRendBuildEl {
    private final String label;
    private final int labelOffset;
    AnaRendContinueBlock(OffsetStringInfo _label, int _offset) {
        super(_offset);
        label = _label.getInfo();
        labelOffset = _label.getOffset();
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        boolean childOfLoop_ = false;
        AnaRendParentBlock b_ = getParent();
        while (b_ != null) {
            if (exit(b_)) {
                childOfLoop_ = true;
                break;
            }
            b_ = b_.getParent();
        }
        if (!childOfLoop_) {
            _page.setSumOffset(getOffset());
            _page.zeroOffset();
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(_page.getCurrentFile());
            un_.setIndexFile(labelOffset);
            if (label.isEmpty()) {
                un_.buildError(_page.getAnalysisMessages().getUnexpectedAbrupt(),
                        _page.getKeyWords().getKeyWordContinue(),
                        StringUtil.join(
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
                        StringUtil.join(
                                new StringList(
                                        _page.getKeyWords().getKeyWordFor(),
                                        _page.getKeyWords().getKeyWordForeach(),
                                        _page.getKeyWords().getKeyWordDo(),
                                        _page.getKeyWords().getKeyWordWhile()
                                ),
                                OR_ERR));
            }
            AnalyzingDoc.addError(un_, _page);
        }
    }
    private boolean exit(AnaRendParentBlock _b) {
        if (AnaRendBreakBlock.isLoop(_b)) {
            if (label.isEmpty()) {
                return true;
            }
            return StringUtil.quickEq(label, ((AnaRendBreakableBlock) _b).getRealLabel());
        }
        return false;
    }

    public String getLabel() {
        return label;
    }
}
