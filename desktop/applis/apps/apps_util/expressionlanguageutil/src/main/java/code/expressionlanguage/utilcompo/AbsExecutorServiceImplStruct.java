package code.expressionlanguage.utilcompo;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.WithoutParentIdStruct;
import code.expressionlanguage.utilimpl.LgNamesUtils;
import code.threads.AbstractAtomicBoolean;

public abstract class AbsExecutorServiceImplStruct extends WithoutParentIdStruct implements AbsExecutorServiceStruct {
    private final AbstractAtomicBoolean stopped;

    protected AbsExecutorServiceImplStruct(AbstractAtomicBoolean _shut) {
        stopped = _shut;
    }

    public AbstractAtomicBoolean getStopped() {
        return stopped;
    }


    @Override
    public String getClassName(ContextEl _contextEl) {
        return ((LgNamesUtils)_contextEl.getStandards()).getExecContent().getCustAliases().getAliasExecutorService();
    }
}
