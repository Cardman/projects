package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.AbstractStask;
import code.expressionlanguage.exec.ExpressionLanguage;
import code.util.CustList;
import code.util.core.IndexConstants;

public final class ExecReturnMethod extends ExecLeaf implements MethodCallingFinally,WithNotEmptyEl {


    private int expressionOffset;

    private CustList<ExecOperationNode> opRet;
    private String returnMethod;
    public ExecReturnMethod(int _expressionOffset, CustList<ExecOperationNode> _opRet, String _returnMethod, int _offsetTrim) {
        super(_offsetTrim);
        expressionOffset = _expressionOffset;
        opRet = _opRet;
        returnMethod = _returnMethod;
    }

    @Override
    public void removeBlockFinally(ContextEl _conf) {
        AbstractPageEl ip_ = _conf.getLastPage();
        while (ip_.hasBlock()) {
            AbstractStask bl_ = ip_.getLastStack();
            if (AbstractPageEl.setRemovedCallingFinallyToProcess(ip_,bl_,this,null)) {
                return;
            }
        }
        ip_.setNullReadWrite();
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context, int _indexProcess) {
        return new ExpressionLanguage(opRet);
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        if (opRet != null) {
            ip_.setOffset(0);
            ip_.setGlobalOffset(expressionOffset);
            ExpressionLanguage el_ = ip_.getCurrentEl(_cont,this, IndexConstants.FIRST_INDEX, IndexConstants.FIRST_INDEX);
            Argument arg_ = ExpressionLanguage.tryToCalculate(_cont,el_,0);
            if (_cont.callsOrException()) {
                return;
            }
            ip_.clearCurrentEls();
            String type_ = _cont.formatVarType(returnMethod);
            if (!ExecTemplates.checkQuick(type_,arg_,_cont)) {
                return;
            }
            ip_.setReturnedArgument(arg_);
        }
        removeBlockFinally(_cont);
    }

}
