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
            if (exit(b_)) {
                childOfBreakable_ = true;
                break;
            }
            b_ = b_.getParent();
        }
        if (!childOfBreakable_) {
            _page.setSumOffset(getOffset());
            _page.zeroOffset();
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(_page.getCurrentFile());
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
            AnalyzingDoc.addError(un_, _page);
        }
    }
    private boolean exit(AnaRendParentBlock _b) {
        if (_b instanceof AnaRendBreakableBlock) {
            if (label.isEmpty()) {
                return isLoop(_b) || _b instanceof AnaRendSwitchBlock;
            }
            return StringUtil.quickEq(label, ((AnaRendBreakableBlock) _b).getRealLabel());
        }
        return false;
    }

    public static boolean isLoop(AnaRendBlock _bl) {
        return _bl instanceof AnaRendDoBlock||_bl instanceof AnaRendForEachLoop||_bl instanceof AnaRendForEachTable||_bl instanceof AnaRendForIterativeLoop||_bl instanceof AnaRendForMutableIterativeLoop||_bl instanceof AnaRendWhileCondition;
    }
    public String getLabel() {
        return label;
    }
}
