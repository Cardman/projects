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
import code.util.CustList;

public final class LambdaVariableSetValuePageEl extends AbstractLambdaVariable {

    private final ArgumentListCall arr;
    public LambdaVariableSetValuePageEl(ArgumentListCall _array) {
        super(true);
        arr = _array;
    }

    Argument prepare(ContextEl _context, StackCall _stack) {
        CustList<ArgumentWrapper> argumentWrappers_ = arr.getArgumentWrappers();
        ArgumentWrapper firstArgumentWrapper_ = ExecHelper.getFirstArgumentWrapper(argumentWrappers_);
        Argument right_ = ArgumentWrapper.helpArg(ExecHelper.getLastArgumentWrapper(argumentWrappers_));
        ExecTemplates.getWrap(firstArgumentWrapper_.getWrapper()).setValue(_stack,_context,right_);
        return right_;
    }

    @Override
    boolean stopAt(ContextEl _context, StackCall _stack) {
        return _stack.getStopper().isStopAtRefVar(arr,_context,_stack);
    }

    @Override
    public CheckedExecOperationNodeInfos infos(ContextEl _context, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = arr.getArgumentWrappers();
        ArgumentWrapper firstArgumentWrapper_ = ExecHelper.getFirstArgumentWrapper(argumentWrappers_);
        AbstractWrapper w_ = firstArgumentWrapper_.getWrapper();
        if (w_ instanceof FieldWrapper) {
            ClassField cf_ = ((FieldWrapper) w_).getId();
            Struct right_ = ArgumentListCall.toStr(ArgumentWrapper.helpArg(ExecHelper.getLastArgumentWrapper(argumentWrappers_)));
            Struct instance_ = value(w_);
            return new CheckedExecOperationNodeInfos(cf_, DbgStackStopper.WRITE,formatted(_context, (FieldWrapper) w_,cf_),instance_,right_);
        }
        return null;
    }
}
