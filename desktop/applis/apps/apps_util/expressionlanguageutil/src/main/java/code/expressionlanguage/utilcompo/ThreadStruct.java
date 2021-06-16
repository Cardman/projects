package code.expressionlanguage.utilcompo;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.structs.WithoutParentIdStruct;
import code.threads.AbstractAtomicBoolean;
import code.threads.AbstractThread;

public final class ThreadStruct extends WithoutParentIdStruct implements Struct {

    private final AbstractThread thread;

    private final AbstractAtomicBoolean ended;

    public ThreadStruct(AbstractThread _thread,AbstractAtomicBoolean _ended) {
        thread = _thread;
        ended = _ended;
    }

    public boolean isEnded() {
        return ended.get();
    }

    public void end() {
        ended.set(true);
    }

    public AbstractThread getThread() {
        return thread;
    }
    public boolean start(){
        if (thread.isAlive()) {
            return false;
        }
        thread.start();
        return true;
    }
    public boolean setPriority(int _prio) {
        if (_prio > Thread.MAX_PRIORITY || _prio < Thread.MIN_PRIORITY) {
            return false;
        }
        thread.setPriority(_prio);
        return true;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return ((LgNamesWithNewAliases)_contextEl.getStandards()).getCustAliases().getAliasThread();
    }

}
