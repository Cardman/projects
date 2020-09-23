package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.util.*;

public final class FinallyEval extends BracedBlock implements Eval {

    public FinallyEval(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public String getRealLabel() {
        Block p_ = getPreviousSibling();
        while (!(p_ instanceof TryEval)) {
            if (p_ == null) {
                return EMPTY_STRING;
            }
            p_ = p_.getPreviousSibling();
        }
        return ((TryEval)p_).getLabel();
    }

    @Override
    public int getRealLabelOffset() {
        Block p_ = getPreviousSibling();
        while (!(p_ instanceof TryEval)) {
            p_ = p_.getPreviousSibling();
        }
        return ((TryEval)p_).getLabelOffset();
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
//        ExecFinallyEval exec_ = new ExecFinallyEval(getOffset());
//        exec_.setFile(_page.getBlockToWrite().getFile());
//        _page.getBlockToWrite().appendChild(exec_);
//        _page.getAnalysisAss().getMappingBracedMembers().put(this,exec_);
//        _page.getCoverage().putBlockOperations(exec_,this);
    }

    @Override
    public void checkTree(AnalyzingEl _anEl, AnalyzedPageEl _page) {
        Block pBlock_ = getPreviousSibling();
        if (!(pBlock_ instanceof AbstractCatchEval)) {
            if (!(pBlock_ instanceof TryEval)) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(getFile().getFileName());
                un_.setIndexFile(getOffset().getOffsetTrim());
                un_.buildError(_page.getAnalysisMessages().getUnexpectedCatchElseFinally(),
                        _page.getKeyWords().getKeyWordFinally(),
                        StringList.join(
                                new StringList(
                                        _page.getKeyWords().getKeyWordCatch(),
                                        _page.getKeyWords().getKeyWordTry()
                                ),
                                "|"));
                //key word len
                _page.addLocError(un_);
                setReachableError(true);
                getErrorsBlock().add(un_.getBuiltError());
            }
        }
    }


}
