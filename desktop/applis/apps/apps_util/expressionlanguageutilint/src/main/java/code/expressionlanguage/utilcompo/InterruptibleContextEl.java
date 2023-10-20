package code.expressionlanguage.utilcompo;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.CommonExecutionInfos;
import code.expressionlanguage.structs.Struct;
import code.threads.AbstractAtomicBoolean;

public class InterruptibleContextEl extends ContextEl {

    public InterruptibleContextEl(AbstractAtomicBoolean _i,CommonExecutionInfos _executionInfos) {
        super(_i,_executionInfos);
    }

    @Override
    public ContextEl copy(Struct _state) {
        return copy(getInterrupt(),_state);
    }

    @Override
    public ContextEl copy(AbstractAtomicBoolean _i, Struct _state) {
        return new InterruptibleContextEl(_i,getExecutionInfos());
    }

    public void stopJoinSleep() {
        interrupt();
    }
    public void interrupt() {
        getInterrupt().set(true);
    }

}
