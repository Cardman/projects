package code.expressionlanguage.methods;

import code.expressionlanguage.AnalyzedPageEl;
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
        _cont.getCoverage().putCatches(_cont,this);
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        ExecNullCatchEval exec_ = new ExecNullCatchEval(getOffset());
        page_.getBlockToWrite().appendChild(exec_);
        page_.getAnalysisAss().getMappingMembers().put(exec_,this);
        page_.getAnalysisAss().getMappingBracedMembers().put(this,exec_);
        _cont.getCoverage().putBlockOperations(_cont, exec_,this);
    }

}
