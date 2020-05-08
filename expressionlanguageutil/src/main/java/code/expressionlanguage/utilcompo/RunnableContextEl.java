package code.expressionlanguage.utilcompo;

import code.expressionlanguage.*;
import code.expressionlanguage.errors.AnalysisMessages;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.Struct;
import code.stream.Locking;

import java.util.concurrent.atomic.AtomicBoolean;

public class RunnableContextEl extends ContextEl implements Locking {

    private CustInitializer custInit;
    private AtomicBoolean interrupt;

    private ExecutingOptions executingOptions;

    private ThreadStruct thread;
    private String idDate;

    protected RunnableContextEl(int _stackOverFlow, DefaultLockingClass _lock,
                      CustInitializer _init, Options _options, ExecutingOptions _exec,
                                AnalysisMessages _mess,
                                KeyWords _keyWords, LgNames _stds, int _tabWidth) {
        super(_exec.isCovering(),_stackOverFlow, _lock, _options, _mess,_keyWords, _stds, _tabWidth);
        custInit = _init;
        executingOptions = _exec;
        interrupt = _exec.getInterrupt();
        setThread();
    }
    protected RunnableContextEl(ContextEl _context) {
        getInitializingTypeInfos().setInitEnums(InitPhase.NOTHING);
        setClasses(_context.getClasses());
        setOptions(_context.getOptions());
        setStandards(_context.getStandards());
        setTabWidth(_context.getTabWidth());
        setStackOverFlow(_context.getStackOverFlow());
        setMemoryError(_context.getMemoryError());
        setAnalysisMessages(_context.getAnalysisMessages());
        setKeyWords(_context.getKeyWords());
        setThrowing(_context.getThrowing());
        setCovering(_context.isCovering());
        setCoverage(_context.getCoverage());
        setExecutingInstance(this);
        executingOptions = ((RunnableContextEl)_context).executingOptions;
        interrupt = ((RunnableContextEl)_context).interrupt;
        custInit = ((RunnableContextEl)_context).getCustInit();
        setThread();
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

    @Override
    public void initError() {
        setMemoryError(new ErrorStruct(this, getStandards().getAliasError()));
    }
    @Override
    public Initializer getInit() {
        return getCustInit();
    }

    public CustInitializer getCustInit() {
        return custInit;
    }

    public ShowUpdates putInThread(Struct _info, ProgressingTests _progressingTests) {
        return new ShowUpdates(_info,this,_progressingTests);
    }
    public ExecutingOptions getExecutingOptions() {
        return executingOptions;
    }

    @Override
    public boolean callsOrException() {
        if (stopped()) {
            return true;
        }
        return super.callsOrException();
    }

    protected EndCallValue removeCall() {
        try {
            return super.removeCall();
        } catch (OutOfMemoryError _0) {
            setException(getMemoryError());
            getThrowing().removeBlockFinally(this);
            if (hasException()) {
                return EndCallValue.EXIT;
            }
            return EndCallValue.NEXT;
        }
    }
    protected void processTags() {
        try {
            super.processTags();
        } catch (OutOfMemoryError _0) {
            setException(getMemoryError());
            getThrowing().removeBlockFinally(this);
        }
    }

    boolean stopped() {
        return interrupt.get() || isCurrentThreadEnded();
    }

    public void interrupt() {
        interrupt.set(true);
    }

}
