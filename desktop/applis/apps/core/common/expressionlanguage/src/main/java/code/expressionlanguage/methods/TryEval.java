package code.expressionlanguage.methods;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecTryEval;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.exec.stacks.TryBlockStack;
import code.util.CustList;
import code.util.StringList;

public final class TryEval extends BracedBlock implements Eval {


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
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        ExecTryEval exec_ = new ExecTryEval(getOffset(),label,labelOffset);
        page_.getBlockToWrite().appendChild(exec_);
        page_.getAnalysisAss().getMappingMembers().put(exec_,this);
        page_.getAnalysisAss().getMappingBracedMembers().put(this,exec_);
        _cont.getCoverage().putBlockOperations(_cont, exec_,this);
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


}
