package code.expressionlanguage.methods;
import org.w3c.dom.Element;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.methods.exceptions.BadTagContinueException;
import code.expressionlanguage.stacks.LoopBlockStack;
import code.expressionlanguage.stacks.RemovableVars;
import code.expressionlanguage.stacks.TryBlockStack;
import code.util.NatTreeMap;

public final class ContinueBlock extends Leaf implements CallingFinally {

    public ContinueBlock(Element _el, ContextEl _importingPage, int _indexChild,
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
        boolean childOfLoop_ = false;
        BracedBlock b_ = getParent();
        while (b_ != null) {
            if (b_ instanceof Loop) {
                childOfLoop_ = true;
                break;
            }
            b_ = b_.getParent();
        }
        if (!childOfLoop_) {
            PageEl page_ = _cont.getLastPage();
//            page_.setProcessingNode(getAssociateElement());
            page_.setProcessingAttribute(EMPTY_STRING);
//            page_.setLookForAttrValue(false);
            page_.setOffset(0);
            throw new BadTagContinueException(_cont.joinPages());
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
        return TAG_CONTINUE;
    }

    @Override
    public void processEl(ContextEl _cont) {
        PageEl ip_ = _cont.getLastPage();
        Loop loop_ = null;
        while (true) {
//            BlockStack blStack_ = l_.last();
            RemovableVars blStack_ = ip_.getLastStack();
            if (blStack_ instanceof LoopBlockStack) {
//                ProcessXmlMethod.removeLocalVars(ip_, blStack_.getBlock());
                BracedBlock br_ = blStack_.getBlock();
                loop_ = (Loop) br_;
                br_.removeLocalVars(ip_);
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
//            if (blStack_ instanceof RemovableVars) {
//                ((RemovableVars)blStack_).removeVarAndLoop(ip_);
//            }
            ip_.setFinallyToProcess(false);
            blStack_.removeVarAndLoop(ip_);
            if (ip_.isFinallyToProcess()) {
//                ip_.setReturning(this);
                ((TryBlockStack)blStack_).setCalling(this);
                return;
            }
//            if (blStack_ instanceof IfBlockStack) {
//                IfBlockStack t_ = (IfBlockStack) blStack_;
//                BracedBlock cur_ = t_.getCurentVisitedBlock();
////                ProcessXmlMethod.removeLocalVars(ip_, cur_);
//                cur_.removeLocalVars(ip_);
//            }
//            if (blStack_ instanceof SwitchBlockStack) {
//                SwitchBlockStack t_ = (SwitchBlockStack) blStack_;
//                BracedBlock cur_ = t_.getCurentVisitedBlock();
////                ProcessXmlMethod.removeLocalVars(ip_, cur_);
//                cur_.removeLocalVars(ip_);
//            }
////            l_.removeLast();
//            ip_.removeLastBlock();
        }
        loop_.processLastElementLoop(_cont);
//        if (loop_ != null) {
//            loop_.processLastElementLoop(_cont);
//        }
//        ProcessXmlMethod.processLastElementLoop(_cont, ip_);
    }

    @Override
    public void removeBlockFinally(ContextEl _conf) {
        PageEl ip_ = _conf.getLastPage();
        Loop loop_ = null;
        while (!ip_.noBlock()) {
            RemovableVars bl_ = ip_.getLastStack();
            ip_.setFinallyToProcess(false);
            if (bl_ instanceof LoopBlockStack) {
                BracedBlock br_ = bl_.getBlock();
                loop_ = (Loop) br_;
                br_.removeLocalVars(ip_);
                break;
            }
            ip_.setFinallyToProcess(false);
            bl_.removeVarAndLoop(ip_);
            if (ip_.isFinallyToProcess()) {
                ((TryBlockStack)bl_).setCalling(this);
                return;
            }
        }
        if (loop_ != null) {
//            ip_.setReturning(null);
            loop_.processLastElementLoop(_conf);
        }
    }
}
