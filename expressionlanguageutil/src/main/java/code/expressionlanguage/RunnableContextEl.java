package code.expressionlanguage;

import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ErrorStruct;

import java.util.concurrent.atomic.AtomicBoolean;

public final class RunnableContextEl extends ContextEl {

    private CustInitializer custInit;
    private AtomicBoolean interrupt;

    private ExecutingOptions executing;

    RunnableContextEl(int _stackOverFlow, DefaultLockingClass _lock,
                      CustInitializer _init, Options _options, ExecutingOptions _exec, KeyWords _keyWords, LgNames _stds, int _tabWidth) {
        super(_exec.isCovering(),_stackOverFlow, _lock, _options, _keyWords, _stds, _tabWidth);
        custInit = _init;
        executing = _exec;
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
        executing = ((RunnableContextEl)_context).executing;
        interrupt = ((RunnableContextEl)_context).interrupt;
        custInit = (CustInitializer) _context.getInit();
    }
    @Override
    public void initError() {
        setMemoryError(new ErrorStruct(this, getStandards().getAliasError()));
    }
    @Override
    public CustInitializer getInit() {
        return custInit;
    }

    public ExecutingOptions getExecuting() {
        return executing;
    }

    public void setExecuting(ExecutingOptions _executing) {
        executing = _executing;
    }
    @Override
    public boolean hasException() {
        return super.hasException() && !interrupt.get();
    }
    public void interrupt() {
        interrupt.set(true);
    }

}
