package code.expressionlanguage.exec.variables;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecArrayTemplates;
import code.expressionlanguage.exec.inherits.ExecVariableTemplates;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.IntStruct;

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
    public void setValue(StackCall _stack, ContextEl _conf, Argument _right) {
        if (ExecVariableTemplates.checkSet(_conf,getLocal(),_right, _stack)&&array.isValid(ind)){
            ExecArrayTemplates.setElement(array,new IntStruct(ind),_right.getStruct(),_conf,_stack);
        }
    }

    @Override
    public String getClassName(StackCall _stack, ContextEl _conf) {
        return getLocal().getStruct().getClassName(_conf);
    }
}
