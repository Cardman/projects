package code.expressionlanguage.methods;
import org.w3c.dom.Element;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.ReadWrite;
import code.expressionlanguage.methods.exceptions.BadLoopException;
import code.expressionlanguage.stacks.LoopBlockStack;
import code.util.NatTreeMap;

public final class WhileCondition extends Condition implements Loop, IncrNextGroup {

    public WhileCondition(Element _el, ContextEl _importingPage, int _indexChild,
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
//        page_.setProcessingNode(getAssociateElement());
        page_.setProcessingAttribute(EMPTY_STRING);
//        page_.setLookForAttrValue(false);
        page_.setOffset(0);
        if (getFirstChild() == null && !(getPreviousSibling() instanceof DoBlock)) {
            throw new BadLoopException(_cont.joinPages());
        }
    }

//    @Override
//    public void buildExpressionLanguage(ContextEl _cont) {
//        PageEl page_ = _cont.getLastPage();
//        page_.setProcessingNode(getAssociateElement());
//        page_.setProcessingAttribute(EMPTY_STRING);
//        page_.setLookForAttrValue(false);
//        page_.setOffset(0);
//        if (getFirstChild() == null && !(getPreviousSibling() instanceof DoBlock)) {
//            throw new BadLoopException(_cont.joinPages(RETURN_LINE));
//        }
//        super.buildExpressionLanguage(_cont);
//    }

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
    public String getTagName() {
        return TAG_WHILE;
    }

    @Override
    public void processEl(ContextEl _cont) {
        PageEl ip_ = _cont.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        LoopBlockStack c_ = null;
        if (!ip_.noBlock() && ip_.getLastStack() instanceof LoopBlockStack) {
            c_ = (LoopBlockStack) ip_.getLastStack();
        }
        if (c_ != null && c_.getBlock() == this) {
            if (c_.isEvaluatingKeepLoop()) {
//                ProcessXmlMethod.processLastElementLoop(_cont, ip_);
                processLastElementLoop(_cont);
                return;
            }
            if (c_.isFinished()) {
//                ProcessXmlMethod.removeVarAndLoop(_cont, c_.getBlock(), ip_.getVars());
                removeVarAndLoop(ip_);
                processBlock(_cont);
                return;
            }
            rw_.setBlock(getFirstChild());
            return;
        }
//        if (getFirstChild() == null) {
//            while (evaluateCondition(_cont)) {
//                continue;
//            }
//            processBlock(_cont, ip_);
//            return;
//        }
        boolean res_ = evaluateCondition(_cont);
        LoopBlockStack l_ = new LoopBlockStack();
        l_.setBlock(this);
        l_.setFinished(!res_);
//        _ip.getBlockStacks().add(l_);
        ip_.addBlock(l_);
//        ProcessXmlMethod.processWhile(_cont, ip_);
//        c_ = (LoopBlockStack) st_.last();
        c_ = (LoopBlockStack) ip_.getLastStack();
        if (c_.isFinished()) {
//            st_.removeLast();
            ip_.removeLastBlock();
            processBlock(_cont);
            return;
        }
        rw_.setBlock(getFirstChild());
    }

    @Override
    public void exitStack(ContextEl _context) {
//        PageEl ip_ = _context.getLastPage();
//        ProcessXmlMethod.processLastElementLoop(_context, ip_);
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

    @Override
    public boolean keepLoop(ContextEl _conf) {
        _conf.getLastPage().setProcessingAttribute(EMPTY_STRING);
        _conf.getLastPage().setOffset(0);
        return evaluateCondition(_conf);
    }
}
