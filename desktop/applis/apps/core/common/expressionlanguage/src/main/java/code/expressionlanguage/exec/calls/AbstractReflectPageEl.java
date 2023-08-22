package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.Identifiable;
import code.util.CustList;

public abstract class AbstractReflectPageEl extends AbstractPageEl implements ForwardPageEl {

    private boolean wrapException;

    private final boolean lambda;

    protected AbstractReflectPageEl(boolean _lambda) {
        super(ExecFormattedRootBlock.defValue());
        lambda = _lambda;
    }
    @Override
    public void processTagsBase(ContextEl _context, StackCall _stack) {
        if (!checkCondition(_context, _stack)) {
            return;
        }
        _stack.nullReadWrite();
    }
    protected boolean checkParamsBase(ContextEl _context, StackCall _stack, Identifiable _id, CustList<Argument> _args, String _cl, Argument _a){
        if (!_stack.getStopper().isStopAtExcMethod()) {
            return false;
        }
        return ExecTemplates.checkParams(_context, _cl,_id, _a, _args, _stack) == null;
    }
    public boolean isWrapException() {
        return wrapException;
    }

    void setWrapException(boolean _wrapException) {
        if (_wrapException && lambda) {
            wrapException = false;
            return;
        }
        wrapException = _wrapException;
    }
    public abstract boolean checkCondition(ContextEl _context, StackCall _stack);
}
