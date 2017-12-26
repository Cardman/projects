package code.expressionlanguage.methods;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.ReadWrite;
import code.expressionlanguage.methods.exceptions.BadElseIfException;
import code.expressionlanguage.stacks.IfBlockStack;
import code.sml.Element;
import code.util.NatTreeMap;

public final class ElseIfCondition extends Condition implements BlockCondition, IncrCurrentGroup, IncrNextGroup {

    public ElseIfCondition(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
    }

    public ElseIfCondition(ContextEl _importingPage, int _indexChild,
            BracedBlock _m, String _condition) {
        super(_importingPage, _indexChild, _m, _condition);
    }

    @Override
    public NatTreeMap<String,String> getClassNames(ContextEl _context) {
        NatTreeMap<String,String> tr_ = new NatTreeMap<String,String>();
        return tr_;
    }

    @Override
    public void checkBlocksTree(ContextEl _cont) {
        Block prev_ = getPreviousSibling();
        boolean existIf_ = false;
        while (prev_ != null) {
            if (prev_ instanceof ElseIfCondition) {
                prev_ = prev_.getPreviousSibling();
                continue;
            }
            existIf_ = prev_ instanceof IfCondition;
            break;
        }
        if (!existIf_ || getFirstChild() == null) {
            PageEl page_ = _cont.getLastPage();
            page_.setProcessingAttribute(EMPTY_STRING);
            page_.setOffset(0);
            throw new BadElseIfException(_cont.joinPages());
        }
    }

    @Override
    boolean canBeIncrementedNextGroup() {
        return true;
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
    public String getTagName() {
        return TAG_ELSEIF;
    }

    @Override
    public void processEl(ContextEl _cont) {
        PageEl ip_ = _cont.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        IfBlockStack if_ = (IfBlockStack) ip_.getLastStack();
        if_.setVisitedBlock(getIndexInGroup());
        if (!if_.isEntered()) {
            boolean assert_ = evaluateCondition(_cont);
            if (assert_) {
                if_.setEntered(true);
                rw_.setBlock(getFirstChild());
                return;
            }
        }
        if (if_.getBlocks().last() == this) {
            ip_.removeLastBlock();
            processBlock(_cont);
            return;
        }
        rw_.setBlock(getNextSibling());
    }

    @Override
    public void exitStack(ContextEl _context) {
        PageEl ip_ = _context.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        IfBlockStack if_ = (IfBlockStack) ip_.getLastStack();
        if (if_.lastVisitedBlock() == this) {
            rw_.setBlock(this);
        } else {
            rw_.setBlock(getNextSibling());
        }
    }
}
