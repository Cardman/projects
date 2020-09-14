package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecNullCatchEval;
import code.expressionlanguage.files.OffsetsBlock;

public final class NullCatchEval extends AbstractCatchEval {

    public NullCatchEval(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public void reach(ContextEl _an, AnalyzingEl _anEl) {
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
    public void buildExpressionLanguageReadOnly(ContextEl _cont) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.getCoverage().putCatches(this);
        ExecNullCatchEval exec_ = new ExecNullCatchEval(getOffset());
        exec_.setFile(page_.getBlockToWrite().getFile());
        page_.getBlockToWrite().appendChild(exec_);
        page_.getAnalysisAss().getMappingBracedMembers().put(this,exec_);
        page_.getCoverage().putBlockOperations(exec_,this);
    }

}
