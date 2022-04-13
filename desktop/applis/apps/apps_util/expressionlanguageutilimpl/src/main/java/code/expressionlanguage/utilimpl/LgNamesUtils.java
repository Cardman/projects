package code.expressionlanguage.utilimpl;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LgNames;
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
    public void build() {
        buildBase();
        buildOther();
    }

    public void buildOther() {
        custAliases.buildOther(getContent(),getExecutingBlocks());
    }
    @Override
    public StringStruct getStringOfObject(ContextEl _cont, Struct _arg) {
        return CustAliases.getStringOfObjectUtil(_cont, _arg);
    }


    @Override
    public AbstractInterceptor getInterceptor() {
        return custAliases.getInterceptor();
    }

    public FileInfos getInfos() {
        return infos;
    }

    public AbstractFunctionalInstance newFunctionalInstance(ExecFormattedRootBlock _className, LambdaStruct _functional, ExecNamedFunctionBlock _named, ContextEl _contextEl){
        return CustAliases.newFunctional(_className, _functional, _named, _contextEl);
    }

    @Override
    public AbstractFunctionalInstance newFullFunctionalInstance(ExecFormattedRootBlock _className, LambdaStruct _functional, ExecNamedFunctionBlock _named, ContextEl _contextEl) {
        return CustAliases.newFunctional(_className, _functional, _named, _contextEl);
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
        return new RunnableContextEl(InitPhase.READ_ONLY_OTHERS, new CommonExecutionInfos(getCustAliases().getInterceptor().newInterceptorStdCaller(getCustAliases().getAliasConcurrentError()),new CommonExecutionMetricsInfos(_opt.getTabWidth(),_opt.getStack(),_opt.getSeedGene()),this,_options.getClasses(), _options.getCoverage(), new DefaultLockingClass(),new CustInitializer(infos.getThreadFactory().newAtomicLong(),getCustAliases().getInterceptor())));
    }

}
