package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.ExpressionLanguage;
import code.util.CustList;
import code.util.core.IndexConstants;

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
        ExpressionLanguage el_ = ip_.getCurrentEl(_cont,this, IndexConstants.FIRST_INDEX, IndexConstants.FIRST_INDEX);
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
        tryReturn(_stack);
    }

}
