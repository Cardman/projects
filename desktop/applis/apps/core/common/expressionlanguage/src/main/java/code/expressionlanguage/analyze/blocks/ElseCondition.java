package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.linkage.ExportCst;
import code.util.StringList;
import code.util.core.StringUtil;

public final class ElseCondition extends BracedBlock implements BlockCondition {

    public ElseCondition(int _offset) {
        super(_offset);
    }

    @Override
    public OffsetStringInfo getRealLabelInfo() {
        return getRealLabelInfo(this);
    }

    static OffsetStringInfo getRealLabelInfo(AbsBk _current) {
        AbsBk p_ = _current.getPreviousSibling();
        while (!(p_ instanceof BreakableLabelBlock)) {
            if (p_ == null) {
                return new OffsetStringInfo(0, EMPTY_STRING);
            }
            p_ = p_.getPreviousSibling();
        }
        return ((BreakableLabelBlock) p_).getLabelInfo();
    }
    @Override
    public void checkTree(AnalyzingEl _anEl, AnalyzedPageEl _page) {
        AbsBk pBlock_ = getPreviousSibling();
        if (!(pBlock_ instanceof IfCondition) && !(pBlock_ instanceof ElseIfCondition)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(getFile());
            un_.setIndexFile(getOffset());
            un_.buildError(_page.getAnalysisMessages().getUnexpectedCatchElseFinally(),
                    _page.getKeyWords().getKeyWordElse(),
                    StringUtil.join(
                            new StringList(
                                    _page.getKeyWords().getKeyWordIf(),
                                    _page.getKeyWords().getKeyWordElseif()
                            ),
                            ExportCst.JOIN_BLOCK));
            //key word len
            addErrorBlock(un_.getBuiltError());
            _page.addLocError(un_);
        }
    }


}
