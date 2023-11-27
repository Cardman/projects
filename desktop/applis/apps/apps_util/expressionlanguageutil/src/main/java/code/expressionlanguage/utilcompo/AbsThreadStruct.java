package code.expressionlanguage.utilcompo;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.structs.WithoutParentIdStruct;
import code.threads.AbstractAtomicBoolean;
import code.threads.AbstractThread;
import code.threads.ThState;

public abstract class AbsThreadStruct extends WithoutParentIdStruct {

    public static final int MAX_PRIORITY = 10;
    public static final int MIN_PRIORITY = 1;
    private final AbstractThread thread;

    private final AbstractAtomicBoolean ended;
    private final Struct runnable;

    protected AbsThreadStruct(AbstractThread _thread, AbstractAtomicBoolean _ended, Struct _r) {
        thread = _thread;
        ended = _ended;
        runnable = _r;
    }

    public boolean isEnded() {
        return ended.get();
    }

    public void end() {
        ended.set(true);
    }

    public abstract boolean isAlive();
    public abstract ThState joinThread(ContextEl _ctx,StackCall _stackCall, String _intro);
    public abstract void startThread();

    public abstract int getPriority();
    public abstract boolean setPriorityTh(int _prio);
    public AbstractThread getThread() {
        return thread;
    }

    public Struct getRunnable() {
        return runnable;
    }

    public boolean start(){
        if (isAlive()) {
            return false;
        }
        startThread();
        return true;
    }
    public boolean setPriority(int _prio) {
        if (_prio > MAX_PRIORITY || _prio < MIN_PRIORITY) {
            return false;
        }
        return setPriorityTh(_prio);
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return ((LgNamesWithNewAliases)_contextEl.getStandards()).getExecContent().getCustAliases().getAliasThread();
    }
}
