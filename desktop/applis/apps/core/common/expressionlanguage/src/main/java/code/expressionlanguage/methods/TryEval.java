package code.expressionlanguage.methods;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.calls.util.ReadWrite;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.stacks.TryBlockStack;
import code.util.CustList;
import code.util.StringList;

public final class TryEval extends BracedStack implements Eval {


    private String label;
    private int labelOffset;

    public TryEval(OffsetStringInfo _label, OffsetsBlock _offset) {
        super(_offset);
        label = _label.getInfo();
        labelOffset = _label.getOffset();
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String getRealLabel() {
        return label;
    }

    @Override
    public int getRealLabelOffset() {
        return getLabelOffset();
    }

    public int getLabelOffset() {
        return labelOffset;
    }

    @Override
    public void buildExpressionLanguageReadOnly(ContextEl _cont) {
    }

    @Override
    public void checkTree(ContextEl _an, AnalyzingEl _anEl) {
        Block nBlock_ = getNextSibling();
        if (!(nBlock_ instanceof AbstractCatchEval)) {
            if (!(nBlock_ instanceof FinallyEval)) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(getFile().getFileName());
                un_.setIndexFile(getOffset().getOffsetTrim());
                //key word len
                un_.buildError(_an.getAnalysisMessages().getUnexpectedDoTry(),
                        _an.getKeyWords().getKeyWordTry(),
                        StringList.join(
                                new StringList(
                                        _an.getKeyWords().getKeyWordCatch(),
                                        _an.getKeyWords().getKeyWordFinally()
                                ),
                                "|"));
                _an.addError(un_);
            }
        }
    }

    @Override
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {
        refLabel(_parts,label,labelOffset);
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        Block n_ = getNextSibling();
        TryBlockStack tryStack_ = new TryBlockStack();
        while (n_ instanceof AbstractCatchEval || n_ instanceof FinallyEval) {
            tryStack_.setLastBlock((BracedBlock) n_);
            n_ = n_.getNextSibling();
        }
        tryStack_.setCurrentVisitedBlock(this);
        tryStack_.setBlock(this);
        ip_.addBlock(tryStack_);
        ip_.getReadWrite().setBlock(getFirstChild());
    }

    @Override
    public void exitStack(ContextEl _context) {
        AbstractPageEl ip_ = _context.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        rw_.setBlock(getNextSibling());
    }

}
