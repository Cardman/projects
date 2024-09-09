package code.expressionlanguage.exec.variables;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecArrayTemplates;
import code.expressionlanguage.structs.RangeStruct;
import code.expressionlanguage.structs.Struct;

public final class ArrPartWrapper extends ValueWrapper {
    private final Struct array;
    private final RangeStruct range;

    public ArrPartWrapper(Struct _v,Struct _array,RangeStruct _range) {
        super(_v);
        this.array = _array;
        this.range = _range;
    }

    @Override
    public void setValue(StackCall _stack, ContextEl _conf, Struct _right) {
        setValue(_right);
        ExecArrayTemplates.setRange(array, range, _right, _conf, _stack);
    }

    @Override
    public Struct getValue(StackCall _stack, ContextEl _conf) {
        return ExecArrayTemplates.getRange(array,range,_conf,_stack);
    }

    @Override
    public String getClassName(ContextEl _conf) {
        return array.getClassName(_conf);
    }

    public Struct getArray() {
        return array;
    }

    public RangeStruct getRange() {
        return range;
    }
}
