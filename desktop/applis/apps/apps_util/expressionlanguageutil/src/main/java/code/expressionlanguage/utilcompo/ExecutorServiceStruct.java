package code.expressionlanguage.utilcompo;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.structs.WithoutParentIdStruct;
import code.expressionlanguage.utilimpl.LgNamesUtils;
import code.threads.AbstractBaseExecutorService;
import code.threads.AbstractFuture;
import code.threads.AbstractThreadFactory;

public final class ExecutorServiceStruct extends WithoutParentIdStruct {
    private final AbstractBaseExecutorService executorService;
//    private final CustList<AbstractFuture> futures = new CustList<AbstractFuture>();

    public ExecutorServiceStruct(AbstractThreadFactory _e) {
        this.executorService = _e.newExecutorService();
    }

    public ExecutorServiceStruct(AbstractThreadFactory _e, int _nbThreads) {
        this.executorService = _e.newExecutorService(_nbThreads);
    }

    public void execute(Runnable _command) {
        executorService.execute(_command);
    }

    public Struct submit(Runnable _command) {
        AbstractFuture s_ = executorService.submit(_command);
//        futures.add(s_);
        return new FutureStruct(s_);
    }

    public void shutdown() {
//        for (AbstractFuture f: futures) {
//            f.attendre();
//        }
        executorService.shutdown();
//        futures.clear();
    }
    @Override
    public String getClassName(ContextEl _contextEl) {
        return ((LgNamesUtils)_contextEl.getStandards()).getCustAliases().getAliasExecutorService();
    }
}
