package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.AbstractStask;
import code.expressionlanguage.exec.ExpressionLanguage;
import code.util.CustList;

public final class ExecReturnMethod extends ExecLeaf implements MethodCallingFinally,WithNotEmptyEl {

    private boolean empty;

    private int expressionOffset;

    private CustList<ExecOperationNode> opRet;
    private String returnMethod;
    public ExecReturnMethod(boolean _empty, int _expressionOffset, CustList<ExecOperationNode> _opRet, String _returnMethod, int _offsetTrim) {
        super(_offsetTrim);
        empty = _empty;
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
        return getElRet();
    }

    @Override
    public void reduce(ContextEl _context) {
        if (opRet == null) {
            return;
        }
        ExecOperationNode r_ = opRet.last();
        opRet = ExpressionLanguage.getReducedNodes(r_);
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        if (!isEmpty()) {
            ip_.setOffset(0);
            ip_.setGlobalOffset(expressionOffset);
            ExpressionLanguage el_ = ip_.getCurrentEl(_cont,this, CustList.FIRST_INDEX, CustList.FIRST_INDEX);
            Argument arg_ = ExpressionLanguage.tryToCalculate(_cont,el_,0);
            if (_cont.callsOrException()) {
                return;
            }
            ip_.clearCurrentEls();
            String type_ = _cont.formatVarType(returnMethod);
            if (!ExecTemplates.checkQuick(type_,arg_,_cont)) {
                return;
            }
            _cont.getLastPage().setReturnedArgument(arg_);
        }
        removeBlockFinally(_cont);
    }

    public boolean isEmpty() {
        return empty;
    }

    public ExpressionLanguage getElRet() {
        return new ExpressionLanguage(opRet);
    }

}
