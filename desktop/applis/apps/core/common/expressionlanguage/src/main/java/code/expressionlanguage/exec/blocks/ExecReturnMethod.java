package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.opers.ExecWrappOperation;
import code.expressionlanguage.exec.stacks.AbstractStask;
import code.expressionlanguage.exec.ExpressionLanguage;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.util.CustList;
import code.util.core.IndexConstants;

public final class ExecReturnMethod extends ExecLeaf implements MethodCallingFinally,WithNotEmptyEl {


    private final int expressionOffset;

    private final CustList<ExecOperationNode> opRet;
    private final String returnMethod;
    public ExecReturnMethod(int _expressionOffset, CustList<ExecOperationNode> _opRet, String _returnMethod, int _offsetTrim) {
        super(_offsetTrim);
        expressionOffset = _expressionOffset;
        opRet = _opRet;
        returnMethod = _returnMethod;
    }

    @Override
    public void removeBlockFinally(ContextEl _conf, StackCall _stack) {
        AbstractPageEl ip_ = _stack.getLastPage();
        while (ip_.hasBlock()) {
            AbstractStask bl_ = ip_.getLastStack();
            if (AbstractPageEl.setRemovedCallingFinallyToProcess(ip_,bl_,this,null)) {
                return;
            }
        }
        ip_.setNullReadWrite();
    }

    @Override
    public CustList<ExecOperationNode> getEl(ContextEl _context, int _indexProcess) {
        return opRet;
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        AbstractPageEl ip_ = _stack.getLastPage();
        if (opRet != null) {
            ip_.setOffset(0);
            ip_.setGlobalOffset(expressionOffset);
            ExpressionLanguage el_ = ip_.getCurrentEl(_cont,this, IndexConstants.FIRST_INDEX, IndexConstants.FIRST_INDEX);
            if (opRet.last() instanceof ExecWrappOperation) {
                ArgumentsPair argumentsPair_ = ExpressionLanguage.tryToCalculatePair(_cont, el_, 0, _stack);
                if (argumentsPair_ == null) {
                    return;
                }
                ip_.clearCurrentEls();
                ip_.setWrapper(argumentsPair_.getWrapper());
                ip_.setReturnedArgument(argumentsPair_.getArgument());
            } else {
                Argument arg_ = ExpressionLanguage.tryToCalculate(_cont,el_,0, _stack);
                if (_cont.callsOrException(_stack)) {
                    return;
                }
                ip_.clearCurrentEls();
                String type_ = _stack.formatVarType(returnMethod);
                if (!ExecTemplates.checkQuick(type_,arg_.getStruct().getClassName(_cont),_cont, _stack)) {
                    return;
                }
                ip_.setReturnedArgument(arg_);
            }
        }
        removeBlockFinally(_cont, _stack);
    }

}
