package code.formathtml;

import code.expressionlanguage.errors.custom.UnexpectedTagName;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.formathtml.stacks.RendLoopBlockStack;
import code.formathtml.stacks.RendReadWrite;

public final class RendDoBlock extends RendParentBlock implements RendLoop {

    private String label;
    private int labelOffset;

    RendDoBlock(OffsetStringInfo _label, OffsetsBlock _offset) {
        super(_offset);
        label = _label.getInfo();
        labelOffset = _label.getOffset();
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String getRealLabel() {
        return getLabel();
    }

    @Override
    public void processEl(Configuration _cont) {
        ImportingPage ip_ = _cont.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        RendLoopBlockStack c_ = ip_.getLastLoopIfPossible(this);
        if (c_ != null) {
            if (c_.isFinished()) {
                RendBlock nextSibling_ = getNextSibling();
                if (nextSibling_ instanceof RendPossibleEmpty) {
                    nextSibling_ = nextSibling_.getNextSibling();
                }
                RendBlock next_ = nextSibling_;
                next_.processBlockAndRemove(_cont);
                return;
            }
            rw_.setRead(getFirstChild());
            return;
        }
        RendLoopBlockStack l_ = new RendLoopBlockStack();
        l_.setBlock(this);
        l_.setCurrentVisitedBlock(this);
        ip_.addBlock(l_);
        rw_.setRead(getFirstChild());
    }

    @Override
    public void exitStack(Configuration _context) {
        processLastElementLoop(_context);
    }

    @Override
    public void processLastElementLoop(Configuration _conf) {
        ImportingPage ip_ = _conf.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        RendBlock nextSibling_ = getNextSibling();
        if (nextSibling_ instanceof RendPossibleEmpty) {
            nextSibling_ = nextSibling_.getNextSibling();
        }
        rw_.setRead(nextSibling_);
    }

    @Override
    public void buildExpressionLanguage(Configuration _cont,RendDocumentBlock _doc) {
        RendBlock pBlock_ = getNextSibling();
        if (pBlock_ == null) {
            UnexpectedTagName un_ = new UnexpectedTagName();
            un_.setFileName(_cont.getCurrentFileName());
            un_.setIndexFile(getOffset().getOffsetTrim());
            _cont.getClasses().addError(un_);
        } else if (!(pBlock_ instanceof RendDoWhileCondition)) {
            if (!(pBlock_ instanceof RendPossibleEmpty)) {
                UnexpectedTagName un_ = new UnexpectedTagName();
                un_.setFileName(_cont.getCurrentFileName());
                un_.setIndexFile(pBlock_.getOffset().getOffsetTrim());
                _cont.getClasses().addError(un_);
            } else if (pBlock_.getNextSibling() == null){
                UnexpectedTagName un_ = new UnexpectedTagName();
                un_.setFileName(_cont.getCurrentFileName());
                un_.setIndexFile(pBlock_.getOffset().getOffsetTrim());
                _cont.getClasses().addError(un_);
            } else if (!(pBlock_.getNextSibling() instanceof RendDoWhileCondition)){
                UnexpectedTagName un_ = new UnexpectedTagName();
                un_.setFileName(_cont.getCurrentFileName());
                un_.setIndexFile(pBlock_.getNextSibling().getOffset().getOffsetTrim());
                _cont.getClasses().addError(un_);
            }
        }
    }
}
