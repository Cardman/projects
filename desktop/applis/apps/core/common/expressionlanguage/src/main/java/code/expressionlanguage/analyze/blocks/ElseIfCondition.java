package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.linkage.ExportCst;
import code.util.StringList;
import code.util.core.StringUtil;

public final class ElseIfCondition extends ConditionBlock implements BreakableBlock, CheckableTree {

    private final int delta;

    public ElseIfCondition(OffsetStringInfo _condition, int _offset, int _delta) {
        super(_condition, _offset);
        delta = _delta;
    }

    @Override
    public OffsetStringInfo getRealLabelInfo() {
        return ElseCondition.getRealLabelInfo(this);
    }

    @Override
    public void checkTree(AnalyzingEl _anEl, AnalyzedPageEl _page) {
        AbsBk pBlock_ = getPreviousSibling();
        if (!(pBlock_ instanceof IfCondition) && !(pBlock_ instanceof ElseIfCondition)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(getFile());
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

    public int getDelta() {
        return delta;
    }
}
