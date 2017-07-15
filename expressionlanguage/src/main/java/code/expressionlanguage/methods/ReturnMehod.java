package code.expressionlanguage.methods;
import org.w3c.dom.Element;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.ReadWrite;
import code.expressionlanguage.exceptions.DynamicCastClassException;
import code.expressionlanguage.methods.exceptions.BadConstructorCall;
import code.expressionlanguage.methods.exceptions.BadReturnException;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.stacks.RemovableVars;
import code.expressionlanguage.stacks.TryBlockStack;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringList;

public final class ReturnMehod extends Leaf implements CallingFinally {

    private final String expression;

//    private ExpressionLanguage elRet;

    private CustList<OperationNode> opRet;

    public ReturnMehod(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        expression = _el.getAttribute(ATTRIBUTE_EXPRESSION);
        setExitable(true);
        setStoppable(true);
    }

    @Override
    public void checkBlocksTree(ContextEl _cont) {
        BracedBlock b_ = getParent();
        while (b_ != null) {
            if (b_ instanceof FunctionBlock) {
                return;
            }
            b_ = b_.getParent();
        }
        throw new BadReturnException(_cont.joinPages());
    }

    @Override
    public NatTreeMap<String,String> getClassNames() {
        NatTreeMap<String,String> tr_ = new NatTreeMap<String,String>();
        return tr_;
    }

    public boolean isEmpty() {
        return expression.isEmpty();
    }

    public String getExpression() {
        return expression;
    }

    public ExpressionLanguage getElRet() {
//        return new ExpressionLanguage(expression, _cont, true, new Calculation(StepCalculation.RIGHT));
        return new ExpressionLanguage(opRet);
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        FunctionBlock f_ = getFunction();
        PageEl page_ = _cont.getLastPage();
//        page_.setProcessingNode(getAssociateElement());
        page_.setProcessingAttribute(EMPTY_STRING);
//        page_.setLookForAttrValue(false);
        page_.setOffset(0);
        if (getNextSibling() != null) {
            throw new BadReturnException(_cont.joinPages());
        }
        String retType_ = OperationNode.VOID_RETURN;
        BracedBlock par_ = getParent();
        while (par_ != null) {
            if (par_ instanceof Returnable) {
                Returnable meth_ = null;
                meth_ = (Returnable) par_;
                retType_ = meth_.getReturnType();
                break;
            }
            par_ = par_.getParent();
        }
//        String retType_ = meth_.getReturnType();
//        page_.setProcessingNode(getAssociateElement());
        page_.setProcessingAttribute(ATTRIBUTE_EXPRESSION);
        page_.setOffset(0);
        if (StringList.quickEq(retType_, OperationNode.VOID_RETURN)) {
            if (!expression.isEmpty()) {
//                page_.setLookForAttrValue(false);
                throw new BadReturnException(_cont.joinPages());
            }
        } else {
//            page_.setLookForAttrValue(true);
//            opRet = ElUtil.getAnalyzedOperations(expression, _cont, f_.isStaticContext());
            opRet = ElUtil.getAnalyzedOperations(expression, _cont, Calculation.staticCalculation(f_.isStaticContext()));
//            elRet = new ExpressionLanguage(expression, _cont, true, new Calculation(StepCalculation.RIGHT));
            if (!PrimitiveTypeUtil.canBeUseAsArgument(retType_, opRet.last().getResultClass().getName(), _cont.getClasses())) {
                throw new DynamicCastClassException(_cont.joinPages());
            }
        }
//        removeLocalVariablesFromParent();
    }

    @Override
    boolean canBeLastOfBlockGroup() {
        return false;
    }

    @Override
    public void checkCallConstructor(ContextEl _cont) {
        if (opRet == null) {
            return;
        }
        PageEl p_ = _cont.getLastPage();
//        p_.setProcessingNode(getAssociateElement());
        p_.setProcessingAttribute(ATTRIBUTE_EXPRESSION);
//        p_.setLookForAttrValue(true);
        for (OperationNode o: opRet) {
            if (o.isSuperThis()) {
                int off_ = o.getFullIndexInEl();
                p_.setOffset(off_);
                throw new BadConstructorCall(_cont.joinPages());
            }
        }
    }

    @Override
    public String getTagName() {
        return TAG_RETURN;
    }

    @Override
    public void processEl(ContextEl _cont) {
        PageEl ip_ = _cont.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        if (!isEmpty()) {
            ip_.setOffset(0);
            ip_.setProcessingAttribute(ATTRIBUTE_EXPRESSION);
            ExpressionLanguage el_;
            if (!ip_.getCurrentEls().isEmpty()) {
                el_ = ip_.getCurrentEls().last();
            } else {
                el_ = getElRet();
                ip_.setCurrentBlock(this);
                ip_.setCurrentEls(new CustList<ExpressionLanguage>(el_));
            }
            Argument arg_ = el_.calculateMember(_cont);
            el_.setCurrentOper(null);
            ip_.getCurrentEls().clear();
//            ip_.setReturning(this);
            _cont.getLastPage().setReturnedArgument(arg_);
        } else {
//            ip_.setReturning(this);
            FunctionBlock f_ = getFunction();
            if (f_ instanceof MethodBlock) {
                Argument void_ = Argument.createVoid();
                _cont.getLastPage().setReturnedArgument(void_);
            } else if (f_ instanceof ConstructorBlock) {
                _cont.getLastPage().setArgumentForConstructor();
            } else if (f_ instanceof AloneBlock) {
//                ProcessXmlMethod.removeBlockFinally(_cont, ip_);
                removeBlockFinally(_cont);
                if (ip_.getReadWrite() == null) {
                    Block bn_ = ((AloneBlock)f_).getNextSibling();
                    if (bn_ != null) {
//                        ip_.setReturning(null);
                        rw_.setBlock(bn_);
                        ip_.setReadWrite(rw_);
                    }
                }
                return;
            }
        }
//        ProcessXmlMethod.removeBlockFinally(_cont, ip_);
        removeBlockFinally(_cont);
    }

    @Override
    public void removeBlockFinally(ContextEl _conf) {
        PageEl ip_ = _conf.getLastPage();
//        ReadWrite rw_ = ip_.getReadWrite();
        while (!ip_.noBlock()) {
            RemovableVars bl_ = ip_.getLastStack();
//            if (bl_ instanceof TryBlockStack) {
//                TryBlockStack t_ = (TryBlockStack) bl_;
//                boolean isFinallyBlock_ = false;
//                if (t_.getVisitedCatch() >= List.FIRST_INDEX) {
//                    BracedBlock catchBlock_ = t_.getCurrentCatchBlock();
//                    if (catchBlock_ instanceof CatchEval) {
//                        String var_ = ((CatchEval)catchBlock_).getVariableName();
//                        ip_.getCatchVars().removeKey(var_);
//                    } else {
//                        isFinallyBlock_ = true;
//                    }
//                    catchBlock_.removeLocalVars(ip_);
//                } else {
//                    t_.getBlock().removeLocalVars(ip_);
//                }
//                if (isFinallyBlock_) {
//                    ip_.removeLastBlock();
//                    continue;
//                }
//                if (t_.getLastCatchBlock() instanceof FinallyEval) {
//                    ip_.getCurrentEls().clear();
//                    rw_.setBlock(t_.getLastCatchBlock());
//                    return;
//                }
//                ip_.removeLastBlock();
//            } else {
//                ((RemovableVars)bl_).removeVarAndLoop(ip_);
//            }
            ip_.setFinallyToProcess(false);
            bl_.removeVarAndLoop(ip_);
            if (ip_.isFinallyToProcess()) {
                ((TryBlockStack)bl_).setCalling(this);
                return;
            }
//            if (bl_ instanceof LoopBlockStack){
//                LoopBlockStack loopStack_ = (LoopBlockStack) bl_;
//                BracedBlock forNode_ = loopStack_.getBlock();
//                forNode_.removeLocalVars(ip_);
//                forNode_.removeVarAndLoop(ip_);
//            } else {
//                if (bl_ instanceof IfBlockStack) {
//                    IfBlockStack t_ = (IfBlockStack) bl_;
//                    BracedBlock cur_ = t_.getCurentVisitedBlock();
//                    cur_.removeLocalVars(ip_);
//                }
//                if (bl_ instanceof SwitchBlockStack) {
//                    SwitchBlockStack t_ = (SwitchBlockStack) bl_;
//                    BracedBlock cur_ = t_.getCurentVisitedBlock();
//                    cur_.removeLocalVars(ip_);
//                }
//                ip_.removeLastBlock();
//            }
        }
        ip_.setNullReadWrite();
    }
}
