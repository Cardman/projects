package code.expressionlanguage.exec.variables;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public final class VariableWrapper implements AbstractWrapper {
    private LocalVariable local = LocalVariable.newLocalVariable(NullStruct.NULL_VALUE,"");

    private Struct value;
    public void setValue(ContextEl _conf, Argument _right) {
        ExecTemplates.checkSet(_conf,local,_right);
        value = _right.getStruct();
    }

    public Struct getValue() {
        return value;
    }

    public void setLocal(LocalVariable _local) {
        local = _local;
        value = _local.getStruct();
    }
}
