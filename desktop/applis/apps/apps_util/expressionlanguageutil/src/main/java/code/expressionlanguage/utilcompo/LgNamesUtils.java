package code.expressionlanguage.utilcompo;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.AbstractFunctionalInstance;
import code.expressionlanguage.structs.LambdaStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.util.StringMap;

public class LgNamesUtils extends LgNames implements LgNamesWithNewAliases {
    protected static final String RUN = "Run";
    private CustAliases custAliases = new CustAliases();

    private FileInfos infos;

    private ExecutingOptions executingOptions;

    private ExecutingBlocks executingBlocks = new ExecutingBlocks();
    public LgNamesUtils(FileInfos _infos) {
        super(_infos.getGenerator());
        custAliases.setInfos(_infos);
        infos = _infos;
    }
    public void forwardAndClear(Classes _classes) {
        executingBlocks.forwardAndClear(getContent(),custAliases,_classes);
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
    public Argument defaultInstance(ContextEl _cont, String _id) {
        return custAliases.defaultInstance(_cont,_id);
    }
    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont,
                                         ConstructorId _method, Struct... _args) {
        return custAliases.getOtherResult(_cont,_method,_args);
    }

    @Override
    protected ResultErrorStd instance(ContextEl _cont, ConstructorId _method, Argument... _args) {
        return custAliases.instance(_cont, _method, _args);
    }

    protected ResultErrorStd invoke(ContextEl _cont, ClassMethodId _method, Struct _struct, AbstractExiting _exit, Argument... _args) {
        return custAliases.invoke(_cont, _method, _struct, _exit, _args);
    }
    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont, Struct _instance,
            ClassMethodId _method, Struct... _args) {
        return custAliases.getOtherResult(_cont,_instance,_method,_args);
    }

    public FileInfos getInfos() {
        return infos;
    }

    public AbstractFunctionalInstance newFunctionalInstance(String _className, ExecRootBlock _rootBlock,LambdaStruct _functional,ContextEl _contextEl){
        return CustAliases.newFunctional(_className, _rootBlock, _functional, _contextEl);
    }

    @Override
    public AbstractFunctionalInstance newFullFunctionalInstance(String _className,ExecRootBlock _rootBlock, LambdaStruct _functional,ContextEl _contextEl) {
        return CustAliases.newFunctional(_className, _rootBlock, _functional, _contextEl);
    }

    public void otherAlias(String _lang, StringMap<String>_cust) {
        custAliases.otherAlias(getContent(),_lang,_cust);
    }
    public void allAlias(StringMap<String> _util, StringMap<String> _cust) {
        custAliases.allAlias(getContent(),_util,_cust);
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

    public void setExecutingOptions(ExecutingOptions executingOptions) {
        this.executingOptions = executingOptions;
    }
}
