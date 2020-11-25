package code.expressionlanguage.exec.variables;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.structs.Struct;

public final class ArrayWrapper implements AbstractWrapper {
    private Struct container;
    private Struct index;
    private Struct value;
    public void setValue(ContextEl _conf, Argument _right) {
        ExecTemplates.setElement(container,index,_right.getStruct(),_conf);
        value = _right.getStruct();
    }

    public Struct getValue() {
        return value;
    }

    public void setContainer(Struct _container) {
        this.container = _container;
    }

    public void setIndex(Struct _index) {
        this.index = _index;
    }

    public void setValue(Struct _value) {
        this.value = _value;
    }
}
