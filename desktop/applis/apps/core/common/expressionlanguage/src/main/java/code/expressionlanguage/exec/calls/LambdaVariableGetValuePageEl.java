package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.CheckedExecOperationNodeInfos;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.dbg.DbgStackStopper;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.FieldWrapper;
import code.expressionlanguage.structs.Struct;

public final class LambdaVariableGetValuePageEl extends AbstractLambdaVariable {

    private final ArgumentListCall arr;
    public LambdaVariableGetValuePageEl(ArgumentListCall _array) {
        super(true);
        arr = _array;
    }

    Argument prepare(ContextEl _context, StackCall _stack) {
        ArgumentWrapper firstArgumentWrapper_ = ExecHelper.getFirstArgumentWrapper(arr.getArgumentWrappers());
        return new Argument(ExecTemplates.getWrap(firstArgumentWrapper_.getWrapper()).getValue(_stack,_context));
    }

    @Override
    boolean stopAt(ContextEl _context, StackCall _stack) {
        return _stack.getStopper().isStopAtRefVar(arr,_context,_stack);
    }

    @Override
    public CheckedExecOperationNodeInfos infos(ContextEl _context, StackCall _stackCall) {
        ArgumentWrapper firstArgumentWrapper_ = ExecHelper.getFirstArgumentWrapper(arr.getArgumentWrappers());
        AbstractWrapper w_ = firstArgumentWrapper_.getWrapper();
        if (w_ instanceof FieldWrapper) {
            _stackCall.getBreakPointInfo().getStackState().resetVisit(true);
            ClassField cf_ = ((FieldWrapper) w_).getId();
            Struct instance_ = value(w_);
            return new CheckedExecOperationNodeInfos(cf_, DbgStackStopper.READ,formatted(_context, (FieldWrapper) w_,cf_),instance_,null);
        }
        return null;
    }
}
