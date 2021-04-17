package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.linkage.ExportCst;
import code.util.StringList;
import code.util.core.StringUtil;

public final class ElseIfCondition extends ConditionBlock implements BlockCondition {

    private final int delta;

    public ElseIfCondition(OffsetStringInfo _condition, int _offset, int _delta) {
        super(_condition, _offset);
        delta = _delta;
    }

    @Override
    public String getRealLabel() {
        AbsBk p_ = getPreviousSibling();
        while (!(p_ instanceof IfCondition)) {
            if (p_ == null) {
                return EMPTY_STRING;
            }
            p_ = p_.getPreviousSibling();
        }
        return ((IfCondition)p_).getLabel();
    }

    @Override
    public int getRealLabelOffset() {
        AbsBk p_ = getPreviousSibling();
        while (!(p_ instanceof IfCondition)) {
            p_ = p_.getPreviousSibling();
        }
        return ((IfCondition)p_).getLabelOffset();
    }

    @Override
    public void checkTree(AnalyzingEl _anEl, AnalyzedPageEl _page) {
        AbsBk pBlock_ = getPreviousSibling();
        if (!(pBlock_ instanceof IfCondition)) {
            if (!(pBlock_ instanceof ElseIfCondition)) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(getFile().getFileName());
                un_.setIndexFile(getOffset());
                un_.buildError(_page.getAnalysisMessages().getUnexpectedCatchElseFinally(),
                        _page.getKeyWords().getKeyWordElseif(),
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

    public int getDelta() {
        return delta;
    }
}
