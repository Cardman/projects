package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.OffsetsBlock;

public final class DoWhileCondition extends Condition {

    public DoWhileCondition(OffsetStringInfo _condition, OffsetsBlock _offset) {
        super(_condition, _offset);
    }

    @Override
    public void checkTree(AnalyzingEl _anEl, AnalyzedPageEl _page) {
        if (getFirstChild() != null) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(getFile().getFileName());
            un_.setIndexFile(getOffset().getOffsetTrim());
            //key word len
            un_.buildError(_page.getAnalysisMessages().getDoWhileNotEmpty(),
                    _page.getKeyWords().getKeyWordWhile(),
                    _page.getKeyWords().getKeyWordDo());
            _page.addLocError(un_);
            setReachableError(true);
            getErrorsBlock().add(un_.getBuiltError());
        }
    }


}
