package code.expressionlanguage.utilcompo;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.WithoutParentIdStruct;
import code.expressionlanguage.utilimpl.LgNamesUtils;
import code.threads.AbstractAtomicBoolean;
import code.threads.AbstractThreadFactory;

public abstract class AbsScheduledExecutorServiceStruct extends WithoutParentIdStruct implements AbsExecutorServiceStruct {
    private final AbstractAtomicBoolean stopped;

    protected AbsScheduledExecutorServiceStruct(AbstractThreadFactory _e) {
        stopped = _e.newAtomicBoolean();
    }

    public AbstractAtomicBoolean getStopped() {
        return stopped;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return ((LgNamesUtils)_contextEl.getStandards()).getExecContent().getCustAliases().getAliasScheduledExecutorService();
    }
}
