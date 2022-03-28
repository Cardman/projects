package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.inherits.ExecInheritsAdv;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;

public final class ExecReturnMethod extends ExecAbstractExpressionReturnMethod {


    private final String returnMethod;
    public ExecReturnMethod(int _expressionOffset, CustList<ExecOperationNode> _opRet, String _returnMethod) {
        super(_expressionOffset,_opRet);
        returnMethod = _returnMethod;
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        AbstractPageEl ip_ = _stack.getLastPage();
        ip_.globalOffset(getExpressionOffset());
        Argument arg_ = ExecHelperBlocks.tryToCalculate(_cont,0, _stack,getExp(),0, this);
        if (_cont.callsOrException(_stack)) {
            return;
        }
        ip_.clearCurrentEls();
        String type_ = _stack.formatVarType(returnMethod);
        if (!ExecInheritsAdv.checkQuick(type_,arg_.getStruct().getClassName(_cont),_cont, _stack)) {
            return;
        }
        ip_.setReturnedArgument(arg_);
        tryReturn(_stack);
    }

}
