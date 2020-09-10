package code.expressionlanguage.utilcompo;

import code.expressionlanguage.*;
import code.expressionlanguage.exec.DefaultLockingClass;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.Initializer;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.Struct;
import code.threads.Locking;
import code.util.StringList;

import java.util.concurrent.atomic.AtomicBoolean;

public class RunnableContextEl extends ContextEl implements Locking {

    private CustInitializer custInit;
    private AtomicBoolean interrupt;

    private ExecutingOptions executingOptions;

    private ThreadStruct thread;
    private String idDate;
    private ExecRootBlock executeType;
    private ExecNamedFunctionBlock executeMethod;
    private ExecRootBlock formatType;
    private ExecNamedFunctionBlock formatObject;
    private ExecNamedFunctionBlock formatObjectTwo;
    private ExecRootBlock runnableType;
    private ExecNamedFunctionBlock runMethod;

    protected RunnableContextEl(int _stackOverFlow, DefaultLockingClass _lock,
                                CustInitializer _init, Options _options, ExecutingOptions _exec,
                                KeyWords _keyWords, LgNames _stds, int _tabWidth) {
        super(_stackOverFlow, _lock, _options, _stds, _tabWidth);
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
        setCovering(_context.isCovering());
        setCoverage(_context.getCoverage());
        executingOptions = ((RunnableContextEl)_context).executingOptions;
        interrupt = ((RunnableContextEl)_context).interrupt;
        executeType = ((RunnableContextEl)_context).executeType;
        executeMethod = ((RunnableContextEl)_context).executeMethod;
        formatType = ((RunnableContextEl)_context).formatType;
        formatObject = ((RunnableContextEl)_context).formatObject;
        formatObjectTwo = ((RunnableContextEl)_context).formatObjectTwo;
        runnableType = ((RunnableContextEl)_context).runnableType;
        runMethod = ((RunnableContextEl)_context).runMethod;
        custInit = ((RunnableContextEl)_context).getCustInit();
        setThread();
    }

    @Override
    public void forwardAndClear() {
        super.forwardAndClear();
        LgNamesUtils standards_ = (LgNamesUtils) getStandards();
        String aliasExecute_ = standards_.getAliasExecute();
        executeType = getClasses().getClassBody(aliasExecute_);
        String infoTest_ = standards_.getAliasInfoTest();
        MethodId fct_ = new MethodId(MethodAccessKind.STATIC,
                standards_.getAliasExecuteTests(),new StringList(infoTest_));
        executeMethod = ExecBlock.getMethodBodiesById(executeType,fct_).first();
        formatType = getClasses().getClassBody(standards_.getAliasFormatType());
        formatObject = ExecBlock.getMethodBodiesById(formatType,new MethodId(MethodAccessKind.STATIC, standards_.getAliasPrint(),new StringList(standards_.getAliasObject()))).first();
        formatObjectTwo = ExecBlock.getMethodBodiesById(formatType,new MethodId(MethodAccessKind.STATIC, standards_.getAliasPrint(),new StringList(standards_.getAliasString(),standards_.getAliasObject()),true)).first();
        runnableType = getClasses().getClassBody(standards_.getAliasRunnable());
        runMethod = ExecBlock.getMethodBodiesById(runnableType,new MethodId(MethodAccessKind.INSTANCE, standards_.getAliasRun(),new StringList())).first();
    }

    public ExecRootBlock getExecuteType() {
        return executeType;
    }

    public ExecNamedFunctionBlock getExecuteMethod() {
        return executeMethod;
    }

    public ExecRootBlock getFormatType() {
        return formatType;
    }

    public ExecNamedFunctionBlock getFormatObject() {
        return formatObject;
    }

    public ExecNamedFunctionBlock getFormatObjectTwo() {
        return formatObjectTwo;
    }

    public ExecRootBlock getRunnableType() {
        return runnableType;
    }

    public ExecNamedFunctionBlock getRunMethod() {
        return runMethod;
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

    boolean stopped() {
        return interrupt.get() || isCurrentThreadEnded();
    }

    public void interrupt() {
        interrupt.set(true);
    }

}
