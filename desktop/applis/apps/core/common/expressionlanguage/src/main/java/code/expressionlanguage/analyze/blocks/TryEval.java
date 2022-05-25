package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.linkage.ExportCst;
import code.util.StringList;
import code.util.core.StringUtil;

public final class TryEval extends LabelledOtherBlock implements Eval,BreakableLabelBlock {

    public TryEval(OffsetStringInfo _label, int _offset) {
        super(_offset,_label);
    }

    @Override
    public void checkTree(AnalyzingEl _anEl, AnalyzedPageEl _page) {
        AbsBk nBlock_ = getNextSibling();
        if (!(nBlock_ instanceof AbstractCatchEval) && !(nBlock_ instanceof FinallyEval)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(getFile());
            un_.setIndexFile(getOffset());
            //key word len
            un_.buildError(_page.getAnalysisMessages().getUnexpectedDoTry(),
                    _page.getKeyWords().getKeyWordTry(),
                    StringUtil.join(
                            new StringList(
                                    _page.getKeyWords().getKeyWordCatch(),
                                    _page.getKeyWords().getKeyWordFinally()
                            ),
                            ExportCst.JOIN_BLOCK));
            _page.addLocError(un_);
            addErrorBlock(un_.getBuiltError());
        }
    }


}
