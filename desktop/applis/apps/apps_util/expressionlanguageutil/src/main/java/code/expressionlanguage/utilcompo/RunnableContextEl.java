package code.expressionlanguage.utilcompo;

import code.expressionlanguage.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.structs.Struct;
import code.stream.core.AbstractZipFact;
import code.threads.AbstractThreadFactory;
import code.threads.Locking;
import code.util.StringList;

public class RunnableContextEl extends ContextEl implements Locking {

    private final ThreadStruct thread;
    private final AbstractThreadFactory threadFactory;
    private final AbstractZipFact zipFact;
    private String idDate;
    private String currentDir;
    private StringList args;

    public RunnableContextEl(Struct _state, CommonExecutionInfos _executionInfos, StringList _args) {
        super(_executionInfos);
        args = _args;
        LgNamesWithNewAliases standards_ = (LgNamesWithNewAliases) _executionInfos.getStandards();
        threadFactory = standards_.getExecContent().getInfos().getThreadFactory();
        thread = new ThreadStruct(threadFactory.newThread(), threadFactory.newAtomicBoolean(),_state);
        currentDir = standards_.getExecContent().getExecutingOptions().getBaseFiles();
        zipFact = standards_.getExecContent().getInfos().getZipFact();
    }

    public StringList getArgs() {
        return args;
    }

    public void setArgs(StringList _a) {
        this.args = _a;
    }

    @Override
    public void forwardAndClear() {
        super.forwardAndClear();
        LgNamesWithNewAliases standards_ = (LgNamesWithNewAliases) getStandards();
        standards_.getExecContent().forwardAndClear(standards_.getContent(),getClasses());
    }

    @Override
    public boolean isCurrentThreadEnded() {
        return thread.isEnded();
    }

    @Override
    public AbstractThreadFactory getCurrentThreadFactory() {
        return threadFactory;
    }

//    @Override
//    public AbstractThread getCurrentThread() {
//        return thread.getThread();
//    }

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
        return ((LgNamesWithNewAliases) getStandards()).getExecContent().getExecutingOptions();
    }

    @Override
    public boolean callsOrException(StackCall _stack) {
        if (stopped()) {
            return true;
        }
        return super.callsOrException(_stack);
    }

    boolean stopped() {
        return ((LgNamesWithNewAliases) getStandards()).getExecContent().getExecutingOptions().getInterrupt().get() || isCurrentThreadEnded();
    }

    public void interrupt() {
        ((LgNamesWithNewAliases) getStandards()).getExecContent().getExecutingOptions().getInterrupt().set(true);
    }

    public AbstractZipFact getZipFact() {
        return zipFact;
    }

}
