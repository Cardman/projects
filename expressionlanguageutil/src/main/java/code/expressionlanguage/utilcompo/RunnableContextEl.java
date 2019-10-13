package code.expressionlanguage.utilcompo;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultLockingClass;
import code.expressionlanguage.EndCallValue;
import code.expressionlanguage.Initializer;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.Struct;

import java.util.concurrent.atomic.AtomicBoolean;

public class RunnableContextEl extends ContextEl {

    private CustInitializer custInit;
    private AtomicBoolean interrupt;

    private ExecutingOptions executingOptions;

    private ThreadStruct thread;
    private String idDate;
    private long number;
    protected RunnableContextEl(int _stackOverFlow, DefaultLockingClass _lock,
                      CustInitializer _init, Options _options, ExecutingOptions _exec, KeyWords _keyWords, LgNames _stds, int _tabWidth) {
        super(_exec.isCovering(),_stackOverFlow, _lock, _options, _keyWords, _stds, _tabWidth);
        custInit = _init;
        executingOptions = _exec;
        interrupt = _exec.getInterrupt();
    }
    protected RunnableContextEl(ContextEl _context) {
        setClasses(_context.getClasses());
        setOptions(_context.getOptions());
        setStandards(_context.getStandards());
        setTabWidth(_context.getTabWidth());
        setStackOverFlow(_context.getStackOverFlow());
        setMemoryError(_context.getMemoryError());
        setKeyWords(_context.getKeyWords());
        setThrowing(_context.getThrowing());
        setCovering(_context.isCovering());
        setCoverage(_context.getCoverage());
        setExecutingInstance(this);
        executingOptions = ((RunnableContextEl)_context).executingOptions;
        interrupt = ((RunnableContextEl)_context).interrupt;
        custInit = ((RunnableContextEl)_context).getCustInit();
    }

    public ThreadStruct getThread() {
        return thread;
    }

    public void setThread(ThreadStruct _thread) {
        thread = _thread;
    }

    public String getIdDate() {
        return idDate;
    }

    public void setIdDate(String _idDate) {
        idDate = _idDate;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long _number) {
        number = _number;
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

    public void setExecutingOptions(ExecutingOptions _executing) {
        executingOptions = _executing;
    }
    protected EndCallValue removeCall() {
        if (interrupt.get()) {
            return EndCallValue.EXIT;
        }
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
    @Override
    public boolean hasException() {
        return super.hasException() && !interrupt.get();
    }
    public void interrupt() {
        interrupt.set(true);
    }

}
