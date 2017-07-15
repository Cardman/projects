package code.expressionlanguage.methods;
import org.w3c.dom.Element;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.ReadWrite;
import code.expressionlanguage.methods.exceptions.BadIfException;
import code.expressionlanguage.stacks.IfBlockStack;
import code.expressionlanguage.stacks.RemovableVars;
import code.util.CustList;
import code.util.NatTreeMap;

public final class IfCondition extends Condition implements BlockCondition, IncrCurrentGroup {

    public IfCondition(Element _el, ContextEl _importingPage, int _indexChild,
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
        if (getFirstChild() == null) {
            PageEl page_ = _cont.getLastPage();
//            page_.setProcessingNode(getAssociateElement());
            page_.setProcessingAttribute(EMPTY_STRING);
//            page_.setLookForAttrValue(false);
            page_.setOffset(0);
            throw new BadIfException(_cont.joinPages());
        }
    }

//    @Override
//    public void buildExpressionLanguage(ContextEl _cont) {
//        super.buildExpressionLanguage(_cont);
////        removeLocalVariablesFromParent();
//    }

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
    public String getTagName() {
        return TAG_IF;
    }

    @Override
    public void processEl(ContextEl _cont) {
        PageEl ip_ = _cont.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        if (!ip_.noBlock()) {
//            BlockStack bl_ = l_.last();
            RemovableVars bl_ = ip_.getLastStack();
            if (bl_.getBlock() == this) {
//                l_.removeLast();
                ip_.removeLastBlock();
                processBlock(_cont);
                return;
            }
        }
        IfBlockStack if_ = new IfBlockStack();
        if_.getBlocks().add(this);
        Block n_ = getNextSibling();
        while (n_ != null) {
            if (!(n_ instanceof BlockCondition)) {
                break;
            }
            if_.getBlocks().add((BracedBlock)n_);
            n_ = n_.getNextSibling();
        }
        if_.setBlock(this);
        if_.setVisitedBlock(CustList.FIRST_INDEX);
        boolean assert_ = evaluateCondition(_cont);
        if (assert_) {
//            if (hasChildNodes()) {
//                ip_.addBlock(if_);
////                l_.add(if_);
//                if_.setEntered(true);
//            } else {
//                if (if_.getBlocks().size() > CustList.ONE_ELEMENT) {
//                    if_.setEntered(true);
//                    ip_.addBlock(if_);
////                    l_.add(if_);
//                    rw_.setBlock(getNextSibling());
//                    return;
//                }
//            }
            ip_.addBlock(if_);
//            l_.add(if_);
            if_.setEntered(true);
            rw_.setBlock(getFirstChild());
//            processAfterBlock(_cont, ip_);
        } else {
            ip_.addBlock(if_);
//            l_.add(if_);
            if (if_.lastVisitedBlock() == this) {
                return;
            }
            rw_.setBlock(getNextSibling());
        }
    }

    @Override
    public void exitStack(ContextEl _context) {
        PageEl ip_ = _context.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        IfBlockStack if_ = (IfBlockStack) ip_.getLastStack();
        if (if_.lastVisitedBlock() == this) {
            rw_.setBlock(this);
        } else {
//            if_.increment();
            rw_.setBlock(getNextSibling());
        }
    }
}
