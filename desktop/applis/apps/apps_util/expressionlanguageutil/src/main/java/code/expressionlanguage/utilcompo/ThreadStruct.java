package code.expressionlanguage.utilcompo;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.structs.Struct;
import code.threads.AbstractAtomicBoolean;
import code.threads.AbstractThread;
import code.threads.ThState;

public final class ThreadStruct extends AbsThreadStruct {

    public ThreadStruct(AbstractThread _thread,AbstractAtomicBoolean _ended, Struct _r) {
        super(_thread, _ended, _r);
    }

    @Override
    public boolean isAlive() {
        return getThread().isAlive();
    }

    @Override
    public void startThread() {
        getThread().start();
    }

    @Override
    public ThState joinThread(ContextEl _ctx, StackCall _stackCall, String _intro) {
        return getThread().join();
    }

    @Override
    public boolean setPriorityTh(int _prio) {
        return getThread().setPriority(_prio);
    }

    @Override
    public int getPriority() {
        return getThread().getPriority();
    }
}
