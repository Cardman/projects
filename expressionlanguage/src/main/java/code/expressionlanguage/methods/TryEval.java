package code.expressionlanguage.methods;
import org.w3c.dom.Element;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.ReadWrite;
import code.expressionlanguage.methods.exceptions.BadCatchException;
import code.expressionlanguage.stacks.TryBlockStack;
import code.util.NatTreeMap;

public final class TryEval extends BracedStack implements Eval, IncrCurrentGroup {

    public TryEval(Element _el, ContextEl _importingPage, int _indexChild,
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
        Block next_ = getNextSibling();
        boolean existCatch_ = false;
        while (next_ != null) {
            if (next_ instanceof FinallyEval) {
                existCatch_ = true;
                break;
            }
            existCatch_ = next_ instanceof CatchEval;
            break;
        }
        if (!existCatch_ || getFirstChild() == null) {
            PageEl page_ = _cont.getLastPage();
//            page_.setProcessingNode(getAssociateElement());
            page_.setProcessingAttribute(EMPTY_STRING);
//            page_.setLookForAttrValue(false);
            page_.setOffset(0);
            throw new BadCatchException(_cont.joinPages());
        }
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
//        removeLocalVariablesFromParent();
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
        return TAG_TRY;
    }

    @Override
    public void processEl(ContextEl _cont) {
        PageEl ip_ = _cont.getLastPage();
        Block n_ = getNextSibling();
        TryBlockStack tryStack_ = new TryBlockStack();
        while (n_ != null) {
            if (!(n_ instanceof Eval)) {
                break;
            }
            tryStack_.getCatchBlocks().add((BracedBlock)n_);
            n_ = n_.getNextSibling();
        }
        tryStack_.setBlock(this);
//        l_.add(tryStack_);
        ip_.addBlock(tryStack_);
        ip_.getReadWrite().setBlock(getFirstChild());
//        processAfterBlock(_cont, ip_);
    }

    @Override
    public void exitStack(ContextEl _context) {
        PageEl ip_ = _context.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        rw_.setBlock(getNextSibling());
    }
}
