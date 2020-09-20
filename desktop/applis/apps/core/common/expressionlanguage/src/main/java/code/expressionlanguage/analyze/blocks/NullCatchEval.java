package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.exec.blocks.ExecNullCatchEval;
import code.expressionlanguage.files.OffsetsBlock;

public final class NullCatchEval extends AbstractCatchEval {

    public NullCatchEval(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public void reach(AnalyzingEl _anEl, AnalyzedPageEl _page) {
        Block p_ = getPreviousSibling();
        boolean reachCatch_ = true;
        while (!(p_ instanceof TryEval)) {
            if (p_ instanceof NullCatchEval) {
                reachCatch_ = false;
                break;
            }
            if (p_ == null) {
                break;
            }
            p_ = p_.getPreviousSibling();
        }
        if (reachCatch_) {
            _anEl.reach(this);
        } else {
            _anEl.unreach(this);
        }
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        _page.getCoverage().putCatches(this);
        ExecNullCatchEval exec_ = new ExecNullCatchEval(getOffset());
        exec_.setFile(_page.getBlockToWrite().getFile());
        _page.getBlockToWrite().appendChild(exec_);
        _page.getAnalysisAss().getMappingBracedMembers().put(this,exec_);
        _page.getCoverage().putBlockOperations(exec_,this);
    }

}
