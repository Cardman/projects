package code.expressionlanguage.methods;
import org.w3c.dom.Element;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.ReadWrite;
import code.expressionlanguage.methods.exceptions.BadTagBreakException;
import code.expressionlanguage.stacks.BreakableBlockStack;
import code.expressionlanguage.stacks.RemovableVars;
import code.expressionlanguage.stacks.TryBlockStack;
import code.util.NatTreeMap;

public final class BreakBlock extends Leaf implements CallingFinally {

    public BreakBlock(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        setStoppable(true);
    }

    @Override
    public NatTreeMap<String,String> getClassNames() {
        NatTreeMap<String,String> tr_ = new NatTreeMap<String,String>();
        return tr_;
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        boolean childOfBreakable_ = false;
        BracedBlock b_ = getParent();
        while (b_ != null) {
            if (b_ instanceof CaseCondition) {
                ((CaseCondition)b_).setPossibleSkipNexts(true);
            }
            if (b_ instanceof BreakableBlock) {
                childOfBreakable_ = true;
                break;
            }
            b_ = b_.getParent();
        }
        if (!childOfBreakable_) {
            PageEl page_ = _cont.getLastPage();
//            page_.setProcessingNode(getAssociateElement());
            page_.setProcessingAttribute(EMPTY_STRING);
//            page_.setLookForAttrValue(false);
            page_.setOffset(0);
            throw new BadTagBreakException(_cont.joinPages());
        }
//        removeLocalVariablesFromParent();
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
        return TAG_BREAK;
    }

    @Override
    public void processEl(ContextEl _cont) {
        PageEl ip_ = _cont.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        BreakableBlockStack stack_ = null;
        while (true) {
//            BlockStack blStack_ = l_.last();
            RemovableVars blStack_ = ip_.getLastStack();
            if (blStack_ instanceof BreakableBlockStack) {
                stack_ = (BreakableBlockStack) blStack_;
//                ProcessXmlMethod.removeLocalVars(ip_, blStack_.getBlock());
                blStack_.getBlock().removeLocalVars(ip_);
                break;
            }
//            if (blStack_ instanceof TryBlockStack) {
//                TryBlockStack tryStack_ = (TryBlockStack) blStack_;
//                int index_ = tryStack_.getVisitedCatch();
//                if (index_ >= List.FIRST_INDEX) {
//                    BracedBlock catchBlock_ = tryStack_.getCatchBlocks().get(index_);
//                    if (catchBlock_ instanceof CatchEval) {
//                        String var_ = ((CatchEval)catchBlock_).getVariableName();
//                        ip_.getCatchVars().removeKey(var_);
//                    }
////                    ProcessXmlMethod.removeLocalVars(ip_, catchBlock_);
//                    catchBlock_.removeLocalVars(ip_);
//                } else {
////                    ProcessXmlMethod.removeLocalVars(ip_, tryStack_.getBlock());
//                    tryStack_.getBlock().removeLocalVars(ip_);
//                }
//                ip_.removeLastBlock();
//            }
            ip_.setFinallyToProcess(false);
            blStack_.removeVarAndLoop(ip_);
            if (ip_.isFinallyToProcess()) {
//                ip_.setReturning(this);
                ((TryBlockStack)blStack_).setCalling(this);
                return;
            }
//            if (blStack_ instanceof RemovableVars) {
//                ((RemovableVars)blStack_).removeVarAndLoop(ip_);
//            }
//            if (blStack_ instanceof IfBlockStack) {
//                IfBlockStack t_ = (IfBlockStack) blStack_;
//                BracedBlock cur_ = t_.getCurentVisitedBlock();
////                ProcessXmlMethod.removeLocalVars(ip_, cur_);
//                cur_.removeLocalVars(ip_);
//            }
////            if (blStack_ instanceof SwitchBlockStack) {
////                SwitchBlockStack t_ = (SwitchBlockStack) blStack_;
////                Block cur_ = t_.getCurentVisitedBlock();
////                removeLocalVars(ip_, cur_);
////            }
////            l_.removeLast();
//            ip_.removeLastBlock();
        }
        Block forLoopLoc_ = stack_.getBlock();
        rw_.setBlock(forLoopLoc_);
        stack_.setFinished(true);
    }

    @Override
    public void removeBlockFinally(ContextEl _conf) {
        PageEl ip_ = _conf.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        BreakableBlockStack stack_ = null;
        while (!ip_.noBlock()) {
            RemovableVars bl_ = ip_.getLastStack();
            ip_.setFinallyToProcess(false);
            if (bl_ instanceof BreakableBlockStack) {
                stack_ = (BreakableBlockStack) bl_;
                bl_.getBlock().removeLocalVars(ip_);
                break;
            }
            bl_.removeVarAndLoop(ip_);
            if (ip_.isFinallyToProcess()) {
                ((TryBlockStack)bl_).setCalling(this);
                return;
            }
        }
        if (stack_ != null) {
//            ip_.setReturning(null);
            Block forLoopLoc_ = stack_.getBlock();
            rw_.setBlock(forLoopLoc_);
            stack_.setFinished(true);
        }
    }

}
