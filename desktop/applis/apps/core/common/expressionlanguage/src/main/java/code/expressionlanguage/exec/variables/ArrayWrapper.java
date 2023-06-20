package code.expressionlanguage.exec.variables;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecArrayTemplates;
import code.expressionlanguage.exec.opers.ExecArrayFieldOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.Struct;
import code.util.core.StringUtil;

public final class ArrayWrapper extends ValueWrapper {
    private final Struct container;
    private final Struct index;
    public ArrayWrapper(Struct _v, Struct _container,Struct _index) {
        super(_v);
        container = _container;
        index = _index;
    }
    public void setValue(StackCall _stack, ContextEl _conf, Argument _right) {
        setValue(ArgumentListCall.toStr(_right));
        ExecArrayTemplates.setElement(container,index,_right.getStruct(),_conf, _stack);
    }

    public Struct getValue(StackCall _stack, ContextEl _conf) {
        return ExecArrayTemplates.getElement(container,index,_conf, _stack);
    }

    @Override
    public String getClassName(ContextEl _conf) {
        ArrayStruct arr_ = ExecArrayFieldOperation.getArray(container, _conf);
        return StringUtil.nullToEmpty(StringExpUtil.getQuickComponentType(arr_.getClassName()));
    }
}
