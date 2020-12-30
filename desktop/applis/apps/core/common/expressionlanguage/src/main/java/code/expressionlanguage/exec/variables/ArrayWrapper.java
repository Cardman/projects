package code.expressionlanguage.exec.variables;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.structs.Struct;

public final class ArrayWrapper implements AbstractWrapper {
    private final Struct container;
    private final Struct index;
    public ArrayWrapper(Struct _container,Struct _index) {
        container = _container;
        index = _index;
    }
    public void setValue(StackCall _stack, ContextEl _conf, Argument _right) {
        ExecTemplates.setElement(container,index,_right.getStruct(),_conf, _stack);
    }

    public Struct getValue(StackCall _stack, ContextEl _conf) {
        return ExecTemplates.getElement(container,index,_conf, _stack);
    }

}
