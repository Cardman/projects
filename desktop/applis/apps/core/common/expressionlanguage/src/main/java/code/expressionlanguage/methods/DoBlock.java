package code.expressionlanguage.methods;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.exec.stacks.LoopBlockStack;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class DoBlock extends BracedStack implements Loop {

    private String label;
    private int labelOffset;

    public DoBlock(OffsetStringInfo _label, OffsetsBlock _offset) {
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
    public void checkTree(ContextEl _an, AnalyzingEl _anEl) {
        Block nextSibling_ = getNextSibling();
        if (nextSibling_ == null) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(getFile().getFileName());
            un_.setIndexFile(getOffset().getOffsetTrim());
            //key word len
            un_.buildError(_an.getAnalysisMessages().getUnexpectedDoTry(),
                    _an.getKeyWords().getKeyWordDo(),
                    StringList.join(
                            new StringList(
                                    _an.getKeyWords().getKeyWordWhile()
                            ),
                            "|"));
            _an.addError(un_);
            return;
        }
        if (!(nextSibling_ instanceof DoWhileCondition)) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(nextSibling_.getFile().getFileName());
            un_.setIndexFile(nextSibling_.getOffset().getOffsetTrim());
            //key word len
            un_.buildError(_an.getAnalysisMessages().getUnexpectedDoTry(),
                    _an.getKeyWords().getKeyWordDo(),
                    StringList.join(
                            new StringList(
                                    _an.getKeyWords().getKeyWordWhile()
                            ),
                            "|"));
            _an.addError(un_);
        }
    }

    @Override
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {
        refLabel(_parts,label,labelOffset);
    }

    @Override
    public void buildExpressionLanguageReadOnly(ContextEl _cont) {
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        LoopBlockStack c_ = ip_.getLastLoopIfPossible(this);
        if (c_ != null) {
            ip_.processVisitedLoop(c_,this,getNextSibling(),_cont);
            return;
        }
        LoopBlockStack l_ = new LoopBlockStack();
        l_.setBlock(this);
        l_.setCurrentVisitedBlock(this);
        ip_.addBlock(l_);
        rw_.setBlock(getFirstChild());
    }

    @Override
    public void exitStack(ContextEl _context) {
        processLastElementLoop(_context);
    }

    @Override
    public void processLastElementLoop(ContextEl _conf) {
        AbstractPageEl ip_ = _conf.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        rw_.setBlock(getNextSibling());
    }

}
