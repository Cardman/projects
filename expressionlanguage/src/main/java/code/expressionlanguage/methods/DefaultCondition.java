package code.expressionlanguage.methods;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.calls.util.ReadWrite;
import code.expressionlanguage.errors.custom.UnexpectedTagName;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.stacks.SwitchBlockStack;

public final class DefaultCondition extends SwitchPartBlock {

    public DefaultCondition(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
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
        buildEmptyEl(_cont);
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        SwitchBlockStack sw_ = (SwitchBlockStack) ip_.getLastStack();
        sw_.setCurentVisitedBlock(this);
        if (sw_.isEntered()) {
            rw_.setBlock(getFirstChild());
            return;
        }
        ip_.setGlobalOffset(getOffset().getOffsetTrim());
        ip_.setOffset(0);
        sw_.setEntered(true);
        rw_.setBlock(getFirstChild());
    }

    @Override
    public void exitStack(ContextEl _context) {
        AbstractPageEl ip_ = _context.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        SwitchBlockStack if_ = (SwitchBlockStack) ip_.getLastStack();
        if (if_.getLastVisitedBlock() == this) {
            rw_.setBlock(if_.getBlock());
        } else {
            rw_.setBlock(getNextSibling());
        }
    }
}
