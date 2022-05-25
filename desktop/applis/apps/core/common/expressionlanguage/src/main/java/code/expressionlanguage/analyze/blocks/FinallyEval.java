package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.linkage.ExportCst;
import code.util.*;
import code.util.core.StringUtil;

public final class FinallyEval extends BracedBlock implements Eval {

    public FinallyEval(int _offset) {
        super(_offset);
    }

    @Override
    public OffsetStringInfo getRealLabelInfo() {
        return ElseCondition.getRealLabelInfo(this);
    }

    @Override
    public void checkTree(AnalyzingEl _anEl, AnalyzedPageEl _page) {
        AbsBk pBlock_ = getPreviousSibling();
        if (!(pBlock_ instanceof AbstractCatchEval) && !(pBlock_ instanceof TryEval)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(getFile());
            un_.setIndexFile(getOffset());
            un_.buildError(_page.getAnalysisMessages().getUnexpectedCatchElseFinally(),
                    _page.getKeyWords().getKeyWordFinally(),
                    StringUtil.join(
                            new StringList(
                                    _page.getKeyWords().getKeyWordCatch(),
                                    _page.getKeyWords().getKeyWordTry()
                            ),
                            ExportCst.JOIN_BLOCK));
            //key word len
            _page.addLocError(un_);
            addErrorBlock(un_.getBuiltError());
        }
    }


}
