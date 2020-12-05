package code.expressionlanguage.exec.variables;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.structs.Struct;

public final class ArrayWrapper implements AbstractWrapper {
    private final Struct container;
    private final Struct index;
    public ArrayWrapper(Struct _container,Struct _index) {
        container = _container;
        index = _index;
    }
    public void setValue(ContextEl _conf, Argument _right) {
        ExecTemplates.setElement(container,index,_right.getStruct(),_conf);
    }

    public Struct getValue(ContextEl _conf) {
        return ExecTemplates.getElement(container,index,_conf);
    }

}
