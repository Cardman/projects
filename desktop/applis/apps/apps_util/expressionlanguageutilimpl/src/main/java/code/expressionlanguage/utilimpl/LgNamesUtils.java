package code.expressionlanguage.utilimpl;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AbstractFieldFilter;
import code.expressionlanguage.analyze.DefaultFieldFilter;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.AbstractFunctionalInstance;
import code.expressionlanguage.structs.LambdaStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.*;

public class LgNamesUtils extends LgNames implements LgNamesWithNewAliases {
    private final CustAliases custAliases = new CustAliases();

    private final FileInfos infos;

    private ExecutingOptions executingOptions;

    private final ExecutingBlocks executingBlocks = new ExecutingBlocks();
    public LgNamesUtils(FileInfos _infos, AbstractInterceptor _inter) {
        super(_infos.getGenerator());
        custAliases.setInfos(_infos);
        custAliases.setInterceptor(_inter);
        infos = _infos;
    }
    public void forwardAndClear(Classes _classes) {
        executingBlocks.forwardAndClear(getContent(),custAliases,_classes);
    }

    @Override
    public void logIssue(String _info) {
        AbstractIssuer issuer_ = infos.getLogger().getIssuer();
        if (issuer_ != null) {
            issuer_.log(_info);
        }
    }

    @Override
    public void buildOther() {
        custAliases.buildOther(getContent());
    }
    @Override
    public StringStruct getStringOfObject(ContextEl _cont, Struct _arg) {
        return CustAliases.getStringOfObjectUtil(_cont, _arg);
    }

    @Override
    public Argument defaultInstance(ContextEl _cont, String _id, StackCall _stackCall) {
        return custAliases.defaultInstance(_cont,_id, _stackCall);
    }

    @Override
    protected ResultErrorStd instance(StackCall _stack, ContextEl _cont, ConstructorId _method, Argument... _args) {
        return custAliases.instance(_cont, _method, _stack, _args);
    }
    @Override
    public ResultErrorStd getOtherResult(StackCall _stack, ContextEl _cont,
                                         ConstructorId _method, Struct... _args) {
        return custAliases.getOtherResult(_cont,_method, _stack,_args);
    }

    protected ResultErrorStd invoke(StackCall _stack, ContextEl _cont, ClassMethodId _method, Struct _struct, AbstractExiting _exit, Argument... _args) {
        return custAliases.invoke(_cont, _method, _struct, _exit, _stack, _args);
    }
    @Override
    public ResultErrorStd getOtherResult(StackCall _stack, ContextEl _cont, Struct _instance,
                                         ClassMethodId _method, Struct... _args) {
        return custAliases.getOtherResult(_cont,_instance,_method, executingBlocks, _stack, _args);
    }

    @Override
    public AbstractInterceptor getInterceptor() {
        return custAliases.getInterceptor();
    }

    public FileInfos getInfos() {
        return infos;
    }

    public AbstractFunctionalInstance newFunctionalInstance(ExecFormattedRootBlock _className, ExecRootBlock _rootBlock, LambdaStruct _functional, ExecNamedFunctionBlock _named, ContextEl _contextEl){
        return CustAliases.newFunctional(_className, _rootBlock, _functional, _named, _contextEl);
    }

    @Override
    public AbstractFunctionalInstance newFullFunctionalInstance(ExecFormattedRootBlock _className, ExecRootBlock _rootBlock, LambdaStruct _functional, ExecNamedFunctionBlock _named, ContextEl _contextEl) {
        return CustAliases.newFunctional(_className, _rootBlock, _functional, _named, _contextEl);
    }

    public CustAliases getCustAliases() {
        return custAliases;
    }

    public ExecutingBlocks getExecutingBlocks() {
        return executingBlocks;
    }

    public ExecutingOptions getExecutingOptions() {
        return executingOptions;
    }

    public void setExecutingOptions(ExecutingOptions _executingOptions) {
        this.executingOptions = _executingOptions;
    }

    @Override
    public ContextEl newContext(Options _opt,Forwards _options) {
        return new RunnableContextEl(InitPhase.READ_ONLY_OTHERS, new CommonExecutionInfos(_opt.getTabWidth(),_opt.getStack(),this,_options.getClasses(), _options.getCoverage(), new DefaultLockingClass(),new DefaultInitializer()));
    }

    @Override
    public AbstractFieldFilter newFieldFilter() {
        return new DefaultFieldFilter();
    }
}
