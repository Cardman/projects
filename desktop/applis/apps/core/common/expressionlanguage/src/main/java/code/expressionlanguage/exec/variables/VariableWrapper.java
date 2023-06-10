package code.expressionlanguage.exec.variables;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecVariableTemplates;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.Struct;

public final class VariableWrapper extends AbstractVariableWrapper {

    public VariableWrapper(LocalVariable _local) {
        super(_local);
    }

    @Override
    public void setValue(StackCall _stack, ContextEl _conf, Argument _right) {
        setValue(ArgumentListCall.toStr(_right));
        ExecVariableTemplates.checkSet(_conf, getLocal(),_right, _stack);
    }

    @Override
    public Struct getValue(StackCall _stack, ContextEl _conf) {
        return getLocal().getStruct();
    }

    @Override
    public String getClassName(StackCall _stack, ContextEl _conf) {
        return getLocal().getClassName();
    }
}
