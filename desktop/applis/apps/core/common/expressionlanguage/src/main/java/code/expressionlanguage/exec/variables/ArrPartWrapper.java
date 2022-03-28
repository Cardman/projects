package code.expressionlanguage.exec.variables;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecArrayTemplates;
import code.expressionlanguage.structs.RangeStruct;
import code.expressionlanguage.structs.Struct;

public final class ArrPartWrapper implements AbstractWrapper {
    private final Struct array;
    private final RangeStruct range;

    public ArrPartWrapper(Struct _array,RangeStruct _range) {
        this.array = _array;
        this.range = _range;
    }

    @Override
    public void setValue(StackCall _stack, ContextEl _conf, Argument _right) {
        ExecArrayTemplates.setRange(array, range, _right.getStruct(), _conf, _stack);
    }

    @Override
    public Struct getValue(StackCall _stack, ContextEl _conf) {
        return ExecArrayTemplates.getRange(array,range,_conf,_stack);
    }

    @Override
    public String getClassName(StackCall _stack, ContextEl _conf) {
        return array.getClassName(_conf);
    }
}
