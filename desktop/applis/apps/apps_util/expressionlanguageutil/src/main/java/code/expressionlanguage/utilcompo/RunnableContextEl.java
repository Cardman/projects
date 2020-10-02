package code.expressionlanguage.utilcompo;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.fwd.Forwards;
import code.threads.Locking;

public class RunnableContextEl extends ContextEl implements Locking {

    private ThreadStruct thread;
    private String idDate;

    public RunnableContextEl(InitPhase _state, CommonExecutionInfos _executionInfos) {
        super(_executionInfos, _state);
        setFullStack(new DefaultFullStack(this));
        setThread();
    }

    @Override
    public void forwardAndClear(AnalyzedPageEl _ana, Forwards _forwards) {
        super.forwardAndClear(_ana, _forwards);
        LgNamesWithNewAliases standards_ = (LgNamesWithNewAliases) getStandards();
        standards_.forwardAndClear(getClasses());
    }

    @Override
    public boolean isCurrentThreadEnded() {
        return thread.isEnded();
    }

    @Override
    public Thread getCurrentThread() {
        return thread.getThread();
    }

    public ThreadStruct getThread() {
        return thread;
    }

    private void setThread() {
        thread = new ThreadStruct(Thread.currentThread());
    }

    String getIdDate() {
        return idDate;
    }

    void setIdDate(String _idDate) {
        idDate = _idDate;
    }


    public CustInitializer getCustInit() {
        return (CustInitializer)getInit();
    }

    public ExecutingOptions getExecutingOptions() {
        return ((LgNamesWithNewAliases) getStandards()).getExecutingOptions();
    }

    @Override
    public boolean callsOrException() {
        if (stopped()) {
            return true;
        }
        return super.callsOrException();
    }

    boolean stopped() {
        return ((LgNamesWithNewAliases) getStandards()).getExecutingOptions().getInterrupt().get() || isCurrentThreadEnded();
    }

    public void interrupt() {
        ((LgNamesWithNewAliases) getStandards()).getExecutingOptions().getInterrupt().set(true);
    }

}
