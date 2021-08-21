package code.expressionlanguage.utilcompo;

import code.expressionlanguage.*;
import code.expressionlanguage.exec.*;
import code.stream.core.AbstractZipFact;
import code.threads.AbstractThread;
import code.threads.AbstractThreadFactory;
import code.threads.Locking;

public class RunnableContextEl extends ContextEl implements Locking {

    private final ThreadStruct thread;
    private final AbstractThreadFactory threadFactory;
    private final AbstractZipFact zipFact;
    private String idDate;
    private String currentDir;

    public RunnableContextEl(InitPhase _state, CommonExecutionInfos _executionInfos) {
        super(_executionInfos);
        LgNamesWithNewAliases standards_ = (LgNamesWithNewAliases) _executionInfos.getStandards();
        threadFactory = standards_.getInfos().getThreadFactory();
        thread = new ThreadStruct(threadFactory.newThread(), threadFactory.newAtomicBoolean());
        currentDir = standards_.getExecutingOptions().getBaseFiles();
        zipFact = standards_.getInfos().getZipFact();
    }

    @Override
    public void forwardAndClear() {
        super.forwardAndClear();
        LgNamesWithNewAliases standards_ = (LgNamesWithNewAliases) getStandards();
        standards_.forwardAndClear(getClasses());
    }

    @Override
    public boolean isCurrentThreadEnded() {
        return thread.isEnded();
    }

    @Override
    public AbstractThreadFactory getCurrentThreadFactory() {
        return threadFactory;
    }

    @Override
    public AbstractThread getCurrentThread() {
        return thread.getThread();
    }

    public ThreadStruct getThread() {
        return thread;
    }

    String getIdDate() {
        return idDate;
    }

    void setIdDate(String _idDate) {
        idDate = _idDate;
    }

    public String getCurrentDir() {
        return currentDir;
    }

    public void setCurrentDir(String _currentDir) {
        this.currentDir = _currentDir;
    }

    public CustInitializer getCustInit() {
        return (CustInitializer)getInit();
    }

    public ExecutingOptions getExecutingOptions() {
        return ((LgNamesWithNewAliases) getStandards()).getExecutingOptions();
    }

    @Override
    public boolean callsOrException(StackCall _stack) {
        if (stopped()) {
            return true;
        }
        return super.callsOrException(_stack);
    }

    boolean stopped() {
        return ((LgNamesWithNewAliases) getStandards()).getExecutingOptions().getInterrupt().get() || isCurrentThreadEnded();
    }

    public void interrupt() {
        ((LgNamesWithNewAliases) getStandards()).getExecutingOptions().getInterrupt().set(true);
    }

    public AbstractZipFact getZipFact() {
        return zipFact;
    }

}
