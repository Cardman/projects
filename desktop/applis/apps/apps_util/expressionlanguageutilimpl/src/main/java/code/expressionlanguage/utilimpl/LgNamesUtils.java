package code.expressionlanguage.utilimpl;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.coverage.Coverage;
import code.expressionlanguage.functionid.*;
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
    public LgNamesUtils(FileInfos _infos) {
        super(_infos.getGenerator());
        custAliases.setInfos(_infos);
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

    public FileInfos getInfos() {
        return infos;
    }

    public AbstractFunctionalInstance newFunctionalInstance(String _className, ExecRootBlock _rootBlock, LambdaStruct _functional, ExecNamedFunctionBlock _named, ContextEl _contextEl){
        return CustAliases.newFunctional(_className, _rootBlock, _functional, _named, _contextEl);
    }

    @Override
    public AbstractFunctionalInstance newFullFunctionalInstance(String _className, ExecRootBlock _rootBlock, LambdaStruct _functional, ExecNamedFunctionBlock _named, ContextEl _contextEl) {
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
    public ContextEl newContext(int _tabWidth, int _stack, Coverage _coverage) {
        return new RunnableContextEl(InitPhase.READ_ONLY_OTHERS, new CommonExecutionInfos(_tabWidth, _stack, this, new Classes(new ClassesCommon()), _coverage, new DefaultLockingClass(), new CustInitializer()));
    }
}
