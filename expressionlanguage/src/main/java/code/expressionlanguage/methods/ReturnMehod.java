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
        return new ExpressionLanguage(opRet);
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        FunctionBlock f_ = getFunction();
        PageEl page_ = _cont.getLastPage();
        page_.setProcessingAttribute(EMPTY_STRING);
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
        page_.setProcessingAttribute(ATTRIBUTE_EXPRESSION);
        page_.setOffset(0);
        if (StringList.quickEq(retType_, OperationNode.VOID_RETURN)) {
            if (!expression.isEmpty()) {
                throw new BadReturnException(_cont.joinPages());
            }
        } else {
            opRet = ElUtil.getAnalyzedOperations(expression, _cont, Calculation.staticCalculation(f_.isStaticContext()));
            if (!PrimitiveTypeUtil.canBeUseAsArgument(retType_, opRet.last().getResultClass().getName(), _cont.getClasses())) {
                throw new DynamicCastClassException(_cont.joinPages());
            }
        }
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
        p_.setProcessingAttribute(ATTRIBUTE_EXPRESSION);
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
            _cont.getLastPage().setReturnedArgument(arg_);
        } else {
            FunctionBlock f_ = getFunction();
            if (f_ instanceof MethodBlock) {
                Argument void_ = Argument.createVoid();
                _cont.getLastPage().setReturnedArgument(void_);
            } else if (f_ instanceof ConstructorBlock) {
                _cont.getLastPage().setArgumentForConstructor();
            } else if (f_ instanceof AloneBlock) {
                removeBlockFinally(_cont);
                if (ip_.getReadWrite() == null) {
                    Block bn_ = ((AloneBlock)f_).getNextSibling();
                    if (bn_ != null) {
                        rw_.setBlock(bn_);
                        ip_.setReadWrite(rw_);
                    }
                }
                return;
            }
        }
        removeBlockFinally(_cont);
    }

    @Override
    public void removeBlockFinally(ContextEl _conf) {
        PageEl ip_ = _conf.getLastPage();
        while (!ip_.noBlock()) {
            RemovableVars bl_ = ip_.getLastStack();
            ip_.setFinallyToProcess(false);
            bl_.removeVarAndLoop(ip_);
            if (ip_.isFinallyToProcess()) {
                ((TryBlockStack)bl_).setCalling(this);
                return;
            }
        }
        ip_.setNullReadWrite();
    }
}
