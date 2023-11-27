package code.expressionlanguage.utilcompo;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.guicompos.stds.FctCompoInvokeLater;
import code.expressionlanguage.structs.Struct;
import code.threads.AbstractAtomicBoolean;
import code.threads.AbstractThread;
import code.threads.ThState;

public final class ThreadDbgStruct extends AbsThreadStruct {

    private boolean started;
    private boolean joined;
    private int pr = 5;
    public ThreadDbgStruct(AbstractThread _thread, AbstractAtomicBoolean _ended, Struct _r) {
        super(_thread, _ended, _r);
    }

    @Override
    public boolean isAlive() {
        return started;
    }

    @Override
    public void startThread() {
        started = true;
    }

    @Override
    public ThState joinThread(ContextEl _ctx, StackCall _stackCall, String _intro) {
        if (!started || joined) {
            return ThState.ENDED;
        }
        FctCompoInvokeLater.procRunnable(_ctx,_stackCall,_stackCall.getStopper().getLogger(),getRunnable(), _intro);
        joined = true;
        return ThState.ALIVE;
    }

    @Override
    public boolean setPriorityTh(int _prio) {
        pr = _prio;
        return true;
    }

    @Override
    public int getPriority() {
        return pr;
    }
}
