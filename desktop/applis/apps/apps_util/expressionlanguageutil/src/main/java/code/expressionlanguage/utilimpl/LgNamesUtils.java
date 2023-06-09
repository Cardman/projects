package code.expressionlanguage.utilimpl;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.CommonExecutionInfos;
import code.expressionlanguage.exec.CommonExecutionMetricsInfos;
import code.expressionlanguage.exec.DefaultLockingClass;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.AbstractFunctionalInstance;
import code.expressionlanguage.structs.LambdaStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.*;
import code.threads.AbstractAtomicBoolean;
import code.util.StringList;

public class LgNamesUtils extends LgNames implements LgNamesWithNewAliases {
    private final LgNamesUtilsContent execContent;
    private AbstractAtomicBoolean atomicBoolean;

    public LgNamesUtils(FileInfos _infos, AbstractInterceptor _inter) {
        super(_infos.getGenerator());
        execContent = new LgNamesUtilsContent(_infos, _inter);
    }

    @Override
    public LgNamesUtilsContent getExecContent() {
        return execContent;
    }

    @Override
    public void build() {
        buildBase();
        buildOther();
    }

    public void buildOther() {
        execContent.getCustAliases().buildOther(getContent(),execContent.getExecutingBlocks());
    }

    @Override
    public StringStruct getStringOfObject(ContextEl _cont, Struct _arg) {
        return CustAliases.getStringOfObjectUtil(_cont, _arg);
    }

    @Override
    public AbstractFunctionalInstance newFullFunctionalInstance(ExecFormattedRootBlock _className, LambdaStruct _functional, ExecNamedFunctionBlock _named, ContextEl _contextEl) {
        return CustAliases.newFunctional(_className, _functional, _named, _contextEl);
    }

    @Override
    public ContextEl newContext(Options _opt,Forwards _options) {
        return new RunnableContextEl(atomicBoolean,null, new CommonExecutionInfos(execContent.getCustAliases().getInterceptor().newInterceptorStdCaller(execContent.getCustAliases().getAliasConcurrentError()),new CommonExecutionMetricsInfos(_opt.getTabWidth(),_opt.getStack(),_opt.getSeedGene()),this,_options.getClasses(), _options.getCoverage(), new DefaultLockingClass(),new CustInitializer(execContent.getInfos().getThreadFactory().newAtomicLong(),execContent.getCustAliases().getInterceptor())), new StringList());
    }

    public AbstractAtomicBoolean getAtomicBoolean() {
        return atomicBoolean;
    }

    public void setAtomicBoolean(AbstractAtomicBoolean _a) {
        this.atomicBoolean = _a;
    }
}
