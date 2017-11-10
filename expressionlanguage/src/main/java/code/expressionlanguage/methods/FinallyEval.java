package code.expressionlanguage.methods;
import org.w3c.dom.Element;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.ReadWrite;
import code.expressionlanguage.exceptions.WrapperException;
import code.expressionlanguage.methods.exceptions.BadTryException;
import code.expressionlanguage.stacks.TryBlockStack;
import code.util.NatTreeMap;

public final class FinallyEval extends BracedStack implements Eval, IncrNextGroup {

    public FinallyEval(Element _el, ContextEl _importingPage, int _indexChild,
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
            throw new BadTryException(_cont.joinPages());
        }
        Block prev_ = getPreviousSibling();
        boolean existTry_ = false;
        while (prev_ != null) {
            if (prev_ instanceof CatchEval) {
                prev_ = prev_.getPreviousSibling();
                continue;
            }
            existTry_ = prev_ instanceof TryEval;
            break;
        }
        if (!existTry_) {
            throw new BadTryException(_cont.joinPages());
        }
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
    }

    @Override
    boolean canBeIncrementedNextGroup() {
        return true;
    }

    @Override
    boolean canBeIncrementedCurGroup() {
        return false;
    }

    @Override
    boolean canBeLastOfBlockGroup() {
        return true;
    }

    @Override
    public void checkCallConstructor(ContextEl _cont) {
    }

    @Override
    public String getTagName() {
        return TAG_FINALLY;
    }
    @Override
    public void processEl(ContextEl _cont) {
        PageEl ip_ = _cont.getLastPage();
        TryBlockStack ts_ = (TryBlockStack) ip_.getLastStack();
        ts_.setVisitedCatch(getIndexInGroup()-1);
        if (ts_.isVisitedFinally()) {
            ip_.removeLastBlock();
            processBlock(_cont);
            return;
        }
        ip_.getReadWrite().setBlock(getFirstChild());
    }

    @Override
    public void exitStack(ContextEl _context) {
        PageEl ip_ = _context.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        interruptAfterFinally(ip_);
        TryBlockStack tryStack_ = (TryBlockStack) ip_.getLastStack();
        CallingFinally call_ = tryStack_.getCalling();
        if (call_ != null) {
            ip_.removeLastBlock();
            call_.removeBlockFinally(_context);
            return;
        }
        FinallyEval catch_ = (FinallyEval) tryStack_.getCurrentCatchBlock();
        tryStack_.setVisitedFinally(true);
        rw_.setBlock(catch_);
    }

    private static void interruptAfterFinally(PageEl _ip) {
        TryBlockStack tryStack_ = (TryBlockStack) _ip.getLastStack();
        WrapperException t_ = tryStack_.getThrownException();
        if (t_ != null) {
            _ip.removeLastBlock();
            throw t_;
        }
    }
}
