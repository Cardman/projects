package code.expressionlanguage.methods;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.calls.util.ReadWrite;
import code.expressionlanguage.errors.custom.UnexpectedTagName;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.methods.util.AbstractCoverageResult;
import code.expressionlanguage.stacks.SwitchBlockStack;
import code.util.CustList;

public final class DefaultCondition extends SwitchPartBlock {

    public DefaultCondition(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        checkDefault(_cont);
        buildEmptyEl(_cont);
    }

    @Override
    public void buildExpressionLanguageReadOnly(ContextEl _cont) {
        checkDefault(_cont);
    }

    private void checkDefault(ContextEl _cont) {
        BracedBlock b_ = getParent();
        if (!(b_ instanceof SwitchBlock)) {
            AnalyzedPageEl page_ = _cont.getAnalyzing();
            page_.setGlobalOffset(getOffset().getOffsetTrim());
            page_.setOffset(0);
            UnexpectedTagName un_ = new UnexpectedTagName();
            un_.setFileName(getFile().getFileName());
            un_.setIndexFile(getOffset().getOffsetTrim());
            _cont.getClasses().addError(un_);
        } else {
            _cont.getCoverage().putBlockOperationsSwitchs(_cont,b_,this);
            Block first_ = b_.getFirstChild();
            while (first_ != this) {
                if (first_ instanceof DefaultCondition) {
                    UnexpectedTagName un_ = new UnexpectedTagName();
                    un_.setFileName(getFile().getFileName());
                    un_.setIndexFile(getOffset().getOffsetTrim());
                    _cont.getClasses().addError(un_);
                    break;
                }
                first_ = first_.getNextSibling();
            }
        }
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        ip_.setGlobalOffset(getOffset().getOffsetTrim());
        ip_.setOffset(0);
        rw_.setBlock(getFirstChild());
    }

    @Override
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {
        BracedBlock parent_ = getParent();
        AbstractCoverageResult result_ = _cont.getCoverage().getCoverSwitchs().getVal(parent_).getVal(this);
        String tag_;
        if (result_.isFullCovered()) {
            tag_ = "<span class=\"f\">";
        } else {
            tag_ = "<span class=\"n\">";
        }
        int off_ = getOffset().getOffsetTrim();
        _parts.add(new PartOffset(tag_,off_));
        tag_ = "</span>";
        _parts.add(new PartOffset(tag_,off_+ _cont.getKeyWords().getKeyWordDefault().length()));
    }
}
