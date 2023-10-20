package code.expressionlanguage;

import code.expressionlanguage.exec.CommonExecutionInfos;
import code.expressionlanguage.structs.Struct;
import code.threads.AbstractAtomicBoolean;
import code.threads.ConcreteBoolean;

public final class SingleInterruptedContextEl extends ContextEl {

    public SingleInterruptedContextEl(CommonExecutionInfos _executionInfos, ConcreteBoolean _conc) {
        super(_conc,_executionInfos);
    }

    @Override
    public ContextEl copy(Struct _state) {
        return null;
    }

    @Override
    public ContextEl copy(AbstractAtomicBoolean _i, Struct _state) {
        return null;
    }
}
