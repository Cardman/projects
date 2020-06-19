package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.AbruptCallingFinally;
import code.expressionlanguage.exec.stacks.RemovableVars;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.methods.CallingFinally;
import code.expressionlanguage.methods.WithNotEmptyEl;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class ExecReturnMethod extends ExecLeaf implements CallingFinally,WithNotEmptyEl {

    private boolean empty;

    private int expressionOffset;

    private CustList<ExecOperationNode> opRet;
    private String returnMethod;
    public ExecReturnMethod(OffsetsBlock _offset, boolean _empty, int _expressionOffset, CustList<ExecOperationNode> _opRet, String _returnMethod) {
        super(_offset);
        empty = _empty;
        expressionOffset = _expressionOffset;
        opRet = _opRet;
        returnMethod = _returnMethod;
    }

    @Override
    public void removeBlockFinally(ContextEl _conf) {
        AbstractPageEl ip_ = _conf.getLastPage();
        while (ip_.hasBlock()) {
            RemovableVars bl_ = ip_.getLastStack();
            if (AbstractPageEl.setRemovedCallingFinallyToProcess(ip_,bl_,this,null)) {
                return;
            }
        }
        ip_.setNullReadWrite();
    }

    @Override
    public AbruptCallingFinally newAbruptCallingFinally(Struct _struct) {
        return new AbruptCallingFinally(this);
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
        opRet = ElUtil.getReducedNodes(r_);
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        if (!isEmpty()) {
            ip_.setOffset(0);
            ip_.setGlobalOffset(expressionOffset);
            ExpressionLanguage el_ = ip_.getCurrentEl(_cont,this, CustList.FIRST_INDEX, CustList.FIRST_INDEX);
            Argument arg_ = ElUtil.tryToCalculate(_cont,el_,0);
            if (_cont.callsOrException()) {
                return;
            }
            ip_.clearCurrentEls();
            String type_ = ip_.formatVarType(returnMethod,_cont);
            if (!Templates.checkQuick(type_,arg_,_cont)) {
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

    public CustList<ExecOperationNode> getOpRet() {
        return opRet;
    }
}
