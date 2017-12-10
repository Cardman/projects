package code.expressionlanguage.methods;
import code.xml.components.Element;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.ReadWrite;
import code.expressionlanguage.methods.exceptions.BadLoopException;
import code.expressionlanguage.stacks.LoopBlockStack;
import code.util.NatTreeMap;

public final class DoBlock extends BracedStack implements Loop, IncrCurrentGroup {

    public DoBlock(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
    }

    @Override
    public NatTreeMap<String,String> getClassNames() {
        NatTreeMap<String,String> tr_ = new NatTreeMap<String,String>();
        return tr_;
    }

    @Override
    public void checkBlocksTree(ContextEl _cont) {
        PageEl page_ = _cont.getLastPage();
        page_.setProcessingAttribute(EMPTY_STRING);
        page_.setOffset(0);
        if (getFirstChild() == null) {
            throw new BadLoopException(_cont.joinPages());
        }
        if (!(getNextSibling() instanceof WhileCondition)) {
            throw new BadLoopException(_cont.joinPages());
        }
        WhileCondition w_ = (WhileCondition) getNextSibling();
        if (w_.getFirstChild() != null) {
            throw new BadLoopException(_cont.joinPages());
        }
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
    }

    @Override
    boolean canBeIncrementedNextGroup() {
        return false;
    }

    @Override
    boolean canBeIncrementedCurGroup() {
        return true;
    }

    @Override
    boolean canBeLastOfBlockGroup() {
        return false;
    }

    @Override
    public void checkCallConstructor(ContextEl _cont) {
    }

    @Override
    public String getTagName() {
        return TAG_DO;
    }

    @Override
    public void processEl(ContextEl _cont) {
        PageEl ip_ = _cont.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        LoopBlockStack c_ = ip_.getLastLoopIfPossible();
        if (c_ != null && c_.getBlock() == this) {
            if (c_.isEvaluatingKeepLoop()) {
                processLastElementLoop(_cont);
                return;
            }
            if (c_.isFinished()) {
                removeVarAndLoop(ip_);
                Block next_ = getNextSibling();
                rw_.setBlock(next_);
                next_.processBlock(_cont);
                return;
            }
            rw_.setBlock(getFirstChild());
            return;
        }
        LoopBlockStack l_ = new LoopBlockStack();
        l_.setBlock(this);
        ip_.addBlock(l_);
        rw_.setBlock(getFirstChild());
    }

    Condition getNext() {
        return (Condition) getNextSibling();
    }

    @Override
    public void exitStack(ContextEl _context) {
        processLastElementLoop(_context);
    }

    @Override
    public void processLastElementLoop(ContextEl _conf) {
        PageEl ip_ = _conf.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        LoopBlockStack l_ = (LoopBlockStack) ip_.getLastStack();
        l_.setEvaluatingKeepLoop(true);
        Block forLoopLoc_ = l_.getBlock();
        rw_.setBlock(forLoopLoc_);
        if (!keepLoop(_conf)) {
            l_.setFinished(true);
        }
        l_.setEvaluatingKeepLoop(false);
    }

    public boolean keepLoop(ContextEl _conf) {
        _conf.getLastPage().setProcessingAttribute(EMPTY_STRING);
        _conf.getLastPage().setOffset(0);
        Condition c_ = getNext();
        return c_.evaluateCondition(_conf);
    }
}
