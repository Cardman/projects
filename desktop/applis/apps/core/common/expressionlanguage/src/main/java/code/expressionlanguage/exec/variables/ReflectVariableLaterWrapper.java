package code.expressionlanguage.exec.variables;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecArrayTemplates;
import code.expressionlanguage.exec.inherits.ExecVariableTemplates;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;

public final class ReflectVariableLaterWrapper extends AbstractVariableWrapper {

    private final ArrayStruct array;
    private final int ind;
    public ReflectVariableLaterWrapper(LocalVariable _local, ArrayStruct _arr, int _index) {
        super(_local);
        array = _arr;
        ind = _index;
    }

    @Override
    public Struct getValue(StackCall _stack, ContextEl _conf) {
        return getLocal().getStruct();
    }

    @Override
    public void setValue(StackCall _stack, ContextEl _conf, Argument _right) {
        ExecVariableTemplates.checkSet(_conf, getLocal(),_right, _stack);
    }

    @Override
    public String getClassName(StackCall _stack, ContextEl _conf) {
        return getLocal().getStruct().getClassName(_conf);
    }

    public void apply(StackCall _stack, ContextEl _conf) {
        ExecArrayTemplates.setElement(array,new IntStruct(ind),getLocal().getStruct(),_conf,_stack);
    }
}
