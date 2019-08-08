package code.expressionlanguage;

import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ErrorStruct;

import java.util.concurrent.atomic.AtomicBoolean;

public class RunnableContextEl extends ContextEl {

    private CustInitializer custInit;
    private AtomicBoolean interrupt;

    private ExecutingOptions executingOptions;

    RunnableContextEl(int _stackOverFlow, DefaultLockingClass _lock,
                      CustInitializer _init, Options _options, ExecutingOptions _exec, KeyWords _keyWords, LgNames _stds, int _tabWidth) {
        super(_exec.isCovering(),_stackOverFlow, _lock, _options, _keyWords, _stds, _tabWidth);
        custInit = _init;
        executingOptions = _exec;
        interrupt = _exec.getInterrupt();
    }
    RunnableContextEl(ContextEl _context) {
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

    public ExecutingOptions getExecutingOptions() {
        return executingOptions;
    }

    public void setExecutingOptions(ExecutingOptions _executing) {
        executingOptions = _executing;
    }
    @Override
    public boolean hasException() {
        return super.hasException() && !interrupt.get();
    }
    public void interrupt() {
        interrupt.set(true);
    }

}
