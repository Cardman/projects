package code.formathtml;

import code.expressionlanguage.errors.custom.UnexpectedTagName;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.stacks.RendTryBlockStack;

public final class RendTryEval extends RendParentBlock implements RendEval {

    private String label;
    private int labelOffset;
    RendTryEval(OffsetStringInfo _label, OffsetsBlock _offset) {
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
    public void buildExpressionLanguage(Configuration _cont,RendDocumentBlock _doc) {
        RendBlock nBlock_ = getNextSibling();
        if (!(nBlock_ instanceof RendAbstractCatchEval)) {
            if (!(nBlock_ instanceof RendFinallyEval)) {
                if (!(nBlock_ instanceof RendPossibleEmpty)) {
                    UnexpectedTagName un_ = new UnexpectedTagName();
                    un_.setFileName(_cont.getCurrentFileName());
                    un_.setIndexFile(getOffset().getOffsetTrim());
                    _cont.addError(un_);
                } else if (!(nBlock_.getNextSibling() instanceof RendAbstractCatchEval)){
                    if (!(nBlock_.getNextSibling() instanceof RendFinallyEval)) {
                        UnexpectedTagName un_ = new UnexpectedTagName();
                        un_.setFileName(_cont.getCurrentFileName());
                        un_.setIndexFile(getOffset().getOffsetTrim());
                        _cont.addError(un_);
                    }
                }
            }
        }
    }

    @Override
    public void processEl(Configuration _cont) {
        ImportingPage ip_ = _cont.getLastPage();
        RendBlock n_ = getNextSibling();
        RendTryBlockStack tryStack_ = new RendTryBlockStack();
        while (n_ instanceof RendAbstractCatchEval || n_ instanceof RendFinallyEval || n_ instanceof RendPossibleEmpty) {
            if (n_ instanceof RendParentBlock) {
                tryStack_.setLastBlock((RendParentBlock) n_);
            }
            n_ = n_.getNextSibling();
        }
        tryStack_.setCurrentVisitedBlock(this);
        tryStack_.setBlock(this);
        ip_.addBlock(tryStack_);
        ip_.getRendReadWrite().setRead(getFirstChild());
    }

    @Override
    public void exitStack(Configuration _context) {
        ImportingPage ip_ = _context.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        rw_.setRead(getNextSibling());
    }
}
