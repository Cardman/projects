package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.formathtml.analyze.AnalyzingDoc;
import code.util.StringList;
import code.util.core.StringUtil;

public final class AnaRendBreakBlock extends AnaRendLeaf implements AnaRendBuildEl {

    private final String label;
    private final int labelOffset;
    AnaRendBreakBlock(OffsetStringInfo _label, int _offset) {
        super(_offset);
        label = _label.getInfo();
        labelOffset = _label.getOffset();
    }

    @Override
    public void buildExpressionLanguage(AnaRendDocumentBlock _doc, AnalyzingDoc _anaDoc, AnalyzedPageEl _page) {
        boolean childOfBreakable_ = false;
        AnaRendParentBlock b_ = getParent();
        while (b_ != null) {
            if (b_ instanceof AnaRendBreakableBlock) {
                if (label.isEmpty()) {
                    if (b_ instanceof AnaRendLoop || b_ instanceof AnaRendSwitchBlock) {
                        childOfBreakable_ = true;
                        break;
                    }
                } else {
                    if (StringUtil.quickEq(label, ((AnaRendBreakableBlock)b_).getRealLabel())){
                        childOfBreakable_ = true;
                        break;
                    }
                }
            }
            b_ = b_.getParent();
        }
        if (!childOfBreakable_) {
            _page.setGlobalOffset(getOffset());
            _page.zeroOffset();
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_anaDoc.getFileName());
            un_.setIndexFile(labelOffset);
            if (label.isEmpty()) {
                un_.buildError(_page.getAnalysisMessages().getUnexpectedAbrupt(),
                        _page.getKeyWords().getKeyWordBreak(),
                        StringUtil.join(
                                new StringList(
                                        _page.getKeyWords().getKeyWordSwitch(),
                                        _page.getKeyWords().getKeyWordFor(),
                                        _page.getKeyWords().getKeyWordForeach(),
                                        _page.getKeyWords().getKeyWordDo(),
                                        _page.getKeyWords().getKeyWordWhile()
                                ),
                                OR_ERR));
            } else {
                un_.buildError(_page.getAnalysisMessages().getUnexpectedAbruptLab(),
                        _page.getKeyWords().getKeyWordBreak(),
                        label,
                        StringUtil.join(
                                new StringList(
                                        _page.getKeyWords().getKeyWordSwitch(),
                                        _page.getKeyWords().getKeyWordTry(),
                                        _page.getKeyWords().getKeyWordCatch(),
                                        _page.getKeyWords().getKeyWordFinally(),
                                        _page.getKeyWords().getKeyWordIf(),
                                        _page.getKeyWords().getKeyWordElseif(),
                                        _page.getKeyWords().getKeyWordElse(),
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
