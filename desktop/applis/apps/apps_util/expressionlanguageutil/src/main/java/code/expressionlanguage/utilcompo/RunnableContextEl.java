package code.expressionlanguage.utilcompo;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.coverage.Coverage;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LgNames;
import code.threads.Locking;
import code.util.StringList;

public class RunnableContextEl extends ContextEl implements Locking {

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
                                LgNames _stds, int _tabWidth, ClassesCommon _com) {
        super(new CommonExecutionInfos(_tabWidth, _stackOverFlow, _stds, new Classes(_com), new Coverage(_options.isCovering()), _lock, _init));
        setFullStack(new DefaultFullStack(this));
        executingOptions = _exec;
        setThread();
    }
    protected RunnableContextEl(ContextEl _context) {
        super(_context.getExecutionInfos());
        setFullStack(new DefaultFullStack(this));
        getInitializingTypeInfos().setInitEnums(InitPhase.NOTHING);
        executingOptions = ((RunnableContextEl)_context).executingOptions;
        executeType = ((RunnableContextEl)_context).executeType;
        executeMethod = ((RunnableContextEl)_context).executeMethod;
        formatType = ((RunnableContextEl)_context).formatType;
        formatObject = ((RunnableContextEl)_context).formatObject;
        formatObjectTwo = ((RunnableContextEl)_context).formatObjectTwo;
        runnableType = ((RunnableContextEl)_context).runnableType;
        runMethod = ((RunnableContextEl)_context).runMethod;
        setThread();
    }

    @Override
    public void forwardAndClear(AnalyzedPageEl _ana, Forwards _forwards) {
        super.forwardAndClear(_ana, _forwards);
        LgNamesWithNewAliases standards_ = (LgNamesWithNewAliases) getStandards();
        String aliasExecute_ = standards_.getCustAliases().getAliasExecute();
        executeType = _ana.getClasses().getClassBody(aliasExecute_);
        String infoTest_ = standards_.getCustAliases().getAliasInfoTest();
        MethodId fct_ = new MethodId(MethodAccessKind.STATIC,
                standards_.getCustAliases().getAliasExecuteTests(),new StringList(infoTest_));
        executeMethod = ExecBlock.getMethodBodiesById(executeType,fct_).first();
        formatType = _ana.getClasses().getClassBody(standards_.getCustAliases().getAliasFormatType());
        formatObject = ExecBlock.getMethodBodiesById(formatType,new MethodId(MethodAccessKind.STATIC, standards_.getCustAliases().getAliasPrint(),new StringList(getStandards().getAliasObject()))).first();
        formatObjectTwo = ExecBlock.getMethodBodiesById(formatType,new MethodId(MethodAccessKind.STATIC, standards_.getCustAliases().getAliasPrint(),new StringList(getStandards().getAliasString(),getStandards().getAliasObject()),true)).first();
        runnableType = _ana.getClasses().getClassBody(standards_.getCustAliases().getAliasRunnable());
        runMethod = ExecBlock.getMethodBodiesById(runnableType,new MethodId(MethodAccessKind.INSTANCE, standards_.getCustAliases().getAliasRun(),new StringList())).first();
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


    public CustInitializer getCustInit() {
        return (CustInitializer)getInit();
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
        return executingOptions.getInterrupt().get() || isCurrentThreadEnded();
    }

    public void interrupt() {
        executingOptions.getInterrupt().set(true);
    }

}
