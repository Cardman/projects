package code.expressionlanguage.exec.variables;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecArrayTemplates;
import code.expressionlanguage.exec.inherits.ExecInheritsAdv;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;

public final class ReflectVariableWrapper extends AbstractVariableWrapper {

    private final ArrayStruct array;
    private final int ind;
    public ReflectVariableWrapper(LocalVariable _local) {
        this(_local,new ArrayStruct(0,""),0);
    }

    public ReflectVariableWrapper(LocalVariable _local, ArrayStruct _arr, int _index) {
        super(_local);
        array = _arr;
        ind = _index;
    }

    @Override
    public Struct getValue(StackCall _stack, ContextEl _conf) {
        Struct v_ = getLocal().getStruct();
        if (array.isValid(ind)) {
            _stack.getInitializingTypeInfos().addSensibleField(array,v_);
        }
        return v_;
    }

    @Override
    public void setValue(StackCall _stack, ContextEl _conf, Struct _right) {
        setValue(_right);
        String formattedClassVar_ = getLocal().getClassName();
        ExecInheritsAdv.checkQuick(formattedClassVar_, _right.getClassName(_conf), _conf, _stack);
        if (!_conf.callsOrException(_stack)&&array.isValid(ind)) {
            ExecArrayTemplates.setElement(array,new IntStruct(ind),_right,_conf,_stack);
        }
        if (!_conf.callsOrException(_stack)) {
            getLocal().setStruct(_right);
        }
    }

    @Override
    public String getClassName(ContextEl _conf) {
        return getLocal().getStruct().getClassName(_conf);
    }
}
