package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecDoBlock;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.util.StringList;

public final class DoBlock extends BracedBlock implements Loop {

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
            setReachableError(true);
            getErrorsBlock().add(un_.getBuiltError());
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
            setReachableError(true);
            getErrorsBlock().add(un_.getBuiltError());
        }
    }

    @Override
    public void buildExpressionLanguageReadOnly(ContextEl _cont) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        ExecDoBlock exec_ = new ExecDoBlock(getOffset(),label);
        exec_.setFile(page_.getBlockToWrite().getFile());
        page_.getBlockToWrite().appendChild(exec_);
        page_.getAnalysisAss().getMappingMembers().put(exec_,this);
        page_.getAnalysisAss().getMappingBracedMembers().put(this,exec_);
        _cont.getCoverage().putBlockOperations(_cont, exec_,this);
    }

}
